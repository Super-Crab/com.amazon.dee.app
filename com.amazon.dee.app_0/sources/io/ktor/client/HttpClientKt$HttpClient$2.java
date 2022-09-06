package io.ktor.client;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/client/engine/HttpClientEngineConfig;", "Lio/ktor/client/HttpClientConfig;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class HttpClientKt$HttpClient$2 extends Lambda implements Function1<HttpClientConfig<T>, Unit> {
    public static final HttpClientKt$HttpClient$2 INSTANCE = new HttpClientKt$HttpClient$2();

    HttpClientKt$HttpClient$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Object obj) {
        invoke((HttpClientConfig) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull HttpClientConfig<T> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
    }
}
