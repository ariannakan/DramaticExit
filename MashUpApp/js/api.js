// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point
var base_url = "https://hm9ttc9y8g.execute-api.us-east-2.amazonaws.com/beta"; //need to change once api is deployed
/**
var add_url    = base_url + "calculator";   // POST
var create_url = base_url + "constant";     // POST
var delete_url = base_url + "delete";       // Can't send JSON to DELETE request. This is POST
var list_url   = base_url + "constants";    // GET
**/

var add_playlist = base_url + "playlist";	//GET
var add_site	 = base_url + "site";		//GET + POST
var delete_site	 = base_url + "site";		//DELETE
var add_video 	 = base_url + "video";		//POST
var get_video	 = base_url + "video";		//GET