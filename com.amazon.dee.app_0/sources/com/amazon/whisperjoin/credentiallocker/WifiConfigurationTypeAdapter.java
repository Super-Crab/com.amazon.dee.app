package com.amazon.whisperjoin.credentiallocker;

import com.amazon.credentiallocker.CredentialConfiguration;
import com.amazon.whisperjoin.wifi.WifiConfiguration;
import com.amazon.whisperjoin.wifi.WifiKeyManagement;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class WifiConfigurationTypeAdapter implements JsonSerializer<WifiConfiguration>, JsonDeserializer<WifiConfiguration> {
    final Gson mGson = new Gson();

    /* renamed from: com.amazon.whisperjoin.credentiallocker.WifiConfigurationTypeAdapter$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement = new int[WifiKeyManagement.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[WifiKeyManagement.WPA_PSK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[WifiKeyManagement.WEP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[WifiKeyManagement.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private boolean isWepNetwork(com.amazon.credentiallocker.WepKeyConfiguration wepKeyConfiguration) {
        if (wepKeyConfiguration == null || wepKeyConfiguration.getWepKeyList() == null) {
            return false;
        }
        for (String str : wepKeyConfiguration.getWepKeyList()) {
            if (!Strings.isNullOrEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    private String toLockerKeyManagement(WifiKeyManagement wifiKeyManagement) {
        int ordinal = wifiKeyManagement.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return "WPAPSK";
            }
            if (ordinal != 2) {
                throw new JsonParseException(String.format(Locale.ENGLISH, "Unsuppored KeyManagement scheme: %s", wifiKeyManagement));
            }
            return "NONE";
        }
        return "NONE";
    }

    private WifiKeyManagement toWhisperjoinKeyManagement(com.amazon.credentiallocker.WifiConfiguration wifiConfiguration) {
        if ("WPAPSK".equals(wifiConfiguration.getKeyManagement())) {
            return WifiKeyManagement.WPA_PSK;
        }
        CredentialConfiguration credentialConfiguration = wifiConfiguration.getCredentialConfiguration();
        if ("NONE".equals(wifiConfiguration.getKeyManagement()) && credentialConfiguration != null && isWepNetwork(credentialConfiguration.getWepKeyConfiguration())) {
            return WifiKeyManagement.WEP;
        }
        if ("NONE".equals(wifiConfiguration.getKeyManagement())) {
            return WifiKeyManagement.NONE;
        }
        throw new JsonParseException("Unsuppored KeyManagement scheme for network");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.JsonDeserializer
    /* renamed from: deserialize */
    public WifiConfiguration mo5482deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String str;
        String str2;
        com.amazon.credentiallocker.WifiConfiguration wifiConfiguration = (com.amazon.credentiallocker.WifiConfiguration) this.mGson.fromJson(jsonElement, (Class<Object>) com.amazon.credentiallocker.WifiConfiguration.class);
        String ssid = wifiConfiguration.getSsid();
        WifiKeyManagement whisperjoinKeyManagement = toWhisperjoinKeyManagement(wifiConfiguration);
        if (wifiConfiguration.getCredentialConfiguration() != null) {
            com.amazon.credentiallocker.WepKeyConfiguration wepKeyConfiguration = wifiConfiguration.getCredentialConfiguration().getWepKeyConfiguration();
            if (whisperjoinKeyManagement.equals(WifiKeyManagement.WEP) && wepKeyConfiguration != null) {
                List<String> wepKeyList = wepKeyConfiguration.getWepKeyList();
                str2 = (wepKeyList == null || wepKeyList.size() < 1) ? null : wepKeyList.get(0);
                str = null;
            } else {
                str = wifiConfiguration.getCredentialConfiguration().getPrivateSharedKey();
                str2 = null;
            }
        } else {
            str = null;
            str2 = null;
        }
        int priority = wifiConfiguration.getPriority();
        try {
            int ordinal = whisperjoinKeyManagement.ordinal();
            if (ordinal == 0) {
                return WifiConfiguration.createOpenWifiConfiguration(ssid, priority, false);
            }
            if (ordinal == 1) {
                return WifiConfiguration.createWpaWifiConfiguration(ssid, str, priority, false);
            }
            if (ordinal == 2) {
                return WifiConfiguration.createWepWifiConfiguration(ssid, str2, priority, false);
            }
            throw new JsonParseException("Unsuppored KeyManagement scheme for network");
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    @Override // com.google.gson.JsonSerializer
    public JsonElement serialize(WifiConfiguration wifiConfiguration, Type type, JsonSerializationContext jsonSerializationContext) {
        com.amazon.credentiallocker.WifiConfiguration wifiConfiguration2 = new com.amazon.credentiallocker.WifiConfiguration();
        wifiConfiguration2.setSsid(wifiConfiguration.getSsid());
        wifiConfiguration2.setBssid(null);
        wifiConfiguration2.setKeyManagement(toLockerKeyManagement(wifiConfiguration.getKeyMgmt()));
        wifiConfiguration2.setPriority(wifiConfiguration.getPriority());
        if ("WPAPSK".equals(wifiConfiguration2.getKeyManagement())) {
            CredentialConfiguration credentialConfiguration = new CredentialConfiguration();
            credentialConfiguration.setPrivateSharedKey(wifiConfiguration.getPsk());
            wifiConfiguration2.setCredentialConfiguration(credentialConfiguration);
        } else if (WifiKeyManagement.WEP.equals(wifiConfiguration.getKeyMgmt())) {
            com.amazon.credentiallocker.WepKeyConfiguration wepKeyConfiguration = new com.amazon.credentiallocker.WepKeyConfiguration();
            wepKeyConfiguration.setKeyIndex(0);
            wepKeyConfiguration.setWepKeyList(ImmutableList.of(wifiConfiguration.getWepKey()));
            CredentialConfiguration credentialConfiguration2 = new CredentialConfiguration();
            credentialConfiguration2.setWepKeyConfiguration(wepKeyConfiguration);
            wifiConfiguration2.setCredentialConfiguration(credentialConfiguration2);
        }
        return this.mGson.toJsonTree(wifiConfiguration2);
    }
}
