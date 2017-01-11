package com.sapient.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.model.Release.ReleaseModel;

@RestController
@RequestMapping("/release")
public class ReleaseController {
	
	private Map<String, ReleaseModel> releaseMap;
	
	@RequestMapping("")
    @ResponseBody
	public List<ReleaseModel> index() {
		loadConfig();
		return new ArrayList<>(releaseMap.values());
	}
	
	@RequestMapping("/{id}")
    @ResponseBody
	public ReleaseModel getReleaseDetails(@PathVariable("id") String Id) {
		loadConfig();
		return releaseMap.get(Id);
	}

	private void loadConfig() {
		if (releaseMap == null) {
			releaseMap = new HashMap<String, ReleaseModel>();
			releaseMap.put("1", new ReleaseModel("1", new Date(),"12.8",new ArrayList<String>(),new ArrayList<String>()));
			releaseMap.put("2",  new ReleaseModel("2", new Date(),"12.9",new ArrayList<String>(),new ArrayList<String>()));
			releaseMap.put("3",  new ReleaseModel("3", new Date(),"12.10",new ArrayList<String>(),new ArrayList<String>()));
			releaseMap.put("4",  new ReleaseModel("4", new Date(),"12.11",new ArrayList<String>(),new ArrayList<String>()));
		}
	}
}
