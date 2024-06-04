<?php

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        
        $host = 'localhost';
        $dbname = 'android';
        $usuario = 'root';
        $contraseña = '';

        $exercise1 = $_POST['exercise1'];
        $exercise2 = $_POST['exercise2'];
        $exercise3 = $_POST['exercise3'];
        $exercise4 = $_POST['exercise4'];
        $exercise5 = $_POST['exercise5'];
        $exercise6 = $_POST['exercise6'];

        try {
            // Insertar datos en la base de datos
            $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
            $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

            $stmt = $conexion->prepare("INSERT INTO trainings (exercise1, exercise2, exercise3, exercise4, exercise5, exercise6) VALUES (:exercise1, :exercise2, :exercise3, :exercise4, :exercise5, :exercise6)");
            $stmt->bindParam(':exercise1', $exercise1);
            $stmt->bindParam(':exercise2', $exercise2);
            $stmt->bindParam(':exercise3', $exercise3);
            $stmt->bindParam(':exercise4', $exercise4);
            $stmt->bindParam(':exercise5', $exercise5);
            $stmt->bindParam(':exercise6', $exercise6);
            $stmt->execute();

            // Enviar respuesta JSON en caso de éxito
            echo json_encode(array("success" => true, "message" => "Entrenamiento creado correctamente"));
        } catch (PDOException $e) {
            // Enviar respuesta JSON en caso de error
            echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
        }
    }
?>
