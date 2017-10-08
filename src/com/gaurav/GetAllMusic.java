package com.gaurav;

import java.net.UnknownHostException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

/**
 * This class responds to the get request made on url http://localhost:8080/xmusic/rest/allmusic/playlist
 * with json file containing all song data.
 */

@Path("/allmusic")
public class GetAllMusic {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/playlist")
	public Response getAllSongs(@Context HttpHeaders httpHeaders) throws UnknownHostException {
	
		 
		DB db = (new MongoClient("localhost",27017)).getDB("xmusicdb");
	    DBCollection dbCollection = db.getCollection("catalog");
	    DBCursor cursor = dbCollection.find();
	           
	    JSON json =new JSON();
	    @SuppressWarnings("static-access")
		String serialize = json.serialize(cursor);
	    System.out.println(serialize);
	    
	    //This line is causing error on client side
	    return Response.ok(serialize, MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
	    
	    
	    //return Response.ok(serialize).header("Access-Control-Allow-Origin", "*").build();
	    //return Response.status(200).entity(serialize).build();
	}
	
	
}
