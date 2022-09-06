package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation;

import com.amazon.whisperjoin.deviceprovisioningservice.error.PresenterInitializationException;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import org.apache.commons.lang.StringUtils;
/* loaded from: classes13.dex */
public class PresenterValidator {
    public static void validatePreconditions(ProvisionerClientData provisionerClientData) {
        if (!StringUtils.isEmpty(provisionerClientData.getClientAppName())) {
            if (!StringUtils.isEmpty(provisionerClientData.getClientAppVersion())) {
                if (!StringUtils.isEmpty(provisionerClientData.getDeviceModel())) {
                    if (!StringUtils.isEmpty(provisionerClientData.getDeviceFirmwareVersion())) {
                        if (!StringUtils.isEmpty(provisionerClientData.getDeviceManufacturer())) {
                            if (!StringUtils.isEmpty(provisionerClientData.getDeviceSerialNumber())) {
                                if (!StringUtils.isEmpty(provisionerClientData.getCustomerEcid())) {
                                    if (StringUtils.isEmpty(provisionerClientData.getMarketplace())) {
                                        throw new PresenterInitializationException("Marketplace must be set");
                                    }
                                    return;
                                }
                                throw new PresenterInitializationException("Customer ecid must be set");
                            }
                            throw new PresenterInitializationException("Provisioner DSN must be set");
                        }
                        throw new PresenterInitializationException("Device manufacturer must be set");
                    }
                    throw new PresenterInitializationException("Device firmware version must be set");
                }
                throw new PresenterInitializationException("Device model must be set");
            }
            throw new PresenterInitializationException("Client app version must be set");
        }
        throw new PresenterInitializationException("Client app name must be set");
    }
}
