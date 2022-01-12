package lesson2;

public class MyArraySizeException extends RuntimeException {
    MyArraySizeException() {
        System.out.println("Неверный размер массива");
    }
}
