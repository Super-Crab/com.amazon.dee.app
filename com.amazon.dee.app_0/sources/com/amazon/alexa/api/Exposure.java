package com.amazon.alexa.api;
/* loaded from: classes6.dex */
public enum Exposure {
    HIDDEN,
    RESTRICTED,
    PUBLIC;
    
    public static final String OPTION_KEY = Exposure.class.getCanonicalName();

    public static final Exposure valueOfIgnoresCase(String str) {
        if (str == null) {
            return null;
        }
        return valueOf(str.toUpperCase());
    }
}
