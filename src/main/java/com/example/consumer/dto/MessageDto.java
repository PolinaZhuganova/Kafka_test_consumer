package com.example.consumer.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Класс Message
 */
@Data
public class MessageDto implements Serializable {
	@ApiModelProperty(notes = "Текст получаемого сообщения")
	private String msgTxt;
}