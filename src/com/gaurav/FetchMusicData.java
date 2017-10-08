package com.gaurav;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

/**
 * This class fetches Song meta(Artist, album, file title, year, album art image title) 
 * and stores to mongodb.
 */

public class FetchMusicData {
	
	String name;
	int listSize;
	
	public void getData(String[] name, int listSize) throws UnknownHostException
	{
		
		DB db = (new MongoClient("localhost",27017)).getDB("xmusicdb");
		DBCollection dbCollection = db.getCollection("catalog");
		BasicDBObject basicDBObject;
		/*
		 * Basic mongo command line 
		 *  show dbs
		 *  use <dbname>
		 *  show collections
		 *  db.<collname>.find().pretty()
		 *  db.<collname>.count()
		 *  db.<collname>.drop()
		 */

		try {
		
			for(int i=0;i<listSize;i++)
			{
				Mp3File mp3file = new Mp3File(MusicUpload.MUSIC_UPLOAD_DIR + File.separator + name[i] );
				
				if (mp3file.hasId3v2Tag()) {
					  ID3v2 id3v2Tag = mp3file.getId3v2Tag();
					  					
					  basicDBObject = new BasicDBObject();
					  
					  System.out.println("");
					  System.out.println("**********V2**********");
					  System.out.println("Title original: " + name[i]);
					  basicDBObject.put("Title", name[i]);
					  System.out.println("Artist: " + id3v2Tag.getArtist());
					  basicDBObject.put("Artist", id3v2Tag.getArtist());
					  System.out.println("Album: " + id3v2Tag.getAlbum());
					  basicDBObject.put("Album", id3v2Tag.getAlbum());
					  System.out.println("Year: " + id3v2Tag.getYear());
					  basicDBObject.put("Year", id3v2Tag.getYear());
					  System.out.println("Image: " + name[i]+ ".jpg");
					  basicDBObject.put("Image", (name[i]+ ".jpg"));
					  
					  
					  byte[] albumImageData = id3v2Tag.getAlbumImage();
					  if (albumImageData != null) {
						    //String mimeType = id3v2Tag.getAlbumImageMimeType();
						    //RandomAccessFile file = new RandomAccessFile("album-artwork", "rw");
						    //file.write(data);
						    //file.close();
						    
						    
				     		FileOutputStream file = new FileOutputStream(MusicUpload.MUSIC_UPLOAD_DIR + File.separator + name[i]+".jpg");
						    file.write(albumImageData);
						    file.close();
						  }
					  
					  //String img = new String(albumImageData);
					  //basicDBObject.put("Album art", albumImageData);
					  //System.out.println("String image"+albumImageData);
					  dbCollection.insert(basicDBObject);
					  if (albumImageData != null) {
					    System.out.println("Album art present, Size: " + albumImageData.length + " bytes");
					    System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
					  }
					}else if(mp3file.hasId3v1Tag()) {
						
						ID3v1 id3v1Tag = mp3file.getId3v1Tag();
						System.out.println("");
						System.out.println("**********V1**********");
						//System.out.println("Track: " + id3v1Tag.getTrack());
						System.out.println("Title original: " + name[i]);
						System.out.println("Artist: " + id3v1Tag.getArtist());
						System.out.println("Title: " + id3v1Tag.getTitle());
						System.out.println("Album: " + id3v1Tag.getAlbum());
						System.out.println("Year: " + id3v1Tag.getYear());
									
						
					}
				
			}
		} catch (UnsupportedTagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
