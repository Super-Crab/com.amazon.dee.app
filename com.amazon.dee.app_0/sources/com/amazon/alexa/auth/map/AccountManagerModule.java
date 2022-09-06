package com.amazon.alexa.auth.map;

import android.content.Context;
import com.amazon.alexa.auth.AccountManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public class AccountManagerModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AccountManager providesAccountManager(Context context) {
        return AccountManagerFactory.create(context);
    }
}
