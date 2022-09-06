package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.app.job.JobScheduler;
import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusClient;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ArcusConstants;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;
@Module
/* loaded from: classes13.dex */
public class ArcusModule {
    @Provides
    @Singleton
    public JSONObject providesDefaultArcuConfiguration() {
        try {
            return new FFSArcusSettings().toJSONObject();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Provides
    @Singleton
    public FFSArcusClient providesFFSArcusSyncClient(RemoteConfigurationManager remoteConfigurationManager) {
        return new FFSArcusClient(remoteConfigurationManager);
    }

    @Provides
    @Singleton
    public FFSArcusSyncCoordinator providesFFSArcusSyncCoordinator(JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, Context context, SharedPreferencesProvider sharedPreferencesProvider, Clock clock, FFSArcusClient fFSArcusClient) {
        return new FFSArcusSyncCoordinator(jobScheduler, jobInfoProvider, context, sharedPreferencesProvider, clock, fFSArcusClient);
    }

    @Provides
    @Singleton
    public RemoteConfigurationManager providesRemoteConfigurationManager(Context context, JSONObject jSONObject) {
        return new RemoteConfigurationManager.Builder(context, ArcusConstants.APP_CONFIGURATION_ID).withDefaultConfiguration(jSONObject).createOrGet();
    }
}
