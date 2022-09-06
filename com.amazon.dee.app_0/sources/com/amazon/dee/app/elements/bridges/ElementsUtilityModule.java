package com.amazon.dee.app.elements.bridges;

import androidx.annotation.VisibleForTesting;
import com.amazon.crypto.Crypto;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
@ReactModule(name = "ElementsUtility")
/* loaded from: classes12.dex */
public class ElementsUtilityModule extends ReactContextBaseJavaModule {
    private static final int AAD_TAG_SIZE = 16;
    private static final int IV_SIZE = 12;
    private static final String TAG = Log.tag(ElementsUtilityModule.class);

    public ElementsUtilityModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private String toBase64EncodedUTF_8String(byte[] bArr) {
        return new String(Crypto.encode(bArr), StandardCharsets.UTF_8);
    }

    @VisibleForTesting
    WritableMap deconstructCipher(byte[] bArr) {
        int length = (bArr.length - 16) - 12;
        byte[] copyOf = Arrays.copyOf(bArr, length);
        int i = length + 16;
        byte[] copyOfRange = Arrays.copyOfRange(bArr, length, i);
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr, i, bArr.length);
        WritableMap createMap = Arguments.createMap();
        createMap.putString("cipher", toBase64EncodedUTF_8String(bArr));
        createMap.putString("ct", toBase64EncodedUTF_8String(copyOf));
        createMap.putString("iv", toBase64EncodedUTF_8String(copyOfRange2));
        createMap.putString("aadTag", toBase64EncodedUTF_8String(copyOfRange));
        return createMap;
    }

    @ReactMethod
    public void encryptStringAES(String str, String str2, String str3, Promise promise) {
        try {
            promise.resolve(deconstructCipher(getAESEncryptedBytes(str, str2, str3)));
        } catch (IllegalArgumentException | SecurityException e) {
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Error while encrypting ", str, ". Exception: ");
            outline115.append(e.getMessage());
            String sb = outline115.toString();
            Log.e(TAG, sb);
            promise.reject(sb);
        }
    }

    @ReactMethod
    public void encryptStringRSA(String str, boolean z, String str2, Promise promise) {
        try {
            promise.resolve(toBase64EncodedUTF_8String(getRSAEncryptedBytes(str, z, str2)));
        } catch (IllegalArgumentException | SecurityException e) {
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Error while encrypting ", str, ". Exception: ");
            outline115.append(e.getMessage());
            String sb = outline115.toString();
            Log.e(TAG, sb);
            promise.reject(sb);
        }
    }

    @ReactMethod
    public void generateAESKey(Promise promise) {
        try {
            promise.resolve(toBase64EncodedUTF_8String(getAESKey()));
        } catch (SecurityException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error while generating AES key. Exception: ");
            outline107.append(e.getMessage());
            String sb = outline107.toString();
            Log.e(TAG, sb);
            promise.reject(sb);
        }
    }

    @VisibleForTesting
    byte[] getAESEncryptedBytes(String str, String str2, String str3) throws IllegalArgumentException, SecurityException {
        return Crypto.encryptWithAESKeyAndAAD(str.getBytes(), str3.getBytes(), str2);
    }

    @VisibleForTesting
    byte[] getAESKey() throws SecurityException {
        return Crypto.generateSymmetricKey().toBytes();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "ElementsUtility";
    }

    @VisibleForTesting
    byte[] getRSAEncryptedBytes(String str, boolean z, String str2) throws IllegalArgumentException, SecurityException {
        byte[] bytes = str.getBytes();
        if (z) {
            bytes = Crypto.decode(bytes);
        }
        return Crypto.encryptWithPublicKey(bytes, str2);
    }

    @ReactMethod
    public void verifyWithRSA(String str, String str2, String str3, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(verifyWithRSAPublicKey(str, str2, str3)));
        } catch (IllegalArgumentException | SecurityException e) {
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Error while verifying ", str, ". Exception: ");
            outline115.append(e.getMessage());
            String sb = outline115.toString();
            Log.e(TAG, sb);
            promise.reject(sb);
        }
    }

    @VisibleForTesting
    boolean verifyWithRSAPublicKey(String str, String str2, String str3) throws IllegalArgumentException, SecurityException {
        return Crypto.verifyWithRSAPublicKey(str, str2, str3);
    }
}
