package threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yuxiao on 4/23/18.
 */
public class MyThreadPoolSize4IOIntensiveTask {

    public static void main(String[] args){

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, Runtime.getRuntime().availableProcessors()*2, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(200));

        threadPool.submit(new IOIntensiveTask());

    }

    private static class IOIntensiveTask implements Runnable{

        public void run() {
            System.out.println("lele");
        }
    }

}
