package com.amazon.deecomms.nativemodules;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsManager;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.sms.util.SendSMSObserver;
import com.amazon.deecomms.util.ReactBridgeUtils;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes12.dex */
public class SendSMSBridge extends ReactContextBaseJavaModule {
    public static final String IS_AUTHORIZED_FOR_CALLBACK = "isAuthorizedForCallback";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SendSMSBridge.class);
    public static final String RECIPIENTS = "recipients";
    private static final int REQUEST_CODE = 5235;
    public static final String SMS_BODY = "sms_body";
    public static final String SUCCESS_TYPES = "successTypes";
    private Promise promise;
    private final ReactApplicationContext reactContext;

    public SendSMSBridge(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.promise = null;
        this.reactContext = reactApplicationContext;
    }

    private Intent buildIntentForActivity(@NonNull ReadableMap readableMap, String str) {
        String string = readableMap.hasKey("body") ? readableMap.getString("body") : "";
        int i = Build.VERSION.SDK_INT;
        String defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(this.reactContext);
        Intent intent = new Intent("android.intent.action.SEND");
        if (defaultSmsPackage != null) {
            intent.setPackage(defaultSmsPackage);
        }
        intent.setType(Constants.TEXT_PLAIN_MEDIA_TYPE);
        intent.putExtra(SMS_BODY, string);
        intent.putExtra("android.intent.extra.TEXT", string);
        intent.putExtra("exit_on_sent", true);
        if (StringUtils.isNotEmpty(str)) {
            intent.putExtra("address", str);
        }
        ReadableMap readableMap2 = null;
        if (readableMap.hasKey("attachment")) {
            readableMap2 = readableMap.mo6945getMap("attachment");
        }
        if (readableMap2 != null) {
            intent.putExtra("android.intent.extra.STREAM", Uri.parse(readableMap2.getString("url")));
            intent.setType(readableMap2.getString("androidType"));
        }
        return intent;
    }

    private String getAddress(@NonNull ReadableMap readableMap) {
        ReadableArray array = readableMap.hasKey("recipients") ? readableMap.getArray("recipients") : null;
        if (array != null) {
            return ReactBridgeUtils.getDelimitedStringFromArray(array, "Samsung".equalsIgnoreCase(Build.MANUFACTURER) ? "," : ";");
        }
        return "";
    }

    private ReadableArray getSuccessTypes(ReadableMap readableMap) {
        if (readableMap.hasKey(SUCCESS_TYPES)) {
            return readableMap.getArray(SUCCESS_TYPES);
        }
        throw new IllegalStateException("Must provide successTypes. Read react-native-sms/README.md");
    }

    private boolean isAuthorizedForCallback(ReadableMap readableMap) {
        return readableMap.hasKey(IS_AUTHORIZED_FOR_CALLBACK) && readableMap.getBoolean(IS_AUTHORIZED_FOR_CALLBACK);
    }

    private void stopSMSObserver(SendSMSObserver sendSMSObserver) {
        if (sendSMSObserver != null) {
            LOG.i("Stopping the observer from bridge.");
            sendSMSObserver.stop();
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    @NonNull
    public String getName() {
        return "NativeMessaging";
    }

    @ReactMethod
    public void send(@NonNull ReadableMap readableMap, @NonNull Promise promise) {
        SendSMSObserver sendSMSObserver = null;
        try {
            this.promise = promise;
            if (SmsManager.getDefaultSmsSubscriptionId() == -1) {
                LOG.i("default SMS SIM subscription unavailable on device. returning error callback to JS.");
                sendErrorCallback("SIM subscription unavailable.");
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.SEND_SMS_FAILURE_INVALID_SUBSCRIPTION_ID);
                return;
            }
            String address = getAddress(readableMap);
            Intent buildIntentForActivity = buildIntentForActivity(readableMap, address);
            boolean isAuthorizedForCallback = isAuthorizedForCallback(readableMap);
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Is authorized for read based callback: ");
            sb.append(isAuthorizedForCallback);
            commsLogger.i(sb.toString());
            if (isAuthorizedForCallback) {
                SendSMSObserver sendSMSObserver2 = new SendSMSObserver(this.reactContext, this, getSuccessTypes(readableMap), address);
                try {
                    sendSMSObserver2.start();
                } catch (ActivityNotFoundException e) {
                    e = e;
                    sendSMSObserver = sendSMSObserver2;
                    sendErrorCallback("Unable to locate messaging app.");
                    MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.SMS_FAILURE_ACTIVITY_NOT_FOUND);
                    LOG.e("Exception while attempting to launch native message app.", e);
                    stopSMSObserver(sendSMSObserver);
                    return;
                } catch (Exception e2) {
                    e = e2;
                    sendSMSObserver = sendSMSObserver2;
                    sendErrorCallback("Failure in SMS bridge.");
                    LOG.e("Exception while attempting to launch native message app.", e);
                    stopSMSObserver(sendSMSObserver);
                    return;
                }
            }
            this.reactContext.startActivityForResult(buildIntentForActivity, REQUEST_CODE, buildIntentForActivity.getExtras());
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.SMS_MESSAGING_ACTIVITY_LAUNCHED);
            if (isAuthorizedForCallback) {
                return;
            }
            sendSuccessCallback();
        } catch (ActivityNotFoundException e3) {
            e = e3;
        } catch (Exception e4) {
            e = e4;
        }
    }

    public void sendErrorCallback(String str) {
        Promise promise = this.promise;
        if (promise != null) {
            promise.reject((String) null, str);
            this.promise = null;
        }
    }

    public void sendSuccessCallback() {
        Promise promise = this.promise;
        if (promise != null) {
            promise.resolve(null);
            this.promise = null;
        }
    }

    @ReactMethod
    public void updateMessagingController(Promise promise) {
        CommsDaggerWrapper.getComponent().getMessagingControllerManager().initializeMessagingController();
        promise.resolve(null);
    }
}
