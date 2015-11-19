INSERT INTO personnel(MID,AMID,role,lastName, firstName, address1,address2,city,state,zip,phone,specialty,email)
VALUES (9900000022,null,'hcp','Tran','Brooke','8546 Trail Lane','Apt. 202','Summerfield','NC','27358','999-888-2222','Optometrist','brooke_tran@iTrust.org'
) ON DUPLICATE KEY UPDATE mid = mid;

INSERT INTO users(MID, password, role, sQuestion, sAnswer) VALUES(9900000022, '30c952fab122c3f9759f02a6d95c3758b246b4fee239957b2d4fee46e26170c4', 'hcp', 'second letter?', 'r')
ON DUPLICATE KEY UPDATE mid = mid;
/*password: pw*/
INSERT INTO hcpassignedhos(HCPID, HosID) VALUES(9900000022,'9191919191'), (9900000022,'8181818181')
ON DUPLICATE KEY UPDATE HCPID = HCPID;

INSERT INTO personnel(MID,AMID,role,lastName, firstName, address1,address2,city,state,zip,phone,specialty,email)
VALUES (9900000023,null,'hcp','Laser','Dr','8546 Trail Lane','Apt. 202','Summerfield','NC','27358','999-888-2222','Ophthalmologist','laser@iTrust.org'
) ON DUPLICATE KEY UPDATE mid = mid;

INSERT INTO users(MID, password, role, sQuestion, sAnswer) VALUES(9900000023, '30c952fab122c3f9759f02a6d95c3758b246b4fee239957b2d4fee46e26170c4', 'hcp', 'second letter?', 'r')
ON DUPLICATE KEY UPDATE mid = mid;
/*password: pw*/
INSERT INTO hcpassignedhos(HCPID, HosID) VALUES(9900000023,'9191919191'), (9900000023,'8181818181')
ON DUPLICATE KEY UPDATE HCPID = HCPID;
