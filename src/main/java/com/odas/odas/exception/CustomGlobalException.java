package com.odas.odas.exception;

import com.odas.odas.payload.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class CustomGlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException re) {
        String message = re.getMessage();
        ApiResponse apiRes = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiRes, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DocumentUploadException.class)
    public ResponseEntity<ApiResponse> documentUploadException(DocumentUploadException de) {
        String message = de.getMessage();
        ApiResponse apiRes = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiRes, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiResponse> maxSizeExceededException(MaxUploadSizeExceededException me) {
        String size = me.getMessage().substring(198, 205);
        long byteSize = Long.valueOf(size);
        long sizeInMb = Math.floorDiv(byteSize, 1048576);
        String message = "Image with size " + String.valueOf(sizeInMb)
                + "MB exceeded maximum upload limit. Please upload within 2MB size.";
        ApiResponse apiRes = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiRes,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
