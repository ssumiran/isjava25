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

2. Register and Global Exception Handler Rest API
	-Create RegisterDto class
	-Create TodoApiException
	-Create AuthService interface and defines register() method
	-Create AuthServiceImpl class implements register() method
	-Create AuthController class @RestController
	-Exception handling in Register API

3. Login Rest API
	-Create LoginDto class
	-In AuthService interface and defines login() method
	-In AuthServiceImpl class implements login() method
	-In AuthController class @PostMapping login() method

4. Secure Rest APIs using JWT Token	
	-Add JWT related dependencies
	-Create JwtAuthenticationEntryPoint class
	-Add JWT properties into application.properties file
	-Create JwtTokenProvider class
	-Create JwtAuthenticationFilter class
	-Create JwtAuthResponseDto class
	-Configure JWT in Spring Security
	-Change Login API to use return JWT token

