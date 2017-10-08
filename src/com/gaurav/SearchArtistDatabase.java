package com.gaurav;

import java.net.UnknownHostException;

//http://localhost:8080/xmusic/rest/search/gaurav

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

@Path("/search-artist")
public class SearchArtistDatabase {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{artist}")
	public Response searchBySong(@Context HttpHeaders httpHeaders, @PathParam("artist") String name) throws UnknownHostException {
		
		String artist = name;
		
		DB db = (new MongoClient("localhost",27017)).getDB("xmusicdb");
	    DBCollection dbCollection = db.getCollection("catalog");
	    
	    BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("Artist",artist);
	    
	    DBCursor cursor = dbCollection.find(whereQuery);
	    		
	           
	    JSON json =new JSON();
	    @SuppressWarnings("static-access")
		String serialize = json.serialize(cursor);
	    System.out.println(serialize);
	    
	    
		
		
		return Response.ok(serialize, MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
		
	}
	
}
