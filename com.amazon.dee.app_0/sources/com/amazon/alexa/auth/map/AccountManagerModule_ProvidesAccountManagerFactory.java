package com.amazon.alexa.auth.map;

import android.content.Context;
import com.amazon.alexa.auth.AccountManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AccountManagerModule_ProvidesAccountManagerFactory implements Factory<AccountManager> {
    private final Provider<Context> contextProvider;
    private final AccountManagerModule module;

    public AccountManagerModule_ProvidesAccountManagerFactory(AccountManagerModule accountManagerModule, Provider<Context> provider) {
        this.module = accountManagerModule;
        this.contextProvider = provider;
    }

    public static AccountManagerModule_ProvidesAccountManagerFactory create(AccountManagerModule accountManagerModule, Provider<Context> provider) {
        return new AccountManagerModule_ProvidesAccountManagerFactory(accountManagerModule, provider);
    }

    public static AccountManager provideInstance(AccountManagerModule accountManagerModule, Provider<Context> provider) {
        return proxyProvidesAccountManager(accountManagerModule, provider.mo10268get());
    }

    public static AccountManager proxyProvidesAccountManager(AccountManagerModule accountManagerModule, Context context) {
        return (AccountManager) Preconditions.checkNotNull(accountManagerModule.providesAccountManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccountManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
