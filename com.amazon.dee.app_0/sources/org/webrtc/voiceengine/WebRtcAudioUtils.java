package org.webrtc.voiceengine;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.webrtc.Logging;
/* loaded from: classes5.dex */
public final class WebRtcAudioUtils {
    private static final int DEFAULT_SAMPLE_RATE_HZ = 16000;
    public static final int FORCE_OUTPUT_AUDIO_DEVICE_NOT_SUPPORTED = -1;
    private static final String TAG = "WebRtcAudioUtils";
    public static int defaultOutputAudioDevice = -1;
    private static int defaultOutputSampleRateHz = 16000;
    private static int defaultSampleRateHz = 16000;
    private static int defaultStreamType = 0;
    private static boolean isDefaultOutputDeviceSet = false;
    private static boolean isDefaultOutputSampleRateOverridden = false;
    private static boolean isDefaultSampleRateOverridden = false;
    private static boolean isDefaultStreamTypeOverridden = false;
    private static boolean isRenderTrackBufferSizeOverriden = false;
    private static boolean isRequestingFlagFastViaJavaAudioTrack = false;
    private static boolean isRequestingFlagFastViaNativeTrack = false;
    private static int outputAudioDevice = -1;
    private static boolean outputAudioDeviceChanged = false;
    private static int requestedRenderTrackBufferSizeBytes = -1;
    private static final String[] BLACKLISTED_OPEN_SL_ES_MODELS = new String[0];
    private static final String[] BLACKLISTED_AEC_MODELS = new String[0];
    private static final String[] BLACKLISTED_NS_MODELS = new String[0];
    private static List<Integer> audioRenderTrackUnderrunReports = new LinkedList();
    private static boolean useWebRtcBasedAcousticEchoCanceler = false;
    private static boolean useWebRtcBasedNoiseSuppressor = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void addAudioRenderTrackUnderrunReport(int i) {
        synchronized (WebRtcAudioUtils.class) {
            if (i < 0) {
                Logging.w(TAG, "addAudioTrackUnderrunReport received underrunCount as = " + i);
                return;
            }
            if (audioRenderTrackUnderrunReports.size() > 10) {
                Logging.w(TAG, "audioRenderTrackUnderrunReports removing first entry before update. Size= " + audioRenderTrackUnderrunReports.size());
                audioRenderTrackUnderrunReports.remove(0);
            }
            audioRenderTrackUnderrunReports.add(Integer.valueOf(i));
        }
    }

    public static boolean deviceIsBlacklistedForOpenSLESUsage() {
        return Arrays.asList(BLACKLISTED_OPEN_SL_ES_MODELS).contains(Build.MODEL);
    }

    public static List<String> getBlackListedModelsForAecUsage() {
        return Arrays.asList(BLACKLISTED_AEC_MODELS);
    }

    public static List<String> getBlackListedModelsForNsUsage() {
        return Arrays.asList(BLACKLISTED_NS_MODELS);
    }

    public static synchronized int getDefaultOutputSampleRateHz() {
        int i;
        synchronized (WebRtcAudioUtils.class) {
            i = defaultOutputSampleRateHz;
        }
        return i;
    }

    public static synchronized int getDefaultSampleRateHz() {
        int i;
        synchronized (WebRtcAudioUtils.class) {
            i = defaultSampleRateHz;
        }
        return i;
    }

    public static synchronized int getDefaultStreamType() {
        int i;
        synchronized (WebRtcAudioUtils.class) {
            i = defaultStreamType;
        }
        return i;
    }

    public static synchronized int getOutputAudioDevice() {
        int i;
        synchronized (WebRtcAudioUtils.class) {
            i = outputAudioDevice;
        }
        return i;
    }

    public static int getOverridenRenderTrackBufferSize() {
        return requestedRenderTrackBufferSizeBytes;
    }

    public static String getThreadInfo() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("@[name=");
        outline107.append(Thread.currentThread().getName());
        outline107.append(", id=");
        outline107.append(Thread.currentThread().getId());
        outline107.append("]");
        return outline107.toString();
    }

    public static boolean hasPermission(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    public static boolean isAcousticEchoCancelerSupported() {
        return WebRtcAudioEffects.canUseAcousticEchoCanceler();
    }

    public static boolean isAutomaticGainControlSupported() {
        return false;
    }

    public static synchronized boolean isDefaultOutputSampleRateOverridden() {
        boolean z;
        synchronized (WebRtcAudioUtils.class) {
            z = isDefaultOutputSampleRateOverridden;
        }
        return z;
    }

    public static synchronized boolean isDefaultSampleRateOverridden() {
        boolean z;
        synchronized (WebRtcAudioUtils.class) {
            z = isDefaultSampleRateOverridden;
        }
        return z;
    }

    public static synchronized boolean isDefaultStreamTypeOverridden() {
        boolean z;
        synchronized (WebRtcAudioUtils.class) {
            z = isDefaultStreamTypeOverridden;
        }
        return z;
    }

    public static synchronized boolean isFlagFastRequestedViaAudioTrack() {
        boolean z;
        synchronized (WebRtcAudioUtils.class) {
            z = isRequestingFlagFastViaJavaAudioTrack;
        }
        return z;
    }

    public static synchronized boolean isFlagFastRequestedViaNativeTrack() {
        boolean z;
        synchronized (WebRtcAudioUtils.class) {
            z = isRequestingFlagFastViaNativeTrack;
        }
        return z;
    }

    public static boolean isNoiseSuppressorSupported() {
        return WebRtcAudioEffects.canUseNoiseSuppressor();
    }

    public static synchronized boolean isOutputAudioDeviceChanged() {
        boolean z;
        synchronized (WebRtcAudioUtils.class) {
            z = outputAudioDeviceChanged;
        }
        return z;
    }

    public static boolean isRenderTrackBufferSizeOverriden() {
        return isRenderTrackBufferSizeOverriden;
    }

    public static void logDeviceInfo(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Android SDK: ");
        outline107.append(Build.VERSION.SDK_INT);
        outline107.append(", Release: ");
        outline107.append(Build.VERSION.RELEASE);
        outline107.append(", Brand: ");
        outline107.append(Build.BRAND);
        outline107.append(", Device: ");
        outline107.append(Build.DEVICE);
        outline107.append(", Id: ");
        outline107.append(Build.ID);
        outline107.append(", Hardware: ");
        outline107.append(Build.HARDWARE);
        outline107.append(", Manufacturer: ");
        outline107.append(Build.MANUFACTURER);
        outline107.append(", Model: ");
        outline107.append(Build.MODEL);
        outline107.append(", Product: ");
        outline107.append(Build.PRODUCT);
        Logging.d(str, outline107.toString());
    }

    public static synchronized List<Integer> readAndResetUnderrunCounts() {
        ArrayList arrayList;
        synchronized (WebRtcAudioUtils.class) {
            if (!runningOnNougatOrHigher() && !isFlagFastRequestedViaNativeTrack()) {
                Logging.w(TAG, "readAndResetUnderrunCounts not supported below Nougat for AudioTrack path!");
            }
            arrayList = new ArrayList(audioRenderTrackUnderrunReports);
            audioRenderTrackUnderrunReports.clear();
        }
        return arrayList;
    }

    public static synchronized void requestFlagFastIfPossible(boolean z) {
        synchronized (WebRtcAudioUtils.class) {
            if (z) {
                isRequestingFlagFastViaJavaAudioTrack = true;
                isRequestingFlagFastViaNativeTrack = false;
            } else {
                isRequestingFlagFastViaJavaAudioTrack = false;
                isRequestingFlagFastViaNativeTrack = true;
            }
        }
    }

    public static synchronized void resetOutputAudioDevice() {
        synchronized (WebRtcAudioUtils.class) {
            if (outputAudioDevice != defaultOutputAudioDevice) {
                outputAudioDevice = -1;
            }
            outputAudioDeviceChanged = false;
        }
    }

    public static synchronized void resetOutputAudioDeviceChanged() {
        synchronized (WebRtcAudioUtils.class) {
            outputAudioDeviceChanged = false;
        }
    }

    public static boolean runningOnEmulator() {
        return Build.HARDWARE.equals("goldfish") && Build.BRAND.startsWith("generic_");
    }

    public static boolean runningOnJellyBeanMR1OrHigher() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean runningOnJellyBeanMR2OrHigher() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean runningOnLollipop() {
        int i = Build.VERSION.SDK_INT;
        return false;
    }

    public static boolean runningOnLollipopOrHigher() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean runningOnMarshmallowOrHigher() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean runningOnNougatOrHigher() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static synchronized void setDefaultInputOutputSampleRateHz(int i, int i2) {
        synchronized (WebRtcAudioUtils.class) {
            isDefaultSampleRateOverridden = true;
            defaultSampleRateHz = i;
            isDefaultOutputSampleRateOverridden = true;
            defaultOutputSampleRateHz = i2;
        }
    }

    public static synchronized void setDefaultSampleRateHz(int i) {
        synchronized (WebRtcAudioUtils.class) {
            isDefaultSampleRateOverridden = true;
            defaultSampleRateHz = i;
            isDefaultOutputSampleRateOverridden = true;
            defaultOutputSampleRateHz = i;
        }
    }

    public static synchronized void setDefaultStreamType(int i) {
        synchronized (WebRtcAudioUtils.class) {
            if (runningOnMarshmallowOrHigher()) {
                Logging.w(TAG, "setDefaultStreamType() is deprecated on Marshmallow or later Android version");
                return;
            }
            isDefaultStreamTypeOverridden = true;
            defaultStreamType = i;
        }
    }

    public static synchronized void setOutputAudioDevice(int i) {
        synchronized (WebRtcAudioUtils.class) {
            if (!isDefaultOutputDeviceSet) {
                Logging.d(TAG, "Setting default output audio device to <" + i + Config.Compare.GREATER_THAN);
                defaultOutputAudioDevice = i;
                isDefaultOutputDeviceSet = true;
            }
            if (outputAudioDevice != i) {
                Logging.d(TAG, "Setting output audio device to <" + i + Config.Compare.GREATER_THAN);
                outputAudioDeviceChanged = true;
                outputAudioDevice = i;
            } else {
                Logging.d(TAG, "Already using audio device <" + i + Config.Compare.GREATER_THAN);
            }
        }
    }

    public static synchronized void setRenderTrackBufferSize(int i) {
        synchronized (WebRtcAudioUtils.class) {
            if (i < 1) {
                isRenderTrackBufferSizeOverriden = false;
                return;
            }
            isRenderTrackBufferSizeOverriden = true;
            requestedRenderTrackBufferSizeBytes = i;
        }
    }

    public static synchronized void setWebRtcBasedAcousticEchoCanceler(boolean z) {
        synchronized (WebRtcAudioUtils.class) {
            useWebRtcBasedAcousticEchoCanceler = z;
        }
    }

    public static synchronized void setWebRtcBasedAutomaticGainControl(boolean z) {
        synchronized (WebRtcAudioUtils.class) {
            Logging.w(TAG, "setWebRtcBasedAutomaticGainControl() is deprecated");
        }
    }

    public static synchronized void setWebRtcBasedNoiseSuppressor(boolean z) {
        synchronized (WebRtcAudioUtils.class) {
            useWebRtcBasedNoiseSuppressor = z;
        }
    }

    public static synchronized boolean useWebRtcBasedAcousticEchoCanceler() {
        boolean z;
        synchronized (WebRtcAudioUtils.class) {
            if (useWebRtcBasedAcousticEchoCanceler) {
                Logging.w(TAG, "Overriding default behavior; now using WebRTC AEC!");
            }
            z = useWebRtcBasedAcousticEchoCanceler;
        }
        return z;
    }

    public static synchronized boolean useWebRtcBasedAutomaticGainControl() {
        synchronized (WebRtcAudioUtils.class) {
        }
        return true;
    }

    public static synchronized boolean useWebRtcBasedNoiseSuppressor() {
        boolean z;
        synchronized (WebRtcAudioUtils.class) {
            if (useWebRtcBasedNoiseSuppressor) {
                Logging.w(TAG, "Overriding default behavior; now using WebRTC NS!");
            }
            z = useWebRtcBasedNoiseSuppressor;
        }
        return z;
    }
}
