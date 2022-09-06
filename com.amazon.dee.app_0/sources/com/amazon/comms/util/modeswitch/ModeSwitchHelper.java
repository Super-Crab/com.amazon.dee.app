package com.amazon.comms.util.modeswitch;

import android.content.Context;
import com.amazon.comms.log.CommsLogger;
import com.google.common.annotations.VisibleForTesting;
/* loaded from: classes12.dex */
public class ModeSwitchHelper {
    @VisibleForTesting
    public static final String MODE_SWITCH_PACKAGE = "amazon.alexa.modeswitch";
    private static ModeSwitchHelper instance;
    private static final CommsLogger sLog = CommsLogger.getLogger(ModeSwitchHelper.class);
    @VisibleForTesting
    Boolean supportsModeSwitch;

    private ModeSwitchHelper() {
    }

    public static synchronized ModeSwitchHelper getInstance() {
        ModeSwitchHelper modeSwitchHelper;
        synchronized (ModeSwitchHelper.class) {
            if (instance == null) {
                instance = new ModeSwitchHelper();
            }
            modeSwitchHelper = instance;
        }
        return modeSwitchHelper;
    }

    public boolean deviceSupportsModeSwitch(Context context) {
        if (this.supportsModeSwitch == null) {
            if (context.getPackageManager().hasSystemFeature(MODE_SWITCH_PACKAGE)) {
                sLog.i("ModeSwitch supported. Setting supportsModeSwitch to true.");
                this.supportsModeSwitch = true;
            } else {
                sLog.i("ModeSwitch not supported. Setting supportsModeSwitch to false.");
                this.supportsModeSwitch = false;
            }
        }
        return this.supportsModeSwitch.booleanValue();
    }
}
