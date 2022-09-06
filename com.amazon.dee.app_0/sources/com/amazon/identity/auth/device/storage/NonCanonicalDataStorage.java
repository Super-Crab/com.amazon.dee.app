package com.amazon.identity.auth.device.storage;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.as;
import com.amazon.identity.auth.device.at;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.f;
import com.amazon.identity.auth.device.framework.IPCCommand;
import com.amazon.identity.auth.device.fz;
import com.amazon.identity.auth.device.g;
import com.amazon.identity.auth.device.gg;
import com.amazon.identity.auth.device.gh;
import com.amazon.identity.auth.device.hx;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.iw;
import com.amazon.identity.auth.device.mz;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* compiled from: DCP */
@Deprecated
/* loaded from: classes12.dex */
public final class NonCanonicalDataStorage extends gg {
    private static final String TAG = "com.amazon.identity.auth.device.storage.NonCanonicalDataStorage";
    private final ed o;
    private final as oP;
    private final f oy;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class GetAccountsAction implements IPCCommand {
        public static final String KEY_VALUES = "values";

        public static Set<String> getResult(Bundle bundle) {
            String[] stringArray = bundle.getStringArray("values");
            HashSet hashSet = new HashSet();
            hashSet.addAll(Arrays.asList(stringArray));
            return hashSet;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            gg Z = NonCanonicalDataStorage.Z(edVar);
            Bundle bundle2 = new Bundle();
            bundle2.putStringArray("values", (String[]) Z.getAccounts().toArray(new String[0]));
            return bundle2;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class GetTokenAction implements IPCCommand {
        public static final String KEY_DIRECTED_ID = "directedId";
        public static final String KEY_KEY = "key";
        public static final String KEY_VALUE = "value";

        public static String getResult(Bundle bundle) {
            return bundle.getString("value");
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directedId");
            String string2 = bundle.getString("key");
            gg Z = NonCanonicalDataStorage.Z(edVar);
            Bundle bundle2 = new Bundle();
            bundle2.putString("value", Z.z(string, string2));
            return bundle2;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class GetUserDataAction implements IPCCommand {
        public static final String KEY_DIRECTED_ID = "directedId";
        public static final String KEY_KEY = "key";
        public static final String KEY_VALUE = "value";

        public static String getResult(Bundle bundle) {
            return bundle.getString("value");
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directedId");
            String string2 = bundle.getString("key");
            gg Z = NonCanonicalDataStorage.Z(edVar);
            Bundle bundle2 = new Bundle();
            bundle2.putString("value", Z.b(string, string2));
            return bundle2;
        }
    }

    public NonCanonicalDataStorage(Context context) {
        this.o = ed.M(context);
        this.oy = g.a(this.o);
        this.oP = at.i(this.o);
    }

    public static boolean Y(Context context) {
        return iw.d(context, DeviceAttribute.CentralAPK) && !hx.aj(context) && mz.be(context);
    }

    static /* synthetic */ gg Z(Context context) {
        gg dV = ((gh) ed.M(context).getSystemService("dcp_data_storage_factory")).dV();
        if (!(dV instanceof NonCanonicalDataStorage)) {
            return dV;
        }
        throw new IllegalStateException("Invalid datastorage");
    }

    private RuntimeException cG(String str) {
        io.e(TAG, str);
        return new IllegalStateException(str);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void B(String str, String str2) {
        throw cG("Cannot call write operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.gg
    public String C(String str, String str2) {
        throw cG("Cannot call device data operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.gg
    public void G(String str) {
        throw cG("Cannot call write operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.gg
    public boolean a(String str, fz fzVar, gg.a aVar) {
        throw cG("Cannot call write operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.gg
    public String b(String str, String str2) {
        if (!TextUtils.equals(str2, AccountConstants.KEY_ACCOUNT_UUID) && !TextUtils.equals(str2, AccountConstants.KEY_SECONDARY_AMAZON_ACCOUNT) && !TextUtils.equals(str2, AccountConstants.KEY_ACCOUNT_STATUS)) {
            throw cG("Cannot call getUserData on NonCanonicalDataStorage for: ".concat(String.valueOf(str2)));
        }
        return CustomerAttributeStore.getValueOrAttributeDefault(this.oP.peekAttribute(str, str2));
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> cc(String str) {
        throw cG("Cannot call getActors on NonCanonicalStorage.");
    }

    @Override // com.amazon.identity.auth.device.gg
    public Account ce(String str) {
        throw new UnsupportedOperationException("getAccountForDirectedId not supported on NonCanonicalDataStorage.");
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> cf(String str) {
        throw cG("Cannot get all token keys in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.gg
    public void eS() {
        throw cG("Cannot call sync dirty data on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> eT() {
        throw cG("getAccountNames not supported in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.gg
    public void f(String str, String str2, String str3) {
        throw cG("Cannot call write operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.gg
    public void g(String str, String str2, String str3) {
        throw cG("Cannot call device data operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> getAccounts() {
        return this.oy.getAccounts();
    }

    @Override // com.amazon.identity.auth.device.gg
    public String getDeviceSnapshot() {
        io.i(TAG, "getDeviceSnapshot API is only supported on 3P devices.");
        return "";
    }

    @Override // com.amazon.identity.auth.device.gg
    public void initialize() {
    }

    @Override // com.amazon.identity.auth.device.gg
    public void setup() {
    }

    @Override // com.amazon.identity.auth.device.gg
    public String z(String str, String str2) {
        throw cG("Cannot call getToken on NonCanonicalStorage. Please use MAP's API instead.");
    }

    @Override // com.amazon.identity.auth.device.gg
    public void a(String str, String str2, String str3) {
        throw cG("Cannot call write operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.gg
    public void a(fz fzVar) {
        throw cG("Cannot call write operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.gg
    public boolean a(String str, fz fzVar, gg.a aVar, List<String> list) {
        throw new UnsupportedOperationException("replaceAccounts not supported on NonCanonicalDataStorage.");
    }
}
