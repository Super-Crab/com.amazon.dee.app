package io.ktor.client.utils;

import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
/* compiled from: Content.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/client/utils/EmptyContent;", "Lio/ktor/http/content/OutgoingContent$NoContent;", "()V", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class EmptyContent extends OutgoingContent.NoContent {
    public static final EmptyContent INSTANCE = new EmptyContent();

    private EmptyContent() {
    }
}
