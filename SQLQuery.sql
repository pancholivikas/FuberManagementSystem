CREATE DATABASE FuberManagementSystem;


use FuberManagementSystem

CREATE TABLE F_Customers(
   Name nvarchar(20),
   mobileNumber nvarchar(10),
   PRIMARY KEY( mobileNumber )
);

INSERT INTO F_Customers
VALUES ('Vikas Pancholi', '9782177780');

Create Table F_CarTypes(
	carTypeId nvarchar(10),
	carName nvarchar(20)
	PRIMARY KEY(carTypeId)
);

INSERT INTO F_CarTypes
VALUES ('NORMAL', 'NORMAL CARS'),
('PINK', 'PINK CARS');

Create Table F_Drivers(
	Name nvarchar(20),
	mobileNumber nvarchar(10),
	isBooked BIT NOT NULL,
	carTypeId nvarchar(10),
	lat DECIMAL( 18, 15 ) NOT NULL ,
	lng DECIMAL( 18, 15 ) NOT NULL ,
	PRIMARY KEY( mobileNumber ),
	FOREIGN KEY (carTypeId) REFERENCES F_CarTypes(carTypeId)
);

INSERT INTO F_Drivers
VALUES ('driver-A', '9998887755', 0, 'NORMAL', 13.011381811929526, 80.22096633911133),
	   ('driver-B', '9876543210', 0, 'NORMAL', 13.016566632727145, 80.2606201171875),
	   ('driver-C', '8976543210', 0, 'NORMAL', 12.97876513221484, 80.19401550292969),
	   ('driver-D', '7894561230', 0, 'PINK',   12.975419589563682, 80.22148132324219),
       ('driver-E', '9012345678', 0, 'NORMAL', 12.98170080165709, 80.25263786315918),
       ('driver-F', '9287456103', 0, 'NORMAL', 12.9807716794297, 80.25937557220459),
       ('driver-G', '9988776655', 0, 'PINK',  12.97517702882445, 80.25083541870117),
       ('driver-H', '8985848622', 0, 'NORMAL', 12.972918759885674, 80.25813102722168),
       ('driver-I','9000012345',0,'PINK', 12.963049046363762, 80.2452242660522);

Create Table F_RideDetails(
	rideId nvarchar(10),
	driverMobileNumber nvarchar(10),
	customerMobileNumber nvarchar(10),
	StartTime datetime,
	EndTime datetime,
	charges FLOAT,
	PRIMARY KEY(rideId),
	FOREIGN KEY (driverMobileNumber) REFERENCES F_Drivers(mobileNumber),
	FOREIGN KEY (customerMobileNumber) REFERENCES F_Customers(mobileNumber)
);
