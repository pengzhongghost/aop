package com.example.springbootdemo;

import org.junit.jupiter.api.Test;
import org.omg.CORBA.TIMEOUT;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.*;

@SpringBootTest
class SpringbootDemoApplicationTests {

    @Test
    void contextLoads() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 3, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        BlockingQueue<Runnable> queue = executor.getQueue();
        //System.out.println(queue);
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + "1");
            System.out.println(queue.equals(executor.getQueue()));
        });
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + "2");
            System.out.println(queue.equals(executor.getQueue()));
        });
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + 1 / 0);
            System.out.println(queue.equals(executor.getQueue()));
        });
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + "3");
            System.out.println(queue.equals(executor.getQueue()));
        });
        TimeUnit.SECONDS.sleep(1);
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + "6");
            System.out.println(queue.equals(executor.getQueue()));
        });
        TimeUnit.SECONDS.sleep(2);
    }

}
