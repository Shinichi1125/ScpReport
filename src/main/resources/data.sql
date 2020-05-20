INSERT INTO report(report_id, title, threat_level, report_date, description, user_id, image) VALUES (NULL, 'Jelly Jiggler', 2, '2020-3-20 15:00:00', 'Looks like jelly, but human-shaped', 1, FILE_READ('C:/springimage/JellyJiggler.png')),
(NULL, 'Don Patch', 3, '2020-3-20 15:00:00', 'Unpredictable movements, changes in size', 1, FILE_READ('C:/springimage/DonPatch.jpg')),
(NULL, 'Ryuk', 5, '2020-5-5 15:30:00', 'Visible only to humans who touched his deathnote. Basically neutral / bystander-like attitude', 1, FILE_READ('C:/springimage/Ryuk.jpg'));

INSERT INTO user(user_id, user_name) VALUES (1, 'Shinichi');