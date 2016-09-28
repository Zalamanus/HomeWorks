package com.javarush.test.level20.lesson07.task01;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/* Externalizable для апартаментов
Реализуйте интерфейс Externalizable для класса Apartment
Подумайте, какие поля не нужно сериализовать.
*/
public class Solution {

    public static class Apartment implements Externalizable {

        private String address;
        private int year;

        public Apartment() { super(); }

        public Apartment(String adr, int y) {
            address = adr;
            year = y;
        }

        public String toString() {
            return("Address: " + address + "\n" + "Year: " + year);
        }

        @Override
        public void writeExternal(ObjectOutput objectOutput) throws IOException {
            objectOutput.writeObject(address);
            objectOutput.writeInt(year);
        }

        @Override
        public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
            address= (String) objectInput.readObject();
            year = objectInput.readInt();
        }
    }
}
