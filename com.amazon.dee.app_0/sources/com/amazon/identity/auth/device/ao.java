package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ao implements as {
    private final ds bb;
    private final ec dr;
    private final boolean ds;
    private final ed o;

    /* renamed from: do  reason: not valid java name */
    public static final Uri f0do = Uri.parse("content://amazon_customer_attribute_store");
    public static final Uri dp = Uri.parse("content://amazon_customer_attribute_store_directboot");
    public static final List<String> dq = Arrays.asList("bundle_value");
    private static final String TAG = ao.class.getName();

    public ao(ed edVar) {
        this(edVar, (ds) edVar.getSystemService("sso_platform"), new ec(edVar), false);
    }

    @Override // com.amazon.identity.auth.device.as
    public Bundle peekAttribute(String str, String str2) {
        JSONObject a = a("peekAttribute", str, str2, null, null);
        if (a == null) {
            io.e(TAG, "Failed to construct peek attribute command");
            return null;
        }
        try {
            Bundle a2 = a(a);
            return a2 == null ? am.e(MAPError.AttributeError.GET_ATTRIBUTE_FAILED, "CustomerAttributeStore returned null", 4, "CustomerAttributeStore returned null") : a2;
        } catch (RemoteMAPException e) {
            io.e(TAG, "Failed to call peekAttribute", e);
            return am.e(MAPError.AttributeError.GET_ATTRIBUTE_FAILED, "Failed to call peekAttribute", 4, "Failed to call peekAttribute");
        }
    }

    @Override // com.amazon.identity.auth.device.as
    public MAPFuture<Bundle> setAttribute(final String str, final String str2, final String str3, Callback callback) {
        final bl blVar = new bl(callback);
        ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.ao.2
            @Override // java.lang.Runnable
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putString(str2, str3);
                JSONObject a = ao.this.a("setAttribute", str, str2, bundle, null);
                if (a != null) {
                    Bundle bundle2 = null;
                    try {
                        bundle2 = ao.this.a(a);
                    } catch (RemoteMAPException e) {
                        io.e(ao.TAG, "Failed to setAttribute in central customer attribute store", e);
                    }
                    String format = String.format("Unable to set the attribute for key: %s.", str2);
                    ao.a(blVar, bundle2, 5, format, MAPError.AttributeError.SET_ATTRIBUTE_FAILED, format);
                    return;
                }
                am.c(blVar, MAPError.CommonError.INTERNAL_ERROR, "Cannot construct command", 1, "Cannot construct command");
            }
        });
        return blVar;
    }

    public ao(ed edVar, byte b) {
        this(edVar, (ds) edVar.getSystemService("sso_platform"), new ec(edVar), true);
    }

    @Override // com.amazon.identity.auth.device.as
    public MAPFuture<Bundle> a(final String str, final String str2, Callback callback, final Bundle bundle, final EnumSet<CustomerAttributeStore.GetAttributeOptions> enumSet, ej ejVar) {
        final bl blVar = new bl(callback);
        ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.ao.1
            @Override // java.lang.Runnable
            public void run() {
                JSONObject a = ao.this.a("getAttribute", str, str2, bundle, enumSet);
                if (a != null) {
                    try {
                        Bundle a2 = ao.this.a(a);
                        String format = String.format("Key %s not supported", str2);
                        ao.a(blVar, a2, 2, format, MAPError.AttributeError.KEY_NOT_FOUND, format);
                        return;
                    } catch (RemoteMAPException e) {
                        io.e(ao.TAG, "Failed to call getAttribute", e);
                        am.c(blVar, MAPError.AttributeError.GET_ATTRIBUTE_FAILED, "Failed to call getAttribute", 4, "Failed to call getAttribute");
                        return;
                    }
                }
                am.c(blVar, MAPError.CommonError.INTERNAL_ERROR, "Cannot construct command", 1, "Cannot construct command");
            }
        });
        return blVar;
    }

    public ao(ed edVar, ds dsVar, ec ecVar, boolean z) {
        this.o = edVar;
        this.bb = dsVar;
        this.dr = ecVar;
        this.ds = z;
    }

    private dj<Bundle> a(final Uri uri, final String str) {
        return new dj<Bundle>() { // from class: com.amazon.identity.auth.device.ao.3
            @Override // com.amazon.identity.auth.device.dj
            /* renamed from: a */
            public Bundle b(ContentProviderClient contentProviderClient) throws RemoteException {
                Cursor query = contentProviderClient.query(uri, (String[]) ao.dq.toArray(new String[0]), str, null, null);
                try {
                    return ao.a(query, str);
                } finally {
                    if (query != null) {
                        query.close();
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle a(JSONObject jSONObject) throws RemoteMAPException {
        String jSONObject2 = jSONObject.toString();
        if (this.bb.dA()) {
            io.i(TAG, String.format("%s try get customer attribute in direct mode for %s", this.o.getPackageName(), jSONObject.optString("key")));
            ec ecVar = this.dr;
            Uri uri = dp;
            return (Bundle) ecVar.a(uri, a(uri, jSONObject2));
        }
        String str = TAG;
        String.format("%s try get customer attribute out of direct mode fo %s", this.o.getPackageName(), jSONObject.optString("key"));
        io.dm(str);
        ec ecVar2 = this.dr;
        Uri uri2 = f0do;
        return (Bundle) ecVar2.a(uri2, a(uri2, jSONObject2));
    }

    public static Bundle a(Cursor cursor, String str) {
        if (cursor != null && cursor.moveToFirst()) {
            String e = ic.e(cursor, "bundle_value");
            if (e == null) {
                io.dm(TAG);
                return null;
            }
            return iu.ds(e);
        }
        String str2 = TAG;
        String.format("No results found from central store: %s", str);
        io.dm(str2);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject a(String str, String str2, String str3, Bundle bundle, EnumSet<CustomerAttributeStore.GetAttributeOptions> enumSet) {
        Account o;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("command", str);
            jSONObject.put("directedId", str2);
            if (this.ds && (o = hu.o(this.o, str2)) != null) {
                jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_ACCOUNT_TYPE_ATTRIBUTE, o.type);
                jSONObject.put("accountName", o.name);
            }
            jSONObject.put("key", str3);
            jSONObject.put("bundleInfo", iu.O(bundle));
            if (enumSet != null) {
                jSONObject.put("getOptions", CustomerAttributeStore.GetAttributeOptions.serializeList(enumSet));
            }
            return jSONObject;
        } catch (JSONException e) {
            io.e(TAG, "Error creating Customer Attribute IPC Command", e);
            return null;
        }
    }

    static /* synthetic */ void a(bl blVar, Bundle bundle, int i, String str, MAPError mAPError, String str2) {
        if (bundle == null) {
            am.c(blVar, mAPError, str2, i, str);
        } else if (bundle.containsKey("error_code_key")) {
            blVar.onError(bundle);
        } else {
            blVar.onSuccess(bundle);
        }
    }
}
