package it.polimi.awt.springmvc.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.polimi.awt.springmvc.domain.Household;
import it.polimi.awt.springmvc.domain.SmartMeter;
import it.polimi.awt.springmvc.domain.User;
import it.polimi.awt.springmvc.service.IConsumptionService;

/**
 * @author anil
 *
 */
@Controller
@RequestMapping("/portal")
@ComponentScan("it.polimi.awt.springmvc.service")
public class ConsumptionController {

	/**
	 * 
	 */
	@Autowired
	private IConsumptionService consumptionService;

	/**
	 * This method is called when the home page is loaded
	 * it fetches the user object from the session and sends the data to the corresponding view
	 * it redirects to the login page should an exception arise
	 * 
	 * @param session
	 * represents the session object
	 * @param model
	 * represents the model paramater to be sent to the view
	 * @param date1
	 * the date object entered to the first date selector
	 * @param date2
	 * the date object entered to the second date selector
	 * @return
	 * returns the view name
	 */
	@RequestMapping(value = "/consumption", method = RequestMethod.GET)
	public String loadHome(HttpSession session, Model model,
			@RequestParam(name = "firstDate", required = false) String date1,
			@RequestParam(name = "lastDate", required = false) String date2) {
		try {
			User user = (User) session.getAttribute("user");

			if (date1 == null || date2 == null) {
				date1 = "03/06/2015";
				date2 = "14/10/2015";
			}
			String firstDateAdjusted = processDate(date1);
			String secondDateAdjusted = processDate(date2);

			Household household = user.getNeutralUser().getHousehold();
			List<Household> households = consumptionService.getHouseholds(household); //returns the households within the same district
			session.setAttribute("households", households);

			SmartMeter householdSmartMeter = consumptionService.getSmartMeter(household);
			session.setAttribute("householdSmartMeter", householdSmartMeter);

			List<SmartMeter> neighbourhoodSmartMeters = consumptionService.getNeighbourhoodSmartMeters(households);
			session.setAttribute("neighbourhoodSmartMeters", neighbourhoodSmartMeters);

			String householdConsumption = consumptionService.getHouseholdConsumption(household, firstDateAdjusted,
					secondDateAdjusted, "Month", households, neighbourhoodSmartMeters, householdSmartMeter);

			model.addAttribute("user", user);
			model.addAttribute("consumptionType", session.getAttribute("consumptionType"));

			model.addAttribute("householdConsumption", householdConsumption);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "redirect:/portal";
		}

		return "portal/home";

	}

	/**
	 * This method updates the histogram asynchronously
	 * It returns the data as a map
	 * @param session
	 * represents the session object
	 * @param date1
	 * the date object entered to the first date selector
	 * @param date2
	 * the date object entered to the second date selector
	 * @param viewType
	 * represents the granularity of the view
	 * @return
	 * returns the data to be put in the updated histogram
	 */
	@ResponseBody
	@RequestMapping(value = "/consumptions", method = RequestMethod.GET)
	public Map<String, Object> refreshHistogramAsync(HttpSession session,
			@RequestParam(name = "firstDate", required = false) String date1,
			@RequestParam(name = "lastDate", required = false) String date2,
			@RequestParam(name = "viewRange", required = false) String viewType) {

		Map<String, Object> map;
		try {
			Household household = ((User) session.getAttribute("user")).getNeutralUser().getHousehold();
			List<Household> households = (List<Household>) session.getAttribute("households");
			SmartMeter householdSmartMeter = (SmartMeter) session.getAttribute("householdSmartMeter");
			List<SmartMeter> neighbourhoodSmartMeters = (List<SmartMeter>) session
					.getAttribute("neighbourhoodSmartMeters");
			String householdConsumption = consumptionService.getHouseholdConsumption(household, processDate(date1),
					processDate(date2), viewType, households, neighbourhoodSmartMeters, householdSmartMeter);

			map = new HashMap<String, Object>();

			map.put("consumptionType", session.getAttribute("consumptionType"));

			map.put("householdConsumption", householdConsumption);
		} catch (Exception e) {
			return null;
		}
		return map;

	}

	/**
	 * This method sends the parameters needed for the hourly-view of the histogram
	 * @param session
	 * represents the session object
	 * @param model
	 * represents the model paramater to be sent to the view
	 * @param date
	 * represents the day that is going to be visualized hourly
	 * @return
	 * returns the data to be put in the hourly-view
	 */
	@ResponseBody
	@RequestMapping(value = "/hourly-view", method = RequestMethod.GET)
	public Map<String, Object> hourlyView(HttpSession session, Model model,
			@RequestParam(name = "date", required = false) String date) {


		Map<String, Object> map;
		try {
			User user = (User) session.getAttribute("user");
			Household household = user.getNeutralUser().getHousehold();
			String hourlyConsumption = consumptionService.getHourlyConsumption(household, date);
			map = new HashMap<String, Object>();
			map.put("hourlyConsumption", hourlyConsumption);
		} catch (Exception e) {
			return null;
		}

		return map;
	}

	/**
	 * This method simply parses the date value into the supported format
	 * @param date
	 * the date value to be parsed
	 * @return
	 * returns the processed date
	 */
	private String processDate(String date) {
		String[] parts = date.split("/");
		String adjustedDate = parts[2] + "-" + parts[1] + "-" + parts[0];
		return adjustedDate;
	}

}