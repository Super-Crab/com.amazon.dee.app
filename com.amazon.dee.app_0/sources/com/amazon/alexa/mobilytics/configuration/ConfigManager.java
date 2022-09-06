package com.amazon.alexa.mobilytics.configuration;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.auth.CognitoCredentialsProvider;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.alexa.mobilytics.internal.JsonConverter;
import com.amazon.alexa.mobilytics.marketplace.Marketplace;
import com.amazon.alexa.mobilytics.stream.KinesisClientProvider;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class ConfigManager {
    private static final String TAG = Log.tag(ConfigManager.class);
    private final KinesisClientProvider clientProvider;
    private final Map<Regions, String> cognitoPoolIds;
    private Config config;
    private final ConfigPuller configPuller;
    private final ConfigStorage configStorage;
    private final JsonConverter converter;
    private final CognitoCredentialsProvider.Factory credentialsProviderFactory;
    private final Config defaultConfig;
    private int domain;
    private AmazonKinesisClient kinesisClient;

    @Inject
    public ConfigManager(@NonNull ConfigPuller configPuller, @NonNull JsonConverter jsonConverter, @NonNull ConfigStorage configStorage, @NonNull CognitoCredentialsProvider.Factory factory, @NonNull KinesisClientProvider kinesisClientProvider, @NonNull @Named("CognitoPoolIds") Map<Regions, String> map, @NonNull Config config) {
        this.configPuller = (ConfigPuller) Preconditions.checkNotNull(configPuller);
        this.converter = (JsonConverter) Preconditions.checkNotNull(jsonConverter);
        this.configStorage = (ConfigStorage) Preconditions.checkNotNull(configStorage);
        this.credentialsProviderFactory = (CognitoCredentialsProvider.Factory) Preconditions.checkNotNull(factory);
        this.clientProvider = (KinesisClientProvider) Preconditions.checkNotNull(kinesisClientProvider);
        this.cognitoPoolIds = (Map) Preconditions.checkNotNull(map);
        this.defaultConfig = (Config) Preconditions.checkNotNull(config);
        this.config = this.defaultConfig;
    }

    private boolean checkStreamsExist(@NonNull List<Config.Stream> list) {
        for (Config.Stream stream : list) {
            if (stream != null) {
                try {
                    if (!TextUtils.isEmpty(stream.name())) {
                        this.kinesisClient.describeStream(stream.name());
                    }
                } catch (Exception e) {
                    Log.e(TAG, e, "Stream does not exist [%s]", stream.name());
                    return false;
                }
            }
            Log.e(TAG, "Stream has empty name");
            return false;
        }
        return true;
    }

    private void initializeConfigPuller() {
        Region region = Region.DEFAULT;
        this.configPuller.initialize(this.credentialsProviderFactory.create(this.cognitoPoolIds.get(region.awsRegion()), region.awsRegion()), region.awsRegion(), this.domain);
    }

    private void updateCredentials(@Nullable MobilyticsUser mobilyticsUser) {
        Marketplace findMarketplaceById;
        Region region = Region.DEFAULT;
        if (mobilyticsUser != null && (findMarketplaceById = Marketplace.findMarketplaceById(mobilyticsUser.marketplaceId(), Marketplace.US)) != null) {
            region = Region.fromCountryCode(findMarketplaceById.name());
        }
        try {
            this.kinesisClient = this.clientProvider.create(this.credentialsProviderFactory.create(this.cognitoPoolIds.get(region.awsRegion()), region.awsRegion()), region.awsRegion());
        } catch (Exception e) {
            Log.e(TAG, "Error creating Kinesis Stream Client", e);
        }
    }

    public Config config() {
        return this.config;
    }

    @NonNull
    public Config defaultConfig() {
        return this.defaultConfig;
    }

    public void initialize(@NonNull MobilyticsConfiguration mobilyticsConfiguration) {
        if (mobilyticsConfiguration.userProvider().user() != null && mobilyticsConfiguration.userProvider().user().hasFeature("ALEXA_ANDROID_MOBILYTICS_HIGH_PRIORITY_METRICS")) {
            this.domain = mobilyticsConfiguration.domain();
            initializeConfigPuller();
            updateCredentials(mobilyticsConfiguration.userProvider().user());
            Config config = this.config;
            if (config == this.defaultConfig || checkStreamsExist(config.streams())) {
                return;
            }
            this.config = this.defaultConfig;
            return;
        }
        this.config = this.defaultConfig;
        this.configStorage.clear();
    }

    public void onUserChanged(@Nullable MobilyticsUser mobilyticsUser) {
        Log.enter();
        updateCredentials(mobilyticsUser);
        Config config = this.config;
        if (config != this.defaultConfig && !checkStreamsExist(config.streams())) {
            this.config = this.defaultConfig;
        }
        Log.leave();
    }

    public boolean readConfig() {
        Config config;
        String read = this.configPuller.read(this.configStorage.lastUpdatedTime());
        if (read != null && (config = (Config) this.converter.fromJson(read, Config.class)) != null) {
            if (!checkStreamsExist(config.streams())) {
                return false;
            }
            this.config = config;
            this.configStorage.storeContent(this.config);
            this.configStorage.storeUpdatedTime(this.configPuller.updatedTime());
            return true;
        } else if (checkStreamsExist(this.config.streams())) {
            return false;
        } else {
            this.config = this.defaultConfig;
            return true;
        }
    }
}
