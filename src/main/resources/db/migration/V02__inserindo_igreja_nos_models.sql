ALTER TABLE `appvlouvor`.`equipe` 
ADD COLUMN `equ_igreja` INT(11) NOT NULL AFTER `equ_id`;

ALTER TABLE `appvlouvor`.`funcao` 
ADD COLUMN `fun_igreja` INT(11) NULL AFTER `fun_nome`;

ALTER TABLE `appvlouvor`.`grupo` 
ADD COLUMN `gru_igreja` INT(11) NOT NULL AFTER `gru_nome`;

ALTER TABLE `appvlouvor`.`repertorio` 
ADD COLUMN `rep_igreja` INT(11) NOT NULL AFTER `equipe_do_dia_equ_id`;

ALTER TABLE `appvlouvor`.`musica` 
ADD COLUMN `mus_igreja` INT(11) NOT NULL AFTER `gru_id`;

ALTER TABLE `appvlouvor`.`usuario` 
CHANGE COLUMN `igr_id` `igr_id` INT(11) NOT NULL ;

