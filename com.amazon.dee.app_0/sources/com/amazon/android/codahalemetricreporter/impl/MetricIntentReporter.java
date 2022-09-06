package com.amazon.android.codahalemetricreporter.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public class MetricIntentReporter {
    private static final String TAG = "MetricIntentReporter";
    private final Context mContext;
    private String mFormats;
    private final String mPermission;
    private final MetricRegistry mRegistry;
    private String mUuid;
    private final BroadcastReceiver mEnumReceiver = new BroadcastReceiver() { // from class: com.amazon.android.codahalemetricreporter.impl.MetricIntentReporter.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MetricIntentReporter.this.onEnumIntent(intent);
        }
    };
    private final BroadcastReceiver mReportReceiver = new BroadcastReceiver() { // from class: com.amazon.android.codahalemetricreporter.impl.MetricIntentReporter.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MetricIntentReporter.this.onReportIntent(intent);
        }
    };
    private final HashMap<String, ReportProvider> mProviders = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class BadRequestException extends Exception {
        public BadRequestException(String str) {
            super(str);
        }
    }

    public MetricIntentReporter(Context context, MetricRegistry metricRegistry, String str) {
        this.mContext = context;
        this.mRegistry = metricRegistry;
        this.mPermission = str;
    }

    private static MetricFilter getFilter(Intent intent) {
        String stringExtra = intent.getStringExtra(MetricIntent.REPORT_FILTER_EXTRA);
        return stringExtra != null ? new RegexMetricFilter(stringExtra) : MetricFilter.ALL;
    }

    private static String getFormats(HashMap<String, ?> hashMap) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it2 = hashMap.keySet().iterator();
        if (it2.hasNext()) {
            sb.append(it2.next());
            while (it2.hasNext()) {
                sb.append(JsonReaderKt.COMMA);
                sb.append(it2.next());
            }
        }
        return sb.toString();
    }

    private Intent newReplyIntent(Intent intent) throws BadRequestException {
        String stringExtra = intent.getStringExtra(MetricIntent.REPLY_ACTION_EXTRA);
        if (stringExtra != null) {
            String stringExtra2 = intent.getStringExtra(MetricIntent.REPLY_PACKAGE_EXTRA);
            if (stringExtra2 != null) {
                String stringExtra3 = intent.getStringExtra(MetricIntent.REPLY_COOKIE_EXTRA);
                Intent intent2 = new Intent(stringExtra);
                intent2.setPackage(stringExtra2);
                if (stringExtra3 != null) {
                    intent2.putExtra(MetricIntent.REPLY_COOKIE_EXTRA, stringExtra3);
                }
                intent2.putExtra(MetricIntent.REPORTER_UUID_EXTRA, this.mUuid);
                intent2.putExtra(MetricIntent.REPORTER_PACKAGE_EXTRA, this.mContext.getPackageName());
                return intent2;
            }
            throw new BadRequestException("REPLY_PACKAGE not specified");
        }
        throw new BadRequestException("REPLY_ACTION not specified");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEnumIntent(Intent intent) {
        try {
            Intent newReplyIntent = newReplyIntent(intent);
            newReplyIntent.putExtra(MetricIntent.REPORTER_FORMATS_EXTRA, this.mFormats);
            this.mContext.sendBroadcast(newReplyIntent);
        } catch (BadRequestException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bad enum request: ");
            outline107.append(e.getMessage());
            Log.e(TAG, outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onReportIntent(Intent intent) {
        try {
            Intent newReplyIntent = newReplyIntent(intent);
            String stringExtra = intent.getStringExtra(MetricIntent.REPORT_FORMAT_EXTRA);
            if (stringExtra != null) {
                MetricFilter filter = getFilter(intent);
                newReplyIntent.putExtra(MetricIntent.REPORT_FORMAT_EXTRA, stringExtra);
                ReportProvider reportProvider = this.mProviders.get(stringExtra);
                try {
                } catch (IOException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Generator error: ");
                    outline107.append(e.getMessage());
                    newReplyIntent.putExtra(MetricIntent.REPORT_ERROR_EXTRA, outline107.toString());
                }
                if (reportProvider != null) {
                    putBinderExtra(newReplyIntent, MetricIntent.REPORT_FILE_EXTRA, reportProvider.generate(this.mRegistry, filter));
                    this.mContext.sendBroadcast(newReplyIntent);
                    return;
                }
                throw new IOException("Format not supported: " + stringExtra);
            }
            throw new BadRequestException("REPORT_FORMAT not specified");
        } catch (BadRequestException e2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Bad report request: ");
            outline1072.append(e2.getMessage());
            Log.e(TAG, outline1072.toString());
        }
    }

    private static void putBinderExtra(Intent intent, String str, IBinder iBinder) {
        Bundle bundle = new Bundle();
        bundle.putBinder(str, iBinder);
        intent.putExtras(bundle);
    }

    public void addFormat(String str, ReportGenerator reportGenerator) {
        this.mProviders.put(str, new ReportProvider(this.mContext, str, reportGenerator));
    }

    public void start(Handler handler) {
        this.mFormats = getFormats(this.mProviders);
        this.mUuid = UUID.randomUUID().toString();
        this.mContext.registerReceiver(this.mEnumReceiver, GeneratedOutlineSupport1.outline10(MetricIntent.ENUM_ACTION), this.mPermission, handler);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MetricIntent.REPORT_ACTION);
        intentFilter.addAction(MetricIntent.getReportAction(this.mUuid));
        this.mContext.registerReceiver(this.mReportReceiver, intentFilter, this.mPermission, handler);
    }

    public void stop() {
        this.mContext.unregisterReceiver(this.mEnumReceiver);
        this.mContext.unregisterReceiver(this.mReportReceiver);
    }
}
