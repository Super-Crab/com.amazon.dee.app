package com.amazon.android.codahalemetricreporter;

import android.os.Handler;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes11.dex */
public interface MetricClient {

    /* loaded from: classes11.dex */
    public interface Callbacks {
        void onEnumReply(Reporter reporter, String str);

        void onReportReply(Report report, String str);
    }

    /* loaded from: classes11.dex */
    public interface Report {
        String getError();

        String getFormat();

        String getReporterPackage();

        String getReporterUuid();

        InputStream open() throws IOException;
    }

    /* loaded from: classes11.dex */
    public interface Reporter {
        String[] getFormats();

        String getPackage();

        String getUuid();
    }

    void close();

    void enumReporters(String str);

    void open(Handler handler, Callbacks callbacks);

    void requestReport(Reporter reporter, String str, String str2, String str3);

    void requestReport(String str, String str2, String str3, String str4);
}
