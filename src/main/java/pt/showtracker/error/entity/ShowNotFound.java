package pt.showtracker.error.entity;

/**
 * Bean for show not found response.
 */
public class ShowNotFound {

    private String message;
    private int error;

    public ShowNotFound(String message, int error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
