<?php
require_once('mysql_connect.php');
$pdo = connectDB();
echo "[AID]	[QID]	[UserID]	[AcontentsID]	[DateID] 	[Anonimity]<br>";
try {
        $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
        $stmt = $pdo->prepare("SELECT * FROM answer");
        $stmt->execute();
        foreach ($stmt as $key=>$row) {
                echo "<br>";
                echo "[".$row['AID']."]	";
                echo "[".$row['QID']."]	";
                echo "[".$row['UserID']."]	";
                echo "[".$row['AcontentsID']."]	";
                echo "[".$row['DateID']."]	";
                echo "[".$row['Anonimity']."]	";
                echo "<br>";
        }
} catch (Exception $e) {
        echo "Error";
}
$pdo = null;

