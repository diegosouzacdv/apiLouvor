ALTER TABLE `appvlouvor`.`equipe` 
ADD COLUMN `equ_igreja` INT(11) NOT NULL AFTER `equ_id`,
add foreign key (equ_igreja) references igreja (igr_id) ON DELETE CASCADE;