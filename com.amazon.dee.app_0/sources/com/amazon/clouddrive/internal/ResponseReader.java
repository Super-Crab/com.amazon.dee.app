package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import java.io.IOException;
import java.net.HttpURLConnection;
/* loaded from: classes11.dex */
interface ResponseReader<T> {
    T read(HttpURLConnection httpURLConnection) throws IOException, CloudDriveException;
}
