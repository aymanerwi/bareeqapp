package com.exdev.cc.web.dto;

import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO implements Comparable<BaseDTO>, Comparator<BaseDTO> {

	private int id;
	private String name;
	private String notes;
	

	public BaseDTO() {

	}

	public BaseDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;

	}

	public BaseDTO(int id, String name, String notes) {
		super();
		this.id = id;
		this.name = name;
		this.notes = notes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public int compareTo(BaseDTO o) {
		if (o == null)
			return 1;
		if (!(o instanceof BaseDTO))
			return 1;
		BaseDTO dto = o;
		return this.id - dto.id;
	}

	@Override
	public int compare(BaseDTO o1, BaseDTO o2) {
		if (o1 == null)
			return -1;
		if (!(o1 instanceof BaseDTO))
			return -1;

		if (o2 == null)
			return 1;
		if (!(o2 instanceof BaseDTO))
			return 1;

		BaseDTO dto1 = o1;
		BaseDTO dto2 = o2;
		return dto1.id - dto2.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof BaseDTO))
			return false;
		BaseDTO dto = (BaseDTO) obj;
		return this.id == dto.id;
	}
}
