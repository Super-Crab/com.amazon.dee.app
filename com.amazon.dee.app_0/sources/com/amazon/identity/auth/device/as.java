package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPFuture;
import java.util.EnumSet;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface as {
    MAPFuture<Bundle> a(String str, String str2, Callback callback, Bundle bundle, EnumSet<CustomerAttributeStore.GetAttributeOptions> enumSet, ej ejVar);

    Bundle peekAttribute(String str, String str2);

    MAPFuture<Bundle> setAttribute(String str, String str2, String str3, Callback callback);
}
