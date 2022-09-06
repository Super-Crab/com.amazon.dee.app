package com.amazon.clouddrive.cdasdk.aps.account;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface APSAccountCalls {
    @NonNull
    Single<AccountFeaturesOutput> getAccountFeatures(@NonNull AccountFeaturesInput accountFeaturesInput);
}
