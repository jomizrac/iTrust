INSERT INTO icdcodes(Code, Description, Chronic, URL) VALUES
('250.10', 'Diabetes with ketoacidosis', 'yes', ''),
('250.30', 'Diabetes with other coma', 'yes', ''),
('487.00', 'Influenza', 'no', ''),
('79.10', 'Echovirus', 'no', ''),
('84.50', 'Malaria', 'no', ''),
('79.30', 'Coxsackie', 'yes', ''),
('11.40', 'Tuberculosis of the lung', 'no', ''),
('15.00', 'Tuberculosis of vertebral column', 'no', ''),
('42.00', 'Human Immunodeficiency Virus', 'yes', ''),
('70.10', 'Viral hepatitis A, infectious', 'yes', ''),
('250.00', 'Acute Lycanthropy', 'yes', ''),
('715.09', 'Osteoarthrosis, generalized, multiple sites', 'yes', ''),
('72.00', 'Mumps', 'no', ''),
('366.0', 'Cataracts', 'no', ''),
('362.5', 'Age-Related Macular Degeneration', 'no', ''),
('368.0', 'Amblyopia', 'no', ''),
('365.0', 'Glaucoma', 'yes', '')
ON DUPLICATE KEY UPDATE Code = Code;
