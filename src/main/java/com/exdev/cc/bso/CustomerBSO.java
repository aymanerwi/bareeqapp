package com.exdev.cc.bso;

import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.exdev.cc.dao.BaseDAO;
import com.exdev.cc.dao.CustomerDAO;
import com.exdev.cc.dao.UserDAO;
import com.exdev.cc.model.Customer;
import com.exdev.cc.model.User;
import com.exdev.cc.utils.CleanCarUtils;
import com.exdev.cc.web.dto.BaseDTO;
import com.exdev.cc.web.dto.CustomerDTO;
import com.exdev.cc.web.dto.UserDTO;

public class CustomerBSO extends BaseBSO {

	public void createCustomer(final CustomerDTO dto) throws BSOException {
		CustomerDTO existCust = getByMobileNo(dto.getUser().getMobileNo());
		CustomerDTO cdto = (CustomerDTO) dto;
		prepareConfirmation(cdto);
		if (existCust == null) {
			UserDTO userDTO = null;
			try {
				UserDAO userDao = new UserDAO();

				List<User> users = userDao.findByMobileNo(dto.getUser().getMobileNo());
				User existingUser = IterableUtils.find(users, new Predicate<User>() {

					@Override
					public boolean evaluate(User user) {
						return dto.getUser().getMobileNo().equals(user.getUsername());

					}

				});
				if (existingUser != null) {
					userDTO = UserBSO.toUserDTO(existingUser);
					userDTO.setCustomer(true);
					userDao.closeEntityManager();
				}

			} catch (Exception e) {
				throw new BSOException("Error: cannot create customer, unauble to find existing user with mobile no: "
						+ dto.getUser().getMobileNo(), e);
			}
			if (userDTO != null)
				cdto.setUser(userDTO);
			create(cdto);

		} else {
			dto.getUser().setId(existCust.getUser().getId());
			update(existCust.getId(), dto);
		}
		cdto.setCode(null);
		CleanCarUtils.sendSMS(cdto.getUser().getMobileNo(), "تطبيق بريق ، كود  التفعيل \n" + cdto.getSmscode());
		cdto.setSmscode(null);
	}

	public static Customer toCustomer(CustomerDTO dto) throws BSOException {
		if (dto == null)
			return null;
		Customer customer;
		try {
			CustomerDAO cDao = new CustomerDAO();
			customer = (Customer) cDao.getEm().find(Customer.class, dto.getId());
			if (customer == null)
				throw new Exception("Error: null customer");
			customer.setNotes(dto.getNotes());
			cDao.closeEntityManager();
		} catch (Exception e) {
			throw new BSOException("Error: cannot find customer by id: " + dto.getId(), e);
		}
		return customer;
	}

	public static CustomerDTO toCustomerDTO(Customer customer) {
		if (customer == null)
			return null;
		CustomerDTO dto = new CustomerDTO();
		dto.setId(customer.getId());
		dto.setNotes(customer.getNotes());
		dto.setName(customer.getUser().getName());
		dto.setCode(customer.getCode());
		UserDTO userDTO = UserBSO.toUserDTO(customer.getUser());
		dto.setUser(userDTO);
		return dto;
	}

	public CustomerBSO() throws BSOException {

	}

	@Override
	protected Object buildEntity(Object o, BaseDTO dto) throws BSOException {
		if (o == null || dto == null)
			return null;
		CustomerDTO cdto = (CustomerDTO) dto;
		Customer customer = (Customer) o;
		if (cdto.getCode() != null)
			customer.setCode(cdto.getCode());
		if (cdto.getNotes() != null)
			customer.setNotes(dto.getNotes());
		UserDTO userDTO = cdto.getUser();
		User user = null;
		try {
			user = (User) dao.getEm().find(User.class, userDTO.getId());
			if (user == null)
				user = new User();
			user.setEmail(userDTO.getEmail());
			user.setMobileNo(userDTO.getMobileNo());
			user.setName(userDTO.getName());
			user.setUsername(
					StringUtils.isEmpty(userDTO.getUsername()) ? userDTO.getMobileNo() : userDTO.getUsername());
			user.setNotes(dto.getNotes());
			if (user.getPassword() != null)
				user.setPassword(userDTO.getPassword());
			if (userDTO.getToken() != null)
				user.setToken(userDTO.getToken());
			customer.setUser(user);
		} catch (Exception e) {
			throw new BSOException("Error: cannot create user", e);
		}

		return customer;
	}

	public void confirm(int id, String code) throws BSOException {
		CustomerDTO cust = (CustomerDTO) get(id);
		if (cust == null)
			throw new BSOException("Customer not found");
		if (code.equals(cust.getCode())) {
			try {
				UserDTO userDTO = cust.getUser();
				User user = (User) dao.getEm().find(User.class, userDTO.getId());
				user.setDisabled(false);
				dao.startTransaction();
				dao.save(user);
				dao.commitTransaction();
			} catch (Exception e) {
				throw new BSOException("Error: unable to update user", e);
			}
		} else
			throw new BSOException("Error: code is invalid");
	}

	private void prepareConfirmation(CustomerDTO cdto) {
		String smscode = RandomStringUtils.randomNumeric(4);
//		String token = UUID.randomUUID().toString();
//		cdto.getUser().setToken(token);
		String code = DigestUtils.md5Hex(smscode);
		cdto.setCode(code);
		cdto.setSmscode(smscode);
	}

	@Override
	protected BaseDAO createDAO() throws BSOException {
		return new CustomerDAO();
	}

	@Override
	protected BaseDTO toDTO(Object entity) {
		if (entity == null)
			return null;
		Customer customer = (Customer) entity;
		return toCustomerDTO(customer);
	}

	@Override
	protected Object toEntity(BaseDTO dto) throws BSOException {
		CustomerDTO cdto = (CustomerDTO) dto;
		Customer customer = new Customer();
		buildEntity(customer, cdto);
		return customer;
	}

	public CustomerDTO getByMobileNo(String mobileNo) throws BSOException {
		try {
			CustomerDAO cDao = (CustomerDAO) dao;
			Customer cust = cDao.findByMobileNo(mobileNo);
			CustomerDTO dto = (CustomerDTO) toDTO(cust);
			return dto;
		} catch (Exception e) {
			throw new BSOException("Error: cannot find customer by mobile no", e);
		}
	}

}
