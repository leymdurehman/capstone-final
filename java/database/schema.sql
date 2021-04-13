BEGIN TRANSACTION;


DROP TABLE IF EXISTS artist_dealer;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS art_pieces;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS dealer;
DROP TABLE IF EXISTS management;
DROP TABLE IF EXISTS artist;

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
	email varchar(250) NOT NULL,
	
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role, email) VALUES ('customer','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'teamalphacohort13@gmail.com');
INSERT INTO users (username,password_hash,role, email) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'teamalphacohort13@gmail.com');
INSERT INTO users (username,password_hash,role, email) VALUES ('artist','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ARTIST', 'teamalphacohort13@gmail.com');
INSERT INTO users (username,password_hash,role, email) VALUES ('dealer','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DEALER', 'teamalphacohort13@gmail.com');
INSERT INTO users (username,password_hash,role, email) VALUES ('seller','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_SELLER', 'seller@email.com');
INSERT INTO users (username,password_hash,role, email) VALUES ('sellerArtist','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_SELLER', 'sellerArtist@email.com');


CREATE TABLE artist(
        artist_id serial primary key,
        user_id int,
        artist_name varchar(56) NOT NULL,
        is_seller boolean default false 
        
);

CREATE TABLE dealer(
        dealer_id serial primary key,
        user_id int NOT NULL,
        
        CONSTRAINT fk_dealer_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE management(
        manager_id serial primary key,
        user_id int NOT NULL,
        
        CONSTRAINT fk_management_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE customer(
        customer_id serial primary key,
        user_id int NOT NULL,
        
        CONSTRAINT fk_customer_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);


CREATE TABLE artist_dealer(
        artist_id int NOT NULL,
        dealer_id int NOT NULL,
        
        PRIMARY KEY (artist_id, dealer_id),
        
        CONSTRAINT fk_artist_dealer_artist_id FOREIGN KEY (artist_id) REFERENCES artist (artist_id),
        CONSTRAINT fk_artist_dealer_dealer_id FOREIGN KEY (dealer_id) REFERENCES dealer (dealer_id)
        
);

CREATE TABLE art_pieces (
        art_id serial primary key,
        title varchar(250) not null,
        date_created date not null,
        price numeric not null,
        img_file_name varchar(250) not null,
        is_sold boolean default false,
        artist_id int not null,
        dealer_id int,
        override_fee numeric,
        override_commission numeric,
        has_override boolean default false,
        is_available boolean default false,
    
        
        CONSTRAINT fk_art_pieces_artist_id FOREIGN KEY (artist_id) REFERENCES artist (artist_id),
        CONSTRAINT fk_art_pieces_dealer_id FOREIGN KEY (dealer_id) REFERENCES dealer (dealer_id)
);

CREATE TABLE transactions (
        order_id serial primary key,
        customer_id int not null,
        art_id int not null,
        date_of_sale date not null,
        fee numeric not null,
        commission numeric not null,
        total_price numeric not null,
        
        CONSTRAINT fk_transactions_customer_id FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
        CONSTRAINT fk_transactions_art_id FOREIGN KEY (art_id) REFERENCES art_pieces (art_id)
);




DROP TABLE IF EXISTS fees;

CREATE TABLE fees (
        fee_id serial primary key,
        fee numeric default 0.0,
        commission numeric default 0.0

);

INSERT INTO fees (fee_id) VALUES (DEFAULT);

--DUMMY DATA

INSERT INTO customer (customer_id, user_id) VALUES (DEFAULT, 1);
INSERT INTO management (manager_id, user_id) VALUES (DEFAULT, 2);
INSERT INTO artist (artist_id, user_id, artist_name, is_seller) VALUES (DEFAULT, 3, 'artist', false );
INSERT INTO dealer (dealer_id, user_id) VALUES (DEFAULT, 4);
INSERT INTO artist_dealer (artist_id, dealer_id) VALUES (1, 1);


INSERT INTO artist (artist_id, artist_name) VALUES (DEFAULT, 'DeadArtist');
INSERT INTO artist (artist_id, user_id, artist_name, is_seller) VALUES (DEFAULT, 6, 'sellerArtist', true );


--INSERT INTO art_pieces (art_id, title, date_created, price, img_file_name, artist_id, dealer_id) 
--VALUES (DEFAULT, 'Test Art', '2020-05-02', 999.99, 'picture.jpeg', 1, 1);

--INSERT INTO art_pieces (art_id, title, date_created, price, img_file_name, artist_id) 
--VALUES (DEFAULT, 'More Art', '2019-05-02', 500.01, 'art.png', 3);
--UPDATE art_pieces SET  is_available = true WHERE art_id = 6;
--UPDATE art_pieces SET  is_sold = false WHERE art_id = 1;

--Set the Piechart for 3
--UPDATE transactions SET date_of_sale = '2021-01-01' WHERE order_id = 1;
--UPDATE transactions SET date_of_sale = '2021-03-14' WHERE order_id = 2;
--UPDATE transactions SET date_of_sale = '2021-04-06' WHERE order_id = 3; 


COMMIT TRANSACTION;

