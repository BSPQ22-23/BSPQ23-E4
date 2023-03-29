CREATE TABLE IF NOT EXISTS USER (
  LOGIN_USER VARCHAR(50) NOT NULL,
  PASSWORD_USER VARCHAR(50) NOT NULL,
  PURSE FLOAT,
  TYPE_USER INT NOT NULL,
  CONSTRAINT CHK_TYPE CHECK(TYPE_USER IN (0,1,2)),
  PRIMARY KEY (LOGIN_USER)
);
