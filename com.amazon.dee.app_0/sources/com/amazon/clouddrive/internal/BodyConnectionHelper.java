package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.NoRetryException;
import com.amazon.clouddrive.internal.utils.Closer;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
/* loaded from: classes11.dex */
class BodyConnectionHelper {
    BodyConnectionHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addPostInput(HttpURLConnection httpURLConnection, PostRequestWriter postRequestWriter) throws CloudDriveException, InterruptedException {
        OutputStream outputStream = null;
        try {
            try {
                try {
                    outputStream = httpURLConnection.getOutputStream();
                    httpURLConnection.connect();
                    postRequestWriter.writeRequest(outputStream);
                    outputStream.flush();
                } catch (IOException e) {
                    throw new NoRetryException("Failed to write request to connection.", e);
                }
            } catch (InterruptedIOException unused) {
                throw new InterruptedException();
            }
        } finally {
            Closer.closeQuietly(outputStream);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setConnectionParameters(HttpURLConnection httpURLConnection, String str) throws ProtocolException {
        httpURLConnection.setRequestMethod(str);
        httpURLConnection.addRequestProperty("Connection", "Keep-Alive");
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
    }
}
