<?php
if ($_SERVER["REQUEST_METHOD"] == "GET") {
    
    $host = 'localhost';
    $dbname = 'android';
    $usuario = 'root';
    $contraseña = '';

    $filter = $_GET['filter'];

    try {
        // Insertar datos en la base de datos
        $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
        $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        if($filter == 'true'){
            $query = "SELECT * FROM users WHERE diet IS NOT NULL AND training IS NOT NULL";
        }else{
            $query = "SELECT * FROM users WHERE diet IS NULL AND training IS NULL";
        }
        $stmt = $conexion->prepare($query);
        $stmt->execute();

        $users = $stmt->fetchAll(PDO::FETCH_ASSOC);

        // Devolver los resultados en formato JSON
        echo json_encode(array("success" => true, "data" => $users));
    } catch (PDOException $e) {
        // Enviar respuesta JSON en caso de error
        echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
    }
}
?>
