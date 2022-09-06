package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.token.CentralTokenManagementCommunication;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class hi {
    public static hh ad(Context context) {
        if (mz.aW(context) && !mz.aj(context)) {
            io.dm("TokenManagementImplementationFactory");
            return new CentralTokenManagementCommunication(context);
        } else if (!mz.bf(context) && mz.aX(context)) {
            io.dm("TokenManagementImplementationFactory");
            return new gx(context);
        } else {
            io.dm("TokenManagementImplementationFactory");
            return hj.ae(context);
        }
    }
}
