package com.amazon.whisperjoin.provisionerSDK.devices.security;

import com.amazon.whisperbridge.constants.Command;
import com.amazon.whispercloak.SecureChannel;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.AesGcmEncryptionProvider;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.AuthenticatedEcdheKeyExchangeRequest;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.EncryptionProvider;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.UnauthenticatedEcdheKeyExchangeRequest;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.TrustProviderInitializationFailedException;
import com.amazon.whisperjoin.common.sharedtypes.utility.Serializer;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.FinalizeEcdheAuthenticationSessionRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.StartEcdheAuthenticationSessionRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.StartEcdheAuthenticationSessionResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.ExecutionException;
import org.bouncycastle.util.encoders.Base64;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class BaseEcdheTrustNegotiator {
    private static final String TAG = "BaseEcdheTrustNegotiator";
    private final DSSClient mDSSClient;
    private final Serializer mSerializer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseEcdheTrustNegotiator(DSSClient dSSClient, Serializer serializer) {
        if (dSSClient != null) {
            if (serializer != null) {
                this.mDSSClient = dSSClient;
                this.mSerializer = serializer;
                return;
            }
            throw new IllegalArgumentException("Serializer can not be null");
        }
        throw new IllegalArgumentException("DSSClient can not be null");
    }

    private static byte[] convertPEMtoDER(String str) {
        String[] split = str.split("\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i != 0 && i != split.length - 1) {
                sb.append(split[i]);
            }
        }
        return Base64.decode(sb.toString());
    }

    private byte[] getPayloadForCommand(Command command, byte[] bArr, byte[] bArr2) {
        if (Command.EXCHANGE_ECDHE_KEY.equals(command)) {
            return this.mSerializer.serialize(new UnauthenticatedEcdheKeyExchangeRequest(bArr, bArr2));
        } else if (Command.EXCHANGE_AUTHENTICATED_ECDHE_KEY.equals(command)) {
            return this.mSerializer.serialize(new AuthenticatedEcdheKeyExchangeRequest(bArr, bArr2));
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported Command : ");
            outline107.append(command.name());
            throw new RuntimeException(outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EncryptionProvider start(PeripheralDevice peripheralDevice, SecureChannel secureChannel, Command command) throws TrustProviderInitializationFailedException {
        if (peripheralDevice != null) {
            WhisperJoinPeripheralDeviceDetails peripheralDeviceDetails = peripheralDevice.getPeripheralDeviceDetails();
            try {
                StartEcdheAuthenticationSessionResponse blockingGet = this.mDSSClient.startEcdheAuthenticationSession(new StartEcdheAuthenticationSessionRequest.Builder().setNonce(peripheralDeviceDetails.getClientNonce()).setProductIndex(peripheralDeviceDetails.getProductIndex()).setAuthMaterialIndex(peripheralDeviceDetails.getDeviceIdentity()).setSoftwareVersionIndex(peripheralDeviceDetails.getSoftwareVersion()).createRequest()).blockingGet();
                WJLog.d(TAG, "Handling response from DSS - Start Auth Session.");
                byte[] convertPEMtoDER = convertPEMtoDER(blockingGet.getProvisionerEcdhePublicKeyPemEncoded());
                byte[] decode = Base64.decode(blockingGet.getEcdsaSignatureBase64Encoded());
                WJLog.d(TAG, "Exchanging Keys with Provisionable");
                byte[] executeCommand = peripheralDevice.executeCommand(command, getPayloadForCommand(command, convertPEMtoDER, decode));
                WJLog.d(TAG, "Finalize Auth Session via DSS");
                FinalizeEcdheAuthenticationSessionRequest finalizeEcdheAuthenticationSessionRequest = new FinalizeEcdheAuthenticationSessionRequest(blockingGet.getContinuationToken(), executeCommand);
                WJLog.d(TAG, "Auth Session Finalized. Creating encryption provider with new shared secret.");
                return new AesGcmEncryptionProvider(secureChannel.getCipher(), this.mDSSClient.finalizeEcdheAuthenticationSession(finalizeEcdheAuthenticationSessionRequest).blockingGet().getSessionKeyDEREncoded(), this.mSerializer);
            } catch (InterruptedException e) {
                WJLog.e(TAG, "Interrupted Exception Occurred", e);
                throw new TrustProviderInitializationFailedException(e);
            } catch (RuntimeException e2) {
                WJLog.e(TAG, "RuntimeException Occurred", e2);
                if (e2.getCause() != null) {
                    throw new TrustProviderInitializationFailedException(e2.getCause());
                }
                throw new TrustProviderInitializationFailedException(e2);
            } catch (ExecutionException e3) {
                WJLog.e(TAG, "Execution Exception Occurred", e3);
                throw new TrustProviderInitializationFailedException(e3);
            } catch (Exception e4) {
                WJLog.e(TAG, "An exception occurred", e4);
                throw new TrustProviderInitializationFailedException(e4);
            }
        }
        throw new IllegalArgumentException("PeripheralDevice can not be null");
    }
}
