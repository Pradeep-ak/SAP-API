package com.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.config.CassandraConfig;
import com.api.entity.Team;
import com.api.repository.TeamRepository;

@Controller
@RequestMapping("/team")
public class TeamController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CassandraConfig.class);
    
	@Autowired
	private TeamRepository teamRepository;
	
	@Value("${teamController.userName}")
	private String userName;
	
	@Value("${teamController.password}")
	private String password;
	
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
	public Team getTeamMember(@PathVariable("id") String id) {
		LOGGER.info(String.format("Request for '%s' the team members data.", id) );
		Team member = teamRepository.findByName(id);
		if (member != null) {
			LOGGER.debug(member.print());
		} else {
			LOGGER.debug(String.format("unable to find any member with ID=%s.", id));
		}
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
	
	@RequestMapping(value="/member/bulk", method = RequestMethod.POST)
	@ResponseBody
	public String udpateTeamMember(@RequestBody List<Team> memberList) {
		LOGGER.info(String.format("Request bulk update/add of team members."));
		for (Team team : memberList) {
			LOGGER.debug(String.format("Updating the member data of '%s', Team=%s" , team.getEmailId(), team.print()));
			team.setChangeDate(new Date());
			teamRepository.save(team);
		}
	    return "OK";
	}
	
	@RequestMapping(value="/member/delete/{id}", method = RequestMethod.DELETE )
	@ResponseBody
	public String deleteTeamMember(@RequestParam(value="userName") String userName,
			@RequestParam(value="password") String password,
			@PathVariable(value="id") String id,
			HttpServletResponse response) {
		if (userName == null || password == null
				 || !userName.equals(this.userName)
				 || !password.equals(this.password)) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return "userName & password not match.";
		}
		
		LOGGER.info(String.format("Request delete '%s' the team members data.", id));
		Team member = teamRepository.findByName(id);
		if (member != null) {
			LOGGER.debug(member.print());
		} else {
			LOGGER.debug(String.format("unable to find any member with ID=%s.", id));
		}
		teamRepository.delete(member);
	    return "OK";
	}
	
	@RequestMapping(value="/member/delete/all", method = RequestMethod.DELETE )
	@ResponseBody
	public String deleteAllTeamMember(@RequestParam(value="userName") String userName,
			@RequestParam(value="password") String password,
			HttpServletResponse response) {
		if (userName == null || password == null
				 || !userName.equals(this.userName)
				 || !password.equals(this.password)) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return "userName & password not match.";
		}
		LOGGER.info(String.format("Request delete all the team members data."));
		teamRepository.deleteAll();
	    return "OK";
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
