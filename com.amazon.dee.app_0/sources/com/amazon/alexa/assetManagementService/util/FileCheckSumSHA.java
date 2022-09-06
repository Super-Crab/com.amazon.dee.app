package com.amazon.alexa.assetManagementService.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.alexa.assetManagementService.model.constants.GetAssetURLServiceMetricsConstants;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes6.dex */
public class FileCheckSumSHA {
    private Context context;

    public FileCheckSumSHA(Context context) {
        this.context = context;
    }

    private static String checksum(InputStream inputStream, MessageDigest messageDigest) throws IOException {
        DigestInputStream digestInputStream = new DigestInputStream(inputStream, messageDigest);
        try {
            MessageDigest messageDigest2 = digestInputStream.getMessageDigest();
            digestInputStream.close();
            StringBuilder sb = new StringBuilder();
            byte[] digest = messageDigest2.digest();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x", Byte.valueOf(digest[i])));
            }
            return sb.toString();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    digestInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    private String getCheckSum() {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            messageDigest = null;
        }
        try {
            return checksum(this.context.getResources().getAssets().open("fallbackAssetMapping.json"), messageDigest);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean shouldFallbackBeParsed() {
        String string = this.context.getSharedPreferences(GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, 0).getString("FALLBACK", null);
        if (string == null) {
            return true;
        }
        if (string.equalsIgnoreCase(getCheckSum())) {
            return false;
        }
        SharedPreferences.Editor edit = this.context.getSharedPreferences(GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, 0).edit();
        edit.putString("FALLBACK", getCheckSum());
        edit.apply();
        return true;
    }
}
