<?php
require_once('mysql_connect.php');
$pdo = connectDB();
echo "[UserID]	[Name]	[Area] <br>";
try {
        $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
        $stmt = $pdo->prepare("SELECT * FROM user");
        $stmt->execute();
        foreach ($stmt as $key=>$row) {
		echo "<br>";  
                echo "[".$row['UserID']."] ";	
                echo "[".$row['Name']."] ";
                echo "[".$row['Area']."] ";
		echo "<br>";  
        }
} catch (Exception $e) {
        echo "Error";
}
$pdo = null;

