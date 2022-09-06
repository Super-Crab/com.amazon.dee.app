package com.amazon.alexa.handsfree.protocols.sierracontentprovider;

import android.net.Uri;
/* loaded from: classes8.dex */
public final class SierraContentProviderContract {
    public static final String KEY_MD5 = "get_md5";
    public static final String KEY_SERVICE_ENABLED = "service_enabled";
    public static final String MD5_VALUE = "md5";
    private static final String PATH_ALEXA = "alexa";
    private static final String WAKE_UP_SETTING_AUTHORITY = "com.samsung.android.wakeup.settings";
    public static final Uri SIERRA_WW_SETTINGS_URI = new Uri.Builder().scheme("content").authority(WAKE_UP_SETTING_AUTHORITY).path("alexa").build();
    private static final String MD5_PATH = "alexa/model/file.bin";
    public static final Uri SIERRA_MD_CHECKSUM_URI = new Uri.Builder().scheme("content").authority(WAKE_UP_SETTING_AUTHORITY).path(MD5_PATH).build();

    private SierraContentProviderContract() {
    }
}
