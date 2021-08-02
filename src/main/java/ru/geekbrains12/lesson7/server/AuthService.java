package ru.geekbrains12.lesson7.server;

import java.util.List;

public class AuthService {

    private static class Entry {
        private final String login;
        private final String password;
        private final String nickname;

        public Entry(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }
    }

    private static final List<Entry> entries;

    static {
        entries = List.of(
                new Entry("thanos", "qwerty", "@Life_is_shit@"),
                new Entry("pquill1980", "123456", "StarLord"),
                new Entry("i", "am", "Groot"));
    }

    public boolean checkCredentials(String login, String password) {
        boolean isValid = false;
        for (Entry entry : entries) {
            if (entry.login.equals(login) && entry.password.equals(password)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    public String getNickname(String login) {
        for (Entry entry : entries) {
            if (entry.login.equals(login)) return entry.nickname;
        }
        return "Anonymous";
    }

}

