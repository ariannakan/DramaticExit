// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point
var base_url = "https://hm9ttc9y8g.execute-api.us-east-2.amazonaws.com/beta"; //need to change once api is deployed
/**
var add_url    = base_url + "calculator";   // POST
var create_url = base_url + "constant";     // POST
var delete_url = base_url + "delete";       // Can't send JSON to DELETE request. This is POST
var list_url   = base_url + "constants";    // GET
**/

var list_playlists	= base_url + "library/playlist";	//GET
var add_site	 	= base_url + "library/site";		//POST
var get_site	 	= base_url + "library/site";		//GET
var delete_site	 	= base_url + "library/site/{url}";	//DELETE
var add_video 	 	= base_url + "library/video";		//POST
var get_video	 	= base_url + "library/video";		//GET
var search_video	= base_url + "library/video/search"	//POST
var delete_video 	= base_url + "library/video/{videoID}"	//DELETE
var update_video	= base_url + "library/video/{videoID}"	//PUT
var create_playlist	= base_url + "playlist"				//POST
var get_playlist	= base_url + "playlist/{playlistID}"	//GET
var append_video	= base_url + "playlist/{playlistID}"	//POST
var delete_playlist	= base_url + "playlist/{playlistID}"	//DELETE
var delete_playlist_video	= base_url + "playlist/{playlistID}/video/{videoID}"	//DELETE