INSERT INTO serious_game (title, description, slogan, completion_time) VALUES ('Serious Game 1', 'Description du serious game 1', 'Slogan 1', '1h');

INSERT INTO user_group(name) VALUES ('Groupe 1');

INSERT INTO appuser(firstname, lastname, appusername, email, pwd, role, user_group_id, serious_game_id, created_at) VALUES ('melchior', 'delescluse','mdelescluse' ,'spokay@spokay.fr', '$2y$10$DDJujaM/af2WAGGiiPP0QeqHKboy8Y2uoqsU/fvk1MJcdIBpp6vlO', 'ADMIN', 1, 1, NOW());