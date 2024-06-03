<?php
if ($_SERVER["REQUEST_METHOD"] == "GET") {
    
    $host = 'localhost';
    $dbname = 'android';
    $usuario = 'root';
    $contraseña = '';

    try {
        // Conectar a la base de datos
        $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
        $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        // Preparar la consulta SQL
        $query = "SELECT * FROM diets";
        $stmt = $conexion->prepare($query);

        // Ejecutar la consulta
        $stmt->execute();
        $diets = $stmt->fetchAll(PDO::FETCH_ASSOC);

        // Devolver los resultados en formato JSON
        echo json_encode(array("success" => true, "data" => $diets));

    } catch (PDOException $e) {
        echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
    }
}
?>
