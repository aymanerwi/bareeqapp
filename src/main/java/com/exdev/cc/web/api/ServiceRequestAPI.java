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
import com.exdev.cc.bso.ServiceRequestBSO;
import com.exdev.cc.web.CCApp;
import com.exdev.cc.web.dto.ServiceRequestAssignUnitSttafDTO;
import com.exdev.cc.web.dto.ServiceRequestDTO;
import com.exdev.cc.web.dto.ServiceRequestRatingDTO;
import com.exdev.cc.web.dto.ServiceRequestStatusDTO;

@RequestScoped
@Path("/sr")
@Produces("application/json")
@Consumes("application/json")
public class ServiceRequestAPI {

	private static final String SERVICE_REQUEST_DATE_FORMAT = "dd-MM-yy";

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response create(final ServiceRequestDTO srdto)  {
		
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();
			bso.create(srdto);
			ServiceRequestDTO dto = (ServiceRequestDTO) bso.get(srdto.getId());
			bso.closeService();
			return Response.status(Status.CREATED).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot create Service Request", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") final int id)  {
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();
			ServiceRequestDTO clientrequestdto = (ServiceRequestDTO) bso.get(id);
			bso.closeService();
			if (clientrequestdto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(clientrequestdto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot create Service Request", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/")
	@Produces("application/json")
	public Response listAll(@QueryParam("start") final int startPosition,
			@QueryParam("max") final int maxResult)  {
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();
			final List<ServiceRequestDTO> clientrequestdtoes = (List) bso.list(startPosition, maxResult);
			bso.closeService();
			return Response.ok(clientrequestdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list Service Request", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/date/{date}")
	@Produces("application/json")
	public Response listAllByRequestDate(@PathParam("date") final String requestDate)
			 {
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();
			SimpleDateFormat fmt = new SimpleDateFormat(SERVICE_REQUEST_DATE_FORMAT);
			Date d = null;
			try {
				d = fmt.parse(requestDate);
			} catch (ParseException e) {
				throw new BSOException("Ivalid Date format, the date must be in format 'dd-MM-yy'", e);
			}
			final List<ServiceRequestDTO> clientrequestdtoes = bso.list(d);
			bso.closeService();
			return Response.ok(clientrequestdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list Service Request by request date", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/date/{date}")
	@Produces("application/json")
	public Response listAllByRequestDate(@PathParam("date") final String requestDate,
			@QueryParam("start") final int startPosition, @QueryParam("max") final int maxResult)  {

		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();
			SimpleDateFormat fmt = new SimpleDateFormat(SERVICE_REQUEST_DATE_FORMAT);
			Date date;
			try {
				date = fmt.parse(requestDate);
			} catch (ParseException e) {
				throw new BSOException("Ivalid Date format, the date must be in format 'dd-MM-yy'", e);
			}
			final List<ServiceRequestDTO> clientrequestdtoes = bso.list(date, startPosition, maxResult);
			bso.closeService();
			return Response.ok(clientrequestdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list Service Request by request date", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/date/{date}/{status:[0-9][0-9]*}")
	@Produces("application/json")
	public Response listAllByRequestDate(@PathParam("date") final String requestDate,
			@PathParam("status") final int status)  {
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();
			SimpleDateFormat fmt = new SimpleDateFormat(SERVICE_REQUEST_DATE_FORMAT);
			Date date;
			try {
				date = fmt.parse(requestDate);
			} catch (ParseException e) {
				throw new BSOException("Ivalid Date format, the date must be in format 'dd-MM-yy'", e);
			}
			final List<ServiceRequestDTO> clientrequestdtoes = bso.list(date, status);
			bso.closeService();
			return Response.ok(clientrequestdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list Service Request by request date", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/status/{s:[0-9][0-9]*}")
	@Produces("application/json")
	public Response listAllByStatus(@PathParam("s") final int status,
			@QueryParam("start") final int startPosition, @QueryParam("max") final int maxResult)  {
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();
			final List<ServiceRequestDTO> clientrequestdtoes = bso.listByStatus(status, startPosition, maxResult);
			bso.closeService();
			return Response.ok(clientrequestdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list Service Request by status", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/assign/{carStaffId}")
	@Produces("application/json")
	public Response listAllByAssignedUnitStaff(@PathParam("carStaffId") final int carStaffId,
			@QueryParam("start") final int startPosition, @QueryParam("max") final int maxResult)  {
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();

			final List<ServiceRequestDTO> clientrequestdtoes = bso.listByUnitStaff(carStaffId, startPosition, maxResult);
			bso.closeService();
			return Response.ok(clientrequestdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list UnitStaff Service Request", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/assign/{unitstaffId}/{date}/{status}")
	@Produces("application/json")
	public Response listAllByAssignedUnitStaffId(@PathParam("unitstaffId") final int unitstaffId,
			@PathParam("date") long requestDate, @PathParam("status") int status,
			@QueryParam("start") final int startPosition, @QueryParam("max") final int maxResult)  {
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();

			Date d = new Date(requestDate);
			final List<ServiceRequestDTO> clientrequestdtoes = bso.listByUnitStaff(unitstaffId, d, status, startPosition,
					maxResult);
			bso.closeService();
			return Response.ok(clientrequestdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot create Service Request", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/cust/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response listByCustomer(@PathParam("id") final int customerId,
			@QueryParam("start") final int startPosition, @QueryParam("max") final int maxResult)  {
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();
			final List<ServiceRequestDTO> clientrequestdtoes = bso.listByCustomer(customerId, startPosition, maxResult);
			bso.closeService();
			return Response.ok(clientrequestdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list Customer Service Request", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/cust/{customerId:[0-9][0-9]*}/{status:[0-9][0-9]*}")
	@Produces("application/json")
	public Response listAllByCustomerAndStatus(@PathParam("customerId") final int customerId,
			@PathParam("status") final int status, @QueryParam("start") final int startPosition,
			@QueryParam("max") final int maxResult)  {
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();
			final List<ServiceRequestDTO> clientrequestdtoes = bso.listByCustomerAndStatus(customerId, status,
					startPosition, maxResult);
			bso.closeService();
			return Response.ok(clientrequestdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list Service Request by customer id", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response update(@PathParam("id") int id, final ServiceRequestDTO srdto)  {
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();
			bso.update(id, srdto);
			ServiceRequestDTO dto = (ServiceRequestDTO) bso.get(id);
			bso.closeService();
			return Response.status(Status.OK).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot update Service Request", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@PUT
	@Path("/rating/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response updateRating(@PathParam("id") int id, final ServiceRequestRatingDTO srdto)  {
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();
			bso.updateRating(id, srdto);
			ServiceRequestDTO dto = (ServiceRequestDTO) bso.get(id);
			bso.closeService();
			return Response.status(Status.OK).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot ipdate Service Request rating", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@PUT
	@Path("/assign/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response updateAssignUnitStaff(@PathParam("id") int id, final ServiceRequestAssignUnitSttafDTO srdto)
			 {
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();
			bso.assignUnitStaffToServiceRequest(id, srdto);
			ServiceRequestDTO dto = (ServiceRequestDTO) bso.get(id);
			bso.closeService();
			return Response.status(Status.OK).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot update Service Request assigned unit staff", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@PUT
	@Path("/status/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response updateStatus(@PathParam("id") int id, final ServiceRequestStatusDTO srdto)  {
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();
			bso.updateStatus(id, srdto);
			ServiceRequestDTO dto = (ServiceRequestDTO) bso.get(id);
			bso.closeService();
			return Response.status(Status.OK).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot update  Service Request status", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id)  {
		try {
			ServiceRequestBSO bso = new ServiceRequestBSO();
			bso.delete(id);
			bso.closeService();
			return Response.noContent().build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot delete Service Request", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

}
