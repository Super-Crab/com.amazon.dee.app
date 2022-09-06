package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.dependency.PandaServiceAccessor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bg {
    private static final String TAG = "com.amazon.identity.auth.device.bg";
    private final PandaServiceAccessor gS;
    private final Context mContext;

    public bg(Context context) {
        this(context, new PandaServiceAccessor(context));
    }

    public static boolean o(Context context) {
        return new Date(new gp(context, "authority.signature.expiry.store").cv("authority.signature.expiry.key")).before(new Date());
    }

    public void a(String str, bh bhVar, Callback callback, ej ejVar) {
        try {
            JSONObject bG = this.gS.a(str, bhVar, ejVar).bG();
            String string = bG.getString(MAPAccountManager.KEY_SSO_CODE);
            long parseLong = Long.parseLong(bG.getString("expiresIn"));
            JSONArray jSONArray = bG.getJSONArray("listOfAccounts");
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string2 = jSONObject.getString("directedId");
                String string3 = jSONObject.getString("loginName");
                String string4 = jSONObject.getString("customerName");
                if (!jg.dD(string2) && !jg.dD(string3) && !jg.dD(string4)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(MAPAccountManager.KEY_SSO_ACCOUNT_DIRECTED_ID, string2);
                    hashMap.put(MAPAccountManager.KEY_SSO_ACCOUNT_LOGIN, string3);
                    hashMap.put(MAPAccountManager.KEY_SSO_ACCOUNT_CUSTOMER_NAME, string4);
                    arrayList.add(hashMap);
                }
                a(callback, MAPAccountManager.BootstrapError.INVALID_RESPONSE.value(), "Response contains empty fields");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(MAPAccountManager.KEY_SSO_CODE, string);
            bundle.putLong(MAPAccountManager.KEY_SSO_CODE_TIME_TO_LIVE, parseLong);
            bundle.putSerializable(MAPAccountManager.KEY_SSO_ACCOUNTS_LIST, arrayList);
            bundle.putString(MAPAccountManager.KEY_SSO_BOOTSTRAPPED_FROM_DEVICE_TYPE, bhVar.getDeviceType());
            bundle.putString(MAPAccountManager.KEY_SSO_BOOTSTRAPPED_FROM_DEVICE_SERIAL, bhVar.bq());
            callback.onSuccess(bundle);
        } catch (PandaServiceAccessor.PandaServiceException e) {
            io.e(TAG, "service exception when calling panda", e);
            a(callback, MAPAccountManager.BootstrapError.INVALID_RESPONSE.value(), e.getMessage());
        } catch (IOException e2) {
            io.e(TAG, "i/o exception when calling panda", e2);
            a(callback, MAPAccountManager.BootstrapError.NETWORK_FAILURE.value(), e2.getMessage());
        } catch (JSONException e3) {
            io.e(TAG, "Json exception when calling panda", e3);
            a(callback, MAPAccountManager.BootstrapError.INVALID_RESPONSE.value(), e3.getMessage());
        }
    }

    public bg(Context context, PandaServiceAccessor pandaServiceAccessor) {
        this.mContext = context;
        this.gS = pandaServiceAccessor;
    }

    public Set<String> a(bf bfVar, ej ejVar) {
        HashSet hashSet = new HashSet();
        try {
            PandaServiceAccessor.a b = this.gS.b(bfVar, ejVar);
            JSONArray jSONArray = b.bG().getJSONArray("authoritySignatures");
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.getString(i));
            }
            new gp(this.mContext, "authority.signature.expiry.store").a("authority.signature.expiry.key", b.bH().getTime());
        } catch (PandaServiceAccessor.PandaServiceException e) {
            io.e(TAG, "service exception when calling panda", e);
        } catch (IOException e2) {
            io.e(TAG, "i/o exception when calling panda", e2);
        } catch (JSONException e3) {
            io.e(TAG, "Json exception when calling panda", e3);
        }
        return hashSet;
    }

    private void a(Callback callback, int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.dcp.sso.ErrorCode", i);
        bundle.putString("com.amazon.dcp.sso.ErrorMessage", str);
        callback.onError(bundle);
    }
}
