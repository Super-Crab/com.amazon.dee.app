package com.amazon.alexa.voice.wakeword;

import com.amazon.alexa.voice.platform.ABIRetriever;
import com.google.common.collect.Sets;
import java.util.Set;
/* loaded from: classes11.dex */
public final class WakewordAbiCompatibility implements AbiCompatibilityInterface {
    private static final String TAG = "WWAbiCompatibility";
    private static final Set<String> UNSUPPORTED_ABIS = Sets.newHashSet("x86", "x86_64");
    private final ABIRetriever abiRetriever;

    public WakewordAbiCompatibility(ABIRetriever aBIRetriever) {
        this.abiRetriever = aBIRetriever;
    }

    @Override // com.amazon.alexa.voice.wakeword.AbiCompatibilityInterface
    public boolean isCompatible() {
        return !UNSUPPORTED_ABIS.contains(this.abiRetriever.getMostPreferredABI());
    }
}
