CREATE TABLE alcoholic(id SERIAL NOT NULL, username TEXT NOT NULL, photo TEXT, CONSTRAINT PK_USER PRIMARY KEY (id));

CREATE TABLE alcohol_item(id SERIAL NOT NULL, name TEXT NOT NULL, degree REAL NOT NULL, brand TEXT NOT NULL, category TEXT, CONSTRAINT PK_ALCOHOL_ITEM PRIMARY KEY (id));

CREATE TABLE act(id SERIAL NOT NULL, alcoholic INT NOT NULL, item INT NOT NULL, ts TIMESTAMP NOT NULL, photo TEXT NOT NULL, 
	latitude double precision, longitude double precision, 
	CONSTRAINT PK_ACT PRIMARY KEY(id), CONSTRAINT FK_ACT_ALCOHOLIC FOREIGN KEY (alcoholic) REFERENCES alcoholic,  
	CONSTRAINT FK_ACT_ITEM FOREIGN KEY (item) REFERENCES alcohol_item);