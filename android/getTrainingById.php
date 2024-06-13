<?php

if ($_SERVER["REQUEST_METHOD"] == "GET") {
    
    $host = 'localhost';
    $dbname = 'android';
    $usuario = 'root';
    $contraseña = '';

    $training_id = $_GET['training_id'];

    try {
        // Insertar datos en la base de datos
        $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
        $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        $stmt = $conexion->prepare("SELECT * FROM trainings WHERE training_id = :training_id");
        $stmt->bindParam(':training_id', $training_id, PDO::PARAM_INT);
        $stmt->execute();

        $trainings = $stmt->fetchAll(PDO::FETCH_ASSOC);

        // Establecer encabezados para JSON
        header('Content-Type: application/json');
        
        // Enviar respuesta JSON en caso de éxito
        echo json_encode(array("success" => true, "data" => $trainings));
    } catch (PDOException $e) {
        // Establecer encabezados para JSON
        header('Content-Type: application/json');
        
        // Enviar respuesta JSON en caso de error
        echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
    }
}
?>
