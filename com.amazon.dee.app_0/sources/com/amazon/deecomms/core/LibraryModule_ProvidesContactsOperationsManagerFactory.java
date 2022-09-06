package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.contacts.operations.ContactsOperationsManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesContactsOperationsManagerFactory implements Factory<ContactsOperationsManager> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesContactsOperationsManagerFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesContactsOperationsManagerFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesContactsOperationsManagerFactory(libraryModule, provider);
    }

    public static ContactsOperationsManager provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (ContactsOperationsManager) Preconditions.checkNotNull(libraryModule.providesContactsOperationsManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ContactsOperationsManager proxyProvidesContactsOperationsManager(LibraryModule libraryModule, Context context) {
        return (ContactsOperationsManager) Preconditions.checkNotNull(libraryModule.providesContactsOperationsManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ContactsOperationsManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
