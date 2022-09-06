package com.amazon.alexa.hho.stickynotes;

import android.system.ErrnoException;
import android.system.OsConstants;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralServiceException;
import io.reactivex.rxjava3.exceptions.CompositeException;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class ErrorMapper {
    private static final String TAG = "ErrorMapper";

    /* JADX INFO: Access modifiers changed from: package-private */
    public String map(Throwable th) {
        if (th instanceof CompositeException) {
            CompositeException compositeException = (CompositeException) th;
            return compositeException.getExceptions().size() == 0 ? "UNKNOWN" : map(compositeException.getExceptions().get(0));
        } else if (th instanceof CoralServiceException) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HTTP_ERROR:");
            outline107.append(((CoralServiceException) th).getStatusCode());
            return outline107.toString();
        } else if (th instanceof DownloadCanceledException) {
            return "CANCELED";
        } else {
            if (!(th instanceof SecurityException) && !(th instanceof StorageException) && !(th instanceof IOException)) {
                if (th instanceof NetworkException) {
                    if (th.getCause() == null) {
                        return "NETWORK_ERROR";
                    }
                    Throwable cause = th.getCause();
                    if (cause instanceof UnknownHostException) {
                        return GeneratedOutlineSupport1.outline72("NETWORK_ERROR", ":NO_CONNECTION");
                    }
                    if ((cause instanceof SSLException) || (cause instanceof ProtocolException)) {
                        return GeneratedOutlineSupport1.outline72("NETWORK_ERROR", ":CONNECTION_LOST");
                    }
                    return cause instanceof SocketTimeoutException ? GeneratedOutlineSupport1.outline72("NETWORK_ERROR", ":TIMEOUT") : "NETWORK_ERROR";
                }
                Log.e(TAG, "Unable to map error: ", th);
                return "UNKNOWN";
            }
            if ((th instanceof StorageException) && th.getCause() != null) {
                th = th.getCause();
            }
            return (th.getCause() == null || !(th.getCause() instanceof ErrnoException) || ((ErrnoException) th.getCause()).errno != OsConstants.ENOSPC) ? "CACHE_ERROR" : GeneratedOutlineSupport1.outline72("CACHE_ERROR", ":DISK_FULL");
        }
    }
}
