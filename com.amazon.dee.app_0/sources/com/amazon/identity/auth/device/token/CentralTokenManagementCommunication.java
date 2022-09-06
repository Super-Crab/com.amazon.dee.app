package com.amazon.identity.auth.device.token;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.bl;
import com.amazon.identity.auth.device.dn;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.framework.IPCCommand;
import com.amazon.identity.auth.device.gy;
import com.amazon.identity.auth.device.hh;
import com.amazon.identity.auth.device.hj;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.mq;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class CentralTokenManagementCommunication implements hh {
    private static final String TAG = "com.amazon.identity.auth.device.token.CentralTokenManagementCommunication";
    private final dn ba;
    private final ed o;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class GetCookiesCommand implements IPCCommand {
        public static final String KEY_DIRECTED_ID = "directed_id";
        public static final String KEY_DOMAIN = "domain";
        public static final String KEY_OPTIONS = "options_key";

        public static Bundle parametersToBundle(String str, String str2, Bundle bundle, ej ejVar) {
            Bundle outline12 = GeneratedOutlineSupport1.outline12("directed_id", str, "domain", str2);
            outline12.putBundle("options_key", bundle);
            ejVar.D(outline12);
            return outline12;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directed_id");
            String string2 = bundle.getString("domain");
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putInt("callingUid", bundle.getInt("callingUid", -1));
            ej d = ej.d(bundle, "TokenManagement:GetCookies");
            hj.ae(edVar).f(string, string2, bundle3, mq.a(d, callback), d);
            return null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class GetCookiesForActorCommand implements IPCCommand {
        public static final String KEY_ACCOUNT_ID = "account_id";
        public static final String KEY_ACTOR_ID = "actor_id";
        public static final String KEY_DOMAIN = "domain";
        public static final String KEY_OPTIONS = "options_key";

        public static Bundle parametersToBundle(String str, String str2, String str3, Bundle bundle, ej ejVar) {
            Bundle outline12 = GeneratedOutlineSupport1.outline12("account_id", str, "actor_id", str2);
            outline12.putString("domain", str3);
            outline12.putBundle("options_key", bundle);
            ejVar.D(outline12);
            return outline12;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("account_id");
            String string2 = bundle.getString("actor_id");
            String string3 = bundle.getString("domain");
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putInt("callingUid", bundle.getInt("callingUid", -1));
            ej d = ej.d(bundle, "TokenManagement:GetCookiesForActor");
            hj.ae(edVar).a(string, string2, string3, bundle3, mq.a(d, callback), d);
            return null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class GetTokenCommand implements IPCCommand {
        public static final String KEY_DIRECTED_ID = "directed_id";
        public static final String KEY_OPTIONS = "options_key";
        public static final String KEY_TOKEN = "token_key";

        public static Bundle parametersToBundle(String str, String str2, Bundle bundle) {
            Bundle outline12 = GeneratedOutlineSupport1.outline12("directed_id", str, KEY_TOKEN, str2);
            outline12.putBundle("options_key", bundle);
            return outline12;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directed_id");
            String string2 = bundle.getString(KEY_TOKEN);
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putInt("callingUid", bundle.getInt("callingUid", -1));
            ej d = ej.d(bundle, "TokenManagement:GetToken");
            hj.ae(edVar).e(string, string2, bundle3, mq.a(d, callback), d);
            return null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class GetTokenForActorCommand implements IPCCommand {
        public static final String KEY_ACCOUNT_ID = "account_id";
        public static final String KEY_ACTOR_ID = "actor_id";
        public static final String KEY_OPTIONS = "options_key";
        public static final String KEY_TOKEN_TYPE = "token_type";
        public static final String KEY_TOKEN_VALI_FAIL_CONTEXT = "failure_context";

        public static Bundle parametersToBundle(String str, String str2, String str3, String str4, Bundle bundle) {
            Bundle outline12 = GeneratedOutlineSupport1.outline12("account_id", str, "actor_id", str2);
            outline12.putString("token_type", str3);
            outline12.putString(KEY_TOKEN_VALI_FAIL_CONTEXT, str4);
            outline12.putBundle("options_key", bundle);
            return outline12;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("account_id");
            String string2 = bundle.getString("actor_id");
            String string3 = bundle.getString("token_type");
            String string4 = bundle.getString(KEY_TOKEN_VALI_FAIL_CONTEXT);
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putInt("callingUid", bundle.getInt("callingUid", -1));
            ej d = ej.d(bundle, "TokenManagement:GetTokenForActor");
            hj.ae(edVar).a(edVar, string, string2, string3, string4, bundle3, mq.a(d, callback), d);
            return null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class InvalidateCookiesCommand implements IPCCommand {
        public static final String KEY_DIRECTED_ID = "directed_id";
        public static final String KEY_DOMAIN = "domain";
        public static final String KEY_OPTIONS = "options_key";

        public static Bundle parametersToBundle(String str, String str2, Bundle bundle, ej ejVar) {
            Bundle outline12 = GeneratedOutlineSupport1.outline12("directed_id", str, "domain", str2);
            outline12.putBundle("options_key", bundle);
            ejVar.D(outline12);
            return outline12;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directed_id");
            String string2 = bundle.getString("domain");
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putInt("callingUid", bundle.getInt("callingUid", -1));
            ej d = ej.d(bundle, "TokenManagement:InvalidateCookies");
            hj.ae(edVar).g(string, string2, bundle3, mq.a(d, callback), d);
            return null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class UpdateTokenCommand implements IPCCommand {
        public static Bundle parametersToBundle(String str, String str2, Bundle bundle, gy gyVar, ej ejVar) {
            Bundle outline12 = GeneratedOutlineSupport1.outline12("key_account_id", str, "key_actor_id", str2);
            outline12.putBundle("key_option", bundle);
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("key_remote_listener", new RemoteInternalListenerWrapper(gyVar));
            outline12.putBundle("key_remote_listener_bundle", bundle2);
            ejVar.D(outline12);
            return outline12;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            Bundle bundle2 = bundle != null ? bundle : new Bundle();
            Bundle bundle3 = bundle2.getBundle("key_option") == null ? new Bundle() : bundle2.getBundle("key_option");
            bundle3.putInt("callingUid", bundle2.getInt("callingUid", -1));
            ej d = ej.d(bundle2, "TokenManagement:UpgradeToken");
            Bundle bundle4 = bundle.getBundle("key_remote_listener_bundle");
            RemoteInternalListenerWrapper remoteInternalListenerWrapper = bundle4 != null ? (RemoteInternalListenerWrapper) bundle4.getParcelable("key_remote_listener") : null;
            hj.ae(edVar).a(bundle2.getString("key_account_id"), bundle2.getString("key_actor_id"), bundle3, mq.a(d, callback, remoteInternalListenerWrapper), remoteInternalListenerWrapper, d);
            return null;
        }
    }

    public CentralTokenManagementCommunication(Context context) {
        this.o = ed.M(context);
        this.ba = new dn(this.o, "com.amazon.dcp.sso.ErrorCode", "com.amazon.dcp.sso.ErrorMessage", Integer.valueOf(MAPAccountManager.RegistrationError.REGISTER_FAILED.value()));
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> a(Context context, String str, String str2, String str3, String str4, Bundle bundle, Callback callback, ej ejVar) {
        io.dm(TAG);
        bl blVar = new bl(callback);
        this.ba.a(GetTokenForActorCommand.class, GetTokenForActorCommand.parametersToBundle(str, str2, str3, str4, bundle), blVar);
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> e(String str, String str2, Bundle bundle, Callback callback, ej ejVar) {
        bl blVar = new bl(callback);
        this.ba.a(GetTokenCommand.class, GetTokenCommand.parametersToBundle(str, str2, bundle), blVar);
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> f(String str, String str2, Bundle bundle, Callback callback, ej ejVar) {
        bl blVar = new bl(callback);
        this.ba.a(GetCookiesCommand.class, GetCookiesCommand.parametersToBundle(str, str2, bundle, ejVar), blVar);
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> g(String str, String str2, Bundle bundle, Callback callback, ej ejVar) {
        io.dm(TAG);
        bl blVar = new bl(callback);
        this.ba.a(InvalidateCookiesCommand.class, InvalidateCookiesCommand.parametersToBundle(str, str2, bundle, ejVar), blVar);
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> a(String str, String str2, String str3, Bundle bundle, Callback callback, ej ejVar) {
        io.dm(TAG);
        bl blVar = new bl(callback);
        this.ba.a(GetCookiesForActorCommand.class, GetCookiesForActorCommand.parametersToBundle(str, str2, str3, bundle, ejVar), blVar);
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, gy gyVar, ej ejVar) {
        bl blVar = new bl(callback);
        this.ba.a(UpdateTokenCommand.class, UpdateTokenCommand.parametersToBundle(str, str2, bundle, gyVar, ejVar), blVar);
        return blVar;
    }
}
