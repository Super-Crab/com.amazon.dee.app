package com.amazon.identity.auth.accounts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.identity.auth.device.ab;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.dn;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.framework.IPCCommand;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.je;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.y;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class MultipleAccountsCommunication implements y {
    private static final String TAG = "com.amazon.identity.auth.accounts.MultipleAccountsCommunication";
    private final dn ba;
    private final Context mContext;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class DoesAccountHaveMappingAction implements IPCCommand {
        public static final String KEY_ACCOUNT_MAPPING = "mapping";
        public static final String KEY_DIRECTED_ID = "directedId";
        public static final String KEY_VALUE = "value";

        public static boolean getResult(Bundle bundle) {
            return bundle.getBoolean("value");
        }

        public static Bundle parametersToBundle(String str, MultipleAccountManager.AccountMappingType accountMappingType) {
            Bundle outline11 = GeneratedOutlineSupport1.outline11("directedId", str);
            outline11.putString(KEY_ACCOUNT_MAPPING, MultipleAccountsCommunication.a(accountMappingType));
            return outline11;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directedId");
            MultipleAccountManager.AccountMappingType[] O = MultipleAccountsCommunication.O(bundle.getString(KEY_ACCOUNT_MAPPING));
            boolean z = false;
            if (O == null || O.length != 1) {
                io.e(MultipleAccountsCommunication.TAG, "Did not specify the mappings properly");
            } else {
                z = ab.g(edVar).doesAccountHaveMapping(string, O[0]);
            }
            return GeneratedOutlineSupport1.outline13("value", z);
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class GetAccountForMappingsUserAction implements IPCCommand {
        public static final String KEY_ACCOUNT_MAPPINGS = "mappings";
        public static final String KEY_VALUE = "value";

        public static String getResult(Bundle bundle) {
            return bundle.getString("value");
        }

        public static Bundle parametersToBundle(MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
            Bundle bundle = new Bundle();
            bundle.putString("mappings", MultipleAccountsCommunication.a(accountMappingTypeArr));
            return bundle;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            return GeneratedOutlineSupport1.outline11("value", ab.g(edVar).getAccountForMapping(MultipleAccountsCommunication.O(bundle.getString("mappings"))));
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class RemoveAccountMappingsUserAction implements IPCCommand {
        public static final String KEY_ACCOUNT_MAPPINGS = "mappings";
        public static final String KEY_DIRECTED_ID = "directedId";
        public static final String KEY_VALUE = "value";

        public static boolean getResult(Bundle bundle) {
            return bundle.getBoolean("value");
        }

        public static Bundle parametersToBundle(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
            Bundle outline11 = GeneratedOutlineSupport1.outline11("directedId", str);
            outline11.putString("mappings", MultipleAccountsCommunication.a(accountMappingTypeArr));
            return outline11;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            return GeneratedOutlineSupport1.outline13("value", ab.g(edVar).removeAccountMappings(bundle.getString("directedId"), MultipleAccountsCommunication.O(bundle.getString("mappings"))));
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class SetAccountMappingsUserAction implements IPCCommand {
        public static final String KEY_ACCOUNT_MAPPINGS = "mappings";
        public static final String KEY_DIRECTED_ID = "directedId";
        public static final String KEY_VALUE = "value";

        public static boolean getResult(Bundle bundle) {
            return bundle.getBoolean("value");
        }

        public static Bundle parametersToBundle(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
            Bundle outline11 = GeneratedOutlineSupport1.outline11("directedId", str);
            outline11.putString("mappings", MultipleAccountsCommunication.a(accountMappingTypeArr));
            return outline11;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            return GeneratedOutlineSupport1.outline13("value", ab.g(edVar).setAccountMappings(bundle.getString("directedId"), MultipleAccountsCommunication.O(bundle.getString("mappings"))));
        }
    }

    public MultipleAccountsCommunication(Context context) {
        this.mContext = ed.M(context);
        this.ba = new dn(this.mContext, "com.amazon.dcp.sso.ErrorCode", "com.amazon.dcp.sso.ErrorMessage", Integer.valueOf(MAPAccountManager.RegistrationError.REGISTER_FAILED.value()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MultipleAccountManager.AccountMappingType[] O(String str) {
        MultipleAccountManager.AccountMappingType primaryUserMappingType;
        MultipleAccountManager.AccountMappingType customKeyMappingType;
        if (str == null) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("mappings");
            MultipleAccountManager.AccountMappingType[] accountMappingTypeArr = new MultipleAccountManager.AccountMappingType[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("type");
                String string2 = jSONObject.getString("value");
                if (AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT.equals(string)) {
                    primaryUserMappingType = new MultipleAccountManager.SessionUserMappingType();
                } else if ("com.amazon.dcp.sso.property.account.extratokens.account_profiles".equals(string)) {
                    Integer dA = je.dA(string2);
                    if (dA != null) {
                        primaryUserMappingType = new MultipleAccountManager.ProfileMappingType(dA.intValue());
                    } else {
                        io.c(TAG, "%s is not a valid profile id", string2);
                        primaryUserMappingType = null;
                    }
                } else {
                    if ("com.amazon.dcp.sso.property.account.extratokens.account_packages".equals(string)) {
                        customKeyMappingType = new MultipleAccountManager.PackageMappingType(string2);
                    } else if ("com.amazon.dcp.sso.property.account.extratokens.custom_keys".equals(string)) {
                        customKeyMappingType = new MultipleAccountManager.CustomKeyMappingType(string2);
                    } else {
                        if ("primary_account_type".equals(string)) {
                            primaryUserMappingType = new MultipleAccountManager.PrimaryUserMappingType();
                        }
                        primaryUserMappingType = null;
                    }
                    primaryUserMappingType = customKeyMappingType;
                }
                if (primaryUserMappingType != null) {
                    accountMappingTypeArr[i] = primaryUserMappingType;
                }
            }
            return accountMappingTypeArr;
        } catch (JSONException e) {
            io.e(TAG, "Could not deserialize all mappings", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String a(MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (MultipleAccountManager.AccountMappingType accountMappingType : accountMappingTypeArr) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", accountMappingType.getAccountMappingType());
                jSONObject2.put("value", accountMappingType.getAccountMappingValue());
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("mappings", jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            io.e(TAG, "Could not seralize all mappings", e);
            return null;
        }
    }

    @Override // com.amazon.identity.auth.device.y
    public boolean doesAccountHaveMapping(String str, MultipleAccountManager.AccountMappingType accountMappingType) {
        return DoesAccountHaveMappingAction.getResult(this.ba.a(DoesAccountHaveMappingAction.class, DoesAccountHaveMappingAction.parametersToBundle(str, accountMappingType)));
    }

    @Override // com.amazon.identity.auth.device.y
    public String getAccountForMapping(MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        return GetAccountForMappingsUserAction.getResult(this.ba.a(GetAccountForMappingsUserAction.class, GetAccountForMappingsUserAction.parametersToBundle(accountMappingTypeArr)));
    }

    @Override // com.amazon.identity.auth.device.y
    public String getAccountMappingOwner(MultipleAccountManager.AccountMappingType accountMappingType) {
        io.e(TAG, "getAccountMappingOwner() currently is not supported on 1P devices");
        throw new IllegalArgumentException("getAccountMappingOwner() currently is not supported on 1P devices");
    }

    @Override // com.amazon.identity.auth.device.y
    public Intent getIntentToRemoveAccountMapping(MultipleAccountManager.AccountMappingType accountMappingType) {
        io.e(TAG, "getIntentToRemoveAccountMapping() currently is not supported on 1P devices");
        throw new IllegalArgumentException("getIntentToRemoveAccountMapping() currently is not supported on 1P devices");
    }

    @Override // com.amazon.identity.auth.device.y
    public boolean removeAccountMappings(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        return RemoveAccountMappingsUserAction.getResult(this.ba.a(RemoveAccountMappingsUserAction.class, RemoveAccountMappingsUserAction.parametersToBundle(str, accountMappingTypeArr)));
    }

    @Override // com.amazon.identity.auth.device.y
    public boolean setAccountMappings(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        return SetAccountMappingsUserAction.getResult(this.ba.a(SetAccountMappingsUserAction.class, SetAccountMappingsUserAction.parametersToBundle(str, accountMappingTypeArr)));
    }
}
