package com.amazon.alexa.logupload;

import androidx.annotation.VisibleForTesting;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Response;
/* loaded from: classes9.dex */
public class LogNetworkService {
    private static final String TAG = "LogNetworkService";
    private OkHttpClient httpClient = new OkHttpClient();

    @VisibleForTesting
    Response makeNetworkCall(LogReport logReport) throws IOException, LogUploadException {
        return this.httpClient.newCall(logReport.getNetworkRequest()).execute();
    }

    public void sendLogReport(LogReport logReport) throws LogUploadException {
        try {
            Response makeNetworkCall = makeNetworkCall(logReport);
            if (makeNetworkCall.isSuccessful()) {
                return;
            }
            LogUploadException logUploadException = new LogUploadException("Unable to sendLogReport due to http error");
            logUploadException.setExceptionCode(2);
            logUploadException.setExceptionSubCode(makeNetworkCall.code());
            throw logUploadException;
        } catch (Exception e) {
            LogUploadException logUploadException2 = new LogUploadException("Unable to make network call", e);
            logUploadException2.setExceptionCode(1);
            throw logUploadException2;
        }
    }
}
