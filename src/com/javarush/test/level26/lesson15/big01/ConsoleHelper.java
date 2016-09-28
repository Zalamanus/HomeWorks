package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import static com.javarush.test.level26.lesson15.big01.CashMachine.RESOURCE_PATH;

/**
 * Created by MVTitov on 12.08.2016.
 */
public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH+"common");

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);

    }

    public static String askCurrencyCode() throws InterruptOperationException {
        boolean correct = false;
        String s = null;
        while (!correct) {
            writeMessage(res.getString("choose.currency.code"));
            s = readString();
            if (s.length() != 3) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            correct = true;
        }
        return s.toUpperCase();
    }

    public static int askSumm() throws InterruptOperationException {
        boolean correct = false;
        int sum = 0;
        while (!correct) {
            correct = true;
            writeMessage(res.getString("specify.amount"));
            try {
                sum = Integer.parseInt(readString());
                if (sum < 0) correct = false;
            } catch (NumberFormatException e) {
                correct = false;
            }
            if (!correct) writeMessage(res.getString("specify.not.empty.amount"));
        }
        return sum;
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] arr = new String[2];
        boolean correct = false;
        while (!correct) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            String[] s = readString().split("\\s+");
            correct = true;
            if (s.length != 2) correct = false;
            for (String s1 : s) {
                try {
                    if (Integer.parseInt(s1) < 0) correct = false;
                } catch (NumberFormatException e) {
                    correct = false;
                }
            }
            if (!correct) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            arr = s;
        }
        return arr;
    }

    public static Operation askOperation() throws InterruptOperationException {
        boolean correct = false;
        Operation op = null;
        while (!correct) {
            writeMessage(res.getString("choose.operation"));
            writeMessage(String.format("1 - %s", res.getString("operation.INFO")));
            writeMessage(String.format("2 - %s", res.getString("operation.DEPOSIT")));
            writeMessage(String.format("3 - %s", res.getString("operation.WITHDRAW")));
            writeMessage(String.format("4 - %s", res.getString("operation.EXIT")));
            try {
                int i = Integer.parseInt(readString());
                op = Operation.getAllowableOperationByOrdinal(i);
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            correct = true;
        }
        return op;
    }

    public static String readString() throws InterruptOperationException {
        String s = null;
        try {
            s = reader.readLine();
            if (s.toUpperCase().equals("EXIT")) {
                throw new InterruptOperationException();
            }
        } catch (IOException e) {
        }
        return s;
    }
    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}
