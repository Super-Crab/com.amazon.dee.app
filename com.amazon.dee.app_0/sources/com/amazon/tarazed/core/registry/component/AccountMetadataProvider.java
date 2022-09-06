package com.amazon.tarazed.core.registry.component;

import com.amazon.tarazed.core.registry.component.exception.AccountProviderException;
import com.amazon.tarazed.core.type.Account;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccountMetadataProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u00020\u00038fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/registry/component/AccountMetadataProvider;", "Lcom/amazon/tarazed/core/registry/component/TarazedComponent;", "accountMetadata", "Lcom/amazon/tarazed/core/type/Account;", "getAccountMetadata", "()Lcom/amazon/tarazed/core/type/Account;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface AccountMetadataProvider extends TarazedComponent {
    @NotNull
    Account getAccountMetadata() throws AccountProviderException;
}
