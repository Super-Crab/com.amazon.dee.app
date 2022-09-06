package com.amazon.alexa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.identity.auth.device.utils.AccountConstants;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AuthenticationChangeBroadcastReceiver.java */
@Singleton
/* loaded from: classes.dex */
public class mCQ extends BroadcastReceiver {
    public static final String zZm = "mCQ";
    public final Context BIo;
    public final AlexaClientEventBus zQM;

    @Inject
    public mCQ(Context context, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = context;
        this.zQM = alexaClientEventBus;
    }

    public void BIo() {
        this.BIo.unregisterReceiver(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        char c;
        String action = intent.getAction();
        int hashCode = action.hashCode();
        if (hashCode != 2037821440) {
            if (hashCode == 2081236832 && action.equals("com.amazon.dcp.sso.action.account.removed")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (action.equals("com.amazon.dcp.sso.action.account.added")) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            this.zQM.zyO(new qiO());
        } else if (c != 1) {
        } else {
            this.zQM.zyO(new Oin());
        }
    }

    public void zZm() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.amazon.dcp.sso.action.account.added");
        intentFilter.addAction("com.amazon.dcp.sso.action.account.removed");
        this.BIo.registerReceiver(this, intentFilter, AccountConstants.PERMISSION_AMAZON_ACCOUNT_CHANGED, null);
    }
}
