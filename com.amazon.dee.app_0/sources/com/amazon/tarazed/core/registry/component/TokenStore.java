package com.amazon.tarazed.core.registry.component;

import com.amazon.tarazed.core.type.NotificationToken;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: TokenStore.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\u0005\"\u0004\b\r\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/amazon/tarazed/core/registry/component/TokenStore;", "Lcom/amazon/tarazed/core/registry/component/TarazedComponent;", "currentToken", "Lcom/amazon/tarazed/core/type/NotificationToken;", "getCurrentToken", "()Lcom/amazon/tarazed/core/type/NotificationToken;", "setCurrentToken", "(Lcom/amazon/tarazed/core/type/NotificationToken;)V", "isCurrentTokenUploaded", "", "()Z", "lastUploadedToken", "getLastUploadedToken", "setLastUploadedToken", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface TokenStore extends TarazedComponent {
    @Nullable
    NotificationToken getCurrentToken();

    @Nullable
    NotificationToken getLastUploadedToken();

    boolean isCurrentTokenUploaded();

    void setCurrentToken(@Nullable NotificationToken notificationToken);

    void setLastUploadedToken(@Nullable NotificationToken notificationToken);
}
