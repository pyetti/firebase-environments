package com.deyvoo;

public enum Environment {

    DEV, PROD;

    static Environment getEnv(String env) {
        if (env == null || env.isEmpty()) {
            return null;
        }

        if (DEV.name().toLowerCase().equals(env.toLowerCase())) {
            return DEV;
        } else if (PROD.name().toLowerCase().equals(env.toLowerCase())) {
            return PROD;
        }
        return null;
    }

}
