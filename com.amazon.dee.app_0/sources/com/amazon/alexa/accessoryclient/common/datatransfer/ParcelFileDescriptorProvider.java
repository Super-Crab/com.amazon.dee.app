package com.amazon.alexa.accessoryclient.common.datatransfer;

import java.io.IOException;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: ParcelFileDescriptorProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0015\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003H&¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorProvider;", "", "createReliableSocketPair", "", "Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorWrapper;", "()[Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorWrapper;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface ParcelFileDescriptorProvider {
    @NotNull
    ParcelFileDescriptorWrapper[] createReliableSocketPair() throws IOException;
}
