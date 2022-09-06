package com.amazon.photos.discovery.internal.model;

import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Digest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/amazon/photos/discovery/internal/model/Digest;", "", SierraContentProviderContract.MD5_VALUE, "", "visualDigest", "(Ljava/lang/String;Ljava/lang/String;)V", "getMd5", "()Ljava/lang/String;", "getVisualDigest", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Digest {
    @Nullable
    private final String md5;
    @Nullable
    private final String visualDigest;

    public Digest() {
        this(null, null, 3, null);
    }

    public Digest(@Nullable String str, @Nullable String str2) {
        this.md5 = str;
        this.visualDigest = str2;
    }

    public static /* synthetic */ Digest copy$default(Digest digest, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = digest.md5;
        }
        if ((i & 2) != 0) {
            str2 = digest.visualDigest;
        }
        return digest.copy(str, str2);
    }

    @Nullable
    public final String component1() {
        return this.md5;
    }

    @Nullable
    public final String component2() {
        return this.visualDigest;
    }

    @NotNull
    public final Digest copy(@Nullable String str, @Nullable String str2) {
        return new Digest(str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Digest)) {
                return false;
            }
            Digest digest = (Digest) obj;
            return Intrinsics.areEqual(this.md5, digest.md5) && Intrinsics.areEqual(this.visualDigest, digest.visualDigest);
        }
        return true;
    }

    @Nullable
    public final String getMd5() {
        return this.md5;
    }

    @Nullable
    public final String getVisualDigest() {
        return this.visualDigest;
    }

    public int hashCode() {
        String str = this.md5;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.visualDigest;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Digest(md5=");
        outline107.append(this.md5);
        outline107.append(", visualDigest=");
        return GeneratedOutlineSupport1.outline91(outline107, this.visualDigest, ")");
    }

    public /* synthetic */ Digest(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
    }
}
