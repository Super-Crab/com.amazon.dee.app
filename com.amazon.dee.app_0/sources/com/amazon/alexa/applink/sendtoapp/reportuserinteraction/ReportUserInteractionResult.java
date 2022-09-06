package com.amazon.alexa.applink.sendtoapp.reportuserinteraction;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class ReportUserInteractionResult {
    public static ReportUserInteractionResult create(@Nullable ReportUserInteractionOutcome reportUserInteractionOutcome, @Nullable ReportUserInteractionReason reportUserInteractionReason) {
        return new AutoValue_ReportUserInteractionResult(reportUserInteractionOutcome, reportUserInteractionReason);
    }

    @Nullable
    public abstract ReportUserInteractionOutcome outcome();

    @Nullable
    public abstract ReportUserInteractionReason reason();
}
