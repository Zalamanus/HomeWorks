package com.javarush.test.level38.lesson06.home01;

/**
 * Created by MVTitov on 22.11.2016.
 */
public class ExceptionFactory {
    public static Throwable getException (Enum em) {
        if (em != null) {
            String emName = em.getClass().getSimpleName();
            String emMessage = em.name();
            emMessage = (emMessage.substring(0,1) + emMessage.substring(1).toLowerCase()).replaceAll("_"," ");
            switch (emName) {
                case "ExceptionApplicationMessage": return new Exception(emMessage);
                case "ExceptionDBMessage": return new RuntimeException(emMessage);
                case "ExceptionUserMessage": return new Error(emMessage);
            }
        }
        return new IllegalArgumentException();
    }
}
