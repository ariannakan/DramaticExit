function processAppendVideoResponse(result, playlistName) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("Append: " + result);
  
  requestPlaylistVideos(playlistName);
  requestDisplayAvailableVideos(playlistName)
  //might need to include refresh playlistlist
  //refreshRemoteList();
}

function requestDisplayAvailableVideos(playlistName){
	var xhr = new XMLHttpRequest();
	   xhr.open("GET", list_videos_url, true);
	   xhr.send();
	   
	   console.log("sent");

	  // This will process results and update HTML as appropriate. 
	  xhr.onloadend = function () {
	    if (xhr.readyState == XMLHttpRequest.DONE) {
	     console.log ("XHR:" + xhr.responseText);
	     processAppendListResponse(playlistName, xhr.responseText);
	    } else {
	      processListResponse("N/A");
	    }
	  };
}

function processAppendListResponse(playlistName, result) {
	  //console.log("res:" , JSON.parse(result).list);
	  console.log("append result:" + result);
	  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
	  var js = JSON.parse(result);
	  var vidList = document.getElementById('appendVideoList');
	  
	  var output = "";
	  for (var i = 0; i < js.list.length; i++) {
	    var videoJson = js.list[i];
	    console.log(videoJson);
	    
	    var availability = videoJson["availability"];
	    var characterName = videoJson["characterName"];
	    var sentence = videoJson["sentence"];
	    var videoID = videoJson["videoID"];
	    var url = videoJson["url"];
	    
		 output = output + "<div id=\"vid" + videoID + "\">" +
	    	"<br><b>" + characterName + ": </b>" + sentence + "</> (<a href='javaScript:processAppendVideoRequest(\"" + playlistName + "\",\"" + videoID + "\")' class = 'viewAppendVideo'>Append</a>)<br></div>";
	    
			
	  }
	  
	  vidList.innerHTML = output;

	}


function processAppendVideoRequest(playlistName, videoID) {
  var data = {};
  data["playlistName"] = playlistName
  data["videoID"] = videoID;

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
			  processAppendVideoResponse(xhr.responseText, playlistName);
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

