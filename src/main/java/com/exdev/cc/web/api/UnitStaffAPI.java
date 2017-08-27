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
import com.exdev.cc.bso.UnitStaffBSO;
import com.exdev.cc.web.dto.UnitDTO;
import com.exdev.cc.web.dto.UnitStaffDTO;

/**
 * @author erwi
 *
 */
@RequestScoped
@Path("/us")
public class UnitStaffAPI {

	/**
	 * @param providercarstaffdto
	 * @return
	 * @
	 */
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response create(final UnitStaffDTO providercarstaffdto)  {
		try {
			UnitStaffBSO pcss = new UnitStaffBSO();
			pcss.create(providercarstaffdto);
			UnitStaffDTO dto = (UnitStaffDTO) pcss.get(providercarstaffdto.getId());
			pcss.closeService();
			return Response.status(Status.CREATED).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot create UnitStaff", e);
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
			UnitStaffBSO pcss = new UnitStaffBSO();
			UnitStaffDTO providercarstaffdto = (UnitStaffDTO) pcss.get(id);
			pcss.closeService();
			if (providercarstaffdto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(providercarstaffdto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot find UnitStaff", e);
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
			UnitStaffBSO pcss = new UnitStaffBSO();
			final List<UnitStaffDTO> providercarstaffdtoes = (List) pcss.list(start, max);
			pcss.closeService();
			return Response.ok(providercarstaffdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list all UnitStaff", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/staff/{staffid:[0-9][0-9]*}")
	@Produces("application/json")
	public Response listAllByStaffId(@PathParam("staffid") final int staffid)  {
		try {
			UnitStaffBSO uss = new UnitStaffBSO();
			final List<UnitStaffDTO> providercarstaffdtoes = (List) uss.listByStaffId(staffid);
			uss.closeService();
			return Response.ok(providercarstaffdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list UnitStaff by Staff id", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/unit/{unitid:[0-9][0-9]*}")
	@Produces("application/json")
	public Response listAllByUnitId(@PathParam("unitid") final int unitid)  {
		try {
			UnitStaffBSO uss = new UnitStaffBSO();
			final List<UnitStaffDTO> providercarstaffdtoes = (List) uss.listByUnitd(unitid);
			uss.closeService();
			return Response.ok(providercarstaffdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list UnitStaff by unit id", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param id
	 * @param providercarstaffdto
	 * @return
	 * @
	 */
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response update(@PathParam("id") int id, final UnitStaffDTO providercarstaffdto)  {
		try {
			UnitStaffBSO pcss = new UnitStaffBSO();
			pcss.update(id, providercarstaffdto);
			UnitStaffDTO dto = (UnitStaffDTO) pcss.get(id);
			pcss.closeService();
			return Response.status(Status.OK).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot update UnitStaff", e);
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
			UnitStaffBSO pcss = new UnitStaffBSO();
			pcss.delete(id);
			return Response.noContent().build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot delete UnitStaff", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

}
