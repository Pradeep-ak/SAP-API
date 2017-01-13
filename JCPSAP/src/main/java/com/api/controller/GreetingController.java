package com.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.entity.Greeting;
import com.api.repository.GreetRepository;

@RestController
public class GreetingController {
	
	@Autowired
	private GreetRepository greetRepository;

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	@ResponseBody
	public List<Greeting> greeting() {
		List<Greeting> greetings = new ArrayList<Greeting>();
		Iterable<Greeting> greetItr = greetRepository.findAll();
		for (Iterator<Greeting> iterator = greetItr.iterator(); iterator.hasNext();) {
			Greeting greeting = (Greeting) iterator.next();
			greetings.add(greeting);
		}
		System.err.println(greetRepository.count());
		return greetings;
	}

	@RequestMapping(value = "/greeting/{user}/{limit}", method = RequestMethod.GET)
	@ResponseBody
	public List<Greeting> greetingUserLimit(@PathVariable(value="user") String user,
			@PathVariable(value="limit") Integer limit) {
		List<Greeting> greetings = new ArrayList<Greeting>();
		System.err.println(user+" "+limit);
		Iterable<Greeting> greetItr = greetRepository.findByUser(user, limit);
		for (Iterator<Greeting> iterator = greetItr.iterator(); iterator.hasNext();) {
			Greeting greeting = (Greeting) iterator.next();
			greetings.add(greeting);
		}
		return greetings;
	}

	@RequestMapping(value = "/greeting", method = RequestMethod.POST)
	@ResponseBody
	public String saveGreeting(@RequestBody Greeting greeting) {
		greeting.setCreationDate(new Date());
		greetRepository.save(greeting);
		return "OK";
	}
	
	@RequestMapping(value = "/greeting/update/{user}/{newGreet}", method = RequestMethod.GET)
	@ResponseBody
	public Greeting greetingUserUpdate(@PathVariable(value="user") String user,
			@PathVariable(value="newGreet") String newGreet) {
		Iterable<Greeting> greetItr = greetRepository.findByUser(user, 1);
		System.out.println("executed..");
		Greeting greeting = null;
		for (Iterator<Greeting> iterator = greetItr.iterator(); iterator.hasNext();) {
			greeting = (Greeting) iterator.next();
		}
		System.out.println("loaded..");
		
		if (greeting != null) {
			greeting.setGreet(newGreet);
			greeting.setCreationDate(new Date());
			System.out.println("updated data..");
			greetRepository.save(greeting);
			System.out.println("saved it..");
		}
		
		return greeting;
	}
}
