package com.amazon.alexa.handsfree.metrics.amok;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
/* loaded from: classes8.dex */
public class AlexaMetricsContentObserver extends ContentObserver {
    private static final String TAG = AlexaMetricsContentObserver.class.getSimpleName();
    private final Context mContext;
    private final Initializer mInitializer;
    private final VoiceAppMetricsEmitter mVoiceAppMetricsEmitter;

    public AlexaMetricsContentObserver(@NonNull Context context, @NonNull Handler handler) {
        this(context, handler, new VoiceAppMetricsEmitter(context), InitializerProvider.getInitializer());
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a0, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a3, code lost:
        return;
     */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void emitMetricsFromPartnerApp() {
        /*
            r9 = this;
            java.lang.String r0 = "_id"
            android.content.Context r1 = r9.mContext     // Catch: java.lang.Exception -> La4
            android.content.Context r1 = r1.getApplicationContext()     // Catch: java.lang.Exception -> La4
            android.content.ContentResolver r2 = r1.getContentResolver()     // Catch: java.lang.Exception -> La4
            android.net.Uri r3 = com.amazon.alexa.handsfree.protocols.metricsprovider.AlexaMetricsContract.AlexaMetricsTable.CONTENT_URI     // Catch: java.lang.Exception -> La4
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> La4
            if (r1 == 0) goto L9e
            int r2 = r1.getCount()     // Catch: java.lang.Throwable -> L92
            if (r2 != 0) goto L21
            goto L9e
        L21:
            int r2 = r1.getCount()     // Catch: java.lang.Throwable -> L92
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L92
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L92
            r3.<init>()     // Catch: java.lang.Throwable -> L92
        L2c:
            boolean r4 = r1.moveToNext()     // Catch: java.lang.Throwable -> L92
            if (r4 == 0) goto L6c
            java.lang.String r4 = com.amazon.alexa.handsfree.metrics.amok.AlexaMetricsContentObserver.TAG     // Catch: java.lang.Throwable -> L92
            java.lang.String r5 = "METRIC_TYPE"
            int r5 = r1.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L92
            java.lang.String r5 = r1.getString(r5)     // Catch: java.lang.Throwable -> L92
            com.amazon.alexa.handsfree.protocols.utils.Log.d(r4, r5)     // Catch: java.lang.Throwable -> L92
            com.amazon.alexa.handsfree.metrics.amok.VoiceAppMetricsEmitter r4 = r9.mVoiceAppMetricsEmitter     // Catch: java.lang.Throwable -> L92
            r4.addMetric(r1)     // Catch: java.lang.Throwable -> L92
            r3.append(r0)     // Catch: java.lang.Throwable -> L92
            java.lang.String r4 = " = ?"
            r3.append(r4)     // Catch: java.lang.Throwable -> L92
            boolean r4 = r1.isLast()     // Catch: java.lang.Throwable -> L92
            if (r4 != 0) goto L59
            java.lang.String r4 = " OR "
            r3.append(r4)     // Catch: java.lang.Throwable -> L92
        L59:
            int r4 = r1.getPosition()     // Catch: java.lang.Throwable -> L92
            int r5 = r1.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L92
            long r5 = r1.getLong(r5)     // Catch: java.lang.Throwable -> L92
            java.lang.String r5 = java.lang.Long.toString(r5)     // Catch: java.lang.Throwable -> L92
            r2[r4] = r5     // Catch: java.lang.Throwable -> L92
            goto L2c
        L6c:
            com.amazon.alexa.handsfree.metrics.amok.VoiceAppMetricsEmitter r0 = r9.mVoiceAppMetricsEmitter     // Catch: java.lang.Throwable -> L92
            r0.emit()     // Catch: java.lang.Throwable -> L92
            android.content.Context r0 = r9.mContext     // Catch: java.lang.Throwable -> L92
            android.content.Context r0 = r0.getApplicationContext()     // Catch: java.lang.Throwable -> L92
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.lang.Throwable -> L92
            android.net.Uri r4 = com.amazon.alexa.handsfree.protocols.metricsprovider.AlexaMetricsContract.AlexaMetricsTable.CONTENT_URI     // Catch: java.lang.Throwable -> L92
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L92
            int r0 = r0.delete(r4, r3, r2)     // Catch: java.lang.Throwable -> L92
            java.lang.String r2 = com.amazon.alexa.handsfree.metrics.amok.AlexaMetricsContentObserver.TAG     // Catch: java.lang.Throwable -> L92
            java.lang.String r0 = java.lang.Integer.toString(r0)     // Catch: java.lang.Throwable -> L92
            com.amazon.alexa.handsfree.protocols.utils.Log.d(r2, r0)     // Catch: java.lang.Throwable -> L92
            r1.close()     // Catch: java.lang.Exception -> La4
            goto Laf
        L92:
            r0 = move-exception
            throw r0     // Catch: java.lang.Throwable -> L94
        L94:
            r2 = move-exception
            r1.close()     // Catch: java.lang.Throwable -> L99
            goto L9d
        L99:
            r1 = move-exception
            r0.addSuppressed(r1)     // Catch: java.lang.Exception -> La4
        L9d:
            throw r2     // Catch: java.lang.Exception -> La4
        L9e:
            if (r1 == 0) goto La3
            r1.close()     // Catch: java.lang.Exception -> La4
        La3:
            return
        La4:
            r0 = move-exception
            java.lang.String r1 = com.amazon.alexa.handsfree.metrics.amok.AlexaMetricsContentObserver.TAG
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = "emitMetricsFromPartnerApp: "
            com.amazon.alexa.handsfree.protocols.utils.Log.e(r1, r3, r0, r2)
        Laf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.handsfree.metrics.amok.AlexaMetricsContentObserver.emitMetricsFromPartnerApp():void");
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        super.onChange(z);
        this.mInitializer.initialize(this.mContext);
        emitMetricsFromPartnerApp();
    }

    @VisibleForTesting
    AlexaMetricsContentObserver(@NonNull Context context, @NonNull Handler handler, @NonNull VoiceAppMetricsEmitter voiceAppMetricsEmitter, @NonNull Initializer initializer) {
        super(handler);
        this.mContext = context;
        this.mVoiceAppMetricsEmitter = voiceAppMetricsEmitter;
        this.mInitializer = initializer;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, @NonNull Uri uri) {
        super.onChange(z, uri);
        this.mInitializer.initialize(this.mContext);
        emitMetricsFromPartnerApp();
    }
}
