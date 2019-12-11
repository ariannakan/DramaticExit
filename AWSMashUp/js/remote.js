//
//RETURN SEGMENTS AND APIURL WHEN INPUT APIKEY
//

function processRemote() {
 
  var data = {};
  var input = document.registerSiteForm.url.value.split("?apikey=");
  var apikey = input[1];
  console.log("apikey:" + apikey);
  var remote_url = input[0];
  console.log("remote_url" + remote_url);


  var js = JSON.stringify(data);
  
  console.log("JS:" + js);
  console.log("apikey: " + apikey);
  var xhr = new XMLHttpRequest();
  xhr.open("GET", remote_url , true);//pretty sure this is the only thing that's wrong
  xhr.setRequestHeader("x-api-key", apikey); 

  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      console.log ("XHR:" + xhr.responseText);
	      processRemoteResponse(xhr.responseText, apikey);
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
      processRemoteResponse("N/A", apikey);
    }
  };
}

function processRemoteResponse(result, apikey) {
	  // Can grab any DIV or SPAN HTML element and can then manipulate its
	  // contents dynamically via javascript
	  console.log("res:" + result);
	  //*********************************************************
	  //THIS IS WHERE WE SEND THE FORMATION TO REGISTER_SITE.JS
	  //*********************************************************
	  var js = JSON.parse(result);
	//  var remoteConstList = document.getElementById('remoteConstantList');
	  console.log("js: " + js);

	  for (var i = 0; i < js.segments.length; i++) {
	    var segmentJson = js.segments[i];
	    console.log(segmentJson);
	    console.log("url: " + segmentJson["url"]);
	    processRegister(apikey, segmentJson["url"], segmentJson["character"], segmentJson["text"])
	    
	  	}
	    	
	  refreshSiteList();
	  //refreshRemoteList();
	}

