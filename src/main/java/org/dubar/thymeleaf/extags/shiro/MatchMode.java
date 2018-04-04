package org.dubar.thymeleaf.extags.shiro;

public enum MatchMode {
    ANY,
    ALL,
    LACK,
    LACKALL;

    public static MatchMode fromString(String name){
        if (name == null || name.length() == 0){
            name = "ANY";
        }
        return MatchMode.valueOf(name.toUpperCase());
    }
}
