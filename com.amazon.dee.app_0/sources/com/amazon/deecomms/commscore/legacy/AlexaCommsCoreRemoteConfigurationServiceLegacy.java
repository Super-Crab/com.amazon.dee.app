package com.amazon.deecomms.commscore.legacy;

import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.commscore.api.remoteconfiguration.RemoteConfigValue;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
/* loaded from: classes12.dex */
public class AlexaCommsCoreRemoteConfigurationServiceLegacy implements AlexaCommsCoreRemoteConfigurationService {
    private ArcusConfig arcusConfig;

    public AlexaCommsCoreRemoteConfigurationServiceLegacy(ArcusConfig arcusConfig) {
        this.arcusConfig = arcusConfig;
    }

    @Override // com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService
    public RemoteConfigValue getRemoteConfiguration(String str, final Object obj) {
        final Object configObject = this.arcusConfig.getConfigObject(str);
        return new RemoteConfigValue() { // from class: com.amazon.deecomms.commscore.legacy.AlexaCommsCoreRemoteConfigurationServiceLegacy.1
            @Override // com.amazon.commscore.api.remoteconfiguration.RemoteConfigValue
            public Object getRawValue() {
                return configObject;
            }

            @Override // com.amazon.commscore.api.remoteconfiguration.RemoteConfigValue
            public Integer toInteger() throws NumberFormatException, NullPointerException {
                Object obj2 = configObject;
                if (obj2 instanceof Integer) {
                    return (Integer) obj2;
                }
                if (obj2 instanceof Number) {
                    return Integer.valueOf(((Number) obj2).intValue());
                }
                if (obj2 instanceof String) {
                    try {
                        return Integer.valueOf((int) Double.parseDouble((String) obj2));
                    } catch (NumberFormatException unused) {
                    }
                }
                return (Integer) obj;
            }

            @Override // com.amazon.commscore.api.remoteconfiguration.RemoteConfigValue
            public String toString() {
                Object obj2 = configObject;
                if (obj2 instanceof String) {
                    return (String) obj2;
                }
                if (obj2 != null) {
                    return String.valueOf(obj2);
                }
                return (String) obj;
            }
        };
    }
}
