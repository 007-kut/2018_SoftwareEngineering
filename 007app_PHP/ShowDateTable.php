<?php
require_once('mysql_connect.php');
$pdo = connectDB();
echo "[DateID]	[Date] <br>";
try {
        $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
        $stmt = $pdo->prepare("SELECT * FROM date");
        $stmt->execute();
        foreach ($stmt as $key=>$row) {
		echo "<br>";  
                echo "[".$row['DateID']."] ";	
                echo "[".$row['Date']."] ";
		echo "<br>";  
        }
} catch (Exception $e) {
        echo "Error";
}
$pdo = null;

