package com.amazon.clouddrive.internal.utils;

import com.amazon.clouddrive.exceptions.InvalidParameter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes11.dex */
public class StreamUtil {
    public static long copyInputStreamToOutputStream(InputStream inputStream, OutputStream outputStream, int i, long j) throws IOException, InterruptedException, InvalidParameter {
        ThreadUtil.checkIfInterrupted();
        byte[] bArr = new byte[i];
        long j2 = 0;
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read <= 0) {
                break;
            }
            j2 += read;
            if (j2 > j) {
                break;
            }
            ThreadUtil.checkIfInterrupted();
            outputStream.write(bArr, 0, read);
            ThreadUtil.checkIfInterrupted();
        }
        return j2;
    }
}
