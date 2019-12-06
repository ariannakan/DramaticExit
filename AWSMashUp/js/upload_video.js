function processUploadVideoResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);

  refreshVideosList();
  //refreshRemoteList();
}

function handleCreateClick(e) {
  //var form = document.uploadForm;
 
  var data = {};
  data["characterName"] = document.uploadForm.characterName.value;
  data["sentence"] = document.uploadForm.sentence.value;
  
  /**
  if (form.system.checked) {  // be sure to flag system constant requests...
	  data["system"] = true;
  }
  **/
  
  // base64EncodedValue":"data:text/plain;base64,My4xND....."
  var segments = document.uploadForm.base64Encoding.value.split(',');
  data["oggFile"] = segments[1];  // skip first one 

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
    		 console.log("success");
	      console.log ("XHR:" + xhr.responseText);
	      processUploadVideoResponse(xhr.responseText);
    	 } else {
    		 console.log("fail");
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
    	console.log("here");
      processUploadVideoResponse("N/A");
    }
  };
}
