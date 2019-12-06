function processUploadVideoResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);

  refreshVideosList();
  //refreshRemoteList();
}

function handleUploadClick(e) {
  //var form = document.uploadForm;
 
  var data = {};
  data["characterName"] = document.uploadForm.characterName.value;
  data["sentence"] = document.uploadForm.sentence.value;
  
  /**
  if (document.uploadForm.system.checked) {  // be sure to flag system constant requests...
	  data["system"] = true;
  }
  **/
  // base64EncodedValue":"data:text/plain;base64,My4xND....."
  var segments = document.uploadForm.base64Encoding.value.split(',');
  console.log(segments[0]);
  data["video64"] = segments[1];  // skip first one 

  //data["oggFile"] = document.uploadForm.oggFile.value;
  console.log(data);
  
  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", add_video_url, true);

  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      console.log ("XHR:" + xhr.responseText);
	      processUploadVideoResponse(xhr.responseText);
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
      processUploadVideoResponse("N/A");
    }
  };
}
