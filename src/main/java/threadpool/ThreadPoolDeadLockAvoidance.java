package threadpool;

import java.util.concurrent.*;

/**
 * Created by yuxiao on 4/23/18.
 */
public class ThreadPoolDeadLockAvoidance {

    private final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args){
        ThreadPoolDeadLockAvoidance me = new ThreadPoolDeadLockAvoidance();
        me.test("This will not deadlock");
    }

    public void test(final String message){
        Runnable taskA = new Runnable() {
            public void run() {
                System.out.println("Executing TaskA ...");
                Runnable taskB = new Runnable() {
                    public void run() {
                        System.out.println("TaskB processes "+ message);
                    }
                };
                Future<?> result = threadPool.submit(taskB);

                try{
                    result.get();
                }catch (InterruptedException e){
                    ;
                }catch (ExecutionException e){
                    e.printStackTrace();
                }

                System.out.println("Task A Done.");
            }
        };

        threadPool.submit(taskA);

    }
}

