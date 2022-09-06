package com.amazon.alexa.applink.evaluator;

import android.content.Intent;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_EvaluatedTargetState extends EvaluatedTargetState {
    private final boolean canLaunch;
    private final InstallStatus installStatus;
    private final boolean isLaunchedInTarget;
    private final Intent launchIntent;
    private final Target target;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_EvaluatedTargetState(Target target, @Nullable Intent intent, InstallStatus installStatus, boolean z, boolean z2) {
        if (target != null) {
            this.target = target;
            this.launchIntent = intent;
            if (installStatus != null) {
                this.installStatus = installStatus;
                this.isLaunchedInTarget = z;
                this.canLaunch = z2;
                return;
            }
            throw new NullPointerException("Null installStatus");
        }
        throw new NullPointerException("Null target");
    }

    @Override // com.amazon.alexa.applink.evaluator.EvaluatedTargetState
    public boolean canLaunch() {
        return this.canLaunch;
    }

    public boolean equals(Object obj) {
        Intent intent;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EvaluatedTargetState)) {
            return false;
        }
        EvaluatedTargetState evaluatedTargetState = (EvaluatedTargetState) obj;
        return this.target.equals(evaluatedTargetState.target()) && ((intent = this.launchIntent) != null ? intent.equals(evaluatedTargetState.launchIntent()) : evaluatedTargetState.launchIntent() == null) && this.installStatus.equals(evaluatedTargetState.installStatus()) && this.isLaunchedInTarget == evaluatedTargetState.isLaunchedInTarget() && this.canLaunch == evaluatedTargetState.canLaunch();
    }

    public int hashCode() {
        int hashCode = (this.target.hashCode() ^ 1000003) * 1000003;
        Intent intent = this.launchIntent;
        int i = 1231;
        int hashCode2 = (((((hashCode ^ (intent == null ? 0 : intent.hashCode())) * 1000003) ^ this.installStatus.hashCode()) * 1000003) ^ (this.isLaunchedInTarget ? 1231 : 1237)) * 1000003;
        if (!this.canLaunch) {
            i = 1237;
        }
        return hashCode2 ^ i;
    }

    @Override // com.amazon.alexa.applink.evaluator.EvaluatedTargetState
    public InstallStatus installStatus() {
        return this.installStatus;
    }

    @Override // com.amazon.alexa.applink.evaluator.EvaluatedTargetState
    public boolean isLaunchedInTarget() {
        return this.isLaunchedInTarget;
    }

    @Override // com.amazon.alexa.applink.evaluator.EvaluatedTargetState
    @Nullable
    public Intent launchIntent() {
        return this.launchIntent;
    }

    @Override // com.amazon.alexa.applink.evaluator.EvaluatedTargetState
    public Target target() {
        return this.target;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EvaluatedTargetState{target=");
        outline107.append(this.target);
        outline107.append(", launchIntent=");
        outline107.append(this.launchIntent);
        outline107.append(", installStatus=");
        outline107.append(this.installStatus);
        outline107.append(", isLaunchedInTarget=");
        outline107.append(this.isLaunchedInTarget);
        outline107.append(", canLaunch=");
        return GeneratedOutlineSupport1.outline97(outline107, this.canLaunch, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
