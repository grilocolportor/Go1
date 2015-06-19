<?php

	if(isset($_POST['country'])){
	
		$country = $_POST['country'];
		var_dump($country);
	
		// include db connect class
		require_once __DIR__ . '/db_connect.php';

		// connecting to db
		$db = new DB_CONNECT();

		
		// mysql query a new row
		$result = mysql_query("select * from country_t where iso2 = '$country'");
		if (!empty($result)) {
				// check for empty result
				if (mysql_num_rows($result) > 0) {
					
					$result = mysql_fetch_array($result);
					
					$retorno = array();
					$retorno["country"] = $result["short_name"];
					$retorno["cod"] = $result["calling_code"];
					//$response["phone"] = $phone ;
					
					$response["success"] = 1;
					//$response["country"] = array();
					//array_push($response["country"], $response);
					
				}
				echo json_encode($retorno);
				echo json_encode($response);
				
				echo 'não retornou vazio';
		}
	
		// check if row inserted or not
		if ($result) {
			// successfully inserted into database
			$response["success"] = 1;
			$response["message"] = "User successfully created.";

			// echoing JSON response
			echo json_encode($response);
		} else {
			// failed to insert row
			$response["success"] = 0;
			$response["message"] = "Oops! An error occurred.";

			// echoing JSON responsw
			//echo mysql_errno($db) . ": " . mysql_error($db) . "\n";
			echo json_encode($response);
		}
	}else {
		// required field is missing
		$response["success"] = 0;
		$response["message"] = "Required field(s) is missing";

		// echoing JSON response
		echo json_encode($response);
	}
	
	
	

?>