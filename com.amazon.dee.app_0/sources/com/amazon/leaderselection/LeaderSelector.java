package com.amazon.leaderselection;

import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import com.amazon.alexa.utils.security.ComponentEnabler;
import com.amazon.alexa.utils.security.SignatureVerifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class LeaderSelector {
    private static final String TAG = "LeaderSelector";
    private final Context context;
    private final i leaderAuthority;
    private final x serviceNameVerifier;
    private final ComponentName thisCandidateComponentName;

    public LeaderSelector(Context context) {
        this.context = context;
        this.thisCandidateComponentName = new ComponentName(context, LeaderSelectionService.class);
        this.serviceNameVerifier = new x(context.getPackageManager());
        this.leaderAuthority = new i(this.thisCandidateComponentName, new s(v.a(context), new d(context.getPackageName(), context.getPackageManager(), new f(context.getPackageManager())), new SignatureVerifier(context)), new y(context, "com.amazon.leaderselection.LEADER_AUTHORITY_PREFERENCES_NAMESPACE"));
    }

    public static void enable(Context context, boolean z) {
        ComponentEnabler.setServiceEnabled(context, LeaderSelectionService.class, z);
    }

    public static boolean isEnabled(Context context) {
        return ComponentEnabler.checkIfServiceIsEnabled(context, LeaderSelectionService.class);
    }

    private Leader makeSelectionRequest(String str) {
        return new k(str, new a(this.context, this.thisCandidateComponentName)).a();
    }

    public Leader select(String str) {
        GeneratedOutlineSupport1.outline158("Selecting leader for: ", str);
        if (this.leaderAuthority.b()) {
            Candidate a = this.leaderAuthority.a();
            if (this.serviceNameVerifier.a(a, str)) {
                return Leader.create(a.getPackageName(), str);
            }
            Log.i(TAG, "Existing leader doesn't provide requested service. Initiate new leader selection process.");
            return makeSelectionRequest(str);
        }
        return makeSelectionRequest(str);
    }
}
