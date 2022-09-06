package com.amazon.whisperjoin.provisioning.security;

import com.amazon.whispercloak.SecureChannel;
import com.amazon.whispercloak.SecureChannelImpl;
/* loaded from: classes13.dex */
public class SecureChannelFactory {
    public SecureChannel createSecureChannel() {
        return new SecureChannelImpl();
    }
}
