package lesson2;

public class MyArrayDataException extends RuntimeException {
    MyArrayDataException(int i, int j) {
        System.out.println("Ошибка в элементе с индексом " + i + " " + j);
    }
}
