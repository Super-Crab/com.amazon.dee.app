package amazon.speech.model;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class AlexaStateHelper {
    public static final String ALEXA_AVAILABILITY = "alexa_availability";
    public static final int DISABLED_SETTINGS = 1;
    public static final int FEATURE_DISABLED = 64;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = 0;
    public static final int PRIORITY_UNKNOWN = -1;
    public static final int UNAVAILABLE_AMA_DEVICE_CONNECTED = 256;
    public static final int UNAVAILABLE_DEVICE_POLICY = 4;
    public static final int UNAVAILABLE_GAME_MODE = 512;
    public static final int UNAVAILABLE_NON_SUPPORTED_LOCALE = 32;
    public static final int UNAVAILABLE_NON_SUPPORTED_MARKETPLACE = 8;
    public static final int UNAVAILABLE_NON_SUPPORTED_PROFILE_TYPE = 16;
    public static final int UNAVAILABLE_OTHER = 65536;
    public static final int UNAVAILABLE_PARENTAL_CONTROLS = 2;
    public static final int UNAVAILABLE_SECONDARY_PROFILES = 128;

    public static int getAlexaUnavailabilityPriority(int i) {
        if (i != 2) {
            return (i == 4 || i == 8 || i == 16 || i == 32 || i == 64 || i == 128 || i == 256 || i == 512 || i == 65536) ? 1 : -1;
        }
        return 0;
    }

    public static ArrayList<Integer> getAlexaUnavailabilityReason(int i) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>() { // from class: amazon.speech.model.AlexaStateHelper.1
            {
                add(2);
                add(4);
                add(8);
                add(16);
                add(32);
                add(64);
                add(128);
                add(256);
                add(512);
                add(65536);
            }
        };
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            int intValue = arrayList2.get(i2).intValue();
            if ((i & intValue) == intValue) {
                arrayList.add(Integer.valueOf(intValue));
            }
        }
        return arrayList;
    }

    public static boolean isAlexaAvailable(int i) {
        return (i >> 1) == 0;
    }

    public static boolean isAlexaDisabled(int i) {
        return (i & 1) == 1;
    }
}
