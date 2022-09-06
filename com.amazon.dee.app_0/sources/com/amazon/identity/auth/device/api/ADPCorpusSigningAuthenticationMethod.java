package com.amazon.identity.auth.device.api;

import android.os.Bundle;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface ADPCorpusSigningAuthenticationMethod {
    @FireOsSdk
    public static final String KEY_ADP_AUTH_SIGNATURE = "adp_signature";
    @FireOsSdk
    public static final String KEY_ADP_AUTH_TOKEN = "adp_token";
    @FireOsSdk
    public static final String KEY_ERROR_CODE = "error_code_key";
    @FireOsSdk
    public static final String KEY_ERROR_MESSAGE = "error_message_key";

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class SignCorpusError {
        @FireOsSdk
        public static final int INVALID_ARGUMENT = 3;
        @FireOsSdk
        public static final int IPC_ERROR = 1;
        @FireOsSdk
        public static final int NO_CREDENTIALS = 2;
        @FireOsSdk
        public static final int UNRECOGNIZED = 5;

        private SignCorpusError() {
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class SignCorpusOption {
        @FireOsSdk
        public static final String USE_FALL_BACK_CREDENTIALS = "UseFallBackCredentials";

        private SignCorpusOption() {
        }
    }

    @FireOsSdk
    MAPFuture<Bundle> signCorpus(byte[] bArr, Bundle bundle, Callback callback);
}
