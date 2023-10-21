package ru.bbnshp.response;

public class MassageResponse {

    private String message;

    public MassageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
