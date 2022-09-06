package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.accounts.SessionUserChanger;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ab implements y {
    private static final String TAG = "com.amazon.identity.auth.device.ab";
    private static ab co;
    private final ds bb;
    private final AmazonAccountManager bg;
    private final ek cp;
    private final Map<List<MultipleAccountManager.AccountMappingType>, en<String>> cq = GeneratedOutlineSupport1.outline136();
    private boolean cr;
    private final ed o;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a {
        boolean S();

        boolean S(String str);

        List<e> T(String str);

        List<e> U(String str);

        List<e> V(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class b extends d {
        public b(ed edVar, MultipleAccountManager.AccountMappingType accountMappingType, AmazonAccountManager amazonAccountManager) {
            super(edVar, accountMappingType, amazonAccountManager);
        }

        public static List<b> a(ed edVar, AmazonAccountManager amazonAccountManager, String str) {
            Set<String> a = d.a(new BackwardsCompatiableDataStorage(edVar), str, "com.amazon.dcp.sso.property.account.extratokens.custom_keys");
            ArrayList arrayList = new ArrayList();
            for (String str2 : a) {
                arrayList.add(new b(edVar, new MultipleAccountManager.CustomKeyMappingType(str2), amazonAccountManager));
            }
            return arrayList;
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public boolean S() {
            return MultipleAccountManager.CustomKeyMappingType.isSupportedOnThisPlatform(this.o);
        }

        @Override // com.amazon.identity.auth.device.ab.d
        public List<e> T() {
            String accountMappingValue = this.cs.getAccountMappingValue();
            String concat = "com.amazon.identity.action.ACCOUNT_FOR_KEY.".concat(String.valueOf(accountMappingValue));
            Bundle outline11 = GeneratedOutlineSupport1.outline11(MAPAccountManager.KEY_EXTRA_KEY, accountMappingValue);
            return Arrays.asList(new e(concat, null, outline11), new e(MAPAccountManager.ACCOUNT_FOR_KEY_HAS_CHANGED_INTENT_ACTION, null, outline11));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class c implements a {
        private final AmazonAccountManager bg;
        private final Context mContext;

        public c(ed edVar, AmazonAccountManager amazonAccountManager) {
            this.mContext = edVar;
            this.bg = amazonAccountManager;
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public boolean S() {
            return MultipleAccountManager.PrimaryUserMappingType.isSupportedOnThisPlatform(this.mContext);
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public List<e> T(String str) {
            io.e(ab.TAG, "Primary user mapping cannot be changed");
            return new ArrayList();
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public List<e> U(String str) {
            io.e(ab.TAG, "Primary user mapping cannot be removed");
            return new ArrayList();
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public List<e> V(String str) {
            io.dm(ab.TAG);
            return new ArrayList();
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public boolean S(String str) {
            return this.bg.C(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static abstract class d implements a {
        private final AmazonAccountManager bg;
        protected final MultipleAccountManager.AccountMappingType cs;
        private final BackwardsCompatiableDataStorage ct;
        protected final ed o;

        public d(ed edVar, MultipleAccountManager.AccountMappingType accountMappingType, AmazonAccountManager amazonAccountManager) {
            this.o = edVar;
            this.bg = amazonAccountManager;
            this.ct = new BackwardsCompatiableDataStorage(this.o);
            this.cs = accountMappingType;
        }

        private boolean W(String str) {
            String accountMappingType = this.cs.getAccountMappingType();
            String accountMappingValue = this.cs.getAccountMappingValue();
            Set<String> a = a(this.ct, str, accountMappingType);
            String unused = ab.TAG;
            io.a("Current values of %s before remove are %s", accountMappingType, a.toString());
            if (!a.contains(accountMappingValue)) {
                io.b(ab.TAG, "Cannot remove %s for type %s from account", accountMappingValue, accountMappingType);
                return false;
            }
            a.remove(accountMappingValue);
            String unused2 = ab.TAG;
            io.a("Current values of %s after remove are %s", accountMappingType, a.toString());
            this.ct.a(str, accountMappingType, b(a));
            return true;
        }

        public static Set<String> a(gg ggVar, String str, String str2) {
            String b = ggVar.b(str, str2);
            HashSet hashSet = new HashSet();
            if (TextUtils.isEmpty(b)) {
                return hashSet;
            }
            hashSet.addAll(Arrays.asList(b.split(",")));
            return hashSet;
        }

        private String b(Set<String> set) {
            if (set == null) {
                return null;
            }
            return TextUtils.join(",", set);
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public boolean S(String str) {
            Set<String> a = a(this.ct, str, this.cs.getAccountMappingType());
            String str2 = ab.TAG;
            StringBuilder sb = new StringBuilder("Looking for ");
            sb.append(this.cs.getAccountMappingValue());
            sb.append(" in metadata values");
            io.dm(str2);
            for (String str3 : a) {
                GeneratedOutlineSupport1.outline161(str3, "Metadata found in list: ", ab.TAG);
            }
            return a.contains(this.cs.getAccountMappingValue());
        }

        protected abstract List<e> T();

        @Override // com.amazon.identity.auth.device.ab.a
        public List<e> T(String str) {
            Iterator<String> it2 = this.bg.getAccounts().iterator();
            boolean z = false;
            while (true) {
                boolean z2 = true;
                if (!it2.hasNext()) {
                    break;
                }
                String next = it2.next();
                if (next.equals(str)) {
                    String accountMappingType = this.cs.getAccountMappingType();
                    String accountMappingValue = this.cs.getAccountMappingValue();
                    Set<String> a = a(this.ct, next, accountMappingType);
                    String unused = ab.TAG;
                    io.a("Current values for type %s before add are %s", accountMappingType, a.toString());
                    if (a.contains(accountMappingValue)) {
                        io.b(ab.TAG, "Cannot create mapping of type with value %s to account", accountMappingType, accountMappingValue);
                        z2 = false;
                    } else {
                        a.add(accountMappingValue);
                        String unused2 = ab.TAG;
                        io.a("Current values for %s after add are %s", accountMappingType, a.toString());
                        this.ct.a(next, accountMappingType, b(a));
                    }
                    z |= z2;
                } else {
                    W(next);
                }
            }
            if (z) {
                io.a(ab.TAG, "Notifying of user change of type %s set. Account for profile %s changed.", this.cs.getAccountMappingType(), this.cs.getAccountMappingValue());
                return T();
            }
            io.a(ab.TAG, "Setting mapping type %s for key %s did not change. Not notifing.", this.cs.getAccountMappingType(), this.cs.getAccountMappingValue());
            return new ArrayList();
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public List<e> U(String str) {
            if (W(str)) {
                io.a(ab.TAG, "Notifying of user change of type %s removed. Account for profile %s changed.", this.cs.getAccountMappingType(), this.cs.getAccountMappingValue());
                return T();
            }
            io.a(ab.TAG, "Cannot remove mapping type %s for key %s did not change. Not notifing.", this.cs.getAccountMappingType(), this.cs.getAccountMappingValue());
            return new ArrayList();
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public List<e> V(String str) {
            return U(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class e {
        public final String action;
        public final Bundle extras;
        public final String packageName;

        public e(String str) {
            this(str, null, null);
        }

        public e(String str, String str2, Bundle bundle) {
            this.action = str;
            this.packageName = str2;
            this.extras = bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class f extends d {
        public f(ed edVar, MultipleAccountManager.AccountMappingType accountMappingType, AmazonAccountManager amazonAccountManager) {
            super(edVar, accountMappingType, amazonAccountManager);
        }

        public static List<f> b(ed edVar, AmazonAccountManager amazonAccountManager, String str) {
            Set<String> a = d.a(new BackwardsCompatiableDataStorage(edVar), str, "com.amazon.dcp.sso.property.account.extratokens.account_packages");
            ArrayList arrayList = new ArrayList();
            for (String str2 : a) {
                arrayList.add(new f(edVar, new MultipleAccountManager.PackageMappingType(str2), amazonAccountManager));
            }
            return arrayList;
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public boolean S() {
            return MultipleAccountManager.PackageMappingType.isSupportedOnThisPlatform(this.o);
        }

        @Override // com.amazon.identity.auth.device.ab.d
        public List<e> T() {
            String str = ab.TAG;
            String.format("%s PackageMappingLogic will notify other apps by sending action %s", this.o.getPackageName(), MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION);
            io.dm(str);
            return Arrays.asList(new e(MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION, this.cs.getAccountMappingValue(), null));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class g extends d {
        public g(ed edVar, MultipleAccountManager.AccountMappingType accountMappingType, AmazonAccountManager amazonAccountManager) {
            super(edVar, accountMappingType, amazonAccountManager);
        }

        public static List<g> c(ed edVar, AmazonAccountManager amazonAccountManager, String str) {
            Set<String> a = d.a(new BackwardsCompatiableDataStorage(edVar), str, "com.amazon.dcp.sso.property.account.extratokens.account_profiles");
            ArrayList arrayList = new ArrayList();
            for (String str2 : a) {
                Integer dA = je.dA(str2);
                if (dA == null) {
                    io.c(ab.TAG, "%s is not a valid profile id", str2);
                } else {
                    arrayList.add(new g(edVar, MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(dA.intValue()), amazonAccountManager));
                }
            }
            return arrayList;
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public boolean S() {
            return ((ds) this.o.getSystemService("sso_platform")).dr();
        }

        @Override // com.amazon.identity.auth.device.ab.d
        public List<e> T() {
            String str = ab.TAG;
            String.format("%s ProfilePrimaryMappingLogic will notify other apps by sending action %s", this.o.getPackageName(), MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION);
            io.dm(str);
            return Arrays.asList(new e(MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class h implements a {
        private final BackwardsCompatiableDataStorage ct;
        private final MultipleAccountManager.SessionPackageMappingType cu;
        private final AmazonAccountManager cv;
        private final ed o;

        public h(ed edVar, MultipleAccountManager.AccountMappingType accountMappingType, AmazonAccountManager amazonAccountManager) {
            this.o = edVar;
            if (accountMappingType instanceof MultipleAccountManager.SessionPackageMappingType) {
                this.cu = (MultipleAccountManager.SessionPackageMappingType) accountMappingType;
                this.ct = new BackwardsCompatiableDataStorage(edVar);
                this.cv = amazonAccountManager;
                return;
            }
            throw new IllegalArgumentException("SessionPackageMappingLogic only allows SessionPackageMappingType");
        }

        private JSONObject b(String str, boolean z) {
            String b = this.ct.b(str, this.cu.getAccountMappingType());
            if (b != null) {
                try {
                    JSONObject jSONObject = new JSONObject(b);
                    if (z) {
                        String string = jSONObject.getString(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER);
                        if (!TextUtils.equals(string, this.cu.getCallingPackage())) {
                            throw new MultipleAccountManager.SessionPackageMappingAlreadySetException(string);
                        }
                    }
                    return jSONObject;
                } catch (JSONException e) {
                    io.e(ab.TAG, "JSONException when trying to de-serialize the session package mapping json", e);
                    return null;
                }
            }
            return null;
        }

        private List<e> c(Set<String> set) {
            ArrayList arrayList = new ArrayList();
            for (String str : set) {
                String str2 = ab.TAG;
                String.format(Locale.US, "Going to notify package: %s about the account change:", str);
                io.dm(str2);
                arrayList.add(new e(MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION, str, null));
            }
            return arrayList;
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public boolean S(String str) {
            String b = this.ct.b(str, this.cu.getAccountMappingType());
            if (!TextUtils.isEmpty(b)) {
                try {
                    JSONArray jSONArray = new JSONObject(b).getJSONArray(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_PACKAGES);
                    if (jSONArray != null) {
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            if (TextUtils.equals(this.cu.getCallingPackage(), jSONArray.getString(i))) {
                                return true;
                            }
                        }
                    }
                } catch (JSONException e) {
                    io.e(ab.TAG, "JSONException when trying to de-serialize the session package mapping json", e);
                }
            }
            return false;
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public List<e> T(String str) {
            HashSet hashSet;
            Set<String> c;
            try {
                Set<String> accounts = this.cv.getAccounts();
                hashSet = new HashSet();
                if (!hz.isEmpty(accounts)) {
                    JSONObject V = V();
                    if (V != null) {
                        try {
                            String string = V.getString(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER);
                            if (!TextUtils.equals(this.o.getPackageName(), string)) {
                                throw new MultipleAccountManager.SessionPackageMappingAlreadySetException(string);
                            }
                        } catch (JSONException e) {
                            io.e(ab.TAG, "JSONException when trying to get session package mapping owner", e);
                        }
                    }
                    HashMap hashMap = new HashMap();
                    for (String str2 : accounts) {
                        hashMap.put(str2, b(str2, false));
                    }
                    for (String str3 : accounts) {
                        if (str3.equals(str)) {
                            c = a(str3, hashMap);
                        } else {
                            c = c(str3, false);
                        }
                        if (!hz.isEmpty(c)) {
                            hashSet.addAll(c);
                        }
                    }
                }
            } catch (JSONException e2) {
                io.e(ab.TAG, "JSONException happened when trying to parse the session package mapping json", e2);
                hashSet = null;
            }
            if (!hz.isEmpty(hashSet)) {
                io.a(ab.TAG, "Notifying of user change of type %s set. Account for profile %s changed.", this.cu.getAccountMappingType(), this.cu.getAccountMappingValue());
                return c(hashSet);
            }
            io.a(ab.TAG, "Setting mapping type %s with value %s did not change. Not notifing.", this.cu.getAccountMappingType(), this.cu.getAccountMappingValue());
            return new ArrayList();
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public List<e> U(String str) {
            return a(str, true);
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public List<e> V(String str) {
            return a(str, false);
        }

        String W() {
            JSONObject V = V();
            if (V != null) {
                try {
                    return V.getString(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER);
                } catch (JSONException e) {
                    io.e(ab.TAG, "JSONException happens when trying get owner of the session package mapping.", e);
                    return null;
                }
            }
            return null;
        }

        Intent X() {
            JSONObject V = V();
            if (V != null) {
                try {
                    String string = V.getString(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER);
                    String string2 = V.getString(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
                    if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                        return null;
                    }
                    Intent intent = new Intent();
                    intent.setAction("com.amazon.identity.auth.device.session_package_mapping.remove.action");
                    intent.setClassName(string, string2);
                    return intent;
                } catch (JSONException e) {
                    io.e(ab.TAG, "JSONException happens when trying get owner and remove activity of the session package mapping.", e);
                    return null;
                }
            }
            return null;
        }

        public List<e> a(String str, boolean z) {
            Set<String> set;
            try {
                set = c(str, z);
            } catch (JSONException e) {
                io.e(ab.TAG, "JSONException happened when trying to parse the session package mapping json", e);
                set = null;
            }
            if (!hz.isEmpty(set)) {
                io.a(ab.TAG, "Notifying of user change of type %s removed. Account for profile %s changed.", this.cu.getAccountMappingType(), this.cu.getAccountMappingValue());
                return c(set);
            }
            io.a(ab.TAG, "Cannot remove mapping type %s with value %s did not change. Not notifing.", this.cu.getAccountMappingType(), this.cu.getAccountMappingValue());
            return new ArrayList();
        }

        private JSONObject V() {
            Set<String> accounts = this.cv.getAccounts();
            if (!hz.isEmpty(accounts)) {
                for (String str : accounts) {
                    JSONObject b = b(str, false);
                    if (b != null) {
                        return b;
                    }
                }
                return null;
            }
            return null;
        }

        public List<e> U() {
            Set<String> accounts = this.cv.getAccounts();
            if (!hz.isEmpty(accounts)) {
                String accountMappingType = this.cu.getAccountMappingType();
                for (String str : accounts) {
                    if (this.ct.b(str, accountMappingType) != null) {
                        return a(str, false);
                    }
                }
            }
            return new ArrayList();
        }

        private Set<String> c(String str, boolean z) throws JSONException {
            JSONObject b = b(str, z);
            if (b == null) {
                GeneratedOutlineSupport1.outline161(str, "Session package mapping doesn't exist for account: ", ab.TAG);
                return null;
            }
            HashSet hashSet = new HashSet();
            JSONArray jSONArray = b.getJSONArray(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_PACKAGES);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                hashSet.add(jSONArray.getString(i));
            }
            this.ct.a(str, this.cu.getAccountMappingType(), (String) null);
            return hashSet;
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public boolean S() {
            return MultipleAccountManager.SessionPackageMappingType.isSupportedOnThisPlatform(this.o);
        }

        private Set<String> a(String str, Map<String, JSONObject> map) throws JSONException {
            HashSet hashSet;
            String accountMappingType = this.cu.getAccountMappingType();
            String accountMappingValue = this.cu.getAccountMappingValue();
            Set<String> sessionPackages = this.cu.getSessionPackages();
            JSONObject jSONObject = map.get(str);
            if (jSONObject == null) {
                io.i(ab.TAG, "Setting a new session package mapping.");
                GeneratedOutlineSupport1.outline161(str, "Setting a new session package mapping for account: ", ab.TAG);
                this.ct.a(str, accountMappingType, accountMappingValue);
                return sessionPackages;
            }
            HashSet hashSet2 = new HashSet();
            JSONArray jSONArray = jSONObject.getJSONArray(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_PACKAGES);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                hashSet2.add(jSONArray.getString(i));
            }
            if (sessionPackages == null ? false : sessionPackages.equals(hashSet2)) {
                io.i(ab.TAG, "No packages changes to the session package mapping.");
                this.ct.a(str, accountMappingType, accountMappingValue);
                return null;
            }
            this.ct.a(str, accountMappingType, accountMappingValue);
            String str2 = ab.TAG;
            String.format("Session package mapping changed. it changed from %s to %s", hashSet2.toString(), sessionPackages.toString());
            io.dm(str2);
            if (hz.isEmpty(sessionPackages)) {
                hashSet = hashSet2;
            } else if (hz.isEmpty(hashSet2)) {
                hashSet = sessionPackages;
            } else {
                HashSet hashSet3 = new HashSet(sessionPackages);
                hashSet3.removeAll(hashSet2);
                HashSet hashSet4 = new HashSet(hashSet2);
                hashSet4.removeAll(sessionPackages);
                hashSet3.addAll(hashSet4);
                hashSet = hashSet3;
            }
            String str3 = ab.TAG;
            String.format("The different packages compared with old session packages mapping: " + hashSet.toString(), new Object[0]);
            io.dm(str3);
            return hashSet;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class i implements a {
        private final AmazonAccountManager bg;
        private final SessionUserChanger cw;
        private final ed o;

        public i(ed edVar, AmazonAccountManager amazonAccountManager) {
            this.o = edVar;
            this.bg = amazonAccountManager;
            this.cw = new SessionUserChanger(amazonAccountManager);
        }

        private List<e> d(boolean z) {
            if (z) {
                String str = ab.TAG;
                String.format("%s creates changed notification and will send action %s", this.o.getPackageName(), MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION);
                io.dm(str);
                return Arrays.asList(new e(MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION));
            }
            return new ArrayList();
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public boolean S() {
            return MultipleAccountManager.SessionUserMappingType.isSupportedOnThisPlatform(this.o);
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public List<e> T(String str) {
            if (this.bg.B(str)) {
                io.w(ab.TAG, "Account is already a session user");
                return new ArrayList();
            }
            this.cw.d(hs.a(str));
            return d(this.bg.B(str));
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public List<e> U(String str) {
            if (!this.bg.B(str)) {
                io.w(ab.TAG, "Account is not a session user, so cannot remove");
                return new ArrayList();
            }
            io.dm(ab.TAG);
            return T(this.bg.o());
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public List<e> V(String str) {
            if (!this.bg.B(str)) {
                io.w(ab.TAG, "Account is not a session user, so cannot remove");
                return new ArrayList();
            }
            io.dm(ab.TAG);
            this.cw.ab(str);
            return d(!this.bg.B(str));
        }

        @Override // com.amazon.identity.auth.device.ab.a
        public boolean S(String str) {
            return this.bg.B(str);
        }
    }

    ab(Context context) {
        this.o = ed.M(context);
        this.bg = (AmazonAccountManager) this.o.getSystemService("dcp_amazon_account_man");
        this.bb = (ds) this.o.getSystemService("sso_platform");
        this.cp = new ek(this.o);
    }

    private void b(Intent intent, Set<String> set) {
        for (String str : set) {
            Intent intent2 = new Intent(intent);
            intent2.setPackage(str);
            this.o.sendBroadcast(intent2, AccountConstants.PERMISSION_AMAZON_ACCOUNT_CHANGED);
        }
    }

    private String c(MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        List<a> d2 = d(accountMappingTypeArr);
        Set<String> p = this.bg.p();
        for (a aVar : d2) {
            for (String str : p) {
                if (aVar.S(str)) {
                    return str;
                }
            }
        }
        io.e(TAG, "No account mapping found for any account, returning null");
        return null;
    }

    private List<a> d(MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        ArrayList arrayList = new ArrayList();
        if (accountMappingTypeArr == null) {
            return arrayList;
        }
        for (MultipleAccountManager.AccountMappingType accountMappingType : accountMappingTypeArr) {
            a a2 = a(accountMappingType);
            if (a2 != null) {
                if (!a2.S()) {
                    io.a("Mapping Type %s is not supported on this platform. Ignoring", accountMappingType.getAccountMappingType());
                } else {
                    arrayList.add(a2);
                }
            }
        }
        return arrayList;
    }

    public static synchronized ab g(Context context) {
        ab abVar;
        synchronized (ab.class) {
            if (co == null || jk.gR()) {
                generateNewInstance(context);
            }
            abVar = co;
        }
        return abVar;
    }

    public static void generateNewInstance(Context context) {
        co = new ab(context.getApplicationContext());
    }

    public void O() {
        if (MultipleAccountManager.SessionPackageMappingType.isSupportedOnThisPlatform(this.o)) {
            MultipleAccountManager.SessionPackageMappingType createSessionPackageMappingInstance = MultipleAccountManager.SessionPackageMappingType.createSessionPackageMappingInstance(this.o);
            String accountMappingOwner = getAccountMappingOwner(createSessionPackageMappingInstance);
            if (TextUtils.isEmpty(accountMappingOwner) || ek.g(this.o, accountMappingOwner)) {
                return;
            }
            String str = TAG;
            String.format(Locale.US, "Session package mapping owner is: %s, but it is already uninstalled from the device. Going to clear the session package mapping.", accountMappingOwner);
            io.dm(str);
            h hVar = new h(this.o, createSessionPackageMappingInstance, this.bg);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(hVar.U());
            a(arrayList);
        }
    }

    public void P() {
        this.cq.clear();
        io.dm(TAG);
    }

    public boolean Q(String str) {
        if (this.bg.D(str)) {
            return g.c(this.o, this.bg, str).size() != 0 || this.bg.C(str);
        }
        io.e(TAG, "The account does not exist so it cannot be a primary");
        return false;
    }

    public void R(String str) {
        R();
        if (!this.bg.D(str)) {
            io.e(TAG, "Cannot remove all account mappings since the account doesn't exist");
            return;
        }
        ArrayList<a> arrayList = new ArrayList();
        ed edVar = this.o;
        AmazonAccountManager amazonAccountManager = this.bg;
        ArrayList arrayList2 = new ArrayList();
        if (amazonAccountManager.B(str)) {
            arrayList2.add(new i(edVar, amazonAccountManager));
        }
        arrayList.addAll(arrayList2);
        arrayList.addAll(g.c(this.o, this.bg, str));
        arrayList.addAll(f.b(this.o, this.bg, str));
        arrayList.addAll(b.a(this.o, this.bg, str));
        ed edVar2 = this.o;
        AmazonAccountManager amazonAccountManager2 = this.bg;
        ArrayList arrayList3 = new ArrayList();
        if (amazonAccountManager2.B(str)) {
            arrayList3.add(new h(edVar2, MultipleAccountManager.SessionPackageMappingType.createSessionPackageMappingInstance(edVar2), amazonAccountManager2));
        }
        arrayList.addAll(arrayList3);
        P();
        ArrayList arrayList4 = new ArrayList();
        for (a aVar : arrayList) {
            arrayList4.addAll(aVar.V(str));
        }
        a(arrayList4);
    }

    public Set<Integer> a(ed edVar, String str) {
        Set<String> a2 = d.a(new BackwardsCompatiableDataStorage(edVar), str, "com.amazon.dcp.sso.property.account.extratokens.account_profiles");
        HashSet hashSet = new HashSet();
        for (String str2 : a2) {
            try {
                hashSet.add(Integer.valueOf(str2));
            } catch (NumberFormatException unused) {
                io.w(TAG, "Ignoring invalid profile id");
            }
        }
        return hashSet;
    }

    @Override // com.amazon.identity.auth.device.y
    public boolean doesAccountHaveMapping(String str, MultipleAccountManager.AccountMappingType accountMappingType) {
        a a2 = a(accountMappingType);
        if (!a2.S()) {
            io.c(TAG, "Mapping Type %s is not supported on this platform. Ignoring", accountMappingType.getAccountMappingType());
            return false;
        }
        return a2.S(str);
    }

    @Override // com.amazon.identity.auth.device.y
    public String getAccountForMapping(MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        if (!this.bb.dj()) {
            return c(accountMappingTypeArr);
        }
        List<MultipleAccountManager.AccountMappingType> asList = accountMappingTypeArr == null ? null : Arrays.asList(accountMappingTypeArr);
        en<String> enVar = this.cq.get(asList);
        if (enVar == null) {
            enVar = new en<>(c(accountMappingTypeArr));
            this.cq.put(asList, enVar);
        }
        return enVar.getValue();
    }

    @Override // com.amazon.identity.auth.device.y
    public String getAccountMappingOwner(MultipleAccountManager.AccountMappingType accountMappingType) {
        if (accountMappingType instanceof MultipleAccountManager.SessionPackageMappingType) {
            return new h(this.o, (MultipleAccountManager.SessionPackageMappingType) accountMappingType, this.bg).W();
        }
        throw new IllegalArgumentException("getAccountMappingOwner() currently only accepts SessionPackageMappingType");
    }

    @Override // com.amazon.identity.auth.device.y
    public Intent getIntentToRemoveAccountMapping(MultipleAccountManager.AccountMappingType accountMappingType) {
        if (accountMappingType instanceof MultipleAccountManager.SessionPackageMappingType) {
            return new h(this.o, (MultipleAccountManager.SessionPackageMappingType) accountMappingType, this.bg).X();
        }
        throw new IllegalArgumentException("getIntentToRemoveAccountMapping() currently only accepts SessionPackageMappingType");
    }

    @Override // com.amazon.identity.auth.device.y
    public boolean removeAccountMappings(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        R();
        List<a> d2 = d(accountMappingTypeArr);
        if (!this.bg.D(str)) {
            io.e(TAG, "Cannot remove account mappings since it doesn't exist");
            return false;
        }
        P();
        ArrayList arrayList = new ArrayList();
        for (a aVar : d2) {
            arrayList.addAll(aVar.U(str));
        }
        a(arrayList);
        return arrayList.size() > 0;
    }

    @Override // com.amazon.identity.auth.device.y
    public boolean setAccountMappings(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        R();
        List<a> d2 = d(accountMappingTypeArr);
        if (this.bg.D(str) && !this.bg.x(str)) {
            P();
            ArrayList arrayList = new ArrayList();
            for (a aVar : d2) {
                arrayList.addAll(aVar.T(str));
            }
            a(arrayList);
            return arrayList.size() > 0;
        }
        io.e(TAG, "Cannot set account mappings since it doesn't exist or is deregistering");
        return false;
    }

    private synchronized void Q() {
        if (this.cr) {
            return;
        }
        this.cr = true;
        if (this.bb.dr() && this.bb.dj()) {
            String o = this.bg.o();
            if (TextUtils.isEmpty(o)) {
                return;
            }
            g gVar = new g(this.o, MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(0), this.bg);
            for (String str : this.bg.getAccounts()) {
                if (gVar.S(str)) {
                    return;
                }
            }
            io.dm(TAG);
            gVar.T(o);
        }
    }

    private a b(MultipleAccountManager.AccountMappingType accountMappingType) {
        boolean dr = this.bb.dr();
        boolean c2 = dr ? false : c(accountMappingType);
        if (!dr && !c2) {
            return new c(this.o, this.bg);
        }
        if (c2) {
            mq.incrementCounterAndRecord("UsingUnsupportedProfile", new String[0]);
        }
        Q();
        return new g(this.o, accountMappingType, this.bg);
    }

    private void a(List<e> list) {
        if (list.size() == 0) {
            return;
        }
        ed edVar = this.o;
        fo.i(edVar, new MAPAccountManager(edVar).getAccount());
        Set<String> ee = this.cp.ee();
        HashMap hashMap = new HashMap();
        for (e eVar : list) {
            if (eVar.extras != null) {
                Intent intent = new Intent(eVar.action);
                intent.putExtras(eVar.extras);
                b(intent, a(eVar.packageName, ee));
            } else {
                Set set = (Set) hashMap.get(eVar.action);
                if (set == null) {
                    set = new HashSet();
                    hashMap.put(eVar.action, set);
                }
                set.addAll(a(eVar.packageName, ee));
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            b(new Intent((String) entry.getKey()), (Set) entry.getValue());
        }
    }

    private boolean c(MultipleAccountManager.AccountMappingType accountMappingType) {
        return !mz.bb(this.o) && !d(accountMappingType);
    }

    private boolean d(MultipleAccountManager.AccountMappingType accountMappingType) {
        return Integer.toString(0).equals(accountMappingType.getAccountMappingValue());
    }

    private void R() {
        if (!this.bb.dn() || this.bb.dj()) {
            return;
        }
        throw new IllegalStateException("getAccount write call cannot be called from this app on this platform");
    }

    private Set<String> a(String str, Set<String> set) {
        if (str == null) {
            return set;
        }
        HashSet hashSet = new HashSet();
        hashSet.add(str);
        return hashSet;
    }

    private a a(MultipleAccountManager.AccountMappingType accountMappingType) {
        if (accountMappingType == null) {
            io.e(TAG, "Account Mapping Type given was null. Ignoring");
            return null;
        }
        String accountMappingType2 = accountMappingType.getAccountMappingType();
        if (AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT.equals(accountMappingType2)) {
            return new i(this.o, this.bg);
        }
        if ("com.amazon.dcp.sso.property.account.extratokens.account_profiles".equals(accountMappingType2)) {
            return b(accountMappingType);
        }
        if ("com.amazon.dcp.sso.property.account.extratokens.account_packages".equals(accountMappingType2)) {
            return new f(this.o, accountMappingType, this.bg);
        }
        if ("com.amazon.dcp.sso.property.account.extratokens.custom_keys".equals(accountMappingType2)) {
            return new b(this.o, accountMappingType, this.bg);
        }
        if ("primary_account_type".equals(accountMappingType2)) {
            return new c(this.o, this.bg);
        }
        if ("com.amazon.dcp.sso.property.account.extratokens.account_session_packages".equals(accountMappingType2)) {
            return new h(this.o, accountMappingType, this.bg);
        }
        io.c(TAG, "Account mapping type %s was not recongized", accountMappingType2);
        return null;
    }
}
