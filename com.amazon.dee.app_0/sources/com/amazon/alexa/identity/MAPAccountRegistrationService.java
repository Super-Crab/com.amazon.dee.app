package com.amazon.alexa.identity;

import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.identity.api.Metric;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import com.google.common.base.Strings;
import java.util.Set;
/* loaded from: classes9.dex */
public class MAPAccountRegistrationService {
    public static final String REGISTER_SECONDARY_ACCOUNT_FAILED = "registerSecondaryAccount failed";
    private static final String TAG = Utils.tag(MAPAccountRegistrationService.class);
    private final String deviceNameTemplate;
    private final EnvironmentService environmentService;
    private final MAPAccountManager mapAccountManager;
    private final MetricsService metricsService;
    private final Mobilytics mobilytics;

    public MAPAccountRegistrationService(MAPAccountManager mAPAccountManager, String str) {
        this.mapAccountManager = mAPAccountManager;
        this.deviceNameTemplate = str;
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        this.environmentService = (EnvironmentService) componentRegistry.get(EnvironmentService.class).get();
        this.metricsService = (MetricsService) componentRegistry.get(MetricsService.class).get();
        this.mobilytics = (Mobilytics) componentRegistry.get(Mobilytics.class).get();
    }

    private Bundle bundleForRegisteringAccount(String str, Set<String> set) {
        Bundle outline11 = GeneratedOutlineSupport1.outline11("com.amazon.dcp.sso.property.account.acctId", str);
        outline11.putString("com.amazon.dcp.sso.property.account.delegateeaccount", this.mapAccountManager.getAccount());
        outline11.putBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary", true);
        outline11.putString("device_name", this.deviceNameTemplate);
        if (set != null && set.contains(IdentityFeature.AIS_EXP_ANDROID_MAP_ACCOUNT_REGISTRATION)) {
            outline11.putString("com.amazon.identity.ap.domain", ".amazon.com");
        } else {
            outline11.putString("com.amazon.identity.ap.domain", this.environmentService.getAuthWebHost());
        }
        return outline11;
    }

    private void recordMobilyticsCriticalEvent(String str) {
        this.mobilytics.recordCriticalEvent(str, REGISTER_SECONDARY_ACCOUNT_FAILED, TAG, "registerSecondaryAccount", (Throwable) null, "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
    }

    public boolean registerSecondaryAccount(String str, Set<String> set) {
        Bundle bundle;
        boolean z = true;
        boolean z2 = false;
        try {
            try {
                try {
                    try {
                        Log.i(TAG, Utils.V2_LOG_PREFIX + String.format("request to register secondary profile with directedId %s domain %s with MAP", str, this.environmentService.getAuthWebHost()));
                        this.metricsService.startTimer(Metric.Event.REGISTER_SECONDARY_ACCOUNT_DURATION, MAPAccountRegistrationService.class.getSimpleName(), Utils.CUSTOM_ENTRIES);
                        bundle = this.mapAccountManager.registerAccount(RegistrationType.REGISTER_DELEGATED_ACCOUNT, bundleForRegisteringAccount(str, set), null).get();
                    } catch (Throwable th) {
                        th = th;
                        this.metricsService.recordOccurrence(Metric.Event.REGISTER_SECONDARY_ACCOUNT_SUCCESS, TAG, z, Utils.CUSTOM_ENTRIES);
                        this.metricsService.recordTimer(Metric.Event.REGISTER_SECONDARY_ACCOUNT_DURATION);
                        throw th;
                    }
                } catch (MAPCallbackErrorException e) {
                    e = e;
                }
            } catch (Exception e2) {
                Log.e(TAG, "IdentityV2 : Secondary account registration failed. Received error: ", e2);
            }
            try {
            } catch (MAPCallbackErrorException e3) {
                e = e3;
                z = false;
                Bundle errorBundle = e.getErrorBundle();
                if (errorBundle == null || errorBundle.getInt("com.amazon.dcp.sso.ErrorCode") != MAPAccountManager.RegistrationError.ACCOUNT_ALREADY_EXISTS.value()) {
                    if (errorBundle != null) {
                        String str2 = "IdentityV2 : Secondary account registration failed. Received error: " + errorBundle.getString("com.amazon.dcp.sso.ErrorMessage");
                        recordMobilyticsCriticalEvent(str2, e);
                        Log.e(TAG, str2, e);
                    } else {
                        Log.e(TAG, "IdentityV2 : Secondary account registration failed. Received error: ", e);
                        recordMobilyticsCriticalEvent("IdentityV2 : Secondary account registration failed. Received error: ", e);
                    }
                    this.metricsService.recordOccurrence(Metric.Event.REGISTER_SECONDARY_ACCOUNT_SUCCESS, TAG, z2, Utils.CUSTOM_ENTRIES);
                    this.metricsService.recordTimer(Metric.Event.REGISTER_SECONDARY_ACCOUNT_DURATION);
                    return z2;
                }
                Log.i(TAG, "IdentityV2 : Secondary account registration successful. Account was already registered.");
                recordMobilyticsCriticalEvent("IdentityV2 : Secondary account registration successful. Account was already registered.", e);
                z2 = z;
                this.metricsService.recordOccurrence(Metric.Event.REGISTER_SECONDARY_ACCOUNT_SUCCESS, TAG, z2, Utils.CUSTOM_ENTRIES);
                this.metricsService.recordTimer(Metric.Event.REGISTER_SECONDARY_ACCOUNT_DURATION);
                return z2;
            }
            if (bundle == null) {
                Log.e(TAG, "IdentityV2 : Secondary account registration failed. Result from MAP was null!");
                recordMobilyticsCriticalEvent("IdentityV2 : Secondary account registration failed. Result from MAP was null!");
            } else if (bundle.containsKey("com.amazon.dcp.sso.ErrorCode")) {
                String string = bundle.getString("com.amazon.dcp.sso.ErrorMessage");
                if (!Strings.isNullOrEmpty(string)) {
                    String str3 = "IdentityV2 : Secondary account registration failed. Received error: " + string;
                    Log.e(TAG, str3);
                    recordMobilyticsCriticalEvent(str3);
                } else {
                    Log.e(TAG, "IdentityV2 : Secondary account registration failed. No error provided");
                    recordMobilyticsCriticalEvent("IdentityV2 : Secondary account registration failed. No error provided");
                }
            } else {
                Log.i(TAG, "IdentityV2 : Secondary account registration successful.");
                z2 = z;
                this.metricsService.recordOccurrence(Metric.Event.REGISTER_SECONDARY_ACCOUNT_SUCCESS, TAG, z2, Utils.CUSTOM_ENTRIES);
                this.metricsService.recordTimer(Metric.Event.REGISTER_SECONDARY_ACCOUNT_DURATION);
                return z2;
            }
            this.metricsService.recordOccurrence(Metric.Event.REGISTER_SECONDARY_ACCOUNT_SUCCESS, TAG, z2, Utils.CUSTOM_ENTRIES);
            this.metricsService.recordTimer(Metric.Event.REGISTER_SECONDARY_ACCOUNT_DURATION);
            return z2;
        } catch (Throwable th2) {
            th = th2;
            z = false;
            this.metricsService.recordOccurrence(Metric.Event.REGISTER_SECONDARY_ACCOUNT_SUCCESS, TAG, z, Utils.CUSTOM_ENTRIES);
            this.metricsService.recordTimer(Metric.Event.REGISTER_SECONDARY_ACCOUNT_DURATION);
            throw th;
        }
    }

    private void recordMobilyticsCriticalEvent(String str, Throwable th) {
        this.mobilytics.recordCriticalEvent(str, REGISTER_SECONDARY_ACCOUNT_FAILED, TAG, "registerSecondaryAccount", th, "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
    }
}
