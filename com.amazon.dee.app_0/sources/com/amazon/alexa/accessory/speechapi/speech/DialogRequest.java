package com.amazon.alexa.accessory.speechapi.speech;

import com.amazon.alexa.accessory.speechapi.LaunchType;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DialogRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/DialogRequest;", "", AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE, "", "launchType", "Lcom/amazon/alexa/accessory/speechapi/LaunchType;", "(Ljava/lang/String;Lcom/amazon/alexa/accessory/speechapi/LaunchType;)V", "getInvocationType", "()Ljava/lang/String;", "getLaunchType", "()Lcom/amazon/alexa/accessory/speechapi/LaunchType;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DialogRequest {
    @NotNull
    private final String invocationType;
    @NotNull
    private final LaunchType launchType;

    public DialogRequest(@NotNull String invocationType, @NotNull LaunchType launchType) {
        Intrinsics.checkParameterIsNotNull(invocationType, "invocationType");
        Intrinsics.checkParameterIsNotNull(launchType, "launchType");
        this.invocationType = invocationType;
        this.launchType = launchType;
    }

    public static /* synthetic */ DialogRequest copy$default(DialogRequest dialogRequest, String str, LaunchType launchType, int i, Object obj) {
        if ((i & 1) != 0) {
            str = dialogRequest.invocationType;
        }
        if ((i & 2) != 0) {
            launchType = dialogRequest.launchType;
        }
        return dialogRequest.copy(str, launchType);
    }

    @NotNull
    public final String component1() {
        return this.invocationType;
    }

    @NotNull
    public final LaunchType component2() {
        return this.launchType;
    }

    @NotNull
    public final DialogRequest copy(@NotNull String invocationType, @NotNull LaunchType launchType) {
        Intrinsics.checkParameterIsNotNull(invocationType, "invocationType");
        Intrinsics.checkParameterIsNotNull(launchType, "launchType");
        return new DialogRequest(invocationType, launchType);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DialogRequest)) {
                return false;
            }
            DialogRequest dialogRequest = (DialogRequest) obj;
            return Intrinsics.areEqual(this.invocationType, dialogRequest.invocationType) && Intrinsics.areEqual(this.launchType, dialogRequest.launchType);
        }
        return true;
    }

    @NotNull
    public final String getInvocationType() {
        return this.invocationType;
    }

    @NotNull
    public final LaunchType getLaunchType() {
        return this.launchType;
    }

    public int hashCode() {
        String str = this.invocationType;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        LaunchType launchType = this.launchType;
        if (launchType != null) {
            i = launchType.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DialogRequest(invocationType=");
        outline107.append(this.invocationType);
        outline107.append(", launchType=");
        outline107.append(this.launchType);
        outline107.append(")");
        return outline107.toString();
    }
}
