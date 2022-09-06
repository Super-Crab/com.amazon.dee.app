package com.amazon.alexa.applink.sendtoapp.reportuserinteraction;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class ReportUserInteractionResults {
    public static ReportUserInteractionResults create(ReportUserInteractionResult reportUserInteractionResult, @Nullable ReportUserInteractionResult reportUserInteractionResult2) {
        return new AutoValue_ReportUserInteractionResults(reportUserInteractionResult, reportUserInteractionResult2);
    }

    @Nullable
    public abstract ReportUserInteractionResult fallback();

    public abstract ReportUserInteractionResult primary();
}
