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
     // console.log ("XHR:" + xhr.responseText);
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
  //console.log("res:" , JSON.parse(result).list);
  console.log("res:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  var vidList = document.getElementById('videoList');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var videoJson = js.list[i];
    console.log(videoJson);
    
    var videoID = videoJson["videoID"];
    var url = videoJson["url"];
    var characterName = videoJson["characterName"];
    var sentence = videoJson["sentence"];
    
    output = output + "<div id=\"vid" + videoID + "\">" +
		"<br><b><center>Video " + videoID + "</b>     " +
  		"(<a href='javaScript:requestVidDelete(\"" + videoID + "\")'><img src='trashcan.png' height=" + 14 + "></img></a>)</center>" +
   		"<br><video height=" + 150 + " controls>" + "<source src=\"" + url + "\" type=\"video/ogg\"></video>" +
   		"<br><b>" + characterName + ": </b>" + sentence + "</><br></div>";
  }
  
  vidList.innerHTML = output;
  /**
  var list =  JSON.parse(result).list
  
  for (var I = 0; I < list.length; I++) {
	  video = "<div id=\"" + list[I].videoID + "\">" +
	  		"<div><b><center>" + list[I].videoID + "</b>" +
	  		"(<a href='javaScript:requestVidDelete(\"" + list[I].videoID + "\")'><img src='trashcan.png' height=" + 14 + "></img></a>)</center>" +
       		"<div><video height=" + 150 + " controls>" + "<source src=\"" + list[I].url + "\" type=\"video/ogg\"></video>" +
       		"<div><b>" + list[I].characterName + ": </b>" + list[I].sentence + "</></div>";
	  document.getElementById("videoList").innerHTML += video;
  }*/
  
  //var output = "";
/**
  for (var i = 0; i < js.list.length; i++) {
    var list = js.list[i];
    console.log(list);*/
    
//    var videoID = videoJson["videoID"];
//    var url = videoJson["url"];
//    var characterName = videoJson["characterName"];
//    var sentence = videoJson["sentence"];
    
//    for (var I = 0; I < list.length; I++)
//    {
//         video = "<video id=\""+list[I].videoID + "\" controls>" + "<source src=\""+list[I].url+ "\" type=\"video/ogg\"> </video>"
//         document.getElementById("videoList").innerHTML += video;
//    }
    
//Play videos one after another    
//    var vid1 = document.getElementById("vid1");
//    vid1.addEventListener("ended", function() {vid2.play(); });
//    var vid2 = document.getElementById("vid2");
//    vid2.addEventListener("ended", function() {vid3.play(); });
//    var vid3 = document.getElementById("vid3");
//    vid3.addEventListener("ended", function() {vid4.play(); });
//    var vid4 = document.getElementById("vid4");
//    vid4.addEventListener("ended", function() {vid5.play(); });
//    var vid5 = document.getElementById("vid5");
//    vid5.addEventListener("ended", function() {vid6.play(); });
//    var vid6 = document.getElementById("vid6");
//    vid6.addEventListener("ended", function() {vid7.play(); });
//
//    var vid7 = document.getElementById("vid7");
//    vid7.addEventListener("ended", function() {vid8.play(); });
//    var vid8 = document.getElementById("vid8");
//    vid8.addEventListener("ended", function() {vid9.play(); });
//    var vid9 = document.getElementById("vid9");
//    vid9.addEventListener("ended", function() {vid10.play(); });

    
    /*
    if (sysvar) {
    	output = output + "<div id=\"video" + videoID + "\"><b>" + videoID + ":</b> = " + character + "\"><b>" + videoID + ":</b> = " + sentence + "<br></div>";
    } else {
    	output = output + "<div id=\"video" + videoID + "\"><b>" + videoID + ":</b> = " + character + "\"><b>" + videoID + ":</b> = " + sentence + "(<a href='javaScript:requestVidDelete(\"" + vname + "\")'><img src='deleteIcon.png'></img></a>) <br></div>";
    }
    */
    
    //output = output + "<div id =\"" + videoID + "\"><b>" + characterName + url + "<br></div>";
    //output = output + "<video id=\"" + videoID + "\" controls> <source src =\"" + url +  "\" type=\" video/ogg \"> </video>";

  // Update computation result
  //vidList.innerHTML = output;
}
