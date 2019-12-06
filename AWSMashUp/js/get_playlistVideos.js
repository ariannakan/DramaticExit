/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value] constants 
 */
function refreshPlaylistVideoList() {
   var xhr = new XMLHttpRequest();
   xhr.open("GET", get_playlist_videos_url, true); //url might need to be edited
   xhr.send();
   
   console.log("sent");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      //console.log ("XHR:" + xhr.responseText);
      processListResponse(xhr.responseText);
    } else {
      processListResponse("N/A");
    }
  };
}

/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'videoList' with a <br>-separated list of name,value pairs.
 */
function processListResponse(result) {
  //console.log("res:" + JSON.parse(result).list);
  console.log("res:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  var plVidList = document.getElementById('playlistVideoList');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var playlistVidJson = js.list[i];
    console.log(playlistVidJson);
    
    //var playlistID = playlistJson["playlistID"];
    var playlistName = playlistVidJson["playlistName"];
    var videoID = playlistVidJson["videoID"];
    
    output = output + "<div id=\"" + playlistName + "\">" +
  		"<br>(<a href='javaScript:removePlaylistVideo(\"" + videoID + "\")'><img src='trashcan.png' height=" + 14 + "></img></a>)" + "</><br></div>";
  }
  
  plVidList.innerHTML = output;
  
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

