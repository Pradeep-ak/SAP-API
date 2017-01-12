package com.api.model.Release;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReleaseModel {

	private String id;
	
	@JsonFormat(pattern="MM/dd/yyyy")
	private Date releaseDate;
	private String releaseNumber;
	private List<String> sprints;
	private List<String> majorChanges;
	
	public ReleaseModel(String id, Date releaseDate, String releaseNumber, List<String> sprints,
			List<String> majorChanges) {
		super();
		this.id = id;
		this.releaseDate = releaseDate;
		this.releaseNumber = releaseNumber;
		this.sprints = sprints;
		this.majorChanges = majorChanges;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReleaseNumber() {
		return releaseNumber;
	}

	public void setReleaseNumber(String releaseNumber) {
		this.releaseNumber = releaseNumber;
	}

	public List<String> getSprints() {
		return sprints;
	}

	public void setSprints(List<String> sprints) {
		this.sprints = sprints;
	}

	public List<String> getMajorChanges() {
		return majorChanges;
	}

	public void setMajorChanges(List<String> majorChanges) {
		this.majorChanges = majorChanges;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
