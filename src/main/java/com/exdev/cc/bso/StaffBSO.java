package com.exdev.cc.bso;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.exdev.cc.dao.BaseDAO;
import com.exdev.cc.dao.PartnerDAO;
import com.exdev.cc.dao.StaffDAO;
import com.exdev.cc.dao.UserDAO;
import com.exdev.cc.model.Partner;
import com.exdev.cc.model.Staff;
import com.exdev.cc.model.User;
import com.exdev.cc.web.dto.BaseDTO;
import com.exdev.cc.web.dto.StaffDTO;
import com.exdev.cc.web.dto.UserDTO;

public class StaffBSO extends BaseBSO {

	public static StaffDTO toStaffDTO(Staff staff) {
		if (staff == null)
			return null;
		StaffDTO dto = new StaffDTO(staff.getId(), staff.getUser().getName(), staff.getNotes(), staff.getIdNo());
		dto.setPartner(PartnerBSO.toPartnerDTO(staff.getPartner()));
		UserDTO userDto = UserBSO.toUserDTO(staff.getUser());
		userDto.setStaff(true);
		dto.setUser(userDto);
		return dto;
	}

	public StaffBSO() throws BSOException {
		
	}

	@Override
	protected Object buildEntity(Object o, BaseDTO dto) throws BSOException {
		StaffDTO psdto = (StaffDTO) dto;
		UserDTO userDTO = psdto.getUser();
		Partner p;
		User user;
		try {
			p = (Partner) dao.getEm().find(Partner.class, psdto.getPartner().getId());
			if (p == null)
				throw new Exception("Error: null partner ( no partner found with id: " + psdto.getPartner().getId());
			user = (User) dao.getEm().find(User.class, userDTO.getId());
			if (user == null) {
				UserDAO userDao = new UserDAO();
				List<User> users = userDao.findByMobileNo(psdto.getUser().getMobileNo());
				if (users.size() > 0) {
					user = users.get(0);
					userDao.closeEntityManager();
				}
				if (user != null)
					user = dao.getEm().find(User.class, user.getId());
				if (user == null)
					user = new User();
			}
			user.setEmail(userDTO.getEmail());
			user.setMobileNo(userDTO.getMobileNo());
			user.setName(StringUtils.isEmpty(userDTO.getName()) ? psdto.getName() : userDTO.getName());
			user.setUsername(
					StringUtils.isEmpty(userDTO.getUsername()) ? userDTO.getMobileNo() : userDTO.getUsername());
		} catch (Exception e) {
			throw new BSOException("Error: in build Staff object", e);
		}

		Staff s = (Staff) o;
		s.setIdNo(psdto.getIdNo());
		s.setUser(user);
		s.setPartner(p);
		return s;
	}

	@Override
	protected BaseDAO createDAO() throws BSOException {
		return new StaffDAO();
	}

	@Override
	protected BaseDTO toDTO(Object entity) {
		Staff ps = (Staff) entity;
		return toStaffDTO(ps);
	}

	@Override
	protected Object toEntity(BaseDTO dto) throws BSOException {
		StaffDTO sdto = (StaffDTO) dto;
		Staff s = new Staff();
		buildEntity(s, sdto);
		return s;
	}

}
