package com.gaurav;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
 * URL testing class
 */

@Path("/test")
public class DBTest {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/single")
	public String sayPlainTextHello() {
	    return "Server: OK; Connection: OK";
	}

}
