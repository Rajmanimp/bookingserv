DROP TABLE IF EXISTS BOOKING_DATA;
 
CREATE TABLE BOOKING_DATA (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  date_of_birth DATETIME NOT NULL,
  checkin_date DATETIME NOT NULL,
  checkout_date DATETIME NOT NULL,
  total_price INT NOT NULL,
  deposit INT NOT NULL,
  line1 VARCHAR(250) NOT NULL,
  line2 VARCHAR(250),
  city VARCHAR(50) NOT NULL,
  state VARCHAR(50) NOT NULL,
  zipcode INT NOT NULL,
  request_time DATETIME NOT NULL 
);
