package it.polimi.awt.springmvc.service;

import java.util.List;

import it.polimi.awt.springmvc.domain.Household;
import it.polimi.awt.springmvc.domain.User;

/**
 * @author anil
 *
 */
public interface IUserService {
	/**
	 * Retrieves the user matched with the username and password
	 * @param username
	 * the parameter storing the username information
	 * @param password
	 * the parameter storing the password information
	 * @return
	 * returns the user retrieved
	 */
	public User retrieveUser(String username, String password);
	/**
	 * Retrieves the consumptionType of the SmartMeter belonging to the input Household
	 * @param household
	 * the parameter for the household object
	 * @return
	 * returns the consumption type as a string
	 */
	public String consumptionType(Household household);
	/**
	 * Retrieves the addresses of the users as a JSON String
	 * @return
	 * returns the addresses JSON string
	 */
	public String getAddresses();
}
		