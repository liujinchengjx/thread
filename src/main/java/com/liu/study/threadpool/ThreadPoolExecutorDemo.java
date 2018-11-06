package com.liu.study.threadpool;

import java.util.concurrent.*;

class MyThread implements Runnable{
    public void run() {
        System.out.println(Thread.currentThread()+"===run");


    }
}

class MyCallable implements Callable{
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"===call");
        return "成功";
    }
}

public class ThreadPoolExecutorDemo {

    public  static  void main(String[] args) throws  Exception{
       // ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,10,3000);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new MyThread());

        MyCallable myCallable = new MyCallable();

        Future threadFuture = executorService.submit(myCallable);
        try{
            Object resualt = threadFuture.get();
            System.out.println(resualt);
        }catch (InterruptedException e){
            // 处理线程中断异常
        }catch (ExecutionException e){
            // 处理无法执行异常
        } finally {
            executorService.shutdown();
        }

    }
}
