-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 27 jan. 2025 à 13:21
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `rhdb`
--

-- --------------------------------------------------------

--
-- Structure de la table `action_permission`
--

CREATE TABLE `action_permission` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `action_permission_seq`
--

CREATE TABLE `action_permission_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `action_permission_seq`
--

INSERT INTO `action_permission_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `affectation`
--

CREATE TABLE `affectation` (
  `id` bigint(20) NOT NULL,
  `employee` bigint(20) DEFAULT NULL,
  `unite_mere` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `affectation_seq`
--

CREATE TABLE `affectation_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `affectation_seq`
--

INSERT INTO `affectation_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `diplome`
--

CREATE TABLE `diplome` (
  `id` bigint(20) NOT NULL,
  `annee_obtention` int(11) DEFAULT NULL,
  `organisme` varchar(255) DEFAULT NULL,
  `specialie` varchar(255) DEFAULT NULL,
  `employee` bigint(20) DEFAULT NULL,
  `type` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `diplome_seq`
--

CREATE TABLE `diplome_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `diplome_seq`
--

INSERT INTO `diplome_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `lieu_naissance` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `ppr` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `fonction` bigint(20) DEFAULT NULL,
  `genre` bigint(20) DEFAULT NULL,
  `grade` bigint(20) DEFAULT NULL,
  `local` bigint(20) DEFAULT NULL,
  `situation_familiale` bigint(20) DEFAULT NULL,
  `unite_structurelle` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `employee_seq`
--

CREATE TABLE `employee_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `employee_seq`
--

INSERT INTO `employee_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `fonction`
--

CREATE TABLE `fonction` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `fonction`
--

INSERT INTO `fonction` (`id`, `code`, `libelle`) VALUES
(1, 'DRR', 'Directeur Régional'),
(2, 'CSRVR', 'Chef de Service Regional'),
(3, 'CSUB', 'Chef de Subdivision'),
(4, 'REC', 'Receveur'),
(5, 'CBUR', 'Chef de Bureau'),
(6, 'CCR', 'Chef de Centre'),
(7, 'CSECT', 'Chef de Section'),
(8, 'CSEC', 'Chef de Secteur'),
(9, 'ANET', 'ANET'),
(10, 'VERIF', 'Verificateur'),
(11, 'Audit', 'Auditeur'),
(12, 'CBRIG', 'Chef de Briguade');

-- --------------------------------------------------------

--
-- Structure de la table `fonction_seq`
--

CREATE TABLE `fonction_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `fonction_seq`
--

INSERT INTO `fonction_seq` (`next_val`) VALUES
(13);

-- --------------------------------------------------------

--
-- Structure de la table `genre`
--

CREATE TABLE `genre` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `genre`
--

INSERT INTO `genre` (`id`, `code`, `libelle`) VALUES
(1, 'M', 'Masculin'),
(2, 'F', 'Féminin');

-- --------------------------------------------------------

--
-- Structure de la table `genre_seq`
--

CREATE TABLE `genre_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `genre_seq`
--

INSERT INTO `genre_seq` (`next_val`) VALUES
(3);

-- --------------------------------------------------------

--
-- Structure de la table `grade`
--

CREATE TABLE `grade` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `grade`
--

INSERT INTO `grade` (`id`, `code`, `libelle`) VALUES
(1, 'T1G', 'Technicien des Finances 1er Grade'),
(2, 'T2G', 'Technicien des Finances 2eme Grade'),
(3, 'T3G', 'Technicien des Finances 3eme Grade'),
(4, 'T4G', 'Technicien des Finances 4eme Grade'),
(5, 'A1G', 'Administrateur des Finances 1er Grade'),
(6, 'A2G', 'Administrateur des Finances 2eme Grade'),
(7, 'A3G', 'Administrateur des Finances 3eme Grade');

-- --------------------------------------------------------

--
-- Structure de la table `grade_seq`
--

CREATE TABLE `grade_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `grade_seq`
--

INSERT INTO `grade_seq` (`next_val`) VALUES
(8);

-- --------------------------------------------------------

--
-- Structure de la table `local`
--

CREATE TABLE `local` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `local`
--

INSERT INTO `local` (`id`, `code`, `libelle`) VALUES
(1, 'T', 'Targa'),
(2, 'AF', 'Allal El Fassi'),
(3, 'CRF', 'Centre Regional de Formation'),
(4, 'JUD', 'Service Regional des Affaires Judiciaires'),
(5, 'SK', 'Subdivision El Kellaa des Sraghnas'),
(6, 'RK', 'RAF El Kelaa des Sraghnas'),
(7, 'B', 'Benguerir'),
(8, 'S1', 'Service d\'Assiette Safi'),
(9, 'S2', 'Bureau Enregistrement et Contrôle Safi'),
(10, 'Y', 'Subdivision Youssoufia'),
(11, 'E', 'Service Regional Essaouira'),
(12, 'I', 'Subdivision Imintanoute'),
(13, 'C', 'Secteur Chichaoua'),
(14, 'H', 'Subdivision Haouz');

-- --------------------------------------------------------

--
-- Structure de la table `local_seq`
--

CREATE TABLE `local_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `local_seq`
--

INSERT INTO `local_seq` (`next_val`) VALUES
(15);

-- --------------------------------------------------------

--
-- Structure de la table `minioinfos`
--

CREATE TABLE `minioinfos` (
  `id` bigint(20) NOT NULL,
  `bucket_name` varchar(255) DEFAULT NULL,
  `bytes` varbinary(255) DEFAULT NULL,
  `etag` varchar(255) DEFAULT NULL,
  `file_size` bigint(20) NOT NULL,
  `original_file_name` varchar(255) DEFAULT NULL,
  `result_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `minioinfos_seq`
--

CREATE TABLE `minioinfos_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `minioinfos_seq`
--

INSERT INTO `minioinfos_seq` (`next_val`) VALUES
(10000);

-- --------------------------------------------------------

--
-- Structure de la table `model_permission`
--

CREATE TABLE `model_permission` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `model_permission_seq`
--

CREATE TABLE `model_permission_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `model_permission_seq`
--

INSERT INTO `model_permission_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `model_permission_utilisateur`
--

CREATE TABLE `model_permission_utilisateur` (
  `id` bigint(20) NOT NULL,
  `sub_attribute` varchar(255) DEFAULT NULL,
  `value` bit(1) DEFAULT NULL,
  `action_permission` bigint(20) DEFAULT NULL,
  `model_permission` bigint(20) DEFAULT NULL,
  `user_app` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `model_permission_utilisateur_seq`
--

CREATE TABLE `model_permission_utilisateur_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `model_permission_utilisateur_seq`
--

INSERT INTO `model_permission_utilisateur_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `role_app`
--

CREATE TABLE `role_app` (
  `id` bigint(20) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `role_app`
--

INSERT INTO `role_app` (`id`, `authority`, `created_at`, `updated_at`) VALUES
(1, 'ROLE_ADMIN', '2025-01-27 11:32:03.000000', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `role_app_user_app`
--

CREATE TABLE `role_app_user_app` (
  `id` bigint(20) NOT NULL,
  `role` bigint(20) DEFAULT NULL,
  `user_app` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `role_app_user_app`
--

INSERT INTO `role_app_user_app` (`id`, `role`, `user_app`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `role_app_user_app_seq`
--

CREATE TABLE `role_app_user_app_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `role_app_user_app_seq`
--

INSERT INTO `role_app_user_app_seq` (`next_val`) VALUES
(51);

-- --------------------------------------------------------

--
-- Structure de la table `role_seq`
--

CREATE TABLE `role_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `role_seq`
--

INSERT INTO `role_seq` (`next_val`) VALUES
(2);

-- --------------------------------------------------------

--
-- Structure de la table `situation_familiale`
--

CREATE TABLE `situation_familiale` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `situation_familiale`
--

INSERT INTO `situation_familiale` (`id`, `code`, `libelle`) VALUES
(1, 'M', 'Marié'),
(2, 'D', 'Divorcé'),
(3, 'C', 'Célibataire'),
(4, 'V', 'Veuf');

-- --------------------------------------------------------

--
-- Structure de la table `situation_familiale_seq`
--

CREATE TABLE `situation_familiale_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `situation_familiale_seq`
--

INSERT INTO `situation_familiale_seq` (`next_val`) VALUES
(5);

-- --------------------------------------------------------

--
-- Structure de la table `type_diplome`
--

CREATE TABLE `type_diplome` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `type_diplome`
--

INSERT INTO `type_diplome` (`id`, `code`, `libelle`) VALUES
(1, 'Lic', 'Licence'),
(2, 'Mast', 'Master'),
(3, 'Ing', 'Ingenieur'),
(4, 'Doc', 'Doctorat');

-- --------------------------------------------------------

--
-- Structure de la table `type_diplome_seq`
--

CREATE TABLE `type_diplome_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `type_diplome_seq`
--

INSERT INTO `type_diplome_seq` (`next_val`) VALUES
(5);

-- --------------------------------------------------------

--
-- Structure de la table `unite_structurelle`
--

CREATE TABLE `unite_structurelle` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `unite_mere` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `unite_structurelle_seq`
--

CREATE TABLE `unite_structurelle_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `unite_structurelle_seq`
--

INSERT INTO `unite_structurelle_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `user_app`
--

CREATE TABLE `user_app` (
  `id` bigint(20) NOT NULL,
  `about` varchar(255) DEFAULT NULL,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `expiration_link_date` datetime(6) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `link_validation_code` varchar(255) DEFAULT NULL,
  `lockout_time` datetime(6) DEFAULT NULL,
  `login_attempts` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `password_changed` bit(1) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user_app`
--

INSERT INTO `user_app` (`id`, `about`, `account_non_expired`, `account_non_locked`, `avatar`, `created_at`, `credentials_non_expired`, `email`, `enabled`, `expiration_link_date`, `first_name`, `full_name`, `last_name`, `link_validation_code`, `lockout_time`, `login_attempts`, `password`, `password_changed`, `phone`, `updated_at`, `username`) VALUES
(1, NULL, b'1', b'1', NULL, '2025-01-27 11:32:03.000000', b'1', 'admin', b'1', NULL, NULL, NULL, NULL, NULL, NULL, 0, '$2a$10$fb8gC/7BxTILdl54BgDzduQH.BojfhOU7wYKYGQ1ZwApJhpgZdZXW', b'0', NULL, NULL, 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `user_app_seq`
--

CREATE TABLE `user_app_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user_app_seq`
--

INSERT INTO `user_app_seq` (`next_val`) VALUES
(2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `action_permission`
--
ALTER TABLE `action_permission`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `affectation`
--
ALTER TABLE `affectation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKic6w0319anfx1xwivlyg7lyvm` (`employee`),
  ADD KEY `FKr3xc8xt5ptlkhxb4e2xlcth7r` (`unite_mere`);

--
-- Index pour la table `diplome`
--
ALTER TABLE `diplome`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5ux00xmusf6yo4of7jqpa5p0d` (`employee`),
  ADD KEY `FKeuvb1h63tpu1g5em32ibqjuer` (`type`);

--
-- Index pour la table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt9hecogp3879gqv85cm9dec6m` (`fonction`),
  ADD KEY `FKj51fsdo6ilr0dout9yegkv33t` (`genre`),
  ADD KEY `FKss70y9pc4xynjns0y9b7m7c35` (`grade`),
  ADD KEY `FKvkqvbs814d0g8acvh5bhnihj` (`local`),
  ADD KEY `FKgynx9s2ptyujbntkm3p3va70b` (`situation_familiale`),
  ADD KEY `FKtgp3nt51nah1p9eby0fnpcwrp` (`unite_structurelle`);

--
-- Index pour la table `fonction`
--
ALTER TABLE `fonction`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `local`
--
ALTER TABLE `local`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `minioinfos`
--
ALTER TABLE `minioinfos`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `model_permission`
--
ALTER TABLE `model_permission`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `model_permission_utilisateur`
--
ALTER TABLE `model_permission_utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKanbrpecqteguq8exl5k9qrpom` (`action_permission`),
  ADD KEY `FKr1a7bvuyitnsb1mgrh800qpl` (`model_permission`),
  ADD KEY `FKra66iie8qbtkxfca1j902simg` (`user_app`);

--
-- Index pour la table `role_app`
--
ALTER TABLE `role_app`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `role_app_user_app`
--
ALTER TABLE `role_app_user_app`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpdp67d0rlhif9iqscbrhlqwch` (`role`),
  ADD KEY `FKg453g3aqxrweeeqml9d5wfipn` (`user_app`);

--
-- Index pour la table `situation_familiale`
--
ALTER TABLE `situation_familiale`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `type_diplome`
--
ALTER TABLE `type_diplome`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `unite_structurelle`
--
ALTER TABLE `unite_structurelle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfi71qaw53ig0nu6lsvkwo5ff1` (`unite_mere`);

--
-- Index pour la table `user_app`
--
ALTER TABLE `user_app`
  ADD PRIMARY KEY (`id`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `affectation`
--
ALTER TABLE `affectation`
  ADD CONSTRAINT `FKic6w0319anfx1xwivlyg7lyvm` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `FKr3xc8xt5ptlkhxb4e2xlcth7r` FOREIGN KEY (`unite_mere`) REFERENCES `unite_structurelle` (`id`);

--
-- Contraintes pour la table `diplome`
--
ALTER TABLE `diplome`
  ADD CONSTRAINT `FK5ux00xmusf6yo4of7jqpa5p0d` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `FKeuvb1h63tpu1g5em32ibqjuer` FOREIGN KEY (`type`) REFERENCES `type_diplome` (`id`);

--
-- Contraintes pour la table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FKgynx9s2ptyujbntkm3p3va70b` FOREIGN KEY (`situation_familiale`) REFERENCES `situation_familiale` (`id`),
  ADD CONSTRAINT `FKj51fsdo6ilr0dout9yegkv33t` FOREIGN KEY (`genre`) REFERENCES `genre` (`id`),
  ADD CONSTRAINT `FKss70y9pc4xynjns0y9b7m7c35` FOREIGN KEY (`grade`) REFERENCES `grade` (`id`),
  ADD CONSTRAINT `FKt9hecogp3879gqv85cm9dec6m` FOREIGN KEY (`fonction`) REFERENCES `fonction` (`id`),
  ADD CONSTRAINT `FKtgp3nt51nah1p9eby0fnpcwrp` FOREIGN KEY (`unite_structurelle`) REFERENCES `unite_structurelle` (`id`),
  ADD CONSTRAINT `FKvkqvbs814d0g8acvh5bhnihj` FOREIGN KEY (`local`) REFERENCES `local` (`id`);

--
-- Contraintes pour la table `model_permission_utilisateur`
--
ALTER TABLE `model_permission_utilisateur`
  ADD CONSTRAINT `FKanbrpecqteguq8exl5k9qrpom` FOREIGN KEY (`action_permission`) REFERENCES `action_permission` (`id`),
  ADD CONSTRAINT `FKr1a7bvuyitnsb1mgrh800qpl` FOREIGN KEY (`model_permission`) REFERENCES `model_permission` (`id`),
  ADD CONSTRAINT `FKra66iie8qbtkxfca1j902simg` FOREIGN KEY (`user_app`) REFERENCES `user_app` (`id`);

--
-- Contraintes pour la table `role_app_user_app`
--
ALTER TABLE `role_app_user_app`
  ADD CONSTRAINT `FKg453g3aqxrweeeqml9d5wfipn` FOREIGN KEY (`user_app`) REFERENCES `user_app` (`id`),
  ADD CONSTRAINT `FKpdp67d0rlhif9iqscbrhlqwch` FOREIGN KEY (`role`) REFERENCES `role_app` (`id`);

--
-- Contraintes pour la table `unite_structurelle`
--
ALTER TABLE `unite_structurelle`
  ADD CONSTRAINT `FKfi71qaw53ig0nu6lsvkwo5ff1` FOREIGN KEY (`unite_mere`) REFERENCES `unite_structurelle` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
