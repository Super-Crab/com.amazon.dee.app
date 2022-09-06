package io.ktor.client.features;

import io.ktor.http.HttpStatusCode;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpRedirect.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"isRedirect", "", "Lio/ktor/http/HttpStatusCode;", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpRedirectKt {
    public static final /* synthetic */ boolean access$isRedirect(HttpStatusCode httpStatusCode) {
        return isRedirect(httpStatusCode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isRedirect(@NotNull HttpStatusCode httpStatusCode) {
        int value = httpStatusCode.getValue();
        return value == HttpStatusCode.Companion.getMovedPermanently().getValue() || value == HttpStatusCode.Companion.getFound().getValue() || value == HttpStatusCode.Companion.getTemporaryRedirect().getValue() || value == HttpStatusCode.Companion.getPermanentRedirect().getValue();
    }
}
