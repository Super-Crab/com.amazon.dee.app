package com.amazon.dee.sdk.iotsoftap;

import com.amazon.whisperjoin.softap.exceptions.SoftAPBadRequestException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPChannelUnsecuredException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPDecryptionException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPDeserializerException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidCipherException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidNetworkException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidProvisioningDataException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidPublicKeyException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidRegistrationTokenException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidSsidException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPRequestTimeoutException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPServerException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPUnableToEstablishConnectionException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPUnexpectedConnectionLostException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPUnknownRequestException;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
/* loaded from: classes12.dex */
public final class ErrorCodeMapper {
    private static final Map<Class, Integer> ERROR_CODE_MAP = new ImmutableMap.Builder().mo7828put(SoftAPInvalidSsidException.class, 1).mo7828put(SoftAPUnableToEstablishConnectionException.class, 2).mo7828put(SoftAPUnexpectedConnectionLostException.class, 3).mo7828put(SoftAPBadRequestException.class, 4).mo7828put(SoftAPDeserializerException.class, 5).mo7828put(SoftAPInvalidCipherException.class, 6).mo7828put(SoftAPInvalidPublicKeyException.class, 7).mo7828put(SoftAPRequestTimeoutException.class, 8).mo7828put(SoftAPServerException.class, 9).mo7828put(SoftAPUnknownRequestException.class, 10).mo7828put(SoftAPInvalidProvisioningDataException.class, 11).mo7828put(SoftAPInvalidRegistrationTokenException.class, 12).mo7828put(SoftAPDecryptionException.class, 13).mo7828put(SoftAPChannelUnsecuredException.class, 14).mo7828put(SoftAPInvalidNetworkException.class, 15).mo7826build();

    private ErrorCodeMapper() {
    }

    public static int getErrorCode(Throwable th) {
        if (ERROR_CODE_MAP.containsKey(th.getClass())) {
            return ERROR_CODE_MAP.get(th.getClass()).intValue();
        }
        return 16;
    }
}
