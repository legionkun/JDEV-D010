package project.green.shop.security;

public class JwtResponse {
private String token;
private int id;
private String email;
private String name;

public JwtResponse() {}

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public JwtResponse(String token, int id, String email, String name) {
	super();
	this.token = token;
	this.id = id;
	this.email = email;
	this.name = name;
}


}
