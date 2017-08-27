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
import com.exdev.cc.bso.StaffBSO;
import com.exdev.cc.web.dto.StaffDTO;

/**
 * @author erwi
 *
 */
@RequestScoped
@Path("/staff")
@Produces("application/json")
@Consumes("application/json")
public class StaffAPI {

	/**
	 * @param providerstaffdto
	 * @return
	 * @
	 */
	@POST
	@Consumes("application/json")
	public Response create(final StaffDTO staffDto)  {

		try {
			StaffBSO pss = new StaffBSO();
			pss.create(staffDto);
			StaffDTO dto = (StaffDTO) pss.get(staffDto.getId());
			pss.closeService();
			return Response.status(Status.CREATED).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot create Staff", e);
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
			StaffBSO pss = new StaffBSO();
			StaffDTO providerstaffdto = (StaffDTO) pss.get(id);
			pss.closeService();
			if (providerstaffdto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(providerstaffdto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot fina Staff", e);
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
			StaffBSO pss = new StaffBSO();

			final List<StaffDTO> providerstaffdtoes = (List) pss.list(start, max);
			pss.closeService();
			return Response.ok(providerstaffdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list all Staffs", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param id
	 * @param providerstaffdto
	 * @return
	 * @
	 */
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") int id, final StaffDTO providerstaffdto)  {
		try {
			StaffBSO pss = new StaffBSO();
			pss.update(id, providerstaffdto);
			StaffDTO dto = (StaffDTO) pss.get(id);
			pss.closeService();
			return Response.status(Status.OK).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot update Staff", e);
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
			StaffBSO pss = new StaffBSO();
			pss.delete(id);
			pss.closeService();
			return Response.noContent().build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot delete Staff", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

}
