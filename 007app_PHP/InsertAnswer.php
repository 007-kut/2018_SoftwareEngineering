<?php
require_once('mysql_connect.php');
$pdo = connectDB();
$userID = $_POST["UserID"];
$aContents = $_POST["Acontents"];
$anonimity = $_POST["QID"];
$qID =$_POST["QID"];
if (!$userID || !$aContents || (!$anonimity == 0 && !$anonimity == 1)) {
	echo "NG";
	return;
}

try {
        $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
        $stmt = $pdo->prepare("INSERT INTO answer_c (Acontents) VALUES (:aContents)");
        $stmt->bindParam(":aContents", $aContents, PDO::PARAM_STR);
        $stmt->execute();

        $stmt2 = $pdo->prepare("SELECT AcontentsID FROM answer_c WHERE AcontentsID = (SELECT MAX(AcontentsID) FROM answer_c)");
        $stmt2->execute();
        foreach ($stmt2 as $row) {
                $aContentsID = $row['AcontentsID'];
        }

	date_default_timezone_set('Asia/Tokyo');
	$currentTime = date('Y/m/d H:i:s');
	$stmt3 = $pdo->prepare("INSERT INTO date (Date) VALUES (:currentTime)");
	$stmt3->bindParam(":currentTime", $currentTime, PDO::PARAM_STR);
	$stmt3->execute();

	
        $stmt4 = $pdo->prepare("SELECT DateID FROM date WHERE DateID = (SELECT MAX(DateID) FROM date)");
        $stmt4->execute();
	foreach ($stmt4 as $row) {
		$dateID = $row['DateID'];	
	}

        $stmt5 = $pdo->prepare("INSERT INTO answer (QID, UserID, AcontentsID, DateID, Anonimity) VALUES (:qID, :userID, :aContentsID, :dateID, :anonimity)");
	$stmt5->bindParam(":qID", $qID, PDO::PARAM_INT);
	$stmt5->bindParam(":userID", $userID, PDO::PARAM_INT);
	$stmt5->bindParam(":aContentsID", $aContentsID, PDO::PARAM_INT);
	$stmt5->bindParam(":dateID", $dateID, PDO::PARAM_INT);
	$stmt5->bindParam(":anonimity", $anonimity, PDO::PARAM_INT);
        $stmt5->execute();


	$res = "OK";
} catch (Exception $e) {
        $res = "NG";
}
$pdo = null;

echo $res;

