package amazon.speech.util;

import amazon.speech.util.DebugUtil;
import android.content.Context;
import android.media.AudioManager;
import android.os.Trace;
import java.lang.reflect.Method;
import java.util.Locale;
/* loaded from: classes.dex */
public class AudioManagerWrapper {
    public static final String TAG = DebugUtil.getTag(DebugUtil.Module.SIM, AudioManagerWrapper.class);
    public static final int UTTERANCE_RECOGNIZED = 2;
    static Method sGetPackageInFocusMethod;
    static Method sNotifySpeechStateMethod;
    static Method sSwitchWakeWordModelMethod;
    private final AudioManager mAudioManager;

    static {
        try {
            sNotifySpeechStateMethod = Class.forName("amazon.media.AmazonAudioManager").getMethod("notifySpeechState", Integer.TYPE);
        } catch (ReflectiveOperationException unused) {
            Log.d(TAG, "unable to get notifySpeechState method.");
            sNotifySpeechStateMethod = null;
        }
        try {
            sGetPackageInFocusMethod = Class.forName("amazon.media.AmazonAudioManager").getMethod("getPackageInFocus", new Class[0]);
        } catch (ReflectiveOperationException unused2) {
            Log.d(TAG, "unable to get packageInFocus method.");
            sGetPackageInFocusMethod = null;
        }
        try {
            sSwitchWakeWordModelMethod = Class.forName("amazon.media.AmazonAudioManager").getDeclaredMethod("switchWakeWordModel", String.class, Locale.class);
        } catch (ReflectiveOperationException unused3) {
            Log.d(TAG, "unable to get switchWakeWordModel method.");
            sSwitchWakeWordModelMethod = null;
        }
    }

    public AudioManagerWrapper(Context context) {
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
    }

    public void abandonAudioFocus(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        Trace.beginSection("abandonAudioFocus");
        this.mAudioManager.abandonAudioFocus(onAudioFocusChangeListener);
        Trace.endSection();
    }

    public int acquireAudioFocus(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, int i) {
        try {
            Trace.beginSection("AudioManagerWrapper_acquireAudioFocus");
            return this.mAudioManager.requestAudioFocus(onAudioFocusChangeListener, 3, i);
        } finally {
            Trace.endSection();
        }
    }

    public void adjustStreamVolume(int i, int i2, int i3) {
        this.mAudioManager.adjustStreamVolume(i, i2, i3);
    }

    public String getPackageInFocus() {
        try {
            if (sGetPackageInFocusMethod != null) {
                try {
                    Trace.beginSection("AudioManagerWrapper_getPackageInFocus");
                    String str = (String) sGetPackageInFocusMethod.invoke(this.mAudioManager, new Object[0]);
                    Trace.endSection();
                    return str;
                } catch (IllegalArgumentException unused) {
                    Log.w(TAG, "getPackageInFocus method unavailable, returning null");
                    Trace.endSection();
                    return null;
                } catch (ReflectiveOperationException e) {
                    Log.e(TAG, "error invoking getPackageInFocus method");
                    throw new RuntimeException(e);
                }
            }
            Log.d(TAG, "unable to invoke getPackageInFocus method, returning null...");
            return null;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public int getStreamMaxVolume(int i) {
        return this.mAudioManager.getStreamMaxVolume(i);
    }

    public int getStreamVolume(int i) {
        return this.mAudioManager.getStreamVolume(i);
    }

    public boolean isMuted() {
        AudioManager audioManager = this.mAudioManager;
        return audioManager != null && audioManager.getStreamVolume(3) == 0;
    }

    public void notifySpeechState(int i) {
        try {
            if (sNotifySpeechStateMethod != null) {
                try {
                    Trace.beginSection("notifySpeechState");
                    sNotifySpeechStateMethod.invoke(this.mAudioManager, Integer.valueOf(i));
                    Trace.endSection();
                    return;
                } catch (IllegalArgumentException unused) {
                    Log.w(TAG, "notifySpeechState method unavailable");
                    Trace.endSection();
                    return;
                } catch (ReflectiveOperationException e) {
                    Log.e(TAG, "error invoking notifySpeechState method");
                    throw new RuntimeException(e);
                }
            }
            Log.d(TAG, "unable to invoke notifySpeechState method, ignoring...");
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public void setStreamVolume(int i, int i2, int i3) {
        this.mAudioManager.setStreamVolume(i, i2, i3);
    }

    public int switchWakeWordModel(String str, Locale locale) {
        try {
            if (sSwitchWakeWordModelMethod != null) {
                try {
                    Trace.beginSection("switchWakeWordModel");
                    int intValue = ((Integer) sSwitchWakeWordModelMethod.invoke(this.mAudioManager, str, locale)).intValue();
                    Trace.endSection();
                    return intValue;
                } catch (IllegalArgumentException unused) {
                    Log.w(TAG, "switchWakeWordModel method unavailable");
                    Trace.endSection();
                    return -1;
                } catch (ReflectiveOperationException e) {
                    Log.e(TAG, "error invoking switchWakeWordModel method");
                    throw new RuntimeException(e);
                }
            }
            Log.d(TAG, "unable to invoke switchWakeWordModel method, returning -1");
            return -1;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }
}
