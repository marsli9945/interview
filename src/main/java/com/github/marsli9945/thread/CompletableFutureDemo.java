package com.github.marsli9945.thread;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.*;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        runAsync();
//        supplyAsync();
//        exceptionally();
//        thenApply();
//        thenCompose();
//        thenAccept();
//        thenAcceptBoth();
//        thenRun();
//        thenCombine();
//        applyToEither();
//        acceptEither();
//        runAfterEither();
//        anyOf();
//        allOf();
        completableFutureTest();
        System.out.println("this is main..........");
    }

    private static void runAsync() {
        Runnable runnable = () -> System.out.println("无返回结果异步任务");
        CompletableFuture.runAsync(runnable);
    }

    private static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回值的异步任务");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello World";
        });
        System.out.println(future.get());
    }

    private static void exceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ignored) {
                    }
                    if (new Random().nextInt(10) % 2 == 0) {
                        int i = 12 / 0;
                    }
                    System.out.println("执行结束！");
                    return "future";
                })
                // 任务完成或异常方法完成时执行该方法
                // 如果出现了异常,任务结果为null
                .whenComplete((t, action) -> System.out.println(t + " 执行完成！"))
                // 出现异常时先执行该方法
                .exceptionally(t -> {
                    System.out.println("执行失败：" + t.getMessage());
                    return "异常xxxix";
                });

        System.out.println(future.get());
    }

    private static void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = 100;
            System.out.println("第一次运算：" + result);
            return result;
        }).thenApply(number -> {
            int result = number * 3;
            System.out.println("第二次运算：" + result);
            return result;
        });
        System.out.println(future.get());
    }

    private static void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(30);
            System.out.println("第一次运算：" + number);
            return number;
        }).thenCompose(param -> CompletableFuture.supplyAsync(() -> {
            int number = param * 2;
            System.out.println("第二次运算：" + number);
            return number;
        }));
        System.out.println(future.get());
    }

    private static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            System.out.println("第一次运算：" + number);
            return number;
        }).thenAccept(number -> System.out.println("第二次运算：" + number * 5));
        System.out.println(future.get());
    }

    private static void thenAcceptBoth() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(3) + 1;
            try {
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结果：" + number);
            return number;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(3) + 1;
            try {
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2结果：" + number);
            return number;
        });

        future1.thenAcceptBoth(future2, (x, y) -> System.out.println("最终结果：" + (x + y)));
        System.out.println(future1.get());
        System.out.println(future2.get());
    }

    private static void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            System.out.println("第一阶段：" + number);
            return number;
        }).thenRun(() -> System.out.println("thenRun 执行"));
        System.out.println(future.get());
    }

    private static void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture
                .supplyAsync(() -> {
                    int number = new Random().nextInt(10);
                    System.out.println("任务1结果：" + number);
                    return number;
                });
        CompletableFuture<Integer> future2 = CompletableFuture
                .supplyAsync(() -> {
                    int number = new Random().nextInt(10);
                    System.out.println("任务2结果：" + number);
                    return number;
                });
        CompletableFuture<Integer> result = future1.thenCombine(future2, Integer::sum);
        System.out.println("组合后结果：" + result.get());
    }

    private static void applyToEither() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结果:" + number);
            return number;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2结果:" + number);
            return number;
        });

        CompletableFuture<Integer> future3 = future1.applyToEither(future2, number -> {
            System.out.println("最快结果：" + number);
            return number * 2;
        });
        System.out.println(future3.get());
    }

    private static void acceptEither() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10) + 1;
            try {
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一阶段：" + number);
            return number;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(10) + 1;
                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第二阶段：" + number);
                return number;
            }
        });

        future1.acceptEither(future2, number -> System.out.println("最快结果：" + number));
        System.out.println(future1.get());
        System.out.println(future2.get());
    }

    private static void runAfterEither() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(5);
            try {
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结果：" + number);
            return number;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(5);
            try {
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2结果:" + number);
            return number;
        });

        future1.runAfterEither(future2, () -> System.out.println("已经有一个任务完成了")).join();
    }

    private static void anyOf() throws ExecutionException, InterruptedException {
        Random random = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        });
        CompletableFuture<Object> result = CompletableFuture.anyOf(future1, future2);
        System.out.println(result.get());
    }

    private static void allOf() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1完成！");
            return "future1完成！";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2完成！");
            return "future2完成！";
        });

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2);

        try {
            combinedFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void completableFutureTest() {
        //任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
                    System.out.println("T1:洗水壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1:烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });
        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
                    System.out.println("T2:洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2:洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2:拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return "龙井";
                });
        //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
            System.out.println("T1:拿到茶叶:" + tf);
            System.out.println("T1:泡茶...");
            return "上茶:" + tf;
        });
        //等待任务3执行结果
        System.out.println(f3.join());
    }

    static void sleep(int t, TimeUnit u){
        try {
            u.sleep(t);
        } catch (InterruptedException ignored) {
        }
    }
}
