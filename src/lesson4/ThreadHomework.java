package lesson4;

public class ThreadHomework {
    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;

    public static void firstMethod() {
        float arr[] = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время выполнения в один поток: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    static void secondMethod() throws InterruptedException {
        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();
        float[] arr1 = new float[HALF];
        System.arraycopy(arr, 0, arr1, 0, 5_000_000);
        float[] arr2 = new float[HALF];
        System.arraycopy(arr,5_000_000, arr2, 0, 5_000_000);


        Thread t1 = new Thread(() -> {
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0, j = 5_000_000; i < arr2.length; i++, j++) {
                arr2[i] = (float) (arr2[i] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        float[] finalArr = new float[SIZE];
        t1 = new Thread(() -> System.arraycopy(arr1, 0, finalArr, 0, 5_000_000));

        t2 = new Thread(() -> System.arraycopy(arr2, 0, finalArr, 5_000_000, 5_000_000));

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Время выполнения в 2 потока: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    public static void main(String[] args) throws InterruptedException {
        firstMethod();
        secondMethod();
    }

}
