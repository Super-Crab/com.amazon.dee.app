package com.amazon.whisperjoin.softap.security;

import com.amazon.whispercloak.SecureChannelImpl;
import com.google.common.base.Optional;
import java.util.List;
/* loaded from: classes13.dex */
public class DefaultSecureChannelProvider implements SecureChannelProvider {
    private static final Integer AES_GCM_ECDH_ENCRYPTION_SCHEME_ID = 0;
    private static final String TAG = "DefaultSecureChannelProvider";

    @Override // com.amazon.whisperjoin.softap.security.SecureChannelProvider
    public Optional<SecureChannelWrapper> createNewSecureChannel(List<Integer> list) {
        if (list != null && list.contains(AES_GCM_ECDH_ENCRYPTION_SCHEME_ID)) {
            return Optional.of(SecureChannelWrapper.builder().secureChannel(new SecureChannelImpl()).scheme(AES_GCM_ECDH_ENCRYPTION_SCHEME_ID.intValue()).build());
        }
        return Optional.absent();
    }
}
