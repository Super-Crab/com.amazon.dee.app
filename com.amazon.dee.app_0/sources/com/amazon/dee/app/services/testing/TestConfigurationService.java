package com.amazon.dee.app.services.testing;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.CrashReportingService;
import com.amazon.alexa.featureservice.configuration.FeatureServiceConfiguration;
import com.amazon.dee.app.services.appreviewrequest.AppReviewRequestService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.services.testing.TestArgumentsService;
import dagger.Lazy;
/* loaded from: classes12.dex */
public class TestConfigurationService {
    private static final String TAG = "TestConfigurationService";
    private final Lazy<AppReviewRequestService> appReviewRequestService;
    private final Lazy<CertificateReaderService> certificateReaderService;
    private final Lazy<CrashReportingService> crashReportingService;
    private final TestArgumentsService testArguments;
    private final Lazy<FeatureServiceConfiguration> testConfigurationLazy;

    public TestConfigurationService(TestArgumentsService testArgumentsService, Lazy<CrashReportingService> lazy, Lazy<CertificateReaderService> lazy2, Lazy<AppReviewRequestService> lazy3, Lazy<FeatureServiceConfiguration> lazy4) {
        this.testArguments = testArgumentsService;
        this.crashReportingService = lazy;
        this.certificateReaderService = lazy2;
        this.appReviewRequestService = lazy3;
        this.testConfigurationLazy = lazy4;
    }

    @VisibleForTesting
    void configureFeatureServiceV2() {
        Log.i(TAG, "Configuring FSV2 for test");
        FeatureServiceConfiguration mo358get = this.testConfigurationLazy.mo358get();
        if (mo358get == null) {
            Log.i(TAG, "FSV2 FeatureServiceConfiguration is null. Test Arguments were not applied");
            return;
        }
        try {
            if (this.testArguments.getValue(TestArgumentsService.Argument.FEATURE_REFRESH_TIME) != null) {
                mo358get.setEnabled(true);
                mo358get.setFeatureRefreshTime(Long.parseLong(this.testArguments.getValue(TestArgumentsService.Argument.FEATURE_REFRESH_TIME)));
            }
            if (this.testArguments.getValue(TestArgumentsService.Argument.SESSION_CHANGE_THRESHOLD) == null) {
                return;
            }
            mo358get.setEnabled(true);
            mo358get.setSessionUpdateThreshold(Long.parseLong(this.testArguments.getValue(TestArgumentsService.Argument.SESSION_CHANGE_THRESHOLD)));
        } catch (NumberFormatException e) {
            mo358get.setEnabled(false);
            Log.w(TAG, "Invalid Test Arguments", e);
        }
    }

    @VisibleForTesting
    void disableAppRating() {
        this.appReviewRequestService.mo358get().setAppReviewDisabled(true);
    }

    public void initialize() {
        if (!this.certificateReaderService.mo358get().isDebugSigned()) {
            return;
        }
        if (this.testArguments.isSet(TestArgumentsService.Argument.CRASH_REPORTING_ACCOUNT)) {
            setCrashAccountInfo();
        }
        if (this.testArguments.isSet(TestArgumentsService.Argument.DISABLE_RATEAPP)) {
            disableAppRating();
        }
        configureFeatureServiceV2();
    }

    @VisibleForTesting
    void setCrashAccountInfo() {
        String value = this.testArguments.getValue(TestArgumentsService.Argument.CRASH_REPORTING_ACCOUNT);
        if (value != null) {
            Log.i(TAG, "Setting crash account info.");
            String[] split = value.split(":");
            this.crashReportingService.mo358get().setAccount(split[0], split[1], split[2]);
        }
    }
}
