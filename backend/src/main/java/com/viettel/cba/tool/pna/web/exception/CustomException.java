/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.cba.tool.pna.web.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final String message;
  private final HttpStatus httpStatus;

  public CustomException(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

}
