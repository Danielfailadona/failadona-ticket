-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2025 at 09:45 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `theater_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_accounts`
--

CREATE TABLE `tbl_accounts` (
  `u_id` int(255) NOT NULL,
  `u_fname` varchar(50) NOT NULL,
  `u_lname` varchar(50) NOT NULL,
  `u_username` varchar(50) NOT NULL,
  `u_type` varchar(255) NOT NULL,
  `u_password` varchar(50) NOT NULL,
  `u_phone` varchar(20) NOT NULL,
  `u_image` varchar(255) NOT NULL,
  `u_status` varchar(50) NOT NULL,
  `security_question` varchar(255) NOT NULL,
  `security_answer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_accounts`
--

INSERT INTO `tbl_accounts` (`u_id`, `u_fname`, `u_lname`, `u_username`, `u_type`, `u_password`, `u_phone`, `u_image`, `u_status`, `security_question`, `security_answer`) VALUES
(1, 'John', 'Parker', 'Johnparker', 'Admin', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', '01234567890', '', 'Active', '', ''),
(2, 'daniel_test', 'failadona', 'dannyboy', 'Employee', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', '012341231234', 'src/images/Screenshot_20241208-234316(1).jpg', 'Active', 'What\'s your favorite Color?', 'test'),
(14, 'test', 'test', 'test2', 'Employee', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', '09165165165', '', 'Active', '', ''),
(23, 'test55', 'test55', 'test55', 'Employee', 'ukYAUwyTQQK0hAZOuWm08XEDhxFCpSTgriqjYy90tJA=', '9846584658465', '', 'Pending', '', ''),
(33, 'h', 'h', 'h', 'Employee', 'ukYAUwyTQQK0hAZOuWm08XEDhxFCpSTgriqjYy90tJA=', '32132132132231', '', 'Deleted', '', ''),
(34, 'test3', 'test3', 'test3', 'Employee', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', '84658465846', '', 'Deleted', '', ''),
(35, 'p', 'p', 'p', 'Employee', 'ukYAUwyTQQK0hAZOuWm08XEDhxFCpSTgriqjYy90tJA=', '465465465465', 'src/userimages/358a8efe7ddf86aad18d22a595a3f75e.jpg', 'Pending', '', ''),
(36, 'o', 'o', 'o', 'Employee', 'ukYAUwyTQQK0hAZOuWm08XEDhxFCpSTgriqjYy90tJA=', '7897987988998', 'src/userimages/eda05644406c0d6100690e6b9fd25bdf.jpg', 'Pending', '', ''),
(37, 'daniel_test', 'failadona', 'dannyboy', 'Employee', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', '012341231234', 'src/userimages/Screenshot_20241208-234316(1).jpg', 'Active', '', ''),
(38, 'oo', 'o', 'oo', 'Employee', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', '132213312111', '', 'Deleted', '', ''),
(39, 'Justine Ian', 'Valen', 'Valenj', 'Employee', 'xa38M2aDO/LMalWu+nE/AUW+ZDkkCBFEeL/Mgr1Q7zU=', '09106083345', '', 'Active', '', ''),
(40, 'Justine Ian', 'Valen', 'Valenj', 'Employee', 'xa38M2aDO/LMalWu+nE/AUW+ZDkkCBFEeL/Mgr1Q7zU=', '09106083345', 'src/userimages/Screenshot_20241208-234316(1).jpg', 'Active', '', ''),
(41, 'Johannes', 'Gwapo', 'gale12', 'Employee', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', '09957342019', '', 'Active', '', ''),
(42, 'yest', 'tset', 'maby12', 'Employee', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', '09081618046', '', 'Active', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_logs`
--

CREATE TABLE `tbl_logs` (
  `log_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `u_username` varchar(50) NOT NULL,
  `action_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `log_action` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_logs`
--

INSERT INTO `tbl_logs` (`log_id`, `u_id`, `u_username`, `action_time`, `log_action`) VALUES
(94, 1, 'Johnparker', '2025-04-06 08:48:05', 'Logged as Admin'),
(95, 1, 'Johnparker', '2025-04-06 08:48:14', 'User Deleted Account: h'),
(96, 1, 'Johnparker', '2025-04-06 08:51:23', 'Logged as Admin'),
(97, 2, 'dannyboy', '2025-04-06 09:17:03', 'Logged as Employee'),
(98, 2, 'dannyboy', '2025-04-06 09:17:16', 'User Changed Their Details'),
(99, 2, 'dannyboy', '2025-04-07 04:10:46', 'Logged as Employee'),
(100, 2, 'dannyboy', '2025-04-07 04:11:06', 'User Changed Their Password'),
(101, 2, 'dannyboy', '2025-04-07 05:58:32', 'Logged as Employee'),
(102, 2, 'dannyboy', '2025-04-07 06:21:18', 'Logged as Employee'),
(103, 2, 'dannyboy', '2025-04-07 06:24:00', 'Logged as Employee'),
(104, 34, 'test3', '2025-04-08 01:22:12', 'Registered for the first time'),
(105, 2, 'dannyboy', '2025-04-08 08:44:08', 'Logged as Employee'),
(106, 2, 'dannyboy', '2025-04-08 08:44:23', 'User Changed Their Details'),
(107, 2, 'dannyboy', '2025-04-08 08:46:26', 'Logged as Employee'),
(108, 2, 'dannyboy', '2025-04-08 08:46:41', 'User Changed Their Details'),
(109, 2, 'dannyboy', '2025-04-08 08:47:49', 'Logged as Employee'),
(110, 2, 'dannyboy', '2025-04-08 08:48:04', 'User Changed Their Details'),
(111, 2, 'dannyboy', '2025-04-08 08:49:47', 'Logged as Employee'),
(112, 2, 'dannyboy', '2025-04-08 08:49:57', 'User Changed Their Details'),
(113, 2, 'dannyboy', '2025-04-08 08:51:22', 'Logged as Employee'),
(114, 2, 'dannyboy', '2025-04-08 08:51:32', 'User Changed Their Details'),
(115, 2, 'dannyboy', '2025-04-08 08:53:45', 'Logged as Employee'),
(116, 2, 'dannyboy', '2025-04-08 08:53:55', 'User Changed Their Details'),
(117, 2, 'dannyboy', '2025-04-08 08:55:00', 'Logged as Employee'),
(118, 2, 'dannyboy', '2025-04-08 08:55:08', 'User Changed Their Details'),
(119, 2, 'dannyboy', '2025-04-08 13:54:05', 'Logged as Employee'),
(120, 2, 'dannyboy', '2025-04-08 13:54:19', 'User Changed Their Details'),
(121, 2, 'dannyboy', '2025-04-08 14:08:55', 'Logged as Employee'),
(122, 2, 'dannyboy', '2025-04-08 14:09:14', 'User Changed Their Details'),
(123, 2, 'dannyboy', '2025-04-09 07:09:23', 'Logged as Employee'),
(127, 2, 'dannyboy', '2025-04-09 07:50:29', 'Forgot, and changed their password'),
(128, 1, 'Johnparker', '2025-04-09 07:51:21', 'Logged as Admin'),
(129, 1, 'Johnparker', '2025-04-09 07:51:32', 'Admin Deleted Account: test3'),
(130, 1, 'Johnparker', '2025-04-09 08:17:27', 'Logged as Admin'),
(131, 1, 'Johnparker', '2025-04-10 06:54:27', 'Forgot, and changed their password'),
(132, 2, 'dannyboy', '2025-04-10 09:44:49', 'Logged as Employee'),
(133, 2, 'dannyboy', '2025-04-10 09:46:30', 'Logged as Employee'),
(134, 2, 'dannyboy', '2025-04-10 09:46:59', 'User Changed Their Details'),
(135, 2, 'dannyboy', '2025-04-10 11:23:02', 'Logged as Employee'),
(136, 2, 'dannyboy', '2025-04-10 11:24:55', 'Logged as Employee'),
(137, 2, 'dannyboy', '2025-04-10 11:25:20', 'User Changed Their Details'),
(138, 2, 'dannyboy', '2025-04-11 02:24:09', 'Logged as Employee'),
(139, 2, 'dannyboy', '2025-04-11 02:24:39', 'User Changed Their Details'),
(140, 2, 'dannyboy', '2025-04-11 02:34:14', 'Logged as Employee'),
(141, 2, 'dannyboy', '2025-04-11 02:35:43', 'User Changed Their Details'),
(142, 2, 'dannyboy', '2025-04-11 02:51:07', 'Logged as Employee'),
(143, 2, 'dannyboy', '2025-04-11 02:51:30', 'User Changed Their Details'),
(144, 2, 'dannyboy', '2025-04-11 08:40:06', 'Logged as Employee'),
(145, 2, 'dannyboy', '2025-04-11 08:40:51', 'User Changed Their Details'),
(146, 2, 'dannyboy', '2025-04-12 02:42:00', 'Logged as Employee'),
(147, 2, 'dannyboy', '2025-04-12 02:42:35', 'User Changed Their Details'),
(148, 2, 'dannyboy', '2025-04-12 02:47:37', 'Logged as Employee'),
(149, 2, 'dannyboy', '2025-04-12 02:48:11', 'User Changed Their Details'),
(150, 2, 'dannyboy', '2025-04-12 02:52:05', 'Logged as Employee'),
(151, 2, 'dannyboy', '2025-04-12 02:52:44', 'User Changed Their Details'),
(153, 14, 'test2', '2025-04-24 06:12:45', 'Logged as Employee'),
(154, 1, 'Johnparker', '2025-04-26 02:34:28', 'Logged as Admin'),
(155, 1, 'Johnparker', '2025-04-26 02:36:55', 'Logged as Admin'),
(156, 1, 'Johnparker', '2025-04-26 02:38:37', 'Admin Added Account: q'),
(157, 1, 'Johnparker', '2025-04-26 02:51:43', 'Logged as Admin'),
(158, 1, 'Johnparker', '2025-04-26 02:52:21', 'Logged as Admin'),
(159, 1, 'Johnparker', '2025-04-26 02:55:24', 'Logged as Admin'),
(160, 1, 'Johnparker', '2025-04-26 02:58:34', 'Logged as Admin'),
(161, 1, 'Johnparker', '2025-04-26 03:02:47', 'Logged as Admin'),
(162, 1, 'Johnparker', '2025-04-26 03:05:41', 'Logged as Admin'),
(163, 1, 'Johnparker', '2025-04-26 03:06:17', 'Admin Added Account: p'),
(164, 1, 'Johnparker', '2025-04-26 03:16:29', 'Logged as Admin'),
(165, 1, 'Johnparker', '2025-04-26 03:17:17', 'Admin Added Account: o'),
(166, 1, 'Johnparker', '2025-04-26 08:04:52', 'Logged as Admin'),
(167, 1, 'Johnparker', '2025-04-26 08:05:45', 'Logged as Admin'),
(168, 1, 'Johnparker', '2025-04-26 08:20:57', 'Logged as Admin'),
(169, 1, 'Johnparker', '2025-04-26 08:24:10', 'Logged Out'),
(170, 2, 'dannyboy', '2025-04-26 08:37:50', 'Logged as Employee'),
(171, 2, 'dannyboy', '2025-04-26 08:38:16', 'User Changed Their Details'),
(172, 2, 'dannyboy', '2025-04-26 08:43:26', 'Logged as Employee'),
(173, 2, 'dannyboy', '2025-04-26 09:32:26', 'Logged as Employee'),
(174, 1, 'Johnparker', '2025-04-26 09:34:58', 'Logged as Admin'),
(175, 1, 'Johnparker', '2025-04-26 09:35:09', 'Logged Out'),
(176, 2, 'dannyboy', '2025-04-26 09:35:19', 'Logged as Employee'),
(177, 1, 'Johnparker', '2025-04-26 09:41:46', 'Logged as Admin'),
(179, 1, 'Johnparker', '2025-04-27 03:42:11', 'Logged as Admin'),
(180, 1, 'Johnparker', '2025-04-27 03:43:16', 'Logged as Admin'),
(181, 1, 'Johnparker', '2025-04-27 03:44:42', 'Logged as Admin'),
(182, 1, 'Johnparker', '2025-04-27 03:44:53', 'Admin Updated The Movie: The Matrix'),
(183, 1, 'Johnparker', '2025-04-27 03:45:24', 'Logged as Admin'),
(184, 1, 'Johnparker', '2025-04-27 03:45:37', 'Admin Updated The Movie: The Matrix'),
(185, 1, 'Johnparker', '2025-04-27 03:49:05', 'Logged as Admin'),
(186, 1, 'Johnparker', '2025-04-27 03:49:15', 'Admin Updated The Movie: The Matrix'),
(187, 1, 'Johnparker', '2025-04-27 03:49:30', 'Admin Updated The Movie: The Matrix'),
(188, 1, 'Johnparker', '2025-04-27 04:20:21', 'Logged as Admin'),
(189, 1, 'Johnparker', '2025-04-27 04:37:19', 'Logged as Admin'),
(190, 1, 'Johnparker', '2025-04-27 04:37:35', 'Admin Updated The Movie: The Matrix'),
(191, 1, 'Johnparker', '2025-04-27 04:43:42', 'Logged as Admin'),
(192, 1, 'Johnparker', '2025-04-27 04:43:56', 'Admin Updated The Movie: The Matrix'),
(193, 1, 'Johnparker', '2025-04-27 04:49:44', 'Logged as Admin'),
(194, 1, 'Johnparker', '2025-04-27 04:49:56', 'Admin Updated The Movie: The Matrix'),
(195, 1, 'Johnparker', '2025-04-27 04:58:03', 'Logged as Admin'),
(196, 1, 'Johnparker', '2025-04-27 04:58:40', 'Admin Added Account: oo'),
(197, 1, 'Johnparker', '2025-04-27 04:59:49', 'Admin Updated Account: oo'),
(198, 1, 'Johnparker', '2025-04-27 05:02:53', 'Logged as Admin'),
(199, 1, 'Johnparker', '2025-04-27 05:03:14', 'Admin Updated The Movie: The Matrix'),
(200, 1, 'Johnparker', '2025-04-27 05:22:01', 'Logged as Admin'),
(201, 1, 'Johnparker', '2025-04-27 05:22:49', 'Admin Updated The Movie: The Matrix'),
(202, 1, 'Johnparker', '2025-04-27 05:22:55', 'Admin Updated The Movie: The Matrix'),
(203, 1, 'Johnparker', '2025-04-27 05:38:49', 'Logged as Admin'),
(204, 1, 'Johnparker', '2025-04-27 05:39:09', 'Admin Updated The Movie: The Matrix'),
(205, 1, 'Johnparker', '2025-04-27 05:54:35', 'Logged as Admin'),
(206, 1, 'Johnparker', '2025-04-27 05:55:26', 'Admin Updated The Movie: The Matrix'),
(207, 1, 'Johnparker', '2025-04-27 06:06:26', 'Logged as Admin'),
(208, 1, 'Johnparker', '2025-04-27 06:06:41', 'Admin Updated The Movie: The Matrix'),
(209, 1, 'Johnparker', '2025-04-27 06:30:54', 'Logged as Admin'),
(210, 1, 'Johnparker', '2025-04-27 06:31:17', 'Admin Deleted Account: oo'),
(211, 1, 'Johnparker', '2025-04-27 06:32:13', 'Logged as Admin'),
(212, 1, 'Johnparker', '2025-04-27 06:32:24', 'Admin Updated The Movie: The Matrix'),
(213, 1, 'Johnparker', '2025-04-27 07:09:42', 'Logged as Admin'),
(214, 1, 'Johnparker', '2025-04-27 07:12:10', 'Logged as Admin'),
(215, 1, 'Johnparker', '2025-04-27 07:15:39', 'Logged as Admin'),
(216, 1, 'Johnparker', '2025-04-27 07:16:48', 'Logged as Admin'),
(217, 1, 'Johnparker', '2025-04-27 07:17:52', 'Logged as Admin'),
(218, 1, 'Johnparker', '2025-04-27 07:18:37', 'Logged as Admin'),
(219, 1, 'Johnparker', '2025-04-27 07:18:49', 'Admin Deleted Account: Johnparker'),
(220, 1, 'Johnparker', '2025-04-27 15:13:20', 'Logged as Admin'),
(221, 1, 'Johnparker', '2025-04-27 15:13:30', 'Logged Out'),
(222, 2, 'dannyboy', '2025-04-27 15:13:44', 'Logged as Employee'),
(223, 2, 'dannyboy', '2025-04-27 15:15:34', 'Logged as Employee'),
(224, 2, 'dannyboy', '2025-04-28 10:02:33', 'Logged as Employee'),
(225, 2, 'dannyboy', '2025-04-28 10:05:36', 'Logged as Employee'),
(226, 2, 'dannyboy', '2025-04-28 10:11:44', 'Logged as Employee'),
(227, 2, 'dannyboy', '2025-04-28 10:16:42', 'Logged as Employee'),
(228, 2, 'dannyboy', '2025-04-28 10:20:55', 'Logged as Employee'),
(229, 2, 'dannyboy', '2025-04-28 10:21:57', 'Logged as Employee'),
(230, 2, 'dannyboy', '2025-04-28 10:23:11', 'Logged as Employee'),
(231, 2, 'dannyboy', '2025-04-28 10:43:46', 'Logged as Employee'),
(232, 2, 'dannyboy', '2025-04-28 10:43:59', 'User made transaction ID: The Matrix'),
(233, 2, 'dannyboy', '2025-04-28 15:12:14', 'Logged as Employee'),
(234, 1, 'Johnparker', '2025-04-28 15:14:29', 'Logged as Admin'),
(235, 1, 'Johnparker', '2025-04-28 15:14:34', 'Logged Out'),
(236, 2, 'dannyboy', '2025-04-28 15:14:42', 'Logged as Employee'),
(237, 2, 'dannyboy', '2025-04-28 16:36:28', 'Logged as Employee'),
(238, 2, 'dannyboy', '2025-04-28 16:40:08', 'Logged as Employee'),
(239, 39, 'Valenj', '2025-04-29 07:58:28', 'Registered for the first time'),
(240, 39, 'Valenj', '2025-04-29 08:03:25', 'Logged as Employee'),
(241, 39, 'Valenj', '2025-04-29 08:04:00', 'User made transaction ID: The Matrix'),
(242, 39, 'Valenj', '2025-04-29 08:04:55', 'User Changed Their Details'),
(243, 1, 'Johnparker', '2025-04-29 08:07:02', 'Logged as Admin'),
(244, 39, 'Valenj', '2025-04-29 08:10:49', 'Logged Out'),
(245, 1, 'Johnparker', '2025-04-29 08:11:02', 'Logged as Admin'),
(246, 2, 'dannyboy', '2025-04-29 08:17:48', 'Logged as Employee'),
(247, 2, 'dannyboy', '2025-04-29 08:19:49', 'Logged as Employee'),
(248, 2, 'dannyboy', '2025-04-29 08:41:16', 'Logged as Employee'),
(249, 2, 'dannyboy', '2025-04-29 08:41:35', 'Logged Out'),
(250, 41, 'gale12', '2025-04-29 08:42:53', 'Registered for the first time'),
(251, 1, 'Johnparker', '2025-04-29 08:43:15', 'Logged as Admin'),
(252, 1, 'Johnparker', '2025-04-29 08:43:36', 'Admin Updated Account: gale12'),
(253, 1, 'Johnparker', '2025-04-29 08:43:41', 'Logged Out'),
(254, 42, 'maby12', '2025-04-29 08:46:19', 'Registered for the first time'),
(255, 42, 'maby12', '2025-04-29 08:47:03', 'Logged as Employee'),
(256, 42, 'maby12', '2025-04-29 08:48:29', 'Logged Out'),
(257, 1, 'Johnparker', '2025-04-29 09:03:38', 'Logged as Admin'),
(258, 1, 'Johnparker', '2025-04-29 09:06:03', 'Logged Out'),
(259, 2, 'dannyboy', '2025-04-29 09:06:12', 'Logged as Employee'),
(260, 2, 'dannyboy', '2025-04-29 09:06:28', 'User made transaction ID: The Matrix'),
(261, 2, 'dannyboy', '2025-04-29 09:07:08', 'User made transaction ID: The Matrix'),
(262, 2, 'dannyboy', '2025-04-29 09:07:31', 'Logged Out'),
(263, 2, 'dannyboy', '2025-04-29 09:07:40', 'Logged as Employee'),
(264, 1, 'Johnparker', '2025-05-05 02:33:16', 'Logged as Admin'),
(265, 1, 'Johnparker', '2025-05-05 02:33:33', 'Admin Added The Movie: Avatar'),
(266, 1, 'Johnparker', '2025-05-25 08:34:15', 'Logged as Admin'),
(267, 1, 'Johnparker', '2025-05-25 08:35:07', 'Admin Added The Movie: test'),
(268, 1, 'Johnparker', '2025-05-25 08:35:49', 'Admin Updated The Movie: Howl Moving Castle'),
(269, 1, 'Johnparker', '2025-05-25 08:42:06', 'Admin Updated The Movie: Howl Moving Castle'),
(270, 1, 'Johnparker', '2025-05-25 08:44:27', 'Admin Updated The Movie: Howl Moving Castle'),
(271, 1, 'Johnparker', '2025-05-25 08:50:02', 'Logged as Admin'),
(272, 1, 'Johnparker', '2025-05-25 08:51:35', 'Logged as Admin'),
(273, 1, 'Johnparker', '2025-05-25 08:52:01', 'Admin Updated The Movie: Avatar'),
(274, 1, 'Johnparker', '2025-05-25 08:52:26', 'Admin Updated The Movie: The Matrix'),
(275, 1, 'Johnparker', '2025-05-25 08:53:21', 'Admin Added The Movie: Song of the Sea'),
(276, 1, 'Johnparker', '2025-05-25 12:07:40', 'Logged as Admin'),
(277, 1, 'Johnparker', '2025-05-25 12:07:45', 'Logged Out'),
(278, 2, 'dannyboy', '2025-05-25 12:07:57', 'Logged as Employee'),
(279, 2, 'dannyboy', '2025-05-26 01:17:03', 'Logged as Employee'),
(280, 2, 'dannyboy', '2025-05-26 01:17:22', 'Logged Out'),
(281, 1, 'Johnparker', '2025-05-26 01:17:36', 'Logged as Admin'),
(282, 1, 'Johnparker', '2025-05-26 02:05:47', 'Logged as Admin'),
(283, 2, 'dannyboy', '2025-05-26 13:57:27', 'Logged as Employee'),
(284, 1, 'Johnparker', '2025-05-27 02:06:06', 'Logged as Admin'),
(285, 1, 'Johnparker', '2025-05-27 02:06:22', 'Logged Out'),
(286, 2, 'dannyboy', '2025-05-27 02:06:35', 'Logged as Employee');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_orders`
--

CREATE TABLE `tbl_orders` (
  `o_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `p_id` int(11) NOT NULL,
  `quantity` int(50) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status` varchar(255) NOT NULL,
  `o_total` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_orders`
--

INSERT INTO `tbl_orders` (`o_id`, `u_id`, `p_id`, `quantity`, `date`, `status`, `o_total`) VALUES
(1, 2, 1, 2, '2025-04-28 10:43:57', 'Succesful', 1000.00),
(2, 39, 1, 3, '2025-04-29 08:03:56', 'Succesful', 3000.00),
(3, 2, 1, 1, '2025-04-29 09:06:26', 'Succesful', 500.00),
(4, 2, 1, 1, '2025-04-29 09:07:06', 'Succesful', 500.00);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_products`
--

CREATE TABLE `tbl_products` (
  `p_id` int(20) NOT NULL,
  `p_name` varchar(50) NOT NULL,
  `p_price` decimal(10,2) NOT NULL,
  `p_quantity` int(11) NOT NULL,
  `p_status` varchar(50) NOT NULL,
  `p_image` varchar(50) NOT NULL,
  `p_sold` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_products`
--

INSERT INTO `tbl_products` (`p_id`, `p_name`, `p_price`, `p_quantity`, `p_status`, `p_image`, `p_sold`) VALUES
(1, 'The Matrix', 500.00, 13, 'Available', 'src/userimages/Matrix.jpg', '2'),
(2, 'Avatar', 100.00, 100, 'Available', 'src/userimages/Avatar Poster 2009.jpg', ''),
(3, 'Howl Moving Castle', 100.00, 80, 'Available', 'src/userimages/Howls Moving Castle (2004).jpg', ''),
(4, 'Song of the Sea', 100.00, 80, 'Available', 'src/userimages/Song of the sea.jpg', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_accounts`
--
ALTER TABLE `tbl_accounts`
  ADD PRIMARY KEY (`u_id`);

--
-- Indexes for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `fk_log_user_id` (`u_id`);

--
-- Indexes for table `tbl_orders`
--
ALTER TABLE `tbl_orders`
  ADD PRIMARY KEY (`o_id`),
  ADD KEY `uid` (`u_id`),
  ADD KEY `pid` (`p_id`);

--
-- Indexes for table `tbl_products`
--
ALTER TABLE `tbl_products`
  ADD PRIMARY KEY (`p_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_accounts`
--
ALTER TABLE `tbl_accounts`
  MODIFY `u_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=287;

--
-- AUTO_INCREMENT for table `tbl_orders`
--
ALTER TABLE `tbl_orders`
  MODIFY `o_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_products`
--
ALTER TABLE `tbl_products`
  MODIFY `p_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  ADD CONSTRAINT `fk_log_user_id` FOREIGN KEY (`u_id`) REFERENCES `tbl_accounts` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_orders`
--
ALTER TABLE `tbl_orders`
  ADD CONSTRAINT `pid` FOREIGN KEY (`p_id`) REFERENCES `tbl_products` (`p_id`),
  ADD CONSTRAINT `uid` FOREIGN KEY (`u_id`) REFERENCES `tbl_accounts` (`u_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
