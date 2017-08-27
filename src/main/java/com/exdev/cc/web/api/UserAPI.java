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
import com.exdev.cc.bso.UserBSO;
import com.exdev.cc.web.dto.UnitStaffDTO;
import com.exdev.cc.web.dto.UserDTO;
import com.exdev.cc.web.dto.UserLoginDTO;

/**
 * @author erwi_000
 *
 */
@RequestScoped
@Path("/usr")
public class UserAPI {

	/**
	 * @param userdto
	 * @return
	 * @
	 */
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response create(final UserDTO userdto)  {
		try {
			UserBSO us = new UserBSO();
			us.create(userdto);
			UserDTO dto = (UserDTO) us.get(userdto.getId());
			us.closeService();
			return Response.status(Status.CREATED).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot create User", e);
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
			UserBSO us = new UserBSO();
			UserDTO userdto = (UserDTO) us.get(id);
			us.closeService();

			if (userdto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(userdto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot find User", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@POST
	@Path("/login")
	@Produces("application/json")
	public Response loginUser(UserLoginDTO dto)  {
		try {
			UserBSO us = new UserBSO();
			UserDTO userdto = (UserDTO) us.login(dto);
			us.closeService();

			if (userdto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(userdto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot login User", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@POST
	@Path("/logout")
	@Produces("application/json")
	public Response logoutUser(String username)  {
		try {
			UserBSO us = new UserBSO();
			us.logout(username);
			us.closeService();
			return Response.ok().build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot logout User", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/m/{mobileNo}")
	@Produces("application/json")
	public Response findByMobileNo(@PathParam("mobileNo") final String mobileNo)  {
		try {
			UserBSO us = new UserBSO();
			List<UserDTO> userdto =  us.getByMobileNo(mobileNo);
			us.closeService();

			if (userdto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(userdto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot find User", e);
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
			UserBSO us = new UserBSO();

			final List<UserDTO> userdtoes = (List) us.list(start, max);
			us.closeService();
			return Response.ok(userdtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list all Users", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param id
	 * @param userdto
	 * @return
	 * @
	 */
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response update(@PathParam("id") int id, final UserDTO userdto)  {
		try {
			UserBSO us = new UserBSO();
			us.update(id, userdto);
			UserDTO dto = (UserDTO) us.get(userdto.getId());
			us.closeService();
			return Response.status(Status.OK).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot update User", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}
	
	@PUT
	@Path("/token/{id:[0-9][0-9]*}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response updateToken(@PathParam("id") int id, final String token)  {
		try {
			UserBSO us = new UserBSO();
			us.updateToken(id, token);
			UserDTO dto = (UserDTO) us.get(id);
			us.closeService();
			return Response.status(Status.OK).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot update User", e);
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
			UserBSO us = new UserBSO();
			us.delete(id);
			us.closeService();
			return Response.noContent().build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot delete User", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

}
