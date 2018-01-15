package com.banary.lock.base;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private final Lock lock = new ReentrantLock();

    private BigDecimal amount;
    private Long id;
    private Long userId;


    public void debit(BigDecimal amount){
        System.out.println("扣减多少钱 ：" + amount);
    }

    public void cebit(BigDecimal amount){
        System.out.println("增加多少钱：" + amount);
    }

    public Lock getLock() {
        return lock;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
