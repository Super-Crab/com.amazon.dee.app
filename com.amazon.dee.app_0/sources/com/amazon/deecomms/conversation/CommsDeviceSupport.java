package com.amazon.deecomms.conversation;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.EncryptionUtils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
/* loaded from: classes12.dex */
public class CommsDeviceSupport {
    private static final String CRYPTOGRAPHIC_CRASH = "cryptographicCrash";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsDeviceSupport.class);
    private final EncryptionUtils encryptionUtils;
    private CapabilitiesManager mCapabilitiesManager = CommsDaggerWrapper.getComponent().getCapabilitiesManager();

    public CommsDeviceSupport(EncryptionUtils encryptionUtils) {
        this.encryptionUtils = encryptionUtils;
    }

    public boolean check() {
        return true;
    }
}
