
function handleSearchClick() {

  var data = {};
  data["keywordname"] = document.searchForm.keywordname.value;
  data["keywordsentence"] = document.searchForm.keywordsentence.value;

  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", search_video_url, true);  
  
  // send the collected data as JSON
  xhr.send(js);
  console.log("sent");
  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
	  console.log(xhr);
	  console.log(xhr.request);
	  if (xhr.readyState == XMLHttpRequest.DONE) {
		  if (xhr.status == 200) {
			  console.log ("XHR:" + xhr.responseText);
			  processSearchResponse(xhr.responseText);
		  } else {
			  console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["error"];
			  alert (err);
		  }
	  } else {
		  processSearchResponse("N/A");
	  }
  };
}

//js for search function
function processSearchResponse(result) {

	  console.log("search:" + result);
	  
	  var js = JSON.parse(result);
	  var searchList = document.getElementById('searchList');
	  var output = "";

	  if (js.list == ""){
		  console.log("search completed but no result");
		  
		  output = output + "<p style='text-indent: 250px'><b>No results match your search</b></p>"
		  	
	  } else {
		  for (var i = 0; i < js.list.length; i++) {
			  var videoJson = js.list[i];
			  console.log(videoJson);
	    
			  var videoID = videoJson["videoID"];
			  var url = videoJson["url"];
			  var characterName = videoJson["characterName"];
			  var sentence = videoJson["sentence"];
			  
			  output = output + "<div id=\"vid" + videoID + "\">" +
			  "<br><b><center>Video " + videoID + "</b>     " +
	   			"<br><video height=" + 150 + " controls>" + "<source src=\"" + url + "\" type=\"video/ogg\"></video>" +
	   			"<br><b>" + characterName + ": </b>" + sentence + "</><br></div>";
	  		}
	  }
	  searchList.innerHTML = output;
	}





