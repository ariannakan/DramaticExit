/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value] constants 
 */
function refreshRemoteVideoList(user) {
   var xhr = new XMLHttpRequest();
   xhr.open("POST", get_remote_videos_url, true);
   xhr.send();
   
   console.log("sent");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
     // console.log ("XHR:" + xhr.responseText);
      if(user === "append"){
    	  processAppendRemoteListResponse(xhr.responseText);
      }else{
    	  processRemoteListResponse(xhr.responseText);
      }
      //processSegmentListResponse(xhr.responseText);
    } else {
      processRemoteListResponse("N/A");
    }
  };
}
//for playlist.html visibility 
function processAppendRemoteListResponse(result) {
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
	    
	    output = output + "<div id=\"vid" + videoID + "\">" +
    	"<br><b>" + characterName + ": </b>" + sentence + "</><br></center></div>";
	    
//	    output = output + "<div id=\"" + videoID + "\">" +
//		"<br><b>" + characterName + ": </b>" + sentence + "</b>" +
//		"(<a href='javaScript:processShowVideo(\"" + videoID + "\")'>show</a>)</center>" + 
//		"(<a href='javaScript:processHideVideo(\"" + videoID + "\")'>hide</a>)</center>" + "</><br></div>";
//			
	  }
	  
	  vidList.innerHTML = output;

	}
/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'videoList' with a <br>-separated list of name,value pairs.
 */
//for admin and user videos visibility
function processRemoteListResponse(result) {
  //console.log("res:" , JSON.parse(result).list);
  console.log("res:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  var vidList = document.getElementById('remoteVideoList');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var videoJson = js.list[i];
    console.log(videoJson);
    
    var videoID = "Remote";
    var url = videoJson["url"];
    var characterName = videoJson["characterName"];
    var sentence = videoJson["sentence"];
    
    output = output + "<div id=\"vid" + videoID + "\">" +
	"<br><b><center>Video " + videoID + "</b>     " +
   	"<br><video height=" + 150 + " controls>" + "<source src=\"" + url + "\" type=\"video/ogg\"></video>" +
   	"<br><b>" + characterName + ": </b>" + sentence + "</><br></center></div>";
  }
  
  vidList.innerHTML = output;
}
