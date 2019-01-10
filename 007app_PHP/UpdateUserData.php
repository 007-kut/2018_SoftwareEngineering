<?php
require_once('mysql_connect.php');
$pdo = connectDB();
$userID = $_POST["UserID"];
if (!$userID){
	echo "NG";
	return;
}
$name = $_POST["Name"];
$area = $_POST["Area"];
try {
	$pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
	if (strlen($name) > 0) {
        	$stmt = $pdo->prepare("UPDATE user SET name = :name where UserID = :userID");
	        $stmt->bindParam(":name", $name, PDO::PARAM_STR);
	        $stmt->bindParam(":userID", $userID, PDO::PARAM_STR);
        	$stmt->execute();
	}
	
	if (strlen($area) > 0) {
	        $stmt2 = $pdo->prepare("UPDATE user SET area = :area where UserID = :userID");
        	$stmt2->bindParam(":area", $area, PDO::PARAM_STR);
        	$stmt2->bindParam(":userID", $userID, PDO::PARAM_STR);
        	$stmt2->execute();
        }
	$res = "OK";
} catch (Exception $e) {
        $res = "NG";
}
$pdo = null;

echo $res;

