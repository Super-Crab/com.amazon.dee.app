package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import com.amazon.alexa.accessory.AccessorySessionOptions;
import com.amazon.alexa.accessory.capabilities.Capability;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessorySessionOptionsTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/AccessorySessionOptionsTransformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessory/AccessorySessionOptions;", "()V", "DEVICE_KNOWN_KEY", "", "SHOULD_FORCE_CONNECTION_KEY", "SHOULD_USE_UNESECURE_LE_CONNECTION_KEY", "SUPPORTED_CAPABILITIES_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "t", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccessorySessionOptionsTransformer implements BundleTransformer<AccessorySessionOptions> {
    private static final String DEVICE_KNOWN_KEY = "isDeviceKnown";
    public static final AccessorySessionOptionsTransformer INSTANCE = new AccessorySessionOptionsTransformer();
    private static final String SHOULD_FORCE_CONNECTION_KEY = "shouldForceConnection";
    private static final String SHOULD_USE_UNESECURE_LE_CONNECTION_KEY = "shouldUseUnsecureLeConnection";
    private static final String SUPPORTED_CAPABILITIES_KEY = "supportedCapabilities";

    private AccessorySessionOptionsTransformer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle */
    public AccessorySessionOptions mo568fromBundle(@NotNull Bundle bundle) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        AccessorySessionOptions accessorySessionOptions = new AccessorySessionOptions();
        ArrayList<Integer> supportedCapabilitiesOrdinalList = bundle.getIntegerArrayList(SUPPORTED_CAPABILITIES_KEY);
        if (supportedCapabilitiesOrdinalList != null) {
            Intrinsics.checkExpressionValueIsNotNull(supportedCapabilitiesOrdinalList, "supportedCapabilitiesOrdinalList");
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(supportedCapabilitiesOrdinalList, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (Integer it2 : supportedCapabilitiesOrdinalList) {
                Capability[] values = Capability.values();
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                arrayList.add(values[it2.intValue()]);
            }
            accessorySessionOptions.setSupportedCapabilities(arrayList);
        }
        accessorySessionOptions.useUnsecureLowEnergyConnection(bundle.getBoolean(SHOULD_USE_UNESECURE_LE_CONNECTION_KEY));
        accessorySessionOptions.setDeviceKnown(bundle.getBoolean(DEVICE_KNOWN_KEY));
        accessorySessionOptions.setForceConnection(bundle.getBoolean(SHOULD_FORCE_CONNECTION_KEY));
        return accessorySessionOptions;
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    public Bundle toBundle(@NotNull AccessorySessionOptions t) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(t, "t");
        Bundle bundle = new Bundle();
        List<Capability> supportedCapabilities = t.getSupportedCapabilities();
        if (supportedCapabilities != null) {
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(supportedCapabilities, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (Capability capability : supportedCapabilities) {
                arrayList.add(Integer.valueOf(capability.ordinal()));
            }
            bundle.putIntegerArrayList(SUPPORTED_CAPABILITIES_KEY, new ArrayList<>(arrayList));
        }
        bundle.putBoolean(SHOULD_USE_UNESECURE_LE_CONNECTION_KEY, t.shouldUseUnsecureLowEnergyConnection());
        bundle.putBoolean(DEVICE_KNOWN_KEY, t.isDeviceKnown());
        bundle.putBoolean(SHOULD_FORCE_CONNECTION_KEY, t.shouldForceConnection());
        return bundle;
    }
}
