package com.amazon.alexa.accessoryclient.common.datatransfer;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.DefaultParcelFileDescriptorWrapperTransformer;
import com.dee.app.metrics.MetricsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ParcelFileDescriptorProviderHolder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\u0004J\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\rJ\u001b\u0010\u000e\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0001¢\u0006\u0002\b\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorProviderHolder;", "", "()V", "Instance", "Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorProvider;", "TransformerInstance", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorWrapper;", MetricsConstants.Method.CACHE_GET, "getTransformer", "set", "", "parcelFileDescriptorProvider", "set$AlexaAccessoryAndroidClient_release", "setTransformer", "transformerInstance", "setTransformer$AlexaAccessoryAndroidClient_release", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ParcelFileDescriptorProviderHolder {
    public static final ParcelFileDescriptorProviderHolder INSTANCE = new ParcelFileDescriptorProviderHolder();
    private static BundleTransformer<ParcelFileDescriptorWrapper> TransformerInstance = DefaultParcelFileDescriptorWrapperTransformer.INSTANCE;
    private static ParcelFileDescriptorProvider Instance = new DefaultParcelFileDescriptorProvider();

    private ParcelFileDescriptorProviderHolder() {
    }

    @NotNull
    public final ParcelFileDescriptorProvider get() {
        return Instance;
    }

    @NotNull
    public final BundleTransformer<ParcelFileDescriptorWrapper> getTransformer() {
        return TransformerInstance;
    }

    @VisibleForTesting
    public final void set$AlexaAccessoryAndroidClient_release(@NotNull ParcelFileDescriptorProvider parcelFileDescriptorProvider) {
        Intrinsics.checkParameterIsNotNull(parcelFileDescriptorProvider, "parcelFileDescriptorProvider");
        Instance = parcelFileDescriptorProvider;
    }

    @VisibleForTesting
    public final void setTransformer$AlexaAccessoryAndroidClient_release(@NotNull BundleTransformer<ParcelFileDescriptorWrapper> transformerInstance) {
        Intrinsics.checkParameterIsNotNull(transformerInstance, "transformerInstance");
        TransformerInstance = transformerInstance;
    }
}
