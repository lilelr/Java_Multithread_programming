package twophasetermination;

import java.lang.ref.WeakReference;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yuxiao on 4/23/18.
 */
public class TerminationToken {

    protected volatile boolean toShutdown = false;

    public final AtomicInteger reservations = new AtomicInteger(0);

    private ConcurrentLinkedDeque<WeakReference<Terminatable>> coordinatedThreads;

    public TerminationToken(){

        coordinatedThreads = new ConcurrentLinkedDeque<WeakReference<Terminatable>>();
    }

    public boolean isToShutdown(){
        return  toShutdown;
    }

    protected void setToShutdown(boolean toShutdown){
        this.toShutdown = true;
    }

    protected void register(Terminatable thread){
        coordinatedThreads.add(new WeakReference<Terminatable>(thread));
    }

    protected void notifyThreadTermiation(Terminatable thread){
        WeakReference<Terminatable> wrThread;
        Terminatable otherThread;
        while (null != (wrThread = coordinatedThreads.poll())){
            otherThread = wrThread.get();
            if(null != otherThread && otherThread!=thread){
                otherThread.terminate();
            }
        }
    }
}
