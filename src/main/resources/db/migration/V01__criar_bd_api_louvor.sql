CREATE TABLE `categoria` (
  `cat_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `cat_nome` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `categoria` VALUES (1,'Rádipa'),(2,'Média-Rápida'),(3,'Média'),(4,'Média-Lenta'),(5,'Lenta');


CREATE TABLE `equipe` (
  `equ_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `baterista` varchar(255) DEFAULT NULL,
  `guitarrista` varchar(255) DEFAULT NULL,
  `tecladista` varchar(255) DEFAULT NULL,
  `violonista` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `equipe` VALUES (1,'Diego','Admin','Admin','Admin'),(2,'Diego','Admin','Admin','Admin'),(3,'Diego','Admin','Admin','Admin');


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
INSERT INTO `grupo` VALUES (1, true,'Avivah'),(2,true,'HillSong');


CREATE TABLE `igreja` (
  `igr_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `ativo` boolean NOT NULL,
  `igr_nome` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `igreja` VALUES (1,true,'Águas Claras'),(2,true,'Samambaia');


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
  `mus_nota_original` int(11) DEFAULT NULL,
  `mus_nota_tocada` int(11) DEFAULT NULL,
  `cat_id` int(11) DEFAULT NULL,
  `gru_id` int(11) DEFAULT NULL,
  KEY `FK956ym5bcapiy59bdx9xx9fg52` (`cat_id`),
  KEY `FK1sqiptrejlh6vmqwrf7ifplt` (`gru_id`),
  CONSTRAINT `FK1sqiptrejlh6vmqwrf7ifplt` FOREIGN KEY (`gru_id`) REFERENCES `grupo` (`gru_id`),
  CONSTRAINT `FK956ym5bcapiy59bdx9xx9fg52` FOREIGN KEY (`cat_id`) REFERENCES `categoria` (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `musica` VALUES (1,true,'04/07/2019',120,'https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms','https://drive.google.com/open?id=1bsaEgF12BDMrEcwWtqm8-viERnghPEqW','https://drive.google.com/open?id=1bsaEgF12BDMrEcwWtqm8-viERnghPEqW','https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms','O Senhor é Bom',2,2,1,1),(2,true,'04/07/2019',120,'https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms','https://drive.google.com/open?id=1bsaEgF12BDMrEcwWtqm8-viERnghPEqW','https://drive.google.com/open?id=1bsaEgF12BDMrEcwWtqm8-viERnghPEqW','https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms','O Senhor é Bom',2,2,1,1);

CREATE TABLE `tut_baixo` (
  `musica_mus_id` int(11) NOT NULL,
  `baixo` varchar(255) DEFAULT NULL,
  KEY `FKia9bl52hb87k3svflort9recb` (`musica_mus_id`),
  CONSTRAINT `FKia9bl52hb87k3svflort9recb` FOREIGN KEY (`musica_mus_id`) REFERENCES `musica` (`mus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `tut_baixo` VALUES (1,'https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms'),(2,'https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms');


CREATE TABLE `tut_bateria` (
  `musica_mus_id` int(11) NOT NULL,
  `bateria` varchar(255) DEFAULT NULL,
  KEY `FK7b2fk60lf388phnsx2i7h57oq` (`musica_mus_id`),
  CONSTRAINT `FK7b2fk60lf388phnsx2i7h57oq` FOREIGN KEY (`musica_mus_id`) REFERENCES `musica` (`mus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `tut_bateria` VALUES (1,'https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms'),(2,'https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms');


CREATE TABLE `tut_guitarra` (
  `musica_mus_id` int(11) NOT NULL,
  `guitarra` varchar(255) DEFAULT NULL,
  KEY `FKjp2b2yelj5n4wvluudqi6sse7` (`musica_mus_id`),
  CONSTRAINT `FKjp2b2yelj5n4wvluudqi6sse7` FOREIGN KEY (`musica_mus_id`) REFERENCES `musica` (`mus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `tut_guitarra` VALUES (1,'https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms'),(2,'https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms');


CREATE TABLE `tut_teclado` (
  `musica_mus_id` int(11) NOT NULL,
  `teclado` varchar(255) DEFAULT NULL,
  KEY `FKeeb36kd86ghiw4x75dj4nxq64` (`musica_mus_id`),
  CONSTRAINT `FKeeb36kd86ghiw4x75dj4nxq64` FOREIGN KEY (`musica_mus_id`) REFERENCES `musica` (`mus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `tut_teclado` VALUES (1,'https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms'),(2,'https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms');


CREATE TABLE `tut_violao` (
  `musica_mus_id` int(11) NOT NULL,
  `violao` varchar(255) DEFAULT NULL,
  KEY `FKses0c1om02v27fvphw4ux6pny` (`musica_mus_id`),
  CONSTRAINT `FKses0c1om02v27fvphw4ux6pny` FOREIGN KEY (`musica_mus_id`) REFERENCES `musica` (`mus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `tut_violao` VALUES (1,'https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms'),(2,'https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms');

CREATE TABLE `repertorio` (
  `rep_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `rep_data` varchar(255) NOT NULL,
  `equipe_do_dia_equ_id` int(11) DEFAULT NULL,
  `rep_criador` varchar(255) NOT NULL,
  KEY `FK9n4c71yairyrx24o9kcp8b9h5` (`equipe_do_dia_equ_id`),
  CONSTRAINT `FK9n4c71yairyrx24o9kcp8b9h5` FOREIGN KEY (`equipe_do_dia_equ_id`) REFERENCES `equipe` (`equ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `repertorio` VALUES (1,'04/07/2019',1,'diego'),(2,'04/07/2019',2,'diego');

CREATE TABLE `musica_repertorio` (
  `rep_id` int(11) NOT NULL,
  `mus_id` int(11) NOT NULL,
  PRIMARY KEY (`mus_id`,`rep_id`),
  KEY `FKibm4vcd8plabbl4dfmeggwjc0` (`rep_id`),
  CONSTRAINT `FKibm4vcd8plabbl4dfmeggwjc0` FOREIGN KEY (`rep_id`) REFERENCES `repertorio` (`rep_id`),
  CONSTRAINT `FKqky2xkdephmkwkjbvjtbk06uv` FOREIGN KEY (`mus_id`) REFERENCES `musica` (`mus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `musica_repertorio` VALUES (1,1),(1,2),(2,1),(2,2);


CREATE TABLE `usuario` (
  `usu_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `ativo` boolean NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `senha` varchar(255) NOT NULL,
  `igr_id` int(11) DEFAULT NULL,
  KEY `FK16nvvtypfda1lsmn3p9tytlr8` (`igr_id`),
  CONSTRAINT `FK16nvvtypfda1lsmn3p9tytlr8` FOREIGN KEY (`igr_id`) REFERENCES `igreja` (`igr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `usuario` VALUES (1,false,'diegoguitaibanez@gmail.com','Diego','(61)98576-9860','godemais',1),(2,true,'admin@gmail.com','Admin','(xx)xxxxx-xxxx','admin',1);

CREATE TABLE `perfis` (
  `usuario_usu_id` int(11) NOT NULL,
  `perfis` int(11) DEFAULT NULL,
  KEY `FKace66pc6qmk5qd1e2j2fmdmvg` (`usuario_usu_id`),
  CONSTRAINT `FKace66pc6qmk5qd1e2j2fmdmvg` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `perfis` VALUES (1,1),(2,2),(2,1);


CREATE TABLE `usuario_funcao` (
  `usu_id` int(11) NOT NULL,
  `fun_id` int(11) NOT NULL,
  KEY `FKrw4lrjee1qrv23qxh31yhcnb1` (`fun_id`),
  KEY `FKs072yiad0larkabeb41b79src` (`usu_id`),
  CONSTRAINT `FKrw4lrjee1qrv23qxh31yhcnb1` FOREIGN KEY (`fun_id`) REFERENCES `funcao` (`fun_id`),
  CONSTRAINT `FKs072yiad0larkabeb41b79src` FOREIGN KEY (`usu_id`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `usuario_funcao` VALUES (1,3),(2,1),(2,2),(2,3);

CREATE TABLE `equipe_ministro` (
  `equipe_equ_id` int(11) NOT NULL,
  `ministro` varchar(255) DEFAULT NULL,
  KEY `FKsymlri2w2bjh7h0mw0h5wqx2l` (`equipe_equ_id`),
  CONSTRAINT `FKsymlri2w2bjh7h0mw0h5wqx2l` FOREIGN KEY (`equipe_equ_id`) REFERENCES `equipe` (`equ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `equipe_ministro` VALUES (1,'Diego'),(2,'Admin');


