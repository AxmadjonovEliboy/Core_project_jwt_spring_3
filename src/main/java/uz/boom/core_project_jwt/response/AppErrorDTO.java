package uz.boom.core_project_jwt.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
//@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppErrorDTO {

    private Timestamp timestamp;
    private Integer status;
    private String code;
    private String message;
    private String path;

    //    @Builder
    public AppErrorDTO(String message, WebRequest webRequest, HttpStatus httpStatus) {
        this(message, ((ServletWebRequest) webRequest).getRequest().getRequestURI(), httpStatus);
    }

    public AppErrorDTO(String message, String path, HttpStatus httpStatus) {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.status = httpStatus.value();
        this.code = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
    }

    public AppErrorDTO(String error, HttpStatus status) {
        this.message = error;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.status = status.value();
        this.code = status.getReasonPhrase();

    }
}
