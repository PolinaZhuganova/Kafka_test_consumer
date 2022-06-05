package com.example.consumer.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Класс Message
 */
@Data
public class Message implements Serializable {
private String msgText;
}