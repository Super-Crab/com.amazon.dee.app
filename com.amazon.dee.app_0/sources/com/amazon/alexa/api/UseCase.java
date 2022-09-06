package com.amazon.alexa.api;
/* loaded from: classes6.dex */
public enum UseCase {
    AUDIO_PROVIDER,
    PRIVILEGED,
    FULL,
    LIGHT;
    
    public static final String OPTION_KEY = UseCase.class.getCanonicalName();

    public static final UseCase valueOfIgnoresCase(String str) {
        if (str == null) {
            return null;
        }
        return valueOf(str.toUpperCase());
    }
}
