package it.polimi.awt.springmvc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.polimi.awt.springmvc.domain.Building;
import it.polimi.awt.springmvc.domain.District;
import it.polimi.awt.springmvc.domain.Household;
import it.polimi.awt.springmvc.domain.SmartMeter;
import it.polimi.awt.springmvc.domain.User;
import it.polimi.awt.springmvc.repository.IConsumptionRepository;
import it.polimi.awt.springmvc.repository.IUserRepository;
import it.polimi.awt.springmvc.utils.MapPOJO;
import it.polimi.awt.springmvc.utils.Utilities;

/**
 * @author anil
 *
 */
@Service
@Transactional
@ComponentScan("it.polimi.awt.springmvc.repository")
public class UserService implements IUserService {

	/**
	 * 
	 */
	@Autowired
	private IUserRepository userRepository;
	
	/**
	 * 
	 */
	@Autowired
	private IConsumptionRepository consumptionRepository;

	/* (non-Javadoc)
	 * @see it.polimi.awt.springmvc.service.IUserService#retrieveUser(java.lang.String, java.lang.String)
	 */
	public User retrieveUser(String username, String password) {
		// TODO Auto-generated method stub
		return userRepository.retrieveUser(username, password);
	}

	/* (non-Javadoc)
	 * @see it.polimi.awt.springmvc.service.IUserService#consumptionType(it.polimi.awt.springmvc.domain.Household)
	 */
	
	public String consumptionType(Household household) {
		// TODO Auto-generated method stub
		Integer numberOfNeighbours = userRepository.numberOfNeighbours(household);
		String consumptionData;
		if (numberOfNeighbours == 1)
			consumptionData = "individual";
		else if (numberOfNeighbours > 1)
			consumptionData = "common";
		else
			consumptionData = "no household information is found";
		return consumptionData;
	}

	/* (non-Javadoc)
	 * @see it.polimi.awt.springmvc.service.IUserService#getAddresses()
	 */
	
	public String getAddresses() {
		
		List<User> users = userRepository.allUsers();
		List<MapPOJO> data = new ArrayList<MapPOJO>();
		for (User user : users) {

			try {
				Household household = user.getNeutralUser().getHousehold();
				Building building = household.getBuilding();
				District district = building.getDistrict();
				String zipCode = district.getZipcode();
				String country = district.getCountry();
				String address = building.getAddress();
				
				String consumptionType = consumptionType(household);
				
				
				if (!Strings.isNullOrEmpty(zipCode) && !Strings.isNullOrEmpty(country)
						&& !Strings.isNullOrEmpty(address)) {
					
					SmartMeter smartMeterOfUser = consumptionRepository.getSmartMeter(household);
					LocalDate now = LocalDate.now();
					LocalDate oneMonthBefore = now.minusMonths(1);
					LocalDate oneWeekBefore = now.minusWeeks(1);
					
					LocalDateTime ldtNowMin = LocalDateTime.of(now, LocalTime.MIN);
					LocalDateTime ldtNowMax = LocalDateTime.of(now, LocalTime.MAX);
					
					String firstDate = consumptionRepository.smartMeterFirstEntryDate(smartMeterOfUser);
					String lastDate = consumptionRepository.smartMeterLastEntryDate(smartMeterOfUser);
					
					String[] splittedDate1 = firstDate.split("[- ]");
					String[] splittedDate2 = lastDate.split("[- ]");
					
					LocalDate firstLocalDate = LocalDate.of(Integer.valueOf(splittedDate1[0]),Integer.valueOf(splittedDate1[1]), Integer.valueOf(splittedDate1[2]));
					LocalDate secondLocalDate = LocalDate.of(Integer.valueOf(splittedDate2[0]),Integer.valueOf(splittedDate2[1]), Integer.valueOf(splittedDate2[2]));
					
					
					Double totalConsumption = consumptionRepository.smartMeterTotalConsumption(smartMeterOfUser);
					Double currentMonthlyAverage = totalConsumption / ((ChronoUnit.MONTHS.between(firstLocalDate, secondLocalDate)) + 1); 
					Double currentWeeklyAverage = totalConsumption / ((ChronoUnit.WEEKS.between(firstLocalDate, secondLocalDate)) + 1); 
					Double currentDailyAverage = totalConsumption / ((ChronoUnit.DAYS.between(firstLocalDate, secondLocalDate)) + 1); 
					
					
					data.add(new MapPOJO(totalConsumption, currentMonthlyAverage, currentWeeklyAverage, currentDailyAverage, new String(country + " " + zipCode + " " + address + " "), consumptionType));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				continue; // to skip the missing or incomplete address instances
			}

		}

		return Utilities.convertToJSONString(data);
	}
	
	
}
