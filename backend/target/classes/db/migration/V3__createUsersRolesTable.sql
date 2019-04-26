create table system_users_roles(
user_id bigint(20),
roles_id bigint(20),
primary key(user_id,roles_id)
);

ALTER TABLE system_users_roles
ADD CONSTRAINT userKey
FOREIGN KEY (user_id) REFERENCES system_users(id);

ALTER TABLE system_users_roles
ADD CONSTRAINT rolesKey
FOREIGN KEY (roles_id) REFERENCES roles(id);