package amazon.speech.options.client;
/* loaded from: classes.dex */
public enum CsmOption {
    WW_EXTENSIVE_PREROLL("persist.sys.csm.extPreroll", "ww_extensive_preroll", null),
    ALEXA_SERVER_ENDPOINT("persist.amazon.scl.host", "persist.amazon.scl.host", "persist.amazon.scl.host.priority"),
    ALEXA_SERVER_PORT("persist.amazon.scl.port", "persist.amazon.scl.port", null),
    ALEXA_SERVER_HTTP_PROXY_ENDPOINT("persist.amazon.scl.proxyhost", null, null),
    ALEXA_SERVER_HTTP_PROXY_PORT("persist.amazon.scl.proxyport", null, null),
    WAKEWORD_KILL_SWITCH("persist.sys.csm.wakeword", "wakeword_disable_default", null),
    DATACOLLECTOR_SERVICE_ENABLED("persist.amazon.dcs", "datacollector_service_enabled", null),
    ALEXA_DCF_ENDPOINT("persist.amazon.dcf.host", "persist.amazon.dcf.host", null),
    ALEXA_DCF_PORT("persist.amazon.dcf.port", "persist.amazon.dcf.port", null),
    MULTICHANNEL_AUDIO_SUPPORT_SERVER_PORT("persist.amazon.multichannel", "persist.amazon.multichannel", null),
    DISABLE_AHE("persist.AHE.disable_ahe", "disable_ahe", null),
    DISABLE_HYBRID_ROUTER("persist.AHE.disable_hr", "disable_hr", null),
    ENABLE_MULTICHANNEL("persist.amazon.multiaudio", "enable_multichannel_audio", null),
    ENABLE_ADAPTIVE_VOLUME("persist.amazon.adaptivevolume", "enable_adaptive_volume", null),
    ENABLE_ACL("persist.amazon.acl", "enable_acl", null),
    ENABLE_ACL_HYBRIDROUTER("persist.amazon.acl_hybridrouter", "enable_acl_hybridrouter", null),
    ENABLE_GENERIC_CONNECTION("persist.amazon.spc", "enable_spc", null),
    ENABLE_REGGAE("persist.amazon.reggae", "enable_reggae", null),
    ARCUS_INTERVAL_SYS_SETTING("persist.amazon.arcus.interval", null, null);
    
    private final String mPropertyName;
    private final String mRemoteSettingsKey;
    private final String mRemoteSettingsPriority;

    CsmOption(String str, String str2, String str3) {
        if (str != null && str.length() > 31) {
            throw new IllegalArgumentException("System property cannot exceed length of 31 characters");
        }
        this.mPropertyName = str;
        this.mRemoteSettingsKey = str2;
        this.mRemoteSettingsPriority = str3;
    }

    public String getPropertyName() {
        return this.mPropertyName;
    }

    public String getRemoteSettingsKey() {
        return this.mRemoteSettingsKey;
    }

    public String getRemoteSettingsPriority() {
        return this.mRemoteSettingsPriority;
    }
}
