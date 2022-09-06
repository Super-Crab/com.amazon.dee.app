package com.amazon.deecomms.core.decoupling;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class MainActivityLoader {
    public boolean startMainActivity(@NonNull Context context, CommsView commsView) {
        char c;
        String string = context.getSharedPreferences(Constants.MAIN_IN_FG, 0).getString(Constants.MAIN_IN_FG, Constants.GONE);
        int hashCode = string.hashCode();
        if (hashCode == -1825417917) {
            if (string.equals(Constants.FOREGROUND)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -847101650) {
            if (hashCode == 2193567 && string.equals(Constants.GONE)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (string.equals(Constants.BACKGROUND)) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1 && c != 2) {
                GeneratedOutlineSupport1.outline164("The status of MainActivity was an unhandled value of ", string, "MainActivityLoader");
                return false;
            }
            try {
                Intent intent = new Intent(context, Class.forName("com.amazon.dee.app.ui.main.MainActivity"));
                intent.setAction(Constants.COMMS_VOICE_NAV_ACTION);
                intent.putExtra(Constants.COMMS_VOICE_NAV_ACTION, commsView.name());
                intent.setFlags(131072);
                intent.setFlags(268435456);
                context.startActivity(intent);
                return true;
            } catch (ClassNotFoundException e) {
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Main activity not found: ");
                outline1.append(e.getCause());
                throw new IllegalArgumentException(outline1.toString());
            }
        }
        return false;
    }
}
