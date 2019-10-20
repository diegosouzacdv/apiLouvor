
CREATE TABLE `categoria` (
  `cat_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `cat_nome` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `categoria` VALUES (1,'Rápida'),(2,'Média-Rápida'),(3,'Média'),(4,'Média-Lenta'),(5,'Lenta');


CREATE TABLE `equipe` (
  `equ_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `baterista` varchar(255) DEFAULT NULL,
  `guitarrista` varchar(255) DEFAULT NULL,
  `tecladista` varchar(255) DEFAULT NULL,
  `violonista` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `funcao` (
  `fun_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `fun_nome` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `funcao` VALUES (1,'Ministro'),(2,'Violonista'),(3,'Guitarrista'),(4,'Baterista'),(5,'Tecladista'),(6,'Baixista');


CREATE TABLE `grupo` (
  `gru_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `ativo` boolean NOT NULL,
  `gru_nome` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `grupo` VALUES (1, true,'Avivah'),
						   (2,true,'HillSong'),
						   (3,true,'Elevation Worship');

CREATE TABLE `igreja` (
  `igr_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `ativo` boolean NOT NULL,
  `igr_nome` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `igreja` VALUES (1,true,'Águas Claras');


CREATE TABLE `musica` (
  `mus_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `ativo` boolean NOT NULL,
  `mus_data_inserida` varchar(255) DEFAULT NULL,
  `est_bpm` int(11) DEFAULT NULL,
  `est_cifra` varchar(255) DEFAULT NULL,
  `est_guia_instrumental` varchar(255) DEFAULT NULL,
  `est_guia_vocal` varchar(255) DEFAULT NULL,
  `est_letra` varchar(255) DEFAULT NULL,
  `mus_nome` varchar(80) NOT NULL,
  `mus_nota_original` varchar(255) DEFAULT NULL,
  `mus_nota_tocada` varchar(255) DEFAULT NULL,
  `cat_id` int(11) DEFAULT NULL,
  `gru_id` int(11) DEFAULT NULL,
  KEY `FK956ym5bcapiy59bdx9xx9fg52` (`cat_id`),
  KEY `FK1sqiptrejlh6vmqwrf7ifplt` (`gru_id`),
  CONSTRAINT `FK1sqiptrejlh6vmqwrf7ifplt` FOREIGN KEY (`gru_id`) REFERENCES `grupo` (`gru_id`),
  CONSTRAINT `FK956ym5bcapiy59bdx9xx9fg52` FOREIGN KEY (`cat_id`) REFERENCES `categoria` (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tut_baixo` (
  `musica_mus_id` int(11) NOT NULL,
  `baixo` varchar(255) DEFAULT NULL,
  KEY `FKia9bl52hb87k3svflort9recb` (`musica_mus_id`),
  CONSTRAINT `FKia9bl52hb87k3svflort9recb` FOREIGN KEY (`musica_mus_id`) REFERENCES `musica` (`mus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `tut_bateria` (
  `musica_mus_id` int(11) NOT NULL,
  `bateria` varchar(255) DEFAULT NULL,
  KEY `FK7b2fk60lf388phnsx2i7h57oq` (`musica_mus_id`),
  CONSTRAINT `FK7b2fk60lf388phnsx2i7h57oq` FOREIGN KEY (`musica_mus_id`) REFERENCES `musica` (`mus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `tut_guitarra` (
  `musica_mus_id` int(11) NOT NULL,
  `guitarra` varchar(255) DEFAULT NULL,
  KEY `FKjp2b2yelj5n4wvluudqi6sse7` (`musica_mus_id`),
  CONSTRAINT `FKjp2b2yelj5n4wvluudqi6sse7` FOREIGN KEY (`musica_mus_id`) REFERENCES `musica` (`mus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `tut_teclado` (
  `musica_mus_id` int(11) NOT NULL,
  `teclado` varchar(255) DEFAULT NULL,
  KEY `FKeeb36kd86ghiw4x75dj4nxq64` (`musica_mus_id`),
  CONSTRAINT `FKeeb36kd86ghiw4x75dj4nxq64` FOREIGN KEY (`musica_mus_id`) REFERENCES `musica` (`mus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;                                 

CREATE TABLE `tut_violao` (
  `musica_mus_id` int(11) NOT NULL,
  `violao` varchar(255) DEFAULT NULL,
  KEY `FKses0c1om02v27fvphw4ux6pny` (`musica_mus_id`),
  CONSTRAINT `FKses0c1om02v27fvphw4ux6pny` FOREIGN KEY (`musica_mus_id`) REFERENCES `musica` (`mus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `repertorio` (
  `rep_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `rep_data` varchar(255) NOT NULL,
  `rep_data_semana` varchar(255) NOT NULL,
  `equipe_do_dia_equ_id` int(11) DEFAULT NULL,
  `rep_criador` varchar(255) NOT NULL,
  `rep_ativo` boolean NOT NULL,
  KEY `FK9n4c71yairyrx24o9kcp8b9h5` (`equipe_do_dia_equ_id`),
  CONSTRAINT `FK9n4c71yairyrx24o9kcp8b9h5` FOREIGN KEY (`equipe_do_dia_equ_id`) REFERENCES `equipe` (`equ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `musica_repertorio` (
  `rep_id` int(11) NOT NULL,
  `mus_id` int(11) NOT NULL,
  PRIMARY KEY (`mus_id`,`rep_id`),
  KEY `FKibm4vcd8plabbl4dfmeggwjc0` (`rep_id`),
  CONSTRAINT `FKibm4vcd8plabbl4dfmeggwjc0` FOREIGN KEY (`rep_id`) REFERENCES `repertorio` (`rep_id`),
  CONSTRAINT `FKqky2xkdephmkwkjbvjtbk06uv` FOREIGN KEY (`mus_id`) REFERENCES `musica` (`mus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `usuario` (
  `usu_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `ativo` boolean NOT NULL,
  `disponivel` boolean NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `senha` varchar(255) NOT NULL,
  `igr_id` int(11) DEFAULT NULL,
  KEY `FK16nvvtypfda1lsmn3p9tytlr8` (`igr_id`),
  CONSTRAINT `FK16nvvtypfda1lsmn3p9tytlr8` FOREIGN KEY (`igr_id`) REFERENCES `igreja` (`igr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `perfis` (
  `usuario_usu_id` int(11) NOT NULL,
  `perfis` int(11) DEFAULT NULL,
  KEY `FKace66pc6qmk5qd1e2j2fmdmvg` (`usuario_usu_id`),
  CONSTRAINT `FKace66pc6qmk5qd1e2j2fmdmvg` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `usuario_funcao` (
  `usu_id` int(11) NOT NULL,
  `fun_id` int(11) NOT NULL,
  KEY `FKrw4lrjee1qrv23qxh31yhcnb1` (`fun_id`),
  KEY `FKs072yiad0larkabeb41b79src` (`usu_id`),
  CONSTRAINT `FKrw4lrjee1qrv23qxh31yhcnb1` FOREIGN KEY (`fun_id`) REFERENCES `funcao` (`fun_id`),
  CONSTRAINT `FKs072yiad0larkabeb41b79src` FOREIGN KEY (`usu_id`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `equipe_ministro` (
  `equipe_equ_id` int(11) NOT NULL,
  `ministro` varchar(255) DEFAULT NULL,
  KEY `FKsymlri2w2bjh7h0mw0h5wqx2l` (`equipe_equ_id`),
  CONSTRAINT `FKsymlri2w2bjh7h0mw0h5wqx2l` FOREIGN KEY (`equipe_equ_id`) REFERENCES `equipe` (`equ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `equipe_baixista` (
  `equipe_equ_id` int(11) NOT NULL,
  `baixista` varchar(255) DEFAULT NULL,
  KEY `FK9X9AFQ2EE96W5VHVUJT7LSV5Y` (`equipe_equ_id`),
  CONSTRAINT `FK9X9AFQ2EE96W5VHVUJT7LSV5Y` FOREIGN KEY (`equipe_equ_id`) REFERENCES `equipe` (`equ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `equipe_baterista` (
  `equipe_equ_id` int(11) NOT NULL,
  `baterista` varchar(255) DEFAULT NULL,
  KEY `FKFI1UDMLR3H374A2NKPRMBDQD5` (`equipe_equ_id`),
  CONSTRAINT `FKFI1UDMLR3H374A2NKPRMBDQD5` FOREIGN KEY (`equipe_equ_id`) REFERENCES `equipe` (`equ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `equipe_guitarrista` (
  `equipe_equ_id` int(11) NOT NULL,
  `guitarrista` varchar(255) DEFAULT NULL,
  KEY `FKF5PCJXJAHPK260WQCEMJ1DLFE` (`equipe_equ_id`),
  CONSTRAINT `FKF5PCJXJAHPK260WQCEMJ1DLFE` FOREIGN KEY (`equipe_equ_id`) REFERENCES `equipe` (`equ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `equipe_tecladista` (
  `equipe_equ_id` int(11) NOT NULL,
  `tecladista` varchar(255) DEFAULT NULL,
  KEY `FKKGXNQFMKHCMFDNWUUS9HNTJJU` (`equipe_equ_id`),
  CONSTRAINT `FKKGXNQFMKHCMFDNWUUS9HNTJJU` FOREIGN KEY (`equipe_equ_id`) REFERENCES `equipe` (`equ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `equipe_violonista` (
  `equipe_equ_id` int(11) NOT NULL,
  `violonista` varchar(255) DEFAULT NULL,
  KEY `FKCDLRIYHYQ4WU4TJSDG2TTIP0F` (`equipe_equ_id`),
  CONSTRAINT `FKCDLRIYHYQ4WU4TJSDG2TTIP0F` FOREIGN KEY (`equipe_equ_id`) REFERENCES `equipe` (`equ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



