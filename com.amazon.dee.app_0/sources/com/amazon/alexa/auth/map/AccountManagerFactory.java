package com.amazon.alexa.auth.map;

import android.content.Context;
import com.amazon.alexa.auth.AccountManager;
/* loaded from: classes6.dex */
public class AccountManagerFactory {
    public static AccountManager create(Context context) {
        return new MAPAccountManager(context);
    }
}
