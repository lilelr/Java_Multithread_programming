/**
 * Created by yuxiao on 12/7/17.
 */

public class OMCAgent extends Thread{
    @Override
    public void run() {
        boolean isTableModificationMsg = false;
        String updateTableName = null;

        while (true){

            if(isTableModificationMsg){
                if("MMSInfo".equals(updateTableName)){
                    MMSCRouter.setInstance(new MMSCRouter());
                }
            }
        }
    }
}
