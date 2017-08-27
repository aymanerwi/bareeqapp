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
import com.exdev.cc.bso.PartnerBSO;
import com.exdev.cc.web.dto.PartnerDTO;

/**
 * @author erwi_000
 *
 */
@RequestScoped
@Path("/prtnr")
@Produces("application/json")
@Consumes("application/json")
public class PartnerAPI {

	/**
	 * @param providerdto
	 * @return @
	 */
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response create(final PartnerDTO providerdto) {
		try {
			PartnerBSO ps = new PartnerBSO();
			ps.create(providerdto);
			PartnerDTO dto = (PartnerDTO) ps.get(providerdto.getId());
			ps.closeService();
			return Response.status(Status.CREATED).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot create Partner", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param id
	 * @return @
	 */
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") final int id) {
		try {
			PartnerDTO providerdto = null;
			PartnerBSO ps = new PartnerBSO();
			providerdto = (PartnerDTO) ps.get(id);
			ps.closeService();

			if (providerdto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(providerdto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot find Partner", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param startPosition
	 * @param maxResult
	 * @return @
	 */
	@GET
	@Produces("application/json")
	public Response listAll(@QueryParam("start") final int startPosition,
			@QueryParam("max") final int maxResult) {
		try {
			List<PartnerDTO> providerdtoes = null;
			PartnerBSO ps = new PartnerBSO();
			providerdtoes = (List) ps.list(startPosition, maxResult);
			ps.closeService();
			return Response.ok(providerdtoes).build();
		} catch (BSOException e) {
			APIError error = new APIError("Error: cannot create Partner", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param id
	 * @param providerdto
	 * @return @
	 */
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") int id, final PartnerDTO providerdto) {
		try {
			PartnerBSO ps = new PartnerBSO();
			ps.update(id, providerdto);
			PartnerDTO dto = (PartnerDTO) ps.get(id);
			ps.closeService();
			return Response.status(Status.OK).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot update Partner", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param id
	 * @return @
	 */
	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id) {
		try {
			PartnerBSO ps = new PartnerBSO();
			ps.delete(id);
			ps.closeService();

			return Response.noContent().build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot delete Partner", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

}
