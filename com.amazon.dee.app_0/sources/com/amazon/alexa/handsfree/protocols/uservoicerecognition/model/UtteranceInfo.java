package com.amazon.alexa.handsfree.protocols.uservoicerecognition.model;

import androidx.annotation.NonNull;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class UtteranceInfo {
    private final String mText;
    private final String mUtteranceId;

    public UtteranceInfo(@NonNull String str, @NonNull String str2) {
        this.mUtteranceId = (String) Objects.requireNonNull(str, "Utterance ID must not be null.");
        this.mText = (String) Objects.requireNonNull(str2, "Text must not be null.");
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != UtteranceInfo.class) {
            return false;
        }
        UtteranceInfo utteranceInfo = (UtteranceInfo) obj;
        return Objects.equals(this.mUtteranceId, utteranceInfo.mUtteranceId) && Objects.equals(this.mText, utteranceInfo.mText);
    }

    @NonNull
    public String getText() {
        return this.mText;
    }

    @NonNull
    public String getUtteranceId() {
        return this.mUtteranceId;
    }

    public int hashCode() {
        return Objects.hash(this.mUtteranceId, this.mText);
    }

    public String toString() {
        return String.format("[id = %s, text = %s]", this.mUtteranceId, this.mText);
    }
}
