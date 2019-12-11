//
//RETURN SEGMENTS AND APIURL WHEN INPUT APIKEY
//
function processRemoteResponse(result, apikey) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("registered:" + result);
  //*********************************************************
  //THIS IS WHERE WE SEND THE FORMATION TO REGISTER_SITE.JS
  //*********************************************************
  var js = JSON.parse(result);
//  var remoteConstList = document.getElementById('remoteConstantList');
  
  for (var i = 0; i < js.list.length; i++) {
    var constantJson = js.list[i];
    
    console.log(constantJson["url"]);
    //processRegister(apikey, constantJson["url"], constantJson["character"], constantJson["text"])
    
  	}
    	
  refreshSiteList();
  //refreshRemoteList();
}

function processRemote() {
 
  var data = {};
  var apikey = document.registerSiteForm.url.value;
  var js = JSON.stringify(data);
  console.log("JS:" + js);
  console.log("apikey: " + apikey);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", search_remote_url, true);//pretty sure this is the only thing that's wrong
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
