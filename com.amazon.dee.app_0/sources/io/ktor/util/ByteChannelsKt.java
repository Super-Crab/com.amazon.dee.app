package io.ktor.util;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.io.ByteChannel;
import kotlinx.coroutines.io.ByteChannelKt;
import kotlinx.coroutines.io.ByteReadChannel;
import org.jetbrains.annotations.NotNull;
/* compiled from: ByteChannels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"CHUNK_BUFFER_SIZE", "", "split", "Lkotlin/Pair;", "Lkotlinx/coroutines/io/ByteReadChannel;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ByteChannelsKt {
    private static final long CHUNK_BUFFER_SIZE = 4096;

    @KtorExperimentalAPI
    @NotNull
    public static final Pair<ByteReadChannel, ByteReadChannel> split(@NotNull ByteReadChannel receiver$0, @NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "coroutineScope");
        ByteChannel ByteChannel = ByteChannelKt.ByteChannel(true);
        ByteChannel ByteChannel2 = ByteChannelKt.ByteChannel(true);
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new ByteChannelsKt$split$1(receiver$0, ByteChannel, ByteChannel2, null), 3, null);
        return TuplesKt.to(ByteChannel, ByteChannel2);
    }
}
