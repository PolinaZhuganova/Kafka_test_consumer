package com.example.consumer.controller;

import com.example.consumer.dto.*;
import com.example.consumer.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Класс ReaderController
 */
@RestController
@RequiredArgsConstructor
public class ReaderController {
	private final ReaderService readerService;

	@GetMapping("/read/data")
	public ResponseMessage read(@RequestParam("count") int count) {
		try {
			long startTime = new Date().getTime();
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setTime_get(startTime);
			List<Message> messages = readerService.readMessages(count);
			responseMessage.setTime_send(new Date().getTime());
			responseMessage.setTime_work(responseMessage.getTime_send() - responseMessage.getTime_get());
			responseMessage.setList(messages);

			return responseMessage;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}