package com.exdev.cc.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.exdev.cc.utils.CleanCarUtils;

@RequestScoped
@Path("/base")
public class BaseAPI {

	public BaseAPI() {

	}

	@POST
	@Path("/send/{token}")
	@Produces("application/json")
	@Consumes("text/plain")
	public Response sendMessage(@PathParam("token") String toToken, String msg) {

		Map m = new HashMap();
		m.put("msg", msg);
		try {
			CleanCarUtils.sendNotification(m,"message", toToken);
		} catch (Exception e) {
			APIError er = new APIError("Error: cannot send message", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(er).build();
		}
		return Response.ok().build();
	}

}
