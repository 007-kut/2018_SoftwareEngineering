<?php
require_once('mysql_connect.php');
$pdo = connectDB();
echo "[QcontentsID]  [Qcontents] <br>";
try {
        $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
        $stmt = $pdo->prepare("SELECT * FROM question_c");
        $stmt->execute();
        foreach ($stmt as $key=>$row) {
                echo "<br>";
                echo "[".$row['QcontentsID']."] ";
                echo "[".$row['Qcontents']."] ";
                echo "<br>";
        }
} catch (Exception $e) {
        echo "Error";
}
$pdo = null;

