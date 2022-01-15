package lesson3;

import java.util.*;

public class PhoneBook {

    Map<List<String>, String> map = new HashMap<>();

    public void add(List<String> phone, String surname) {
        map.put(phone, surname);
    }

    public List<List<String>> get(String surname) {
        List<List<String>> values = new ArrayList<>();
        if (map.containsValue(surname)) {
            for (Map.Entry<List<String>, String> i : map.entrySet()) {
                if (surname.equals(i.getValue())) {
                    values.add(i.getKey());
                }
            }
        } else {
            System.out.println("Совпадений не найдено");
        }

        return values;
    }

}
