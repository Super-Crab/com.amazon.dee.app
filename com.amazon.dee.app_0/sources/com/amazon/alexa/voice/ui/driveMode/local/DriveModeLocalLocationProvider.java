package com.amazon.alexa.voice.ui.driveMode.local;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.util.IntentUtils;
import com.amazon.regulator.ViewController;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class DriveModeLocalLocationProvider {
    private static final String GOOGLE_NAVIGATION_PREFIX = "google.navigation:q=";
    private static final String MAP_ADDRESS_PREFIX = "geo:0,0?q=";
    private static final String TAG = "DriveModeLocalLocationProvider";
    private ViewController viewController;

    public DriveModeLocalLocationProvider(ViewController viewController) {
        this.viewController = viewController;
    }

    public void mapAddress(String str) {
        ViewController viewController = this.viewController;
        IntentUtils.openLinkUrl(viewController, MAP_ADDRESS_PREFIX + str);
    }

    public void mapGeoLocation(String str) throws IllegalArgumentException {
        String[] split = str.split(":");
        if (split.length >= 2 && split[1].split(",").length >= 2) {
            ViewController viewController = this.viewController;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(GOOGLE_NAVIGATION_PREFIX);
            outline107.append(split[1]);
            IntentUtils.openLinkUrl(viewController, outline107.toString());
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid geo location", str));
    }

    public void showLocation(CharSequence charSequence, @NonNull CharSequence charSequence2) {
        if (charSequence != null && charSequence.length() != 0) {
            try {
                mapGeoLocation(charSequence.toString());
                return;
            } catch (IllegalArgumentException unused) {
                mapAddress(charSequence2.toString());
                return;
            }
        }
        mapAddress(charSequence2.toString());
    }
}
