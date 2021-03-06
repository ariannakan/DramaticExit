/**
 * 
 */
function processRemoveVideoResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("deleted:" + result);
  
  var js = JSON.parse(result);
  console.log("result.playlistName " + js.playlistName);
  requestPlaylistVideos(js.playlistName);
  //might need to include refresh playlistlist
  //refreshRemoteList();
}

function requestRemoveVideo(playlistName, video) {
	console.log("playlist name: " + playlistName + " video: " + video);
   if (confirm("Request to remove " + video + " from playlist " + playlistName)) {
     processRemovePlaylistVideo(playlistName, video);
   }
}

function processRemovePlaylistVideo(playlistName, video) {
  var data = {};
  data["videoID"] = video;
  data["playlistName"] = playlistName;

  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", delete_playlist_video_url, true);  // Can't be DELETE since then no data sent via JSON

  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
	  console.log(xhr);
	  console.log(xhr.request);
	  if (xhr.readyState == XMLHttpRequest.DONE) {
		  if (xhr.status == 200) {
			  console.log ("XHR:" + xhr.responseText);
			  processRemoveVideoResponse(xhr.responseText);
		  } else {
			  console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["error"];
			  alert (err);
		  }
	  } else {
		  processRemoveVideoResponse("N/A");
	  }
  };
}

