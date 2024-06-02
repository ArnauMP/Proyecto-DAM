<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    
    $host = 'localhost';
    $dbname = 'android';
    $usuario = 'root';
    $contraseña = '';

    $email = $_POST['email'];
    $password = $_POST['password'];

    try {
        // Conectar a la base de datos
        $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
        $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        $stmt = $conexion->prepare("SELECT * FROM users WHERE email = :email");
        $stmt->bindParam(':email', $email);
        $stmt->execute();

        $user = $stmt->fetch(PDO::FETCH_ASSOC);

        // Verificar si el usuario existe, la contraseña es correcta y el usuario está verificado
        if ($user && password_verify($password, $user['password'])) {
            if ($user['is_verified'] == 1) {
                echo json_encode(array("success" => true, "message" => "Login successful", "name" => $user['name']));
            } else {
                echo json_encode(array("success" => false, "message" => "Please verify your email before logging in."));
            }
        } else {
            echo json_encode(array("success" => false, "message" => "Invalid email or password"));
        }
    } catch (PDOException $e) {
        echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
    }
}
?>
