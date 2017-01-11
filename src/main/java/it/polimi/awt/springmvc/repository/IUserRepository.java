package it.polimi.awt.springmvc.repository;

import java.util.List;

import it.polimi.awt.springmvc.domain.Household;
import it.polimi.awt.springmvc.domain.User;

/**
 * @author anil
 *
 */
public interface IUserRepository {

	/**
	 * Retrieves all of the users
	 * @return
	 * returns all of the users
	 */
	public List<User> allUsers();
	/**
	 * Retrieves the user matched with the username and password
	 * @param username
	 * represents the username value
	 * @param password
	 * represents the password value
	 * @return
	 * returns the corresponding user to the given username & value
	 */
	public User retrieveUser(String username, String password);
	/**
	 * Retrieves the numberOfNeighbours belonging to a Household
	 * @param household
	 * @return
	 * returns the number of neighbours of the given household
	 */
	public Integer numberOfNeighbours(Household household);
}
