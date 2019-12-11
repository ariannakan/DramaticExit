/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value] constants 
 */
function refreshRemoteVideoList(user) {
   var xhr = new XMLHttpRequest();
   xhr.open("GET", get_remote_videos_url, true);
   xhr.send();
   
   console.log("sent");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
     // console.log ("XHR:" + xhr.responseText);
      if(user === "admin"){
    	  processRemoteListResponse("N/A");
      }
      else if(user === "append"){
    	  processAppendListResponse(xhr.responseText);
      }
      else{
    	  processRemoteListResponse(xhr.responseText);
      }
      //processSegmentListResponse(xhr.responseText);
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
	    
	    var videoID = videoJson["videoID"];
	    var characterName = videoJson["characterName"];
	    var sentence = videoJson["sentence"];
	    var url = videoJson["url"];
	    
	    output = output + "<div id=\"vid" + videoID + "\">" +
    		"<br><b>" + characterName + ": </b>" + sentence + "</><br></center></div>";
	  }
	  
	  vidList.innerHTML = output;

	}


/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'videoList' with a <br>-separated list of name,value pairs.
 */
function processRemoteListResponse(result) {
  //console.log("res:" , JSON.parse(result).list);
  console.log("res:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  var remoteVidList = document.getElementById('remoteVideoList');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var remoteVideoJson = js.list[i];
    console.log(remoteVideoJson);
    
    var videoID = remoteVideoJson["videoID"];
    var characterName = remoteVideoJson["characterName"];
    var sentence = remoteVideoJson["sentence"];
    var url = remoteVideoJson["url"];
    
    output = output + "<div id=\"vid" + videoID + "\">" +
		"<br><b><center>Video " + videoID + "</b>     " +
		"(<a href='javaScript:requestVidDelete(\"" + videoID + "\")' style = 'filter: invert(22%) sepia(95%) saturate(7454%) hue-rotate(360deg) brightness(100%) contrast(117%)'><img src='trashcan.png' height=" + 14 + "></img></a>)" +
		"<br><video height=" + 150 + " controls>" + "<source src=\"" + url + "\" type=\"video/ogg\"></video>" +
		"<br><b>" + characterName + ": </b>" + sentence + "</><br></center></div>";
  
  remoteVidList.innerHTML = output;
  
}
}
