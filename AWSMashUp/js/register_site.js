function processRegisterResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("registered:" + result);

  refreshSiteList();
  //refreshRemoteList();
}

function processRegister(e) {
 
  var data = {};
  data["characterName"] = document.registerSiteForm.characterName.value;
  data["sentence"] = document.registerSiteForm.sentence.value;
  data["url"] = document.registerSiteForm.url.value;
  
  /**
  if (form.system.checked) {  // be sure to flag system constant requests...
     data["system"] = true;
  }*/
  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", register_site_url, true);
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
