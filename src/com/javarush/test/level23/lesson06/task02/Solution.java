package com.javarush.test.level23.lesson06.task02;

/* Рефакторинг
Отрефакторите класс Solution: вынесите все константы в public вложенный(nested) класс Constants.
Запретите наследоваться от Constants.
*/
public class Solution {
    public static final class Constants {
        public static final String servNotAcc = "Server is not accessible for now.";
        public static final String unAutUser = "User is not authorized.";
        public static final String banUser = "User is banned.";
        public static final String accDen = "Access is denied.";
    }

    public class ServerNotAccessibleException extends Exception {
        public ServerNotAccessibleException() {
            super(Constants.servNotAcc);
        }

        public ServerNotAccessibleException(Throwable cause) {
            super(Constants.servNotAcc, cause);
        }
    }

    public class UnauthorizedUserException extends Exception {
        public UnauthorizedUserException() {
            super(Constants.unAutUser);
        }

        public UnauthorizedUserException(Throwable cause) {
            super(Constants.unAutUser, cause);
        }
    }

    public class BannedUserException extends Exception {
        public BannedUserException() {
            super(Constants.banUser);
        }

        public BannedUserException(Throwable cause) {
            super(Constants.banUser, cause);
        }
    }

    public class RestrictionException extends Exception {
        public RestrictionException() {
            super(Constants.accDen);
        }

        public RestrictionException(Throwable cause) {
            super(Constants.accDen, cause);
        }
    }
}
