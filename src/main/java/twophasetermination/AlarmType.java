package twophasetermination;

/**
 * Created by yuxiao on 4/23/18.
 */

public enum AlarmType {
    FAULT("fault"),
    RESUME("resume");

    private final String name;
    private AlarmType(String name){
        this.name=name;
    }

    public String toString(){

        return name;
    }
}