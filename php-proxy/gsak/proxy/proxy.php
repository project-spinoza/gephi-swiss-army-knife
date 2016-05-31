<?php
	
	$curl_handler = curl_init();
	curl_setopt($curl_handler, CURLOPT_HEADER, 0);
  	curl_setopt($curl_handler, CURLOPT_VERBOSE, 0);
  	curl_setopt($curl_handler, CURLOPT_RETURNTRANSFER, true);
  	curl_setopt($curl_handler, CURLOPT_USERAGENT, "Mozilla/4.0 (compatible;)");
	/*
	* ONLY get Requests to be Routed.
	*/
	if ($_SERVER['REQUEST_METHOD'] === 'GET') { 
		$url = "localhost:9090/".$_GET['route']."?".http_build_query($_GET);
		curl_setopt($curl_handler, CURLOPT_URL, $url);
	} else {
		// post actions...
	}
	
	$response = curl_exec($curl_handler);
  	curl_close($curl_handler);
	print $response;

?>