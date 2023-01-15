INSERT INTO USER (id, confirmation_token, username, enabled, first_name, last_name, gender, password, authority, lastseen)
VALUES
(3, 'AAAAAAA', 'student@utrains.test', 1, 'student', 'Utrains student', 'Male', 'school1', 'ROLE_ADMIN', '05/01/2022'),
(4, 'BBBBBBB', 'root@utrains.test', 1, 'root','Utrains ROOT','Female', 'root_pass', 'ROLE_USER', '05/01/2022'),
(5, 'CCCCCCC', 'mekano@utrains.test', 1, 'Mekano','hermann','Male', 'mekano_pass', 'ROLE_DOCTOR', '05/01/2022'),
(6, 'DDDDDDD', 'alain-pierre@utrains.test', 1, 'Alain','Pierre','Male', 'alain_pass', 'ROLE_ADMIN', '05/01/2022'),
(7, 'EEEEEEE', 'dev@utrains.test', 1,'Dev', 'User','Male', 'dev_test', 'ROLE_PATIENT', '05/01/2022'),
(8, 'GGGGGGG', 'serge@utrains.test', 1, 'Serge','Prof.','Male', 'serge_pass', 'ROLE_ADMIN', '05/01/2022'),
(31, 'AAAAAAA', 'student2@utrains.test', 1, 'student', 'Utrains student', 'Male', 'school1', 'ROLE_ADMIN', '05/01/2022'),
(41, 'BBBBBBB', 'root2@utrains.test', 1, 'root','Utrains ROOT','Female', 'root_pass', 'ROLE_USER', '05/01/2022'),
(51, 'CCCCCCC', 'mekano2@utrains.test', 1, 'Mekano','hermann','Male', 'mekano_pass', 'ROLE_DOCTOR', '05/01/2022'),
(61, 'DDDDDDD', 'alain-pierre2@utrains.test', 1, 'Alain','Pierre','Male', 'alain_pass', 'ROLE_ADMIN', '05/01/2022'),
(71, 'EEEEEEE', 'dev2@utrains.test', 1, 'dev', 'User','Male', 'dev_test', 'ROLE_PATIENT', '05/01/2022'),
(81, 'GGGGGGG', 'serge2@utrains.test', 1, 'Serge','Prof.','Male', 'serge_pass', 'ROLE_ADMIN', '05/01/2022');
commit;