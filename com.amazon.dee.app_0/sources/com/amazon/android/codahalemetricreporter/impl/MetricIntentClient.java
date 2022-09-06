package com.amazon.android.codahalemetricreporter.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.android.codahalemetricreporter.MetricClient;
import com.amazon.android.codahalemetricreporter.impl.IRemoteFile;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes11.dex */
public class MetricIntentClient implements MetricClient {
    private static final String TAG = "MetricIntentClient";
    private MetricClient.Callbacks mCallbacks;
    private final Context mContext;
    private String mEnumReplyAction;
    private final String mPermission;
    private String mReportReplyAction;
    private final BroadcastReceiver mEnumReplyReceiver = new BroadcastReceiver() { // from class: com.amazon.android.codahalemetricreporter.impl.MetricIntentClient.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MetricIntentClient.this.onEnumReply(intent);
        }
    };
    private final BroadcastReceiver mReportReplyReceiver = new BroadcastReceiver() { // from class: com.amazon.android.codahalemetricreporter.impl.MetricIntentClient.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MetricIntentClient.this.onReportReply(intent);
        }
    };

    /* loaded from: classes11.dex */
    private static final class BadReplyException extends Exception {
        public BadReplyException(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class ReportImpl implements MetricClient.Report {
        private final String mError;
        private final IRemoteFile mFile;
        private final String mFormat;
        private final String mReporterPackage;
        private final String mReporterUuid;

        public ReportImpl(Intent intent) throws BadReplyException {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                this.mReporterUuid = extras.getString(MetricIntent.REPORTER_UUID_EXTRA);
                if (this.mReporterUuid != null) {
                    this.mReporterPackage = extras.getString(MetricIntent.REPORTER_PACKAGE_EXTRA);
                    if (this.mReporterPackage != null) {
                        this.mFormat = extras.getString(MetricIntent.REPORT_FORMAT_EXTRA);
                        if (this.mFormat != null) {
                            this.mError = extras.getString(MetricIntent.REPORT_ERROR_EXTRA);
                            if (this.mError != null) {
                                this.mFile = null;
                                return;
                            }
                            IBinder binder = extras.getBinder(MetricIntent.REPORT_FILE_EXTRA);
                            if (binder != null) {
                                this.mFile = IRemoteFile.Stub.asInterface(binder);
                                return;
                            }
                            throw new BadReplyException("REPORT_FILE not specified");
                        }
                        throw new BadReplyException("REPORT_FORMAT not specified");
                    }
                    throw new BadReplyException("REPORTER_PACKAGE not specified");
                }
                throw new BadReplyException("REPORTER_UUID not specified");
            }
            throw new BadReplyException("No extras specified");
        }

        @Override // com.amazon.android.codahalemetricreporter.MetricClient.Report
        public String getError() {
            return this.mError;
        }

        @Override // com.amazon.android.codahalemetricreporter.MetricClient.Report
        public String getFormat() {
            return this.mFormat;
        }

        @Override // com.amazon.android.codahalemetricreporter.MetricClient.Report
        public String getReporterPackage() {
            return this.mReporterPackage;
        }

        @Override // com.amazon.android.codahalemetricreporter.MetricClient.Report
        public String getReporterUuid() {
            return this.mReporterUuid;
        }

        @Override // com.amazon.android.codahalemetricreporter.MetricClient.Report
        public InputStream open() throws IOException {
            try {
                return new ParcelFileDescriptor.AutoCloseInputStream(this.mFile.getFd());
            } catch (RemoteException e) {
                throw new IOException("Report content not available", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class ReporterImpl implements MetricClient.Reporter {
        private final String[] mFormats;
        private final String mPackage;
        private final String mUuid;

        public ReporterImpl(Intent intent) throws BadReplyException {
            this.mUuid = intent.getStringExtra(MetricIntent.REPORTER_UUID_EXTRA);
            if (this.mUuid != null) {
                this.mPackage = intent.getStringExtra(MetricIntent.REPORTER_PACKAGE_EXTRA);
                String stringExtra = intent.getStringExtra(MetricIntent.REPORTER_FORMATS_EXTRA);
                if (stringExtra != null) {
                    this.mFormats = stringExtra.split(",");
                    return;
                } else {
                    this.mFormats = new String[0];
                    return;
                }
            }
            throw new BadReplyException("REPORTER_UUID not specified");
        }

        @Override // com.amazon.android.codahalemetricreporter.MetricClient.Reporter
        public String[] getFormats() {
            return this.mFormats;
        }

        @Override // com.amazon.android.codahalemetricreporter.MetricClient.Reporter
        public String getPackage() {
            return this.mPackage;
        }

        @Override // com.amazon.android.codahalemetricreporter.MetricClient.Reporter
        public String getUuid() {
            return this.mUuid;
        }
    }

    public MetricIntentClient(Context context, String str) {
        this.mContext = context;
        this.mPermission = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEnumReply(Intent intent) {
        try {
            this.mCallbacks.onEnumReply(new ReporterImpl(intent), intent.getStringExtra(MetricIntent.REPLY_COOKIE_EXTRA));
        } catch (BadReplyException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bad enum reply: ");
            outline107.append(e.getMessage());
            Log.e(TAG, outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onReportReply(Intent intent) {
        try {
            this.mCallbacks.onReportReply(new ReportImpl(intent), intent.getStringExtra(MetricIntent.REPLY_COOKIE_EXTRA));
        } catch (BadReplyException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bad report reply: ");
            outline107.append(e.getMessage());
            Log.e(TAG, outline107.toString());
        }
    }

    @Override // com.amazon.android.codahalemetricreporter.MetricClient
    public void close() {
        this.mContext.unregisterReceiver(this.mEnumReplyReceiver);
        this.mContext.unregisterReceiver(this.mReportReplyReceiver);
        this.mCallbacks = null;
    }

    @Override // com.amazon.android.codahalemetricreporter.MetricClient
    public void enumReporters(String str) {
        Intent intent = new Intent(MetricIntent.ENUM_ACTION);
        intent.putExtra(MetricIntent.REPLY_ACTION_EXTRA, this.mEnumReplyAction);
        intent.putExtra(MetricIntent.REPLY_PACKAGE_EXTRA, this.mContext.getPackageName());
        if (str != null) {
            intent.putExtra(MetricIntent.REPLY_COOKIE_EXTRA, str);
        }
        this.mContext.sendBroadcast(intent);
    }

    @Override // com.amazon.android.codahalemetricreporter.MetricClient
    public void open(Handler handler, MetricClient.Callbacks callbacks) {
        this.mCallbacks = callbacks;
        this.mEnumReplyAction = MetricIntent.genReplyAction();
        this.mReportReplyAction = MetricIntent.genReplyAction();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.mEnumReplyAction);
        this.mContext.registerReceiver(this.mEnumReplyReceiver, intentFilter, this.mPermission, handler);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(this.mReportReplyAction);
        this.mContext.registerReceiver(this.mReportReplyReceiver, intentFilter2, this.mPermission, handler);
    }

    @Override // com.amazon.android.codahalemetricreporter.MetricClient
    public void requestReport(MetricClient.Reporter reporter, String str, String str2, String str3) {
        requestReport(MetricIntent.getReportAction(reporter.getUuid()), reporter.getPackage(), str, str2, str3);
    }

    @Override // com.amazon.android.codahalemetricreporter.MetricClient
    public void requestReport(String str, String str2, String str3, String str4) {
        requestReport(MetricIntent.REPORT_ACTION, str, str2, str3, str4);
    }

    private void requestReport(String str, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent(str);
        intent.setPackage(str2);
        intent.putExtra(MetricIntent.REPLY_ACTION_EXTRA, this.mReportReplyAction);
        intent.putExtra(MetricIntent.REPLY_PACKAGE_EXTRA, this.mContext.getPackageName());
        intent.putExtra(MetricIntent.REPORT_FORMAT_EXTRA, str3);
        if (str4 != null) {
            intent.putExtra(MetricIntent.REPORT_FILTER_EXTRA, str4);
        }
        if (str5 != null) {
            intent.putExtra(MetricIntent.REPLY_COOKIE_EXTRA, str5);
        }
        this.mContext.sendBroadcast(intent);
    }
}
