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
import com.exdev.cc.bso.CustomerCarsBSO;
import com.exdev.cc.web.dto.CustomerCarDTO;

/**
 * @author erwi
 *
 */
@RequestScoped
@Path("/custcar")
@Produces("application/json")
@Consumes("application/json")
public class CustomerCarAPI {

	/**
	 * @param customercardto
	 * @return
	 * @
	 */
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response create(final CustomerCarDTO customercardto)  {
		try {
			CustomerCarsBSO ccs = new CustomerCarsBSO();
			ccs.create(customercardto);
			CustomerCarDTO dto = (CustomerCarDTO) ccs.get(customercardto.getId());
			ccs.closeService();
			return Response.status(Status.CREATED).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot create Customer Car", e);
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
			CustomerCarsBSO ccs = new CustomerCarsBSO();
			CustomerCarDTO customercardto = (CustomerCarDTO) ccs.get(id);
			ccs.closeService();
			if (customercardto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(customercardto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot find Customer car", e);
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
			CustomerCarsBSO ccs = new CustomerCarsBSO();
			final List<CustomerCarDTO> customercardtoes = (List) ccs.list(start, max);
			ccs.closeService();
			return Response.ok(customercardtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list Customer cars", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param customer
	 *            id (id)
	 * @return
	 * @
	 */
	@GET
	@Path("/cust/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response listAll(@PathParam("id") int cutomerid)  {
		try {
			CustomerCarsBSO ccs = new CustomerCarsBSO();
			final List<CustomerCarDTO> customercardtoes = ccs.list(cutomerid);
			ccs.closeService();
			return Response.ok(customercardtoes).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot list Customer Cars", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param id
	 * @param customercardto
	 * @return
	 * @
	 */
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") int id, final CustomerCarDTO customercardto)  {
		try {
			CustomerCarsBSO ccs = new CustomerCarsBSO();
			ccs.update(id, customercardto);
			CustomerCarDTO dto = (CustomerCarDTO) ccs.get(id);
			ccs.closeService();
			return Response.status(Status.OK).entity(dto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot update Customer Car", e);
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
			CustomerCarsBSO ccs = new CustomerCarsBSO();
			ccs.delete(id);
			ccs.closeService();
			return Response.noContent().build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot delete Customer Car", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

}
