-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 05 Mar 2023, 21:46:46
-- Sunucu sürümü: 10.4.24-MariaDB
-- PHP Sürümü: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `case`
--
CREATE DATABASE IF NOT EXISTS `case` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `case`;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `players`
--

DROP TABLE IF EXISTS `players`;
CREATE TABLE IF NOT EXISTS `players` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `position` enum('POINT_GUARD','SHOOTING_GUARD','SMALL_FORWARD','POWER_FORWARD','CENTER') NOT NULL,
  `team_id` varchar(255) NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT current_timestamp(),
  `update_date` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `players`
--

INSERT INTO `players` (`id`, `name`, `surname`, `position`, `team_id`, `deleted`, `created_date`, `update_date`) VALUES
('2422aa4d-6b9b-4c5c-ba88-1b0b34badedf', 'shawn', 'marion', 'CENTER', '27d6a857-6d5a-4368-8636-0a3edc84c0c4', 1, '2023-02-24 18:20:02', '2023-02-24 22:32:25'),
('33005306-fc04-4d31-8bdc-5ef143bfc1ff', 'lebron', 'james', 'POWER_FORWARD', '8a0af5bf-f956-4320-98e3-3d5f3f69b83a', 0, '2023-02-24 18:20:41', '2023-02-24 22:47:43'),
('3ec8eccc-605f-497c-a16c-61fc7d725ad6', 'recaizade mahmut', 'ekrem', 'SHOOTING_GUARD', '27d6a857-6d5a-4368-8636-0a3edc84c0c4', 0, '2023-02-22 17:16:49', '2023-02-22 22:01:10'),
('3f8791bb-a2b7-4bcc-be41-23f7b6c73f7e', 'şemsi', 'paşa', 'POINT_GUARD', '4ce9689a-b2b1-11ed-afa1-0242ac120002', 0, '2023-02-22 17:15:46', '2023-02-22 17:15:46'),
('479f821a-21dd-4692-bc4e-5fe861e39f4b', 'shawn', 'marion', 'CENTER', '27d6a857-6d5a-4368-8636-0a3edc84c0c4', 0, '2023-02-24 18:11:42', '2023-02-24 18:11:42'),
('9cf520af-0d6b-4be6-8853-8b0be445335b', 'erel', 'colak', 'POINT_GUARD', '4ce9689a-b2b1-11ed-afa1-0242ac120002', 0, '2023-02-22 17:16:09', '2023-02-22 17:16:09'),
('a350945e-e83b-4653-ab23-e4e1bf7f061b', 'patrick', 'viera', 'SHOOTING_GUARD', '3fbad642-29fe-4046-8bc9-af72ab72bd36', 0, '2023-02-25 21:21:54', '2023-02-25 21:21:54'),
('aa1c795e-ffeb-4840-9096-82c7ba437b55', 'Shaquile', 'oneal', 'CENTER', '27d6a857-6d5a-4368-8636-0a3edc84c0c4', 0, '2023-02-24 18:11:11', '2023-02-24 18:11:11'),
('e603bce7-a435-499c-a3da-4508e5ea3fcb', 'tracy', 'mcgrady', 'SHOOTING_GUARD', '3fbad642-29fe-4046-8bc9-af72ab72bd36', 0, '2023-02-24 22:33:41', '2023-02-24 22:33:41'),
('ecbe7645-2683-444e-95db-55e00a0ca437', 'erbil', 'colak', 'POINT_GUARD', '4ce9689a-b2b1-11ed-afa1-0242ac120002', 0, '2023-02-22 17:16:03', '2023-02-22 17:16:03'),
('f9e28a37-6672-4560-82db-25483bb61d84', 'nazım', 'hikmet', 'POINT_GUARD', '4ce9689a-b2b1-11ed-afa1-0242ac120002', 0, '2023-02-22 17:16:30', '2023-02-22 17:16:30');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
('001af9ad-b55c-44c0-af9c-6fafb029ba1a', 'USER'),
('10c3aa7d-b10b-4ccc-821d-88e8a05016bb', 'ADMIN'),
('8b7b66f6-398a-4795-8847-da156fd202ca', 'MANAGER'),
('9ef030f8-4e9e-4da9-a068-57fbca3cedf7', 'MANAGER');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `teams`
--

DROP TABLE IF EXISTS `teams`;
CREATE TABLE IF NOT EXISTS `teams` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `name` varchar(50) NOT NULL,
  `capacity` int(11) NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT current_timestamp(),
  `update_date` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `teams`
--

INSERT INTO `teams` (`id`, `name`, `capacity`, `deleted`, `created_date`, `update_date`) VALUES
('27d6a857-6d5a-4368-8636-0a3edc84c0c4', 'Golden State', 4, 1, '2023-02-22 17:54:52', '2023-02-22 22:41:46'),
('3fbad642-29fe-4046-8bc9-af72ab72bd36', 'Charlotte', 15, 0, '2023-02-22 17:55:15', '2023-02-22 17:55:15'),
('7aa04b7c-c807-4181-b8db-ff1425457b54', 'Orlando Magic', 15, 0, '2023-02-22 17:55:27', '2023-02-22 17:55:27'),
('8a0af5bf-f956-4320-98e3-3d5f3f69b83a', 'Houston Rockets', 15, 0, '2023-02-22 17:53:46', '2023-02-22 17:53:46'),
('d2434c78-d9c0-49e8-a1ee-d18ed67a6e7f', 'San Antonio Spurs', 15, 1, '2023-02-22 17:55:57', '2023-02-25 18:37:43'),
('d4f3a578-338c-4077-a58b-6e279cedf9ad', 'Phoneix Suns', 15, 0, '2023-02-22 17:55:37', '2023-02-22 17:55:37'),
('dc6d28b6-384e-490c-b7e6-ed43b66cd5e7', 'Lakers', 15, 0, '2023-02-22 17:55:20', '2023-02-22 17:55:20'),
('f5098845-d9dc-49bb-a8df-7d3cee2435ba', 'Atlanta Hawks', 20, 0, '2023-02-24 23:04:19', '2023-02-25 18:36:09');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT current_timestamp(),
  `update_date` datetime NOT NULL DEFAULT current_timestamp(),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `deleted`, `created_date`, `update_date`) VALUES
('0ab1c95b-3697-44fa-96e3-6849d917075d', 'orhanveli', 'orhanveli@gmail.com', '$2a$10$4vr0F13nwBYjsnoiumsDg.EcoV/EsZPergHdzDuaww3.pLY6Ar4lO', 0, '2023-02-24 22:25:44', '2023-02-24 22:25:44'),
('1f32a091-80b9-4ea7-a14b-a931aa5d1750', 'memduh', 'esendal@gmail.com', '$2a$10$mvLQnyBsxGw4mVs.gQlmq.wgG1zJXu99779aDMhRYKK0EVcQCI2kO', 0, '2023-02-24 22:14:54', '2023-02-24 22:14:54'),
('25f1b0a8-c2b7-441b-b862-7ac445018956', 'halikarnasbalikcisi', 'balikcisi@gmail.com', '$2a$10$pBNwn7kPrrZE7L1frKMIV.YLAQGG9qJ79KdRPIWZsSMoYOXIT/RMC', 0, '2023-02-25 18:03:19', '2023-02-25 18:03:19'),
('2d9f3db3-68cc-45d1-9bed-4d45a877b8fc', 'producter', 'erbilcolak90@gmail.com', '$2a$10$A9pte7SAxsYB5hhKET56wO/drkUf6P6bnY4TMoSlw4Xy4T1p22wEy', 0, '2023-02-23 15:52:41', '2023-02-23 15:52:41'),
('33a8d34a-74de-403e-8b5d-a02532cbb9d5', 'nazım', 'hikmet@gmail.com', '$2a$10$e/KmfTN662QADQJJEAfhUu1QoF6tEUWa8iLfTpZYZBYR3L69DM4YK', 0, '2023-02-24 22:19:42', '2023-02-24 22:19:42'),
('a83a5673-a455-4282-a56b-93bc0c2ba66e', 'fuzuli', 'fuzuli@gmail.com', '$2a$10$OymuUvKGcPf3yGFKWDtQsuNuwgbFE3.T1d.xJVaHib2selj0sgHKe', 0, '2023-02-24 22:23:32', '2023-02-24 22:23:32'),
('f4676b6e-8724-4515-bd72-6c82ff92e991', 'erel', 'erelcolak87@gmail.com', '$2a$10$cXH7JL2bWpHUSYDn/Yb3Fu5kx.XQ2U6s/j2TvjlttHWpuOmJr7wym', 0, '2023-02-24 22:11:02', '2023-02-24 22:11:02');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `user_roles`
--

INSERT INTO `user_roles` (`id`, `user_id`, `role_id`) VALUES
('2257a591-1175-44eb-86a0-705ba44139ad', '33a8d34a-74de-403e-8b5d-a02532cbb9d5', '10c3aa7d-b10b-4ccc-821d-88e8a05016bb'),
('2847d738-8f5d-4dcd-a705-907bca3392d4', 'f4676b6e-8724-4515-bd72-6c82ff92e991', '001af9ad-b55c-44c0-af9c-6fafb029ba1a'),
('3e73a69a-35f6-4544-b745-7402d8313401', '33a8d34a-74de-403e-8b5d-a02532cbb9d5', '001af9ad-b55c-44c0-af9c-6fafb029ba1a'),
('60cfbaef-a591-4b40-a89d-bca7675d4d1d', '0ab1c95b-3697-44fa-96e3-6849d917075d', '001af9ad-b55c-44c0-af9c-6fafb029ba1a'),
('7536c30b-bcaf-42d4-a6b2-d363de268209', '2d9f3db3-68cc-45d1-9bed-4d45a877b8fc', '001af9ad-b55c-44c0-af9c-6fafb029ba1a'),
('81dae0b0-187e-48e6-930f-56f0ae924208', '2d9f3db3-68cc-45d1-9bed-4d45a877b8fc', '10c3aa7d-b10b-4ccc-821d-88e8a05016bb'),
('b50cf80a-2747-4be0-b127-eb6dc61b9243', 'f4676b6e-8724-4515-bd72-6c82ff92e991', '10c3aa7d-b10b-4ccc-821d-88e8a05016bb'),
('bc73d888-a932-4a8a-b0cc-12e4b0bc3123', '25f1b0a8-c2b7-441b-b862-7ac445018956', '001af9ad-b55c-44c0-af9c-6fafb029ba1a');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
