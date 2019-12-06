// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point
var base_url = "https://8dqaod91nl.execute-api.us-east-2.amazonaws.com/beta/"; //correct url for us
/**
var add_url    = base_url + "calculator";   // POST
var create_url = base_url + "constant";     // POST
var delete_url = base_url + "delete";       // Can't send JSON to DELETE request. This is POST
var list_url   = base_url + "constants";    // GET
**/

var list_playlists_url	= base_url + "library/playlist";	//GET
var register_site_url	= base_url + "library/site";		//POST
var get_sites_url	 	= base_url + "library/site";		//GET
var delete_site_url	 	= base_url + "library/site/{url}";	//POST / DELETE
var add_video_url 	 	= base_url + "library/video";		//POST
var list_videos_url	 	= base_url + "library/video";		//GET
var search_video_url	= base_url + "library/video/search"	//POST
var delete_video_url 	= base_url + "library/video/{videoID}"	//POST / DELETE
var update_video_url	= base_url + "library/video/{videoID}"	//PUT
var create_playlist_url	= base_url + "playlist"				//POST
var get_playlist_videos_url	= base_url + "playlist/{playlistID}"	//GET
var append_video_url	= base_url + "playlist/{playlistID}"	//PUT
var delete_playlist_url	= base_url + "playlist/{playlistID}"	//POST / DELETE
var delete_playlist_video_url	= base_url + "playlist/{playlistID}/video/{videoID}"	//POST / DELETE

//var update_video_url	= base_url + "library/uploadvideo/{videoID}"	//POST