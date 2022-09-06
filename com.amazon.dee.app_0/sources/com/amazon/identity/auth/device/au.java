package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.utils.AccountConstants;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class au {
    public static boolean a(im imVar) {
        String key = imVar.getKey();
        return CustomerAttributeKeys.KEY_COR.equals(key) || CustomerAttributeKeys.KEY_PFM.equals(key);
    }

    public static boolean b(im imVar) {
        return "com.amazon.dcp.sso.property.deviceemail".equals(imVar.getKey()) || "com.amazon.dcp.sso.property.devicename".equals(imVar.getKey()) || "com.amazon.dcp.sso.property.username".equals(imVar.getKey()) || "com.amazon.dcp.sso.property.firstname".equals(imVar.getKey()) || "com.amazon.dcp.sso.token.devicedevicetype".equals(imVar.getKey()) || "com.amazon.dcp.sso.token.device.deviceserialname".equals(imVar.getKey()) || "com.amazon.dcp.sso.token.device.accountpool".equals(imVar.getKey()) || "com.amazon.dcp.sso.property.account.delegateeaccount".equals(imVar.getKey()) || CustomerAttributeKeys.KEY_IS_ANONYMOUS.equals(imVar.getKey()) || AccountConstants.KEY_ACCOUNT_UUID.equals(imVar.getKey()) || AccountConstants.KEY_SECONDARY_AMAZON_ACCOUNT.equals(imVar.getKey()) || AccountConstants.KEY_DEVICE_ACCOUNT_ROLE.equals(imVar.getKey()) || imVar.getKey().startsWith("com.amazon.dcp.sso.property.account.extratokens");
    }

    public static boolean c(im imVar) {
        return "com.amazon.dcp.sso.token.cookie.xmainAndXabcCookies".equals(imVar.getKey());
    }

    public static boolean d(im imVar) {
        return "com.amazon.identity.cookies.xfsn".equals(imVar.getKey());
    }
}
