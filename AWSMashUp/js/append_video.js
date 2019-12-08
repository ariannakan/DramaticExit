function processAppendVideoResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("Append: " + result);
  
  refreshPlaylistVideoList();
  //might need to include refresh playlistlist
  //refreshRemoteList();
}

function requestDisplayAvailableVideos(){
	var xhr = new XMLHttpRequest();
	   xhr.open("GET", list_videos_url, true);
	   xhr.send();
	   
	   console.log("sent");

	  // This will process results and update HTML as appropriate. 
	  xhr.onloadend = function () {
	    if (xhr.readyState == XMLHttpRequest.DONE) {
	     console.log ("XHR:" + xhr.responseText);
	     processAppendListResponse(xhr.responseText);
	    } else {
	      processListResponse("N/A");
	    }
	  };
}

function processAppendListResponse(result) {
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
	    
	    if(availability === true){
	    	 output = output + "<div id=\"vid" + videoID + "\">" +
		    	"<br><b>" + characterName + ": </b>" + sentence + "</> (<a href='javaScript:processAppendListResponse(\"" + videoID + "\")' class = 'viewAppendVideo'>Append</a>)<br></div>";
	    } else {
	    	 output = output + "<div id=\"vid" + videoID + "\">" +
		    	"<br><b>" + characterName + ": </b>" + sentence + "</> (<a href='javaScript:processAppendListResponse(\"" + videoID + "\")' class = 'viewAppendVideo'>Append</a>)<br></div>";
	    }
	    
//	    output = output + "<div id=\"" + videoID + "\">" +
//		"<br><b>" + characterName + ": </b>" + sentence + "</b>" +
//		"(<a href='javaScript:processShowVideo(\"" + videoID + "\")'>show</a>)</center>" + 
//		"(<a href='javaScript:processHideVideo(\"" + videoID + "\")'>hide</a>)</center>" + "</><br></div>";
//			
	  }
	  
	  vidList.innerHTML = output;

	}


function processAppendVideoList() {
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

