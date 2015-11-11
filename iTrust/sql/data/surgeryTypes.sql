INSERT INTO surgeryTypes (SurgeryID, SurgeryName)
	VALUES 	(1, 'Cataract Surgery'), 
			(2, 'Laser Surgery'),
			(3, 'Refractive Surgery')
			ON DUPLICATE KEY UPDATE SurgeryID = SurgeryID;
			