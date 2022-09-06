package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import com.amazon.alexa.accessoryclient.common.datatransfer.DefaultParcelFileDescriptorWrapper;
import com.amazon.alexa.accessoryclient.common.datatransfer.ParcelFileDescriptorWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DefaultParcelFileDescriptorWrapperTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/DefaultParcelFileDescriptorWrapperTransformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorWrapper;", "()V", "PARCEL_FILE_DESCRIPTOR_KEY", "", "fromBundle", "Lcom/amazon/alexa/accessoryclient/common/datatransfer/DefaultParcelFileDescriptorWrapper;", "bundle", "Landroid/os/Bundle;", "toBundle", "t", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DefaultParcelFileDescriptorWrapperTransformer implements BundleTransformer<ParcelFileDescriptorWrapper> {
    public static final DefaultParcelFileDescriptorWrapperTransformer INSTANCE = new DefaultParcelFileDescriptorWrapperTransformer();
    private static final String PARCEL_FILE_DESCRIPTOR_KEY = "fd";

    private DefaultParcelFileDescriptorWrapperTransformer() {
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle */
    public ParcelFileDescriptorWrapper mo568fromBundle(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        return new DefaultParcelFileDescriptorWrapper((ParcelFileDescriptor) bundle.getParcelable(PARCEL_FILE_DESCRIPTOR_KEY));
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    public Bundle toBundle(@NotNull ParcelFileDescriptorWrapper t) {
        Intrinsics.checkParameterIsNotNull(t, "t");
        Bundle bundle = new Bundle();
        ParcelFileDescriptor parcelFileDescriptor = ((DefaultParcelFileDescriptorWrapper) t).getParcelFileDescriptor();
        if (parcelFileDescriptor != null) {
            bundle.putParcelable(PARCEL_FILE_DESCRIPTOR_KEY, parcelFileDescriptor);
        }
        return bundle;
    }
}
