package io.ktor.client;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.client.engine.HttpClientEngineConfig;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpClientConfig.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/client/engine/HttpClientEngineConfig;", "invoke", "(Lio/ktor/client/engine/HttpClientEngineConfig;)V"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class HttpClientConfig$engine$1 extends Lambda implements Function1<T, Unit> {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ Function1 $oldConfig;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpClientConfig$engine$1(Function1 function1, Function1 function12) {
        super(1);
        this.$oldConfig = function1;
        this.$block = function12;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Object obj) {
        invoke((HttpClientEngineConfig) obj);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)V */
    public final void invoke(@NotNull HttpClientEngineConfig receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        this.$oldConfig.mo12165invoke(receiver$0);
        this.$block.mo12165invoke(receiver$0);
    }
}
