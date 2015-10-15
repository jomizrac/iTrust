INSERT INTO personnel
(MID,
AMID,
lastName,
firstName,
address1,
address2,
city,
state,
zip,
phone, 
specialty
)
VALUES (
8000000009,
9000000000,
'LastUAP',
'FirstUAP',
'100 Ave',
'',
'Raleigh',
'NC',
'27607',
'111-111-1111',
'General'
);

INSERT INTO users(MID, password, role, sQuestion, sAnswer) 
VALUES (8000000009, 'b43b19d6d9687c5e7786bfa06e5362982acc0ca7e61dc27304dd5bec8410ee2c', 'uap', 'opposite of yin', 'yang');
--password: uappass1