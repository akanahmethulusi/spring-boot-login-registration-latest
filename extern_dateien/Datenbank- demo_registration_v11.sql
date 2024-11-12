{\rtf1\ansi\ansicpg1252\cocoartf2513
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0

\f0\fs24 \cf0 -- phpMyAdmin SQL Dump\
-- version 4.9.7\
-- https://www.phpmyadmin.net/\
--\
-- Host: localhost:8889\
-- Erstellungszeit: 11. Nov 2024 um 14:51\
-- Server-Version: 5.7.32\
-- PHP-Version: 7.4.12\
\
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";\
SET time_zone = "+00:00";\
\
--\
-- Datenbank: `demo_registration_v11`\
--\
\
-- --------------------------------------------------------\
\
--\
-- Tabellenstruktur f\'fcr Tabelle `kunden`\
--\
\
CREATE TABLE `kunden` (\
  `id` int(11) NOT NULL,\
  `email` varchar(45) NOT NULL,\
  `first_name` varchar(45) NOT NULL,\
  `last_name` varchar(45) NOT NULL,\
  `password` varchar(15) NOT NULL,\
  `enabled` tinyint(1) NOT NULL\
) ENGINE=InnoDB DEFAULT CHARSET=utf8;\
\
--\
-- Daten f\'fcr Tabelle `kunden`\
--\
\
INSERT INTO `kunden` (`id`, `email`, `first_name`, `last_name`, `password`, `enabled`) VALUES\
(1, 'kilian@lebensvoll.at', 'Kilian', 'Stenker', '123456', 0),\
(2, 'mark@codefabrik.at', 'Mark', 'Stevanson', '4533322', 1),\
(3, 'ernst@codefabrik.at', 'Ernst', 'Klauser', '3499995', 0);\
\
--\
-- Indizes der exportierten Tabellen\
--\
\
--\
-- Indizes f\'fcr die Tabelle `kunden`\
--\
ALTER TABLE `kunden`\
  ADD PRIMARY KEY (`id`),\
  ADD UNIQUE KEY `UKh404f7i3wdul8w6gpcck2vcb1` (`email`);\
\
--\
-- AUTO_INCREMENT f\'fcr exportierte Tabellen\
--\
\
--\
-- AUTO_INCREMENT f\'fcr Tabelle `kunden`\
--\
ALTER TABLE `kunden`\
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;\
}