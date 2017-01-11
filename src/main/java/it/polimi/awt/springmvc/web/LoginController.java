package it.polimi.awt.springmvc.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.polimi.awt.springmvc.domain.Household;
import it.polimi.awt.springmvc.domain.User;
import it.polimi.awt.springmvc.service.IUserService;

/**
 * @author anil
 *
 */
@Controller
@RequestMapping("/portal")
@ComponentScan("it.polimi.awt.springmvc.service")
public class LoginController {


	@Autowired
	private IUserService userService;

	/**
	 * This method loads the login page
	 * @param model
	 * represents the model paramater to be sent to the view
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String loginView(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "portal/login";
	}

	/**
	 * This method is used for log-out functionality
	 * @param model
	 * represents the model paramater to be sent to the view
	 * @param session
	 * represents the session object
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		try {
			session.invalidate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//session is already invalid.
		}
		return "redirect:/portal";
	}

	/**
	 * This method is responsible of the user authentication
	 * @param session
	 * represents the session object
	 * @param username
	 * represents the username information
	 * @param password
	 * represents the password information
	 * @param model
	 * represents the model paramater to be sent to the view
	 * @return
	 */
	@RequestMapping(params = "login", method = RequestMethod.POST) // params to
																	// catch the
																	// button
																	// value
	public String login(HttpSession session, @RequestParam("username") String username,
			@RequestParam("password") String password, Model model) {

		try {
			User user = userService.retrieveUser(username, password);
			if (user != null) {

				session.setAttribute("user", user);
				Household household = user.getNeutralUser().getHousehold();
				String consumptionType = userService.consumptionType(household);
				session.setAttribute("consumptionType", consumptionType);

				return "redirect:/portal/consumption";
			} else
				model.addAttribute("error", "Wrong username password combination");
			return loginView(model);
		} catch (Exception e) {
			return "redirect:/portal";
		}
	}

}
