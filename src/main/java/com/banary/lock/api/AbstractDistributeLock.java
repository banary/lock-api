package com.banary.lock.api;

/**
 * 分布式锁抽象实现类
 *
 * @author Eden
 * @date 2017/8/11 下午6:00
 */
public abstract class AbstractDistributeLock implements DistributeLock {

    protected transient String lockName;
    private transient Thread exclusiveOwnerThread;

    protected String getLockName() {
        return lockName;
    }

    protected void setLockName(String lockName) {
        this.lockName = lockName;
    }

    protected Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }

    protected void setExclusiveOwnerThread(Thread exclusiveOwnerThread) {
        this.exclusiveOwnerThread = exclusiveOwnerThread;
    }
}
