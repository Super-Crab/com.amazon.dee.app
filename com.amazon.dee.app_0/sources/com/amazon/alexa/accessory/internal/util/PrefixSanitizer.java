package com.amazon.alexa.accessory.internal.util;
/* loaded from: classes.dex */
public final class PrefixSanitizer implements StringSanitizer {
    private final String prefix;

    public PrefixSanitizer(String str) {
        this.prefix = str;
    }

    @Override // com.amazon.alexa.accessory.internal.util.StringSanitizer
    public String sanitize(String str) {
        return StringUtils.removePrefix(str, this.prefix);
    }
}
