package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessory.protocol.Hearing;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.MediaEnhancementCorrectionAmountTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SetMediaEnhancementCorrectionAmountRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0019B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/SetMediaEnhancementCorrectionAmountRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "identifier", "", "correctionAmount", "Lcom/amazon/alexa/accessory/protocol/Hearing$MediaEnhancementCorrectionAmount;", "(Ljava/lang/String;Lcom/amazon/alexa/accessory/protocol/Hearing$MediaEnhancementCorrectionAmount;)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getCorrectionAmount", "()Lcom/amazon/alexa/accessory/protocol/Hearing$MediaEnhancementCorrectionAmount;", "getIdentifier", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SetMediaEnhancementCorrectionAmountRequest implements Query.Request<SetMediaEnhancementCorrectionAmountRequest> {
    @NotNull
    private final BundleTransformer<SetMediaEnhancementCorrectionAmountRequest> bundleTransformer;
    @NotNull
    private final Hearing.MediaEnhancementCorrectionAmount correctionAmount;
    @NotNull
    private final String identifier;

    /* compiled from: SetMediaEnhancementCorrectionAmountRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/SetMediaEnhancementCorrectionAmountRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/SetMediaEnhancementCorrectionAmountRequest;", "()V", "CORRECTION_AMOUNT_KEY", "", "IDENTIFIER_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "setMediaEnhancementCorrectionAmountRequest", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<SetMediaEnhancementCorrectionAmountRequest> {
        private static final String CORRECTION_AMOUNT_KEY = "correctionAmount";
        private static final String IDENTIFIER_KEY = "identifier";
        public static final Transformer INSTANCE = new Transformer();

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public SetMediaEnhancementCorrectionAmountRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            String string = bundle.getString("identifier");
            if (string == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(IDENTIFIER_KEY)!!");
            MediaEnhancementCorrectionAmountTransformer mediaEnhancementCorrectionAmountTransformer = MediaEnhancementCorrectionAmountTransformer.INSTANCE;
            Bundle bundle2 = bundle.getBundle(CORRECTION_AMOUNT_KEY);
            if (bundle2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(CORRECTION_AMOUNT_KEY)!!");
            return new SetMediaEnhancementCorrectionAmountRequest(string, mediaEnhancementCorrectionAmountTransformer.mo568fromBundle(bundle2));
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull SetMediaEnhancementCorrectionAmountRequest setMediaEnhancementCorrectionAmountRequest) {
            Intrinsics.checkParameterIsNotNull(setMediaEnhancementCorrectionAmountRequest, "setMediaEnhancementCorrectionAmountRequest");
            Bundle bundle = new Bundle();
            bundle.putString("identifier", setMediaEnhancementCorrectionAmountRequest.getIdentifier());
            bundle.putBundle(CORRECTION_AMOUNT_KEY, MediaEnhancementCorrectionAmountTransformer.INSTANCE.toBundle(setMediaEnhancementCorrectionAmountRequest.getCorrectionAmount()));
            return bundle;
        }
    }

    public SetMediaEnhancementCorrectionAmountRequest(@NotNull String identifier, @NotNull Hearing.MediaEnhancementCorrectionAmount correctionAmount) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(correctionAmount, "correctionAmount");
        this.identifier = identifier;
        this.correctionAmount = correctionAmount;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ SetMediaEnhancementCorrectionAmountRequest copy$default(SetMediaEnhancementCorrectionAmountRequest setMediaEnhancementCorrectionAmountRequest, String str, Hearing.MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount, int i, Object obj) {
        if ((i & 1) != 0) {
            str = setMediaEnhancementCorrectionAmountRequest.identifier;
        }
        if ((i & 2) != 0) {
            mediaEnhancementCorrectionAmount = setMediaEnhancementCorrectionAmountRequest.correctionAmount;
        }
        return setMediaEnhancementCorrectionAmountRequest.copy(str, mediaEnhancementCorrectionAmount);
    }

    @NotNull
    public final String component1() {
        return this.identifier;
    }

    @NotNull
    public final Hearing.MediaEnhancementCorrectionAmount component2() {
        return this.correctionAmount;
    }

    @NotNull
    public final SetMediaEnhancementCorrectionAmountRequest copy(@NotNull String identifier, @NotNull Hearing.MediaEnhancementCorrectionAmount correctionAmount) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(correctionAmount, "correctionAmount");
        return new SetMediaEnhancementCorrectionAmountRequest(identifier, correctionAmount);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SetMediaEnhancementCorrectionAmountRequest)) {
                return false;
            }
            SetMediaEnhancementCorrectionAmountRequest setMediaEnhancementCorrectionAmountRequest = (SetMediaEnhancementCorrectionAmountRequest) obj;
            return Intrinsics.areEqual(this.identifier, setMediaEnhancementCorrectionAmountRequest.identifier) && Intrinsics.areEqual(this.correctionAmount, setMediaEnhancementCorrectionAmountRequest.correctionAmount);
        }
        return true;
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public Bundle getBundle() {
        return Query.Request.DefaultImpls.getBundle(this);
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public BundleTransformer<SetMediaEnhancementCorrectionAmountRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final Hearing.MediaEnhancementCorrectionAmount getCorrectionAmount() {
        return this.correctionAmount;
    }

    @NotNull
    public final String getIdentifier() {
        return this.identifier;
    }

    public int hashCode() {
        String str = this.identifier;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Hearing.MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount = this.correctionAmount;
        if (mediaEnhancementCorrectionAmount != null) {
            i = mediaEnhancementCorrectionAmount.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SetMediaEnhancementCorrectionAmountRequest(identifier=");
        outline107.append(this.identifier);
        outline107.append(", correctionAmount=");
        outline107.append(this.correctionAmount);
        outline107.append(")");
        return outline107.toString();
    }
}
