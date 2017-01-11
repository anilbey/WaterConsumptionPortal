package it.polimi.awt.springmvc.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import it.polimi.awt.springmvc.domain.Household;
import it.polimi.awt.springmvc.domain.User;

/**
 * @author anil
 *
 */
@Repository
public class JpaUserRepository implements IUserRepository {


	@PersistenceContext
	private EntityManager em;
	
	/* (non-Javadoc)
	 * @see it.polimi.awt.springmvc.repository.IUserRepository#numberOfNeighbours(it.polimi.awt.springmvc.domain.Household)
	 */
	public Integer numberOfNeighbours(Household household)
	{
		try {
			Query query = em.createQuery("SELECT count(h) FROM Household h where :buildingOid = h.buildingOid");
			query.setParameter("buildingOid",household.getBuildingOid());
			List results = query.getResultList();
			String resultString = String.valueOf(results.get(0));
			return new Integer(resultString);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new Integer(0);
		}	
	}

	/* (non-Javadoc)
	 * @see it.polimi.awt.springmvc.repository.IUserRepository#retrieveUser(java.lang.String, java.lang.String)
	 */
	public User retrieveUser(String username, String password) {
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username and u.password = :password", User.class);
			query.setParameter("username",username);
			query.setParameter("password", password);
			List<User> resultList = query.getResultList();
			User user = null;		
			if (!resultList.isEmpty()){
				user = resultList.get(0);
			}
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see it.polimi.awt.springmvc.repository.IUserRepository#allUsers()
	 */
	public List<User> allUsers()
	{
		Query query = em.createNamedQuery("User.findAll");
		List<User> results = (List<User>) query.getResultList();
		return results;
	}

}
