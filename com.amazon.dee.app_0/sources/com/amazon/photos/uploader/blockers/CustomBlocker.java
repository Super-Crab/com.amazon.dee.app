package com.amazon.photos.uploader.blockers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Blocker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/amazon/photos/uploader/blockers/CustomBlocker;", "Lcom/amazon/photos/uploader/blockers/Blocker;", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CustomBlocker extends Blocker {
    @NotNull
    private final String name;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomBlocker(@NotNull String name) {
        super(name, null);
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
    }

    public static /* synthetic */ CustomBlocker copy$default(CustomBlocker customBlocker, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = customBlocker.getName();
        }
        return customBlocker.copy(str);
    }

    @NotNull
    public final String component1() {
        return getName();
    }

    @NotNull
    public final CustomBlocker copy(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new CustomBlocker(name);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof CustomBlocker) && Intrinsics.areEqual(getName(), ((CustomBlocker) obj).getName());
        }
        return true;
    }

    @Override // com.amazon.photos.uploader.blockers.Blocker
    @NotNull
    public String getName() {
        return this.name;
    }

    public int hashCode() {
        String name = getName();
        if (name != null) {
            return name.hashCode();
        }
        return 0;
    }

    @Override // com.amazon.photos.uploader.blockers.Blocker
    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CustomBlocker(name=");
        outline107.append(getName());
        outline107.append(")");
        return outline107.toString();
    }
}
