<?php
require_once('mysql_connect.php');
$pdo = connectDB();
echo "[AcontentsID]  [Acontents] <br>";
try {
        $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
        $stmt = $pdo->prepare("SELECT * FROM answer_c");
        $stmt->execute();
        foreach ($stmt as $key=>$row) {
                echo "<br>";
                echo "[".$row['AcontentsID']."] ";
                echo "[".$row['Acontents']."] ";
                echo "<br>";
        }
} catch (Exception $e) {
        echo "Error";
}
$pdo = null;

