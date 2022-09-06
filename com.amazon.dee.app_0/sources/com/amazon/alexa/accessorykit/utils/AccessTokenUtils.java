package com.amazon.alexa.accessorykit.utils;

import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
/* loaded from: classes6.dex */
public final class AccessTokenUtils {
    private AccessTokenUtils() {
    }

    public static Single<String> getAccessToken(UserSupplier userSupplier) {
        Preconditions.notNull(userSupplier, "userSupplier");
        return userSupplier.queryUser().firstOrError().flatMap($$Lambda$AccessTokenUtils$SBzxDTBEx7KRc4HQjRpRP53PR6I.INSTANCE).map($$Lambda$fwlW44gkCCNtM2tQl5SjwArOfks.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SingleSource lambda$getAccessToken$0(User user) throws Throwable {
        if (user.equals(User.ABSENT)) {
            return Single.error(new IllegalStateException("User is absent!"));
        }
        if (user.getAccessToken() == null) {
            return Single.error(new IllegalStateException("Access token not available for user: " + user));
        }
        return Single.just(user);
    }
}
