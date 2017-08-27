package com.exdev.cc.web.api;

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
import com.exdev.cc.bso.ServiceBSO;
import com.exdev.cc.web.dto.ServiceDTO;

@RequestScoped
@Path("/srvc")
@Produces("application/json")
@Consumes("application/json")
public class ServiceAPI {

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response create(final ServiceDTO servicedto)  {
		try {
			ServiceBSO bso = new ServiceBSO();
			bso.create(servicedto);
			ServiceDTO dto = (ServiceDTO) bso.get(servicedto.getId());
			bso.closeService();
			return Response.status(Status.CREATED).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot create Service", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") final int id)  {
		try {
			ServiceBSO bso = new ServiceBSO();
			ServiceDTO servicedto = (ServiceDTO) bso.get(id);
			bso.closeService();
			if (servicedto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(servicedto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot find Service", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Produces("application/json")
	public Response listAll()  {
		try {
			ServiceBSO bso = new ServiceBSO();
			final List<ServiceDTO> servicedtoes = (List) bso.list();
			bso.closeService();
			return Response.ok(servicedtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list Service", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Produces("application/json")
	public Response listAll(@QueryParam("start") final int start, @QueryParam("max") final int max)
			 {
		try {
			ServiceBSO bso = new ServiceBSO();
			final List<ServiceDTO> servicedtoes = (List) bso.list(start, max);
			bso.closeService();
			return Response.ok(servicedtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list Service", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") int id, final ServiceDTO servicedto)  {
		try {
			ServiceBSO bso = new ServiceBSO();
			bso.update(id, servicedto);
			ServiceDTO dto = (ServiceDTO) bso.get(id);
			bso.closeService();
			return Response.status(Status.OK).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot update Service", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id)  {
		try {
			ServiceBSO bso = new ServiceBSO();
			bso.delete(id);
			bso.closeService();
			return Response.noContent().build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot delete Service", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

}
