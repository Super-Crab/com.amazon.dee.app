package com.amazon.alexa.handsfree.latencyreporter;

import android.content.Intent;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class TimestampIntentConverter {

    /* loaded from: classes8.dex */
    public enum TimestampName {
        ALEXA_UI_SHOWN,
        NEW_DIALOG_REQUEST_TIME,
        AUDIO_START_TIME,
        PRYON_RESET_TIME,
        PRYON_FINISH_TIME
    }

    public Intent convertToIntent(@NonNull String str, @Nullable String str2, long j) {
        Intent intent = new Intent();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        if (TimestampName.ALEXA_UI_SHOWN.name().equals(str)) {
            arrayList.add(new LatencyTimestamp(Latency.ALEXA_SERVICE_WAKE_WORD_DETECTION_LATENCY, TimestampType.END, str2, j));
            arrayList.add(new LatencyTimestamp(Latency.ALEXA_SERVICE_VOICE_CHROME_LATENCY_UI, TimestampType.END, str2, j));
            arrayList.add(new LatencyTimestamp(Latency.OVERALL_VOICE_CHROME_LATENCY, TimestampType.END, str2, j));
        } else if (TimestampName.AUDIO_START_TIME.name().equals(str)) {
            arrayList.add(new LatencyTimestamp(Latency.PARTNER_WAKE_WORD_DETECTION_LATENCY, TimestampType.START, str2, j));
            arrayList.add(new LatencyTimestamp(Latency.PARTNER_VOICE_CHROME_LATENCY, TimestampType.START, str2, j));
        } else if (TimestampName.NEW_DIALOG_REQUEST_TIME.name().equals(str)) {
            arrayList.add(new LatencyTimestamp(Latency.PARTNER_VOICE_CHROME_LATENCY, TimestampType.END, str2, j));
            arrayList.add(new LatencyTimestamp(Latency.ALEXA_SERVICE_VOICE_CHROME_LATENCY_API, TimestampType.START, str2, j));
        } else if (TimestampName.PRYON_RESET_TIME.name().equals(str)) {
            arrayList.add(new LatencyTimestamp(Latency.ALEXA_SERVICE_VOICE_CHROME_LATENCY_API, TimestampType.END, str2, j));
            arrayList.add(new LatencyTimestamp(Latency.ALEXA_SERVICE_VOICE_CHROME_LATENCY_PRYON, TimestampType.START, str2, j));
        } else if (TimestampName.PRYON_FINISH_TIME.name().equals(str)) {
            arrayList.add(new LatencyTimestamp(Latency.ALEXA_SERVICE_VOICE_CHROME_LATENCY_PRYON, TimestampType.END, str2, j));
            arrayList.add(new LatencyTimestamp(Latency.ALEXA_SERVICE_VOICE_CHROME_LATENCY_UI, TimestampType.START, str2, j));
        }
        intent.putParcelableArrayListExtra("latency", arrayList);
        return intent;
    }
}
