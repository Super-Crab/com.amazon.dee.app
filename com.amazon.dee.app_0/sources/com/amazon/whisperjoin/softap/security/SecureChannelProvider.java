package com.amazon.whisperjoin.softap.security;

import com.google.common.base.Optional;
import java.util.List;
/* loaded from: classes13.dex */
public interface SecureChannelProvider {
    Optional<SecureChannelWrapper> createNewSecureChannel(List<Integer> list);
}
