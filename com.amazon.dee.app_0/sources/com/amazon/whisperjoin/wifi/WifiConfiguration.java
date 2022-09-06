package com.amazon.whisperjoin.wifi;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable;
import com.amazon.whisperjoin.utils.InputValidator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.BitSet;
/* loaded from: classes13.dex */
public class WifiConfiguration extends WifiBaseConfiguration implements Serializable, Validatable {
    private String psk;
    private String wepKey;

    /* renamed from: com.amazon.whisperjoin.wifi.WifiConfiguration$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement = new int[WifiKeyManagement.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[WifiKeyManagement.WEP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[WifiKeyManagement.WPA_PSK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[WifiKeyManagement.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[WifiKeyManagement.OTHER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private WifiConfiguration(String str, WifiKeyManagement wifiKeyManagement, String str2, String str3, int i, boolean z) {
        super(str, wifiKeyManagement, i, z);
        if (wifiKeyManagement == WifiKeyManagement.WEP) {
            this.wepKey = getValidWepKey(str2);
        } else if (wifiKeyManagement == WifiKeyManagement.WPA_PSK) {
            this.psk = getValidPsk(str3);
        }
        validate();
    }

    public static WifiConfiguration createOpenWifiConfiguration(String str) {
        return new WifiConfiguration(str, WifiKeyManagement.NONE, null, null, 0, false);
    }

    public static WifiConfiguration createWepWifiConfiguration(String str, String str2) {
        return new WifiConfiguration(str, WifiKeyManagement.WEP, str2, null, 0, false);
    }

    public static WifiConfiguration createWpaWifiConfiguration(String str, String str2) {
        return new WifiConfiguration(str, WifiKeyManagement.WPA_PSK, null, str2, 0, false);
    }

    private String getValidPsk(String str) {
        if (str != null && str.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) && str.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED)) {
            return GeneratedOutlineSupport1.outline51(str, 1, 1);
        }
        throw new IllegalArgumentException("Invalid wifi configuration. PSK is not quoted. Please use com.amazon.whisperjoin.utils.InputValidator to validate wifi parameters.");
    }

    private String getValidWepKey(String str) {
        if (str == null || !str.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) || !str.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED)) {
            return str;
        }
        String outline51 = GeneratedOutlineSupport1.outline51(str, 1, 1);
        StringBuilder sb = new StringBuilder();
        for (char c : outline51.toCharArray()) {
            sb.append(Integer.toHexString(c).toUpperCase());
        }
        return sb.toString();
    }

    @Override // com.amazon.whisperjoin.wifi.WifiBaseConfiguration, com.amazon.whisperjoin.wifi.WifiNetwork
    protected boolean canEqual(Object obj) {
        return obj instanceof WifiConfiguration;
    }

    @Override // com.amazon.whisperjoin.wifi.WifiBaseConfiguration, com.amazon.whisperjoin.wifi.WifiNetwork
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WifiConfiguration)) {
            return false;
        }
        WifiConfiguration wifiConfiguration = (WifiConfiguration) obj;
        if (!wifiConfiguration.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String wepKey = getWepKey();
        String wepKey2 = wifiConfiguration.getWepKey();
        if (wepKey != null ? !wepKey.equals(wepKey2) : wepKey2 != null) {
            return false;
        }
        String psk = getPsk();
        String psk2 = wifiConfiguration.getPsk();
        return psk != null ? psk.equals(psk2) : psk2 == null;
    }

    public String getPsk() {
        if (this.psk == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED), this.psk, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
    }

    public String getWepKey() {
        return this.wepKey;
    }

    @Override // com.amazon.whisperjoin.wifi.WifiBaseConfiguration, com.amazon.whisperjoin.wifi.WifiNetwork
    public int hashCode() {
        int hashCode = super.hashCode();
        String wepKey = getWepKey();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (wepKey == null ? 43 : wepKey.hashCode());
        String psk = getPsk();
        int i2 = hashCode2 * 59;
        if (psk != null) {
            i = psk.hashCode();
        }
        return i2 + i;
    }

    public android.net.wifi.WifiConfiguration toAndroidConfiguration() throws UnsupportedOperationException {
        android.net.wifi.WifiConfiguration wifiConfiguration = new android.net.wifi.WifiConfiguration();
        wifiConfiguration.SSID = getSsid();
        wifiConfiguration.hiddenSSID = this.hidden;
        wifiConfiguration.priority = this.priority;
        wifiConfiguration.allowedKeyManagement = new BitSet();
        wifiConfiguration.allowedAuthAlgorithms = new BitSet();
        wifiConfiguration.allowedGroupCiphers = new BitSet();
        int ordinal = this.keyMgmt.ordinal();
        if (ordinal == 0) {
            wifiConfiguration.allowedKeyManagement.set(0);
        } else if (ordinal == 1) {
            wifiConfiguration.preSharedKey = getPsk();
            wifiConfiguration.allowedKeyManagement.set(1);
        } else if (ordinal == 2) {
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedAuthAlgorithms.set(1);
            wifiConfiguration.allowedGroupCiphers.set(0);
            wifiConfiguration.allowedGroupCiphers.set(1);
            String str = this.wepKey;
            if (str != null) {
                wifiConfiguration.wepTxKeyIndex = 0;
                wifiConfiguration.wepKeys[0] = str;
            }
        } else if (ordinal == 3) {
            throw new UnsupportedOperationException("Network type of OTHER key management not supported");
        }
        return wifiConfiguration;
    }

    @Override // com.amazon.whisperjoin.wifi.WifiBaseConfiguration, com.amazon.whisperjoin.wifi.WifiNetwork
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiConfiguration(wepKey=");
        outline107.append(getWepKey());
        outline107.append(", psk=");
        outline107.append(getPsk());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // com.amazon.whisperjoin.wifi.WifiNetwork, com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable
    public void validate() {
        super.validate();
        if (this.psk != null && this.wepKey != null) {
            throw new IllegalArgumentException("Wifi configuration cannot have both WEP-key and PSK. Please use com.amazon.whisperjoin.utils.InputValidator to validate wifi parameters.");
        }
        if (this.psk != null && !InputValidator.isValidWPAWifiConfiguration(this.keyMgmt, getPsk())) {
            throw new IllegalArgumentException("Invalid PSK in wifi configuration. Please use com.amazon.whisperjoin.utils.InputValidator to validate wifi parameters.");
        }
        String str = this.wepKey;
        if (str != null && !InputValidator.isValidWEPWifiConfiguration(this.keyMgmt, str)) {
            throw new IllegalArgumentException("Invalid WEP-key in wifi configuration. Please use com.amazon.whisperjoin.utils.InputValidator to validate wifi parameters.");
        }
        if (this.psk != null || this.wepKey != null || InputValidator.isValidOpenWifiConfiguration(this.keyMgmt)) {
            return;
        }
        throw new IllegalArgumentException("Invalid open wifi configuration. Please use com.amazon.whisperjoin.utils.InputValidator to validate wifi parameters.");
    }

    public static WifiConfiguration createOpenWifiConfiguration(String str, int i, boolean z) {
        return new WifiConfiguration(str, WifiKeyManagement.NONE, null, null, i, z);
    }

    public static WifiConfiguration createWepWifiConfiguration(String str, String str2, int i, boolean z) {
        return new WifiConfiguration(str, WifiKeyManagement.WEP, str2, null, i, z);
    }

    public static WifiConfiguration createWpaWifiConfiguration(String str, String str2, int i, boolean z) {
        return new WifiConfiguration(str, WifiKeyManagement.WPA_PSK, null, str2, i, z);
    }

    public WifiConfiguration(WifiConfiguration wifiConfiguration) {
        this(wifiConfiguration.getSsid(), wifiConfiguration.getKeyMgmt(), wifiConfiguration.getWepKey(), wifiConfiguration.getPsk(), wifiConfiguration.priority, false);
    }
}
