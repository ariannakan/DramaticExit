/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value] constants 
 */
function refreshPlaylistList() {
   var xhr = new XMLHttpRequest();
   xhr.open("GET", list_playlists_url, true);
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
  var plList = document.getElementById('playlistList');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var playlistJson = js.list[i];
    console.log(playlistJson);
    
    var playlistID = playlistJson["playlistID"];
    var playlistName = playlistJson["playlistName"];
    
    output = output + "<div id=\"" + playlistID + "\">" +
		"<br>" + "(<img src='plus.png' height=" + 14 + "></img>) <b>" + playlistName + "</b>" +
  		" (<a href='javaScript:requestPlaylistDelete(\"" + playlistID + "\")'><img src='trashcan.png' height=" + 14 + "></img></a>)" + "</><br></div>";
  }
  
  plList.innerHTML = output;
  
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

