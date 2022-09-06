package com.amazon.whisperjoin.softap.observables;

import android.util.Log;
import com.amazon.whisperjoin.softap.exceptions.SoftAPBadRequestException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPDecryptionException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidRegistrationTokenException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPRequestTimeoutException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPServerException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPUnknownRequestException;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilderProvider;
import com.amazon.whisperjoin.softap.wifi.requests.RequestException;
import com.amazon.whisperjoin.softap.wifi.requests.RequestTimeoutException;
/* loaded from: classes13.dex */
public class SecureSaveRegistrationToken extends PostBinaryData {
    private static final String ENDPOINT_PATH = "/stoken";
    private static final String TAG = "SecureSaveRegistrationToken";

    public SecureSaveRegistrationToken(byte[] bArr, RequestBuilderProvider requestBuilderProvider) {
        super(ENDPOINT_PATH, bArr, requestBuilderProvider);
    }

    @Override // com.amazon.whisperjoin.softap.observables.PostBinaryData
    protected Exception getSoftApException(RequestException requestException) {
        if (requestException.getErrorCode().isPresent()) {
            int intValue = requestException.getErrorCode().get().intValue();
            if (intValue == 400) {
                Log.e(TAG, "Invalid decrypted JSON format or missing decrypted fields while saving registration token", requestException);
                return new SoftAPBadRequestException(requestException);
            } else if (intValue == 401) {
                Log.e(TAG, "Invalid registration token while saving registration token", requestException);
                return new SoftAPInvalidRegistrationTokenException(requestException);
            } else if (intValue == 407) {
                Log.e(TAG, "Failed decryption or GCM authentication while saving registration token", requestException);
                return new SoftAPDecryptionException(requestException);
            } else if (intValue != 500) {
                Log.e(TAG, String.format("Request Exception while saving registration token with error code: %d", Integer.valueOf(intValue)), requestException);
                return new SoftAPUnknownRequestException(requestException);
            } else {
                Log.e(TAG, "Unknown device error", requestException);
                return new SoftAPServerException(requestException);
            }
        } else if (requestException instanceof RequestTimeoutException) {
            Log.e(TAG, "A Request Timeout Exception occurred while saving registration token", requestException);
            return new SoftAPRequestTimeoutException(requestException);
        } else {
            Log.e(TAG, "An Request Exception occurred while saving registration token", requestException);
            return new SoftAPUnknownRequestException(requestException);
        }
    }
}
