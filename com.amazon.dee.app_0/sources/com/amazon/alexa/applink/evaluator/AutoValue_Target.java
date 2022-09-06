package com.amazon.alexa.applink.evaluator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_Target extends Target {
    private final String catalogId;
    private final String identifier;
    private final boolean isMandatoryToLaunchTarget;
    private final TargetIdentifierType targetIdentifierType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Target(@Nullable String str, String str2, TargetIdentifierType targetIdentifierType, boolean z) {
        this.catalogId = str;
        if (str2 != null) {
            this.identifier = str2;
            if (targetIdentifierType != null) {
                this.targetIdentifierType = targetIdentifierType;
                this.isMandatoryToLaunchTarget = z;
                return;
            }
            throw new NullPointerException("Null targetIdentifierType");
        }
        throw new NullPointerException("Null identifier");
    }

    @Override // com.amazon.alexa.applink.evaluator.Target
    @Nullable
    public String catalogId() {
        return this.catalogId;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Target)) {
            return false;
        }
        Target target = (Target) obj;
        String str = this.catalogId;
        if (str != null ? str.equals(target.catalogId()) : target.catalogId() == null) {
            if (this.identifier.equals(target.identifier()) && this.targetIdentifierType.equals(target.targetIdentifierType()) && this.isMandatoryToLaunchTarget == target.isMandatoryToLaunchTarget()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.catalogId;
        return (((((((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003) ^ this.identifier.hashCode()) * 1000003) ^ this.targetIdentifierType.hashCode()) * 1000003) ^ (this.isMandatoryToLaunchTarget ? 1231 : 1237);
    }

    @Override // com.amazon.alexa.applink.evaluator.Target
    @NonNull
    public String identifier() {
        return this.identifier;
    }

    @Override // com.amazon.alexa.applink.evaluator.Target
    public boolean isMandatoryToLaunchTarget() {
        return this.isMandatoryToLaunchTarget;
    }

    @Override // com.amazon.alexa.applink.evaluator.Target
    @NonNull
    public TargetIdentifierType targetIdentifierType() {
        return this.targetIdentifierType;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Target{catalogId=");
        outline107.append(this.catalogId);
        outline107.append(", identifier=");
        outline107.append(this.identifier);
        outline107.append(", targetIdentifierType=");
        outline107.append(this.targetIdentifierType);
        outline107.append(", isMandatoryToLaunchTarget=");
        return GeneratedOutlineSupport1.outline97(outline107, this.isMandatoryToLaunchTarget, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
