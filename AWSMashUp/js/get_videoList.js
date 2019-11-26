/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value] constants 
 */
function refreshVideoList() {
   var xhr = new XMLHttpRequest();
   xhr.open("GET", list_videos_url, true);
   xhr.send();
   
   console.log("sent");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
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
  console.log("res:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  var vidList = document.getElementById('videoList');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var videoJson = js.list[i];
    console.log(videoJson);
    
    var videoID = videoJson["videoID"];
    var character = videoJson["character"];
    var sentence = videoJson["sentence"];
    var sysvar = videoJson["system"];
    if (sysvar) {
    	output = output + "<div id=\"const" + videoID + "\"><b>" + videoID + ":</b> = " + character + "\"><b>" + videoID + ":</b> = " + sentence + "<br></div>";
    } else {
    	output = output + "<div id=\"const" + videoID + "\"><b>" + videoID + ":</b> = " + character + "\"><b>" + videoID + ":</b> = " + sentence + "(<a href='javaScript:requestVidDelete(\"" + vname + "\")'><img src='deleteIcon.png'></img></a>) <br></div>";
    }
  }

  // Update computation result
  vidList.innerHTML = output;
}

