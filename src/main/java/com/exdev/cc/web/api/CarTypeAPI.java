package com.exdev.cc.web.api;

import java.util.List;
import java.util.logging.Level;

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
import com.exdev.cc.bso.CustomerBSO;
import com.exdev.cc.web.CCApp;
import com.exdev.cc.web.dto.CarTypeDTO;
import com.exdev.cc.web.dto.CustomerDTO;

@RequestScoped
@Path("/ct")
@Produces("application/json")
@Consumes("application/json")
public class CarTypeAPI {

	@POST
	public Response create(final CarTypeDTO cartypedto) {
		try {
			CarTypeBSO cs = new CarTypeBSO();
			cs.create(cartypedto);
			CarTypeDTO ct = (CarTypeDTO) cs.get(cartypedto.getId());
			cs.closeService();
			return Response.status(Status.CREATED).entity(ct).build();
		} catch (Exception e) {
			
			APIError error = new APIError("Error: cannot create Car Type", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final int id) throws BSOException {
		CarTypeBSO cs = new CarTypeBSO();
		CarTypeDTO cartypedto = (CarTypeDTO) cs.get(id);
		cs.closeService();
		if (cartypedto == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(cartypedto).build();
	}

	@GET
	public List<CarTypeDTO> listAll(@QueryParam("start") final int startPosition,
			@QueryParam("max") final int maxResult) throws BSOException {
		CarTypeBSO cs = new CarTypeBSO();
		final List<CarTypeDTO> cartypedtoes = (List) cs.list();
		cs.closeService();
		return cartypedtoes;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") int id, final CarTypeDTO cartypedto) throws BSOException {
		CarTypeBSO cs = new CarTypeBSO();
		cs.update(id, cartypedto);
		CarTypeDTO ct = (CarTypeDTO) cs.get(cartypedto.getId());
		cs.closeService();
		return Response.status(Status.OK).entity(ct).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id) throws BSOException {
		CarTypeBSO cs = new CarTypeBSO();
		cs.delete(id);
		cs.closeService();
		return Response.noContent().build();
	}

}
