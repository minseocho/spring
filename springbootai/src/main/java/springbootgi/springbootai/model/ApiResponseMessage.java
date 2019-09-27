package springbootgi.springbootai.model;

import lombok.Data;

@Data
public class ApiResponseMessage {

	// HttpStatus
    private String status;
    // Http Default Message
    private String message;
    private Object data;
    // Error Message to USER
    private String errorMessage;
    // Error Code
    private String errorCode;

    public ApiResponseMessage() {}

    public ApiResponseMessage(String status, String message, Object data, String errorCode, String errorMessage) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
