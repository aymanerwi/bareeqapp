/**
 * 
 */
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
import javax.ws.rs.core.UriBuilder;

import com.exdev.cc.bso.BSOException;
import com.exdev.cc.bso.CustomerBSO;
import com.exdev.cc.web.CCApp;
import com.exdev.cc.web.dto.CustomerDTO;

/**
 * @author erwi_000
 *
 */
@RequestScoped
@Path("/cust")
@Produces("application/json")
@Consumes("application/json")
public class CustomerAPI {

	/**
	 * @param customerdto
	 * @return @
	 */
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response create(final CustomerDTO customerdto) {
		try {
			CustomerBSO cs = new CustomerBSO();
			cs.createCustomer(customerdto);
			CustomerDTO currCust = (CustomerDTO) cs.get(customerdto.getId());
			cs.closeService();
			return Response.status(Status.CREATED).entity(currCust).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot create Customer", e);
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
			CustomerBSO cs = new CustomerBSO();
			CustomerDTO customerdto = (CustomerDTO) cs.get(id);
			cs.closeService();
			if (customerdto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(customerdto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot find Customer by ID: " + id, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/m/{mobileNo:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findByMobileNo(@PathParam("mobileNo") final String mobileNo) {
		try {
			CustomerBSO cs = new CustomerBSO();
			CustomerDTO customerdto = (CustomerDTO) cs.getByMobileNo(mobileNo);
			cs.closeService();
			if (customerdto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(customerdto).build();
		} catch (Exception e) {
			APIError error = new APIError("Error: cannot find Customer by mobile " + mobileNo, e);
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
	public Response listAll(@QueryParam("start") final int start, @QueryParam("max") final int max) {
		try {
			CustomerBSO cs = new CustomerBSO();
			final List<CustomerDTO> customerdtoes = (List) cs.list(start, max);
			cs.closeService();
			return Response.ok(customerdtoes).build();
		} catch (BSOException e) {
			APIError error = new APIError("Error: cannot list all Customers", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@GET
	@Path("/search")
	@Produces("application/json")
	public Response search(@QueryParam("q") String query) {
		try {
			CustomerBSO cs = new CustomerBSO();
			final List<CustomerDTO> customerdtoes = (List) cs.list(query);
			cs.closeService();
			return Response.ok(customerdtoes).build();
		} catch (BSOException e) {
			APIError error = new APIError("Error: cannot search all Customers", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param id
	 * @param customerdto
	 * @return @
	 */
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") int id, final CustomerDTO customerdto) {
		try {
			CustomerBSO cs = new CustomerBSO();
			cs.update(id, customerdto);
			CustomerDTO currCust = (CustomerDTO) cs.get(id);
			cs.closeService();
			return Response.ok().entity(currCust).build();
		} catch (BSOException e) {
			APIError error = new APIError("Error: cannot update Customer " + id, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	/**
	 * @param id
	 * @param customerdto
	 * @return @
	 */
	@PUT
	@Path("/confirm/{id:[0-9][0-9]*}")
	@Consumes("text/plain")
	public Response confirm(@PathParam("id") int id, final String code) {
		try {
			CustomerBSO cs = new CustomerBSO();
			cs.confirm(id, code);
			CustomerDTO currCust = (CustomerDTO) cs.get(id);
			cs.closeService();
			return Response.status(Status.OK).entity(currCust).build();
		} catch (BSOException e) {
		
			APIError error = new APIError("Error: cannot confirm customer", e);
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
			CustomerBSO cs = new CustomerBSO();
			cs.delete(id);
			cs.closeService();
			return Response.noContent().build();
		} catch (BSOException e) {
			APIError error = new APIError("Error: cannot delete all Customers", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

}
