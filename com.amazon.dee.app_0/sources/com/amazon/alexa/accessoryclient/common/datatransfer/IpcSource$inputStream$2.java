package com.amazon.alexa.accessoryclient.common.datatransfer;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: IpcSource.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/io/InputStream;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class IpcSource$inputStream$2 extends Lambda implements Function0<InputStream> {
    final /* synthetic */ ParcelFileDescriptorWrapper $readFileDescriptor;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IpcSource$inputStream$2(ParcelFileDescriptorWrapper parcelFileDescriptorWrapper) {
        super(0);
        this.$readFileDescriptor = parcelFileDescriptorWrapper;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke */
    public final InputStream mo12560invoke() {
        return this.$readFileDescriptor.getAutoCloseInputStream();
    }
}
