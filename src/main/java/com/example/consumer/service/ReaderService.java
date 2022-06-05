package com.example.consumer.service;

import com.example.consumer.dto.Message;

import java.io.IOException;
import java.util.List;

/**
 * Класс ReaderService
 */
public interface ReaderService {
	List<Message> readMessages(int count) throws IOException;
}