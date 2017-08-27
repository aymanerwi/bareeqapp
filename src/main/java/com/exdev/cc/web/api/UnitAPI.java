/**
 * 
 */
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
import com.exdev.cc.bso.UnitBSO;
import com.exdev.cc.web.dto.TimeSlotDTO;
import com.exdev.cc.web.dto.UnitDTO;

/**
 * @author erwi
 *
 */
@RequestScoped
@Path("/unit")
public class UnitAPI {

	/**
	 * @param providercardto
	 * @return
	 * @
	 */
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response create(final UnitDTO providercardto)  {
		try {
			UnitBSO pcs = new UnitBSO();
			pcs.create(providercardto);
			UnitDTO dto = (UnitDTO) pcs.get(providercardto.getId());
			pcs.closeService();
			return Response.status(Status.CREATED).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot create Unit", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param id
	 * @return
	 * @
	 */
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") final int id)  {
		try {
			UnitBSO pcs = new UnitBSO();
			UnitDTO providercardto = (UnitDTO) pcs.get(id);
			pcs.closeService();
			if (providercardto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(providercardto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot find Unit", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param startPosition
	 * @param maxResult
	 * @return
	 * @
	 */
	@GET
	@Produces("application/json")
	public Response listAll(@QueryParam("start") final int start, @QueryParam("max") final int max)
			 {
		try {
			UnitBSO pcs = new UnitBSO();
			final List<UnitDTO> providercardtoes = (List) pcs.list(start, max);
			pcs.closeService();
			return Response.ok(providercardtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list All Unit", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param id
	 * @param providercardto
	 * @return
	 * @
	 */
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response update(@PathParam("id") int id, final UnitDTO providercardto)  {
		try {
			UnitBSO pcs = new UnitBSO();
			pcs.update(id, providercardto);
			UnitDTO dto = (UnitDTO) pcs.get(id);
			pcs.closeService();
			return Response.status(Status.OK).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot update Unit", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param id
	 * @return
	 * @
	 */
	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id)  {
		try {
			UnitBSO pcs = new UnitBSO();
			pcs.delete(id);
			pcs.closeService();
			return Response.noContent().build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot delete Unit", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

}
