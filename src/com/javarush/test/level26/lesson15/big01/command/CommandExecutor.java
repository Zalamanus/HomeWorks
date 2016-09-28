package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.Operation;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

import static com.javarush.test.level26.lesson15.big01.CashMachine.RESOURCE_PATH;

/**
 * Created by MVTitov on 12.08.2016.
 */
public final class CommandExecutor {
    private static Map<Operation, Command> map = new HashMap<>();

    private CommandExecutor() {
    }

    static {
        map.put(Operation.DEPOSIT, new DepositCommand());
        map.put(Operation.EXIT, new ExitCommand());
        map.put(Operation.WITHDRAW, new WithdrawCommand());
        map.put(Operation.INFO, new InfoCommand());
        map.put(Operation.LOGIN, new LoginCommand());
    }

    public static final void execute(Operation operation) throws InterruptOperationException {
        map.get(operation).execute();
    }

}

interface Command {
    void execute() throws InterruptOperationException;
}

class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH+"exit");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String s = ConsoleHelper.readString();
        if (s.toLowerCase().equals(res.getString("yes"))) {
            ConsoleHelper.writeMessage(res.getString("thank.message"));
            System.exit(0);
        }

    }
}

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH+"info");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        int i = 0;
        for (CurrencyManipulator currencyManipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (currencyManipulator.hasMoney()) {
                System.out.println(String.format("%s - %s", currencyManipulator.getCurrencyCode(), currencyManipulator.getTotalAmount()));
                i++;
            }
        }
        if (i == 0) ConsoleHelper.writeMessage(res.getString("no.money"));

    }
}

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH+"withdraw");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        boolean correct = false;
        while (!correct) {
            int sum = ConsoleHelper.askSumm();
            if (!manipulator.isAmountAvailable(sum)) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }
            try {
                for (Map.Entry<Integer, Integer> entry : manipulator.withdrawAmount(sum).entrySet()) {
                    ConsoleHelper.writeMessage(String.format("\t%d - %d", entry.getKey(), entry.getValue()));
                }
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),sum,currencyCode));
                correct = true;
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }


        }

    }
}

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH+"deposit");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] cash = ConsoleHelper.getValidTwoDigits(currencyCode);
        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode).addAmount(Integer.parseInt(cash[0]), Integer.parseInt(cash[1]));
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"),Integer.parseInt(cash[0])*Integer.parseInt(cash[1]),currencyCode));


    }
}

class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(RESOURCE_PATH+"verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH+"login");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean correct = false;
        while (!correct) {
            correct = true;
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String s1 = ConsoleHelper.readString();
            if (s1.length() != 12 || s1.matches("\\d*\\D+\\d*")) correct = false;
            String s2 = ConsoleHelper.readString();
            if (s2.length() != 4 || s1.matches("\\d*\\D+\\d*")) correct = false;
            if (!correct) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }
            try {
                if (!validCreditCards.getString(s1).equals(s2)) correct = false;
            } catch (Exception e) {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),s1));
                ConsoleHelper.writeMessage(String.format(res.getString("try.again.or.exit"),s1));
                correct = false;
            }
            if (!correct) continue;
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"),s1));
        }
    }
}

