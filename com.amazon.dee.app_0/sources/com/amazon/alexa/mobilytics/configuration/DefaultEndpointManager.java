package com.amazon.alexa.mobilytics.configuration;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazonaws.regions.Regions;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public final class DefaultEndpointManager implements EndpointManager {
    private static final String TAG = Log.tag(DefaultEndpointManager.class);
    private final Map<Regions, String> cognitoPoolIds;
    private final ConfigManager configManager;
    private List<Map<Regions, Endpoint>> endpoints = new ArrayList();
    private List<Endpoint> dcmEndpoints = new ArrayList();

    @Inject
    public DefaultEndpointManager(@NonNull ConfigManager configManager, @NonNull @Named("CognitoPoolIds") Map<Regions, String> map) {
        this.configManager = (ConfigManager) Preconditions.checkNotNull(configManager);
        this.cognitoPoolIds = (Map) Preconditions.checkNotNull(map);
        initialize();
    }

    @Override // com.amazon.alexa.mobilytics.configuration.EndpointManager
    public List<Endpoint> dcmEndpoints() {
        return Collections.unmodifiableList(this.dcmEndpoints);
    }

    @Override // com.amazon.alexa.mobilytics.configuration.EndpointManager
    public List<Map<Regions, Endpoint>> endpoints() {
        return Collections.unmodifiableList(this.endpoints);
    }

    @Override // com.amazon.alexa.mobilytics.configuration.EndpointManager
    public void initialize() {
        this.endpoints.clear();
        this.dcmEndpoints.clear();
        for (Config.Stream stream : this.configManager.config().streams()) {
            List<Map<Regions, Endpoint>> list = this.endpoints;
            ImmutableMap.Builder builder = ImmutableMap.builder();
            Regions regions = Regions.US_EAST_1;
            ImmutableMap.Builder mo7828put = builder.mo7828put(regions, new KinesisEndpoint(this.cognitoPoolIds.get(regions), Regions.US_EAST_1, stream));
            Regions regions2 = Regions.EU_WEST_1;
            ImmutableMap.Builder mo7828put2 = mo7828put.mo7828put(regions2, new KinesisEndpoint(this.cognitoPoolIds.get(regions2), Regions.EU_WEST_1, stream));
            Regions regions3 = Regions.US_WEST_2;
            list.add(mo7828put2.mo7828put(regions3, new KinesisEndpoint(this.cognitoPoolIds.get(regions3), Regions.US_WEST_2, stream)).mo7826build());
        }
        for (Config.DcmStream dcmStream : this.configManager.config().dcmStreams()) {
            this.dcmEndpoints.add(new DCMEndpoint(dcmStream.priority(), dcmStream.tag()));
        }
    }
}
