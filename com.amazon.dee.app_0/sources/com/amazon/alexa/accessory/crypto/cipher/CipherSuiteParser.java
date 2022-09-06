package com.amazon.alexa.accessory.crypto.cipher;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class CipherSuiteParser {
    private static final Map<String, SupportedCipherSuite> DESCRIPTOR_TO_CIPHER_SUITE;

    static {
        SupportedCipherSuite[] values;
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (SupportedCipherSuite supportedCipherSuite : SupportedCipherSuite.values()) {
            builder.mo7828put(supportedCipherSuite.descriptor, supportedCipherSuite);
        }
        DESCRIPTOR_TO_CIPHER_SUITE = builder.mo7826build();
    }

    private CipherSuiteParser() {
    }

    public static Optional<SupportedCipherSuite> fromAccessoryDescriptor(String str) {
        return Optional.fromNullable(DESCRIPTOR_TO_CIPHER_SUITE.get(Strings.nullToEmpty(str)));
    }
}
