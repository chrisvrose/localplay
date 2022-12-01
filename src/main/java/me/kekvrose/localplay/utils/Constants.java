package me.kekvrose.localplay.utils;

public final class Constants {
    protected static final String ROLE_PREFIX_STRING = "ROLE_";
    public final class Names {
        public static final String ADMIN = "admin";
        public static final String USER = "user";
    }
    public final class Roles{
        public static final String ADMIN_ROLE = "admin";
        public static final String USER_ROLE = "user";
    }
    public final class GrantedAuthority {
        public static final String ROLE_ADMIN=ROLE_PREFIX_STRING+Roles.ADMIN_ROLE;
        public static final String ROLE_USER=ROLE_PREFIX_STRING+Roles.USER_ROLE;
    }
}
