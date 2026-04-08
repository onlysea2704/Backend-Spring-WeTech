package com.wetech.backend_spring_wetech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response body for PDF upload endpoint.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PdfUploadResponse {
    /**
     * URL of the uploaded PDF file from Cloudinary.
     */
    private String url;
    
    /**
     * File name of the uploaded PDF.
     */
    private String fileName;
}

