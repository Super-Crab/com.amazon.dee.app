package com.amazon.commscore.commsidentity.repo.provider;

import com.amazon.commscore.commsidentity.repo.model.AccountForDirectedId;
import com.amazon.commscore.commsidentity.repo.model.IdentityV2;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
/* loaded from: classes12.dex */
public interface CommsIdentityProvider {
    Flowable<AccountForDirectedId> getAccount(String str);

    Flowable<IdentityV2> getIdentityV2(String str);

    Completable refreshCommsIdentity(String str);
}
