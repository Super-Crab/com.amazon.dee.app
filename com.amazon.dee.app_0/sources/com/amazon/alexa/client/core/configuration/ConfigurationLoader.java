package com.amazon.alexa.client.core.configuration;

import android.net.Uri;
import android.text.TextUtils;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public abstract class ConfigurationLoader implements Loader<ClientConfiguration.Builder> {
    private static final Map<String, Feature> PROPERTIES_KEY_TO_FEATURE_MAP;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("wakeword_activation_sound_enabled", Feature.WAKEWORD_ACTIVATION_SOUND);
        hashMap.put("touch_initiated_activation_sound_enabled", Feature.TOUCH_ACTIVATION_SOUND);
        PROPERTIES_KEY_TO_FEATURE_MAP = Collections.unmodifiableMap(hashMap);
    }

    private Set<Locale> getLocaleSet(String str) {
        Set<String> stringSet = getStringSet(str);
        if (stringSet == null || stringSet.isEmpty()) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (String str2 : stringSet) {
            hashSet.add(Locale.forLanguageTag(str2));
        }
        return hashSet;
    }

    private void loadFeatures(ClientConfiguration.Builder builder) {
        Set<Feature> hashSet = new HashSet<>();
        Set<Feature> hashSet2 = new HashSet<>();
        for (Map.Entry<String, Feature> entry : PROPERTIES_KEY_TO_FEATURE_MAP.entrySet()) {
            Boolean bool = getBoolean(entry.getKey());
            if (bool != null) {
                if (bool.booleanValue()) {
                    hashSet.add(entry.getValue());
                } else {
                    hashSet2.add(entry.getValue());
                }
            }
        }
        builder.enableFeatures(hashSet).disableFeatures(hashSet2);
    }

    protected abstract Boolean getBoolean(String str);

    protected abstract Long getLong(String str);

    protected abstract String getString(String str);

    protected abstract Set<String> getStringSet(String str);

    @Override // com.amazon.alexa.client.core.configuration.Loader
    public void load(ClientConfiguration.Builder builder) {
        loadFeatures(builder);
        Stage from = Stage.from(getString("stage"));
        if (from != null) {
            builder.setStage(from);
        }
        String string = getString("avs_endpoint");
        if (string != null) {
            builder.setAvsEndpoint(Uri.parse(string));
        }
        String string2 = getString("capabilities_endpoint");
        if (string2 != null) {
            builder.setCapabilitiesEndpoint(Uri.parse(string2));
        }
        Set<Locale> localeSet = getLocaleSet("supported_locales");
        if (localeSet != null) {
            builder.setSupportedLocales(localeSet);
        }
        Boolean bool = getBoolean("ECA_V2");
        if (bool != null) {
            builder.setECAV2(bool);
        }
        Set<Locale> localeSet2 = getLocaleSet("actual_supported_locales");
        if (localeSet2 != null) {
            builder.setActualSupportedLocales(localeSet2);
        }
        String string3 = getString("metrics_device_type");
        if (string3 != null) {
            builder.setMetricsDeviceType(string3);
        }
        Long l = getLong("network_total_write_timeout_seconds");
        if (l != null) {
            builder.setNetworkTotalWriteTimeout(l);
        }
        Long l2 = getLong("first_turn_network_total_write_timeout_seconds");
        if (l2 != null) {
            builder.setFirstTurnNetworkTotalWriteTimeout(l2);
        }
        Long l3 = getLong("network_write_bytes_timeout_milliseconds");
        if (l3 != null) {
            builder.setNetworkWriteBytesTimeout(l3);
        }
        Long l4 = getLong("max_utterance_duration");
        if (l4 != null) {
            builder.setMaxUtteranceDuration(l4);
        }
        String string4 = getString("aws_ma_application_id");
        if (string4 != null) {
            builder.setAwsMobileAnalyticsApplicationId(string4);
        }
        String string5 = getString("aws_ma_identity_pool_id");
        if (string5 != null) {
            builder.setAwsMobileAnalyticsIdentityPoolId(string5);
        }
        String string6 = getString("metrics_kinesis_stream_name");
        if (string6 != null) {
            builder.setMobilyticsKinesisStreamName(string6);
        }
        String string7 = getString("metrics_kinesis_cognito_pool_id");
        if (string7 != null) {
            builder.setMobilyticsKinesisCognitoIdentityPoolId(string7);
        }
        String string8 = getString("metrics_kinesis_region");
        if (string8 != null) {
            builder.setMobilyticsKinesisRegion(string8);
        }
        Long l5 = getLong("playback_resuming_timeout");
        if (l5 != null) {
            builder.setPlaybackResumingTimeout(l5);
        }
        Boolean bool2 = getBoolean("remove_notification_on_teardown");
        if (bool2 != null) {
            builder.setRemoveNotificationOnTeardown(bool2);
        }
        String string9 = getString("metrics_service_name");
        if (!TextUtils.isEmpty(string9)) {
            builder.setMetricsServiceName(string9);
        }
        String string10 = getString("backup_ww_model");
        if (!TextUtils.isEmpty(string10)) {
            builder.setBackupWakeWordModelAssetFileName(string10);
        }
        String string11 = getString("alexa_profile_override");
        if (!TextUtils.isEmpty(string11)) {
            builder.setAlexaProfileOverride(string11);
        }
        Boolean bool3 = getBoolean("location_permissions_allowed");
        if (bool3 != null) {
            builder.setLocationPermissionsAllowed(bool3);
        }
        Boolean bool4 = getBoolean("multi_ww_enabled");
        if (bool4 != null) {
            builder.setMultiWwEnabled(bool4);
        }
        Boolean bool5 = getBoolean("hardcoded_capabilities_disabled");
        if (bool5 != null) {
            builder.setHardcodedCapabilitiesDisabled(bool5);
        }
        String string12 = getString("datamart_namespace");
        if (!TextUtils.isEmpty(string12)) {
            builder.setDatamartNamespace(string12);
        }
        Boolean bool6 = getBoolean("supports_pfm_changed");
        if (bool6 != null) {
            builder.setSupportsPfmChanged(bool6);
        }
    }
}
