package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import com.amazon.alexa.accessory.protocol.Fitness;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: StopLiveFitnessDataTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/StopLiveFitnessDataTransformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessory/protocol/Fitness$StopLiveFitnessData;", "()V", "PROTO_KEY", "", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "stopLiveFitnessData", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class StopLiveFitnessDataTransformer implements BundleTransformer<Fitness.StopLiveFitnessData> {
    public static final StopLiveFitnessDataTransformer INSTANCE = new StopLiveFitnessDataTransformer();
    private static final String PROTO_KEY = "proto";

    private StopLiveFitnessDataTransformer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle */
    public Fitness.StopLiveFitnessData mo568fromBundle(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Fitness.StopLiveFitnessData parseFrom = Fitness.StopLiveFitnessData.parseFrom(bundle.getByteArray(PROTO_KEY));
        Intrinsics.checkExpressionValueIsNotNull(parseFrom, "Fitness.StopLiveFitnessD…rray(PROTO_KEY)\n        )");
        return parseFrom;
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    public Bundle toBundle(@NotNull Fitness.StopLiveFitnessData stopLiveFitnessData) {
        Intrinsics.checkParameterIsNotNull(stopLiveFitnessData, "stopLiveFitnessData");
        Bundle bundle = new Bundle();
        bundle.putByteArray(PROTO_KEY, stopLiveFitnessData.toByteArray());
        return bundle;
    }
}
