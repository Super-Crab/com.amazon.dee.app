package com.amazon.alexa.accessoryclient.common.datatransfer;

import com.amazon.alexa.accessory.io.Source;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: IpcSource.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000eH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/datatransfer/IpcSource;", "Lcom/amazon/alexa/accessory/io/Source;", "readFileDescriptor", "Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorWrapper;", "(Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorWrapper;)V", "inputStream", "Ljava/io/InputStream;", "getInputStream", "()Ljava/io/InputStream;", "inputStream$delegate", "Lkotlin/Lazy;", "close", "", "read", "", "buffer", "", "offset", "count", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class IpcSource implements Source {
    private final Lazy inputStream$delegate;

    public IpcSource(@NotNull ParcelFileDescriptorWrapper readFileDescriptor) {
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(readFileDescriptor, "readFileDescriptor");
        lazy = LazyKt__LazyJVMKt.lazy(new IpcSource$inputStream$2(readFileDescriptor));
        this.inputStream$delegate = lazy;
    }

    private final InputStream getInputStream() {
        return (InputStream) this.inputStream$delegate.getValue();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        getInputStream().close();
    }

    @Override // com.amazon.alexa.accessory.io.Source
    public int read(@NotNull byte[] buffer, int i, int i2) throws IOException {
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        return getInputStream().read(buffer, i, i2);
    }
}
