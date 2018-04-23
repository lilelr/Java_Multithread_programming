package guardsuspension;

import java.util.concurrent.Callable;

/**
 * Created by yuxiao on 4/23/18.
 */
public abstract class GuardedAction<V> implements Callable<V> {
    protected  Predicate guard;

    public GuardedAction(Predicate guard){
        this.guard = guard;
    }
}
