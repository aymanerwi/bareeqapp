package com.exdev.cc.bso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import com.exdev.cc.dao.BaseDAO;
import com.exdev.cc.dao.TimeSlotDAO;
import com.exdev.cc.dao.UnitStaffDAO;
import com.exdev.cc.model.ServiceRequest;
import com.exdev.cc.model.TimeSlot;
import com.exdev.cc.model.UnitStaff;
import com.exdev.cc.web.dto.BaseDTO;
import com.exdev.cc.web.dto.TimeSlotDTO;

public class TimeSlotBSO extends BaseBSO {

	public static TimeSlot toTimeSlot(TimeSlotDTO dto) throws BSOException {
		TimeSlotDAO dao = new TimeSlotDAO();
		TimeSlot ts;
		try {
			ts = (TimeSlot) dao.find(dto.getId());
			dao.closeEntityManager();
		} catch (Exception e) {
			throw new BSOException("Error: cannot find Timeslot", e);
		}
		return ts;
	}

	public static TimeSlotDTO toTimeSlotDTO(TimeSlot ts) {

		return new TimeSlotDTO(ts.getId(), ts.getName(), ts.getNotes(), ts.getDescription(), ts.getStartTime(),
				ts.getEndTime(), ts.getDisabled());
	}

	public TimeSlotBSO() throws BSOException {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object buildEntity(Object o, BaseDTO dto) throws BSOException {
		TimeSlot ts = (TimeSlot) o;
		TimeSlotDTO tsdto = (TimeSlotDTO) dto;
		if (ts == null || dto == null)
			return null;
		ts.setDescription(tsdto.getDescription());
		ts.setDisabled(tsdto.isDisabled());
		ts.setEndTime(tsdto.getEndTime());
		ts.setStartTime(tsdto.getStartTime());
		ts.setName(tsdto.getName());
		ts.setId(tsdto.getId());
		ts.setNotes(tsdto.getNotes());
		return ts;
	}

	@Override
	protected BaseDAO createDAO() throws BSOException {

		return new TimeSlotDAO();
	}

	public List<TimeSlotDTO> listTimeSlotsByDate(final Date date) throws BSOException {
		List<TimeSlot> tsList;
		List<TimeSlotDTO> tsDtosList;
		List<UnitStaff> usList;
		try {
			tsList = (List) dao.list();
			UnitStaffDAO usDao = new UnitStaffDAO();
			usList = new ArrayList<>((List) usDao.list());

			tsDtosList = new ArrayList<>(tsList.size());

			for (TimeSlot ts : tsList) {
				int freeUnits = usList.size();
				TimeSlotDTO dto = (TimeSlotDTO) toDTO(ts);
				List<ServiceRequest> srs;
				srs = (List) CollectionUtils.select(ts.getServiceRequests(), new Predicate<ServiceRequest>() {

					@Override
					public boolean evaluate(ServiceRequest sr) {
						return date.equals(sr.getRequestDate());

					}
				});

				for (ServiceRequest sr : srs) {
					if (ServiceRequestStatus.Closed != sr.getStatus()
							|| ServiceRequestStatus.Cancelled != sr.getStatus()) {
						if (usList.contains(sr.getUnitStaff()))
							freeUnits--;
					}
				}
				if (freeUnits > 0)
					dto.setAvailable(true);
				dto.setFreeUnitStaff(freeUnits);
				tsDtosList.add(dto);
			}
			usDao.closeEntityManager();
		} catch (Exception e) {
			throw new BSOException("Error: cannot list timeSlots", e);
		}
		return tsDtosList;
	}

	@Override
	protected BaseDTO toDTO(Object entity) throws BSOException {
		TimeSlot ts = (TimeSlot) entity;
		return new TimeSlotDTO(ts.getId(), ts.getName(), ts.getNotes(), ts.getDescription(), ts.getStartTime(),
				ts.getEndTime(), ts.getDisabled());
	}

	@Override
	protected Object toEntity(BaseDTO dto) throws BSOException {
		TimeSlot ts = new TimeSlot();

		return this.buildEntity(ts, dto);
	}

}
