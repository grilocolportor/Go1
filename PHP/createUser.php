<?php

	if(isset($_POST['json_user'])){
	
		$json_user = $_POST['json_user'];

		$obj = json_decode($json_user);

		$phone = $obj->phone;
		$name = $obj->name;
		$area_cod = $obj->area_Cod;
		$country = $obj->country;
		$imei = $obj->imei;
		$serial_sim = $obj->serial_Sim;
		$status = $obj->status;
	
		// include db connect class
		require_once __DIR__ . '/db_connect.php';

		// connecting to db
		$db = new DB_CONNECT();

		// mysql inserting a new row
		$result = mysql_query("INSERT INTO user(PHONE, NOME, AREA_COD, COUNTRY, IMEI, SERIAL_SIM, STATUS)
          VALUES('$phone', '$name', '$area_Cod', '$country', '$imei','$serial_sim', '$status')");
	
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