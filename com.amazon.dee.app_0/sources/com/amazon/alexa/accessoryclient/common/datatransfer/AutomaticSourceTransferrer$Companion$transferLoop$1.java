package com.amazon.alexa.accessoryclient.common.datatransfer;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.io.Source;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
/* compiled from: IpcSource.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class AutomaticSourceTransferrer$Companion$transferLoop$1 extends Lambda implements Function0<Integer> {
    final /* synthetic */ byte[] $buffer;
    final /* synthetic */ Ref.IntRef $read;
    final /* synthetic */ ParcelFileDescriptorWrapper $readFileDescriptor;
    final /* synthetic */ Source $source;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutomaticSourceTransferrer$Companion$transferLoop$1(Ref.IntRef intRef, Source source, byte[] bArr, ParcelFileDescriptorWrapper parcelFileDescriptorWrapper) {
        super(0);
        this.$read = intRef;
        this.$source = source;
        this.$buffer = bArr;
        this.$readFileDescriptor = parcelFileDescriptorWrapper;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ Integer mo12560invoke() {
        return Integer.valueOf(mo12560invoke());
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [int, java.lang.Integer] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public final Integer mo12560invoke() {
        try {
            this.$read.element = this.$source.read(this.$buffer, 0, this.$buffer.length);
            return this.$read.element;
        } catch (IOException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AutomaticSourceTransferrer: error in reading source ");
            outline107.append(this.$source);
            outline107.append(", aborting transfer and ");
            outline107.append("closing the created outputStream and the provided source");
            Logger.e(outline107.toString(), e);
            ParcelFileDescriptorWrapper parcelFileDescriptorWrapper = this.$readFileDescriptor;
            parcelFileDescriptorWrapper.closeWithError("AutomaticSourceTransferrer: encountered error in transfer: " + e);
            throw e;
        }
    }
}
