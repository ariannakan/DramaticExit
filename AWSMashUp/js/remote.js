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
	    
	    processRegister(apikey, segmentJson)
	    console.log("sleep for 1 second");
	    sleep(45);
	    console.log(i);
	    console.log(i);

	  	}
	    	
	  refreshSiteList();
	  //refreshRemoteList();
	}

function processRegisterResponse(result) {
	  refreshSiteList();
	  // Can grab any DIV or SPAN HTML element and can then manipulate its
	  // contents dynamically via javascript
	  console.log("registered:" + result);
	}

	function processRegister(apikey, segmentJson) {
	 
	  var data = {};
	  data["apikey"] = apikey;
	  data["url"] = segmentJson["url"];
	  data["characterName"] = segmentJson["character"];
	  data["sentence"] = segmentJson["text"];
	  
	  /**
	  if (form.system.checked) {  // be sure to flag system constant requests...
	     data["system"] = true;
	  }*/
	  var js = JSON.stringify(data);
	  console.log("JS:" + js);
	  var xhr = new XMLHttpRequest();
	  xhr.open("POST", register_site_url, true);

	  // send the collected data as JSON
	  xhr.send(js);

	  // This will process results and update HTML as appropriate. 
	  xhr.onloadend = function () {
	    console.log(xhr);
	    console.log(xhr.request);
	    if (xhr.readyState == XMLHttpRequest.DONE) {
	    	 if (xhr.status == 200) {
		      console.log ("XHR:" + xhr.responseText);
		      processRegisterResponse(xhr.responseText);
	    	 } else {
	    		 console.log("actual:" + xhr.responseText)
				  var js = JSON.parse(xhr.responseText);
				  var err = js["response"];
				  alert (err);
	    	 }
	    } else {
	      processRegisterResponse("N/A");
	    }
	  };
	}

	function sleep(milliseconds) {
		  var start = new Date().getTime();
		  for (var i = 0; i < 1e7; i++) {
		    if ((new Date().getTime() - start) > milliseconds){
		      break;
		    }
		  }
		}

