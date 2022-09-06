package com.amazon.whisperjoin.softap.actions;

import com.amazon.whisperjoin.softap.observables.GetDeviceDetails;
import com.amazon.whisperjoin.softap.observables.GetPublicKey;
import com.amazon.whisperjoin.softap.observables.SaveProvisioningData;
import com.amazon.whisperjoin.softap.observables.SecureProvisionDevice;
import com.amazon.whisperjoin.softap.observables.SecureSaveRegistrationToken;
import com.amazon.whisperjoin.softap.observables.SendPublicKey;
import com.amazon.whisperjoin.softap.pojos.DeviceDetails;
import com.amazon.whisperjoin.softap.pojos.ProvisioningData;
import com.amazon.whisperjoin.softap.serializer.SerializerFactory;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilderProvider;
import rx.Single;
/* loaded from: classes13.dex */
public class SoftApOperationFactoryImpl implements SoftApOperationFactory {
    private final RequestBuilderProvider requestBuilderProvider = new RequestBuilderProvider.DefaultRequestBuilderProvider();

    @Override // com.amazon.whisperjoin.softap.actions.SoftApOperationFactory
    public Single<DeviceDetails> getDeviceDetails() {
        return new GetDeviceDetails(this.requestBuilderProvider, SerializerFactory.getInstance().getStringDeserializer(DeviceDetails.class)).observe();
    }

    @Override // com.amazon.whisperjoin.softap.actions.SoftApOperationFactory
    public Single<String> getPublicKey() {
        return new GetPublicKey(this.requestBuilderProvider).observe();
    }

    @Override // com.amazon.whisperjoin.softap.actions.SoftApOperationFactory
    public Single<Void> saveProvisioningData(ProvisioningData provisioningData) {
        return new SaveProvisioningData(this.requestBuilderProvider, SerializerFactory.getInstance().getByteSerializer(ProvisioningData.class), provisioningData).observe();
    }

    @Override // com.amazon.whisperjoin.softap.actions.SoftApOperationFactory
    public Single<Void> secureProvisionDevice(byte[] bArr) {
        return new SecureProvisionDevice(bArr, this.requestBuilderProvider).observe();
    }

    @Override // com.amazon.whisperjoin.softap.actions.SoftApOperationFactory
    public Single<Void> secureSaveRegistrationToken(byte[] bArr) {
        return new SecureSaveRegistrationToken(bArr, this.requestBuilderProvider).observe();
    }

    @Override // com.amazon.whisperjoin.softap.actions.SoftApOperationFactory
    public Single<Void> sendPublicKey(String str, int i) {
        return new SendPublicKey(str, i, this.requestBuilderProvider).observe();
    }
}
