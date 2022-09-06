package com.amazon.deecomms.nativemodules;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.util.List;
/* loaded from: classes12.dex */
public class CommsEventEmitterBridge extends ReactContextBaseJavaModule {
    private static final String AUDIO_PLAYER_DID_FINISH = "AudioFileDidFinishPlayingNotification";
    private static final String AUDIO_RECORDER_DID_FINISH = "AudioRecorderDidFinishRecording";
    static final String CALL_ENDED = "CallEndedNotification";
    private static final String CONVERSATION_DELETED = "ConversationDeleted";
    static final String DM_CALLING_CARD = "driveModeCallingCardClicked";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsEventEmitterBridge.class);
    private static final String MEDIA_DOWNLOADED = "CommsFileSharingDownloadFinishedNotification";
    private static final String MEDIA_UPLOADED = "CommsFileSharingUploadFinishedNotification";
    private static final String OOBE_COMPLETED_NOTIFICATION = "OOBECompletedNotification";
    private static final String WHITELIST_CONTACTS_NOTIFICATION = "WhitelistContactsNotification";
    private static ReactApplicationContext mApplicationContext;

    public CommsEventEmitterBridge(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        mApplicationContext = reactApplicationContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void notifyAudioFileDidFinishPlaying(@Nullable String str) {
        if (mApplicationContext != null) {
            CommsLogger commsLogger = LOG;
            commsLogger.d("Notifying audioFileDidFinishPlaying(%b)", commsLogger.sensitive(str));
            WritableMap createMap = Arguments.createMap();
            createMap.putString("localUri", str);
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) mApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(AUDIO_PLAYER_DID_FINISH, createMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void notifyAudioRecorderDidFinish(boolean z, @Nullable String str, boolean z2) {
        if (mApplicationContext != null) {
            LOG.d("Notifying audioRecorderDidFinishRecording(%b, %s, %b)", Boolean.valueOf(z), LOG.sensitive(str), Boolean.valueOf(z2));
            WritableMap createMap = Arguments.createMap();
            createMap.putBoolean("success", z);
            createMap.putString("filepath", str);
            createMap.putBoolean("wasMaxTimeValReached", z2);
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) mApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(AUDIO_RECORDER_DID_FINISH, createMap);
        }
    }

    public static void notifyConversationDeletion(@NonNull String str) {
        if (mApplicationContext != null) {
            LOG.d(".notifyConversationDeletion(%s)", str);
            WritableMap createMap = Arguments.createMap();
            createMap.putString("conversationId", str);
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) mApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(CONVERSATION_DELETED, createMap);
        }
    }

    public static void notifyDownloadComplete(@NonNull ReadableMap readableMap) {
        if (mApplicationContext != null) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Notifying media download complete");
            outline1.append(readableMap.toString());
            commsLogger.d(outline1.toString());
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) mApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(MEDIA_DOWNLOADED, readableMap);
        }
    }

    public static void notifyUploadComplete(@NonNull ReadableMap readableMap) {
        if (mApplicationContext != null) {
            LOG.d("Notifying media uploaded complete");
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) mApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(MEDIA_UPLOADED, readableMap);
        }
    }

    public static void sendCallEndedNotification() {
        if (mApplicationContext != null) {
            LOG.i("emitting CallEndedNotification event to react native");
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) mApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(CALL_ENDED, null);
            return;
        }
        LOG.e("cannot send CallEndedNotification event - application context is null");
    }

    public static void sendDriveModeCallingCardClickedEvent() {
        if (mApplicationContext != null) {
            LOG.i("emitting driveModeCallingCardClicked event to react native");
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) mApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(DM_CALLING_CARD, null);
        }
    }

    public static void sendOOBECompleteNotification() {
        if (mApplicationContext != null) {
            LOG.i("OOBEActivity completed; emitting OOBECompletedNotification event to react native");
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) mApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(OOBE_COMPLETED_NOTIFICATION, null);
        }
    }

    public static void sendStartWhitelistContactsNotification(@NonNull List<String> list) {
        if (mApplicationContext != null) {
            WritableArray createArray = Arguments.createArray();
            WritableMap createMap = Arguments.createMap();
            createMap.putString("childContactId", list.get(0));
            for (int i = 1; i < list.size(); i++) {
                createArray.pushString(list.get(i));
            }
            createMap.putArray("whitelistedContactIds", createArray);
            LOG.i("Emitting start whitelist contacts notification event to react native");
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) mApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(WHITELIST_CONTACTS_NOTIFICATION, createMap);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsEventEmitter";
    }
}
