<?php
    require 'send.php';

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        
        $host = 'localhost';
        $dbname = 'android';
        $usuario = 'root';
        $contraseña = '';

        $email = $_POST['email'];
        $password = password_hash($_POST['password'], PASSWORD_DEFAULT);
        $name = $_POST['name'];
        $verification_code = mt_rand(10000, 99999); // Generar un código de verificación

        try {
            // Insertar datos en la base de datos
            $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
            $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

            $stmt = $conexion->prepare("INSERT INTO users (email, password, name, verification_code, is_verified) VALUES (:email, :password, :name, :verification_code, 0)");
            $stmt->bindParam(':email', $email);
            $stmt->bindParam(':name', $name);
            $stmt->bindParam(':password', $password);
            $stmt->bindParam(':verification_code', $verification_code);
            $stmt->execute();

            // Enviar respuesta JSON en caso de éxito
            if (sendVerificationEmail($email, $verification_code)){
                echo json_encode(array("success" => true, "message" => "User created successfully"));
            }
            else{
                echo json_encode(array("success" => false, "message" => "User created but failed to send verification email."));
            }
        } catch (PDOException $e) {
            // Enviar respuesta JSON en caso de error
            echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
        }
    }
?>
