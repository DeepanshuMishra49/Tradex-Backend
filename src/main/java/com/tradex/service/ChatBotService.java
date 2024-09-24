package com.tradex.service;


import com.tradex.modal.CoinDTO;
import com.tradex.response.ApiResponse;

public interface ChatBotService {
    ApiResponse getCoinDetails(String coinName);

    CoinDTO getCoinByName(String coinName);

    String simpleChat(String prompt);
}
