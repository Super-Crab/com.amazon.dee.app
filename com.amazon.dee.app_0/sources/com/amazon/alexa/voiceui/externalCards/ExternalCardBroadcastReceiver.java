package com.amazon.alexa.voiceui.externalCards;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class ExternalCardBroadcastReceiver extends BroadcastReceiver {
    private final Context context;
    private final ExternalCardListener externalCardListener;

    /* loaded from: classes11.dex */
    static class Actions {
        static final String EXTERNAL_CARD_RENDERED = "com.amazon.alexa.intent.action.EXTERNAL_CARD_RENDERED";

        Actions() {
        }
    }

    public ExternalCardBroadcastReceiver(Context context, ExternalCardListener externalCardListener) {
        this.context = context;
        this.externalCardListener = externalCardListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Preconditions.notNull(action, "action is null");
        if (((action.hashCode() == 1230239358 && action.equals("com.amazon.alexa.intent.action.EXTERNAL_CARD_RENDERED")) ? (char) 0 : (char) 65535) != 0) {
            return;
        }
        this.externalCardListener.onExternalCardRendered();
    }

    public void register() {
        this.context.registerReceiver(this, GeneratedOutlineSupport1.outline10("com.amazon.alexa.intent.action.EXTERNAL_CARD_RENDERED"));
    }

    public void unregister() {
        this.context.unregisterReceiver(this);
    }
}
