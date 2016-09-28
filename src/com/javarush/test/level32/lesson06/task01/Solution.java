package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать
ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            ByteArrayOutputStream password = getPassword();
            System.out.println(password.toString());

        }
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Random r = new Random();
        for (int i = 0; i < 8; i++) {
            byte[] symbols = new byte[3];
            symbols[0] = (byte) (97 + r.nextInt(26));
            symbols[1] = (byte) (65 + r.nextInt(26));
            symbols[2] = (byte) (48 + r.nextInt(10));
            baos.write(symbols[r.nextInt(3)]);
        }
        String s = baos.toString();
        if (!s.matches(".*[a-z]+.*") || !s.matches(".*[A-Z]+.*") || !s.matches(".*[0-9]+.*")) baos = getPassword();
        return baos;
    }
}
