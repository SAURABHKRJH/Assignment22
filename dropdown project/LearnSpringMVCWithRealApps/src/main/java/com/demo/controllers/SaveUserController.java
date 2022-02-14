package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.entities.City;
import com.demo.entities.Country;
import com.demo.entities.CustomerEntity;
import com.demo.entities.State;
import com.demo.repositories.CityRepository;
import com.demo.repositories.CountryRepository;
import com.demo.repositories.CustomerCrudInf;
import com.demo.repositories.StateRepository;
@Controller
@RequestMapping(path = "/cust")
public class SaveUserController {
	  @Autowired
	     private CustomerCrudInf customerCrudInf; 
	  @Autowired
	  private CountryRepository countryRepository;
	  @Autowired
	  private StateRepository staterepository;
	  @Autowired
	  private CityRepository cityRepository; 
	  @RequestMapping(method = RequestMethod.GET)
      public String save(CustomerEntity customerEntity) {
		  return "demo/userform";
	}
	  @RequestMapping(method = RequestMethod.POST)
      public String saved(CustomerEntity customerEntity) {
		  //INSERTING COUNTRY NAME I\NSTEAD OF COUNTRY ID
		  Country country = countryRepository.findById(Integer.parseInt(customerEntity.getCountry())).get();
		  customerEntity.setCountry(country.getName());
		  //INSERTING STATE NAME INSTEAD OF STATE ID
		  State state = staterepository.findById(Integer.parseInt(customerEntity.getState())).get();
		  customerEntity.setState(state.getName());
		  //INSERTING CITY NAME INSTEAD OF CITY ID
		  City city = cityRepository.findById(Integer.parseInt(customerEntity.getCity())).get();
		  customerEntity.setCity(city.getName());
		customerCrudInf.save(customerEntity);
		return "demo/return";
	}
}