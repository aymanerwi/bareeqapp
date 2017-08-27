package com.exdev.cc.bso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.exdev.cc.dao.BaseDAO;
import com.exdev.cc.dao.UserDAO;
import com.exdev.cc.model.User;
import com.exdev.cc.web.dto.BaseDTO;
import com.exdev.cc.web.dto.UserDTO;
import com.exdev.cc.web.dto.UserLoginDTO;

public class UserBSO extends BaseBSO {

	public static UserDTO toUserDTO(User u) {
		if (u == null)
			return null;
		UserDTO dto = new UserDTO(u.getId(), u.getName(), u.getNotes(), u.getEmail(), u.getPassword(), u.getMobileNo(),
				u.getUsername());
		dto.setToken(u.getToken());
		dto.setLoginDate(u.getLoginDate());
		dto.setLogoutDate(u.getLogoutDate());
		dto.setAdmin(u.isAdmin());
		dto.setStaff(u.isStaff());
		dto.setCustomer(u.isCustomer());
		dto.setUuid(u.getUuid());
		return dto;
	}

	public UserBSO() throws BSOException {

	}

	public List<UserDTO> getByMobileNo(String mobileNo) throws BSOException {
		try {
			UserDAO userDao = (UserDAO) dao;
			List<User> users = userDao.findByMobileNo(mobileNo);
			List<UserDTO> dtos = new ArrayList<>(users.size());
			for (User user : users) {
				dtos.add((UserDTO) toDTO(user));
			}
			return dtos;
		} catch (Exception e) {
			throw new BSOException("Error: cannot find user by mobile no", e);
		}
	}

	@Override
	protected Object buildEntity(Object o, BaseDTO dto) throws BSOException {
		UserDTO userDTO = (UserDTO) dto;
		User user = (User) o;
		if (o == null)
			return null;
		user.setId(dto.getId());
		user.setEmail(userDTO.getEmail());
		user.setMobileNo(userDTO.getMobileNo());
		user.setName(userDTO.getName());
		user.setUsername(StringUtils.isEmpty(userDTO.getUsername()) ? userDTO.getMobileNo() : userDTO.getUsername());
		user.setNotes(dto.getNotes());
		user.setPassword(userDTO.getPassword());
		user.setAdmin(userDTO.isAdmin());
		user.setStaff(userDTO.isStaff());
		user.setCustomer(userDTO.isCustomer());
		user.setToken(userDTO.getToken());
		if (userDTO.getUuid() != null)
			user.setUuid(userDTO.getUuid());
		return user;
	}

	@Override
	public void create(BaseDTO dto) throws BSOException {
		UserDTO udto = (UserDTO) dto;
		String uuid = UUID.randomUUID().toString();
		udto.setUuid(uuid);
		super.create(dto);
	}

	@Override
	public void update(int id, BaseDTO dto) throws BSOException {
		try {
			User user = (User) dao.find(id);
			if (user.getUuid() == null || user.getUuid().isEmpty()) {
				UserDTO udto = (UserDTO) dto;
				String uuid = UUID.randomUUID().toString();
				udto.setUuid(uuid);
			}
			super.update(id, dto);
		} catch (Exception e) {
			throw new BSOException("Error: cannot update user", e);
		}
	}

	@Override
	protected BaseDAO createDAO() throws BSOException {
		return new UserDAO();
	}

	@Override
	protected BaseDTO toDTO(Object entity) {
		User u = (User) entity;
		return toUserDTO(u);
	}

	@Override
	protected Object toEntity(BaseDTO dto) throws BSOException {
		User user = new User();
		buildEntity(user, dto);
		return user;
	}

	public static User validateToken(String token) throws Exception {
		UserDAO dao = new UserDAO();
		User user = dao.findByToken(token);
		dao.closeEntityManager();
		return user;
	}

	public static User validateUUID(String uuid) throws Exception {
		UserDAO dao = new UserDAO();
		User user = dao.findByUUID(uuid);
		dao.closeEntityManager();
		return user;
	}

	public UserDTO login(UserLoginDTO dto) {
		UserDAO userDao = (UserDAO) dao;
		User user = null;
		try {
			user = userDao.findByUsername(dto.getUsername());
			if (user == null)
				return null;
			if (dto.getPassword().equals(user.getPassword())) {
				user.setLoginDate(new Date());
				user.setLogoutDate(null);
				userDao.startTransaction();
				userDao.save(user);
				userDao.commitTransaction();
				return toUserDTO(user);
			} else
				return null;
		} catch (Exception e) {
		}
		return null;
	}

	public void logout(String username) {
		UserDAO userDao = (UserDAO) dao;
		User user = null;
		try {
			user = userDao.findByUsername(username);

			if (user == null)
				return;
			user.setToken(null);
			user.setDisabled(true);
			user.setLogoutDate(new Date());
			user.setLoginDate(null);
			userDao.startTransaction();
			userDao.save(user);
			userDao.commitTransaction();
		} catch (Exception e) {
		}
	}

	public void updateToken(int id, String token) throws BSOException {
		try {
			UserDAO userDao = (UserDAO) dao;
			User user = (User) userDao.find(id);
			user.setToken(token);
			user.setModifyDate(new Date());
			user.setNotes("Update Token");
			userDao.startTransaction();
			userDao.save(user);
			userDao.commitTransaction();
		} catch (Exception e) {
			throw new BSOException("Error: cannot update token", e);
		}
	}

}
