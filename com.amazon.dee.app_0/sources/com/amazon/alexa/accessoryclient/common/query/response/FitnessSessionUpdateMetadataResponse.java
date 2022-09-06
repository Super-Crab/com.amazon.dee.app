package com.amazon.alexa.accessoryclient.common.query.response;

import android.os.Bundle;
import com.amazon.alexa.accessoryclient.common.api.FitnessSessionUpdateMetadata;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.FitnessSessionUpdateMetadataTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessSessionUpdateMetadataResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/response/FitnessSessionUpdateMetadataResponse;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "fitnessSessionUpdateMetadata", "Lcom/amazon/alexa/accessoryclient/common/api/FitnessSessionUpdateMetadata;", "(Lcom/amazon/alexa/accessoryclient/common/api/FitnessSessionUpdateMetadata;)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getFitnessSessionUpdateMetadata", "()Lcom/amazon/alexa/accessoryclient/common/api/FitnessSessionUpdateMetadata;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class FitnessSessionUpdateMetadataResponse implements Query.Response<FitnessSessionUpdateMetadataResponse> {
    @NotNull
    private final BundleTransformer<FitnessSessionUpdateMetadataResponse> bundleTransformer;
    @NotNull
    private final FitnessSessionUpdateMetadata fitnessSessionUpdateMetadata;

    /* compiled from: FitnessSessionUpdateMetadataResponse.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/response/FitnessSessionUpdateMetadataResponse$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/response/FitnessSessionUpdateMetadataResponse;", "()V", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "fitnessSessionUpdateMetadataResponse", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<FitnessSessionUpdateMetadataResponse> {
        public static final Transformer INSTANCE = new Transformer();

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public FitnessSessionUpdateMetadataResponse mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            return new FitnessSessionUpdateMetadataResponse(FitnessSessionUpdateMetadataTransformer.INSTANCE.mo568fromBundle(bundle));
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull FitnessSessionUpdateMetadataResponse fitnessSessionUpdateMetadataResponse) {
            Intrinsics.checkParameterIsNotNull(fitnessSessionUpdateMetadataResponse, "fitnessSessionUpdateMetadataResponse");
            return FitnessSessionUpdateMetadataTransformer.INSTANCE.toBundle(fitnessSessionUpdateMetadataResponse.getFitnessSessionUpdateMetadata());
        }
    }

    public FitnessSessionUpdateMetadataResponse(@NotNull FitnessSessionUpdateMetadata fitnessSessionUpdateMetadata) {
        Intrinsics.checkParameterIsNotNull(fitnessSessionUpdateMetadata, "fitnessSessionUpdateMetadata");
        this.fitnessSessionUpdateMetadata = fitnessSessionUpdateMetadata;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ FitnessSessionUpdateMetadataResponse copy$default(FitnessSessionUpdateMetadataResponse fitnessSessionUpdateMetadataResponse, FitnessSessionUpdateMetadata fitnessSessionUpdateMetadata, int i, Object obj) {
        if ((i & 1) != 0) {
            fitnessSessionUpdateMetadata = fitnessSessionUpdateMetadataResponse.fitnessSessionUpdateMetadata;
        }
        return fitnessSessionUpdateMetadataResponse.copy(fitnessSessionUpdateMetadata);
    }

    @NotNull
    public final FitnessSessionUpdateMetadata component1() {
        return this.fitnessSessionUpdateMetadata;
    }

    @NotNull
    public final FitnessSessionUpdateMetadataResponse copy(@NotNull FitnessSessionUpdateMetadata fitnessSessionUpdateMetadata) {
        Intrinsics.checkParameterIsNotNull(fitnessSessionUpdateMetadata, "fitnessSessionUpdateMetadata");
        return new FitnessSessionUpdateMetadataResponse(fitnessSessionUpdateMetadata);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof FitnessSessionUpdateMetadataResponse) && Intrinsics.areEqual(this.fitnessSessionUpdateMetadata, ((FitnessSessionUpdateMetadataResponse) obj).fitnessSessionUpdateMetadata);
        }
        return true;
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Response
    @NotNull
    public Bundle getBundle() {
        return Query.Response.DefaultImpls.getBundle(this);
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Response
    @NotNull
    public BundleTransformer<FitnessSessionUpdateMetadataResponse> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final FitnessSessionUpdateMetadata getFitnessSessionUpdateMetadata() {
        return this.fitnessSessionUpdateMetadata;
    }

    public int hashCode() {
        FitnessSessionUpdateMetadata fitnessSessionUpdateMetadata = this.fitnessSessionUpdateMetadata;
        if (fitnessSessionUpdateMetadata != null) {
            return fitnessSessionUpdateMetadata.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FitnessSessionUpdateMetadataResponse(fitnessSessionUpdateMetadata=");
        outline107.append(this.fitnessSessionUpdateMetadata);
        outline107.append(")");
        return outline107.toString();
    }
}
