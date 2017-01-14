package com.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.CassandraConfig;
import com.api.cassandra.bean.Team;
import com.api.cassandra.repository.TeamRepository;

@Controller
@RequestMapping("/team")
public class TeamController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CassandraConfig.class);
    
	@Autowired
	private TeamRepository teamRepository;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Team> getTeamMember() {
		LOGGER.info("Request for all the team member data");
		List<Team> members = new ArrayList<Team>();
		teamRepository.findAll().forEach(e->members.add(e));
		for (Team team : members) {
			LOGGER.debug(team.print());
		}
		return members;
	}

	@RequestMapping(value="/member/{id}", method = RequestMethod.GET )
	@ResponseBody
	public Team getTeamMember(@PathVariable("id") String email) {
		LOGGER.info(String.format("Request for '%s' the team members data.", email) );
		Team member = teamRepository.findByName(email);
		LOGGER.debug(member.print());
	    return member;
	}
	
	@RequestMapping(value="/member/add", method = RequestMethod.PUT )
	@ResponseBody
	public String addTeamMember(@RequestBody Team member) {
		LOGGER.info(String.format("Request add '%s' the team members data.", member.print()));
		member.setChangeDate(new Date());
		teamRepository.save(member);
	    return "OK";
	}
	
	@RequestMapping(value="/member/update", method = RequestMethod.POST )
	@ResponseBody
	public String udpateTeamMember(@RequestBody Team member) {
		LOGGER.info(String.format("Request update '%s' the team members data.", member.print()));
		member.setChangeDate(new Date());
		teamRepository.save(member);
	    return "OK";
	}
}
