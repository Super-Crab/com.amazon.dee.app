package com.amazon.crypto.asymmetric;

import com.amazon.crypto.utils.UtilityInstanceAttempt;
/* loaded from: classes12.dex */
final class AsymmetricCrypto {
    static final String ALGORITHM = "RSA";
    static final int KEY_SIZE_2048 = 2048;
    static final int MAX_PAYLOAD_LENGTH = 512;

    private AsymmetricCrypto() {
        UtilityInstanceAttempt.in(this);
    }
}
