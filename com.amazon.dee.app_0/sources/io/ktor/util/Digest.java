package io.ktor.util;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Crypto.kt */
@InternalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H¦\u0002J\b\u0010\b\u001a\u00020\u0006H&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lio/ktor/util/Digest;", "", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "plusAssign", "", "bytes", "reset", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface Digest {
    @Nullable
    Object build(@NotNull Continuation<? super byte[]> continuation);

    void plusAssign(@NotNull byte[] bArr);

    void reset();
}
