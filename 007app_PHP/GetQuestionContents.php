<?php
require_once('mysql_connect.php');
$pdo = connectDB();
$qid = $_POST["QID"];
if (!$qid) {
	echo "NG";
	return;
}
$qid = 1;
try {
	$pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
	$stmt = $pdo->prepare("SELECT * FROM ((question INNER JOIN question_c on question.QcontentsID = question_c.QcontentsID) INNER JOIN user on question.UserID = user.UserID) INNER JOIN date on question.DateID = date.DateID where QID = :qid");
	$stmt->bindParam(":qid", $qid, PDO::PARAM_INT);
	$stmt->execute();
	foreach ($stmt as $row) {	
                $res .= $row['QID'].'<:-o-:>';
                $res .= $row['Name'].'<:-o-:>';
                $res .= $row['Area'].'<:-o-:>';
                $res .= $row['Qcontents'].'<:-o-:>';
                $res .= $row['Anonimity'].'<:-o-:>';
                $res .= $row['Date'].'<:-o-:>';
        }
} catch (Exception $e) {
	$res = "NG";
}
$pdo = null;

echo $res;

