<?php

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        
        $host = 'localhost';
        $dbname = 'android';
        $usuario = 'root';
        $contraseña = '';

        $telf = $_POST['telf'];
        $birthday = $_POST['birthday'];
        $weight = $_POST['weight'];
        $sportFrecuency = $_POST['sportFrecuency'];
        $email = $_POST['email'];
        $role = 'VIP';

        // Convertir la fecha de formato DD/MM/YYYY a YYYY-MM-DD
        $date = DateTime::createFromFormat('m/d/Y', $birthday);
        $formatted_birthday = $date ? $date->format('Y-m-d') : null;

        try {
            // Insertar datos en la base de datos
            $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
            $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

            $stmt = $conexion->prepare("UPDATE users SET telf = :telf, birthday = :birthday, weight = :weight, sportFrecuency = :sportFrecuency, role = :role WHERE email = :email");
            $stmt->bindParam(':telf', $telf);
            $stmt->bindParam(':birthday', $formatted_birthday);
            $stmt->bindParam(':weight', $weight);
            $stmt->bindParam(':sportFrecuency', $sportFrecuency);
            $stmt->bindParam(':email', $email);
            $stmt->bindParam(':role', $role);
            $stmt->execute();

            // Enviar respuesta JSON en caso de éxito
            echo json_encode(array("success" => true, "message" => "User created successfully"));
        } catch (PDOException $e) {
            // Enviar respuesta JSON en caso de error
            echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
        }
    }
?>
