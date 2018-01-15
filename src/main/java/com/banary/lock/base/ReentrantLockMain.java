package com.banary.lock.base;


import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 互斥锁
 */
public class ReentrantLockMain {

    private static final Lock lock = new ReentrantLock();

    /**
     *
     * tryLock
     */
    /**
     * 轮询锁，解决死锁问题，在休眠时间里加入固定部分和随机部分，避免发生活锁的可能性
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @param timeout
     * @param unit
     * @return
     */
    public boolean transferMoney(Account fromAccount, Account toAccount, BigDecimal amount, long timeout, TimeUnit unit){
        long fixdDelay = getFixedDelayComponentNanos(timeout, unit);
        long randMod = getRandomDelayModulusNanos(timeout, unit);
        long stopTime = System.nanoTime() + unit.toNanos(timeout);

        try{
            while (true){
                if(fromAccount.getLock().tryLock()){
                    try{

                        if(toAccount.getLock().tryLock()){
                            try {
                                if(fromAccount.getAmount().compareTo(amount)<0){
                                    throw new RuntimeException("钱不够啊");
                                }
                                fromAccount.debit(amount);
                                toAccount.cebit(amount);
                                return true;
                            }finally {
                                toAccount.getLock().unlock();
                            }
                        }

                    }finally {
                        fromAccount.getLock().unlock();
                    }
                }

                //休眠
                if(System.nanoTime() < stopTime){
                    return false;
                }

                TimeUnit.NANOSECONDS.sleep(fixdDelay + new Random().nextLong() % randMod);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return false;
    }

    public long getFixedDelayComponentNanos(long timeout, TimeUnit timeUnit){
        return 0L;
    }

    public long getRandomDelayModulusNanos(long timeout, TimeUnit timeUnit){
        return 0L;
    }

    /**
     * 定时锁
     * @param message
     * @param timeout
     * @param timeUnit
     * @return
     * @throws InterruptedException
     */
    public boolean trySendOnSharedLine(String message, long timeout, TimeUnit timeUnit) throws InterruptedException{
        long nanosToLock = timeUnit.toNanos(timeout);

        if(!lock.tryLock(nanosToLock, TimeUnit.NANOSECONDS)){
            try {
                return sendMSG(message);
            }finally {
                lock.unlock();
            }
        }
    }

    public boolean sendMSG(String msg){
        System.out.println("发送消息：" + msg);
        return true;
    }

    /**
     * 可中断锁
     * @return
     */
    public boolean sendOnSharedLine(String message) throws InterruptedException{
        lock.lockInterruptibly();
        try {
            return cancellableSendOnSharedLine(message);
        }finally {
            lock.unlock();
        }
    }

    private void cancellableSendOnSharedLine(String message) throws InterruptedException{

    }
}
