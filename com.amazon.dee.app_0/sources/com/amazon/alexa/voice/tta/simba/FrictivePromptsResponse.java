package com.amazon.alexa.voice.tta.simba;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: GetFrictivePromptsAndDomainsApi.kt */
@Parcelize
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000bHÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/FrictivePromptsResponse;", "Landroid/os/Parcelable;", "frictivePrompts", "", "Lcom/amazon/alexa/voice/tta/simba/PromptData;", "(Ljava/util/List;)V", "getFrictivePrompts", "()Ljava/util/List;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class FrictivePromptsResponse implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    @NotNull
    private final List<PromptData> frictivePrompts;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object createFromParcel(@NotNull Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            int readInt = in.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            while (readInt != 0) {
                arrayList.add((PromptData) PromptData.CREATOR.createFromParcel(in));
                readInt--;
            }
            return new FrictivePromptsResponse(arrayList);
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object[] newArray(int i) {
            return new FrictivePromptsResponse[i];
        }
    }

    public FrictivePromptsResponse(@NotNull List<PromptData> frictivePrompts) {
        Intrinsics.checkParameterIsNotNull(frictivePrompts, "frictivePrompts");
        this.frictivePrompts = frictivePrompts;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FrictivePromptsResponse copy$default(FrictivePromptsResponse frictivePromptsResponse, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = frictivePromptsResponse.frictivePrompts;
        }
        return frictivePromptsResponse.copy(list);
    }

    @NotNull
    public final List<PromptData> component1() {
        return this.frictivePrompts;
    }

    @NotNull
    public final FrictivePromptsResponse copy(@NotNull List<PromptData> frictivePrompts) {
        Intrinsics.checkParameterIsNotNull(frictivePrompts, "frictivePrompts");
        return new FrictivePromptsResponse(frictivePrompts);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof FrictivePromptsResponse) && Intrinsics.areEqual(this.frictivePrompts, ((FrictivePromptsResponse) obj).frictivePrompts);
        }
        return true;
    }

    @NotNull
    public final List<PromptData> getFrictivePrompts() {
        return this.frictivePrompts;
    }

    public int hashCode() {
        List<PromptData> list = this.frictivePrompts;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("FrictivePromptsResponse(frictivePrompts="), this.frictivePrompts, ")");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        List<PromptData> list = this.frictivePrompts;
        parcel.writeInt(list.size());
        for (PromptData promptData : list) {
            promptData.writeToParcel(parcel, 0);
        }
    }
}
