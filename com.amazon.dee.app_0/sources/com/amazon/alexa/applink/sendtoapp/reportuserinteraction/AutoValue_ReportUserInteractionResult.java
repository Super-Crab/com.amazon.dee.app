package com.amazon.alexa.applink.sendtoapp.reportuserinteraction;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_ReportUserInteractionResult extends ReportUserInteractionResult {
    private final ReportUserInteractionOutcome outcome;
    private final ReportUserInteractionReason reason;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ReportUserInteractionResult(@Nullable ReportUserInteractionOutcome reportUserInteractionOutcome, @Nullable ReportUserInteractionReason reportUserInteractionReason) {
        this.outcome = reportUserInteractionOutcome;
        this.reason = reportUserInteractionReason;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ReportUserInteractionResult)) {
            return false;
        }
        ReportUserInteractionResult reportUserInteractionResult = (ReportUserInteractionResult) obj;
        ReportUserInteractionOutcome reportUserInteractionOutcome = this.outcome;
        if (reportUserInteractionOutcome != null ? reportUserInteractionOutcome.equals(reportUserInteractionResult.outcome()) : reportUserInteractionResult.outcome() == null) {
            ReportUserInteractionReason reportUserInteractionReason = this.reason;
            if (reportUserInteractionReason == null) {
                if (reportUserInteractionResult.reason() == null) {
                    return true;
                }
            } else if (reportUserInteractionReason.equals(reportUserInteractionResult.reason())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        ReportUserInteractionOutcome reportUserInteractionOutcome = this.outcome;
        int i = 0;
        int hashCode = ((reportUserInteractionOutcome == null ? 0 : reportUserInteractionOutcome.hashCode()) ^ 1000003) * 1000003;
        ReportUserInteractionReason reportUserInteractionReason = this.reason;
        if (reportUserInteractionReason != null) {
            i = reportUserInteractionReason.hashCode();
        }
        return hashCode ^ i;
    }

    @Override // com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionResult
    @Nullable
    public ReportUserInteractionOutcome outcome() {
        return this.outcome;
    }

    @Override // com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionResult
    @Nullable
    public ReportUserInteractionReason reason() {
        return this.reason;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReportUserInteractionResult{outcome=");
        outline107.append(this.outcome);
        outline107.append(", reason=");
        outline107.append(this.reason);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
