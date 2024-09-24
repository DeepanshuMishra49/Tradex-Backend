package com.tradex.service;

import com.tradex.modal.Order;
import com.tradex.modal.User;
import com.tradex.modal.Wallet;

public interface WalletService {


    Wallet getUserWallet(User user) ;

    public Wallet addBalanceToWallet(Wallet wallet, Long money) ;

    public Wallet findWalletById(Long id) throws Exception;

    public Wallet walletToWalletTransfer(User sender,Wallet receiverWallet, Long amount) throws Exception;

    public Wallet payOrderPayment(Order order, User user) throws Exception;



}