package atguigu.com.p2p.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：李银庆 on 2017/6/21 12:39
 */
public class ThreadManager {

    private ThreadManager(){};

    private static ThreadManager threadManager = new ThreadManager();

    public ThreadManager getInstence(){
        return threadManager;
    }


    private ExecutorService executorService = Executors.newCachedThreadPool();

    public ExecutorService getThread(){
        return executorService;
    }
}
