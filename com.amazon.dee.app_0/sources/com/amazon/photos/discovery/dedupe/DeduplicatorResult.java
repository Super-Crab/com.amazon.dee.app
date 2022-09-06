package com.amazon.photos.discovery.dedupe;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DeduplicatorResult.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/amazon/photos/discovery/dedupe/DeduplicatorResult;", "", "itemsModified", "", "stageName", "", "(ILjava/lang/String;)V", "getItemsModified", "()I", "getStageName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DeduplicatorResult {
    private final int itemsModified;
    @NotNull
    private final String stageName;

    public DeduplicatorResult(int i, @NotNull String stageName) {
        Intrinsics.checkParameterIsNotNull(stageName, "stageName");
        this.itemsModified = i;
        this.stageName = stageName;
    }

    public static /* synthetic */ DeduplicatorResult copy$default(DeduplicatorResult deduplicatorResult, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = deduplicatorResult.itemsModified;
        }
        if ((i2 & 2) != 0) {
            str = deduplicatorResult.stageName;
        }
        return deduplicatorResult.copy(i, str);
    }

    public final int component1() {
        return this.itemsModified;
    }

    @NotNull
    public final String component2() {
        return this.stageName;
    }

    @NotNull
    public final DeduplicatorResult copy(int i, @NotNull String stageName) {
        Intrinsics.checkParameterIsNotNull(stageName, "stageName");
        return new DeduplicatorResult(i, stageName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DeduplicatorResult)) {
                return false;
            }
            DeduplicatorResult deduplicatorResult = (DeduplicatorResult) obj;
            return this.itemsModified == deduplicatorResult.itemsModified && Intrinsics.areEqual(this.stageName, deduplicatorResult.stageName);
        }
        return true;
    }

    public final int getItemsModified() {
        return this.itemsModified;
    }

    @NotNull
    public final String getStageName() {
        return this.stageName;
    }

    public int hashCode() {
        int i = this.itemsModified * 31;
        String str = this.stageName;
        return i + (str != null ? str.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeduplicatorResult(itemsModified=");
        outline107.append(this.itemsModified);
        outline107.append(", stageName=");
        return GeneratedOutlineSupport1.outline91(outline107, this.stageName, ")");
    }
}
