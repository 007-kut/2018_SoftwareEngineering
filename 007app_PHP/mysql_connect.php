<?php
#ini_set( 'display_errors', 1 );
function connectDB(){
    $dsn = 'mysql:dbname=se;host=52.197.93.178;charset=utf8';
    $username = 'root';
    $password = '2018Se#007'; 
try {
	$pdo = new PDO($dsn, $username, $password);
    } catch (PDOException $e) {
        exit('' . $e->getMessage());
    }
    return $pdo;
}
