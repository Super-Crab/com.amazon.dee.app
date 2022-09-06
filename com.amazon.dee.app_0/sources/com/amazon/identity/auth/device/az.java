package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.android.amazonprofile.AmazonProfileManager;
import com.amazon.android.amazonprofile.Profile;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.api.ActorInfo;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPActorManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.fq;
import com.amazon.identity.auth.device.fr;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class az implements ba {
    private static az ff;
    private static final Map<String, Integer> fg;
    private final OAuthTokenManager B;
    private final ed fh;
    private final AmazonAccountManager s;
    private final gg w;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        public final String fr;
        public final String fs;
        public final String ft;

        public a(String str, String str2, String str3) {
            this.fr = str;
            this.ft = str3;
            this.fs = str2;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        fg = hashMap;
        hashMap.put(ActorInfo.ACTOR_TYPE_ADULT, 1);
        fg.put(ActorInfo.ACTOR_TYPE_CHILD, 2);
        fg.put(ActorInfo.ACTOR_TYPE_TEEN, 3);
    }

    private az(ed edVar) {
        this.fh = edVar;
        this.w = this.fh.dV();
        this.B = new OAuthTokenManager(this.fh);
        this.s = new AmazonAccountManager(this.w);
    }

    private String aD(String str) {
        return "actor_info_storage_".concat(String.valueOf(str));
    }

    private Bundle bc() {
        Bundle bundle = new Bundle();
        bundle.putInt("result_code", 1);
        return bundle;
    }

    public static void c(ed edVar) {
        ff = new az(edVar);
    }

    private String f(String str, String str2) {
        return "actor_info/" + str + "/" + str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public a g(String str, String str2) {
        return new a(this.w.k(str, str2, AccountConstants.KEY_ACTOR_SUBTYPE), this.w.k(str, str2, AccountConstants.KEY_ACTOR_ENTITYTYPE), this.w.k(str, str2, AccountConstants.KEY_ACTOR_CONVERTEDTYPE));
    }

    private Bundle h(String str, String str2) {
        Bundle bc = bc();
        bc.putString(str, str2);
        return bc;
    }

    @Override // com.amazon.identity.auth.device.ba
    public Bundle getActor(String str, Bundle bundle) {
        String string = bundle.getString("program");
        if (!a(str, string)) {
            return MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.BAD_REQUEST, "accountId/Program cannot be null", false);
        }
        if (!this.s.D(str)) {
            io.e("ActorManagerLogic", "Account no longer exist, returning null for actor info mapping.");
            return MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.AccountError.ACCOUNT_ALREADY_DEREGISTERED, "The account doesn't exist in MAP.", false);
        }
        try {
            Bundle bc = bc();
            gp l = gp.l(this.fh, aD(str));
            String cs = l.cs(f(string, "actor_id"));
            String cs2 = l.cs(f(string, "actor_type"));
            if (a(cs, cs2)) {
                bc.putString("actor_id", cs);
                bc.putString("actor_type", cs2);
                return bc;
            }
            io.e("ActorManagerLogic", "accountId/Program does not have associated actor id or actor type");
            return MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.BAD_REQUEST, "accountId/Program does not have associated actor id or actor type.", false);
        } catch (NullPointerException e) {
            io.e("ActorManagerLogic", "Exception happened when trying to get actor info for mapping.", e);
            return MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.BAD_REQUEST, "Fail to query database.", false);
        }
    }

    @Override // com.amazon.identity.auth.device.ba
    public Bundle getActorForMapping(String str, String str2) {
        if (!a(str, str2)) {
            return a(5, "accountId/actorMapping cannot be null", false);
        }
        if (!this.s.D(str)) {
            io.e("ActorManagerLogic", "Account no longer exist, returning null for actor mapping.");
            return a(2, "The account doesn't exist in MAP.", false);
        }
        try {
            return h("actor_id", this.w.E(str, str2));
        } catch (Exception e) {
            io.e("ActorManagerLogic", "Exception happened when trying to get actor for mapping.", e);
            return a(3, "Fail to query database.", true);
        }
    }

    @Override // com.amazon.identity.auth.device.ba
    public Bundle getCurrentActor(String str) {
        return getActorForMapping(str, "com.amazon.identity.auth.device.current.actor");
    }

    @Override // com.amazon.identity.auth.device.ba
    public Bundle removeActorMapping(String str, String str2) {
        if (!a(str, str2)) {
            return a(5, "accountId/actorMapping cannot be null", false);
        }
        if (!this.s.D(str)) {
            io.i("ActorManagerLogic", "The account doesn't exist in MAP. Remove actor mapping success.");
            return bc();
        }
        try {
            this.w.j(str, null, str2);
            return bc();
        } catch (Exception e) {
            io.e("ActorManagerLogic", "Exception happened when trying to remove actor mapping.", e);
            return a(3, "Fail to insert into database.", true);
        }
    }

    @Override // com.amazon.identity.auth.device.ba
    public Bundle removeCurrentActor(String str) {
        return removeActorMapping(str, "com.amazon.identity.auth.device.current.actor");
    }

    @Override // com.amazon.identity.auth.device.ba
    public Bundle setActorMapping(String str, String str2, String str3) {
        if (!a(str, str2, str3)) {
            return a(5, "accountId/actorId/actorMapping cannot be null", false);
        }
        if (!this.s.D(str)) {
            io.e("ActorManagerLogic", "The account doesn't exist in MAP.");
            return a(2, "The account doesn't exist in MAP.", false);
        }
        try {
            this.w.j(str, str2, str3);
            return bc();
        } catch (Exception e) {
            io.e("ActorManagerLogic", "Exception happened when trying to set actor mapping.", e);
            return a(3, "Fail to insert into database.", true);
        }
    }

    @Override // com.amazon.identity.auth.device.ba
    public Bundle setCurrentActor(String str, String str2) {
        return setActorMapping(str, str2, "com.amazon.identity.auth.device.current.actor");
    }

    public static synchronized az b(ed edVar) {
        az azVar;
        synchronized (az.class) {
            if (ff == null || jk.gR()) {
                c(edVar);
            }
            azVar = ff;
        }
        return azVar;
    }

    @Override // com.amazon.identity.auth.device.ba
    public MAPFuture<Bundle> a(@NonNull final Context context, @NonNull final String str, @NonNull final String str2, @NonNull final Bundle bundle, Callback callback, final ej ejVar) {
        io.i("ActorManagerLogic", "Signing up and enrolling actor with ui called");
        final bl blVar = new bl(callback);
        final String string = bundle.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT);
        if (!a(string)) {
            blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.BAD_REQUEST, "KEY_SIGN_IN_ENDPOINT in option bundle cannot be empty!", false));
            return blVar;
        }
        ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.az.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    fr.a c = fq.b.b(az.this.fh, str, str2, az.this.a("com.amazon.dcp.sso.token.oauth.amazon.access_token", str, (String) null, (ActorInfo) null, ejVar, (String) null), bundle, az.b(az.this, string, str, null, ejVar)).c(ejVar);
                    bb bbVar = new bb(az.this.B);
                    if (AuthEndpointErrorParser.a(c.nb)) {
                        bbVar.a(blVar, c);
                        io.e("ActorManagerLogic", "Fail to call Panda for signup and enroll actor");
                        return;
                    }
                    io.i("ActorManagerLogic", "Handling success response for signUpAndEnrollActor call. Proceed to webview.");
                    bbVar.a(context, str, blVar, bundle, c.na, ejVar);
                } catch (MAPCallbackErrorException e) {
                    io.e("ActorManagerLogic", String.format(Locale.ENGLISH, "Received an error when getting token or cookies before sending the signUpAndEnrollActorWithUI request. ErrorCode: %d ErrorMessage: %s ", Integer.valueOf(e.getError().getErrorCode()), e.getError().getErrorMessage()));
                    blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(e.getError(), e.getErrorMessage(), true));
                } catch (IOException e2) {
                    io.e("ActorManagerLogic", "Received an IOException when parsing the signUpAndEnrollActorWithUI response.");
                    blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.NETWORK_ERROR, e2.getMessage(), true));
                } catch (JSONException e3) {
                    io.e("ActorManagerLogic", "Received a JSONException when parsing the signUpAndEnrollActorWithUI response");
                    blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.PARSE_ERROR, e3.getMessage(), true));
                } catch (Exception e4) {
                    io.e("ActorManagerLogic", String.format(Locale.ENGLISH, "Received an exception before sending the signUpAndEnrollActorWithUI request. ErrorCode: %d ErrorMessage: %s ", Integer.valueOf(MAPError.CommonError.INTERNAL_ERROR.getErrorCode()), e4.getMessage()));
                    blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, e4.getMessage(), true));
                }
            }
        });
        return blVar;
    }

    static /* synthetic */ Bundle b(az azVar, String str, String str2, String str3, ej ejVar) throws MAPCallbackErrorException, ExecutionException, TimeoutException, InterruptedException {
        MAPFuture<Bundle> a2;
        Bundle outline13 = GeneratedOutlineSupport1.outline13(CookieKeys.Options.KEY_FORCE_REFRESH, true);
        if (TextUtils.isEmpty(str3)) {
            a2 = hj.ae(azVar.fh).f(str2, str, outline13, null, ejVar);
        } else {
            a2 = hj.ae(azVar.fh).a(str2, str3, str, outline13, (Callback) null, ejVar);
        }
        return a2.get(15L, TimeUnit.SECONDS);
    }

    @Override // com.amazon.identity.auth.device.ba
    public MAPFuture<Bundle> a(@NonNull final Context context, @NonNull final String str, @NonNull final String str2, @NonNull final String str3, @NonNull final Bundle bundle, Callback callback, final ej ejVar) {
        io.i("ActorManagerLogic", "Enrolling actor with ui called");
        final bl blVar = new bl(callback);
        final String string = bundle.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT);
        if (!a(string)) {
            blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.BAD_REQUEST, "KEY_SIGN_IN_ENDPOINT in option bundle cannot be empty!", false));
            return blVar;
        }
        ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.az.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    fr.a c = fq.a.a(az.this.fh, str, str3, az.this.a("com.amazon.dcp.sso.token.oauth.amazon.actor.access_token", str, str2, (ActorInfo) null, ejVar, (String) null), bundle, az.b(az.this, string, str, str2, ejVar)).c(ejVar);
                    bb bbVar = new bb(az.this.B);
                    if (AuthEndpointErrorParser.a(c.nb)) {
                        bbVar.a(blVar, c);
                        io.e("ActorManagerLogic", "Fail to call Panda for actor enroll call.");
                        return;
                    }
                    io.i("ActorManagerLogic", "Handling success response for enroll actor call. Proceed to webview.");
                    bbVar.a(context, str, str2, blVar, bundle, c.na, ejVar);
                } catch (MAPCallbackErrorException e) {
                    io.e("ActorManagerLogic", String.format(Locale.ENGLISH, "Received an error when getting actor token or cookies before sending the enroll actor request. ErrorCode: %d ErrorMessage: %s ", Integer.valueOf(e.getError().getErrorCode()), e.getError().getErrorMessage()));
                    blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(e.getError(), e.getErrorMessage(), true));
                } catch (IOException e2) {
                    io.e("ActorManagerLogic", "Received an IOException when parsing the enroll actor response.");
                    blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.NETWORK_ERROR, e2.getMessage(), true));
                } catch (JSONException e3) {
                    io.e("ActorManagerLogic", "Received a JSONException when parsing the enroll actor response");
                    blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.PARSE_ERROR, e3.getMessage(), true));
                } catch (Exception e4) {
                    io.e("ActorManagerLogic", String.format(Locale.ENGLISH, "Received an exception before sending the enroll actor request. ErrorCode: %d ErrorMessage: %s ", Integer.valueOf(MAPError.CommonError.INTERNAL_ERROR.getErrorCode()), e4.getMessage()));
                    blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, e4.getMessage(), true));
                }
            }
        });
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.ba
    public MAPFuture<Bundle> a(@NonNull final MAPActorManager.ActorSwitchMode actorSwitchMode, @NonNull final ActorInfo actorInfo, final String str, Bundle bundle, Callback callback, final ej ejVar) {
        boolean a2;
        io.i("ActorManagerLogic", "Switch actor is called.");
        final bl blVar = new bl(callback);
        if (actorSwitchMode != MAPActorManager.ActorSwitchMode.Force || (!TextUtils.isEmpty(actorInfo.getSuggestedActorType()) && fg.containsKey(actorInfo.getSuggestedActorType()))) {
            a2 = a(actorInfo.getAccountDirectedId(), actorInfo.getActorDirectedId(), actorInfo.getProgram());
        } else {
            io.e("ActorManagerLogic", "Null or invalid suggested actor type is passed in with ActorSwitchMode.Force");
            a2 = false;
        }
        if (!a2) {
            blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.BAD_REQUEST, "The information in ActorInfo is not correct.", false));
            return blVar;
        } else if (!this.s.D(actorInfo.getAccountDirectedId())) {
            io.e("ActorManagerLogic", "The account for switching doesn't exist in MAP.");
            blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.AccountError.ACCOUNT_ALREADY_DEREGISTERED, "The account for switching doesn't exist in MAP.", false));
            return blVar;
        } else if (mz.bb(this.fh)) {
            ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.az.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String a3 = az.a(az.this, actorInfo.getAccountDirectedId(), actorInfo.getActorDirectedId(), actorInfo, str, ejVar);
                        if (TextUtils.isEmpty(a3)) {
                            if (!MAPActorManager.ActorSwitchMode.Force.equals(actorSwitchMode)) {
                                io.e("ActorManagerLogic", "MAP is not able to get a valid actor type for the switch actor call");
                                blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Actor type is null or unknown!", true));
                                return;
                            }
                            a3 = actorInfo.getSuggestedActorType();
                        }
                        az.a(az.this, actorInfo, a3, blVar);
                    } catch (MAPCallbackErrorException e) {
                        io.w("ActorManagerLogic", "Received MAPCallbackErrorException for getActorType, checking detailed error...", e);
                        if (MAPError.CommonError.NETWORK_ERROR.getErrorCode() == e.getErrorBundle().getInt(MAPError.KEY_ERROR_CODE)) {
                            String str2 = az.this.g(actorInfo.getAccountDirectedId(), actorInfo.getActorDirectedId()).ft;
                            if (TextUtils.isEmpty(str2)) {
                                io.w("ActorManagerLogic", "No cached actor type. Fail the call if not in force switch mode.");
                                if (!MAPActorManager.ActorSwitchMode.Force.equals(actorSwitchMode)) {
                                    blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "No cached actor type, please retry", true));
                                    return;
                                }
                                str2 = actorInfo.getSuggestedActorType();
                            }
                            az.a(az.this, actorInfo, str2, blVar);
                            return;
                        }
                        blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.NETWORK_ERROR, e.getErrorMessage(), true));
                    } catch (TimeoutException unused) {
                        io.e("ActorManagerLogic", "Timeout waiting for actor token after 15s, please check your network status.");
                        blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Timeout fetching actor type, please retry", true));
                    } catch (Exception e2) {
                        io.e("ActorManagerLogic", "Received Exception for getActorType", e2);
                        blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Unable to fetch actor type, please retry", true));
                    }
                }
            });
            return blVar;
        } else if (!mz.bm(this.fh)) {
            blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.UNSUPPORTED_OPERATION, "AmazonProfileService 4.0 is not available on this device. Please contact device type owner to merge AmazonProfileService 4.0 to the device.", false));
            return blVar;
        } else {
            final String str2 = (bundle == null ? new Bundle() : bundle).getBoolean(MAPActorManager.KEY_DO_NOT_PASS_PACKAGE_NAME_TO_APS) ? null : str;
            ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.az.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String a3 = az.a(az.this, actorInfo.getAccountDirectedId(), actorInfo.getActorDirectedId(), actorInfo, str, ejVar);
                        if (TextUtils.isEmpty(a3)) {
                            if (!MAPActorManager.ActorSwitchMode.Force.equals(actorSwitchMode)) {
                                io.e("ActorManagerLogic", "MAP is not able to get a valid actor type for switch actor call. Received actor type: ".concat(String.valueOf(a3)));
                                mq.incrementCounterAndRecord("1P_SWITCH_ACTOR_FAILED:CANNOT_FETCH_ACTOR_TYPE_NORMAL", new String[0]);
                                blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Actor type is null or unknown!", true));
                                return;
                            }
                            a3 = actorInfo.getSuggestedActorType();
                        }
                        az.a(az.this, actorInfo, a3, str2, blVar);
                    } catch (MAPCallbackErrorException e) {
                        io.w("ActorManagerLogic", "Received MAPCallbackErrorException for getActorType, checking detailed error...", e);
                        Bundle errorBundle = e.getErrorBundle();
                        if (MAPError.CommonError.NETWORK_ERROR.getErrorCode() == errorBundle.getInt(MAPError.KEY_ERROR_CODE)) {
                            String str3 = az.this.g(actorInfo.getAccountDirectedId(), actorInfo.getActorDirectedId()).ft;
                            if (TextUtils.isEmpty(str3)) {
                                io.w("ActorManagerLogic", "No cached actor type. Fail the call if not in force switch mode.");
                                if (!MAPActorManager.ActorSwitchMode.Force.equals(actorSwitchMode)) {
                                    mq.incrementCounterAndRecord("1P_SWITCH_ACTOR_FAILED:CANNOT_FETCH_ACTOR_TYPE_AND_NO_CACHE", new String[0]);
                                    blVar.onError(e.getErrorBundle());
                                    return;
                                }
                                str3 = actorInfo.getSuggestedActorType();
                            }
                            az.a(az.this, actorInfo, str3, str2, blVar);
                            return;
                        }
                        blVar.onError(e.getErrorBundle());
                        mq.incrementCounterAndRecord("1P_SWITCH_ACTOR_FAILED:MAP_CALLBACK_ERROR_WITH_CODE:" + errorBundle.getInt(MAPError.KEY_ERROR_CODE), new String[0]);
                    } catch (TimeoutException unused) {
                        io.e("ActorManagerLogic", "Timeout waiting for actor token after 15s, please check your network status.");
                        blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Timeout fetching actor type, please retry", true));
                        mq.incrementCounterAndRecord("1P_SWITCH_ACTOR_FAILED:TIMEOUT_FETCHING_ACTOR_TOKEN", new String[0]);
                    } catch (Exception e2) {
                        io.e("ActorManagerLogic", "Received Exception for getActorType", e2);
                        blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Unable to fetch actor type, please retry", true));
                        mq.incrementCounterAndRecord("1P_SWITCH_ACTOR_FAILED:OTHER_EXCEPTION", new String[0]);
                    }
                }
            });
            return blVar;
        }
    }

    private boolean a(String... strArr) {
        for (String str : strArr) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    private Bundle a(int i, String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putInt("result_code", i);
        bundle.putString(MAPActorManager.KEY_ERROR_MESSAGE, str);
        bundle.putBoolean(MAPActorManager.KEY_RETRYABLE, z);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, String str2, String str3, ActorInfo actorInfo, ej ejVar, String str4) throws MAPCallbackErrorException, ExecutionException, TimeoutException, InterruptedException {
        MAPFuture<Bundle> a2;
        Bundle outline13 = GeneratedOutlineSupport1.outline13(TokenKeys.Options.KEY_FORCE_REFRESH_OAUTH, true);
        if (actorInfo != null) {
            outline13.putString("program", actorInfo.getProgram());
            outline13.putString("app_identifier", str4);
        }
        if (TextUtils.isEmpty(str3)) {
            a2 = hj.ae(this.fh).e(str2, str, outline13, null, ejVar);
        } else {
            a2 = hj.ae(this.fh).a(null, str2, str3, str, null, outline13, null, ejVar);
        }
        return a2.get(15L, TimeUnit.SECONDS).getString("value_key");
    }

    static /* synthetic */ String a(az azVar, String str, String str2, ActorInfo actorInfo, String str3, ej ejVar) throws MAPCallbackErrorException, ExecutionException, TimeoutException, InterruptedException {
        io.i("ActorManagerLogic", "Fetching actor type from server side.");
        azVar.a("com.amazon.dcp.sso.token.oauth.amazon.actor.access_token", str, str2, actorInfo, ejVar, str3);
        return azVar.g(str, str2).ft;
    }

    static /* synthetic */ void a(az azVar, ActorInfo actorInfo, String str, Callback callback) {
        String accountDirectedId = actorInfo.getAccountDirectedId();
        String program = actorInfo.getProgram();
        if (!fg.containsKey(str)) {
            io.e("ActorManagerLogic", "Unknown actor type: ".concat(String.valueOf(str)));
            callback.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Unknown actor type.", true));
        }
        try {
            gp l = gp.l(azVar.fh, azVar.aD(accountDirectedId));
            l.U(azVar.f(program, "actor_id"), actorInfo.getActorDirectedId());
            l.U(azVar.f(program, "actor_type"), str);
            callback.onSuccess(azVar.h(MAPActorManager.KEY_RESULT_ACTOR_TYPE, str));
        } catch (NullPointerException e) {
            io.e("ActorManagerLogic", "Exception happened when trying to set actor mapping.", e);
            callback.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Fail to insert into database, please retry", true));
        }
    }

    static /* synthetic */ void a(az azVar, ActorInfo actorInfo, String str, String str2, Callback callback) {
        if (!fg.containsKey(str)) {
            io.e("ActorManagerLogic", "Unknown actor type: ".concat(String.valueOf(str)));
            callback.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Unknown actor type.", true));
        }
        String.format("Passing profile to APS. Program: %s, ActorId: %s, ActorType: %s, PackageName: %s, AccountId: %s", actorInfo.getProgram(), actorInfo.getActorDirectedId(), str, str2, actorInfo.getAccountDirectedId());
        io.dm("ActorManagerLogic");
        int activeProfile = AmazonProfileManager.getAmazonProfileManager(azVar.fh).setActiveProfile(new Profile(actorInfo.getProgram(), actorInfo.getActorDirectedId(), fg.get(str).intValue(), str2, actorInfo.getAccountDirectedId()));
        if (activeProfile == AmazonProfileManager.SUCCESS) {
            io.i("ActorManagerLogic", "Switch actor success!");
            Bundle h = azVar.h(MAPActorManager.KEY_RESULT_ACTOR_TYPE, str);
            mq.incrementCounterAndRecord("SWITCH_ACTOR_SUCCESS", new String[0]);
            callback.onSuccess(h);
            return;
        }
        io.e("ActorManagerLogic", "Failed to switch actor from APS, APS error code: ".concat(String.valueOf(activeProfile)));
        mq.incrementCounterAndRecord("1P_SWITCH_ACTOR_FAILED:APS_FAILURE", new String[0]);
        callback.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Unable to switch actor type", true));
    }
}
