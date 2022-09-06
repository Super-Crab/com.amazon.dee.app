package io.ktor.http.content;

import io.ktor.util.AttributeKey;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Versions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001d\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"4\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002*\u00020\b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"VersionListProperty", "Lio/ktor/util/AttributeKey;", "", "Lio/ktor/http/content/Version;", "getVersionListProperty", "()Lio/ktor/util/AttributeKey;", "value", "versions", "Lio/ktor/http/content/OutgoingContent;", "getVersions", "(Lio/ktor/http/content/OutgoingContent;)Ljava/util/List;", "setVersions", "(Lio/ktor/http/content/OutgoingContent;Ljava/util/List;)V", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class VersionsKt {
    @NotNull
    private static final AttributeKey<List<Version>> VersionListProperty = new AttributeKey<>("VersionList");

    @NotNull
    public static final AttributeKey<List<Version>> getVersionListProperty() {
        return VersionListProperty;
    }

    @NotNull
    public static final List<Version> getVersions(@NotNull OutgoingContent receiver$0) {
        List<Version> emptyList;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        List<Version> list = (List) receiver$0.getProperty(VersionListProperty);
        if (list != null) {
            return list;
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    public static final void setVersions(@NotNull OutgoingContent receiver$0, @NotNull List<? extends Version> value) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(value, "value");
        receiver$0.setProperty(VersionListProperty, value);
    }
}
