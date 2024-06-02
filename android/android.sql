-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-06-2024 a las 18:56:59
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `android`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `diets`
--

CREATE TABLE `diets` (
  `breakfast` varchar(50) NOT NULL,
  `lunch` varchar(50) NOT NULL,
  `snack` varchar(50) NOT NULL,
  `dinner` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `diet_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trainings`
--

CREATE TABLE `trainings` (
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `reps` int(5) NOT NULL,
  `sets` int(5) NOT NULL,
  `training_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `verification_code` varchar(10) NOT NULL,
  `is_verified` tinyint(1) NOT NULL,
  `telf` varchar(20) DEFAULT NULL,
  `role` enum('ADMIN','TRAINER','VIP','USER') DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `weight` int(10) DEFAULT NULL,
  `sportFrecuency` enum('NEVER','MONTHLY','WEEKLY','DAILY') NOT NULL,
  `diet` varchar(50) DEFAULT NULL,
  `training` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `diets`
--
ALTER TABLE `diets`
  ADD PRIMARY KEY (`diet_id`),
  ADD KEY `fk_user_email` (`email`);

--
-- Indices de la tabla `trainings`
--
ALTER TABLE `trainings`
  ADD PRIMARY KEY (`training_id`),
  ADD KEY `fk_user_email_training` (`email`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`email`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `diets`
--
ALTER TABLE `diets`
  ADD CONSTRAINT `fk_user_email` FOREIGN KEY (`email`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `trainings`
--
ALTER TABLE `trainings`
  ADD CONSTRAINT `fk_user_email_training` FOREIGN KEY (`email`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
