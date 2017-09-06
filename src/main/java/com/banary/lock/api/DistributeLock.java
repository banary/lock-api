package com.banary.lock.api;

/**
 * 分布式锁接口
 *
 * @author Eden
 * @date 2017/8/11 下午5:58
 */
public interface DistributeLock {

    void lock();

    void unlock();

    boolean tryLock();
}
