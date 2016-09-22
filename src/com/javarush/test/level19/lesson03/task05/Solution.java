package com.javarush.test.level19.lesson03.task05;

import java.util.HashMap;
import java.util.Map;

/* Закрепляем адаптер
Адаптировать Customer и Contact к RowItem.
Классом-адаптером является DataAdapter.
Инициализируйте countries перед началом выполнения программы. Соответствие кода страны и названия:
UA Ukraine
RU Russia
CA Canada
*/

public class Solution {
    private static Map<String,String> countries = new HashMap<String,String>();
    static {
        countries.put("Ukraine", "UA");
        countries.put("Russia", "RU");
        countries.put("Canada", "CA");
    }

    public static class DataAdapter implements RowItem {
        private Customer cus;
        private Contact con;

        public DataAdapter(Customer cus, Contact con) {
            this.cus = cus;
            this.con = con;
        }

        @Override
        public String getCountryCode() {
            return countries.get(cus.getCountryName());
        }

        @Override
        public String getCompany() {
            return cus.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            String s =con.getName();
            int i = s.indexOf(", ");
            return s.substring(i+2);
        }

        @Override
        public String getContactLastName() {
            String s =con.getName();
            int i = s.indexOf(", ");
            return s.substring(0,i);
        }

        @Override
        public String getDialString() {
            String s = con.getPhoneNumber();
            s=s.replaceAll("[()-]", "");
            s="callto://"+s;
            return s;
        }
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }


}