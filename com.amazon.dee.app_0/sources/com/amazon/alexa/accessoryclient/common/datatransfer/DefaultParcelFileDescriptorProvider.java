package com.amazon.alexa.accessoryclient.common.datatransfer;

import android.os.ParcelFileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DefaultParcelFileDescriptorProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/datatransfer/DefaultParcelFileDescriptorProvider;", "Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorProvider;", "()V", "createReliableSocketPair", "", "Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorWrapper;", "()[Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorWrapper;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DefaultParcelFileDescriptorProvider implements ParcelFileDescriptorProvider {
    @Override // com.amazon.alexa.accessoryclient.common.datatransfer.ParcelFileDescriptorProvider
    @NotNull
    public ParcelFileDescriptorWrapper[] createReliableSocketPair() throws IOException {
        ParcelFileDescriptor[] createReliableSocketPair = ParcelFileDescriptor.createReliableSocketPair();
        Intrinsics.checkExpressionValueIsNotNull(createReliableSocketPair, "ParcelFileDescriptor.createReliableSocketPair()");
        ArrayList arrayList = new ArrayList(createReliableSocketPair.length);
        for (ParcelFileDescriptor parcelFileDescriptor : createReliableSocketPair) {
            arrayList.add(new DefaultParcelFileDescriptorWrapper(parcelFileDescriptor));
        }
        Object[] array = arrayList.toArray(new ParcelFileDescriptorWrapper[0]);
        if (array != null) {
            return (ParcelFileDescriptorWrapper[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
