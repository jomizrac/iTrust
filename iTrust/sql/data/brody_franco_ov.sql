INSERT INTO itrust.officevisits(id, visitDate, appt_type, HCPID, notes, PatientID, HospitalID, isERIncident, IsBilled)
VALUES('963', '2015-10-14', 'Ophthalmology', '9900000022', '', '72', 1, 0, 0);

INSERT INTO itrust.ovophthalmologydata(id, VisitId, acuityNumerator, acuityDenominator, ODSphere, OSSphere, ODCylinder, OSCylinder, ODAxis, ODAdd, OSAdd)
VALUES(47, 963, 20, 40, -3.25, -3.00, -1.25, 0, 50, 1.00, 1.0);