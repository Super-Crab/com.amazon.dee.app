package io.ktor.util;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.Input;
import org.jetbrains.annotations.NotNull;
/* compiled from: InputJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007¨\u0006\u0003"}, d2 = {"asStream", "Ljava/io/InputStream;", "Lkotlinx/io/core/Input;", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class InputJvmKt {
    @KtorExperimentalAPI
    @NotNull
    public static final InputStream asStream(@NotNull final Input receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new InputStream() { // from class: io.ktor.util.InputJvmKt$asStream$1
            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                Input.this.close();
            }

            @Override // java.io.InputStream
            public int read() {
                return Input.this.tryPeek();
            }

            @Override // java.io.InputStream
            public long skip(long j) {
                return Input.this.discard(j);
            }

            @Override // java.io.InputStream
            public int read(@NotNull byte[] buffer, int i, int i2) {
                Intrinsics.checkParameterIsNotNull(buffer, "buffer");
                if (Input.this.getEndOfInput()) {
                    return -1;
                }
                return Input.this.readAvailable(buffer, i, i2);
            }
        };
    }
}
