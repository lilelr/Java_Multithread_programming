package guardsuspension;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yuxiao on 4/23/18.
 */
public class ConditionVarBlocker implements Blocker{

    private  Lock lock;
    private Condition condition;
    private boolean allowAccess2lock;

    public ConditionVarBlocker(Lock lock){

    }

    private ConditionVarBlocker(Lock lock, boolean allowAccess2Lock){
        this.lock = lock;


    }

    public ConditionVarBlocker() {
        this(false);
    }

    public ConditionVarBlocker(boolean allowAccess2Lock){
        this(new ReentrantLock(), allowAccess2Lock);
    }

    public Lock getLock(){
        if (allowAccess2lock){
            return this.lock;
        }
        throw new IllegalStateException("Access to the lock disallowed.");
    }

    public <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception {
        lock.lockInterruptibly();
        V result;
        try {
            final Predicate guard = guardedAction.guard;
            while (!guard.evaluate()) {
                System.out.println("waiting...");
                condition.await();
            }
            result = guardedAction.call();
            return result;
        } finally {
            lock.unlock();
        }
    }

    public void signalAfter(Callable<Boolean> stateOperation) throws Exception{
        lock.lockInterruptibly();
        try{
            if(stateOperation.call()){
                condition.signal();
            }
        }finally {
            lock.unlock();
        }
    }

    public void broadcastAfter(Callable<Boolean> stateOperation) throws Exception{
        lock.lockInterruptibly();
        try {
            if(stateOperation.call()){
                condition.signalAll();
            }
        }finally {
            lock.unlock();
        }
    }

    public void signal() throws InterruptedException{
        lock.lockInterruptibly();

        try {
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
