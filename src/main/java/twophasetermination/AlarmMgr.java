package twophasetermination;

import jdk.nashorn.internal.runtime.Debug;

/**
 * Created by yuxiao on 4/23/18.
 */
public class AlarmMgr {

    private static final AlarmMgr INSTANCE = new AlarmMgr();

    private volatile  boolean shutdownRequested =false;

    private AlarmMgr(){

    }

    public static AlarmMgr getInstance(){
        return INSTANCE;
    }

    private  AlarmSendingThread alarmSendingThread;

    public int sendAlarm(AlarmType type, String id, String extraInfo){
        String debug_info = "trigger alarm "+ type + "," + id + "," + extraInfo;
        int duplicateSummissionCount = 0;
        try {
            AlarmInfo alarmInfo = new AlarmInfo(id, type);
            alarmInfo.setExtraInfo(extraInfo);
            duplicateSummissionCount = alarmSendingThread.
        }
    }

}
