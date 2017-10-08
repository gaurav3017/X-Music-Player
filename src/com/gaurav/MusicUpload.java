package com.gaurav;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * This class uploads songs to the server folder and stores all file names 
 * and forwards to FetchMusicData.java
 */
@WebServlet("/MusicUpload")
public class MusicUpload extends HttpServlet {
	
	//Manually enter path to ~\X Music Player\songs before running project
	final static String MUSIC_UPLOAD_DIR = "C:\\Users\\grv\\eclipse-workspace\\X Music Player\\songs";
	//access this variable in other class by using: MusicUpload.MUSIC_UPLOAD_DIR 
	
	
	
	private static final long serialVersionUID = 1L;
	int count = 0;
	String[] name = new String[60];
	FetchMusicData fetchMusicData = new FetchMusicData();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MusicUpload() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//FetchMusicData fetchData = new FetchMusicData();
		if(ServletFileUpload.isMultipartContent(request))
		{
			try
			{
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for(FileItem item : multiparts)
				{
					if(!item.isFormField())
					{
						name[count] = new File(item.getName()).getName();
						item.write(new File(MUSIC_UPLOAD_DIR + File.separator + name[count]));
						
						count++;
					}
				}
				request.setAttribute("message", "Music Added. Hit back and click '3. Fetch'");//+System.getProperty("user.dir")
				//FetchMusicData fetchData = new FetchMusicData();
				System.out.println("");
				System.out.println("<<<<<<" + count + " songs uploaded>>>>>>");
				int listSize = multiparts.size();
				fetchMusicData.getData(name, listSize);//passing array to fetch tags
			}
			catch(Exception ex)
			{
				request.setAttribute("message", "File upload failed due to : " + ex);
			}
		}
		else
		{
			request.setAttribute("message", "Its working but its a post request which accepts multipart only.");
		}
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

}
