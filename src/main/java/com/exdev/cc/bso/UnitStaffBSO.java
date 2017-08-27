package com.exdev.cc.bso;

import java.util.ArrayList;
import java.util.List;

import com.exdev.cc.dao.BaseDAO;
import com.exdev.cc.dao.StaffDAO;
import com.exdev.cc.dao.UnitDAO;
import com.exdev.cc.dao.UnitStaffDAO;
import com.exdev.cc.model.Staff;
import com.exdev.cc.model.Unit;
import com.exdev.cc.model.UnitStaff;
import com.exdev.cc.web.dto.BaseDTO;
import com.exdev.cc.web.dto.UnitStaffDTO;

public class UnitStaffBSO extends BaseBSO {

	public static UnitStaff toUnitStaff(UnitStaffDTO usDto) throws BSOException {
		if (usDto == null)
			return null;
		UnitStaffDAO dao = new UnitStaffDAO();
		UnitStaff us;
		try {
			us = (UnitStaff) dao.find(usDto.getId());
			if (us == null) {
				throw new Exception("Error: null unitstaff");
			}

			us.setAssignDate(usDto.getAssignDate());
			us.setNotes(usDto.getNotes());
			us.setId(usDto.getId());
			us.setLatitude(usDto.getLatitude());
			us.setLongitude(usDto.getLongitude());

			dao.closeEntityManager();
		} catch (

		Exception e) {
			throw new BSOException("Error: cannot find unit staff by id", e);
		}

		return us;
	}

	public static UnitStaffDTO toUnitStaffDTO(UnitStaff entity) {
		if (entity == null)
			return null;
		String name = entity.getUnit().getName();
		name += entity.getStaff() != null ? "-" + entity.getStaff().getUser().getName() : "";
		UnitStaffDTO pcsDto = new UnitStaffDTO(entity.getId(), name, entity.getNotes(), entity.getAssignDate(),
				entity.getLongitude(), entity.getLatitude());
		pcsDto.setUnit(UnitBSO.toUnitDTO(entity.getUnit()));
		pcsDto.setStaff(StaffBSO.toStaffDTO(entity.getStaff()));
		return pcsDto;
	}

	public UnitStaffBSO() throws BSOException {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object buildEntity(Object o, BaseDTO dto) throws BSOException {
		UnitStaffDTO usDto = (UnitStaffDTO) dto;
		UnitStaff us = (UnitStaff) o;
		try {
			UnitDAO unitDao = new UnitDAO();
			StaffDAO staffDao = new StaffDAO();
			Unit unit;
			Staff staff = null;
			try {
				unit = (Unit) unitDao.find(usDto.getUnit().getId());
				if (unit == null)
					throw new Exception("Error: unit cannot be null");
				if (usDto.getStaff() != null)
					staff = (Staff) staffDao.find(usDto.getStaff().getId());
			} catch (Exception e) {
				throw new BSOException(e);
			}

			us.setAssignDate(usDto.getAssignDate());
			us.setNotes(dto.getNotes());
			us.setLatitude(usDto.getLatitude());
			us.setLongitude(usDto.getLongitude());
			us.setUnit(unit);
			us.setStaff(staff);

			unitDao.closeEntityManager();
			staffDao.closeEntityManager();
		} catch (Exception e) {
			throw new BSOException(e);
		}

		return us;
	}

	@Override
	protected BaseDAO createDAO() throws BSOException {
		return new UnitStaffDAO();
	}

	@Override
	protected BaseDTO toDTO(Object entity) {
		return toUnitStaffDTO((UnitStaff) entity);
	}

	@Override
	protected Object toEntity(BaseDTO dto) throws BSOException {
		UnitStaffDTO usDto = (UnitStaffDTO) dto;
		UnitStaff us = new UnitStaff();

		return buildEntity(us, usDto);
	}

	public List<UnitStaffDTO> listByStaffId(int staffid) throws BSOException {
		List<UnitStaffDTO> list;
		try {
			StaffDAO staffDao = new StaffDAO();
			Staff staff = (Staff) staffDao.find(staffid);
			List<UnitStaff> usList = staff.getUnitStaffs();
			list = new ArrayList<>(usList.size());
			for (UnitStaff us : usList) {
				list.add(toUnitStaffDTO(us));
			}
			staffDao.closeEntityManager();
		} catch (Exception e) {
			throw new BSOException(e);
		}

		return list;
	}

	public List<UnitStaffDTO> listByUnitd(int unitid) throws BSOException {
		List<UnitStaffDTO> list;
		try {
			UnitDAO unitDao = new UnitDAO();
			Unit unit = (Unit) unitDao.find(unitid);
			List<UnitStaff> usList = unit.getUnitStaffs();
			list = new ArrayList<>(usList.size());
			for (UnitStaff us : usList) {
				list.add(toUnitStaffDTO(us));
			}
			unitDao.closeEntityManager();
		} catch (Exception e) {
			throw new BSOException(e);
		}

		return list;
	}

}
