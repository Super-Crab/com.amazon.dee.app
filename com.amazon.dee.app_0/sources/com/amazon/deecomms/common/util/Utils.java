package com.amazon.deecomms.common.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.mode.ModeName;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.sipclient.SipStatusCode;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.OobeService;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.auth.CommsSharedPreferences;
import com.amazon.deecomms.auth.SecuredSharedPreference;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.deecomms.calling.controller.DeviceCallingServiceEventListener;
import com.amazon.deecomms.calling.enums.DeviceCommsAvailability;
import com.amazon.deecomms.calling.enums.DropInAvailability;
import com.amazon.deecomms.calling.model.MediaRelayInfoModel;
import com.amazon.deecomms.calling.telecom.TelecomConstants;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.calling.util.DialogUtils;
import com.amazon.deecomms.calling.util.SetupCallHelper;
import com.amazon.deecomms.calling.util.TelecomUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.contacts.presence.ContactPresenceService;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.exceptions.DeviceTargetingException;
import com.amazon.deecomms.media.model.MediaFileContent;
import com.amazon.deecomms.media.photos.MediaContentManager;
import com.amazon.deecomms.messaging.provider.MessagingProviderWrapper;
import com.amazon.deecomms.ndt.DevicesSource;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.PersonalizedAccount;
import com.amazon.deecomms.util.BiConsumer;
import com.amazon.deecomms.util.ThreadUtils;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;
/* loaded from: classes12.dex */
public final class Utils {
    private static final String APP_NAME = "AlexaApp";
    private static final int BUFFER_SIZE = 2048;
    private static final String NETWORK_NOT_SET = "Not Set";
    private static final String NETWORK_OFFLINE = "Offline";
    private static final int US_COUNTRY_CODE = 1;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, Utils.class);
    private static final Boolean IS_FIRE_OS = Boolean.valueOf(computeIsFireOS());
    private static Boolean sIsSSO = null;
    private static int sWindowWidth = -1;
    private static int sMessageRowHorizontalMargin = -1;
    private static final List<DeviceModel> emptyList = new ArrayList(0);

    /* renamed from: com.amazon.deecomms.common.util.Utils$3  reason: invalid class name */
    /* loaded from: classes12.dex */
    static class AnonymousClass3 implements Observable.OnSubscribe<T> {
        final /* synthetic */ Context val$context;
        final /* synthetic */ Function val$function;
        final /* synthetic */ Intent val$intent;

        AnonymousClass3(Function function, Context context, Intent intent) {
            this.val$function = function;
            this.val$context = context;
            this.val$intent = intent;
        }

        @Override // rx.functions.Action1
        public void call(final Subscriber<? super T> subscriber) {
            final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.deecomms.common.util.Utils.3.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent != null) {
                        subscriber.onNext(AnonymousClass3.this.val$function.mo8172apply(intent));
                    }
                }
            };
            final Context context = this.val$context;
            subscriber.add(Subscriptions.create(new Action0() { // from class: com.amazon.deecomms.common.util.-$$Lambda$Utils$3$qnBu2VRw45kbs-qm8mz-EjK7DX4
                @Override // rx.functions.Action0
                public final void call() {
                    LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
                }
            }));
            LocalBroadcastManager.getInstance(this.val$context).registerReceiver(broadcastReceiver, new IntentFilter(this.val$intent.getAction()));
        }
    }

    private Utils() {
    }

    public static boolean areAccessoriesConnected() {
        boolean isAnyAccessoryConnected = CommsDaggerWrapper.getComponent().getCommsAccessorySessionListener().isAnyAccessoryConnected();
        GeneratedOutlineSupport.outline5("Are accessories connected: ", isAnyAccessoryConnected, LOG);
        return isAnyAccessoryConnected;
    }

    public static void cancelAlarm(Context context, String str) {
        if (str == null || context == null) {
            return;
        }
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (!isAlarmScheduled(context, str)) {
            LOG.w(" Alarm not configured. Skipping clearAlarm!");
            return;
        }
        LOG.i(String.format("Clearing alarm with action %s", str));
        PendingIntent alarmPendingIntent = getAlarmPendingIntent(context, str, 536870912);
        if (alarmPendingIntent == null) {
            return;
        }
        alarmManager.cancel(alarmPendingIntent);
        alarmPendingIntent.cancel();
    }

    public static void clearCommsInfo(boolean z) {
        OobeService oobeService = CommsDaggerWrapper.getComponent().getOobeService();
        Context context = CommsDaggerWrapper.getComponent().getContext();
        if (getBooleanPreferenceFromSharedPrefs(context, TelecomConstants.SHARED_PREF_KEY_TELECOM_REGISTERED, false)) {
            TelecomUtils.unregisterPhoneAccount("clearCommsInfo", context);
        }
        if (ThreadUtils.isRunningOnMainThread()) {
            LOG.i("Running on main thread not allowed");
            return;
        }
        ContactsProviderUtils.deleteAllContacts(context);
        new MessagingProviderWrapper(context, null, null).deleteAllConversations();
        CommsDaggerWrapper.getComponent().getAudioContentManager().clearMediaCache();
        CommsDaggerWrapper.getComponent().getMediaContentManager().clearMediaCache();
        CommsIdentityManager commsIdentityManager = CommsDaggerWrapper.getComponent().getCommsIdentityManager();
        boolean equals = CommsProvisionStatus.DEPROVISIONED.equals(commsIdentityManager.getProvisionStatus(true, "Utils.clearCommsInfo", false));
        SharedPreferences.Editor edit = context.getSharedPreferences("SHARED_PREFS", 0).edit();
        if (!z && equals) {
            edit.remove(Constants.COMMS_ID_PREF).apply();
            commsIdentityManager.deprovisionCurrentUser("clearCommsInfo", false, null, true);
        } else {
            edit.clear().apply();
            SecuredSharedPreference.clearAllPrefs(context);
            new CommsSharedPreferences(context).clearAll();
            oobeService.cleanUpOobe();
            commsIdentityManager.signoutCurrentUser();
        }
        CommsDaggerWrapper.getComponent().getDevicesSource().clearCache();
        ContactPresenceService.clearCachedPresence();
        CommsDaggerWrapper.getComponent().getCommsInternal().shutDown();
        LOG.i("Cleared all comms information");
    }

    private static boolean computeIsFireOS() {
        try {
            Class.forName("amazon.os.Build$VERSION");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private static boolean computeIsSSO(@NonNull Context context) {
        try {
            return !context.getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 128).metaData.getBoolean("MAPIsolateApplication", false);
        } catch (Exception e) {
            LOG.e("Failed to load meta-data", e);
            return false;
        }
    }

    public static void configureAccessibilityFocus(Activity activity, @NonNull final View view, @NonNull final String str) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) activity.getSystemService("accessibility");
        if (!accessibilityManager.isEnabled() || !accessibilityManager.isTouchExplorationEnabled()) {
            return;
        }
        view.post(new Runnable() { // from class: com.amazon.deecomms.common.util.Utils.5
            @Override // java.lang.Runnable
            public void run() {
                view.announceForAccessibility(str);
                view.sendAccessibilityEvent(8);
            }
        });
    }

    @Nullable
    public static MediaRelayInfoModel constructMediaRelayInfoFromJSON(String str) {
        if (str == null) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return (MediaRelayInfoModel) objectMapper.readValue(str, MediaRelayInfoModel.class);
        } catch (Exception e) {
            CommsLogger commsLogger = LOG;
            commsLogger.e("Exception while retrieving endpoint configuration for TURN Server " + e);
            return null;
        }
    }

    public static File copyContentStreamToCache(@NonNull Uri uri) {
        MediaContentManager mediaContentManager = CommsDaggerWrapper.getComponent().getMediaContentManager();
        String path = uri.getPath();
        File file = new File(path);
        if (mediaContentManager.existsInCache(path)) {
            LOG.d("[Sharing] EXISTS IN CACHE");
            File file2 = mediaContentManager.getFromCache(path).getFile();
            if (!lastModifiedDiffers(file2.lastModified(), file.lastModified()).booleanValue()) {
                return file2;
            }
            LOG.d("[Sharing] Last modified differs");
            return putContentStreamInCache(uri, path, file, true);
        }
        LOG.d("[Sharing] NOT IN CACHE");
        return putContentStreamInCache(uri, path, file, false);
    }

    public static boolean deregisterAccount() {
        MAPAccountManager accountManager = CommsDaggerWrapper.getComponent().getApplicationManager().getAccountManager();
        String account = accountManager.getAccount();
        boolean z = false;
        if (!accountManager.isAccountRegistered(account)) {
            return TextUtils.isEmpty(account);
        }
        try {
            z = accountManager.deregisterAccount(account, new Callback() { // from class: com.amazon.deecomms.common.util.Utils.2
                @Override // com.amazon.identity.auth.device.api.Callback
                public void onError(Bundle bundle) {
                    Utils.LOG.i("Cannot unregister device");
                }

                @Override // com.amazon.identity.auth.device.api.Callback
                public void onSuccess(Bundle bundle) {
                    Utils.LOG.i("De-registered Successfully");
                }
            }).get().getBoolean("booleanResult");
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Deregistration response :: ");
            sb.append(z);
            commsLogger.i(sb.toString());
            return z;
        } catch (MAPCallbackErrorException e) {
            CommsLogger commsLogger2 = LOG;
            commsLogger2.i("MAPCallbackError Exception" + e);
            return z;
        } catch (InterruptedException e2) {
            CommsLogger commsLogger3 = LOG;
            commsLogger3.e(PhotosMetricsConstants.INTERRUPTED_EXCEPTION + e2);
            return z;
        } catch (ExecutionException e3) {
            CommsLogger commsLogger4 = LOG;
            commsLogger4.e("ExecutionException" + e3);
            return z;
        }
    }

    @NonNull
    public static List<DeviceModel> fetchMyDevices(@NonNull DevicesSource devicesSource, boolean z) {
        if (ThreadUtils.isRunningOnMainThread()) {
            LOG.i("Running on main thread not allowed");
            return emptyList;
        }
        try {
            return devicesSource.getServerData().getDeviceModels();
        } catch (ServiceException | DeviceTargetingException | InterruptedException | NullPointerException e) {
            if (z) {
                SetupCallHelper.MetadataBuilder withSource = SetupCallHelper.MetadataBuilder.newBuilder().withCallOrigin(Call.Side.Local).withRequestId(devicesSource.getRequestId()).withSource(SetupCallHelper.Source.SipCallPreparation);
                SetupCallHelper.ResultType resultType = SetupCallHelper.ResultType.EXPECTED;
                Integer valueOf = Integer.valueOf(SipStatusCode.TEMPORARILY_UNAVAILABLE.getCode());
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("getDevices failed with error: ");
                outline1.append(e.getMessage());
                SetupCallHelper.recordInitiationMetrics(null, null, resultType, valueOf, withSource.withReason(outline1.toString()));
            }
            LOG.e("Unable to force refresh the devices for Drop In", e);
            return emptyList;
        }
    }

    public static void filterDevices(@NonNull List<DeviceModel> list) {
        ArrayList arrayList = new ArrayList();
        for (DeviceModel deviceModel : list) {
            DropInAvailability deviceDropInAvailability = deviceModel.getDeviceStatus().getDeviceDropInAvailability();
            DeviceCommsAvailability deviceCommsAvailability = deviceModel.getDeviceStatus().getDeviceCommsAvailability();
            if (deviceDropInAvailability == DropInAvailability.OFF || deviceCommsAvailability == DeviceCommsAvailability.OFF) {
                arrayList.add(deviceModel);
            }
        }
        if (arrayList.size() > 0) {
            list.removeAll(arrayList);
        }
    }

    public static String formatPhoneNumber(String str) {
        try {
            Phonenumber.PhoneNumber parse = PhoneNumberUtil.getInstance().parse(str, "");
            if (parse.getCountryCode() == 1) {
                return PhoneNumberUtil.getInstance().format(parse, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
            }
            return PhoneNumberUtil.getInstance().format(parse, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
        } catch (Exception e) {
            LOG.e("Exception while formatting phone number", e);
            return str;
        }
    }

    public static int generateInt() {
        return new SecureRandom().nextInt();
    }

    private static PendingIntent getAlarmPendingIntent(Context context, String str, int i) {
        return PendingIntent.getBroadcast(context, 0, new Intent(str), i);
    }

    public static int getAppImportance(@NonNull ActivityManager activityManager) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        int myPid = Process.myPid();
        if (runningAppProcesses != null && !runningAppProcesses.isEmpty()) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid) {
                    LOG.v("fetchImportance: process index is %d", Integer.valueOf(runningAppProcesses.indexOf(runningAppProcessInfo)));
                    return runningAppProcessInfo.importance;
                }
            }
        }
        LOG.w("Could not obtain app importance");
        return 500;
    }

    public static String getAppVersion(@NonNull Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            CommsLogger commsLogger = LOG;
            commsLogger.e(" Exception while retrieving the app version " + e);
            return "";
        }
    }

    public static boolean getBooleanPreferenceFromSharedPrefs(Context context, @NonNull String str, boolean z) {
        if (context == null) {
            LOG.e("Context is null. Cannot fetch Boolean preferences");
            return false;
        }
        return context.getSharedPreferences("SHARED_PREFS", 0).getBoolean(str, z);
    }

    public static int getColorFromResource(int i) {
        return CommsDaggerWrapper.getComponent().getContext().getResources().getColor(i);
    }

    public static String getContactsURL() {
        String commsId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("getContactsURL", false);
        if (commsId == null) {
            LOG.e("Unable to delete contacts, comms id is null");
            return null;
        }
        return MessageFormat.format(AppUrl.CONTACTS_URL, commsId);
    }

    public static String getContactsURLForOwner(String str) {
        if (TextUtils.isEmpty(str)) {
            LOG.e("Unable to delete contacts, comms id is null");
            return null;
        }
        return MessageFormat.format(AppUrl.CONTACTS_URL, str);
    }

    public static String getDeviceName(Context context) {
        String account = CommsDaggerWrapper.getComponent().getApplicationManager().getAccountManager().getAccount();
        if (account == null) {
            LOG.e("The account is not registered with MAP, direct Id is null");
            return "";
        }
        try {
            return CustomerAttributeStore.getValueOrAttributeDefault(CustomerAttributeStore.getInstance(context).getAttribute(account, "com.amazon.dcp.sso.property.devicename", null).get());
        } catch (MAPCallbackErrorException | InterruptedException | ExecutionException e) {
            CommsLogger commsLogger = LOG;
            commsLogger.e(" Error while retrieving the account name: " + e);
            return "";
        }
    }

    @NonNull
    public static List<DeviceModel> getDevicesForChildAccount(@NonNull List<DeviceModel> list, @NonNull String str) {
        ArrayList arrayList = new ArrayList();
        for (DeviceModel deviceModel : list) {
            if (deviceModel.getPersonalizedAccounts() != null) {
                for (PersonalizedAccount personalizedAccount : deviceModel.getPersonalizedAccounts()) {
                    if (personalizedAccount.getCommsId() != null && personalizedAccount.getCommsId().equals(str) && personalizedAccount.isChildAccount()) {
                        arrayList.add(deviceModel);
                    }
                }
            }
        }
        return arrayList;
    }

    public static int getDimensionFromResource(int i) {
        return (int) CommsDaggerWrapper.getComponent().getContext().getResources().getDimension(i);
    }

    public static String getFileNameFromUri(Uri uri, Context context) {
        Throwable th;
        Cursor cursor;
        try {
            cursor = context.getContentResolver().query(uri, null, null, null, null);
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
        try {
            int columnIndex = cursor.getColumnIndex("_display_name");
            cursor.moveToFirst();
            String string = cursor.getString(columnIndex);
            cursor.close();
            return string;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static long getLongPreferenceFromSharedPrefs(@NonNull Context context, @NonNull String str, Long l) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SHARED_PREFS", 0);
        if (l == null) {
            return sharedPreferences.getLong(str, -1L);
        }
        return sharedPreferences.getLong(str, l.longValue());
    }

    public static Observable<Boolean> getLowStorageObservable(Context context) {
        return observableFromBroadcast(context, new Intent("android.intent.action.DEVICE_STORAGE_OK"), $$Lambda$Utils$G1z5ZwJfKVflMImkdnhGchAQBPc.INSTANCE).mergeWith(observableFromBroadcast(context, new Intent("android.intent.action.DEVICE_STORAGE_LOW"), $$Lambda$Utils$wOTu9rXJyvJbE_1hvyCG0EkvY.INSTANCE));
    }

    public static int getMessageHorizontalRowMargin(Activity activity) {
        if (sMessageRowHorizontalMargin == -1) {
            sMessageRowHorizontalMargin = getWindowWidth(activity) / 4;
        }
        return sMessageRowHorizontalMargin;
    }

    public static String getMimeType(Uri uri) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
        if (fileExtensionFromUrl != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl.toLowerCase());
        }
        return null;
    }

    @NonNull
    public static String getNetworkType() {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        Context context = CommsDaggerWrapper.getComponent().getContext();
        return (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) ? NETWORK_NOT_SET : !activeNetworkInfo.isConnected() ? NETWORK_OFFLINE : activeNetworkInfo.getTypeName();
    }

    @NonNull
    public static String getOSType() {
        return isFireOS() ? Constants.OS_FIREOS : "Android";
    }

    public static String getRealPathFromCopy(Uri uri, Context context) throws IOException {
        InputStream openInputStream = context.getContentResolver().openInputStream(uri);
        File file = new File(context.getFilesDir(), getFileNameFromUri(uri, context));
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bArr = new byte[8192];
        while (true) {
            int read = openInputStream.read(bArr);
            if (read != -1) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                openInputStream.close();
                fileOutputStream.close();
                return file.getAbsolutePath();
            }
        }
    }

    public static String getSipURIforRegisteredUser(Context context) {
        return getStringPreferenceFromSharedPrefs(context, Constants.SIP_USER_AOR, null);
    }

    public static String getStringFromResource(int i) {
        Context context = CommsDaggerWrapper.getComponent().getContext();
        if (context != null) {
            return context.getResources().getString(i);
        }
        return null;
    }

    public static String getStringPreferenceFromSharedPrefs(Context context, String str, String str2) {
        if (context == null) {
            LOG.e("Context is null. Cannot fetch String preferences");
            return null;
        }
        return context.getSharedPreferences("SHARED_PREFS", 0).getString(str, str2);
    }

    @NonNull
    public static List<String> getSupportedCodecsforGivenMimeType(String str) {
        MediaCodecInfo[] codecInfos;
        ArrayList arrayList = new ArrayList();
        for (MediaCodecInfo mediaCodecInfo : new MediaCodecList(1).getCodecInfos()) {
            String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
            int length = supportedTypes.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (supportedTypes[i].equalsIgnoreCase(str)) {
                    arrayList.add(mediaCodecInfo.getName());
                    break;
                } else {
                    i++;
                }
            }
        }
        return arrayList;
    }

    @NonNull
    public static String getUserAgent() {
        Joiner useForNull = Joiner.on("/").useForNull("");
        String appVersion = getAppVersion(CommsDaggerWrapper.getComponent().getContext());
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Android_");
        outline1.append(Build.VERSION.RELEASE);
        return useForNull.join("AlexaApp", appVersion, "", Build.MODEL, outline1.toString());
    }

    public static Map<String, String> getUserNameAndDomainNameFromAOR(String str) {
        HashMap hashMap = new HashMap();
        String[] split = str.split("@");
        if (split.length != 2) {
            LOG.e("Cannot find userName and domainName from AOR");
            return hashMap;
        }
        String[] split2 = split[0].split(":");
        if (split2.length != 2) {
            LOG.e("Cannot find userName from AOR");
            return hashMap;
        }
        String str2 = split2[1];
        hashMap.put(Constants.DOMAIN, split[1]);
        hashMap.put(Constants.USER_NAME, str2);
        return hashMap;
    }

    public static String getUsernameForRegisteredUser(Context context) {
        return context.getSharedPreferences("SHARED_PREFS", 0).getString(Constants.MY_COMMS_USERNAME, null);
    }

    public static String getUsernameFromUri(String str) {
        Preconditions.checkArgument(str != null, " The URI should not be null");
        String[] split = str.split("@")[0].split(":");
        if (split.length > 1) {
            return split[1].trim();
        }
        return null;
    }

    public static String getValueFromDataStore(@NonNull Context context, @NonNull String str) throws DeviceDataStoreException {
        return DeviceDataStore.getInstance(context).getValue(str);
    }

    public static int getWindowWidth(Activity activity) {
        if (sWindowWidth == -1) {
            sWindowWidth = activity.getWindowManager().getDefaultDisplay().getWidth();
        }
        return sWindowWidth;
    }

    public static void hideKeyboard(@NonNull Activity activity) {
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public static boolean isAlarmScheduled(Context context, String str) {
        return getAlarmPendingIntent(context, str, 536870912) != null;
    }

    public static boolean isAndroid10AndAbove() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public static boolean isAndroid10AndAboveFOS() {
        return isFireOS() && isAndroid10AndAbove();
    }

    public static boolean isAndroid10ChangesEnabled() {
        return isAndroid10AndAbove() && CommsDaggerWrapper.getComponent().getCapabilitiesManager().isAndroid10CallingEnabled();
    }

    public static boolean isCameraPresent(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    @TargetApi(23)
    public static boolean isDeviceInIdleMode() {
        CommsDaggerWrapper.getComponent().getContext();
        if (!isMarshMallowAndAbove()) {
            LOG.e("Cannot access this method below Marshmallow");
            return false;
        }
        return CommsDaggerWrapper.getComponent().getPowerManager().isDeviceIdleMode();
    }

    @TargetApi(21)
    public static boolean isDeviceInPowerSaveMode() {
        CommsDaggerWrapper.getComponent().getContext();
        if (isLollipopAndAbove()) {
            return CommsDaggerWrapper.getComponent().getPowerManager().isPowerSaveMode();
        }
        throw new IllegalAccessError("Cannot access this method below Lollipop");
    }

    public static boolean isFireOS() {
        return IS_FIRE_OS.booleanValue();
    }

    public static boolean isFireOS5() {
        if (isFireOS()) {
            int i = Build.VERSION.SDK_INT;
            return false;
        }
        return false;
    }

    public static boolean isHomeGroupId(String str) {
        return TextUtils.equals(str, CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("Utils.isHomeGroupId", false));
    }

    public static boolean isInDriveMode() {
        ModeService modeService = (ModeService) GeneratedOutlineSupport1.outline21(ModeService.class);
        if (modeService != null) {
            return modeService.getMode().equals(ModeName.DRIVE_MODE);
        }
        return false;
    }

    public static boolean isKitKatAndAbove() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean isLollipopAndAbove() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean isMapAccountRegistered(@NonNull String str) {
        MAPAccountManager accountManager = CommsDaggerWrapper.getComponent().getApplicationManager().getAccountManager();
        if (str.equals(accountManager.getAccount())) {
            return accountManager.isAccountRegistered(str);
        }
        return false;
    }

    public static boolean isMarshMallowAndAbove() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean isNetworkConnected() {
        return CommsDaggerWrapper.getComponent().getCommsConnectivityMonitor().isConnected();
    }

    public static boolean isNougatAndAbove() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isOfflineDialogShown(@Nullable Activity activity, boolean z, String str, @NonNull AlertSource alertSource) {
        if (!isNetworkConnected()) {
            DialogUtils.showOfflineDialog(activity, str, alertSource);
            return true;
        } else if (!z || DeviceCallingServiceEventListener.canMakeACall()) {
            return false;
        } else {
            MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_ERROR, str, alertSource);
            showDialog(activity, R.string.error_title, R.string.call_error_dialog_message);
            return true;
        }
    }

    public static boolean isOreoAndAbove() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean isPermissionGranted(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public static boolean isPie() {
        return Build.VERSION.SDK_INT == 28;
    }

    public static boolean isPieAndAbove() {
        return Build.VERSION.SDK_INT >= 28;
    }

    public static boolean isRegisteredCommsId(String str) {
        return TextUtils.equals(str, CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("isRegisteredCommsId", false));
    }

    public static boolean isSSO(@NonNull Context context) {
        if (sIsSSO == null) {
            sIsSSO = Boolean.valueOf(computeIsSSO(context));
        }
        return sIsSSO.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$getLowStorageObservable$1(Intent intent) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$getLowStorageObservable$2(Intent intent) {
        return true;
    }

    public static Boolean lastModifiedDiffers(long j, long j2) {
        return Boolean.valueOf(j != j2);
    }

    public static void navigateToAppSettingsPage(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("package:");
        outline1.append(context.getPackageName());
        intent.setData(Uri.parse(outline1.toString()));
        intent.addFlags(268435456);
        intent.addFlags(1073741824);
        intent.addFlags(8388608);
        context.startActivity(intent);
    }

    public static <T> Observable<T> observableFromBroadcast(final Context context, final Intent intent, final Function<Intent, T> function) {
        return Observable.create(new Observable.OnSubscribe<T>() { // from class: com.amazon.deecomms.common.util.Utils.4
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super T> subscriber) {
                final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.deecomms.common.util.Utils.4.1
                    @Override // android.content.BroadcastReceiver
                    public void onReceive(Context context2, Intent intent2) {
                        if (intent2 != null) {
                            subscriber.onNext(Function.this.mo8172apply(intent2));
                        }
                    }
                };
                final Context context2 = context;
                subscriber.add(Subscriptions.create(new Action0() { // from class: com.amazon.deecomms.common.util.-$$Lambda$Utils$4$oqJHv-TPSLwo0kmtnvRTT2DNatQ
                    @Override // rx.functions.Action0
                    public final void call() {
                        context2.unregisterReceiver(broadcastReceiver);
                    }
                }));
                Intent registerReceiver = context.registerReceiver(broadcastReceiver, new IntentFilter(intent.getAction()));
                if (registerReceiver != null) {
                    subscriber.onNext(Function.this.mo8172apply(registerReceiver));
                }
            }
        });
    }

    public static <T> Observable<T> observableFromLocalBroadcast(Context context, Intent intent, Function<Intent, T> function) {
        return Observable.create(new AnonymousClass3(function, context, intent));
    }

    public static void openUrlInExternalBrowser(@NonNull String str, @NonNull Fragment fragment) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        if (intent.resolveActivity(fragment.getActivity().getPackageManager()) == null) {
            LOG.e("Attempting to open a link in an external browser, but browser doesn't exist.");
        } else {
            fragment.startActivity(intent);
        }
    }

    public static boolean platformSupportsRuntimePermissions() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean platformSupportsStatusBar() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    private static File putContentStreamInCache(@NonNull Uri uri, String str, File file, boolean z) {
        MediaContentManager mediaContentManager = CommsDaggerWrapper.getComponent().getMediaContentManager();
        MediaFileContent mediaFileContent = new MediaFileContent(file);
        mediaFileContent.setContentType(getMimeType(uri));
        mediaContentManager.putInCache(mediaFileContent, str, z);
        return file;
    }

    public static void recordMapMetric(@NonNull String str, @NonNull String str2, @NonNull Exception exc) {
        recordMapMetric(str, str2, exc.getClass().getSimpleName() + RealTimeTextConstants.COLON_SPACE + exc.getMessage());
    }

    public static void removePreferenceFromSharedPrefs(@NonNull Context context, @NonNull String str) {
        context.getSharedPreferences("SHARED_PREFS", 0).edit().remove(str).apply();
    }

    public static void scheduleRepeatingAlarm(Context context, long j, long j2, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return;
        }
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        long currentTimeMillis = System.currentTimeMillis();
        if (j <= currentTimeMillis) {
            j = currentTimeMillis + j2;
        }
        alarmManager.setRepeating(0, j, j2, getAlarmPendingIntent(context, str, 134217728));
    }

    public static void sendExplicitBroadcastIntent(@NonNull Context context, @NonNull Intent intent, @Nullable String str) {
        for (ResolveInfo resolveInfo : context.getPackageManager().queryBroadcastReceivers(intent, 0)) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Resolving to the broadcast receiver ..");
            outline1.append(resolveInfo.activityInfo.name);
            commsLogger.i(outline1.toString());
            Intent intent2 = new Intent(intent);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            if (TextUtils.isEmpty(str)) {
                context.sendBroadcast(intent2);
            } else {
                context.sendBroadcast(intent2, str);
            }
        }
    }

    public static String serialize(@NonNull Object obj) throws JsonProcessingException {
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(obj);
    }

    public static void setAudioMode(@NonNull AudioManager audioManager) {
        if (shouldUseNormalAudioMode()) {
            audioManager.setMode(0);
            LOG.i("Audio mode is set to normal");
            return;
        }
        audioManager.setMode(3);
        LOG.i("Audio mode is set to communication");
    }

    public static MediaPlayer setLoopingVideo(@NonNull Context context, @NonNull TextureView textureView, int i) {
        final MediaPlayer create = MediaPlayer.create(context, i);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.amazon.deecomms.common.util.Utils.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
                try {
                    create.setSurface(new Surface(surfaceTexture));
                    create.setLooping(true);
                    create.setVideoScalingMode(2);
                    create.prepare();
                } catch (Exception e) {
                    Utils.LOG.e("Error in OOBE media player", e);
                }
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }
        });
        textureView.setVisibility(0);
        return create;
    }

    public static boolean sharedPreferenceDefined(@NonNull Context context, @NonNull String str) {
        return context.getSharedPreferences("SHARED_PREFS", 0).contains(str);
    }

    public static boolean shouldAllowAlexaCall(@NonNull Context context) {
        int mode = ((AudioManager) context.getSystemService("audio")).getMode();
        int callState = ((TelephonyManager) context.getSystemService("phone")).getCallState();
        CommsLogger commsLogger = LOG;
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Audio Manager Mode ", mode, " Telephony Call State ", callState, " Disable incoming call state :");
        outline110.append(CallUtils.disableIncomingCalls.get());
        commsLogger.d(outline110.toString());
        return (callState == 1 || callState == 2 || mode == 1 || mode == 2 || CommsDaggerWrapper.getComponent().getCallManager().isAnyActiveCallPresent() || (isFireOS() && CallUtils.disableIncomingCalls.get())) ? false : true;
    }

    public static void shouldRedirectToSettingsDialog(@NonNull Activity activity, @NonNull BiConsumer<Boolean, Map<String, String>> biConsumer) {
        showCommsDisabledDialog(activity, biConsumer, new AlertDialog.Builder(activity));
    }

    public static boolean shouldUseNormalAudioMode() {
        return isFireOS() && isLollipopAndAbove() && !isNougatAndAbove();
    }

    @VisibleForTesting
    static void showCommsDisabledDialog(@NonNull final Activity activity, @NonNull final BiConsumer<Boolean, Map<String, String>> biConsumer, @NonNull AlertDialog.Builder builder) {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.common.util.Utils.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Utils.LOG.i("User will be redirected to settigs where he can enable comms.");
                try {
                    DeviceDataStore deviceDataStore = DeviceDataStore.getInstance(activity);
                    String value = deviceDataStore.getValue(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
                    String value2 = deviceDataStore.getValue("DeviceType");
                    HashMap hashMap = new HashMap();
                    hashMap.put(Constants.BUNDLE_SERIAL_NUMBER, value);
                    hashMap.put("deviceType", value2);
                    biConsumer.accept(true, hashMap);
                } catch (DeviceDataStoreException e) {
                    Utils.LOG.e("retrieving serial number or device type from settings failed", e);
                    biConsumer.accept(false, null);
                }
            }
        };
        DialogInterface.OnClickListener onClickListener2 = new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.common.util.Utils.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Utils.LOG.i("Communications is still disabled, user cancelled popup to redirect to settings.");
                BiConsumer.this.accept(false, null);
            }
        };
        builder.setMessage(activity.getString(R.string.comms_disabled_dialog));
        builder.setPositiveButton(activity.getString(R.string.settings), onClickListener);
        builder.setNegativeButton(activity.getString(R.string.cancel), onClickListener2);
        builder.setCancelable(false);
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(false);
        create.show();
    }

    public static void showDialog(@Nullable Activity activity, int i, int i2) {
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            new AlertDialog.Builder(activity).setTitle(i).setMessage(i2).setPositiveButton(R.string.dialog_ok_button, $$Lambda$Utils$eOnmtImLS2dEbXJzMh2EpQmHLpg.INSTANCE).create().show();
        } else {
            LOG.e("Cannot show dialog finished activity");
        }
    }

    public static void stopAndCleanUpMediaPlayer(@Nullable MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            if (mediaPlayer.isLooping() || mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
    }

    public static byte[] toBytes(char[] cArr) {
        CharBuffer wrap = CharBuffer.wrap(cArr);
        ByteBuffer encode = Charset.forName("UTF-8").encode(wrap);
        byte[] copyOfRange = Arrays.copyOfRange(encode.array(), encode.position(), encode.limit());
        Arrays.fill(wrap.array(), (char) 0);
        Arrays.fill(encode.array(), (byte) 0);
        return copyOfRange;
    }

    public static boolean vibrate(Context context, long j) {
        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(j);
            CommsLogger commsLogger = LOG;
            commsLogger.d("Vibration requested; successfully vibrated for duration " + j);
            return true;
        }
        LOG.d("Vibration requested; but no support for it on this device");
        return false;
    }

    public static void writeBooleanPreferenceToSharedPrefs(Context context, @NonNull String str, boolean z) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("SHARED_PREFS", 0).edit().putBoolean(str, z).apply();
    }

    public static void writeLongPreferenceToSharedPrefs(Context context, @NonNull String str, long j) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("SHARED_PREFS", 0).edit().putLong(str, j).apply();
    }

    public static void writeStringPreferenceToSharedPrefs(Context context, @NonNull String str, String str2) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("SHARED_PREFS", 0).edit().putString(str, str2).apply();
    }

    public static void recordMapMetric(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        recordMapMetric(str, str2, str3, null);
    }

    public static void recordMapMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4) {
        CounterMetric generateOperational = CounterMetric.generateOperational(str);
        Map<String, Object> metadata = generateOperational.getMetadata();
        metadata.put("source", str2 + "_" + str3);
        if (!TextUtils.isEmpty(str4)) {
            metadata.put("statusCode", str4);
        }
        MetricsHelper.recordSingleOccurrence(generateOperational);
    }
}
