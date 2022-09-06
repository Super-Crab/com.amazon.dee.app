package com.amazon.alexa.accessoryclient.common.datatransfer;

import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: ParcelFileDescriptorWrapper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorWrapper;", "", "closeWithError", "", "msg", "", "getAutoCloseInputStream", "Ljava/io/InputStream;", "getAutoCloseOutputStream", "Ljava/io/OutputStream;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface ParcelFileDescriptorWrapper {
    void closeWithError(@NotNull String str);

    @NotNull
    InputStream getAutoCloseInputStream();

    @NotNull
    OutputStream getAutoCloseOutputStream();
}
