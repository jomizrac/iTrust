INSERT INTO patients
(MID, 
lastName, 
firstName,
email,
address1,
address2,
city,
state,
zip,
phone,
eName,
ePhone,
iCName,
iCAddress1,
iCAddress2,
iCCity, 
ICState,
iCZip,
iCPhone,
iCID,
dateofbirth,
mothermid,
fathermid,
bloodtype,
ethnicity,
gender, 
topicalnotes)
VALUES
(15,
'Chandler', 
'Freya', 
'freya_chandler@gmail.com', 
'1247 Lane Dr', 
'Apt 106', 
'Raleigh', 
'NC', 
'27606-1234', 
'919-971-0000', 
'Bob Chandler', 
'704-532-2117', 
'Aetna', 
'1234 Aetna Blvd', 
'Suite 602', 
'Charlotte',
'NC', 
'28215', 
'704-555-1234', 
'ChetumNHowe', 
'1950-05-10',
0,
0,
'AB+',
'African American',
'Female',
'')
 ON DUPLICATE KEY UPDATE MID = MID;

INSERT INTO users(MID, password, role, sQuestion, sAnswer) 
			VALUES (15, '30c952fab122c3f9759f02a6d95c3758b246b4fee239957b2d4fee46e26170c4', 'patient', 'what is your favorite color?', 'blue')
 ON DUPLICATE KEY UPDATE MID = MID;
 /*password: pw*/

DELETE FROM personalhealthinformation WHERE PatientID = 15;
INSERT INTO personalhealthinformation
(PatientID,OfficeVisitID,Height,Weight,Smoker,SmokingStatus,BloodPressureN,BloodPressureD,CholesterolHDL,CholesterolLDL,CholesterolTri,HCPID,AsOfDate,OfficeVisitDate,BMI)
VALUES ( 15,  717, 72,   180,   0, 9,      100,          100,           40,             100,         100,          9900000022, '2007-06-07 20:33:58','2015-10-15',24.41)
on duplicate key update OfficeVisitID = OfficeVisitID;


INSERT INTO officevisits(id,visitDate,HCPID,notes,HospitalID,PatientID)
VALUES (717,'2015-10-12',9900000022,'Yet another ophthalmology office visit.','9',15)
 ON DUPLICATE KEY UPDATE id = id;
 

INSERT INTO ovdiagnosis(ICDCode, VisitID) VALUES 
(365.0, 117)
 ON DUPLICATE KEY UPDATE ICDCode = VALUES(ICDCode), VisitID = VALUES(VisitID);

INSERT INTO declaredhcp(PatientID,HCPID) VALUE(15, 9900000022)
 ON DUPLICATE KEY UPDATE PatientID = PatientID;
 
 /* Add Opthalmology Data to Office Visit */
INSERT INTO itrust.ovophthalmologydata(id, VisitId, ODAcuityNumerator, ODAcuityDenominator, OSAcuityNumerator, OSAcuityDenominator, ODSphere, OSSphere, ODCylinder, OSCylinder, ODAxis, OSAxis, ODAdd, OSAdd)
	VALUES(7, 717, 20, 10, 20, 10, 1.75, 1.75, 0, 0, 0, 0, 1.25, 1.25);
	
INSERT INTO ovprocedure(id, visitid, cptcode)
VALUES
	(2000111, 717, "90645")
