package com.example.rest.api.controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/api")
public class TestController {

	@GetMapping("/message")
	public ResponseEntity<String> getMessage() throws InterruptedException {

		return ResponseEntity.ok().body("Hello Everyone!!");
	}

	@GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImage() throws IOException {
		InputStream in = getClass().getResourceAsStream("/static/image.png");
		return IOUtils.toByteArray(in);
	}

	@GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public @ResponseBody byte[] getPdf() throws IOException {
		InputStream in = getClass().getResourceAsStream("/static/sample.pdf");
		return IOUtils.toByteArray(in);
	}

	@GetMapping(value = "/pdfStream", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<StreamingResponseBody> getPdfStream() throws IOException {

		StreamingResponseBody stream = out -> {
			try (InputStream in = getClass().getResourceAsStream("/static/sample.pdf")) {
				out.write(IOUtils.toByteArray(in));
			}
		};

		return new ResponseEntity<StreamingResponseBody>(stream, HttpStatus.OK);

	}

}
