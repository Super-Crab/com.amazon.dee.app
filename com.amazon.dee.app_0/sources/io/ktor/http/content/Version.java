package io.ktor.http.content;

import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: Versions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lio/ktor/http/content/Version;", "", "appendHeadersTo", "", "builder", "Lio/ktor/http/HeadersBuilder;", "check", "Lio/ktor/http/content/VersionCheckResult;", "requestHeaders", "Lio/ktor/http/Headers;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface Version {
    void appendHeadersTo(@NotNull HeadersBuilder headersBuilder);

    @NotNull
    VersionCheckResult check(@NotNull Headers headers);
}
