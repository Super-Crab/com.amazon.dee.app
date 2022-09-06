package com.amazon.deecomms.conversation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes12.dex */
public class ConversationNotificationReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (Constants.NOTIFICATION_CLICK_ACTION.equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("notification_redirect_path");
            if (TextUtils.isEmpty(stringExtra)) {
                return;
            }
            try {
                Intent intent2 = new Intent(context, Class.forName("com.amazon.dee.app.ui.main.MainActivity"));
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("conversations/");
                outline1.append(Uri.encode(stringExtra));
                outline1.append("/start/");
                outline1.append(false);
                intent2.setData(Uri.parse(outline1.toString()));
                intent2.setFlags(335544320);
                intent2.setAction("android.intent.action.VIEW");
                intent2.addCategory("android.intent.category.DEFAULT");
                context.startActivity(intent2);
            } catch (ClassNotFoundException e) {
                StringBuilder outline12 = GeneratedOutlineSupport.outline1("Main activity not found: ");
                outline12.append(e.getCause());
                throw new IllegalArgumentException(outline12.toString());
            }
        }
    }
}
