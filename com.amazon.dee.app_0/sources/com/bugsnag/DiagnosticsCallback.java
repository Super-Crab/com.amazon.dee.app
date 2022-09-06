package com.bugsnag;

import com.amazon.alexa.mobilytics.event.LogLevel;
import com.bugsnag.android.Callback;
import com.bugsnag.android.MetaData;
import com.bugsnag.android.Report;
import com.bugsnag.android.Severity;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.HashMap;
import java.util.Map;
/* compiled from: BugsnagReactNative.java */
/* loaded from: classes11.dex */
class DiagnosticsCallback implements Callback {
    static final String NOTIFIER_NAME = "Bugsnag for React Native";
    static final String NOTIFIER_URL = "https://github.com/bugsnag/bugsnag-react-native";
    private final String bugsnagAndroidVersion;
    private final String context;
    private final String groupingHash;
    private final String libraryVersion;
    private final Map<String, Object> metadata;
    private final Severity severity;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DiagnosticsCallback(String str, String str2, ReadableMap readableMap) {
        this.libraryVersion = str;
        this.bugsnagAndroidVersion = str2;
        this.severity = parseSeverity(readableMap.getString("severity"));
        this.metadata = readObjectMap(readableMap.mo6945getMap("metadata"));
        if (readableMap.hasKey("context")) {
            this.context = readableMap.getString("context");
        } else {
            this.context = null;
        }
        if (readableMap.hasKey("groupingHash")) {
            this.groupingHash = readableMap.getString("groupingHash");
        } else {
            this.groupingHash = null;
        }
    }

    @Override // com.bugsnag.android.Callback
    public void beforeNotify(Report report) {
        report.getNotifier().setName(NOTIFIER_NAME);
        report.getNotifier().setURL(NOTIFIER_URL);
        report.getNotifier().setVersion(String.format("%s (Android %s)", this.libraryVersion, this.bugsnagAndroidVersion));
        String str = this.groupingHash;
        if (str != null && str.length() > 0) {
            report.getError().setGroupingHash(this.groupingHash);
        }
        String str2 = this.context;
        if (str2 != null && str2.length() > 0) {
            report.getError().setContext(this.context);
        }
        if (this.metadata != null) {
            MetaData metaData = report.getError().getMetaData();
            for (String str3 : this.metadata.keySet()) {
                Object obj = this.metadata.get(str3);
                if (obj instanceof Map) {
                    Map map = (Map) obj;
                    for (String str4 : map.keySet()) {
                        metaData.addToTab(str3, str4, map.get(str4));
                    }
                }
            }
        }
    }

    Severity parseSeverity(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == 3237038) {
            if (str.equals(LogLevel.INFO)) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != 96784904) {
            if (hashCode == 1124446108 && str.equals("warning")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("error")) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                return Severity.WARNING;
            }
            return Severity.INFO;
        }
        return Severity.ERROR;
    }

    Map<String, Object> readObjectMap(ReadableMap readableMap) {
        HashMap hashMap = new HashMap();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            ReadableMap mo6945getMap = readableMap.mo6945getMap(nextKey);
            String string = mo6945getMap.getString("type");
            char c = 65535;
            switch (string.hashCode()) {
                case -1034364087:
                    if (string.equals("number")) {
                        c = 1;
                        break;
                    }
                    break;
                case -891985903:
                    if (string.equals("string")) {
                        c = 2;
                        break;
                    }
                    break;
                case 107868:
                    if (string.equals(com.amazon.alexa.auth.BuildConfig.FLAVOR_authtype)) {
                        c = 3;
                        break;
                    }
                    break;
                case 64711720:
                    if (string.equals("boolean")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                hashMap.put(nextKey, Boolean.valueOf(mo6945getMap.getBoolean("value")));
            } else if (c == 1) {
                hashMap.put(nextKey, Double.valueOf(mo6945getMap.getDouble("value")));
            } else if (c == 2) {
                hashMap.put(nextKey, mo6945getMap.getString("value"));
            } else if (c == 3) {
                hashMap.put(nextKey, readObjectMap(mo6945getMap.mo6945getMap("value")));
            }
        }
        return hashMap;
    }
}
