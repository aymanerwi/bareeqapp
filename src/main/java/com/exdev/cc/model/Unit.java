package com.exdev.cc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the unit database table.
 * 
 */
@Entity
@Table(name="unit")
@NamedQuery(name = "Unit.findAll", query = "SELECT u FROM Unit u")
public class Unit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String make;

	@Column(name = "mobile_no")
	private String mobileNo;

	private String model;

	@Column(name = "model_year")
	private int modelYear;

	private String name;

	private String notes;

	@Column(name = "plate_no")
	private String plateNo;

	// bi-directional many-to-one association to Partner
	@ManyToOne
	private Partner partner;

	// bi-directional many-to-one association to UnitStaff
	@OneToMany(mappedBy = "unit")
	private List<UnitStaff> unitStaffs;

	public Unit() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getModelYear() {
		return this.modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
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

	public String getPlateNo() {
		return this.plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public List<UnitStaff> getUnitStaffs() {
		return this.unitStaffs;
	}

	public void setUnitStaffs(List<UnitStaff> unitStaffs) {
		this.unitStaffs = unitStaffs;
	}

	public UnitStaff addUnitStaff(UnitStaff unitStaff) {
		getUnitStaffs().add(unitStaff);
		unitStaff.setUnit(this);

		return unitStaff;
	}

	public UnitStaff removeUnitStaff(UnitStaff unitStaff) {
		getUnitStaffs().remove(unitStaff);
		unitStaff.setUnit(null);

		return unitStaff;
	}

}