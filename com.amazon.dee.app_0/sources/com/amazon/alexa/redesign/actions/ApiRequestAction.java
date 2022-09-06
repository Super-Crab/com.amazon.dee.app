package com.amazon.alexa.redesign.actions;

import android.util.Log;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.client.HomeFeedServiceClient;
import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralServiceException;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class ApiRequestAction extends Action {
    private static final String TAG = "ApiRequestAction";
    private JSONObject body;
    private String endpoint;
    private HomeFeedServiceClient homeFeedServiceClient;
    private IdentityService identityService;
    private String method;

    public ApiRequestAction(String str, String str2, String str3, String str4, JSONObject jSONObject, HomeFeedServiceClient homeFeedServiceClient, IdentityService identityService) {
        super("ApiRequestAction", str, str2);
        this.homeFeedServiceClient = homeFeedServiceClient;
        this.endpoint = str3;
        this.method = str4;
        this.identityService = identityService;
        this.body = jSONObject;
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public void execute() {
        String directedId;
        try {
            HashMap hashMap = new HashMap();
            UserIdentity user = this.identityService.getUser("ApiRequestAction");
            if (user != null && (directedId = user.getDirectedId()) != null) {
                hashMap.put(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID, directedId);
            }
            Disposable disposable = (Disposable) this.homeFeedServiceClient.request(this.endpoint, hashMap, this.method, this.body).subscribeOn(Schedulers.io()).subscribeWith(new DisposableSingleObserver<JSONObject>() { // from class: com.amazon.alexa.redesign.actions.ApiRequestAction.1
                @Override // io.reactivex.rxjava3.core.SingleObserver
                public void onError(Throwable th) {
                    Log.e("ApiRequestAction", th.toString());
                }

                @Override // io.reactivex.rxjava3.core.SingleObserver
                public void onSuccess(JSONObject jSONObject) {
                }
            });
        } catch (Throwable th) {
            if (th.getCause() instanceof IOException) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IOException occurred during execution: \n");
                outline107.append(Log.getStackTraceString(th));
                Log.w("ApiRequestAction", outline107.toString());
            } else if (th.getCause() instanceof JSONException) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("JSONException occurred during execution: \n");
                outline1072.append(Log.getStackTraceString(th));
                Log.w("ApiRequestAction", outline1072.toString());
            } else if (th.getCause() instanceof CoralServiceException) {
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("CoralServiceException during execution: \n");
                outline1073.append(Log.getStackTraceString(th));
                Log.w("ApiRequestAction", outline1073.toString());
            }
        }
        HomeContract.Presenter presenter = this.presenter;
        if (presenter != null) {
            presenter.onCancelTimerAction(this.position);
        }
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public void execute(Map<String, Object> map) {
    }
}
