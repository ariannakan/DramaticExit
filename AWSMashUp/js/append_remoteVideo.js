function processAppendRemoteVideoResponse(result, playlistName) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("Append: " + result);
  
  //refreshPlaylistList();
  requestPlaylistVideos(playlistName);
  //requestDisplayAvailableVideos(playlistName)
  //might need to include refresh playlistlist
  //refreshRemoteList();
}

function requestDisplayAvailableRemoteVideos(playlistName){
	var xhr = new XMLHttpRequest();
	   xhr.open("POST", get_remote_videos_url, true);
	   xhr.send();
	   
	   console.log("sent empty js for append remote video");

	  // This will process results and update HTML as appropriate. 
	  xhr.onloadend = function () {
	    if (xhr.readyState == XMLHttpRequest.DONE) {
	     console.log ("XHR:" + xhr.responseText);
	     processAppendRemoteListResponse(playlistName, xhr.responseText);
	    } else {
	      processRemoteListResponse("N/A");
	    }
	  };
}

function processAppendRemoteListResponse(playlistName, result) {
	  //console.log("res:" , JSON.parse(result).list);
	  console.log("append result:" + result);
	  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
	  var js = JSON.parse(result);
	  var vidList = document.getElementById('appendRemoteVideoList');
	  
	  var output = "";
	  for (var i = 0; i < js.list.length; i++) {
	    var videoJson = js.list[i];
	    console.log(videoJson);
	    
	    var characterName = videoJson["characterName"];
	    var sentence = videoJson["sentence"];
	    var videoID = videoJson["videoID"];
	    var url = videoJson["url"];
	    
//		 output = output + "<div id=\"vid" + videoID + "\">" +
//	    	"<br><b>" + characterName + ": </b>" + sentence + "</> (<a href='javaScript:processAppendVideoRequest(\"" + playlistName + "\",\"" + videoID + "\")' class = 'viewAppendVideo'>Append</a>)<br></div>";

		 output = output + "<div id=\"vid" + videoID + "\">" +
	    	"<br>(<a href='javaScript:processAppendRemoteVideoRequest(\"" + playlistName + "\",\"" + videoID + "\")' class = 'viewAppendRemoteVideo'>Append</a>) <b>" + characterName + ": </b>" + sentence + "</><br></div>";	
	  }
	  
	  vidList.innerHTML = output;

	}


function processAppendRemoteVideoRequest(playlistName, videoID) {
  var data = {};
  data["playlistName"] = playlistName
  data["videoID"] = videoID;
  
  console.log("append remote video request:" + data);
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
			  processAppendRemoteVideoResponse(xhr.responseText, playlistName);
		  } else {
			  console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["error"];
			  alert (err);
		  }
	  } else {
		  processAppendRemoteVideoResponse("N/A");
	  }
  };
}

