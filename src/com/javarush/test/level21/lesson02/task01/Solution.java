package com.javarush.test.level21.lesson02.task01;

/* Определяем адрес сети
1) Даны IP-адрес и маска подсети, необходимо вычислить адрес сети - метод getNetAddress.
Используйте операцию поразрядной конъюнкции (логическое И).
Пример:
IP-адрес:       11000000 10101000 00000001 00000010 (192.168.1.2)
Маска подсети:  11111111 11111111 11111110 00000000 (255.255.254.0)
Адрес сети:     11000000 10101000 00000000 00000000 (192.168.0.0)
2) Реализовать метод print, который выведет в консоль данные в двоичном коде
3) Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] b = new byte[ip.length];
        for (int i = 0; i < ip.length; i++) {
            b[i] = (byte) (ip[i] & mask[i]);
        }
        return b;
    }

    public static void print(byte[] bytes) {
        int[] arr = new int[bytes.length];
        for (int j =0; j<bytes.length; j++) {
            int i = bytes[j];
            if (i<0) i = i+256;
            arr[j]=i;
        }

        System.out.printf("%08d %08d %08d %08d\n",Integer.parseInt(Integer.toBinaryString(arr[0])),Integer.parseInt(Integer.toBinaryString(arr[1])),Integer.parseInt(Integer.toBinaryString(arr[2])),Integer.parseInt(Integer.toBinaryString(arr[3])));
    }
}
