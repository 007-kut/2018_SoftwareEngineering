<?php
require_once('mysql_connect.php');
$pdo = connectDB();
$name = $_POST["Name"];
$area = $_POST["Area"];
if (!$name || !$area) {
echo "NG(NoParameter)";
return;
}

try {
        $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
        $stmt = $pdo->prepare("INSERT INTO user (Name, Area) VALUES (:name, :area)");
        $stmt->bindParam(":name", $name, PDO::PARAM_STR);
        $stmt->bindParam(":area", $area, PDO::PARAM_STR);
        $stmt->execute();

        $stmt2 = $pdo->prepare("SELECT UserID FROM user WHERE UserID = (SELECT MAX(UserID) FROM user)");
        $stmt2->execute();
	foreach ($stmt2 as $row) {
                $res = $row['UserID'];
        }
} catch (Exception $e) {
        $res = $e;
}
$pdo = null;

echo $res;

