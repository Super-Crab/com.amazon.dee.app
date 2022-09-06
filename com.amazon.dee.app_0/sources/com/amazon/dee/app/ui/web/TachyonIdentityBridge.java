package com.amazon.dee.app.ui.web;

import android.text.TextUtils;
import android.util.ArrayMap;
import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileProvider;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class TachyonIdentityBridge extends JavaScriptBridge {
    private static final String TAG = Log.tag(TachyonIdentityBridge.class);
    CommsManager commsManager;
    Lazy<CommsServiceV2> commsServiceV2;
    ArrayMap<String, JavaScriptBridgeMethod> methods;

    /* loaded from: classes12.dex */
    class GetCurrentPersonIdMethod implements JavaScriptBridgeMethod {
        GetCurrentPersonIdMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            if (!TachyonIdentityBridge.this.commsServiceV2.mo358get().oobeService().hasSelectedProfile()) {
                javaScriptResponse.setErrorReason("No current person");
                javaScriptResponse.setError(true);
                String unused = TachyonIdentityBridge.TAG;
                TachyonIdentityBridge.this.completeRequest(javaScriptResponse);
                return;
            }
            String directedId = TachyonIdentityBridge.this.commsManager.getDirectedId();
            String firstName = TachyonIdentityBridge.this.commsManager.getFirstName();
            String lastName = TachyonIdentityBridge.this.commsManager.getLastName();
            if (directedId == null) {
                javaScriptResponse.setErrorReason("No current person");
                javaScriptResponse.setError(true);
                String unused2 = TachyonIdentityBridge.TAG;
                TachyonIdentityBridge.this.completeRequest(javaScriptResponse);
                return;
            }
            JSONObject put = new JSONObject().put(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID, directedId).put("firstName", firstName).put("lastName", lastName);
            String unused3 = TachyonIdentityBridge.TAG;
            StringBuilder outline116 = GeneratedOutlineSupport1.outline116("Returning the following for GetCurrentPersonId bridge call:\n directedId: ", directedId, "\n firstName: ", firstName, "\n lastName: ");
            outline116.append(lastName);
            outline116.toString();
            javaScriptResponse.setResponse(put);
            TachyonIdentityBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    class GetCurrentUserMethod implements JavaScriptBridgeMethod {
        GetCurrentUserMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            if (!TachyonIdentityBridge.this.commsServiceV2.mo358get().oobeService().hasSelectedProfile()) {
                javaScriptResponse.setErrorReason("No current user");
                javaScriptResponse.setError(true);
                TachyonIdentityBridge.this.completeRequest(javaScriptResponse);
                return;
            }
            String commsId = TachyonIdentityBridge.this.commsManager.getCommsId();
            String firstName = TachyonIdentityBridge.this.commsManager.getFirstName();
            String lastName = TachyonIdentityBridge.this.commsManager.getLastName();
            if (TextUtils.isEmpty(commsId)) {
                javaScriptResponse.setErrorReason("No current user");
                javaScriptResponse.setError(true);
                TachyonIdentityBridge.this.completeRequest(javaScriptResponse);
                return;
            }
            javaScriptResponse.setResponse(new JSONObject().put("id", commsId).put("firstName", firstName).put("lastName", lastName));
            TachyonIdentityBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public TachyonIdentityBridge(JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, CommsManager commsManager, Lazy<CommsServiceV2> lazy) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.commsManager = commsManager;
        this.commsServiceV2 = lazy;
        this.methods = new ArrayMap<>();
        this.methods.put("currentUser", new GetCurrentUserMethod());
        this.methods.put("currentPersonId", new GetCurrentPersonIdMethod());
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.methods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public String getJavaScriptInterfaceName() {
        return "TachyonIdentityBridge";
    }
}
