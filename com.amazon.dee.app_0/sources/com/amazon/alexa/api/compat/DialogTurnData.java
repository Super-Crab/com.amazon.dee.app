package com.amazon.alexa.api.compat;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DialogTurnData.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00062\u00020\u0001:\u0002\u0005\u0006B\u0011\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/api/compat/DialogTurnData;", "", AccessoryMetricsConstants.DIALOG_TURN_ID, "", "(Ljava/lang/String;)V", "Builder", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DialogTurnData {
    public static final Companion Companion = new Companion(null);

    /* compiled from: DialogTurnData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\tJ\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0010\u0010\u0002\u001a\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/api/compat/DialogTurnData$Builder;", "", AccessoryMetricsConstants.DIALOG_TURN_ID, "", "(Ljava/lang/String;)V", "getDialogTurnId", "()Ljava/lang/String;", "setDialogTurnId", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/alexa/api/compat/DialogTurnData;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Builder {
        @Nullable
        private String dialogTurnId;

        public Builder() {
            this(null, 1, null);
        }

        public Builder(@Nullable String str) {
            this.dialogTurnId = str;
        }

        public static /* synthetic */ Builder copy$default(Builder builder, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = builder.dialogTurnId;
            }
            return builder.copy(str);
        }

        @NotNull
        public final DialogTurnData build() {
            return new DialogTurnData(this.dialogTurnId, null);
        }

        @Nullable
        public final String component1() {
            return this.dialogTurnId;
        }

        @NotNull
        public final Builder copy(@Nullable String str) {
            return new Builder(str);
        }

        @NotNull
        public final Builder dialogTurnId(@Nullable String str) {
            this.dialogTurnId = str;
            return this;
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof Builder) && Intrinsics.areEqual(this.dialogTurnId, ((Builder) obj).dialogTurnId);
            }
            return true;
        }

        @Nullable
        public final String getDialogTurnId() {
            return this.dialogTurnId;
        }

        public int hashCode() {
            String str = this.dialogTurnId;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public final void setDialogTurnId(@Nullable String str) {
            this.dialogTurnId = str;
        }

        @NotNull
        public String toString() {
            return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Builder(dialogTurnId="), this.dialogTurnId, ")");
        }

        public /* synthetic */ Builder(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str);
        }
    }

    /* compiled from: DialogTurnData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/api/compat/DialogTurnData$Companion;", "", "()V", "convertToCompat", "Lcom/amazon/alexa/api/compat/DialogTurnData;", "noncompatDialogTurnData", "Lcom/amazon/alexa/api/DialogTurnData;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final DialogTurnData convertToCompat(@NotNull com.amazon.alexa.api.DialogTurnData noncompatDialogTurnData) {
            Intrinsics.checkParameterIsNotNull(noncompatDialogTurnData, "noncompatDialogTurnData");
            return new Builder(null, 1, null).dialogTurnId(noncompatDialogTurnData.getDialogTurnId()).build();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private DialogTurnData(String str) {
    }

    public /* synthetic */ DialogTurnData(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }
}
