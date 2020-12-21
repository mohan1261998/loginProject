
CREATE TABLE User (
 id bigint UNSIGNED AUTO_INCREMENT PRIMARY KEY,
 fullname varchar(255),
 isAdmin boolean not null,
 password varchar(255),
 username varchar(255)
);

