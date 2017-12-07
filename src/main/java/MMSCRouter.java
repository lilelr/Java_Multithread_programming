import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuxiao on 12/7/17.
 */
public class MMSCRouter {

    private static volatile MMSCRouter instance = new MMSCRouter();

    private final Map<String, MMSInfo> routeMap;

    public MMSCRouter(){
        this.routeMap = MMSCRouter.retrieveRouteMapFromDB();
    }

    private static Map<String,MMSInfo> retrieveRouteMapFromDB(){
        Map<String,MMSInfo> map = new HashMap<String, MMSInfo>();
        return map;
    }

    public static MMSCRouter getInstance(){
        return instance;
    }


    public MMSInfo getMSC(String msiddnPrefix){
        return routeMap.get(msiddnPrefix);
    }

    public static  void setInstance(MMSCRouter newInstance){
        instance = newInstance;
    }

    private static Map<String, MMSInfo> deepCopy(Map<String, MMSInfo> m){
        Map<String, MMSInfo> result = new HashMap<String, MMSInfo>();

        for (String key: m.keySet()){
            result.put(key, new MMSInfo(m.get(key)));
        }
        return result;
    }

    public Map<String,MMSInfo> getRouteMap(){
        return Collections.unmodifiableMap(deepCopy(routeMap));
    }

}
