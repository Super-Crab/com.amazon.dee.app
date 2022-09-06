package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import com.amazon.alexa.accessory.repositories.device.DeviceFeature;
import com.amazon.alexa.accessory.repositories.device.SubFeature;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DeviceFeatureTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00070\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/DeviceFeatureTransformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessory/repositories/device/DeviceFeature;", "()V", "ID_KEY", "", "ListTransformer", "", "getListTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "SUB_FEATURES_KEY", "VERSION_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "t", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DeviceFeatureTransformer implements BundleTransformer<DeviceFeature> {
    private static final String ID_KEY = "id";
    public static final DeviceFeatureTransformer INSTANCE = new DeviceFeatureTransformer();
    @NotNull
    private static final BundleTransformer<List<DeviceFeature>> ListTransformer = new ListTransformer(INSTANCE);
    private static final String SUB_FEATURES_KEY = "sub_features";
    private static final String VERSION_KEY = "version";

    private DeviceFeatureTransformer() {
    }

    @NotNull
    public final BundleTransformer<List<DeviceFeature>> getListTransformer() {
        return ListTransformer;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle */
    public DeviceFeature mo568fromBundle(@NotNull Bundle bundle) {
        Bundle it2;
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        List<SubFeature> list = null;
        Integer valueOf = bundle.containsKey("version") ? Integer.valueOf(bundle.getInt("version")) : null;
        if (bundle.containsKey(SUB_FEATURES_KEY) && (it2 = bundle.getBundle(SUB_FEATURES_KEY)) != null) {
            BundleTransformer<List<SubFeature>> listTransformer = SubFeatureTransformer.INSTANCE.getListTransformer();
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            list = listTransformer.mo568fromBundle(it2);
        }
        return new DeviceFeature(bundle.getInt("id"), valueOf, list);
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    public Bundle toBundle(@NotNull DeviceFeature t) {
        Intrinsics.checkParameterIsNotNull(t, "t");
        Bundle bundle = new Bundle();
        bundle.putInt("id", t.getId());
        Integer it2 = t.getVersion();
        if (it2 != null) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            bundle.putInt("version", it2.intValue());
        }
        List<SubFeature> subFeatures = t.getSubFeatures();
        if (subFeatures != null) {
            bundle.putBundle(SUB_FEATURES_KEY, SubFeatureTransformer.INSTANCE.getListTransformer().toBundle(subFeatures));
        }
        return bundle;
    }
}
