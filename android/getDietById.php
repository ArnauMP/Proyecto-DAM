<?php

if ($_SERVER["REQUEST_METHOD"] == "GET") {
    
    $host = 'localhost';
    $dbname = 'android';
    $usuario = 'root';
    $contraseña = '';

    $diet_id = $_GET['diet_id'];

    try {
        // Insertar datos en la base de datos
        $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
        $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        $stmt = $conexion->prepare("SELECT * FROM diets WHERE diet_id = :diet_id");
        $stmt->bindParam(':diet_id', $diet_id, PDO::PARAM_INT);
        $stmt->execute();

        $diets = $stmt->fetchAll(PDO::FETCH_ASSOC);

        // Establecer encabezados para JSON
        header('Content-Type: application/json');
        
        // Enviar respuesta JSON en caso de éxito
        echo json_encode(array("success" => true, "data" => $diets));
    } catch (PDOException $e) {
        // Establecer encabezados para JSON
        header('Content-Type: application/json');
        
        // Enviar respuesta JSON en caso de error
        echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
    }
}
?>
