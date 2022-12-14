package com.amazon.tarazed.core.registry.component;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: TokenUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/tarazed/core/registry/component/TokenUploader;", "Lcom/amazon/tarazed/core/registry/component/TarazedComponent;", "scheduleImmediateTokenUpload", "", "tokenStore", "Lcom/amazon/tarazed/core/registry/component/TokenStore;", "schedulePeriodicTokenUpload", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface TokenUploader extends TarazedComponent {
    void scheduleImmediateTokenUpload(@NotNull TokenStore tokenStore);

    void schedulePeriodicTokenUpload(@NotNull TokenStore tokenStore);
}
