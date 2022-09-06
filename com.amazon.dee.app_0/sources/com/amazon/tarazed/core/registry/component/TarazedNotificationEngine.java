package com.amazon.tarazed.core.registry.component;

import android.content.Context;
import com.amazon.device.messaging.ADMRegistrationConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedNotificationEngine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H&J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\r\u001a\u00020\u0005H&J\b\u0010\u000e\u001a\u00020\u0005H&¨\u0006\u000f"}, d2 = {"Lcom/amazon/tarazed/core/registry/component/TarazedNotificationEngine;", "Lcom/amazon/tarazed/core/registry/component/TarazedComponent;", "getToken", "", "initialize", "", "isSupported", "", "onMessageReceived", "context", "Landroid/content/Context;", "message", "onNewToken", ADMRegistrationConstants.METHOD_REGISTER, ADMRegistrationConstants.METHOD_UNREGISTER, "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface TarazedNotificationEngine extends TarazedComponent {
    @Nullable
    String getToken();

    void initialize();

    boolean isSupported();

    void onMessageReceived(@NotNull Context context, @NotNull String str);

    void onNewToken(@NotNull Context context);

    void register();

    void unregister();
}
