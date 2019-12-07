var plName;

function requestPlaylistVideos(playlistName){
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
  for (var i = 0; i < js.list.length; i++) {
    var videoJson = js.list[i];
    console.log(videoJson);
    
    var videoID = videoJson["videoID"];
    var url = videoJson["url"];
    var characterName = videoJson["characterName"];
    var sentence = videoJson["sentence"];
    
    output = output + "<div id=\"" + videoID + "\">" + 
		"<center>(<a href='javaScript:requestRemoveVideo(\"" + plName + "\",\"" + videoID + "\")' style = 'filter: invert(22%) sepia(95%) saturate(7454%) hue-rotate(360deg) brightness(100%) contrast(117%)'><img src='trashcan.png' height=" + 14 + "></img></a>)" +
		"<br><video height=" + 150 + " controls>" + "<source src=\"" + url + "\" type=\"video/ogg\"></video>" + "</><br></center></div>";
    
//    if(availability === true){
//	    output = output + "<div id=\"vid" + videoID + "\">" +
//		"<br><b><center>Video " + videoID + "</b>     " +
//  		"(<a href='javaScript:requestVidDelete(\"" + videoID + "\")'><img src='trashcan.png' height=" + 14 + "></img></a>)</center>" +
//   		"<br><video height=" + 150 + " controls>" + "<source src=\"" + url + "\" type=\"video/ogg\"></video>" +
//   		"<br><b>" + characterName + ": </b>" + sentence + "</><br></div>";
//    } else {
//    	output = output + "<div id=\"vid" + videoID + "\">" +
//		"<br><b><center>Video " + videoID + "</b>     " +
//  		"(<a href='javaScript:requestVidDelete(\"" + videoID + "\")'><img src='trashcan.png' height=" + 14 + "></img></a>)</center>" +
//   		"<br><video height=" + 150 + " controls>" + "<source src=\"" + url + "\" type=\"video/ogg\"></video>" +
//   		"<br><b>" + characterName + ": </b>" + sentence + "</><br></div>";
//    }
	
  }
  
//  Play videos one after another    
//  var 2019.12.07.01.04.33 = document.getElementById("2019.12.07.01.04.33");
//  2019.12.07.01.04.33.addEventListener("ended", function() {2019.12.07.01.05.18.play(); });
//  var 2019.12.07.01.05.18 = document.getElementById("2019.12.07.01.05.18");
//  2019.12.07.01.05.18.addEventListener("ended", function() {2019.12.07.01.05.37.play(); });
//  var 2019.12.07.01.05.37 = document.getElementById("2019.12.07.01.05.37");
//  2019.12.07.01.05.37.addEventListener("ended", function() {2019.12.07.01.06.03.play(); });
//  var 2019.12.07.01.06.03 = document.getElementById("2019.12.07.01.06.03");
//  2019.12.07.01.06.03.addEventListener("ended", function() {2019.12.07.01.06.32.play(); });
//  var 2019.12.07.01.06.32 = document.getElementById("2019.12.07.01.06.32");
//  2019.12.07.01.06.32.addEventListener("ended", function() {2019.12.07.01.06.57.play(); });
//  var 2019.12.07.01.06.57 = document.getElementById("2019.12.07.01.06.57");
//  2019.12.07.01.06.57.addEventListener("ended", function() {2019.12.07.01.07.16.play(); });
//  var 2019.12.07.01.07.16 = document.getElementById("2019.12.07.01.07.16");
//  2019.12.07.01.07.16.addEventListener("ended", function() {2019.12.07.01.07.38.play(); });
//  var 2019.12.07.01.07.38 = document.getElementById("2019.12.07.01.07.38");
//  2019.12.07.01.07.38.addEventListener("ended", function() {2019.12.07.01.07.56.play(); });
//  var 2019.12.07.01.07.56 = document.getElementById("2019.12.07.01.07.56");
//  2019.12.07.01.07.56.addEventListener("ended", function() {2019.12.07.01.08.11.play(); });

  
  
  playlistVideosList.innerHTML = output;
  
  /*
  var list = JSON.parse(result).list
  
  for (var I = 0; I < list.length; I++){
	  playlist = "<div id=\"" + list[I].playlistID + "\"> <br>" +
	  "(<img src='plus.png' height=" + 14 + "></img>) " +
	  list[I].playlistName +  
	  " (<img src='trashcan.png' height=" + 14 + "></img>)" +
	  "</div>"
      document.getElementById("playlistList").innerHTML += playlist;
  }
  
  
  for (var i = 0; i < js.list.length; i++) {
    var list = js.list[i];
    console.log(list);
  }
  */
}

