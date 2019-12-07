/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value] constants 
 */
function refreshSiteList() {
   var xhr = new XMLHttpRequest();
   xhr.open("GET", get_sites_url, true);
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
  var sitesList = document.getElementById('siteList');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var siteJson = js.list[i];
    console.log(siteJson);
    
    var url = siteJson["url"];
    
    output = output + "<div id=\"" + url + "\">" +
	"<br><b>" + url + "</b>     " +
		"(<a href='javaScript:requestSiteDelete(\"" + url + "\")' style = 'filter: invert(22%) sepia(95%) saturate(7454%) hue-rotate(360deg) brightness(100%) contrast(117%)'><img src='trashcan.png' height=" + 14 + "></img></a>)" + "</><br></div>";
}
  
  sitesList.innerHTML = output;

}
