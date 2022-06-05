package com.example.consumer.dto;

import lombok.Data;

import java.util.List;

/**
 * Класс ResponseMessage
 */
@Data
public class ResponseMessage {
	Long time_get;
	Long time_send;
	Long time_work;
	List<Message> list;

}