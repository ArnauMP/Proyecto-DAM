<?php

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        
        $host = 'localhost';
        $dbname = 'android';
        $usuario = 'root';
        $contraseña = '';

        $meal1 = $_POST['meal1'];
        $meal2 = $_POST['meal2'];
        $meal3 = $_POST['meal3'];
        $meal4 = $_POST['meal4'];
        $meal5 = $_POST['meal5'];
        $pre_workout = $_POST['pre_workout'];
        $post_workout = $_POST['post_workout'];
        $type = $_POST['type'];
        $diet_id = $_POST['diet_id'];

        try {
            // Insertar datos en la base de datos
            $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
            $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

            $stmt = $conexion->prepare("UPDATE diets SET meal1 = :meal1, meal2 = :meal2, meal3 = :meal3, meal4 = :meal4, meal5 = :meal5, pre_workout = :pre_workout, post_workout = :post_workout, type = :type WHERE diet_id = :diet_id");
            $stmt->bindParam(':meal1', $meal1);
            $stmt->bindParam(':meal2', $meal2);
            $stmt->bindParam(':meal3', $meal3);
            $stmt->bindParam(':meal4', $meal4);
            $stmt->bindParam(':meal5', $meal5);
            $stmt->bindParam(':pre_workout', $pre_workout);
            $stmt->bindParam(':post_workout', $post_workout);
            $stmt->bindParam(':type', $type);
            $stmt->bindParam(':diet_id', $diet_id);
            $stmt->execute();

            // Enviar respuesta JSON en caso de éxito
            echo json_encode(array("success" => true, "message" => "Dieta actualizada correctamente"));
        } catch (PDOException $e) {
            // Enviar respuesta JSON en caso de error
            echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
        }
    }
?>
