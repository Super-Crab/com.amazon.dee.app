package com.amazon.alexa.applink.sendtoapp.reportuserinteraction;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_ReportUserInteractionPayload extends ReportUserInteractionPayload {
    private final ReportUserInteractionResults result;
    private final String token;
    private final ReportUserInteractionType type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ReportUserInteractionPayload(ReportUserInteractionType reportUserInteractionType, ReportUserInteractionResults reportUserInteractionResults, String str) {
        if (reportUserInteractionType != null) {
            this.type = reportUserInteractionType;
            if (reportUserInteractionResults != null) {
                this.result = reportUserInteractionResults;
                if (str != null) {
                    this.token = str;
                    return;
                }
                throw new NullPointerException("Null token");
            }
            throw new NullPointerException("Null result");
        }
        throw new NullPointerException("Null type");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ReportUserInteractionPayload)) {
            return false;
        }
        ReportUserInteractionPayload reportUserInteractionPayload = (ReportUserInteractionPayload) obj;
        return this.type.equals(reportUserInteractionPayload.type()) && this.result.equals(reportUserInteractionPayload.result()) && this.token.equals(reportUserInteractionPayload.token());
    }

    public int hashCode() {
        return ((((this.type.hashCode() ^ 1000003) * 1000003) ^ this.result.hashCode()) * 1000003) ^ this.token.hashCode();
    }

    @Override // com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionPayload
    public ReportUserInteractionResults result() {
        return this.result;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReportUserInteractionPayload{type=");
        outline107.append(this.type);
        outline107.append(", result=");
        outline107.append(this.result);
        outline107.append(", token=");
        return GeneratedOutlineSupport1.outline91(outline107, this.token, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Override // com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionPayload
    public String token() {
        return this.token;
    }

    @Override // com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionPayload
    public ReportUserInteractionType type() {
        return this.type;
    }
}
