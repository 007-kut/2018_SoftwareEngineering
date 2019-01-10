<?php
require_once('mysql_connect.php');
$pdo = connectDB();
$userID = $_POST["UserID"];
$qContents = $_POST["Qcontents"];
$anonimity = $_POST["Anonimity"];
if (!$userID || !$qContents || (!$anonimity == 0 && !$anonimity == 1)) {
	echo "NG";
	return;
}

try {
        $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
        $stmt = $pdo->prepare("INSERT INTO question_c (Qcontents) VALUES (:qContents)");
        $stmt->bindParam(":qContents", $qContents, PDO::PARAM_STR);
        $stmt->execute();

        $stmt2 = $pdo->prepare("SELECT QcontentsID FROM question_c WHERE QcontentsID = (SELECT MAX(QcontentsID) FROM question_c)");
        $stmt2->execute();
        foreach ($stmt2 as $row) {
                $qContentsID = $row['QcontentsID'];
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

        $stmt5 = $pdo->prepare("INSERT INTO question (UserID, QcontentsID, DateID, Anonimity) VALUES (:userID, :qContentsID, :dateID, :anonimity)");
	$stmt5->bindParam(":userID", $userID, PDO::PARAM_INT);
	$stmt5->bindParam(":qContentsID", $qContentsID, PDO::PARAM_INT);
	$stmt5->bindParam(":dateID", $dateID, PDO::PARAM_INT);
	$stmt5->bindParam(":anonimity", $anonimity, PDO::PARAM_INT);
        $stmt5->execute();


	$res = "OK";
} catch (Exception $e) {
        $res = "NG";
}
$pdo = null;

echo $res;

