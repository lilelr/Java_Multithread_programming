package twophasetermination;

import guardsuspension.Blocker;
import guardsuspension.ConditionVarBlocker;
import guardsuspension.GuardedAction;
import guardsuspension.Predicate;

import java.util.Timer;

/**
 * Created by yuxiao on 4/23/18.
 */
public class AlarmAgent {

    private volatile boolean connectedToServer = false;

    private final Predicate agentConnected = new Predicate() {
        @Override
        public boolean evaluate() {
            return  connectedToServer;
        }
    };

    private final Blocker blocker = new ConditionVarBlocker();

    private final Timer heartbeatTimer = new Timer(true);


    public void sendAlarm(AlarmInfo alarm)throws Exception{
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected) {
            @Override
            public Void call() throws Exception {

                return null;
            }
        };

        blocker.callWithGuard(guardedAction);

    }
}
