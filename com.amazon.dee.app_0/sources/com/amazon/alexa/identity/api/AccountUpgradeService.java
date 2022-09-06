package com.amazon.alexa.identity.api;

import rx.Observable;
/* loaded from: classes9.dex */
public interface AccountUpgradeService {
    boolean isAccountUpgraded();

    Observable<UserIdentity> upgradeAccount();
}
