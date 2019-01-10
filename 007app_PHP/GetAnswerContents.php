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
	$stmt = $pdo->prepare("SELECT * FROM ((answer INNER JOIN answer_c on answer.AcontentsID = answer_c.AcontentsID) INNER JOIN user on answer.UserID = user.UserID) INNER JOIN date on answer.DateID = date.DateID where QID = :qid");
	$stmt->bindParam(":qid", $qid, PDO::PARAM_INT);
	$stmt->execute();
	foreach ($stmt as $row) {	
                $res .= $row['QID'].'<:-o-:>';
                $res .= $row['Name'].'<:-o-:>';
                $res .= $row['Area'].'<:-o-:>';
                $res .= $row['Acontents'].'<:-o-:>';
                $res .= $row['Anonimity'].'<:-o-:>';
                $res .= $row['Date'].'<:-o-:>';
        }
} catch (Exception $e) {
	$res = "NG";
}
$pdo = null;

echo $res;

