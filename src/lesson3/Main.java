package lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String[] arr = {
                "word1",
                "word2",
                "word4",
                "word4",
                "word5",
                "word6",
                "word7",
                "word8",
                "word8",
                "word10",
                "word11",
                "word12",
                "word13",
                "word14",
                "word15",
                "word16",
                "word17",
                "word18",
                "word18",
                "word20",
        };

        Map<String, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < arr.length; i++) {
            Integer count = map.getOrDefault(arr[i], 0);
            map.put((arr[i]), ++count);
        }

        System.out.println(map);


        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add(Arrays.asList("123-3233-323"), "Валуев");
        phoneBook.add(Arrays.asList("123-32-323"), "Валуев");
        phoneBook.add(Arrays.asList("123-3233-324343"), "Грацкий");
        phoneBook.add(Arrays.asList("123-5533-323"), "Пугачева");
        phoneBook.add(Arrays.asList("8-800-555-35-35"), "Киркоров");
        phoneBook.add(Arrays.asList("999-2323-454"), "Газманов");
        phoneBook.add(Arrays.asList("123-323333-323"), "Сердючка");
        phoneBook.add(Arrays.asList("123-35555-233-323", "33-22-3"), "Галыгин");

        System.out.println(phoneBook.get("Незлобин"));
    }

}
