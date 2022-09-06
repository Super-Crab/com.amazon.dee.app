package com.amazon.alexa.applink.sendtoapp.reportuserinteraction;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_ReportUserInteractionResults extends ReportUserInteractionResults {
    private final ReportUserInteractionResult fallback;
    private final ReportUserInteractionResult primary;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ReportUserInteractionResults(ReportUserInteractionResult reportUserInteractionResult, @Nullable ReportUserInteractionResult reportUserInteractionResult2) {
        if (reportUserInteractionResult != null) {
            this.primary = reportUserInteractionResult;
            this.fallback = reportUserInteractionResult2;
            return;
        }
        throw new NullPointerException("Null primary");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ReportUserInteractionResults)) {
            return false;
        }
        ReportUserInteractionResults reportUserInteractionResults = (ReportUserInteractionResults) obj;
        if (this.primary.equals(reportUserInteractionResults.primary())) {
            ReportUserInteractionResult reportUserInteractionResult = this.fallback;
            if (reportUserInteractionResult == null) {
                if (reportUserInteractionResults.fallback() == null) {
                    return true;
                }
            } else if (reportUserInteractionResult.equals(reportUserInteractionResults.fallback())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionResults
    @Nullable
    public ReportUserInteractionResult fallback() {
        return this.fallback;
    }

    public int hashCode() {
        int hashCode = (this.primary.hashCode() ^ 1000003) * 1000003;
        ReportUserInteractionResult reportUserInteractionResult = this.fallback;
        return hashCode ^ (reportUserInteractionResult == null ? 0 : reportUserInteractionResult.hashCode());
    }

    @Override // com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionResults
    public ReportUserInteractionResult primary() {
        return this.primary;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReportUserInteractionResults{primary=");
        outline107.append(this.primary);
        outline107.append(", fallback=");
        outline107.append(this.fallback);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
