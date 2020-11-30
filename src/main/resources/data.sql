INSERT INTO users (email, first_name, last_name, password, username) VALUES ('admin@admin.com', 'admin', 'admin', '$2a$04$n6WIRDQlIByVFi.5rtQwEOTAzpzLPzIIG/O6quaxRKY2LlIHG8uty', 'admin');
INSERT INTO role (description, name) VALUES ('ROLE_ADMIN', 'ADMIN');
INSERT INTO role (description, name) VALUES ('ROLE_USER', 'USER');
INSERT INTO user_roles (user_id, role_id) VALUES ('1', '1'); 
INSERT INTO master_map (id, name_map, namecode, width) VALUES ('1', 'PaisesMercator', 'world_countries_mercator', '100%');
INSERT INTO master_map (id, name_map, namecode, width) VALUES ('2', 'España', 'es', '80%');
