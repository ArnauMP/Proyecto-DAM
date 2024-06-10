-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-06-2024 a las 17:57:31
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
  `meal1` varchar(50) NOT NULL,
  `meal2` varchar(50) NOT NULL,
  `meal3` varchar(50) NOT NULL,
  `meal4` varchar(50) NOT NULL,
  `meal5` varchar(50) NOT NULL,
  `pre_workout` varchar(50) NOT NULL,
  `post_workout` varchar(50) NOT NULL,
  `diet_id` int(11) NOT NULL,
  `type` enum('DEFINITION','VOLUME','NEUTRAL','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trainings`
--

CREATE TABLE `trainings` (
  `exercise1` varchar(50) NOT NULL,
  `exercise2` varchar(50) NOT NULL,
  `exercise3` varchar(50) NOT NULL,
  `exercise4` varchar(50) NOT NULL,
  `exercise5` varchar(50) NOT NULL,
  `exercise6` varchar(50) NOT NULL,
  `training_id` int(11) NOT NULL,
  `type` enum('HYPERTROPHY','STRENGTH','','') NOT NULL
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
  `role` enum('ADMIN','TRAINER','VIP','FREE') DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `weight` int(10) DEFAULT NULL,
  `sportFrecuency` enum('NEVER','MONTHLY','WEEKLY','DAILY') NOT NULL,
  `diet` int(11) DEFAULT NULL,
  `training` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `diets`
--
ALTER TABLE `diets`
  ADD PRIMARY KEY (`diet_id`);

--
-- Indices de la tabla `trainings`
--
ALTER TABLE `trainings`
  ADD PRIMARY KEY (`training_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`email`),
  ADD KEY `fk_diet_id` (`diet`),
  ADD KEY `fk_training_id` (`training`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `diets`
--
ALTER TABLE `diets`
  MODIFY `diet_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `trainings`
--
ALTER TABLE `trainings`
  MODIFY `training_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_diet_id` FOREIGN KEY (`diet`) REFERENCES `diets` (`diet_id`) ON DELETE SET NULL,
  ADD CONSTRAINT `fk_training_id` FOREIGN KEY (`training`) REFERENCES `trainings` (`training_id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
