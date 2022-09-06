package com.amazon.whisperjoin.provisionerSDK.devices.security;

import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DeviceConnectionConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.utility.Serializer;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class TrustNegotiatorProvider {
    private static final String TAG = "TrustNegotiatorProvider";
    private DeviceConnectionConfiguration connectionConfiguration;
    private final DSSClient mDSSClient;
    private final Serializer mSerializer;
    private String mSharedSecretBase64;

    /* renamed from: com.amazon.whisperjoin.provisionerSDK.devices.security.TrustNegotiatorProvider$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$cryptography$TrustProvider$TrustState = new int[TrustProvider.TrustState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$cryptography$TrustProvider$TrustState[TrustProvider.TrustState.TRUSTED_ENCRYPTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$cryptography$TrustProvider$TrustState[TrustProvider.TrustState.UNTRUSTED_ENCRYPTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$cryptography$TrustProvider$TrustState[TrustProvider.TrustState.UNTRUSTED_PIN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$cryptography$TrustProvider$TrustState[TrustProvider.TrustState.UNTRUSTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public TrustNegotiatorProvider(DSSClient dSSClient, Serializer serializer) {
        if (dSSClient != null) {
            if (serializer != null) {
                this.mDSSClient = dSSClient;
                this.mSerializer = serializer;
                this.connectionConfiguration = null;
                this.mSharedSecretBase64 = null;
                return;
            }
            throw new IllegalArgumentException("serializer can not be null");
        }
        throw new IllegalArgumentException("DSSClient can not be null");
    }

    public TrustNegotiator getTrustProvider() {
        String str = this.mSharedSecretBase64;
        if (str != null) {
            return new PreSharedSecretTrustNegotiator(str, this.mSerializer);
        }
        if (this.connectionConfiguration != null) {
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Returning Trust Negotiator for trust state: ");
            outline107.append(this.connectionConfiguration.getTrustState());
            WJLog.d(str2, outline107.toString());
            int ordinal = this.connectionConfiguration.getTrustState().ordinal();
            if (ordinal == 0) {
                throw new UnsupportedOperationException("Untrusted Trust State is not supported");
            }
            if (ordinal == 1) {
                return new UnauthenticatedEcdheTrustNegotiator(this.mDSSClient, this.mSerializer);
            }
            if (ordinal == 2) {
                return new AuthenticatedEcdheTrustNegotiator(this.mDSSClient, this.mSerializer);
            }
            if (ordinal == 3) {
                return new UnauthenticatedJPAKETrustNegotiator(this.connectionConfiguration.getPin(), this.connectionConfiguration.getPublicKey(), this.mSerializer);
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unknown Trust State: ");
            outline1072.append(this.connectionConfiguration.getTrustState().toString());
            throw new IllegalStateException(outline1072.toString());
        }
        throw new IllegalStateException("Must set DeviceConnectionConfiguration before getting provider");
    }

    public void setDeviceConnectionConfiguration(DeviceConnectionConfiguration deviceConnectionConfiguration) {
        if (deviceConnectionConfiguration != null) {
            this.connectionConfiguration = deviceConnectionConfiguration;
            return;
        }
        throw new IllegalArgumentException("configuration can not be set to null");
    }

    public void setPreSharedSecret(String str) {
        this.mSharedSecretBase64 = str;
    }
}
