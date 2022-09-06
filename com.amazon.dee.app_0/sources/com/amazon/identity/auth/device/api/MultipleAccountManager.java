package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.accounts.MultipleAccountsCommunication;
import com.amazon.identity.auth.device.ab;
import com.amazon.identity.auth.device.da;
import com.amazon.identity.auth.device.ds;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.hz;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.mv;
import com.amazon.identity.auth.device.mz;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.y;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class MultipleAccountManager {
    private static final String TAG = "com.amazon.identity.auth.device.api.MultipleAccountManager";
    private final y gG;
    private final Context mContext;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static abstract class AccountMappingType {
        private final String gH;
        private final String mValue;

        AccountMappingType(String str, String str2) {
            this.gH = str;
            this.mValue = str2;
        }

        @FireOsSdk
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                AccountMappingType accountMappingType = (AccountMappingType) obj;
                if (TextUtils.equals(this.gH, accountMappingType.gH) && TextUtils.equals(this.mValue, accountMappingType.mValue)) {
                    return true;
                }
            }
            return false;
        }

        public String getAccountMappingType() {
            return this.gH;
        }

        public String getAccountMappingValue() {
            return this.mValue;
        }

        @FireOsSdk
        public int hashCode() {
            String str = this.gH;
            int i = 0;
            int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
            String str2 = this.mValue;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode + i;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class CustomKeyMappingType extends AccountMappingType {
        @FireOsSdk
        public CustomKeyMappingType(String str) {
            super("com.amazon.dcp.sso.property.account.extratokens.custom_keys", str);
        }

        public static boolean isSupportedOnThisPlatform(Context context) {
            return ((ds) ed.M(context).getSystemService("sso_platform")).dp();
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class PackageMappingType extends AccountMappingType {
        @FireOsSdk
        public PackageMappingType(String str) {
            super("com.amazon.dcp.sso.property.account.extratokens.account_packages", str);
        }

        @FireOsSdk
        public static PackageMappingType createCurrentPackageMapping(Context context) {
            return new PackageMappingType(context.getPackageName());
        }

        public static boolean isSupportedOnThisPlatform(Context context) {
            return ((ds) ed.M(context).getSystemService("sso_platform")).dp();
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class PrimaryUserMappingType extends AccountMappingType {
        /* synthetic */ PrimaryUserMappingType(int i, byte b) {
            this(i);
        }

        @FireOsSdk
        public static PrimaryUserMappingType createPrimaryMappingForProfile(int i) {
            return new PrimaryUserMappingType(i);
        }

        public static boolean isSupportedOnThisPlatform(Context context) {
            return true;
        }

        @FireOsSdk
        public PrimaryUserMappingType() {
            this(da.cz());
        }

        private PrimaryUserMappingType(int i) {
            super("com.amazon.dcp.sso.property.account.extratokens.account_profiles", Integer.toString(i));
        }
    }

    /* compiled from: DCP */
    @Deprecated
    /* loaded from: classes12.dex */
    public static class ProfileMappingType extends PrimaryUserMappingType {
        public ProfileMappingType(int i) {
            super(i, (byte) 0);
        }

        public static ProfileMappingType createCurrentProfile(Context context) {
            return new ProfileMappingType(0);
        }

        public static ProfileMappingType createCurrentViewableProfile(Context context) {
            return new ProfileMappingType(0);
        }

        public static boolean isSupportedOnThisPlatform(Context context) {
            io.e(MultipleAccountManager.TAG, "ProfileMappingType is deprecated. Please use PrimaryUserMappingType instead");
            return false;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class SessionPackageMappingAlreadySetException extends RuntimeException {
        private final String mOwner;

        @FireOsSdk
        public SessionPackageMappingAlreadySetException(String str) {
            this.mOwner = str;
        }

        @FireOsSdk
        public String getOwner() {
            return this.mOwner;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class SessionUserMappingType extends AccountMappingType {
        @FireOsSdk
        public SessionUserMappingType() {
            super(AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT, "true");
        }

        public static boolean isSupportedOnThisPlatform(Context context) {
            ds dsVar = (ds) ed.M(context).getSystemService("sso_platform");
            return dsVar.dp() || dsVar.bu(AccountConstants.INTENT_ACTION_SESSION_USER_CHANGE_SERVICE);
        }
    }

    @FireOsSdk
    public MultipleAccountManager(Context context) {
        y g;
        MAPInit.getInstance(context).initialize();
        this.mContext = ed.M(context);
        Context context2 = this.mContext;
        if (mz.aW(context2)) {
            g = new MultipleAccountsCommunication(context2);
        } else {
            g = ab.g(context2);
        }
        this.gG = g;
    }

    @FireOsSdk
    public boolean doesAccountHaveMapping(String str, AccountMappingType accountMappingType) {
        ej by = ej.by("MultipleAccountManager:doesAccountHaveMapping");
        mv f = by.f(this.mContext, "Time");
        try {
            return this.gG.doesAccountHaveMapping(str, accountMappingType);
        } finally {
            f.stop();
            by.eb();
        }
    }

    @FireOsSdk
    public String getAccountForMapping(AccountMappingType... accountMappingTypeArr) {
        return this.gG.getAccountForMapping(accountMappingTypeArr);
    }

    @FireOsSdk
    public String getAccountMappingOwner(AccountMappingType accountMappingType) {
        ej by = ej.by("MultipleAccountManager:getAccountMappingOwner");
        mv ea = by.ea();
        try {
            return this.gG.getAccountMappingOwner(accountMappingType);
        } finally {
            ea.stop();
            by.eb();
        }
    }

    @FireOsSdk
    public Intent getIntentToRemoveAccountMapping(AccountMappingType accountMappingType) {
        ej by = ej.by("MultipleAccountManager:getIntentToRemoveAccountMapping");
        mv ea = by.ea();
        try {
            return this.gG.getIntentToRemoveAccountMapping(accountMappingType);
        } finally {
            ea.stop();
            by.eb();
        }
    }

    @FireOsSdk
    public boolean removeAccountMappings(String str, AccountMappingType... accountMappingTypeArr) {
        ej by = ej.by("MultipleAccountManager:removeAccountMappings");
        mv ea = by.ea();
        try {
            return this.gG.removeAccountMappings(str, accountMappingTypeArr);
        } finally {
            ea.stop();
            by.eb();
        }
    }

    @FireOsSdk
    public boolean setAccountMappings(String str, AccountMappingType... accountMappingTypeArr) {
        ej by = ej.by("MultipleAccountManager:setAccountMappings");
        mv ea = by.ea();
        try {
            return this.gG.setAccountMappings(str, accountMappingTypeArr);
        } finally {
            ea.stop();
            by.eb();
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class SessionPackageMappingType extends AccountMappingType {
        public static final String JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER = "owner";
        public static final String JSON_KEY_SESSION_PACKAGE_MAPPING_PACKAGES = "packages";
        public static final String JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME = "activity";
        private final String gI;
        private final Set<String> gJ;
        private final String gK;

        SessionPackageMappingType(Context context, Set<String> set, String str) {
            super("com.amazon.dcp.sso.property.account.extratokens.account_session_packages", a(context, set, str));
            this.gJ = set;
            this.gI = context.getPackageName();
            this.gK = str;
        }

        private static String a(Context context, Set<String> set, String str) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, context.getPackageName());
                jSONObject.put(JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, str);
                JSONArray jSONArray = new JSONArray();
                for (String str2 : set) {
                    jSONArray.put(str2);
                }
                jSONObject.put(JSON_KEY_SESSION_PACKAGE_MAPPING_PACKAGES, jSONArray);
            } catch (JSONException e) {
                io.e(MultipleAccountManager.TAG, "Unable to create session package mapping json.", e);
            }
            return jSONObject.toString();
        }

        @FireOsSdk
        public static SessionPackageMappingType createNewSessionPackageMapping(Context context, Set<String> set, String str) {
            if (context != null && !hz.isEmpty(set) && !TextUtils.isEmpty(str)) {
                return new SessionPackageMappingType(context, set, str);
            }
            throw new IllegalArgumentException("You cannot pass empty or null parameters when construct a new session package mapping.");
        }

        @FireOsSdk
        public static SessionPackageMappingType createSessionPackageMappingInstance(Context context) {
            if (context != null) {
                return new SessionPackageMappingType(context.getPackageName());
            }
            throw new IllegalArgumentException("You cannot pass null context when construct session package mapping.");
        }

        @FireOsSdk
        public static boolean isSupportedOnThisPlatform(Context context) {
            return ((ds) ed.M(context).getSystemService("sso_platform")).ds();
        }

        @FireOsSdk
        public String getCallingPackage() {
            return this.gI;
        }

        @FireOsSdk
        public String getRemoveActivityClassName() {
            return this.gK;
        }

        @FireOsSdk
        public Set<String> getSessionPackages() {
            return this.gJ;
        }

        private SessionPackageMappingType(String str) {
            super("com.amazon.dcp.sso.property.account.extratokens.account_session_packages", null);
            this.gJ = null;
            this.gI = str;
            this.gK = null;
        }
    }
}
