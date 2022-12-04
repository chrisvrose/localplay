package me.kekvrose.localplay.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Constants {
    public static final String ROLE_PREFIX_STRING = "ROLE_";
    /**
     * User class types
     * @deprecated
     */
    @Deprecated
    public final class Names {
        public static final String ADMIN = "admin";
        public static final String USER = "user";
    }
    /**
     * User roles
     */
    public final class Roles{
        public static final String ADMIN_ROLE = "admin";
        public static final String USER_ROLE = "user";

        public static final List<String> DEFAULT_ROLE_LIST = Collections.singletonList(USER_ROLE);
        public static final List<String> ADMIN_ROLE_LIST = Arrays.asList(USER_ROLE,ADMIN_ROLE);
    }
    /**
     * Roles written as authority strings
     */
    public final class GrantedAuthority {
        public static final String ROLE_ADMIN=ROLE_PREFIX_STRING+Roles.ADMIN_ROLE;
        public static final String ROLE_USER=ROLE_PREFIX_STRING+Roles.USER_ROLE;
    }
}
