package com.amazon.dee.app.services.testing;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.dee.app.services.security.CertificateReaderService;
import dagger.Lazy;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes12.dex */
public class TestArgumentsService {
    @VisibleForTesting
    static final String CONFIGURATION_ACTION = "com.amazon.dee.CONFIGURATION";
    private final Map<String, String> arguments;
    private final Lazy<CertificateReaderService> lazyCertificateReaderService;

    /* loaded from: classes12.dex */
    public enum Argument {
        OVERRIDE_FEATURES_BLOCKLIST("config.overrideFeaturesBlocklist"),
        OVERRIDE_FEATURES_ALLOWLIST("config.overrideFeaturesAllowlist"),
        DISABLE_AUTOPAIRING("config.disableAutopairing"),
        DISABLE_RATEAPP("config.disableRateApp"),
        DISABLE_INTERSTITIALS("config.disableInterstitials"),
        LOGIN_NAME("config.loginName"),
        LOGIN_PASSWORD("config.loginPassword"),
        CRASH_REPORTING_ACCOUNT("config.overideFeaturesBlocklist"),
        FEATURE_REFRESH_TIME("config.fsv2.featureRefreshTime"),
        SESSION_CHANGE_THRESHOLD("config.fsv2.sessionChangeThreshold");
        
        private final String name;

        Argument(String str) {
            this.name = str;
        }

        @Override // java.lang.Enum
        @NonNull
        public String toString() {
            return this.name;
        }
    }

    public TestArgumentsService(Intent intent, Lazy<CertificateReaderService> lazy) {
        this.lazyCertificateReaderService = lazy;
        this.arguments = extractArguments(intent);
    }

    @NonNull
    @VisibleForTesting
    static Map<String, String> bundleToMap(Bundle bundle) {
        Set<String> keySet = bundle.keySet();
        HashMap hashMap = new HashMap();
        for (String str : keySet) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    @VisibleForTesting
    Map<String, String> extractArguments(Intent intent) {
        if (intent != null && CONFIGURATION_ACTION.equals(intent.getAction())) {
            return bundleToMap(intent.getExtras());
        }
        return Collections.EMPTY_MAP;
    }

    public Map<String, String> getArgs() {
        return !isEnabled() ? Collections.EMPTY_MAP : this.arguments;
    }

    public String getValue(Argument argument) {
        if (!isEnabled()) {
            return null;
        }
        return this.arguments.get(argument.toString());
    }

    @VisibleForTesting
    boolean isEnabled() {
        return this.lazyCertificateReaderService.mo358get().isDebugSigned();
    }

    public boolean isSet(Argument... argumentArr) {
        if (!isEnabled()) {
            return false;
        }
        for (Argument argument : argumentArr) {
            if (!this.arguments.containsKey(argument.toString())) {
                return false;
            }
        }
        return true;
    }
}
