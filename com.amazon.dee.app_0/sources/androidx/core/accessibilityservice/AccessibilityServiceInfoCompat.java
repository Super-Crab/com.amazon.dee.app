package androidx.core.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.redesign.view.carousel.ChipIconTitleViewHolder;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public final class AccessibilityServiceInfoCompat {
    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;

    private AccessibilityServiceInfoCompat() {
    }

    @NonNull
    public static String capabilityToString(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i != 8 ? "UNKNOWN" : "CAPABILITY_CAN_FILTER_KEY_EVENTS" : "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY" : "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION" : "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
    }

    @NonNull
    public static String feedbackTypeToString(int i) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
        while (i > 0) {
            int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(i);
            i &= ~numberOfTrailingZeros;
            if (outline107.length() > 1) {
                outline107.append(", ");
            }
            if (numberOfTrailingZeros == 1) {
                outline107.append("FEEDBACK_SPOKEN");
            } else if (numberOfTrailingZeros == 2) {
                outline107.append("FEEDBACK_HAPTIC");
            } else if (numberOfTrailingZeros == 4) {
                outline107.append("FEEDBACK_AUDIBLE");
            } else if (numberOfTrailingZeros == 8) {
                outline107.append("FEEDBACK_VISUAL");
            } else if (numberOfTrailingZeros == 16) {
                outline107.append("FEEDBACK_GENERIC");
            }
        }
        outline107.append("]");
        return outline107.toString();
    }

    @Nullable
    public static String flagToString(int i) {
        if (i != 1) {
            if (i == 2) {
                return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
            }
            if (i == 4) {
                return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
            }
            if (i == 8) {
                return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            }
            if (i == 16) {
                return "FLAG_REPORT_VIEW_IDS";
            }
            if (i == 32) {
                return "FLAG_REQUEST_FILTER_KEY_EVENTS";
            }
            return null;
        }
        return ChipIconTitleViewHolder.STATE_DEFAULT;
    }

    public static int getCapabilities(@NonNull AccessibilityServiceInfo accessibilityServiceInfo) {
        int i = Build.VERSION.SDK_INT;
        return accessibilityServiceInfo.getCapabilities();
    }

    @Nullable
    public static String loadDescription(@NonNull AccessibilityServiceInfo accessibilityServiceInfo, @NonNull PackageManager packageManager) {
        int i = Build.VERSION.SDK_INT;
        return accessibilityServiceInfo.loadDescription(packageManager);
    }
}
