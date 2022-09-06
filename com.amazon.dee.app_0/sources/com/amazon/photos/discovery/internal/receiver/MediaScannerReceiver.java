package com.amazon.photos.discovery.internal.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* compiled from: MediaScannerReceiver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/photos/discovery/internal/receiver/MediaScannerReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MediaScannerReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(@Nullable Context context, @Nullable Intent intent) {
        for (DiscoveryComponent component : Discovery.Companion.getAllDiscoveryComponents$AndroidPhotosDiscovery_release()) {
            Intrinsics.checkExpressionValueIsNotNull(component, "component");
            Discovery discovery = component.getDiscovery();
            Intrinsics.checkExpressionValueIsNotNull(discovery, "component.discovery");
            if (!discovery.isDestroyed()) {
                component.getDiscovery().getOperations().scan();
            }
        }
    }
}
