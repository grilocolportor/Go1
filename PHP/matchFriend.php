<?php

	if(isset($_POST['json_friend'])){
		
		$json_friend = $_POST['json_friend'];

		$obj = json_decode($json_friend);

		$userPhone = $obj->phone;
		$friend = $obj->friend;
		
		// include db connect class
		require_once __DIR__ . '/db_connect.php';

		// connecting to db
		$db = new DB_CONNECT();
		
		//percorre a lista de contatos
		foreach($friend as $e){
			// get a product from products table
			$f = $e->friend;
			
			$result = mysql_query("select * from user where phone = $f" and status=0);
			
			//essa consulta e para procurar amigos
			//$result = mysql_query("select distinct user.nome, user.phone, friend.id_user, 
			//	friend.id_friend, friend.status from friend, user where ($f = friend.id_friend and $userPhone = friend.id_user)
			//	or ($userPhone = friend.id_friend and $f = friend.id_user)");

			if (!empty($result)) {
				// check for empty result
				if (mysql_num_rows($result) > 0) {

					//$result = mysql_fetch_array($result);

					
					$insert = mysql_query("INSERT INTO friend(id_user, id_friend)VALUES('$phone', '$f')");
					// success
					//$response["name"] = $result["user_name"];
					//$response["phone"] = $phone ;
					//$response["status"] = $result['status'];
					//$response["success"] = 1;
					//$response["match"] = 1;

					// user node
					//$response["product"] = array();

					//array_push($response["product"], $product);

					// echoing JSON response
					//echo json_encode($response);
				} else {
					// no product found
					//$response["success"] = 0;
					//$response["match"] = 0;

					// echo no users JSON
					//echo json_encode($response);
				}
			}
				
		}
		
	}
?>