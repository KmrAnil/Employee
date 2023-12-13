package com.cic.employee.dto;

import lombok.Data;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

@Data
public class Post {
  private Integer id;

  private String title;

  private String body;

  private Integer userId;
}
