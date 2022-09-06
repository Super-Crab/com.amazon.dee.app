package com.amazon.alexa.client.core.configuration;
/* loaded from: classes6.dex */
public enum Stage {
    ALPHA("alpha"),
    BETA("beta"),
    GAMMA("gamma"),
    PRE_RELEASE("pre-release"),
    PRE_PROD("pre-prod"),
    DOMAIN_TESTING("domain-testing"),
    PROD("prod");
    
    private final String stage;

    Stage(String str) {
        this.stage = str;
    }

    public static Stage from(String str) {
        Stage[] values;
        for (Stage stage : values()) {
            if (stage.toString().equals(str)) {
                return stage;
            }
        }
        return null;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.stage;
    }
}
