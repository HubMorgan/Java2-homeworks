package lesson2;

public class Main {
    public static int arraySum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if(array.length != 4 || array[i].length != 4) {
                throw new MyArraySizeException();
            } else {
                for (int j = 0; j < array[i].length; j++) {
                    try {
                       sum += Integer.parseInt(array[i][j]);
                    } catch (NumberFormatException e) {
                        throw new MyArrayDataException(i,j);
                    }

                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {
        String[][] array = {
                {"1", "3", "6", "5" },
                {"1", "3", "4", "1" },
                {"1", "3", "9", "6" },
                {"1", "3", "23", "5" }
        };

        try {
            System.out.println(arraySum(array));
        } catch (MyArraySizeException | MyArrayDataException e) {
        }
    }
}
