package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessoryclient.common.api.AccessoryTransportChange;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessoryTransportChangeTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/AccessoryTransportChangeTransformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/api/AccessoryTransportChange;", "()V", "NEW_ACCESSORY_KEY", "", "NEW_TRANSPORT_KEY", "OLD_ACCESSORY_KEY", "OLD_TRANSPORT_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "t", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccessoryTransportChangeTransformer implements BundleTransformer<AccessoryTransportChange> {
    public static final AccessoryTransportChangeTransformer INSTANCE = new AccessoryTransportChangeTransformer();
    private static final String NEW_ACCESSORY_KEY = "accessoryNew";
    private static final String NEW_TRANSPORT_KEY = "transportNew";
    private static final String OLD_ACCESSORY_KEY = "accessoryOld";
    private static final String OLD_TRANSPORT_KEY = "transportOld";

    private AccessoryTransportChangeTransformer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle */
    public AccessoryTransportChange mo568fromBundle(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        AccessoryTransformer accessoryTransformer = AccessoryTransformer.INSTANCE;
        Bundle bundle2 = bundle.getBundle(OLD_ACCESSORY_KEY);
        if (bundle2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(OLD_ACCESSORY_KEY)!!");
        Accessory mo568fromBundle = accessoryTransformer.mo568fromBundle(bundle2);
        AccessoryTransportTypeTransformer accessoryTransportTypeTransformer = AccessoryTransportTypeTransformer.INSTANCE;
        Bundle bundle3 = bundle.getBundle(OLD_TRANSPORT_KEY);
        if (bundle3 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(bundle3, "bundle.getBundle(OLD_TRANSPORT_KEY)!!");
        AccessoryTransport.Type mo568fromBundle2 = accessoryTransportTypeTransformer.mo568fromBundle(bundle3);
        AccessoryTransformer accessoryTransformer2 = AccessoryTransformer.INSTANCE;
        Bundle bundle4 = bundle.getBundle(NEW_ACCESSORY_KEY);
        if (bundle4 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(bundle4, "bundle.getBundle(NEW_ACCESSORY_KEY)!!");
        Accessory mo568fromBundle3 = accessoryTransformer2.mo568fromBundle(bundle4);
        AccessoryTransportTypeTransformer accessoryTransportTypeTransformer2 = AccessoryTransportTypeTransformer.INSTANCE;
        Bundle bundle5 = bundle.getBundle(NEW_TRANSPORT_KEY);
        if (bundle5 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(bundle5, "bundle.getBundle(NEW_TRANSPORT_KEY)!!");
        return new AccessoryTransportChange(mo568fromBundle, mo568fromBundle2, mo568fromBundle3, accessoryTransportTypeTransformer2.mo568fromBundle(bundle5));
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    public Bundle toBundle(@NotNull AccessoryTransportChange t) {
        Intrinsics.checkParameterIsNotNull(t, "t");
        Bundle bundle = new Bundle();
        bundle.putBundle(OLD_ACCESSORY_KEY, AccessoryTransformer.INSTANCE.toBundle(t.getOldAccessory()));
        bundle.putBundle(OLD_TRANSPORT_KEY, AccessoryTransportTypeTransformer.INSTANCE.toBundle(t.getOldTransport()));
        bundle.putBundle(NEW_ACCESSORY_KEY, AccessoryTransformer.INSTANCE.toBundle(t.getNewAccessory()));
        bundle.putBundle(NEW_TRANSPORT_KEY, AccessoryTransportTypeTransformer.INSTANCE.toBundle(t.getNewTransport()));
        return bundle;
    }
}
