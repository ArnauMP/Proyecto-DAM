<?php
if ($_SERVER["REQUEST_METHOD"] == "DELETE") {
    // Configuración de la base de datos
    $host = 'localhost';
    $dbname = 'android';
    $usuario = 'root';
    $contraseña = '';

    $diet_id = $_GET['diet_id'];

    try {
        // Establecer la conexión a la base de datos
        $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
        $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        // Preparar y ejecutar la consulta
        $query = "DELETE FROM diets WHERE diet_id = :diet_id";
        $stmt = $conexion->prepare($query);
        $stmt->bindParam(':diet_id', $diet_id);
        $stmt->execute();

        // Respuesta según el resultado de la ejecución
        if ($stmt->rowCount() > 0) {
            echo json_encode(array("success" => true, "message" => "Dieta eliminada correctamente"));
        } else {
            echo json_encode(array("success" => false, "message" => "No se encontró la dieta o ya fue eliminada"));
        }
    } catch (PDOException $e) {
        echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
    }
}
?>
