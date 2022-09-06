package com.amazon.clouddrive.auth;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
@Deprecated
/* loaded from: classes11.dex */
public interface AuthenticatedURLConnectionFactory {
    HttpURLConnection createHttpURLConnection(URL url) throws IOException, CloudDriveException, InterruptedException;
}
