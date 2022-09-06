package amazon.speech.audio;

import amazon.speech.audio.v2.AudioStreamV2Factory;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class AudioStreamMajorVersion {
    private static final String TAG = GeneratedOutlineSupport1.outline39(AudioStreamMajorVersion.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    private static Version gVersion;

    /* renamed from: amazon.speech.audio.AudioStreamMajorVersion$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$amazon$speech$audio$AudioStreamMajorVersion$Version = new int[Version.values().length];

        static {
            try {
                $SwitchMap$amazon$speech$audio$AudioStreamMajorVersion$Version[Version.V1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$speech$audio$AudioStreamMajorVersion$Version[Version.V2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum Version {
        V1,
        V2
    }

    static {
        gVersion = Version.V1;
        try {
            Class<?> cls = Class.forName("amazon.speech.io.ipcdatastream.VersionInfo");
            Integer num = (Integer) cls.getMethod("getMajorVersion", new Class[0]).invoke(cls.getConstructor(new Class[0]).newInstance(new Object[0]), new Object[0]);
            if (num.intValue() == 2) {
                gVersion = Version.V2;
                Log.i(TAG, "ipcdatastream is available. Found majorVersion = " + num);
            } else {
                Log.w(TAG, "Unsupported version of ipcdatastream. Found majorVersion = " + num);
            }
        } catch (Exception e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ipcdatastream is not available. Message: ");
            outline107.append(e.getMessage());
            Log.i(str, outline107.toString());
        }
    }

    public static AudioStreamFactory createAudioStreamFactory() {
        int ordinal = getVersion().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return new AudioStreamV2Factory();
            }
            throw new IllegalStateException("Unknown version of AudioStream");
        }
        return new AudioStreamV1Factory();
    }

    public static Version getVersion() {
        return gVersion;
    }
}
