Todo Management Requirements
Users: 	-Admin
		-User

1. Rest APIs
	-Add Todo         accessed by Admin
	-Get Todo         accessed by Admin, User
	-Get All Todo     accessed by Admin, User
	-Update Todo      accessed by Admin
	-Delete Todo      accessed by Admin
	-Complete Todo    accessed by Admin, User
	-In-complete Todo accessed by Admin, User

2. Register and Login Rest APIs
	-Create RegisterDto class
	-Create TodoApiException
	-Create AuthService interface and defines register() method
	-Create AuthServiceImpl class implements register() method
	-Create AuthController class @RestController
	-Exception handling in Register API

3. Secure Rest APIs using JWT Token	