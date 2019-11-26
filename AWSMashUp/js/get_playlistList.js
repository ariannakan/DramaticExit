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
  console.log("res:" + JSON.parse(result).list);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  var plList = document.getElementById('playlistList');
  var list = JSON.parse(result).list
  
  for (var I = 0; I < list.length; I++){
	  playlist = "<div id=\"" + list[I].playlistID + "\"><b>" + list[I].playlistName + "</b><br></div>";
      document.getElementById("playlistList").innerHTML += playlist; 
  }
  
  for (var i = 0; i < js.list.length; i++) {
    var list = js.list[i];
    console.log(list);
  }

}

