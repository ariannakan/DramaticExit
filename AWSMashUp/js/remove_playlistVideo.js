/**
 * 
 */
function processRemoveVideoResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("deleted:" + result);
  
  refreshVideoList();
  //might need to include refresh playlistlist
  //refreshRemoteList();
}

function requestRemoveVideo(playlistName, video) {
   if (confirm("Request to remove from playlist " + video)) {
     processRemovePlaylistVideo(video, playlist);
   }
}

function processRemovePlaylistVideo(vid, playlist) {
  var data = {};
  data["videoID"] = vid;
  data["playlistName"] = playlist;

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

