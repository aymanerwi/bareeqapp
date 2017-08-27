package com.exdev.cc.web.dto;

public class ServiceRequestRatingDTO extends BaseDTO {
	private int rating;
	private String ratingComments;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getRatingComments() {
		return ratingComments;
	}

	public void setRatingComments(String ratingComments) {
		this.ratingComments = ratingComments;
	}

	public ServiceRequestRatingDTO(int id, String name, int rating, String ratingComments) {
		super(id, name);
		this.rating = rating;
		this.ratingComments = ratingComments;
	}

	public ServiceRequestRatingDTO() {
		super();
	}

}
