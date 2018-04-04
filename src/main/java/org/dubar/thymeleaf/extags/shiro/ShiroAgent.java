package org.dubar.thymeleaf.extags.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public final class ShiroAgent {
    public static boolean isAuthenticated() {
        Subject subject =  SecurityUtils.getSubject();
        return subject != null && subject.isAuthenticated();
    }

    public static boolean PermissionMatched(String[] permissions, MatchMode mode) {
        switch (mode){
            case ALL: return HaveAllPermissions(permissions);
            case LACK:return !HaveAllPermissions(permissions);
            case LACKALL:return !HaveAnyPermission(permissions);
            default:return HaveAnyPermission(permissions);
        }
    }

    private static boolean HaveAnyPermission(String[] permissions) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            for (String perm : permissions) {
                if (subject.isPermitted(perm)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean HaveAllPermissions(String[] permissions) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null){
            for (String perm : permissions){
                if (!subject.isPermitted(perm)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean UserMatched(String[] users, String propName, boolean lacked) throws NoSuchFieldException, IllegalAccessException {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null){
            String text = getSubjectText(subject,propName);
            if (lacked){
                return !Arrays.asList(users).contains(text);
            }
            return Arrays.asList(users).contains(text);
        }
        return lacked;
    }

    public static boolean RoleMatched(String[] roles, MatchMode mode) {
        switch (mode){
            case ALL: return HaveAllRoles(roles);
            case LACK:return !HaveAllRoles(roles);
            case LACKALL:return !HaveAnyRole(roles);
            default:return HaveAnyRole(roles);
        }
    }

    private static boolean HaveAnyRole(String[] roles) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null){
            for (String role : roles){
                if (subject.hasRole(role)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean HaveAllRoles(String[] roles) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null){
            List<String> roleList = Arrays.asList(roles);
            return subject.hasAllRoles(roleList);
        }
        return false;
    }

    public static String getPrincipalInfo(String propertyName) throws NoSuchFieldException, IllegalAccessException {
        Subject subject = SecurityUtils.getSubject();
        return getSubjectText(subject,propertyName);
    }

    private static String getSubjectText(Subject subject, String propertyName) throws NoSuchFieldException, IllegalAccessException {
        if (subject != null){
            Object principal = subject.getPrincipal();
            if (principal != null){
                if (propertyName == null || propertyName.isEmpty()){
                    return String.valueOf(principal);
                }
                return getPrincipalProperty(principal,propertyName);
            }
        }
        return null;
    }

    private static String getPrincipalProperty(Object principal, String propertyName) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = principal.getClass();
        Field field = clazz.getDeclaredField(propertyName);
        field.setAccessible(true);
        return field != null ? String.valueOf(field.get(principal)) : String.valueOf(principal);
    }
}
