package com.amazon.alexa.featureservice.dependencies;

import android.content.Context;
import com.amazon.alexa.featureservice.database.roomdb.FeatureAppDatabase;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class DatabaseModule_ProvideRoomDatabaseFactory implements Factory<FeatureAppDatabase> {
    private final Provider<Context> contextProvider;
    private final DatabaseModule module;

    public DatabaseModule_ProvideRoomDatabaseFactory(DatabaseModule databaseModule, Provider<Context> provider) {
        this.module = databaseModule;
        this.contextProvider = provider;
    }

    public static DatabaseModule_ProvideRoomDatabaseFactory create(DatabaseModule databaseModule, Provider<Context> provider) {
        return new DatabaseModule_ProvideRoomDatabaseFactory(databaseModule, provider);
    }

    public static FeatureAppDatabase provideInstance(DatabaseModule databaseModule, Provider<Context> provider) {
        return proxyProvideRoomDatabase(databaseModule, provider.mo10268get());
    }

    public static FeatureAppDatabase proxyProvideRoomDatabase(DatabaseModule databaseModule, Context context) {
        return (FeatureAppDatabase) Preconditions.checkNotNull(databaseModule.provideRoomDatabase(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureAppDatabase mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
