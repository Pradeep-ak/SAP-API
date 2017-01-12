package com.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.model.Team.TeamMemberModel;

@Controller
@RequestMapping("/team")
public class TeamController {

	private Map<String, TeamMemberModel> teamList;
	
	@RequestMapping(value="/all")
	@ResponseBody
	public List<TeamMemberModel> getTeamMember() {
		loadConfig();
		return new ArrayList<TeamMemberModel>(teamList.values());
	}

	@RequestMapping(value="/member/{id}")
	@ResponseBody
	public TeamMemberModel getTeamMember(@PathVariable("id") String id) {
		loadConfig();
		return teamList.get(id);
	}
	
	private void loadConfig() {
		if(teamList == null){
			teamList = new HashMap<String, TeamMemberModel>();
			teamList.put("1",new TeamMemberModel("1","Pradeep","Kulkarni","pkulkar4@test.com","7259183375","Bangalore"));
			teamList.put("2",new TeamMemberModel("2","Naveen","Gundu","naveen@test.com","7259181234","Plano"));
			teamList.put("3",new TeamMemberModel("3","Harsha","Manjunath","pkulkar4@test.com","7259183123","Plano"));
			teamList.put("4",new TeamMemberModel("4","Nilesh","Shukla","nilesh@test.com","7259183312","Plano"));
			teamList.put("5",new TeamMemberModel("5","Uge","P","uge@test.com","7259183371","Plano"));
			teamList.put("6",new TeamMemberModel("6","Ravindra","H","ravindra@test.com","72591838976","Plano"));
			teamList.put("7",new TeamMemberModel("7","Sanal","S","sanal@test.com","7259181935","Plano"));
			teamList.put("8",new TeamMemberModel("8","Jithu","M","jithu@test.com","7259180000","Plano"));
		}
	}
}
