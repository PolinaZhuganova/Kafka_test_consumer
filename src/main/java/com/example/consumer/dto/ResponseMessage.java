package com.example.consumer.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Класс ResponseMessage
 */
@Data
public class ResponseMessage {
	@ApiModelProperty(notes = "Время в миллисекундах получения запроса API")
	Long time_get;
	@ApiModelProperty(notes = "Время в миллисекундах отправки ответа на API")
	Long time_send;
	@ApiModelProperty(notes = "Время выполнения")
	Long time_work;
	@ApiModelProperty(notes = "Последние count записей из топика Data_Delivery")
	List<MessageDto> list;

}