# ProductOrderSystem

#### The application is covered with JUnit tests
#### Framework: Spring Boot;
#### Libraries:Lombok,Junit,Mockito;
#### Migration:Liquibase;
#### DB:Postgres;

### MODEL DESCRIPTION:
	- An Order is made of N OrderLine and is related to a Customer, and has a date of submission
	- Each OrderLine is made of a Product and a quantity
	- A Product has a name, skuCode, unitPrice
	- A Customer has a registrationCode, fullName, email, telephone
### API:
	- Provide api for following services:
	- Create customer
	- Create product
	- Create order
	- Search all orders by date
	
