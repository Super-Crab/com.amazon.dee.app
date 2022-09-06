package com.amazon.deecomms.calling.util;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.amazon.deecomms.settings.IdentityPreferencesProvider;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.ShortNumberInfo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Func0;
import rx.schedulers.Schedulers;
/* loaded from: classes12.dex */
public final class CoboUtils {
    private static final String DEFAULT_REGION = "US";
    private static final String PHONE_NUMBER = "PHONE_NUMBER";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CoboUtils.class);
    private static final Pattern PATTERN_PHONE_NUMBER = Pattern.compile("(sips:([^@]+))");
    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    private static final ShortNumberInfo shortNumberInfo = ShortNumberInfo.getInstance();
    private static final Dependencies sDependencies = new Dependencies();

    /* loaded from: classes12.dex */
    public static class Dependencies {
        @Inject
        public CapabilitiesManager capabilitiesManager;
    }

    static {
        CommsDaggerWrapper.getComponent().inject(sDependencies);
    }

    private CoboUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Observable<Boolean> deferredSubscribeForGetter() {
        try {
            LOG.d("Retrieving user's caller id preference from ACMS...");
            boolean fetchShowPhoneNumberToCalleePreference = fetchShowPhoneNumberToCalleePreference();
            LOG.d("Retrieved user's caller id preference from ACMS.");
            return Observable.just(Boolean.valueOf(fetchShowPhoneNumberToCalleePreference));
        } catch (ServiceException e) {
            LOG.e("Could not retrieve user's caller id preference from ACMS.", e);
            return Observable.error(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Observable<Boolean> deferredSubscribeForSetter(boolean z) {
        try {
            LOG.d("Updating caller id preference on ACMS...");
            saveShowPhoneNumberToCalleePreference(z);
            LOG.d("Updated caller id preference on ACMS.");
            return Observable.just(Boolean.valueOf(z));
        } catch (ServiceException e) {
            LOG.e("Could not update caller id preference on ACMS.", e);
            return Observable.error(e);
        }
    }

    private static boolean fetchShowPhoneNumberToCalleePreference() throws ServiceException {
        return !Boolean.valueOf(new IdentityPreferencesProvider(IdentityPreferencesProvider.AuthenticationType.AS_COMMS_USER).get(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("fetchShowPhoneNumberToCalleePreference", false), Constants.IDENTITY_PREFERENCE_COBO_PRIVATE_NUMBER)).booleanValue();
    }

    @Nullable
    public static String formatPhoneNumber(@NonNull String str) {
        return formatPhoneNumber(str, PhoneNumberUtil.PhoneNumberFormat.E164);
    }

    @Nullable
    public static String formatPhoneNumberForDisplay(@Nullable String str) {
        return formatPhoneNumber(str, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
    }

    @Nullable
    public static String generatePstnSipUri(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Invalid phone number: ");
            outline1.append(LOG.sensitive(str));
            commsLogger.e(outline1.toString());
            return null;
        }
        String pstnUriFormat = getPstnUriFormat();
        if (TextUtils.isEmpty(pstnUriFormat)) {
            LOG.e("Invalid pstn uri format: null");
            return null;
        }
        String formatPhoneNumber = formatPhoneNumber(str);
        if (formatPhoneNumber != null) {
            return pstnUriFormat.replace("PHONE_NUMBER", formatPhoneNumber);
        }
        return pstnUriFormat.replace("PHONE_NUMBER", str);
    }

    @Nullable
    public static String getPhoneNumberFromPstnSipUri(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Matcher matcher = PATTERN_PHONE_NUMBER.matcher(str);
        if (!matcher.find()) {
            return null;
        }
        return matcher.group(matcher.groupCount());
    }

    @Nullable
    static String getPstnUriFormat() {
        return CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.SIP_PSTN_URI_FORMAT_KEY);
    }

    public static Observable<Boolean> getShowPhoneNumberToCalleeObservable() {
        return Observable.defer($$Lambda$CoboUtils$9A030bjpzlRjgJ4vpRz1a_0a3cE.INSTANCE).subscribeOn(Schedulers.io());
    }

    public static boolean isCallerIDToggleAvailable() {
        if (!sDependencies.capabilitiesManager.getCoboValue()) {
            LOG.d("User is not permitted to use COBO callerID toggle .");
            return false;
        }
        return true;
    }

    public static boolean isCoboCallingAvailable() {
        if (!sDependencies.capabilitiesManager.getCyborgValue()) {
            LOG.d("User is not permitted to use COBO.");
            return false;
        }
        return true;
    }

    private static void saveShowPhoneNumberToCalleePreference(boolean z) throws ServiceException {
        new IdentityPreferencesProvider(IdentityPreferencesProvider.AuthenticationType.AS_COMMS_USER).set(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("saveShowPhoneNumberToCalleePreference", false), Constants.IDENTITY_PREFERENCE_COBO_PRIVATE_NUMBER, Boolean.valueOf(!z));
    }

    public static Observable<Boolean> setShowPhoneNumberToCalleeObservable(final boolean z) {
        return Observable.defer(new Func0() { // from class: com.amazon.deecomms.calling.util.-$$Lambda$CoboUtils$RMnu545wrA8kNtFaokW0xSGJiUE
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            /* renamed from: call */
            public final Object mo13098call() {
                Observable deferredSubscribeForSetter;
                deferredSubscribeForSetter = CoboUtils.deferredSubscribeForSetter(z);
                return deferredSubscribeForSetter;
            }
        }).subscribeOn(Schedulers.io());
    }

    public static boolean shouldShowPSTNForNonCOBORegion() {
        return sDependencies.capabilitiesManager.showPSTNForNonCOBORegions();
    }

    @Nullable
    private static String formatPhoneNumber(@Nullable String str, @NonNull PhoneNumberUtil.PhoneNumberFormat phoneNumberFormat) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Phonenumber.PhoneNumber parse = phoneNumberUtil.parse(str, "US");
            if (!shortNumberInfo.isPossibleShortNumber(parse)) {
                return phoneNumberUtil.format(parse, phoneNumberFormat);
            }
        } catch (NumberParseException e) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Could not format phone number: ");
            outline1.append(LOG.sensitive(str));
            commsLogger.e(outline1.toString(), e);
        }
        return str;
    }
}
