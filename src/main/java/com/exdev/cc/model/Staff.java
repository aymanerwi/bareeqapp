package com.exdev.cc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
 * The persistent class for the staff database table.
 * 
 */
@Entity
@Table(name="staff")
@NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s")
public class Staff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "id_no")
	private String idNo;

	private String notes;

	// bi-directional many-to-one association to User
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

	// bi-directional many-to-one association to UnitStaff
	@OneToMany(mappedBy = "staff")
	private List<UnitStaff> unitStaffs;

	// bi-directional many-to-one association to Partner
	@ManyToOne
	private Partner partner;

	public Staff() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<UnitStaff> getUnitStaffs() {
		return this.unitStaffs;
	}

	public void setUnitStaffs(List<UnitStaff> unitStaffs) {
		this.unitStaffs = unitStaffs;
	}

	public UnitStaff addUnitStaff(UnitStaff unitStaff) {
		getUnitStaffs().add(unitStaff);
		unitStaff.setStaff(this);

		return unitStaff;
	}

	public UnitStaff removeUnitStaff(UnitStaff unitStaff) {
		getUnitStaffs().remove(unitStaff);
		unitStaff.setStaff(null);

		return unitStaff;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

}