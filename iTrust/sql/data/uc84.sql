/* Brittany Franco (Dependent) */
INSERT INTO users (MID, Password, Role, sQuestion, sAnswer, isDependent)
	VALUES (73, '30c952fab122c3f9759f02a6d95c3758b246b4fee239957b2d4fee46e26170c4', 'patient', '2+2?', '4', 1)
	ON DUPLICATE KEY UPDATE MID = MID;
	
INSERT INTO patients 	(MID, lastName, firstName, email, address1, address2,
						city, state, zip, phone, eName, ePhone,
						DateOfBirth, BloodType, Ethnicity, Gender, TopicalNotes)
	VALUES	(73, 'Franco', 'Brittany', 'bfranco@gmail.com', '123 Broad St.', '',
			'Raleigh', 'NC', '27607', '555-666-7777', '', '',
			'2001-02-06', 'AB+', 'White', 'Female', 'Dependent of Brody Franco')
	ON DUPLICATE KEY UPDATE MID = MID;
	
/* Brody Franco (Representative) */
INSERT INTO patients
(MID, lastName,  firstName,email,address1,address2,city,state,zip,phone,eName,ePhone,iCName,iCAddress1,iCAddress2,iCCity, ICState,
iCZip,iCPhone,iCID,dateofbirth,mothermid,fathermid,bloodtype,ethnicity,gender, topicalnotes)
VALUES
(72,'Franco', 'Brody', 'franco_brody@gmail.com', '1247 Fireside Dr', 'Suite 106', 'Raleigh', 'NC', '27606-1234', '919-971-0000', 'Susan Franco', '704-532-2117', 
'Aetna', '1234 Aetna Blvd', 'Suite 602', 'Charlotte','NC', '28215', '704-555-1234', 'ChetumNHowe', '1950-05-10',0,0,'AB+','African American','Male','')
 ON DUPLICATE KEY UPDATE MID = MID;

INSERT INTO users(MID, password, role, sQuestion, sAnswer, isDependent) 
			VALUES (72, '30c952fab122c3f9759f02a6d95c3758b246b4fee239957b2d4fee46e26170c4', 'patient', 'what is your favorite color?', 'blue', 0)
 ON DUPLICATE KEY UPDATE MID = MID;
 /*password: pw*/

DELETE FROM personalhealthinformation WHERE PatientID = 72;

INSERT INTO declaredhcp(PatientID,HCPID) VALUE(72, 9900000022)
 ON DUPLICATE KEY UPDATE PatientID = PatientID;

 /* Brody represents Brittany */
INSERT INTO representatives (representerMID, representeeMID)
	VALUES	(72, 73);
	
	
/* Give Brittany Franco an Office Visit on Oct 30, 2015 */
INSERT INTO itrust.officevisits(ID, visitDate, appt_type, HCPID, notes, PatientID, HospitalId, IsERIncident, IsBilled)
	VALUES(147, '2015-10-30', 'Ophthalmology', 9900000022, 'Ophthalmology Appt', 73, 1, 0, 0);
	
/* Add Opthalmology Data to Brittany Franco's Office Visit */
INSERT INTO itrust.ovophthalmologydata(id, VisitId, ODAcuityNumerator, ODAcuityDenominator, OSAcuityNumerator, OSAcuityDenominator, ODSphere, OSSphere, ODCylinder, OSCylinder, ODAxis, OSAxis, ODAdd, OSAdd)
	VALUES(2, 147, 20, 18, 20, 18, .75, .5, 0, -.25, 0, 30, 1.0, 1.0);
	
/* Baby Chandler (Dependent) */
INSERT INTO users (MID, Password, Role, sQuestion, sAnswer, isDependent)
	VALUES (74, '30c952fab122c3f9759f02a6d95c3758b246b4fee239957b2d4fee46e26170c4', 'patient', '2+2?', '4', 1)
	ON DUPLICATE KEY UPDATE MID = MID;
	
INSERT INTO patients 	(MID, lastName, firstName, email, address1, address2,
						city, state, zip, phone, eName, ePhone,
						DateOfBirth, BloodType, Ethnicity, Gender, TopicalNotes)
	VALUES	(74, 'Chandler', 'Baby', 'gogogaga@gmail.com', '456 W Lane Rd', '',
			'Raleigh', 'NC', '27607', '555-666-7777', '', '',
			'2010-02-06', 'AB+', 'White', 'Female', 'Dependent of Logan Chandler')
	ON DUPLICATE KEY UPDATE MID = MID;
	
/* Logan Chandler (Representative) */
INSERT INTO patients 	(MID, lastName, firstName, email, address1, address2,
						city, state, zip, phone, eName, ePhone,
						DateOfBirth, BloodType, Ethnicity, Gender, TopicalNotes)
	VALUES	(75, 'Chandler', 'Logan', 'lkchandler@gmail.com', '456 W Lane Rd', '',
			'Raleigh', 'NC', '27607', '555-666-7777', '', '',
			'2010-02-06', 'AB+', 'White', 'Male', 'Representative of Baby Chandler')
	ON DUPLICATE KEY UPDATE MID = MID;

INSERT INTO users(MID, password, role, sQuestion, sAnswer, isDependent) 
			VALUES (75, '30c952fab122c3f9759f02a6d95c3758b246b4fee239957b2d4fee46e26170c4', 'patient', 'what is your favorite color?', 'blue', 0)
 ON DUPLICATE KEY UPDATE MID = MID;
 /*password: pw*/

 /* Logan represents Baby */
INSERT INTO representatives (representerMID, representeeMID)
	VALUES	(75, 74);
	
/* Give Baby Chandler an Office Visit on Oct 30, 2015 */
INSERT INTO itrust.officevisits(ID, visitDate, appt_type, HCPID, notes, PatientID, HospitalId, IsERIncident, IsBilled)
	VALUES(148, '2015-10-11', 'Ophthalmology', 9900000022, 'Ophthalmology Appt', 74, 1, 0, 0);
	
/* Add Opthalmology Data to Baby Chandler's Office Visit */
INSERT INTO itrust.ovophthalmologydata(id, VisitId, ODAcuityNumerator, ODAcuityDenominator, OSAcuityNumerator, OSAcuityDenominator, ODSphere, OSSphere, ODCylinder, OSCylinder, ODAxis, OSAxis, ODAdd, OSAdd)
	VALUES(3, 148, 20, 18, 20, 18, 0, .75, .5, -.25, 0, 30, 1.0, 1.0);
	
 