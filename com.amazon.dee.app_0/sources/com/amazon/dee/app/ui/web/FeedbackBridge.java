package com.amazon.dee.app.ui.web;

import android.app.Activity;
import android.util.ArrayMap;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.logupload.LogRetriever;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.web.FeedbackBridge;
import com.amazon.dee.app.util.Utils;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.dee.app.http.CoralService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.IOException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
/* loaded from: classes12.dex */
public class FeedbackBridge extends JavaScriptBridge {
    private static final String BRIDGE_NAME = "FeedbackBridge";
    private static final String TAG = Utils.safeTag(FeedbackBridge.class.getSimpleName());
    private static final String USER_PERMISSIONS_ERROR_MESSAGE = "*ERROR: User did not or could not give permission to write logs to disk*";
    private Activity activity;
    private boolean allowedWritePermissions;
    private CoralService coralService;
    private LogRetriever logRetriever;
    private ArrayMap<String, JavaScriptBridgeMethod> methods;
    Runnable pendingRunnable;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class SendFeedbackMethod implements JavaScriptBridgeMethod {
        private SendFeedbackMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, final JavaScriptResponse javaScriptResponse) throws JSONException {
            final JsonObject jsonObject = (JsonObject) new JsonParser().parse(jSONObject.toString());
            FeedbackBridge feedbackBridge = FeedbackBridge.this;
            feedbackBridge.pendingRunnable = new Runnable() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$FeedbackBridge$SendFeedbackMethod$3D4SaVzzkFFBRpZlMPyLC8gnbQA
                @Override // java.lang.Runnable
                public final void run() {
                    FeedbackBridge.SendFeedbackMethod.this.lambda$execute$2$FeedbackBridge$SendFeedbackMethod(jsonObject, javaScriptResponse);
                }
            };
            if (ContextCompat.checkSelfPermission(feedbackBridge.activity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                FeedbackBridge.this.allowedWritePermissions = true;
                FeedbackBridge.this.pendingRunnable.run();
                return;
            }
            ActivityCompat.requestPermissions(FeedbackBridge.this.activity, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        }

        public /* synthetic */ void lambda$execute$2$FeedbackBridge$SendFeedbackMethod(JsonObject jsonObject, final JavaScriptResponse javaScriptResponse) {
            if (jsonObject.has("includeLogs") && jsonObject.get("includeLogs").getAsBoolean()) {
                try {
                    if (FeedbackBridge.this.allowedWritePermissions) {
                        jsonObject.addProperty("logs", FeedbackBridge.this.logRetriever.getLogsAsBase64String());
                    } else {
                        jsonObject.add("includeLogs", new JsonPrimitive(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE));
                        String asString = jsonObject.get("details").getAsString();
                        jsonObject.add("details", new JsonPrimitive(asString + "\n\n" + FeedbackBridge.USER_PERMISSIONS_ERROR_MESSAGE));
                        Log.e(FeedbackBridge.TAG, "Permission to read filesystem not granted by user, cannot fetch device logs.");
                    }
                } catch (IOException | IllegalThreadStateException | InterruptedException e) {
                    Log.e(FeedbackBridge.TAG, "Unable to attach log file to request.", e);
                }
            }
            FeedbackBridge.this.coralService.request("/api/beta-feedback").post(jsonObject).as(Void.class).toObservable().subscribeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$FeedbackBridge$SendFeedbackMethod$NlrGZG4Or7EbMntG-c-rz4akYUY
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    FeedbackBridge.SendFeedbackMethod.this.lambda$null$0$FeedbackBridge$SendFeedbackMethod(javaScriptResponse, (Void) obj);
                }
            }, new Action1() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$FeedbackBridge$SendFeedbackMethod$RpeKEe2edb8_d3FLumo4zlaraf0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    FeedbackBridge.SendFeedbackMethod.this.lambda$null$1$FeedbackBridge$SendFeedbackMethod(javaScriptResponse, (Throwable) obj);
                }
            });
        }

        public /* synthetic */ void lambda$null$0$FeedbackBridge$SendFeedbackMethod(JavaScriptResponse javaScriptResponse, Void r2) {
            FeedbackBridge.this.completeRequest(javaScriptResponse);
            String unused = FeedbackBridge.TAG;
        }

        public /* synthetic */ void lambda$null$1$FeedbackBridge$SendFeedbackMethod(JavaScriptResponse javaScriptResponse, Throwable th) {
            FeedbackBridge.this.errorResponse(javaScriptResponse, "Unable to POST feedback", th);
            Log.e(FeedbackBridge.TAG, "Unable to POST feedback", th);
        }
    }

    public FeedbackBridge(Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, CoralService coralService, LogRetriever logRetriever) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.coralService = coralService;
        this.logRetriever = logRetriever;
        this.activity = activity;
        this.allowedWritePermissions = false;
        this.methods = new ArrayMap<>();
        this.methods.put("sendFeedback", new SendFeedbackMethod());
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.methods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public String getJavaScriptInterfaceName() {
        return BRIDGE_NAME;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 1 || this.pendingRunnable == null) {
            return;
        }
        if (iArr.length > 0 && iArr[0] == 0) {
            this.allowedWritePermissions = true;
            Log.i(TAG, "User has granted permission to write logs to storage");
        } else {
            this.allowedWritePermissions = false;
            Log.w(TAG, "User did not grant permission to write to storage, can not attach feedback to logs");
        }
        this.pendingRunnable.run();
    }
}
