package com.amazon.deecomms.calling.phonecallcontroller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
/* loaded from: classes12.dex */
public class DefaultNoCallPermissionHandler implements INoCallPermissionHandler {
    private final Context context;

    public DefaultNoCallPermissionHandler(@NonNull Context context) {
        this.context = context;
    }

    @Override // com.amazon.deecomms.calling.phonecallcontroller.INoCallPermissionHandler
    public void handleNoPermission(int i, @Nullable String str, @NonNull String str2) {
        CounterMetric generateOperational = CounterMetric.generateOperational(str2);
        generateOperational.getMetadata().put("errorSource", MetricKeys.VALUE_NO_PERMISSIONS);
        PCCDirectiveHandler.recordPCCMetric(generateOperational, false);
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setFlags(268435456);
        intent.setData(Uri.parse("tel:" + str));
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CALL_OPEN_DIALER);
        ContextCompat.startActivity(this.context, intent, null);
    }
}
