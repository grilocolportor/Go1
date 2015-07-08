<?php //string json contendo os dados de um funcionário

    //$json_str = '{"nome":"Jason Jones", "idade":38, "sexo": "M"}';

    $json_str = $_POST['usuario'];

    //faz o parsing na string, gerando um objeto PHP
    $jsonObj = json_decode($json_str);
    $usuario = $jsonObj->usuario;

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    foreach ( $usuario as $e ) {

        $areaCod = $e->areaCod;
        $country = $e->country;
        $imei = $e->imei;
        $user = $e->nome;
        $phoneNumber = $e->phone;
        $serialSim = $e->serialSim;
        $status = $e->status;
        $born = $e->born;

        // mysql inserting a new row
        $result = mysql_query("INSERT INTO USUARIO(COD, PHONE, NOME, IMEI, SERIAL_SIM, COUNTRY, STATUS, BORN )
                      VALUES('$areaCod', '$phoneNumber', '$user', '$imei', '$serialSim','$country', $status, '$born' )");

        echo "phone: $e->phoneNumber - country: $e->country - areaCod: $e->areaCod<br>";
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
        echo mysql_error($db) . ": " . mysql_error($db) . "\n";
        echo json_encode($response);
    }

//echo "nome: $obj->nome<br>";
//echo "idade: $obj->idade<br>";
//echo "sexo: $obj->sexo<br>";
?>

