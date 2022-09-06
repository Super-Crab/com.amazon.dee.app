package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import com.amazon.devicesetupservice.DiscoveryInputParameters;
import com.amazon.devicesetupservice.DiscoveryOutputParameters;
import com.amazon.devicesetupservice.ProvisioneeDetails;
import com.amazon.devicesetupservice.ProvisioneeIdentifier;
import com.amazon.devicesetupservice.ProvisionerDetails;
import com.amazon.devicesetupservice.v1.AuthMaterialIdentifier;
import com.amazon.devicesetupservice.v1.BarcodeIdentifier;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.scopes.NetworkingScope;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSRetrofitInterface;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSServiceConfiguration;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.FFSApiGatewayInterface;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.jackson.AuthMaterialIdentifierMixin;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.jackson.BarcodeIdentifierMixin;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.jackson.DiscoveryInputParametersMixin;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.jackson.DiscoveryOutputParametersMixin;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.jackson.ProvisioneeDetailsMixin;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.jackson.ProvisioneeIdentifierMixin;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
@Module
/* loaded from: classes13.dex */
public class NetworkingModule {
    private final DSSServiceConfiguration mServiceConfiguration;

    public NetworkingModule(DSSServiceConfiguration dSSServiceConfiguration) {
        this.mServiceConfiguration = dSSServiceConfiguration;
    }

    public static OkHttpClient buildOkHttpClient(DSSServiceConfiguration dSSServiceConfiguration) {
        OkHttpClient.Builder followSslRedirects = new OkHttpClient.Builder().connectTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).retryOnConnectionFailure(true).followSslRedirects(false);
        if (dSSServiceConfiguration.isDebugEnabled()) {
            followSslRedirects.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return followSslRedirects.build();
    }

    @Provides
    @NetworkingScope
    public DSSRetrofitInterface providesDSSRetroInterface(DSSServiceConfiguration dSSServiceConfiguration, OkHttpClient okHttpClient, JacksonConverterFactory jacksonConverterFactory) {
        return (DSSRetrofitInterface) new Retrofit.Builder().baseUrl(dSSServiceConfiguration.getBaseUrl()).client(okHttpClient).addConverterFactory(jacksonConverterFactory).build().create(DSSRetrofitInterface.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @NetworkingScope
    public DSSServiceConfiguration providesDSSServiceConfiguration() {
        return this.mServiceConfiguration;
    }

    @Provides
    @NetworkingScope
    public FFSApiGatewayInterface providesFFSApiGatewayInterface(DSSServiceConfiguration dSSServiceConfiguration, OkHttpClient okHttpClient, JacksonConverterFactory jacksonConverterFactory) {
        return (FFSApiGatewayInterface) new Retrofit.Builder().baseUrl(dSSServiceConfiguration.getApiGatewayBaseUrl()).client(okHttpClient).addConverterFactory(jacksonConverterFactory).build().create(FFSApiGatewayInterface.class);
    }

    @Provides
    @NetworkingScope
    public JacksonConverterFactory providesJacksonConverterFactory(ObjectMapper objectMapper) {
        return JacksonConverterFactory.create(objectMapper);
    }

    @Provides
    @NetworkingScope
    public OkHttpClient providesOkHttpClient(DSSServiceConfiguration dSSServiceConfiguration) {
        return buildOkHttpClient(dSSServiceConfiguration);
    }

    @Provides
    @NetworkingScope
    public ObjectMapper proviesObjectMapper() {
        ObjectMapper disable = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
        disable.addMixIn(ProvisioneeDetails.class, ProvisioneeDetailsMixin.class);
        disable.addMixIn(ProvisionerDetails.class, ProvisioneeDetailsMixin.class);
        disable.addMixIn(DiscoveryInputParameters.class, DiscoveryInputParametersMixin.class);
        disable.addMixIn(ProvisioneeIdentifier.class, ProvisioneeIdentifierMixin.class);
        disable.addMixIn(AuthMaterialIdentifier.class, AuthMaterialIdentifierMixin.class);
        disable.addMixIn(BarcodeIdentifier.class, BarcodeIdentifierMixin.class);
        disable.addMixIn(DiscoveryOutputParameters.class, DiscoveryOutputParametersMixin.class);
        return disable;
    }
}
