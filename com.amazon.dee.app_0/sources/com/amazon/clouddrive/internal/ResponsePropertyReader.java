package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import java.net.HttpURLConnection;
import okhttp3.Response;
/* loaded from: classes11.dex */
public interface ResponsePropertyReader {
    @Deprecated
    void readHeaders(HttpURLConnection httpURLConnection) throws CloudDriveException, InterruptedException;

    void readHeaders(Response response) throws CloudDriveException, InterruptedException;
}
