package com.liu.study.threadpool;

import java.util.concurrent.*;

class MyThread implements Runnable{
    public void run() {
        System.out.println(Thread.currentThread()+"===run");
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }


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
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(new MyThread());

        MyCallable myCallable = new MyCallable();
        MyCallable myCallable2 = new MyCallable();
        MyCallable myCallable3 = new MyCallable();
        MyCallable myCallable4 = new MyCallable();
        MyCallable myCallable5 = new MyCallable();
        MyCallable myCallable6 = new MyCallable();
        MyCallable myCallable7 = new MyCallable();
        MyCallable myCallable8 = new MyCallable();

        Future threadFuture = executorService.submit(myCallable);
        Future threadFuture2 = executorService.submit(myCallable2);
        Future threadFuture3= executorService.submit(myCallable3);
        Future threadFuture4 = executorService.submit(myCallable4);
        Future threadFuture5 = executorService.submit(myCallable5);
        Future threadFuture6 = executorService.submit(myCallable6);
        Future threadFuture7 = executorService.submit(myCallable7);
        Future threadFuture8 = executorService.submit(myCallable8);

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
        long end  = System.currentTimeMillis();

        System.out.println(end - start);

    }
}
