package com.amazon.alexa.devicesetup.impl;

import com.amazon.alexa.identity.MAPIdentityService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.amazon.dee.sdk.iotsoftap.IoTSoftApConfigurationProvider;
import com.amazon.dee.sdk.iotsoftap.RegistrationTokenProvider;
import com.amazon.whisperjoin.softap.pojos.ProvisioningData;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes7.dex */
public class IoTSoftAPConfigurationProviderImpl implements IoTSoftApConfigurationProvider {
    private final LazyComponent<EnvironmentService> environmentServiceLazyComponent;
    private final LazyComponent<IdentityService> identityServiceLazyComponent;

    public IoTSoftAPConfigurationProviderImpl(LazyComponent<EnvironmentService> lazyComponent, LazyComponent<IdentityService> lazyComponent2) {
        this.environmentServiceLazyComponent = lazyComponent;
        this.identityServiceLazyComponent = lazyComponent2;
    }

    private static String getUTCTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
        return simpleDateFormat.format(new Date());
    }

    @Override // com.amazon.dee.sdk.iotsoftap.IoTSoftApConfigurationProvider
    public ProvisioningData getProvisioningData() {
        EnvironmentService mo10268get = this.environmentServiceLazyComponent.mo10268get();
        String marketplaceId = mo10268get.getMarketplace().getObfuscatedId().toString();
        return ProvisioningData.builder().utcTime(getUTCTime()).marketplace(marketplaceId).countryCode(mo10268get.getCountryCode()).build();
    }

    @Override // com.amazon.dee.sdk.iotsoftap.IoTSoftApConfigurationProvider
    public RegistrationTokenProvider getRegistrationTokenProvider() {
        final MAPIdentityService mAPIdentityService = (MAPIdentityService) this.identityServiceLazyComponent.mo10268get();
        return new RegistrationTokenProvider() { // from class: com.amazon.alexa.devicesetup.impl.-$$Lambda$IoTSoftAPConfigurationProviderImpl$yZljuKIPN7t0QqKy5ANFJyLGPn8
            @Override // com.amazon.dee.sdk.iotsoftap.RegistrationTokenProvider
            public final Single get() {
                Single subscribeOn;
                subscribeOn = Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.devicesetup.impl.-$$Lambda$IoTSoftAPConfigurationProviderImpl$oHvGE3qiiERhl-k5j1N8Ku_58GA
                    @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
                    public final void subscribe(SingleEmitter singleEmitter) {
                        MAPIdentityService.this.generateCBLRegistrationToken().subscribe(new Consumer() { // from class: com.amazon.alexa.devicesetup.impl.-$$Lambda$IoTSoftAPConfigurationProviderImpl$6_ReYMYMFNF67lSmQapisEGaL38
                            @Override // io.reactivex.rxjava3.functions.Consumer
                            public final void accept(Object obj) {
                                SingleEmitter.this.onSuccess((String) obj);
                            }
                        }, new Consumer() { // from class: com.amazon.alexa.devicesetup.impl.-$$Lambda$IoTSoftAPConfigurationProviderImpl$95OxMEFQ1X-hWXOcoUTsOZkQlak
                            @Override // io.reactivex.rxjava3.functions.Consumer
                            public final void accept(Object obj) {
                                SingleEmitter.this.onError((Throwable) obj);
                            }
                        });
                    }
                }).subscribeOn(Schedulers.io());
                return subscribeOn;
            }
        };
    }
}
