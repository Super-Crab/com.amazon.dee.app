package com.amazon.alexa.accessoryclient.common.query.response;

import android.os.Bundle;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BooleanResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/response/BooleanResponse;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "boolValue", "", "(Z)V", "getBoolValue", "()Z", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class BooleanResponse implements Query.Response<BooleanResponse> {
    private final boolean boolValue;
    @NotNull
    private final BundleTransformer<BooleanResponse> bundleTransformer = Transformer.INSTANCE;

    /* compiled from: BooleanResponse.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/response/BooleanResponse$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/response/BooleanResponse;", "()V", "BOOLEAN_KEY", "", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "booleanResponse", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<BooleanResponse> {
        private static final String BOOLEAN_KEY = "boolean";
        public static final Transformer INSTANCE = new Transformer();

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public BooleanResponse mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            return new BooleanResponse(bundle.getBoolean(BOOLEAN_KEY));
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull BooleanResponse booleanResponse) {
            Intrinsics.checkParameterIsNotNull(booleanResponse, "booleanResponse");
            Bundle bundle = new Bundle();
            bundle.putBoolean(BOOLEAN_KEY, booleanResponse.getBoolValue());
            return bundle;
        }
    }

    public BooleanResponse(boolean z) {
        this.boolValue = z;
    }

    public static /* synthetic */ BooleanResponse copy$default(BooleanResponse booleanResponse, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = booleanResponse.boolValue;
        }
        return booleanResponse.copy(z);
    }

    public final boolean component1() {
        return this.boolValue;
    }

    @NotNull
    public final BooleanResponse copy(boolean z) {
        return new BooleanResponse(z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof BooleanResponse) && this.boolValue == ((BooleanResponse) obj).boolValue;
        }
        return true;
    }

    public final boolean getBoolValue() {
        return this.boolValue;
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Response
    @NotNull
    public Bundle getBundle() {
        return Query.Response.DefaultImpls.getBundle(this);
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Response
    @NotNull
    public BundleTransformer<BooleanResponse> getBundleTransformer() {
        return this.bundleTransformer;
    }

    public int hashCode() {
        boolean z = this.boolValue;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline97(GeneratedOutlineSupport1.outline107("BooleanResponse(boolValue="), this.boolValue, ")");
    }
}
