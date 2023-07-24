package ca.fly.mtm.admin.exception;

import ca.fly.mtm.admin.model.ApplicationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)

    // handle when the large file was uploaded
    public ResponseEntity<ApplicationResult> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        ApplicationResult result = new ApplicationResult();
        result.setStatus("error");
        result.setMsg("One or more files are too large!");
        return ResponseEntity.ok(result);
    }
}
