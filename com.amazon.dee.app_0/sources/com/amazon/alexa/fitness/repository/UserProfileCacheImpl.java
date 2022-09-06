package com.amazon.alexa.fitness.repository;

import android.content.Context;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.fitness.api.afx.UserProfileRepository;
import com.amazon.alexa.fitness.api.fitnessSdk.UserProfile;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.util.SerializationUtilsKt;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.dee.app.cachemanager.CacheMetadata;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.SerializationUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UserProfileCacheImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002J\n\u0010\u0010\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/fitness/repository/UserProfileCacheImpl;", "Lcom/amazon/alexa/fitness/api/afx/UserProfileRepository;", "componentRegistry", "Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;", "cacheProvider", "Lcom/amazon/alexa/fitness/repository/CacheProvider;", "identityManager", "Lcom/amazon/alexa/fitness/identity/IdentityManager;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;Lcom/amazon/alexa/fitness/repository/CacheProvider;Lcom/amazon/alexa/fitness/identity/IdentityManager;Lcom/amazon/alexa/fitness/logs/ILog;)V", MetricsConstants.OperationalMetrics.GET_USER_PROFILE, "Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;", "profileDirectedId", "", "getUserProfileDirectedId", "getUserProfileForCurrentUser", "saveUserProfile", "", "userProfile", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class UserProfileCacheImpl implements UserProfileRepository {
    private final CacheProvider cacheProvider;
    private final IdentityManager identityManager;
    private final ILog log;

    @Inject
    public UserProfileCacheImpl(@NotNull ComponentRegistry componentRegistry, @NotNull CacheProvider cacheProvider, @NotNull IdentityManager identityManager, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(componentRegistry, "componentRegistry");
        Intrinsics.checkParameterIsNotNull(cacheProvider, "cacheProvider");
        Intrinsics.checkParameterIsNotNull(identityManager, "identityManager");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.cacheProvider = cacheProvider;
        this.identityManager = identityManager;
        this.log = log;
        componentRegistry.bindConcreteFactory(UserProfileRepository.class, new ComponentRegistry.ConcreteComponentFactory<UserProfileRepository>() { // from class: com.amazon.alexa.fitness.repository.UserProfileCacheImpl.1
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            @NotNull
            /* renamed from: create */
            public final UserProfileRepository create2(Context context) {
                return UserProfileCacheImpl.this;
            }
        });
    }

    private final String getUserProfileDirectedId() {
        String userProfileDirectedId = this.identityManager.getUserProfileDirectedId();
        if (userProfileDirectedId != null) {
            return userProfileDirectedId;
        }
        ILog.DefaultImpls.error$default(this.log, "UserProfileCacheImpl", "Profile directed ID was empty while retrieving local user profile data", null, 4, null);
        return null;
    }

    @Override // com.amazon.alexa.fitness.api.afx.UserProfileRepository
    @Nullable
    public UserProfile getUserProfile(@NotNull String profileDirectedId) {
        Intrinsics.checkParameterIsNotNull(profileDirectedId, "profileDirectedId");
        byte[] orNull = this.cacheProvider.getUserProfileCache().get(profileDirectedId, new CacheMetadata(1)).toBlocking().single().orNull();
        UserProfile userProfile = null;
        if (orNull != null) {
            try {
                Object deserialize = SerializationUtils.deserialize(orNull);
                if (deserialize == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.api.fitnessSdk.UserProfile");
                }
                userProfile = (UserProfile) deserialize;
            } catch (Exception unused) {
            }
        }
        ILog iLog = this.log;
        ILog.DefaultImpls.debug$default(iLog, "UserProfileCacheImpl", "Retrieved UserProfile from secure storage: " + userProfile, null, 4, null);
        return userProfile;
    }

    @Override // com.amazon.alexa.fitness.api.afx.UserProfileRepository
    @Nullable
    public UserProfile getUserProfileForCurrentUser() {
        String userProfileDirectedId = getUserProfileDirectedId();
        if (userProfileDirectedId != null) {
            return getUserProfile(userProfileDirectedId);
        }
        return null;
    }

    @Override // com.amazon.alexa.fitness.api.afx.UserProfileRepository
    public void saveUserProfile(@NotNull UserProfile userProfile) {
        Intrinsics.checkParameterIsNotNull(userProfile, "userProfile");
        String userProfileDirectedId = getUserProfileDirectedId();
        if (userProfileDirectedId != null) {
            this.cacheProvider.getUserProfileCache().put(userProfileDirectedId, SerializationUtilsKt.serialize(userProfile), new CacheMetadata(1)).toBlocking().singleOrDefault(null);
            ILog iLog = this.log;
            ILog.DefaultImpls.debug$default(iLog, "UserProfileCacheImpl", "Saved user profile to secure storage, profileDirectedId: " + userProfileDirectedId + ", userProfile: " + userProfile, null, 4, null);
        }
    }
}
