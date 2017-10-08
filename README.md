# X-Music-Player
Restful web service based music player developed in Java and Javascript. Web service for X Music Player.

![captureee](https://user-images.githubusercontent.com/9462473/31319678-6ca970d6-ac85-11e7-871c-20c95ba8dd7f.PNG)

Apache Tomcat 8.5 server
MongoDB

Java(Jersey jax-rs, Apache commons fileUpload, jackson, mp3agic)
Html, 
css, 
javascript, 
Ajax, 
Json for data exchange, 
RESTful paradigm

# Features
1. Theme settings <br>
2. Album art, title, album, artist, year fetch<br>
3. Music animation<br>
4. Search music in database based on artist/album name<br>
5. Wipe database and file upload directory with single click<br>
6. Play, pause, next, previous, scroll, mute audio.

# Working:
Music files are uploaded from client side to the server where song meta such as Title, artist name, album name, year, Album art image is fetched
and stored in MongoDB. The client then gets a response in json format containing all data which is then parsed into html. No page loading is done
while requests are made(Ajax) except while uploading music files.
Searches can be made from client side using artist name or album name which inturn gets a json response from server containing appropriate data.
all files can be deleted along with complete xmusic database wipe from the client end.

# Known problems:
Server needs to be restarted after 20-30 file uploads as it starts throwing ArrayOutOfBounds exception, 
Few bugs here and there, 
No Click event listener on all tracks list.

# Important: 
This is backend for the "X Music Player". Set local path to "..\X Music Player\songs" folder in "MusicUpload.java" file before running the project.
"MusicUpload.java" is present in "xmusic" folder. Variable name: MUSIC_UPLOAD_DIR. "songs" folder contains uploaded songs and
fetched album art images.
