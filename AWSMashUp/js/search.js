
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
	  
	  refreshPlaylistList("search");
	  	  	  	  
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
			  console.log("videoID: "+ videoJson["videoID"])
			  if (videoJson["videoID"] == null){
				  
				  var videoID = "remote";

			  } else{
			  var videoID = videoJson["videoID"];
			  }
			  var url = videoJson["url"];
			  var characterName = videoJson["characterName"];
			  var sentence = videoJson["sentence"];
			  
			  output = output + "<div id=\"vid" + videoID + "\">" +
			  "<br><b><center>Video " + videoID + "</b><br>" +
   			  "<br><video height=" + 150 + " controls>" + "<source src=\"" + url + "\" type=\"video/ogg\"></video>" +
   			  "<br><b>" + characterName + ": </b>" + sentence +  
			  //"<div class='dropdown'><button onclick='myFunction()' class='dropbtn'>(Append To Playlist)</button><div id='myDropdown' class='dropdown-content'><div id='appendPlaylistList' style = 'font-size:14px; margin-left:-600px;'><script src = '../js/get_playlistList.js'></script></div></div></div>" +
			  "</><br></div>";
	  		}
	  }
	  searchList.innerHTML = output;

	}

/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
} 





