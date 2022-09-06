package com.amazon.alexa.logupload;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.logupload.LogUploadMetricsConstants;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes9.dex */
public class LogUploadMetricsService {
    private static final String TAG = "LogUploadMetricsService";
    private final Mobilytics mobilytics = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
    private final String prefix;

    public LogUploadMetricsService(@Nullable String str) {
        this.prefix = TextUtils.isEmpty(str) ? "default" : str;
    }

    private void recordError(String str, @NonNull String str2) {
        String str3 = "Error name: " + str + ", description: " + str2;
        this.mobilytics.recordOccurrence(str, true, LogUploadMetricsConstants.ComponentName.COMPONENT, LogUploadMetricsConstants.ComponentName.SUB_COMPONENT);
    }

    private void recordOccurrence(String str) {
        GeneratedOutlineSupport1.outline158("Occurrence: ", str);
        this.mobilytics.recordOccurrence(str, true, LogUploadMetricsConstants.ComponentName.COMPONENT, LogUploadMetricsConstants.ComponentName.SUB_COMPONENT);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recordLogUploadAttempt() {
        recordOccurrence(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.prefix, LogUploadMetricsConstants.EventName.UPLOAD_ATTEMPT_SUFFIX));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recordLogUploadHttpError(int i, @NonNull String str) {
        recordError(this.prefix + LogUploadMetricsConstants.EventName.UPLOAD_HTTP_ERROR_INFIX + i, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recordLogUploadOtherError(@NonNull String str) {
        recordError(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.prefix, LogUploadMetricsConstants.EventName.UPLOAD_OTHER_ERROR_SUFFIX), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recordLogUploadRetrievingError(@NonNull String str) {
        recordError(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.prefix, LogUploadMetricsConstants.EventName.UPLOAD_RETRIEVING_ERROR_SUFFIX), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recordLogUploadSuccess() {
        recordOccurrence(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.prefix, LogUploadMetricsConstants.EventName.UPLOAD_SUCCESS_SUFFIX));
    }
}
