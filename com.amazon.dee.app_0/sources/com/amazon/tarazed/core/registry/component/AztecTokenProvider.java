package com.amazon.tarazed.core.registry.component;

import com.amazon.tarazed.core.registry.component.exception.NoAuthenticatedUserException;
import com.amazon.tarazed.core.registry.component.exception.TarazedAuthenticationException;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: AztecTokenProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/registry/component/AztecTokenProvider;", "Lcom/amazon/tarazed/core/registry/component/TarazedComponent;", "provideAztecToken", "", "isSessionActive", "", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface AztecTokenProvider extends TarazedComponent {
    @Nullable
    String provideAztecToken(boolean z) throws NoAuthenticatedUserException, TarazedAuthenticationException;
}
