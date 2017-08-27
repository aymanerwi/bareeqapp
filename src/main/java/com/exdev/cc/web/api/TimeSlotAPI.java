package com.exdev.cc.web.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.exdev.cc.bso.BSOException;
import com.exdev.cc.bso.TimeSlotBSO;
import com.exdev.cc.web.dto.TimeSlotDTO;

@RequestScoped
@Path("/ts")
@Produces("application/json")
@Consumes("application/json")
public class TimeSlotAPI {
	private static final String SERVICE_REQUEST_DATE_FORMAT = "dd-MM-yy";

	@POST
	public Response create(final TimeSlotDTO timeslotdto)  {
		try {
			TimeSlotBSO tss = new TimeSlotBSO();
			tss.create(timeslotdto);
			TimeSlotDTO dto = (TimeSlotDTO) tss.get(timeslotdto.getId());
			tss.closeService();
			return Response.status(Status.CREATED).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot create Time slot", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final int id)  {
		try {
			TimeSlotBSO tss = new TimeSlotBSO();
			TimeSlotDTO timeslotdto = (TimeSlotDTO) tss.get(id);
			tss.closeService();
			if (timeslotdto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(timeslotdto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot find Time slot", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/date/{date}")
	public Response listAllByDate(@PathParam("date") final String requestDate)  {
		try {
			TimeSlotBSO tss = new TimeSlotBSO();
			SimpleDateFormat fmt = new SimpleDateFormat(SERVICE_REQUEST_DATE_FORMAT);
			Date date;
			try {
				date = fmt.parse(requestDate);
			} catch (ParseException e) {
				throw new BSOException("Ivalid Date format, the date must be in format 'dd-MM-yy'", e);
			}
			final List<TimeSlotDTO> timeslotdtoes = tss.listTimeSlotsByDate(date);
			tss.closeService();
			return Response.ok(timeslotdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list  Time slot by date", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	public Response listAll(@QueryParam("start") final int startPosition,
			@QueryParam("max") final int maxResult)  {
		try {
			TimeSlotBSO tss = new TimeSlotBSO();
			final List<TimeSlotDTO> timeslotdtoes = (List) tss.list(startPosition, maxResult);
			tss.closeService();
			return Response.ok(timeslotdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list all Time slot", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response update(@PathParam("id") int id, final TimeSlotDTO timeslotdto)  {
		try {
			TimeSlotBSO tss = new TimeSlotBSO();
			tss.update(id, timeslotdto);
			TimeSlotDTO dto = (TimeSlotDTO) tss.get(timeslotdto.getId());
			tss.closeService();
			return Response.status(Status.OK).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot update Time slot", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id)  {
		try {
			TimeSlotBSO tss = new TimeSlotBSO();
			tss.delete(id);
			tss.closeService();
			return Response.noContent().build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot delete Time slot", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

}
