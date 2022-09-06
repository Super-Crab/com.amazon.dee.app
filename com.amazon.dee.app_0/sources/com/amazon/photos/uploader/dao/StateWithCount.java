package com.amazon.photos.uploader.dao;

import com.amazon.photos.uploader.UploadState;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StateWithCount.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/amazon/photos/uploader/dao/StateWithCount;", "", "state", "Lcom/amazon/photos/uploader/UploadState;", "count", "", "(Lcom/amazon/photos/uploader/UploadState;I)V", "getCount", "()I", "getState", "()Lcom/amazon/photos/uploader/UploadState;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class StateWithCount {
    private final int count;
    @NotNull
    private final UploadState state;

    public StateWithCount(@NotNull UploadState state, int i) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        this.state = state;
        this.count = i;
    }

    public static /* synthetic */ StateWithCount copy$default(StateWithCount stateWithCount, UploadState uploadState, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            uploadState = stateWithCount.state;
        }
        if ((i2 & 2) != 0) {
            i = stateWithCount.count;
        }
        return stateWithCount.copy(uploadState, i);
    }

    @NotNull
    public final UploadState component1() {
        return this.state;
    }

    public final int component2() {
        return this.count;
    }

    @NotNull
    public final StateWithCount copy(@NotNull UploadState state, int i) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        return new StateWithCount(state, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof StateWithCount)) {
                return false;
            }
            StateWithCount stateWithCount = (StateWithCount) obj;
            return Intrinsics.areEqual(this.state, stateWithCount.state) && this.count == stateWithCount.count;
        }
        return true;
    }

    public final int getCount() {
        return this.count;
    }

    @NotNull
    public final UploadState getState() {
        return this.state;
    }

    public int hashCode() {
        UploadState uploadState = this.state;
        return ((uploadState != null ? uploadState.hashCode() : 0) * 31) + this.count;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StateWithCount(state=");
        outline107.append(this.state);
        outline107.append(", count=");
        return GeneratedOutlineSupport1.outline86(outline107, this.count, ")");
    }
}
