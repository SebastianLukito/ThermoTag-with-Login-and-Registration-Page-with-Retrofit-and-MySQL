<?php
require 'vendor/autoload.php';

$dotenv = Dotenv\Dotenv::createImmutable(__DIR__);
$dotenv->load();

$servername = $_ENV['DB_SERVER'];
$username = $_ENV['DB_USERNAME'];
$password = $_ENV['DB_PASSWORD'];
$dbname = $_ENV['DB_NAME'];
$port = $_ENV['DB_PORT'];

$conn = new mysqli($servername, $username, $password, $dbname, $port);


if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}


if (isset($_POST['username']) && isset($_POST['password'])) {
    $username = $_POST['username'];
    $password = $_POST['password'];

    $sql = "SELECT password FROM users WHERE username='$username'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        
        $row = $result->fetch_assoc();
        $hashedPassword = $row['password'];
        
        
        if (substr($hashedPassword, 0, 7) === '$2y$10$') {
            if (password_verify($password, $hashedPassword)) {
                echo json_encode(array("status" => "success"));
            } else {
                echo json_encode(array("status" => "error", "message" => "Invalid password"));
            }
        } else {
            
            if ($password === $hashedPassword) {
                echo json_encode(array("status" => "success", "message" => "Password is not hashed, consider updating it."));
            } else {
                echo json_encode(array("status" => "error", "message" => "Invalid password"));
            }
        }
    } else {
        echo json_encode(array("status" => "error", "message" => "No user found"));
    }
} else {
    echo json_encode(array("status" => "error", "message" => "Username and password are required"));
}

$conn->close();
?>
