package com.amazon.alexa.devicesetup.sdk.whisperjoin.zerotouch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.alexa.devicesetup.sdk.whisperjoin.FFSConfigurationProvider;
import com.amazon.alexa.devicesetup.sdk.whisperjoin.helper.ProvisioningConfigGenerator;
import com.amazon.alexa.devicesetup.service.ServiceWrapper;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.locale.LocaleConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
/* loaded from: classes7.dex */
public class BackgroundProvisioningService implements ServiceWrapper {
    private Context context;
    private LocaleConfiguration localeConfiguration;
    private ProvisioningServiceConfiguration serviceConfiguration;
    private BackgroundProvisioningServiceConnection serviceConnection;

    public BackgroundProvisioningService(Context context, FFSConfigurationProvider fFSConfigurationProvider) {
        this.context = context;
        this.serviceConfiguration = ProvisioningConfigGenerator.getProvisioningServiceConfiguration(fFSConfigurationProvider.getProvisioningServiceConfiguration());
        this.serviceConnection = new BackgroundProvisioningServiceConnection(this.serviceConfiguration);
    }

    @Override // com.amazon.alexa.devicesetup.service.ServiceWrapper
    public void start() {
        Bundle bundle = new Bundle();
        this.serviceConfiguration.writeToBundle(bundle);
        Intent intent = new Intent("com.amazon.whisperjoin.deviceprovisioningservice.BindService");
        intent.setPackage(this.context.getPackageName());
        intent.putExtras(bundle);
        this.context.startService(intent);
        this.context.bindService(intent, this.serviceConnection, 1);
    }

    public void startProvisioningService() {
        BackgroundProvisioningServiceConnection backgroundProvisioningServiceConnection = this.serviceConnection;
        if (backgroundProvisioningServiceConnection != null) {
            backgroundProvisioningServiceConnection.startService();
        }
    }

    @Override // com.amazon.alexa.devicesetup.service.ServiceWrapper
    public void stop() {
        stopProvisioningService();
    }

    public void stopProvisioningService() {
        BackgroundProvisioningServiceConnection backgroundProvisioningServiceConnection = this.serviceConnection;
        if (backgroundProvisioningServiceConnection != null) {
            backgroundProvisioningServiceConnection.stopService();
        }
    }
}
