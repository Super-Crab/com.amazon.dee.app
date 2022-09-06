package com.amazon.photos.uploader.blockers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Blocker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0000H\u0096\u0002J\b\u0010\n\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\f\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016¨\u0006\u0017"}, d2 = {"Lcom/amazon/photos/uploader/blockers/Blocker;", "", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "compareTo", "", "other", "toString", "Lcom/amazon/photos/uploader/blockers/MeteredConnectionBlocker;", "Lcom/amazon/photos/uploader/blockers/NoNetworkBlocker;", "Lcom/amazon/photos/uploader/blockers/QuotaExceededBlocker;", "Lcom/amazon/photos/uploader/blockers/TokenUnavailableBlocker;", "Lcom/amazon/photos/uploader/blockers/NotChargingBlocker;", "Lcom/amazon/photos/uploader/blockers/LowBatteryBlocker;", "Lcom/amazon/photos/uploader/blockers/BackoffBlocker;", "Lcom/amazon/photos/uploader/blockers/PauseBlocker;", "Lcom/amazon/photos/uploader/blockers/PowerSaverBlocker;", "Lcom/amazon/photos/uploader/blockers/NetworkPermissionBlocker;", "Lcom/amazon/photos/uploader/blockers/StoragePermissionBlocker;", "Lcom/amazon/photos/uploader/blockers/CustomBlocker;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class Blocker implements Comparable<Blocker> {
    @NotNull
    private final String name;

    private Blocker(String str) {
        this.name = str;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Blocker(name='");
        outline107.append(getName());
        outline107.append("')");
        return outline107.toString();
    }

    public /* synthetic */ Blocker(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull Blocker other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return getName().compareTo(other.getName());
    }
}
