package com.javarush.test.level26.lesson15.big01;



import com.javarush.test.level26.lesson15.big01.command.CommandExecutor;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;

/**
 * Created by MVTitov on 12.08.2016.
 */
public class CashMachine {
    public static final String RESOURCE_PATH = "com.javarush.test.level26.lesson15.big01.resources.";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Operation currentOp;
        try {
            CommandExecutor.execute(Operation.LOGIN);

            do {
                currentOp = ConsoleHelper.askOperation();
                CommandExecutor.execute(currentOp);

            } while (true);
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
    }


}
