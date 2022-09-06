package com.amazon.alexa.accessory.notificationpublisher.notificationlistener;

import android.app.Notification;
import android.app.Person;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.parser.BaseCustomAppParser;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.JSONLoader;
import com.amazon.alexa.accessory.notificationpublisher.utils.LRUHashMap;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class StatusBarNotificationParser {
    private static final String APP_BLACKLIST_FILENAME = "app_blacklist.json";
    private static final long DUPLICATE_EXPIRATION_DURATION = TimeUnit.SECONDS.toMillis(10);
    @VisibleForTesting
    static final String MESSAGE_KEY_PERSON = "person";
    @VisibleForTesting
    static final String MESSAGE_KEY_SENDER = "sender";
    @VisibleForTesting
    static final String MESSAGE_KEY_SENDER_PERSON = "sender_person";
    @VisibleForTesting
    static final String MESSAGE_KEY_TIME = "time";
    @VisibleForTesting
    static final String PERSON_KEY_NAME = "name";
    private static final String TAG = "StatusBarNotificationParser";
    private Context context;
    private Set<String> restrcitedAppslist;
    private final Map<String, String> notificationMap = new LRUHashMap();
    private long lastZionAccessoryConnectedTimeStamp = -1;
    private ImmutableMap<String, String> selfDirectReplyExceptionMap = new ImmutableMap.Builder().mo7828put("com.samsung.android.messaging", "^10.\\d+.\\d+.\\d+$").mo7826build();
    private Pattern patternForInvisibleChars = Pattern.compile("[\\p{C}\\r\\x{200B}-\\x{200D}\\x{2062}\\x{2063}\\x{FEFF}]");
    private Pattern patternForNewlineTab = Pattern.compile("[\\n\\t]");

    public StatusBarNotificationParser(Context context) {
        this.restrcitedAppslist = new HashSet(Arrays.asList("android", "com.android.providers.downloads", "com.android.systemui", "com.android.vending", "com.android.chrome", "com.brave.browser", "com.samsung.android.lool", "com.wssyncmldm"));
        this.context = context;
        Set<String> fetchAppBlacklist = fetchAppBlacklist();
        if (fetchAppBlacklist != null) {
            this.restrcitedAppslist = fetchAppBlacklist;
        }
    }

    private JSONObject createNotificationJson(@NonNull String str, @NonNull String str2, @NonNull String str3, long j, Long l, String str4, String str5, JSONObject jSONObject) throws Exception {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(Constants.BUNDLE_KEY_NOTIFICATION_ID, str);
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, AccessoryMetricsConstants.DELIMITER);
        outline113.append(String.valueOf(j));
        jSONObject2.put("uuid", outline113.toString());
        jSONObject2.put("packageIdentifier", str2);
        jSONObject2.put(SettingsStorageModule.FILTER_SETTINGS_APP_NAME_KEY, str3);
        jSONObject2.put("postTime", j);
        Object obj = l;
        if (l == null) {
            obj = JSONObject.NULL;
        }
        jSONObject2.put("when", obj);
        Object obj2 = str4;
        if (str4 == null) {
            obj2 = JSONObject.NULL;
        }
        jSONObject2.put("title", obj2);
        Object obj3 = str5;
        if (str5 == null) {
            obj3 = JSONObject.NULL;
        }
        jSONObject2.put("text", obj3);
        Object obj4 = jSONObject;
        if (jSONObject == null) {
            obj4 = JSONObject.NULL;
        }
        jSONObject2.put("metaData", obj4);
        return jSONObject2;
    }

    private Set<String> fetchAppBlacklist() {
        HashSet hashSet = new HashSet();
        try {
            JSONArray loadJSONArrayFromAsset = JSONLoader.loadJSONArrayFromAsset(this.context, APP_BLACKLIST_FILENAME);
            if (loadJSONArrayFromAsset != null && loadJSONArrayFromAsset.length() != 0) {
                for (int i = 0; i < loadJSONArrayFromAsset.length(); i++) {
                    hashSet.add(loadJSONArrayFromAsset.optString(i, ""));
                }
                String str = "fetchAppBlacklist - App blacklist: " + hashSet;
                return hashSet;
            }
            return null;
        } catch (Exception e) {
            Log.w(TAG, "fetchAppBlacklist - Exception: ", e);
            return null;
        }
    }

    @NonNull
    private String getNotificationAsString(@NonNull Notification notification) {
        String valueOf = String.valueOf(notification.when);
        Bundle bundle = notification.extras;
        if (bundle != null) {
            CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TITLE, null);
            CharSequence charSequence2 = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT, null);
            CharSequence charSequence3 = bundle.getCharSequence(NotificationCompat.EXTRA_BIG_TEXT, null);
            CharSequence charSequence4 = bundle.getCharSequence(NotificationCompat.EXTRA_SUB_TEXT, null);
            return valueOf + AccessoryMetricsConstants.DELIMITER + ((Object) charSequence2) + AccessoryMetricsConstants.DELIMITER + ((Object) charSequence) + AccessoryMetricsConstants.DELIMITER + ((Object) charSequence3) + AccessoryMetricsConstants.DELIMITER + ((Object) charSequence4);
        }
        return valueOf;
    }

    @VisibleForTesting
    boolean isDuplicateGroupNotification(@NonNull String str, boolean z, long j, @NonNull Notification notification) {
        if (!z) {
            return false;
        }
        if (j <= this.lastZionAccessoryConnectedTimeStamp + DUPLICATE_EXPIRATION_DURATION) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("isDuplicateGroupNotification - true - expired: ");
            outline107.append(this.notificationMap.toString());
            outline107.toString();
            return true;
        }
        String notificationAsString = getNotificationAsString(notification);
        if (!this.notificationMap.containsKey(str)) {
            this.notificationMap.put(str, notificationAsString);
            String str2 = "isDuplicateGroupNotification - false - first update: " + this.notificationMap.toString();
            return false;
        } else if (this.notificationMap.containsKey(str) && this.notificationMap.get(str).equals(notificationAsString)) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("isDuplicateGroupNotification - true - duplicate: ");
            outline1072.append(this.notificationMap.toString());
            outline1072.toString();
            return true;
        } else {
            this.notificationMap.put(str, notificationAsString);
            String str3 = "isDuplicateGroupNotification - false - new update: " + this.notificationMap.toString();
            return false;
        }
    }

    @VisibleForTesting
    boolean isIgnoredNotification(StatusBarNotification statusBarNotification) {
        try {
            if (this.restrcitedAppslist.contains(statusBarNotification.getPackageName())) {
                String str = "isIgnoredNotification -- blocked app " + statusBarNotification.getKey();
                return true;
            } else if (statusBarNotification.isOngoing()) {
                String str2 = "isIgnoredNotification -- ongoing notification " + statusBarNotification.getKey();
                return true;
            } else if (!statusBarNotification.isGroup() || (statusBarNotification.getNotification().flags & 512) == 0) {
                return false;
            } else {
                String str3 = "isIgnoredNotification -- group summary notification " + statusBarNotification.getKey();
                return true;
            }
        } catch (Exception e) {
            Log.w(TAG, "isIgnoredNotification -- error.", e);
            return true;
        }
    }

    @VisibleForTesting
    boolean isSelfDirectReplyMessage(@NonNull Notification notification) {
        boolean z;
        Parcelable[] parcelableArray;
        Parcelable parcelable;
        try {
            Bundle bundle = notification.extras;
            Notification.Action[] actionArr = notification.actions;
            if (actionArr == null) {
                return false;
            }
            int length = actionArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                } else if (actionArr[i].getRemoteInputs() != null) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (z && (parcelableArray = bundle.getParcelableArray(NotificationCompat.EXTRA_MESSAGES)) != null && parcelableArray.length > 0) {
                Bundle bundle2 = (Bundle) parcelableArray[parcelableArray.length - 1];
                CharSequence charSequence = bundle2.getCharSequence("sender");
                CharSequence charSequence2 = null;
                if (charSequence == null) {
                    if (bundle2.keySet().contains("person")) {
                        parcelable = bundle2.getParcelable("person");
                    } else {
                        parcelable = bundle2.keySet().contains(MESSAGE_KEY_SENDER_PERSON) ? bundle2.getParcelable(MESSAGE_KEY_SENDER_PERSON) : null;
                    }
                    if (parcelable != null) {
                        if (Build.VERSION.SDK_INT >= 28 && (parcelable instanceof Person)) {
                            charSequence = ((Person) parcelable).getName();
                        } else if (parcelable instanceof Bundle) {
                            charSequence = ((Bundle) parcelable).getCharSequence("name");
                        }
                    }
                }
                CharSequence charSequence3 = bundle.getCharSequence(NotificationCompat.EXTRA_SELF_DISPLAY_NAME);
                if ((charSequence3 == null || charSequence3.length() == 0) && Build.VERSION.SDK_INT >= 28) {
                    Person person = (Person) bundle.getParcelable("android.messagingUser");
                    if (person != null) {
                        charSequence2 = person.getName();
                    }
                    charSequence3 = charSequence2;
                }
                long j = bundle2.getLong("time");
                boolean z2 = bundle.getBoolean(NotificationCompat.EXTRA_SHOW_WHEN, false);
                String.format("isSelfDirectReplyMessage - selfName: %s, lastSender: %s, lastMessageTime: %s, notification.when %s", charSequence3, charSequence, Long.valueOf(j), Long.valueOf(notification.when));
                if ((charSequence == null && z2 && j != notification.when) || (charSequence != null && charSequence.equals(charSequence3))) {
                    Log.i(TAG, "isSelfDirectReplyMessage - Direct reply from self.");
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            Log.e(TAG, "isSelfDirectReplyMessage - Failed with error. ", e);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x017b A[Catch: Exception -> 0x01b7, TryCatch #0 {Exception -> 0x01b7, blocks: (B:3:0x0003, B:5:0x0020, B:7:0x0029, B:9:0x0047, B:12:0x0074, B:14:0x007a, B:16:0x0085, B:18:0x0095, B:20:0x00be, B:21:0x00c7, B:23:0x00d1, B:24:0x00d7, B:26:0x00e8, B:28:0x00f0, B:29:0x00ff, B:31:0x0121, B:32:0x0128, B:34:0x012e, B:36:0x0134, B:42:0x0145, B:44:0x014b, B:47:0x0155, B:51:0x0163, B:56:0x0170, B:58:0x017b, B:60:0x0184, B:61:0x01ae, B:15:0x0081), top: B:66:0x0003 }] */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.json.JSONObject parseNotification(java.lang.String r20, android.service.notification.StatusBarNotification r21) {
        /*
            Method dump skipped, instructions count: 450
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.notificationlistener.StatusBarNotificationParser.parseNotification(java.lang.String, android.service.notification.StatusBarNotification):org.json.JSONObject");
    }

    @VisibleForTesting
    String removeInvisibleChar(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        String charSequence2 = charSequence.toString();
        try {
            return this.patternForInvisibleChars.matcher(this.patternForNewlineTab.matcher(charSequence2).replaceAll(" ")).replaceAll("");
        } catch (Exception e) {
            Log.w(TAG, "removeInvisibleChar error.", e);
            return charSequence2;
        }
    }

    @VisibleForTesting
    boolean shouldCheckSelfDirectReply(String str, PackageInfo packageInfo, String str2) {
        return str != null && str.equals(BaseCustomAppParser.NOTIFICATION_STYLE_MESSAGING) && (packageInfo == null || Strings.isNullOrEmpty(packageInfo.versionName) || !this.selfDirectReplyExceptionMap.containsKey(str2) || !packageInfo.versionName.matches(this.selfDirectReplyExceptionMap.mo7740get(str2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateLastZionConnectedTimestamp(long j) {
        this.lastZionAccessoryConnectedTimeStamp = j;
    }
}
