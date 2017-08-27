package com.exdev.cc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the partner database table.
 * 
 */
@Entity
@Table(name="partner")
@NamedQuery(name = "Partner.findAll", query = "SELECT p FROM Partner p")
public class Partner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String address;

	@Column(name = "cr_no")
	private String crNo;

	private String email;

	@Column(name = "mobile_no")
	private String mobileNo;

	private String name;

	private String notes;

	// bi-directional many-to-one association to PartnerService
	@OneToMany(mappedBy = "partner")
	private List<PartnerService> partnerServices;

	// bi-directional many-to-one association to Unit
	@OneToMany(mappedBy = "partner")
	private List<Unit> units;

	public Partner() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCrNo() {
		return this.crNo;
	}

	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<PartnerService> getPartnerServices() {
		return this.partnerServices;
	}

	public void setPartnerServices(List<PartnerService> partnerServices) {
		this.partnerServices = partnerServices;
	}

	public PartnerService addPartnerService(PartnerService partnerService) {
		getPartnerServices().add(partnerService);
		partnerService.setPartner(this);

		return partnerService;
	}

	public PartnerService removePartnerService(PartnerService partnerService) {
		getPartnerServices().remove(partnerService);
		partnerService.setPartner(null);

		return partnerService;
	}

	public List<Unit> getUnits() {
		return this.units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public Unit addUnit(Unit unit) {
		getUnits().add(unit);
		unit.setPartner(this);

		return unit;
	}

	public Unit removeUnit(Unit unit) {
		getUnits().remove(unit);
		unit.setPartner(null);

		return unit;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Partner))
			return false;
		Partner p = (Partner) obj;
		return p.id == this.id;
	}
}