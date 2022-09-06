package com.amazon.alexa.applink.sendtoapp.reportuserinteraction;

import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class ReportUserInteractionPayload {
    public static ReportUserInteractionPayload create(ReportUserInteractionType reportUserInteractionType, ReportUserInteractionResults reportUserInteractionResults, String str) {
        return new AutoValue_ReportUserInteractionPayload(reportUserInteractionType, reportUserInteractionResults, str);
    }

    public abstract ReportUserInteractionResults result();

    public abstract String token();

    public abstract ReportUserInteractionType type();
}
