package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
/* loaded from: classes8.dex */
public class StringUtils {
    private static final int BUFFER_SIZE = 1024;
    private static final String TAG = "StringUtils";

    @Nullable
    public String getStringFromInputStream(@Nullable InputStream inputStream, @NonNull Charset charset) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                try {
                    return byteArrayOutputStream.toString(charset.name());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.e(TAG, "getStringFromInputStream: ", e, new Object[0]);
                    return null;
                }
            }
        }
    }
}
