<?php
if ($_SERVER["REQUEST_METHOD"] == "GET") {
    
    $host = 'localhost';
    $dbname = 'android';
    $usuario = 'root';
    $contraseña = '';

    $type = $_GET['type'];

    try {
        // Insertar datos en la base de datos
        $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
        $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        $stmt = $conexion->prepare("SELECT * FROM trainings WHERE type = :type");
        $stmt->bindParam(':type', $type);
        $stmt->execute();

        $trainings = $stmt->fetchAll(PDO::FETCH_ASSOC);

       // Devolver los resultados en formato JSON
        echo json_encode(array("success" => true, "data" => $trainings));
    } catch (PDOException $e) {
        // Enviar respuesta JSON en caso de error
        echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
    }
}
?>
