var plName;
var videoSource = new Array();
var v = 0;

function handlePlayClick(){
	v = 0;
	document.getElementById('videoPlayer').addEventListener('ended', handler);
	playVideo(0);
}

function handler(){
	if (v < (videoSource.length - 1)) {
    	v++;
    	playVideo(v);
    }
}

function playVideo(videoNum) {
	document.getElementById("videoPlayer").setAttribute("src", videoSource[v]);
    document.getElementById("videoPlayer").load();
    document.getElementById("videoPlayer").play();
}



function requestPlaylistVideos(playlistName){
	requestDisplayAvailableVideos(playlistName);
	var data = {};
	data["playlistName"] = playlistName;
	plName = playlistName;
	
	var js = JSON.stringify(data);
	console.log(get_playlist_videos_url);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", get_playlist_videos_url, true); //url might need to be edited
    xhr.send(js);
	   
    console.log("sent");
	
   // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
	if (xhr.readyState == XMLHttpRequest.DONE) {
	      console.log ("XHR:" + xhr.responseText);
	      processPlaylistVideosResponse(xhr.responseText);
	} else {
		processPlaylistVideosResponse("N/A");
    	}
	};
}

/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'videoList' with a <br>-separated list of name,value pairs.
 */
function processPlaylistVideosResponse(result) {
  //console.log("res:" + JSON.parse(result).list);
  //console.log("playlistVideos result:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  var playlistVideosList = document.getElementById('playlistVideosList');
  
  var output = "";

  if (js.list == ""){
	  console.log("no videos in playlist");
	  output = output + "<p style='text-indent: 20px'><b>No videos in this playlist</b></p>" +
	  		"<a href='javaScript:requestDisplayAvailableVideos(\"" + plName + "\")' class = 'appendButton' style = 'margin-left:10px'>Append New Video</a></b></br>	<div style = 'border-bottom: 1px solid white; margin-top:15px; margin-left:-30px;'></div>";
  } else {
	  for (var i = 0; i < js.list.length; i++) {
		var videoJson = js.list[i];
    	console.log(videoJson);
    
    	var videoID = videoJson["videoID"];
    	var url = videoJson["url"];
    	var characterName = videoJson["characterName"];
    	var sentence = videoJson["sentence"];
    
    	videoSource[i] = url;
    
    	output = output + "<div id=\"" + videoID + "\">" + 
			"<center>(<a href='javaScript:requestRemoveVideo(\"" + plName + "\",\"" + videoID + "\")' style = 'filter: invert(22%) sepia(95%) saturate(7454%) hue-rotate(360deg) brightness(100%) contrast(117%)'><img src='trashcan.png' height=" + 14 + "></img></a>)" +
			"<br><video height=" + 150 + " controls>" + "<source src=\"" + url + "\" type=\"video/ogg\"></video>" + 
			"</><br></center></div>";
  		}
	  	output = output + "<br><b class = 'appendButton' style = 'margin-left:10px'>Append New Video</b></br><div style = 'border-bottom: 1px solid white; margin-top:15px; margin-left:-30px;'></div>";
  	}
  playlistVideosList.innerHTML = output;
}


