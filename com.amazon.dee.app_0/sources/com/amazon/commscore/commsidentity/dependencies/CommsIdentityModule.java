package com.amazon.commscore.commsidentity.dependencies;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.room.Room;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.commscore.api.remoteconfiguration.RemoteConfigValue;
import com.amazon.commscore.commsidentity.common.MAPAuthenticationInterceptor;
import com.amazon.commscore.commsidentity.common.TimeUtil;
import com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao;
import com.amazon.commscore.commsidentity.database.dao.IdentityV2Dao;
import com.amazon.commscore.commsidentity.database.roomdb.CommsCoreIdentityDatabase;
import com.amazon.commscore.commsidentity.dependencies.annotation.AcmsOkHttpClient;
import com.amazon.commscore.commsidentity.dependencies.annotation.IdentitySharedPrefererences;
import com.amazon.commscore.commsidentity.implementation.CommsCoreIdentityViewModel;
import com.amazon.commscore.commsidentity.implementation.DefaultAlexaCommsCoreIdentityService;
import com.amazon.commscore.commsidentity.remote.client.AcmsClient;
import com.amazon.commscore.commsidentity.repo.mapper.AccountForDirectedIdMapper;
import com.amazon.commscore.commsidentity.repo.mapper.IdentityV2Mapper;
import com.amazon.commscore.commsidentity.repo.provider.CommsIdentityProvider;
import com.amazon.commscore.commsidentity.repo.repository.CommsIdentityRepo;
import com.amazon.commscore.dependencies.annotation.ApplicationContext;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
@Module
/* loaded from: classes12.dex */
public class CommsIdentityModule {
    private static final int DEFAULT_TIMEOUT = 15;
    private static final String SHARED_PREFS_FILE = "comms_identity_prefs";
    private Lazy<EnvironmentService> environmentServiceLazy;
    private Lazy<IdentityService> identityServiceLazy;

    public CommsIdentityModule(@NonNull Lazy<IdentityService> lazy, @NonNull Lazy<EnvironmentService> lazy2) {
        this.identityServiceLazy = lazy;
        this.environmentServiceLazy = lazy2;
    }

    public /* synthetic */ RemoteConfigValue lambda$providesConfigService$0$CommsIdentityModule(String str, final Object obj) {
        return new RemoteConfigValue() { // from class: com.amazon.commscore.commsidentity.dependencies.CommsIdentityModule.1
            @Override // com.amazon.commscore.api.remoteconfiguration.RemoteConfigValue
            public Object getRawValue() {
                return obj;
            }

            @Override // com.amazon.commscore.api.remoteconfiguration.RemoteConfigValue
            public Integer toInteger() throws NumberFormatException, NullPointerException {
                return Integer.valueOf(Integer.parseInt(String.valueOf(obj)));
            }

            @Override // com.amazon.commscore.api.remoteconfiguration.RemoteConfigValue
            public String toString() {
                return String.valueOf(obj);
            }
        };
    }

    @Provides
    @Singleton
    public AcmsClient providesAcmsClient(@AcmsOkHttpClient OkHttpClient okHttpClient, AlexaCommsCoreRemoteConfigurationService alexaCommsCoreRemoteConfigurationService) {
        return new AcmsClient(okHttpClient, alexaCommsCoreRemoteConfigurationService);
    }

    @Provides
    @Singleton
    @AcmsOkHttpClient
    public OkHttpClient providesAcmsOkHttpClient(MAPAuthenticationInterceptor mAPAuthenticationInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(httpLoggingInterceptor);
        builder.addInterceptor(mAPAuthenticationInterceptor);
        builder.readTimeout(15L, TimeUnit.SECONDS);
        builder.writeTimeout(15L, TimeUnit.SECONDS);
        return builder.build();
    }

    @Provides
    @Singleton
    public AlexaCommsCoreIdentityService providesCommsCoreIdentityService(Lazy<EventBus> lazy, CommsCoreIdentityViewModel commsCoreIdentityViewModel, TimeUtil timeUtil) {
        return new DefaultAlexaCommsCoreIdentityService(lazy, commsCoreIdentityViewModel, timeUtil);
    }

    @Provides
    @Singleton
    public CommsIdentityDao providesCommsIdentityDao(CommsCoreIdentityDatabase commsCoreIdentityDatabase) {
        return commsCoreIdentityDatabase.commsIdentityDao();
    }

    @Provides
    @Singleton
    public CommsCoreIdentityDatabase providesCommsIdentityDatabase(@ApplicationContext Context context) {
        return (CommsCoreIdentityDatabase) Room.databaseBuilder(context, CommsCoreIdentityDatabase.class, "comms-core-identity-database").build();
    }

    @Provides
    @Singleton
    public CommsIdentityProvider providesCommsIdentityProvider(AcmsClient acmsClient, CommsIdentityDao commsIdentityDao, IdentityV2Mapper identityV2Mapper, AccountForDirectedIdMapper accountForDirectedIdMapper) {
        return new CommsIdentityRepo(acmsClient, commsIdentityDao, identityV2Mapper, accountForDirectedIdMapper);
    }

    @Provides
    @Singleton
    @IdentitySharedPrefererences
    public SharedPreferences providesCommsSharedPrefs(@ApplicationContext Context context) {
        return context.getSharedPreferences(SHARED_PREFS_FILE, 0);
    }

    @Provides
    @Singleton
    public AlexaCommsCoreRemoteConfigurationService providesConfigService() {
        return new AlexaCommsCoreRemoteConfigurationService() { // from class: com.amazon.commscore.commsidentity.dependencies.-$$Lambda$CommsIdentityModule$HLE6PfBySkynl17qS4Za-MKazQo
            @Override // com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService
            public final RemoteConfigValue getRemoteConfiguration(String str, Object obj) {
                return CommsIdentityModule.this.lambda$providesConfigService$0$CommsIdentityModule(str, obj);
            }
        };
    }

    @Provides
    @Singleton
    public EnvironmentService providesEnvironmentService() {
        return this.environmentServiceLazy.mo358get();
    }

    @Provides
    @Singleton
    public IdentityService providesIdentityService() {
        return this.identityServiceLazy.mo358get();
    }

    @Provides
    @Singleton
    public IdentityV2Dao providesIdentityV2Dao(CommsCoreIdentityDatabase commsCoreIdentityDatabase) {
        return commsCoreIdentityDatabase.identityV2Dao();
    }

    @Provides
    @Singleton
    public MAPAuthenticationInterceptor providesMAPAuthInterceptor() {
        return new MAPAuthenticationInterceptor(this.identityServiceLazy.mo358get(), this.environmentServiceLazy.mo358get());
    }
}
