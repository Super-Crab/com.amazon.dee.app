package com.google.android.exoplayer2.audio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.provider.Settings;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
/* loaded from: classes2.dex */
public final class AudioCapabilities {
    private static final int DEFAULT_MAX_CHANNEL_COUNT = 8;
    private static final String EXTERNAL_SURROUND_SOUND_KEY = "external_surround_sound_enabled";
    public static final String USE_EXTERNAL_SURROUND_SOUND_FLAG = "use_external_surround_sound_flag";
    private final int maxChannelCount;
    private final int[] supportedEncodings;
    public static final AudioCapabilities DEFAULT_AUDIO_CAPABILITIES = new AudioCapabilities(new int[]{2}, 8);
    public static final AudioCapabilities EXTERNAL_SURROUND_SOUND_CAPABILITIES = new AudioCapabilities(new int[]{2, 5, 6}, 8);

    public AudioCapabilities(@Nullable int[] iArr, int i) {
        if (iArr != null) {
            this.supportedEncodings = Arrays.copyOf(iArr, iArr.length);
            Arrays.sort(this.supportedEncodings);
        } else {
            this.supportedEncodings = new int[0];
        }
        this.maxChannelCount = i;
    }

    private static boolean deviceMaySetExternalSurroundSoundGlobalSetting() {
        return Util.SDK_INT >= 17 && ("Amazon".equals(Util.MANUFACTURER) || "Xiaomi".equals(Util.MANUFACTURER));
    }

    public static AudioCapabilities getCapabilities(Context context) {
        return getCapabilities(context, context.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG")));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Uri getExternalSurroundSoundGlobalSettingUri() {
        if (deviceMaySetExternalSurroundSoundGlobalSetting()) {
            return Settings.Global.getUriFor(EXTERNAL_SURROUND_SOUND_KEY);
        }
        return null;
    }

    @TargetApi(17)
    public static boolean isSurroundSoundEnabledV17(ContentResolver contentResolver) {
        return Settings.Global.getInt(contentResolver, EXTERNAL_SURROUND_SOUND_KEY, 0) == 1;
    }

    public static boolean useSurroundSoundFlagV17(ContentResolver contentResolver) {
        return Settings.Global.getInt(contentResolver, USE_EXTERNAL_SURROUND_SOUND_FLAG, 0) == 1;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioCapabilities)) {
            return false;
        }
        AudioCapabilities audioCapabilities = (AudioCapabilities) obj;
        return Arrays.equals(this.supportedEncodings, audioCapabilities.supportedEncodings) && this.maxChannelCount == audioCapabilities.maxChannelCount;
    }

    public int getMaxChannelCount() {
        return this.maxChannelCount;
    }

    public int hashCode() {
        return (Arrays.hashCode(this.supportedEncodings) * 31) + this.maxChannelCount;
    }

    public boolean supportsEncoding(int i) {
        return Arrays.binarySearch(this.supportedEncodings, i) >= 0;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AudioCapabilities[maxChannelCount=");
        outline107.append(this.maxChannelCount);
        outline107.append(", supportedEncodings=");
        outline107.append(Arrays.toString(this.supportedEncodings));
        outline107.append("]");
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"InlinedApi"})
    public static AudioCapabilities getCapabilities(Context context, @Nullable Intent intent) {
        boolean z;
        boolean z2;
        if (Util.SDK_INT >= 17) {
            ContentResolver contentResolver = context.getContentResolver();
            z2 = useSurroundSoundFlagV17(contentResolver);
            z = isSurroundSoundEnabledV17(contentResolver);
        } else {
            z = false;
            z2 = false;
        }
        if (z2) {
            return z ? EXTERNAL_SURROUND_SOUND_CAPABILITIES : DEFAULT_AUDIO_CAPABILITIES;
        } else if (deviceMaySetExternalSurroundSoundGlobalSetting() && Settings.Global.getInt(context.getContentResolver(), EXTERNAL_SURROUND_SOUND_KEY, 0) == 1) {
            return EXTERNAL_SURROUND_SOUND_CAPABILITIES;
        } else {
            if (intent != null && intent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) != 0) {
                return new AudioCapabilities(intent.getIntArrayExtra("android.media.extra.ENCODINGS"), intent.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 8));
            }
            return DEFAULT_AUDIO_CAPABILITIES;
        }
    }
}
