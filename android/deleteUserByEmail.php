<?php
if ($_SERVER["REQUEST_METHOD"] == "DELETE") {
    // Configuración de la base de datos
    $host = 'localhost';
    $dbname = 'android';
    $usuario = 'root';
    $contraseña = '';

    // Obtener el email directamente de la URL
    $email = isset($_GET['email']) ? $_GET['email'] : '';

    try {
        // Establecer la conexión a la base de datos
        $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
        $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        // Preparar y ejecutar la consulta
        $query = "DELETE FROM users WHERE email = :email";
        $stmt = $conexion->prepare($query);
        $stmt->bindParam(':email', $email);
        $stmt->execute();

        // Respuesta según el resultado de la ejecución
        if ($stmt->rowCount() > 0) {
            echo json_encode(array("success" => true, "message" => "Usuario eliminado correctamente"));
        } else {
            echo json_encode(array("success" => false, "message" => "No se encontró el usuario o ya fue eliminado"));
        }
    } catch (PDOException $e) {
        echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
    }
}
?>
