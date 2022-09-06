package com.amazon.alexa.voice.tta.simba;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: GetFrictivePromptsAndDomainsApi.kt */
@Parcelize
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\rHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/PromptData;", "Landroid/os/Parcelable;", "promptId", "", "namespace", "(Ljava/lang/String;Ljava/lang/String;)V", "getNamespace", "()Ljava/lang/String;", "getPromptId", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class PromptData implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    @NotNull
    private final String namespace;
    @NotNull
    private final String promptId;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object createFromParcel(@NotNull Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new PromptData(in.readString(), in.readString());
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object[] newArray(int i) {
            return new PromptData[i];
        }
    }

    public PromptData(@NotNull String promptId, @NotNull String namespace) {
        Intrinsics.checkParameterIsNotNull(promptId, "promptId");
        Intrinsics.checkParameterIsNotNull(namespace, "namespace");
        this.promptId = promptId;
        this.namespace = namespace;
    }

    public static /* synthetic */ PromptData copy$default(PromptData promptData, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = promptData.promptId;
        }
        if ((i & 2) != 0) {
            str2 = promptData.namespace;
        }
        return promptData.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.promptId;
    }

    @NotNull
    public final String component2() {
        return this.namespace;
    }

    @NotNull
    public final PromptData copy(@NotNull String promptId, @NotNull String namespace) {
        Intrinsics.checkParameterIsNotNull(promptId, "promptId");
        Intrinsics.checkParameterIsNotNull(namespace, "namespace");
        return new PromptData(promptId, namespace);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof PromptData)) {
                return false;
            }
            PromptData promptData = (PromptData) obj;
            return Intrinsics.areEqual(this.promptId, promptData.promptId) && Intrinsics.areEqual(this.namespace, promptData.namespace);
        }
        return true;
    }

    @NotNull
    public final String getNamespace() {
        return this.namespace;
    }

    @NotNull
    public final String getPromptId() {
        return this.promptId;
    }

    public int hashCode() {
        String str = this.promptId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.namespace;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PromptData(promptId=");
        outline107.append(this.promptId);
        outline107.append(", namespace=");
        return GeneratedOutlineSupport1.outline91(outline107, this.namespace, ")");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.promptId);
        parcel.writeString(this.namespace);
    }
}
