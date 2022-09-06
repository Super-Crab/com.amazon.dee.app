package com.amazon.alexa.fitness.repository;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.alexa.fitness.api.UserPreferenceKey;
import com.amazon.alexa.fitness.api.UserPreferenceStore;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.dee.app.metrics.MetricsConstants;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UserPreferenceStoreImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u000fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/repository/UserPreferenceStoreImpl;", "Lcom/amazon/alexa/fitness/api/UserPreferenceStore;", "componentRegistry", "Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;", "context", "Landroid/content/Context;", "identityManager", "Lcom/amazon/alexa/fitness/identity/IdentityManager;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;Landroid/content/Context;Lcom/amazon/alexa/fitness/identity/IdentityManager;Lcom/amazon/alexa/fitness/logs/ILog;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", MetricsConstants.Method.CACHE_GET, "", "key", "Lcom/amazon/alexa/fitness/api/UserPreferenceKey;", "set", "", "value", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class UserPreferenceStoreImpl implements UserPreferenceStore {
    private final IdentityManager identityManager;
    private final ILog log;
    private final SharedPreferences sharedPreferences;

    @Inject
    public UserPreferenceStoreImpl(@NotNull ComponentRegistry componentRegistry, @NotNull Context context, @NotNull IdentityManager identityManager, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(componentRegistry, "componentRegistry");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(identityManager, "identityManager");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.identityManager = identityManager;
        this.log = log;
        this.sharedPreferences = context.getSharedPreferences("userPreferences", 0);
        componentRegistry.bindConcreteFactory(UserPreferenceStore.class, new ComponentRegistry.ConcreteComponentFactory<UserPreferenceStore>() { // from class: com.amazon.alexa.fitness.repository.UserPreferenceStoreImpl.1
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            @NotNull
            /* renamed from: create */
            public final UserPreferenceStore create2(Context context2) {
                return UserPreferenceStoreImpl.this;
            }
        });
    }

    @Override // com.amazon.alexa.fitness.api.UserPreferenceStore
    public boolean get(@NotNull UserPreferenceKey key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        String userProfileDirectedId = this.identityManager.getUserProfileDirectedId();
        if (userProfileDirectedId != null) {
            SharedPreferences sharedPreferences = this.sharedPreferences;
            return sharedPreferences.getBoolean(key.name() + '_' + userProfileDirectedId, false);
        }
        return false;
    }

    @Override // com.amazon.alexa.fitness.api.UserPreferenceStore
    public void set(@NotNull UserPreferenceKey key, boolean z) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        String userProfileDirectedId = this.identityManager.getUserProfileDirectedId();
        if (userProfileDirectedId != null) {
            SharedPreferences.Editor edit = this.sharedPreferences.edit();
            edit.putBoolean(key.name() + '_' + userProfileDirectedId, z).apply();
            return;
        }
        ILog.DefaultImpls.error$default(this.log, "UserPreferenceStore", "no user profile to store user preference", null, 4, null);
    }
}
