BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


DROP TABLE IF EXISTS artist_dealer;
DROP TABLE IF EXISTS art_pieces;
DROP TABLE IF EXISTS dealer;
DROP TABLE IF EXISTS artist;

CREATE TABLE artist(
        artist_id serial primary key,
        artist_name varchar(56) NOT NULL
        
);

CREATE TABLE dealer(
        dealer_id serial primary key,
        user_id int NOT NULL,
        
        CONSTRAINT fk_dealer_user_id FOREIGN KEY (user_id) REFERENCES users (user_id));

CREATE TABLE management(
        manager_id serial primary key,
        user_id int NOT NULL,
        
        CONSTRAINT fk_management_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE artist_dealer(
        artist_id int NOT NULL,
        dealer_id int NOT NULL,
        
        PRIMARY KEY (artist_id, dealer_id),
        
        CONSTRAINT fk_artist_dealer_artist_id FOREIGN KEY (artist_id) REFERENCES artist (artist_id),
        CONSTRAINT fk_artist_dealer_dealer_id FOREIGN KEY (dealer_id) REFERENCES dealer (dealer_id)
        
);

CREATE TABLE art_pieces (
        art_id serial primary key
);


COMMIT TRANSACTION;
