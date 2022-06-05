package com.example.consumer.controller;

import com.example.consumer.dto.*;
import com.example.consumer.service.ReaderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Класс ReaderController
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ReaderController {
	private final ReaderService readerService;

	@ApiOperation(value = "Получение сообщения из очереди", response = ResponseMessage.class)
	@GetMapping("/read/data")
	public ResponseMessage read(@RequestParam("count") int count) {
		try {
			long startTime = new Date().getTime();
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setTime_get(startTime);
			List<MessageDto> messages = readerService.readMessages(count);
			responseMessage.setTime_send(new Date().getTime());
			responseMessage.setTime_work(responseMessage.getTime_send() - responseMessage.getTime_get());
			responseMessage.setList(messages);
			log.info("message has been read: " + responseMessage);
			return responseMessage;
		} catch (Exception e) {
			log.debug("message reading failed");
			e.printStackTrace();
		}
		return null;
	}
}