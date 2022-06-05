package com.example.consumer.service;

import com.example.consumer.dto.MessageDto;

import java.io.IOException;
import java.util.List;

/**
 * Класс ReaderService
 */
public interface ReaderService {
	List<MessageDto> readMessages(int count) throws IOException;
}