package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public class UserPerceivedLatencyData {
    private final long actualUpl;
    private final long alexaSpeechStarted;
    private final String dialogRequestId;
    private final long endOfSpeechOffset;
    private final long estimatedUpl;

    /* loaded from: classes6.dex */
    static class a implements u<UserPerceivedLatencyData> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.amazon.alexa.api.UserPerceivedLatencyData$a$a  reason: collision with other inner class name */
        /* loaded from: classes6.dex */
        public enum EnumC0027a implements Bundles.Key {
            DIALOG_REQUEST_ID,
            ESTIMATED_UPL,
            END_OF_SPEECH_OFFSET,
            ACTUAL_UPL,
            ALEXA_SPEECH_STARTED
        }

        @Override // com.amazon.alexa.api.u
        /* renamed from: a */
        public Bundle toBundle(UserPerceivedLatencyData userPerceivedLatencyData) {
            Preconditions.notNull(userPerceivedLatencyData, "UPL data is null");
            Bundle bundle = new Bundle();
            bundle.putString(EnumC0027a.DIALOG_REQUEST_ID.name(), userPerceivedLatencyData.getDialogRequestId());
            bundle.putLong(EnumC0027a.ESTIMATED_UPL.name(), userPerceivedLatencyData.getEstimatedUserPerceivedLatency());
            bundle.putLong(EnumC0027a.END_OF_SPEECH_OFFSET.name(), userPerceivedLatencyData.getEndOfSpeechOffset());
            bundle.putLong(EnumC0027a.ACTUAL_UPL.name(), userPerceivedLatencyData.getUserPerceivedLatency());
            bundle.putLong(EnumC0027a.ALEXA_SPEECH_STARTED.name(), userPerceivedLatencyData.getAlexaSpeechStarted());
            return bundle;
        }

        @Override // com.amazon.alexa.api.u
        /* renamed from: a */
        public UserPerceivedLatencyData mo844createFromBundle(Bundle bundle) {
            Preconditions.notNull(bundle, "bundle is null");
            return new UserPerceivedLatencyData(Bundles.getString(bundle, EnumC0027a.DIALOG_REQUEST_ID), Bundles.getLong(bundle, EnumC0027a.ESTIMATED_UPL), Bundles.getLong(bundle, EnumC0027a.END_OF_SPEECH_OFFSET), Bundles.getLong(bundle, EnumC0027a.ACTUAL_UPL), Bundles.getLong(bundle, EnumC0027a.ALEXA_SPEECH_STARTED));
        }
    }

    public UserPerceivedLatencyData(String str, long j, long j2, long j3, long j4) {
        this.dialogRequestId = str;
        this.estimatedUpl = j;
        this.endOfSpeechOffset = j2;
        this.actualUpl = j3;
        this.alexaSpeechStarted = j4;
    }

    public static UserPerceivedLatencyData fromBundle(Bundle bundle) {
        return new a().mo844createFromBundle(bundle);
    }

    public static Bundle toBundle(UserPerceivedLatencyData userPerceivedLatencyData) {
        return new a().toBundle(userPerceivedLatencyData);
    }

    public long getAlexaSpeechStarted() {
        return this.alexaSpeechStarted;
    }

    public String getDialogRequestId() {
        return this.dialogRequestId;
    }

    public long getEndOfSpeechOffset() {
        return this.endOfSpeechOffset;
    }

    public long getEstimatedUserPerceivedLatency() {
        return this.estimatedUpl;
    }

    public long getUserPerceivedLatency() {
        return this.actualUpl;
    }

    public boolean hasEndOfSpeechOffset() {
        return this.endOfSpeechOffset >= 0;
    }

    public boolean hasUserPerceivedLatency() {
        return this.actualUpl >= 0;
    }
}
