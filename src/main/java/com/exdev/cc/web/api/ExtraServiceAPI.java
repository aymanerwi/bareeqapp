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

import com.exdev.cc.bso.BSOException;
import com.exdev.cc.bso.CarTypeBSO;
import com.exdev.cc.bso.ExtraServiceBSO;
import com.exdev.cc.web.dto.CarTypeDTO;
import com.exdev.cc.web.dto.ExtraServiceDTO;

@RequestScoped
@Path("/es")
@Produces("application/json")
@Consumes("application/json")
public class ExtraServiceAPI {

	@POST
	public Response create(final ExtraServiceDTO extraservicedto) {
		try {
			ExtraServiceBSO bso = new ExtraServiceBSO();
			bso.create(extraservicedto);
			ExtraServiceDTO es = (ExtraServiceDTO) bso.get(extraservicedto.getId());
			bso.closeService();
			return Response.status(Status.CREATED).entity(es).build();
		} catch (Exception e) {

			APIError error = new APIError("Error: cannot create ExtraService", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final int id) {
		try {
			ExtraServiceBSO bso = new ExtraServiceBSO();
			ExtraServiceDTO extraservicedto = (ExtraServiceDTO) bso.get(id);
			bso.closeService();
			if (extraservicedto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(extraservicedto).build();
		} catch (BSOException e) {
			APIError error = new APIError("Error: cannot find ExtraService", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	public Response listAll(@QueryParam("start") final int startPosition, @QueryParam("max") final int maxResult) {
		List<ExtraServiceDTO> extraservicedtoes;
		try {
			ExtraServiceBSO bso = new ExtraServiceBSO();
			extraservicedtoes = (List) bso.list(startPosition, maxResult);
			bso.closeService();
			return Response.ok(extraservicedtoes).build();
		} catch (BSOException e) {
			APIError error = new APIError("Error: cannot list all ExtraServices", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}

	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") int id, final ExtraServiceDTO extraservicedto) {
		try {
			ExtraServiceBSO bso = new ExtraServiceBSO();
			bso.update(id, extraservicedto);
			ExtraServiceDTO es = (ExtraServiceDTO) bso.get(extraservicedto.getId());
			bso.closeService();
			return Response.ok(es).build();
		} catch (BSOException e) {
			APIError error = new APIError("Error: cannot update ExtraService", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id) {
		try {
			ExtraServiceBSO bso = new ExtraServiceBSO();
			bso.delete(id);
			bso.closeService();
			return Response.noContent().build();
		} catch (BSOException e) {
			APIError error = new APIError("Error: cannot delete ExtraService", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

}
