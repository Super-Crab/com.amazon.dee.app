package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.clouddrive.cdasdk.aps.APSCalls;
import com.amazon.clouddrive.cdasdk.aps.APSCallsImpl;
import com.amazon.clouddrive.cdasdk.aps.interceptor.APSInterceptor;
import com.amazon.clouddrive.cdasdk.cdds.CDDSCalls;
import com.amazon.clouddrive.cdasdk.cdds.CDDSCallsImpl;
import com.amazon.clouddrive.cdasdk.cdp.CDPCalls;
import com.amazon.clouddrive.cdasdk.cdp.CDPCallsImpl;
import com.amazon.clouddrive.cdasdk.cdrs.CDRSCalls;
import com.amazon.clouddrive.cdasdk.cdrs.CDRSCallsImpl;
import com.amazon.clouddrive.cdasdk.cds.CDSCalls;
import com.amazon.clouddrive.cdasdk.cds.CDSCallsImpl;
import com.amazon.clouddrive.cdasdk.cdts.CDTSCalls;
import com.amazon.clouddrive.cdasdk.cdts.CDTSCallsImpl;
import com.amazon.clouddrive.cdasdk.cdus.CDUSCalls;
import com.amazon.clouddrive.cdasdk.cdus.CDUSCallsImpl;
import com.amazon.clouddrive.cdasdk.dagger.component.ApplicationComponent;
import com.amazon.clouddrive.cdasdk.dagger.component.DaggerApplicationComponent;
import com.amazon.clouddrive.cdasdk.dagger.module.ApplicationModule;
import com.amazon.clouddrive.cdasdk.dps.DPSCalls;
import com.amazon.clouddrive.cdasdk.dps.DPSCallsImpl;
import com.amazon.clouddrive.cdasdk.onelens.OneLensCalls;
import com.amazon.clouddrive.cdasdk.onelens.OneLensCallsImpl;
import com.amazon.clouddrive.cdasdk.prompto.PromptoCalls;
import com.amazon.clouddrive.cdasdk.prompto.PromptoCallsImpl;
import com.amazon.clouddrive.cdasdk.retry.DefaultRetryPolicy;
import com.amazon.clouddrive.cdasdk.retry.ExponentialJitterBackoffStrategy;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
/* loaded from: classes11.dex */
public final class CDClientImpl implements CDClient {
    private static final int MAX_OKHTTP_RETRIES = 3;
    @NonNull
    private static final String TAG = "CDClientImpl";
    private static ApplicationComponent appComponent;
    private APSCalls apsCalls;
    @NonNull
    private OkHttpClient baseHttpClient;
    private CDDSCalls cddsCalls;
    private CDPCalls cdpCalls;
    private CDRSCalls cdrsCalls;
    private CDSCalls cdsCalls;
    private CDTSCalls cdtsCalls;
    private CDUSCalls cdusCalls;
    @NonNull
    private final ClientConfig clientConfig;
    private DPSCalls dpsCalls;
    private JacksonConverterFactory jacksonConverterFactory;
    private OneLensCalls oneLensCalls;
    private PromptoCalls promptoCalls;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CDClientImpl(@NonNull ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
        initDagger();
        this.baseHttpClient = createOkHttpClient(clientConfig, 3);
        initJackson();
        initRetrofitBindings();
    }

    @NonNull
    @VisibleForTesting
    static JacksonConverterFactory createJacksonConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return JacksonConverterFactory.create(objectMapper);
    }

    @NonNull
    static OkHttpClient createOkHttpClient(@NonNull ClientConfig clientConfig, int i) {
        TokenAuthenticator tokenAuthenticator = new TokenAuthenticator(clientConfig.getAppContext(), clientConfig.getTokenProvider(), appComponent.getLogger());
        OkHttpClient.Builder addInterceptor = new OkHttpClient.Builder().socketFactory(clientConfig.getSocketFactory()).connectTimeout(clientConfig.getConnectTimeoutMs(), TimeUnit.MILLISECONDS).readTimeout(clientConfig.getReadTimeoutMs(), TimeUnit.MILLISECONDS).writeTimeout(clientConfig.getWriteTimeoutMs(), TimeUnit.MILLISECONDS).authenticator(tokenAuthenticator).addInterceptor(tokenAuthenticator).addInterceptor(new StandardHeaderInterceptor(clientConfig, appComponent.getSystemUtil())).addInterceptor(new RetryInterceptor(appComponent.getLogger(), appComponent.getSystemUtil(), new DefaultRetryPolicy(new ExponentialJitterBackoffStrategy(appComponent.getSystemUtil()), i)));
        new SSLUtil(appComponent.getLogger()).initSafeSSL(addInterceptor);
        return addInterceptor.build();
    }

    public static ApplicationComponent getAppComponent() {
        return appComponent;
    }

    @NonNull
    private Retrofit.Builder getRetrofitBuilder() {
        return getRetrofitBuilder(null);
    }

    private void initDagger() {
        if (appComponent == null) {
            appComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this.clientConfig.getAppContext())).build();
        }
    }

    private void initJackson() {
        this.jacksonConverterFactory = createJacksonConverter();
    }

    private void initRetrofitBindings() {
        this.cdsCalls = new CDSCallsImpl(this.clientConfig, getRetrofitBuilder().baseUrl(this.clientConfig.getEndpointConfiguration().getMetadataUrl()).build());
        this.cdpCalls = new CDPCallsImpl(this.clientConfig, getRetrofitBuilder().baseUrl(this.clientConfig.getEndpointConfiguration().getContentUrl()).build());
        this.cdtsCalls = new CDTSCallsImpl(this.clientConfig, getRetrofitBuilder().baseUrl(this.clientConfig.getEndpointConfiguration().getThumbnailUrl()).build());
        this.promptoCalls = new PromptoCallsImpl(this.clientConfig, getRetrofitBuilder().baseUrl(this.clientConfig.getEndpointConfiguration().getPromptoServiceUrl()).build());
        this.apsCalls = new APSCallsImpl(this.clientConfig, getRetrofitBuilder(Collections.singletonList(new APSInterceptor(this.clientConfig))).baseUrl(this.clientConfig.getEndpointConfiguration().getApsServiceUrl()).build());
        this.oneLensCalls = new OneLensCallsImpl(this.clientConfig, getRetrofitBuilder().baseUrl("https://www.amazon.com/photos/api/").build());
        this.cdusCalls = new CDUSCallsImpl(this.clientConfig, getRetrofitBuilder(Collections.singletonList(new TraceInterceptor())).baseUrl(this.clientConfig.getEndpointConfiguration().getContentUrl()).build());
        this.cddsCalls = new CDDSCallsImpl(this.clientConfig, getRetrofitBuilder().baseUrl(this.clientConfig.getEndpointConfiguration().getContentUrl()).build());
        this.cdrsCalls = new CDRSCallsImpl(this.clientConfig, getRetrofitBuilder().baseUrl(this.clientConfig.getEndpointConfiguration().getCdrsServiceUrl()).build());
        this.dpsCalls = new DPSCallsImpl(this.clientConfig, getRetrofitBuilder().baseUrl(this.clientConfig.getEndpointConfiguration().getDpsServiceUrl()).build());
    }

    @VisibleForTesting
    public static void setAppComponent(@NonNull ApplicationComponent applicationComponent) {
        appComponent = applicationComponent;
    }

    @Override // com.amazon.clouddrive.cdasdk.CDClient
    @NonNull
    public APSCalls getAPSCalls() {
        return this.apsCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.CDClient
    @NonNull
    public CDDSCalls getCDDSCalls() {
        return this.cddsCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.CDClient
    @NonNull
    public CDPCalls getCDPCalls() {
        return this.cdpCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.CDClient
    @NonNull
    public CDRSCalls getCDRSCalls() {
        return this.cdrsCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.CDClient
    @NonNull
    public CDSCalls getCDSCalls() {
        return this.cdsCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.CDClient
    @NonNull
    public CDUSCalls getCDUSCalls() {
        return this.cdusCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.CDClient
    @NonNull
    public DPSCalls getDPSCalls() {
        return this.dpsCalls;
    }

    @NonNull
    @VisibleForTesting
    public OkHttpClient getOkHttpClient() {
        return this.baseHttpClient;
    }

    @Override // com.amazon.clouddrive.cdasdk.CDClient
    @NonNull
    public OneLensCalls getOneLensCalls() {
        return this.oneLensCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.CDClient
    @NonNull
    public PromptoCalls getPromptoCalls() {
        return this.promptoCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.CDClient
    @NonNull
    public CDTSCalls getThumbnailCalls() {
        return this.cdtsCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.CDClient
    public void updateEndpointConfiguration(@NonNull EndpointConfiguration endpointConfiguration) {
        appComponent.getLogger().i(TAG, "Given a new endpoint configuration to use");
        this.clientConfig.setEndpointConfiguration(endpointConfiguration);
        initRetrofitBindings();
    }

    @NonNull
    private Retrofit.Builder getRetrofitBuilder(@Nullable List<Interceptor> list) {
        OkHttpClient.Builder newBuilder = this.baseHttpClient.newBuilder();
        if (list != null) {
            for (Interceptor interceptor : list) {
                newBuilder.addInterceptor(interceptor);
            }
        }
        newBuilder.addInterceptor(new RequestLoggingInterceptor(this.clientConfig.getRequestLoggingConfig(), appComponent.getLogger(), appComponent.getSystemUtil()));
        return new Retrofit.Builder().client(newBuilder.build()).addConverterFactory(this.jacksonConverterFactory).addCallAdapterFactory(RxJava3CallAdapterFactory.create());
    }
}
