package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.common.io.FileBackedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.ResponseBody;
/* loaded from: classes11.dex */
public final class ResponseBodyToInputStream {
    private static final int IN_MEMORY_CACHE_SIZE = 32768;
    private static final int READ_BUFFER_SIZE = 4096;
    private static final boolean RESET_ON_FINALIZE = true;

    private ResponseBodyToInputStream() {
    }

    @Nullable
    public static InputStream toStream(@NonNull ResponseBody responseBody) {
        byte[] bArr = new byte[4096];
        try {
            FileBackedOutputStream fileBackedOutputStream = new FileBackedOutputStream(32768, true);
            InputStream byteStream = responseBody.byteStream();
            while (true) {
                try {
                    int read = byteStream.read(bArr);
                    if (read == -1) {
                        fileBackedOutputStream.flush();
                        InputStream openBufferedStream = fileBackedOutputStream.asByteSource().openBufferedStream();
                        byteStream.close();
                        fileBackedOutputStream.close();
                        return openBufferedStream;
                    }
                    fileBackedOutputStream.write(bArr, 0, read);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (byteStream != null) {
                            try {
                                byteStream.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }
}
