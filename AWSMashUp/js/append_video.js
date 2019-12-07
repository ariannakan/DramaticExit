function processAppendVideoResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("Append: " + result);
  
  refreshPlaylistVideoList();
  //might need to include refresh playlistlist
  //refreshRemoteList();
}


function processAppendVideo() {
  var data = {};
  data["playlistName"] = document.appendForm.playlistName.value;
  data["videoID"] = document.appendForm.videoID.value;

  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", append_video_url, true);  // Can't be DELETE since then no data sent via JSON

  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
	  console.log(xhr);
	  console.log(xhr.request);
	  if (xhr.readyState == XMLHttpRequest.DONE) {
		  if (xhr.status == 200) {
			  console.log ("XHR:" + xhr.responseText);
			  processAppendVideoResponse(xhr.responseText);
		  } else {
			  console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["error"];
			  alert (err);
		  }
	  } else {
		  processAppendVideoResponse("N/A");
	  }
  };
}

