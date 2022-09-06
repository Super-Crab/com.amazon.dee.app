package io.ktor.http.content;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.http.ApplicationResponsePropertiesKt;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Versions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\u0012\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u0019*\u00020\u0003H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001a"}, d2 = {"Lio/ktor/http/content/EntityTagVersion;", "Lio/ktor/http/content/Version;", "etag", "", "(Ljava/lang/String;)V", "getEtag", "()Ljava/lang/String;", "appendHeadersTo", "", "builder", "Lio/ktor/http/HeadersBuilder;", "check", "Lio/ktor/http/content/VersionCheckResult;", "requestHeaders", "Lio/ktor/http/Headers;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "parseMatchTag", "", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class EntityTagVersion implements Version {
    @NotNull
    private final String etag;

    public EntityTagVersion(@NotNull String etag) {
        Intrinsics.checkParameterIsNotNull(etag, "etag");
        this.etag = etag;
    }

    @NotNull
    public static /* synthetic */ EntityTagVersion copy$default(EntityTagVersion entityTagVersion, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = entityTagVersion.etag;
        }
        return entityTagVersion.copy(str);
    }

    private final Set<String> parseMatchTag(@NotNull String str) {
        int collectionSizeOrDefault;
        Set<String> set;
        String removePrefix;
        List<String> split = new Regex("\\s*,\\s*").split(str, 0);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(split, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (String str2 : split) {
            removePrefix = StringsKt__StringsKt.removePrefix(str2, (CharSequence) "W/");
            arrayList.add(removePrefix);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((String) obj).length() > 0) {
                arrayList2.add(obj);
            }
        }
        set = CollectionsKt___CollectionsKt.toSet(arrayList2);
        return set;
    }

    @Override // io.ktor.http.content.Version
    public void appendHeadersTo(@NotNull HeadersBuilder builder) {
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        ApplicationResponsePropertiesKt.etag(builder, this.etag);
    }

    @Override // io.ktor.http.content.Version
    @NotNull
    public VersionCheckResult check(@NotNull Headers requestHeaders) {
        Intrinsics.checkParameterIsNotNull(requestHeaders, "requestHeaders");
        String str = requestHeaders.get(HttpHeaders.INSTANCE.getIfNoneMatch());
        Set<String> set = null;
        Set<String> parseMatchTag = str != null ? parseMatchTag(str) : null;
        String str2 = requestHeaders.get(HttpHeaders.INSTANCE.getIfMatch());
        if (str2 != null) {
            set = parseMatchTag(str2);
        }
        if (parseMatchTag != null && parseMatchTag.contains(this.etag) && !parseMatchTag.contains("*")) {
            return VersionCheckResult.NOT_MODIFIED;
        }
        if (set != null && (!set.isEmpty()) && !set.contains(this.etag) && !set.contains("*")) {
            return VersionCheckResult.PRECONDITION_FAILED;
        }
        return VersionCheckResult.OK;
    }

    @NotNull
    public final String component1() {
        return this.etag;
    }

    @NotNull
    public final EntityTagVersion copy(@NotNull String etag) {
        Intrinsics.checkParameterIsNotNull(etag, "etag");
        return new EntityTagVersion(etag);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof EntityTagVersion) && Intrinsics.areEqual(this.etag, ((EntityTagVersion) obj).etag);
        }
        return true;
    }

    @NotNull
    public final String getEtag() {
        return this.etag;
    }

    public int hashCode() {
        String str = this.etag;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("EntityTagVersion(etag="), this.etag, ")");
    }
}
