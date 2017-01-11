package it.polimi.awt.springmvc.web;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polimi.awt.springmvc.domain.User;
import it.polimi.awt.springmvc.service.IUserService;

/**
 * @author anil
 *
 */
@Controller
@RequestMapping("/portal")
@ComponentScan("it.polimi.awt.springmvc.service")
public class MapController {

	//autowiring of the map server
	@Autowired
	private IUserService userService;

	/**
	 * Loads the map view page
	 * @param model
	 * represents the model paramater to be sent to the view
	 * @param session
	 * represents the session object
	 * @return
	 */
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String mapView(Model model, HttpSession session) {
		try {
			User user = (User) session.getAttribute("user");
			if (user == null) {
				return "redirect:/portal";
			}

			String addressesJSON = userService.getAddresses();
			model.addAttribute("addresses", addressesJSON);
		} catch (Exception e) {
			 return "redirect:/portal";
		}
		return "portal/map";
	}

}
