
 function processUpdateResponse(result) {
   // Can grab any DIV or SPAN HTML element and can then manipulate its
   // contents dynamically via javascript
   console.log("Update Availability:" + result);
   }

// function requestUpdateVideo(vid, availability) {
//    if (confirm("Request to update " + vid)) {
//      processUpdateVideo(vid, availability);
//    }
// }

 function processHideVideo(vid) {
   var data = {};
   data["videoID"] = vid; 
   data["availability"] = false;

   console.log(update_video_url);
   var js = JSON.stringify(data);
   console.log("JS:" + js);
   var xhr = new XMLHttpRequest();
   xhr.setRequestHeader('Access-Control-Allow-Origin', "*");
   xhr.open("PUT", update_video_url, true);  // Can't be DELETE since then no data sent via JSON

   // send the collected data as JSON
   xhr.send(js);

   // This will process results and update HTML as appropriate. 
   xhr.onloadend = function () {
 	  console.log(xhr);
 	  console.log(xhr.request);
 	  if (xhr.readyState == XMLHttpRequest.DONE) {
 		  if (xhr.status == 200) {
 			  console.log ("XHR:" + xhr.responseText);
 			  processUpdateResponse(xhr.responseText);
 		  } else {
 			  console.log("actual:" + xhr.responseText)
 			  var js = JSON.parse(xhr.responseText);
 			  var err = js["error"];
 			  alert (err);
 		  }
 	  } else {
 		  processUpdateResponse("N/A");
 	  }
   };
 }
 
 function processShowVideo(vid) {
	   var data = {};
	   data["videoID"] = vid; //form????
	   data["availability"] = true;//form??
	   
	   var js = JSON.stringify(data);
	   console.log("JS:" + js);
	   var xhr = new XMLHttpRequest();
	   xhr.open("PUT", update_video_url, true);  // Can't be DELETE since then no data sent via JSON

	   // send the collected data as JSON
	   xhr.send(js);

	   // This will process results and update HTML as appropriate. 
	   xhr.onloadend = function () {
	 	  console.log(xhr);
	 	  console.log(xhr.request);
	 	  if (xhr.readyState == XMLHttpRequest.DONE) {
	 		  if (xhr.status == 200) {
	 			  console.log ("XHR:" + xhr.responseText);
	 			  processUpdateResponse(xhr.responseText);
	 		  } else {
	 			  console.log("actual:" + xhr.responseText)
	 			  var js = JSON.parse(xhr.responseText);
	 			  var err = js["error"];
	 			  alert (err);
	 		  }
	 	  } else {
	 		  processUpdateResponse("N/A");
	 	  }
	   };
	 }

