 package reference;
 //ref : https://github.com/kousen/java_8_recipes

import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        @SuppressWarnings("Convert2Lambda")
        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(100);
                return "Hello, World!";
            }
        });

        System.out.println("Processing...");

        getIfNotCancelled(future);

        future = service.submit(() -> {
            Thread.sleep(10);
            return "Hello, World!";
        });

        System.out.println("More processing...");

        while (!future.isDone()) {
            System.out.println("Waiting...");
        }

        getIfNotCancelled(future);

        future = service.submit(() -> {
            Thread.sleep(10);
            return "Hello, World!";
        });

        future.cancel(true);

        System.out.println("Even more processing...");

        getIfNotCancelled(future);

        if (!service.isShutdown())
            service.shutdown();
    }

    private static void getIfNotCancelled(Future<String> future) {
        try {
            if (!future.isCancelled()) {
                System.out.println(future.get());
            } else {
                System.out.println("Cancelled");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
