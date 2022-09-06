package com.amazon.whispercloak;

import com.amazon.whispercloak.random.PRNGFixes;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
/* loaded from: classes13.dex */
public class CryptoInitializer {
    private static boolean mInitialized = false;

    private CryptoInitializer() {
    }

    public static void initialize() {
        if (!mInitialized) {
            PRNGFixes.apply();
            Security.addProvider(new BouncyCastleProvider());
            mInitialized = true;
        }
    }
}
