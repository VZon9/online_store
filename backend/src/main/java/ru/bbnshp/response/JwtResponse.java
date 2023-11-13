package ru.bbnshp.response;

public class JwtResponse {
    private String token;
    private final String type = "Barer";
    private Integer id;

    private String login;

    private String email;


    public JwtResponse(String token, Integer id, String login, String email){
        this.token = token;
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
