INSERT INTO "F_Applicant" (
  "first_name", "middle_name", "last_name", "email", "phone", "WeChat", "LinkedIn", 
  "address_street", "address_suite_number", "address_city", "address_postal_code", 
  "address_province", "address_country", "created_by", "modified_by", "created_at", "modified_at") 
VALUES
('Beverly', 'R', 'Bennett', 'BeverlyRBennett@teleworm.us', '626-839-9632', 'BevB_CA', 'BeverlyB_USA', 
  '656 Providence Lane', 'NA', 'La Puente', '91744', 'CA', 'USA', 'Admin', 'Admin', '2023-01-01', '2023-01-02'),
('Tara', 'W', 'Andrews', 'TaraWAndrews@jourrapide.com', '704-656-5614', 'Tara_NC', 'Andrews_USA', 
  '888 Snyder Avenue', 'NA', 'Charlotte', '28202', 'NC', 'USA', 'Admin', 'Admin', '2023-01-03', '2023-01-04'),
('Иван', '', 'Зайцев', 'izabella87@list.ru', '8-800-748-4128', 'Зайцев_wechat', 'Зайцев_linkedin',
  '12 спуск Ломоносова', 'NA', 'город Серпухов', '240206', 'ULY', 'Russia', 'Admin', 'Admin', '2023-01-05', '2023-01-06'),
('Henry', 'K', 'Lawson', 'HenryKLawson@rhyta.com', '615-989-2170', 'Lawson_TN', 'HenryK_USA',
  '3685 Cottonwood Lane', 'NA', 'Nashville', '37203', 'TN', 'USA', 'Admin', 'Admin', '2023-01-07', '2023-01-08'),
('Xiu Juan', '', 'Tan', 'XiuJuanTan@jourrapide.com', '306-987-4761', 'XiuJuan_Sweden', 'Tan_Sweden', 
  'Bursiljum 57', 'NA', 'LÖVÅNGER', '93010', '', 'Sweden', 'Admin', 'Admin', '2023-02-01', '2023-02-02'),
('沖原', '', '智子', 'Whoem1997@armyspy.com', '0913-8732592', 'Whoem1997_Japan', 'Whoem1997_JPN', 
  '廣川町宇野', '002', '坂本市', '2045800', '滋贺县', 'Japan', 'Admin', 'Admin', '2023-02-03', '2023-02-04'),
('Ranger', '', 'Deserres', 'RangerDeserres@rhyta.com', '66 66 10', 'Ranger_Greenland', 'Deserres_GL',
  'Gl. Sygehusvej 81', '008', 'Narsarsuaq', '3923', 'NA', 'Greenland', 'Admin', 'Admin', '2023-02-05', '2023-02-06'),
('Flora', '', 'Gavrilova', 'FloraGavrilova@armyspy.com', '05.22.23.45.25', 'Flora_France', 'Gavrilova_FR',
  '89, Faubourg Saint Honoré', 'NA', 'PAU', '64000', 'NA', 'France', 'Admin', 'Admin', '2023-02-07', '2023-02-08');

INSERT INTO "F_Skill" ("category", "name") 
VALUES
('Programming', 'Python'),
('Programming', 'JavaScript'),
('Programming', 'Java'),
('Web Development', 'React.js'),
('Web Development', 'Angular.js'),
('Web Development', 'HTML/CSS'),
('Database Management', 'SQL'),
('Database Management', 'NoSQL'),
('Cloud Computing', 'AWS'),
('Cloud Computing', 'Azure');

INSERT INTO "F_Applicant_Skill" ("applicant_id", "skill_id") 
VALUES
(1, 3),
(1, 5),
(1, 10),
(2, 6),
(2, 4),
(2, 7),
(2, 1),
(3, 2),
(3, 5),
(3, 7),
(3, 10),
(3, 4),
(3, 9),
(4, 9),
(4, 2),
(4, 1),
(4, 7),
(5, 6),
(5, 3),
(5, 1),
(5, 10),
(5, 4),
(5, 2),
(5, 7),
(6, 1),
(6, 4),
(7, 2),
(7, 7),
(7, 5),
(7, 10),
(7, 3),
(7, 9),
(8, 3),
(8, 10),
(8, 5),
(8, 4),
(8, 7),
(8, 9),
(8, 1);

INSERT INTO "F_Application" (
  "applicant_id", "role", "status", "created_by", "modified_by", "created_at", "modified_at") 
VALUES
(1, 'Web Developer', 'Applied', 'Admin', 'Admin', '2023-01-01', '2023-01-02'),
(2, 'Software Engineer', 'Interviewed', 'Admin', 'Admin', '2023-01-03', '2023-01-04'),
(3, 'Data Analyst', 'Hired', 'Admin', 'Admin', '2023-01-05', '2023-01-06'),
(4, 'Cloud Architect', 'Rejected', 'Admin', 'Admin', '2023-01-07', '2023-01-08'),
(5, 'Front-end Developer', 'Applied', 'Admin', 'Admin', '2023-02-01', '2023-02-02'),
(5, 'Back-end Developer', 'Interviewed', 'Admin', 'Admin', '2023-02-03', '2023-02-04'),
(6, 'Database Manager', 'Hired', 'Admin', 'Admin', '2023-02-05', '2023-02-06'),
(6, 'Data Scientist', 'Applied', 'Admin', 'Admin', '2023-02-07', '2023-02-08'),
(7, 'Software Tester', 'Rejected', 'Admin', 'Admin', '2023-02-09', '2023-02-10'),
(7, 'DevOps Engineer', 'Hired', 'Admin', 'Admin', '2023-02-11', '2023-02-12'),
(7, 'UI/UX Designer', 'Interviewed', 'Admin', 'Admin', '2023-02-13', '2023-02-14'),
(8, 'Full-stack Developer', 'Applied', 'Admin', 'Admin', '2023-02-15', '2023-02-16'),
(8, 'Web Designer', 'Hired', 'Admin', 'Admin', '2023-02-17', '2023-02-18'),
(8, 'Project Manager', 'Interviewed', 'Admin', 'Admin', '2023-02-19', '2023-02-20');

INSERT INTO "F_Document" (
  "application_id", "content", "created_by", "modified_by", "created_at", "modified_at") 
VALUES
-- 2 Applicants have 1 document
(1, E'\\000'::bytea, 'Beverly Bennett', 'Admin', '2023-01-01', '2023-01-02'),
(2, E'\\000'::bytea, 'Tara Andrews', 'Admin', '2023-01-03', '2023-01-04'),

-- 2 Applicants have 2 documents
(3, E'\\000'::bytea, 'Gerald Driggers', 'Admin', '2023-01-05', '2023-01-06'),
(3, E'\\000'::bytea, 'Gerald Driggers', 'Admin', '2023-01-07', '2023-01-08'),
(4, E'\\000'::bytea, 'Henry Lawson', 'Admin', '2023-01-09', '2023-01-10'),
(4, E'\\000'::bytea, 'Henry Lawson', 'Admin', '2023-01-11', '2023-01-12'),

-- 2 Applicants have 3 documents
(5, E'\\000'::bytea, 'Xiu Juan Tan', 'Admin', '2023-02-01', '2023-02-02'),
(5, E'\\000'::bytea, 'Xiu Juan Tan', 'Admin', '2023-02-03', '2023-02-04'),
(5, E'\\000'::bytea, 'Xiu Juan Tan', 'Admin', '2023-02-05', '2023-02-06'),
(6, E'\\000'::bytea, '沖原 智子', 'Admin', '2023-02-07', '2023-02-08'),
(6, E'\\000'::bytea, '沖原 智子', 'Admin', '2023-02-09', '2023-02-10'),
(6, E'\\000'::bytea, '沖原 智子', 'Admin', '2023-02-11', '2023-02-12'),

-- 2 Applicants have 4 documents
(7, E'\\000'::bytea, 'Ranger Deserres', 'Admin', '2023-02-13', '2023-02-14'),
(7, E'\\000'::bytea, 'Ranger Deserres', 'Admin', '2023-02-15', '2023-02-16'),
(7, E'\\000'::bytea, 'Ranger Deserres', 'Admin', '2023-02-17', '2023-02-18'),
(7, E'\\000'::bytea, 'Ranger Deserres', 'Admin', '2023-02-19', '2023-02-20'),
(8, E'\\000'::bytea, 'Flora Gavrilova', 'Admin', '2023-02-21', '2023-02-22'),
(8, E'\\000'::bytea, 'Flora Gavrilova', 'Admin', '2023-02-23', '2023-02-24'),
(8, E'\\000'::bytea, 'Flora Gavrilova', 'Admin', '2023-02-25', '2023-02-26'),
(8, E'\\000'::bytea, 'Flora Gavrilova', 'Admin', '2023-02-27', '2023-02-28');

