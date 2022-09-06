package com.amazon.alexa.drive.entertainment.interactor;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.api.AlexaServicesApis;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.entertainment.model.MediaErrorPayload;
import com.amazon.alexa.drive.entertainment.model.TransportControlCommand;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class NowPlayingScreenInteractor implements NowPlayingScreenContract.Interactor {
    private static final String TAG = "NowPlayingScreenInteractor";
    private AlexaServicesConnection mAlexaServicesConnection;
    private JSONObject mCurrentPlayerInfoModel;
    private DeviceInformation mDeviceInformation;
    private String mDeviceType;
    private String mDsn;
    private EntertainmentAsyncTaskFactory mEntertainmentAsyncTaskFactory;
    private EventBus mEventBus;
    private boolean mIsMediaReferenceIdUpdated;
    private String mMediaReferenceId;
    private Lazy<NowPlayingScreenContract.Presenter> mNowPlayingScreenPresenter;
    private NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener mOnNowPlayingScreenInfoAsyncTaskListener;
    private List<NowPlayingScreenContract.Interactor.OnUpdateNowPlayingPlaybackStateListener> mOnUpdateNowPlayingPlaybackStateListenerList;
    private List<NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener> mOnUpdateNowPlayingScreenListenerList;
    private NowPlayingScreenContract.Interactor.PlayerCommandAsyncTaskStatusListener mPlayerCommandAsyncTaskStatusListener;
    private SimpleMultiFilterSubscriber mTCommEventSubscriber;

    /* renamed from: com.amazon.alexa.drive.entertainment.interactor.NowPlayingScreenInteractor$3  reason: invalid class name */
    /* loaded from: classes7.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState = new int[AlexaPlayerInfoState.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$drive$entertainment$model$TransportControlCommand;

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState[AlexaPlayerInfoState.PLAYING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaPlayerInfoState[AlexaPlayerInfoState.BUFFERING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$amazon$alexa$drive$entertainment$model$TransportControlCommand = new int[TransportControlCommand.values().length];
            try {
                $SwitchMap$com$amazon$alexa$drive$entertainment$model$TransportControlCommand[TransportControlCommand.PLAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drive$entertainment$model$TransportControlCommand[TransportControlCommand.PAUSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drive$entertainment$model$TransportControlCommand[TransportControlCommand.PREV.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drive$entertainment$model$TransportControlCommand[TransportControlCommand.NEXT.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drive$entertainment$model$TransportControlCommand[TransportControlCommand.JUMP_BACK_30.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drive$entertainment$model$TransportControlCommand[TransportControlCommand.JUMP_FORWARD_30.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drive$entertainment$model$TransportControlCommand[TransportControlCommand.REPEAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drive$entertainment$model$TransportControlCommand[TransportControlCommand.SHUFFLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drive$entertainment$model$TransportControlCommand[TransportControlCommand.LIKE.ordinal()] = 9;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drive$entertainment$model$TransportControlCommand[TransportControlCommand.DISLIKE.ordinal()] = 10;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public NowPlayingScreenInteractor(Lazy<NowPlayingScreenContract.Presenter> lazy, AlexaServicesConnection alexaServicesConnection, EntertainmentAsyncTaskFactory entertainmentAsyncTaskFactory) {
        Log.i(TAG, "NowPlayingScreenInteractor Constructor");
        this.mNowPlayingScreenPresenter = lazy;
        this.mAlexaServicesConnection = alexaServicesConnection;
        this.mEntertainmentAsyncTaskFactory = entertainmentAsyncTaskFactory;
        initialize();
    }

    private void initAlexaServicesConnection() {
        Log.i(TAG, "initAlexaServicesConnection");
        this.mAlexaServicesConnection.connect();
    }

    private void sendPrimaryPlayerCommandEvent(String str) {
        String str2 = TAG;
        Log.i(str2, "sendPrimaryPlayerCommandEvent " + str);
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(EntertainmentConstants.API_PLAYER_COMMAND);
            sb.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
            sb.append("deviceSerialNumber");
            sb.append(Config.Compare.EQUAL_TO);
            sb.append(getDSN());
            sb.append(";");
            sb.append("deviceType");
            sb.append(Config.Compare.EQUAL_TO);
            sb.append(getDeviceType());
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", str);
            getEntertainmentAsyncTaskFactory().createPlayerCommandAsyncTask(getPlayerCommandAsyncTaskStatusListener()).execute(sb.toString(), jSONObject.toString());
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Exception when sending primary player command ", e, TAG);
        }
    }

    private void sendSecondaryPlayerCommandEvent(String str, boolean z) {
        Log.i(TAG, "sendSecondaryPlayerCommandEvent " + str + " enabled  " + z);
        StringBuilder sb = new StringBuilder();
        JSONObject jSONObject = new JSONObject();
        char c = 65535;
        try {
            switch (str.hashCode()) {
                case -806681328:
                    if (str.equals(EntertainmentConstants.PLAYER_COMMAND_REPEAT)) {
                        c = 0;
                        break;
                    }
                    break;
                case -631315834:
                    if (str.equals(EntertainmentConstants.PLAYER_COMMAND_DISLIKE)) {
                        c = 3;
                        break;
                    }
                    break;
                case -162265196:
                    if (str.equals(EntertainmentConstants.PLAYER_COMMAND_LIKE)) {
                        c = 2;
                        break;
                    }
                    break;
                case 1900667442:
                    if (str.equals(EntertainmentConstants.PLAYER_COMMAND_SHUFFLE)) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c != 0 && c != 1) {
                if (c != 2 && c != 3) {
                    return;
                }
                if (str.equals(EntertainmentConstants.PLAYER_COMMAND_LIKE)) {
                    sb.append(EntertainmentConstants.API_PLAYER_COMMAND_LIKE);
                } else {
                    sb.append(EntertainmentConstants.API_PLAYER_COMMAND_DISLIKE);
                }
                jSONObject.put("deviceSerialNumber", getDSN());
                jSONObject.put("deviceType", getDeviceType());
                jSONObject.put(EntertainmentConstants.PLAYER_COMMAND_ATTR_REFERENCE_ID, getCurrentMediaReferenceId());
                jSONObject.put("mediaOwnerCustomerId", getMediaOwnerCustomerId());
                jSONObject.put(EntertainmentConstants.PLAYER_COMMAND_GO_TO_NEXT_ATTR, !str.equals(EntertainmentConstants.PLAYER_COMMAND_LIKE));
                getEntertainmentAsyncTaskFactory().createPlayerCommandAsyncTask(getPlayerCommandAsyncTaskStatusListener()).execute(sb.toString(), jSONObject.toString());
                return;
            }
            sb.append(EntertainmentConstants.API_PLAYER_COMMAND);
            sb.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
            sb.append("deviceSerialNumber");
            sb.append(Config.Compare.EQUAL_TO);
            sb.append(getDSN());
            sb.append(";");
            sb.append("deviceType");
            sb.append(Config.Compare.EQUAL_TO);
            sb.append(getDeviceType());
            jSONObject.put("type", str);
            if (str.equals(EntertainmentConstants.PLAYER_COMMAND_SHUFFLE)) {
                jSONObject.put("shuffle", z);
            } else {
                jSONObject.put("repeat", z);
            }
            getEntertainmentAsyncTaskFactory().createPlayerCommandAsyncTask(getPlayerCommandAsyncTaskStatusListener()).execute(sb.toString(), jSONObject.toString());
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Exception when sending secondary player command ", e, TAG);
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor
    public void addOnUpdateNowPlayingPlaybackStateListener(NowPlayingScreenContract.Interactor.OnUpdateNowPlayingPlaybackStateListener onUpdateNowPlayingPlaybackStateListener) {
        String str = TAG;
        Log.i(str, "addOnUpdateNowPlayingPlaybackStateListener " + onUpdateNowPlayingPlaybackStateListener);
        getOnUpdateNowPlayingPlaybackStateListenerList().add(onUpdateNowPlayingPlaybackStateListener);
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor
    public void addOnUpdateNowPlayingScreenListener(NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener onUpdateNowPlayingScreenListener) {
        String str = TAG;
        Log.i(str, "addOnUpdateNowPlayingScreenListener " + onUpdateNowPlayingScreenListener);
        getOnUpdateNowPlayingScreenListenerList().add(onUpdateNowPlayingScreenListener);
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor
    public void destroy() {
        Log.i(TAG, "destroy");
        if (getAlexaServicesConnection() != null) {
            this.mAlexaServicesConnection.disconnect();
        }
        if (getTCommEventSubscriber() != null) {
            this.mEventBus.unsubscribe(getTCommEventSubscriber());
        }
    }

    @VisibleForTesting
    AlexaServicesConnection getAlexaServicesConnection() {
        return this.mAlexaServicesConnection;
    }

    @VisibleForTesting
    String getCurrentMediaReferenceId() {
        JSONObject currentPlayerInfoModel;
        String mediaReferenceId = getMediaReferenceId();
        GeneratedOutlineSupport1.outline163("getMediaReferenceId ", mediaReferenceId, TAG);
        if ((mediaReferenceId == null || mediaReferenceId.isEmpty()) && (currentPlayerInfoModel = getCurrentPlayerInfoModel()) != null) {
            Log.i(TAG, "media id is empty, parsing player info ");
            mediaReferenceId = currentPlayerInfoModel.optString("mediaId", "");
            this.mMediaReferenceId = mediaReferenceId;
        }
        GeneratedOutlineSupport1.outline163("Return media reference Id ", mediaReferenceId, TAG);
        return mediaReferenceId;
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor
    public JSONObject getCurrentPlayerInfo() {
        Log.i(TAG, "getCurrentPlayerInfo");
        if (isMediaReferenceIdUpdated() || getCurrentPlayerInfoModel() == null) {
            return null;
        }
        return getCurrentPlayerInfoModel();
    }

    @VisibleForTesting
    JSONObject getCurrentPlayerInfoModel() {
        return this.mCurrentPlayerInfoModel;
    }

    @VisibleForTesting
    String getDSN() {
        return this.mDsn;
    }

    @VisibleForTesting
    JSONObject getDeserializedTCommPayloadJSON(String str) {
        try {
            String replace = new JSONObject(str).getString("payload").replaceAll(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_SERIALIZED, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED).replace(EntertainmentConstants.TCOMM_PAYLOAD_SERIALIZED_OPEN, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN).replace(EntertainmentConstants.TCOMM_PAYLOAD_SERIALIZED_CLOSE, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            Log.i(TAG, "payload ");
            return new JSONObject(replace);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Error parsing TComm message ", e, TAG);
            return null;
        }
    }

    @VisibleForTesting
    String getDeviceType() {
        return this.mDeviceType;
    }

    @VisibleForTesting
    EntertainmentAsyncTaskFactory getEntertainmentAsyncTaskFactory() {
        return this.mEntertainmentAsyncTaskFactory;
    }

    @VisibleForTesting
    String getMediaOwnerCustomerId() {
        JSONObject optJSONObject;
        Log.i(TAG, "getMediaOwnerCustomerId");
        JSONObject currentPlayerInfoModel = getCurrentPlayerInfoModel();
        return (currentPlayerInfoModel == null || (optJSONObject = currentPlayerInfoModel.optJSONObject("transport")) == null) ? "" : optJSONObject.optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_RATE_CONTENT_ACTION).optString("mediaOwnerCustomerId", "");
    }

    @VisibleForTesting
    String getMediaReferenceId() {
        return this.mMediaReferenceId;
    }

    @VisibleForTesting
    Lazy<NowPlayingScreenContract.Presenter> getNowPlayingScreenPresenter() {
        return this.mNowPlayingScreenPresenter;
    }

    @VisibleForTesting
    NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener getOnNowPlayingScreenInfoAsyncTaskListener() {
        return this.mOnNowPlayingScreenInfoAsyncTaskListener;
    }

    @VisibleForTesting
    List<NowPlayingScreenContract.Interactor.OnUpdateNowPlayingPlaybackStateListener> getOnUpdateNowPlayingPlaybackStateListenerList() {
        return this.mOnUpdateNowPlayingPlaybackStateListenerList;
    }

    @VisibleForTesting
    List<NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener> getOnUpdateNowPlayingScreenListenerList() {
        return this.mOnUpdateNowPlayingScreenListenerList;
    }

    @VisibleForTesting
    NowPlayingScreenContract.Interactor.PlayerCommandAsyncTaskStatusListener getPlayerCommandAsyncTaskStatusListener() {
        return this.mPlayerCommandAsyncTaskStatusListener;
    }

    SimpleMultiFilterSubscriber getTCommEventSubscriber() {
        return this.mTCommEventSubscriber;
    }

    @VisibleForTesting
    void handlePlayerInfo(String str) {
        Log.i(TAG, "parsePlayerInfo ");
        if (str != null) {
            try {
                this.mIsMediaReferenceIdUpdated = false;
                JSONObject jSONObject = new JSONObject(str);
                this.mCurrentPlayerInfoModel = jSONObject.getJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO);
                updateNowPlayingScreenListeners(jSONObject.getJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO));
                return;
            } catch (JSONException unused) {
                Log.e(TAG, "Error parsing player info");
            }
        }
        updateNowPlayingScreenListeners(null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x00b1, code lost:
        updateNowPlayingPlaybackStateListeners(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x009c A[Catch: JSONException -> 0x00b9, TryCatch #0 {JSONException -> 0x00b9, blocks: (B:4:0x000f, B:6:0x002f, B:8:0x0035, B:9:0x0039, B:12:0x006a, B:15:0x0075, B:17:0x0084, B:19:0x009c, B:26:0x00b1, B:27:0x00b5, B:22:0x00a8, B:16:0x007d), top: B:32:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void handleTCommAudioPlayerStateCommand(org.json.JSONObject r8) {
        /*
            r7 = this;
            java.lang.String r0 = "asin"
            java.lang.String r1 = ""
            java.lang.String r2 = "mediaReferenceId"
            java.lang.String r3 = com.amazon.alexa.drive.entertainment.interactor.NowPlayingScreenInteractor.TAG
            java.lang.String r4 = "parseTCommAudioPlayerStateCommand "
            android.util.Log.i(r3, r4)
            if (r8 == 0) goto Ld0
            java.lang.String r3 = r8.optString(r2, r1)     // Catch: org.json.JSONException -> Lb9
            java.lang.String r4 = com.amazon.alexa.drive.entertainment.interactor.NowPlayingScreenInteractor.TAG     // Catch: org.json.JSONException -> Lb9
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> Lb9
            r5.<init>()     // Catch: org.json.JSONException -> Lb9
            java.lang.String r6 = "mediaReferenceId is "
            r5.append(r6)     // Catch: org.json.JSONException -> Lb9
            r5.append(r3)     // Catch: org.json.JSONException -> Lb9
            java.lang.String r5 = r5.toString()     // Catch: org.json.JSONException -> Lb9
            android.util.Log.i(r4, r5)     // Catch: org.json.JSONException -> Lb9
            org.json.JSONObject r2 = r8.optJSONObject(r2)     // Catch: org.json.JSONException -> Lb9
            if (r2 == 0) goto L39
            boolean r4 = r2.has(r0)     // Catch: org.json.JSONException -> Lb9
            if (r4 == 0) goto L39
            java.lang.String r3 = r2.optString(r0, r1)     // Catch: org.json.JSONException -> Lb9
        L39:
            java.lang.String r0 = "audioPlayerState"
            java.lang.String r8 = r8.getString(r0)     // Catch: org.json.JSONException -> Lb9
            java.lang.String r0 = com.amazon.alexa.drive.entertainment.interactor.NowPlayingScreenInteractor.TAG     // Catch: org.json.JSONException -> Lb9
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> Lb9
            r1.<init>()     // Catch: org.json.JSONException -> Lb9
            java.lang.String r2 = "Requesting player info mCurrentMediaReferenceId "
            r1.append(r2)     // Catch: org.json.JSONException -> Lb9
            java.lang.String r2 = r7.getMediaReferenceId()     // Catch: org.json.JSONException -> Lb9
            r1.append(r2)     // Catch: org.json.JSONException -> Lb9
            java.lang.String r2 = " mediaReferenceId "
            r1.append(r2)     // Catch: org.json.JSONException -> Lb9
            r1.append(r3)     // Catch: org.json.JSONException -> Lb9
            java.lang.String r1 = r1.toString()     // Catch: org.json.JSONException -> Lb9
            android.util.Log.i(r0, r1)     // Catch: org.json.JSONException -> Lb9
            java.lang.String r0 = r7.getMediaReferenceId()     // Catch: org.json.JSONException -> Lb9
            java.lang.String r1 = "PLAYING"
            r2 = 1
            if (r0 == 0) goto L7d
            java.lang.String r0 = r7.getMediaReferenceId()     // Catch: org.json.JSONException -> Lb9
            boolean r0 = r0.equals(r3)     // Catch: org.json.JSONException -> Lb9
            if (r0 != 0) goto L75
            goto L7d
        L75:
            boolean r0 = r8.equals(r1)     // Catch: org.json.JSONException -> Lb9
            r7.setCurrentPlayerInfoModelAudioState(r0)     // Catch: org.json.JSONException -> Lb9
            goto L84
        L7d:
            r7.mIsMediaReferenceIdUpdated = r2     // Catch: org.json.JSONException -> Lb9
            r7.mMediaReferenceId = r3     // Catch: org.json.JSONException -> Lb9
            r7.requestPlayerInfo()     // Catch: org.json.JSONException -> Lb9
        L84:
            java.lang.String r0 = com.amazon.alexa.drive.entertainment.interactor.NowPlayingScreenInteractor.TAG     // Catch: org.json.JSONException -> Lb9
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> Lb9
            r3.<init>()     // Catch: org.json.JSONException -> Lb9
            java.lang.String r4 = "Audio player state "
            r3.append(r4)     // Catch: org.json.JSONException -> Lb9
            r3.append(r8)     // Catch: org.json.JSONException -> Lb9
            java.lang.String r3 = r3.toString()     // Catch: org.json.JSONException -> Lb9
            android.util.Log.i(r0, r3)     // Catch: org.json.JSONException -> Lb9
            if (r8 == 0) goto Ld0
            r0 = -1
            int r3 = r8.hashCode()     // Catch: org.json.JSONException -> Lb9
            r4 = 224418830(0xd605c0e, float:6.9136136E-31)
            r5 = 0
            if (r3 == r4) goto La8
            goto Laf
        La8:
            boolean r8 = r8.equals(r1)     // Catch: org.json.JSONException -> Lb9
            if (r8 == 0) goto Laf
            r0 = r5
        Laf:
            if (r0 == 0) goto Lb5
            r7.updateNowPlayingPlaybackStateListeners(r5)     // Catch: org.json.JSONException -> Lb9
            goto Ld0
        Lb5:
            r7.updateNowPlayingPlaybackStateListeners(r2)     // Catch: org.json.JSONException -> Lb9
            goto Ld0
        Lb9:
            r8 = move-exception
            java.lang.String r0 = com.amazon.alexa.drive.entertainment.interactor.NowPlayingScreenInteractor.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Error parsing audio player state "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            android.util.Log.e(r0, r8)
        Ld0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.drive.entertainment.interactor.NowPlayingScreenInteractor.handleTCommAudioPlayerStateCommand(org.json.JSONObject):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: handleTCommMessage */
    public void lambda$initialize$0$NowPlayingScreenInteractor(Message message) {
        String string;
        JSONObject optJSONObject;
        try {
            Log.i(TAG, "handleTCommMessage ");
            JSONObject jSONObject = new JSONObject(message.getPayloadAsString());
            JSONObject deserializedTCommPayloadJSON = getDeserializedTCommPayloadJSON(message.getPayloadAsString());
            if (!isCurrentDevice(deserializedTCommPayloadJSON) || (string = jSONObject.getString("command")) == null) {
                return;
            }
            char c = 65535;
            int hashCode = string.hashCode();
            if (hashCode != -1624419135) {
                if (hashCode != -1269114722) {
                    if (hashCode == 1581757876 && string.equals(EntertainmentConstants.TCOMM_MESSAGE_EVENT_PUSH_MEDIA_PREFERENCE_CHANGE)) {
                        c = 2;
                    }
                } else if (string.equals(EntertainmentConstants.TCOMM_MESSAGE_EVENT_PUSH_MEDIA_QUEUE_CHANGE)) {
                    c = 0;
                }
            } else if (string.equals(EntertainmentConstants.TCOMM_MESSAGE_EVENT_PUSH_AUDIO_PLAYER_STATE)) {
                c = 1;
            }
            if (c == 0) {
                Log.i(TAG, EntertainmentConstants.TCOMM_MESSAGE_EVENT_PUSH_MEDIA_QUEUE_CHANGE);
                requestPlayerInfo();
            } else if (c == 1) {
                Log.i(TAG, EntertainmentConstants.TCOMM_MESSAGE_EVENT_PUSH_AUDIO_PLAYER_STATE);
                handleTCommAudioPlayerStateCommand(deserializedTCommPayloadJSON);
            } else if (c != 2) {
                Log.e(TAG, "TComm event not supported");
            } else {
                Log.i(TAG, EntertainmentConstants.TCOMM_MESSAGE_EVENT_PUSH_MEDIA_PREFERENCE_CHANGE);
                String optString = deserializedTCommPayloadJSON.optString(EntertainmentConstants.TCOMM_MESSAGE_ATTRIBUTE_MEDIA_REFERENCE_ID, "");
                if (optString.isEmpty() && (optJSONObject = deserializedTCommPayloadJSON.optJSONObject(EntertainmentConstants.TCOMM_MESSAGE_ATTRIBUTE_MEDIA_REFERENCE_ID)) != null) {
                    optString = optJSONObject.optString(EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_ASIN, "");
                }
                if (!optString.equals(getCurrentMediaReferenceId())) {
                    return;
                }
                Log.i(TAG, "media preference for current media changed.");
                requestPlayerInfo();
            }
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Error parsing TComm message ", e, TAG);
        }
    }

    @VisibleForTesting
    void initListeners(NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener onNowPlayingScreenInfoAsyncTaskListener, NowPlayingScreenContract.Interactor.PlayerCommandAsyncTaskStatusListener playerCommandAsyncTaskStatusListener) {
        Log.i(TAG, "initListeners");
        this.mOnNowPlayingScreenInfoAsyncTaskListener = onNowPlayingScreenInfoAsyncTaskListener;
        this.mPlayerCommandAsyncTaskStatusListener = playerCommandAsyncTaskStatusListener;
    }

    @VisibleForTesting
    void initialize() {
        Log.i(TAG, "initialize");
        this.mDeviceInformation = (DeviceInformation) ComponentRegistry.getInstance().get(DeviceInformation.class).get();
        try {
            this.mDsn = this.mDeviceInformation.getDeviceSerialNumber();
            this.mDeviceType = this.mDeviceInformation.getDeviceType();
        } catch (DeviceInformationException e) {
            String str = TAG;
            Log.e(str, "DeviceInformationException " + e);
        }
        this.mOnUpdateNowPlayingScreenListenerList = new ArrayList();
        this.mOnUpdateNowPlayingPlaybackStateListenerList = new ArrayList();
        this.mEventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(this.mEventBus);
        this.mTCommEventSubscriber = new SimpleMultiFilterSubscriber();
        getTCommEventSubscriber().subscribe(new EventTypeMessageFilter("tcomm::message"), new MessageHandler() { // from class: com.amazon.alexa.drive.entertainment.interactor.-$$Lambda$NowPlayingScreenInteractor$UMpF0bDZpu7ep6fGrHs0u4VEllU
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                NowPlayingScreenInteractor.this.lambda$initialize$0$NowPlayingScreenInteractor(message);
            }
        });
        this.mEventBus.subscribe(getTCommEventSubscriber());
        initListeners(new NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener() { // from class: com.amazon.alexa.drive.entertainment.interactor.NowPlayingScreenInteractor.1
            @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener
            public void onNowPlayingScreenInfoFetchComplete(String str2) {
                Log.i(NowPlayingScreenInteractor.TAG, "onNowPlayingScreenInfoFetchComplete");
                NowPlayingScreenInteractor.this.handlePlayerInfo(str2);
            }

            @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener
            public void onNowPlayingScreenInfoFetchFailed(MediaErrorPayload mediaErrorPayload) {
                String str2 = NowPlayingScreenInteractor.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onNowPlayingScreenInfoFetchFailed ");
                outline107.append(mediaErrorPayload.getTitle());
                Log.e(str2, outline107.toString());
                if (NowPlayingScreenInteractor.this.getNowPlayingScreenPresenter().mo358get() != null) {
                    NowPlayingScreenInteractor.this.getNowPlayingScreenPresenter().mo358get().onMediaError(mediaErrorPayload);
                }
            }
        }, new NowPlayingScreenContract.Interactor.PlayerCommandAsyncTaskStatusListener() { // from class: com.amazon.alexa.drive.entertainment.interactor.NowPlayingScreenInteractor.2
            @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor.PlayerCommandAsyncTaskStatusListener
            public void onPlayerCommandAsyncTaskFailed(MediaErrorPayload mediaErrorPayload) {
                String str2 = NowPlayingScreenInteractor.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onPlayerCommandAsyncTaskFailed ");
                outline107.append(mediaErrorPayload.getTitle());
                Log.e(str2, outline107.toString());
                if (NowPlayingScreenInteractor.this.getNowPlayingScreenPresenter().mo358get() != null) {
                    NowPlayingScreenInteractor.this.getNowPlayingScreenPresenter().mo358get().onMediaError(mediaErrorPayload);
                }
            }
        });
        initAlexaServicesConnection();
    }

    @VisibleForTesting
    boolean isCurrentDevice(JSONObject jSONObject) {
        Log.i(TAG, "isCurrentDevice ");
        try {
            String optString = jSONObject.getJSONObject(EntertainmentConstants.TCOMM_MESSAGE_ATTRIBUTE_DOPPLER_ID).optString("deviceSerialNumber", "");
            String str = TAG;
            Log.i(str, "Equals " + optString.equals(getDSN()));
            return optString.equals(getDSN());
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Error parsing TComm payload ", e, TAG);
            return false;
        }
    }

    boolean isMediaReferenceIdUpdated() {
        return this.mIsMediaReferenceIdUpdated;
    }

    @VisibleForTesting
    void next() {
        Log.i(TAG, "VOX Next");
        AlexaServicesApis.MediaPlaybackController.next(getAlexaServicesConnection());
    }

    @VisibleForTesting
    void pause() {
        Log.i(TAG, "VOX Pause");
        AlexaServicesApis.MediaPlaybackController.pause(getAlexaServicesConnection());
    }

    @VisibleForTesting
    void play() {
        Log.i(TAG, "VOX Play");
        AlexaServicesApis.MediaPlaybackController.play(getAlexaServicesConnection());
    }

    @VisibleForTesting
    void previous() {
        Log.i(TAG, "VOX Previous");
        AlexaServicesApis.MediaPlaybackController.previous(getAlexaServicesConnection());
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor
    public void removeOnUpdateNowPlayingPlaybackStateListener(NowPlayingScreenContract.Interactor.OnUpdateNowPlayingPlaybackStateListener onUpdateNowPlayingPlaybackStateListener) {
        String str = TAG;
        Log.i(str, "removeOnUpdateNowPlayingPlaybackStateListener " + onUpdateNowPlayingPlaybackStateListener);
        getOnUpdateNowPlayingPlaybackStateListenerList().remove(onUpdateNowPlayingPlaybackStateListener);
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor
    public void removeOnUpdateNowPlayingScreenListener(NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener onUpdateNowPlayingScreenListener) {
        String str = TAG;
        Log.i(str, "removeOnUpdateNowPlayingScreenListener " + onUpdateNowPlayingScreenListener);
        getOnUpdateNowPlayingScreenListenerList().remove(onUpdateNowPlayingScreenListener);
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor
    public void requestPlayerInfo() {
        Log.i(TAG, "requestPlayerInfo");
        getEntertainmentAsyncTaskFactory().createNowPlayingScreenInfoAsyncTask(getOnNowPlayingScreenInfoAsyncTaskListener()).execute(getDeviceType(), getDSN());
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor
    public void sendPrimaryTransportControlCommand(TransportControlCommand transportControlCommand) {
        String str = TAG;
        Log.i(str, "sendPrimaryTransportControlCommand " + transportControlCommand);
        if (transportControlCommand != null) {
            int ordinal = transportControlCommand.ordinal();
            if (ordinal == 0) {
                pause();
            } else if (ordinal == 1) {
                play();
            } else if (ordinal == 2) {
                previous();
            } else if (ordinal == 3) {
                next();
            } else if (ordinal == 4) {
                sendPrimaryPlayerCommandEvent(EntertainmentConstants.PLAYER_COMMAND_REWIND);
            } else if (ordinal != 5) {
            } else {
                sendPrimaryPlayerCommandEvent(EntertainmentConstants.PLAYER_COMMAND_FORWARD);
            }
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor
    public void sendSecondaryTransportControlCommand(TransportControlCommand transportControlCommand, boolean z) {
        String str = TAG;
        Log.i(str, "sendSecondaryTransportControlCommand " + transportControlCommand + " enabled  " + z);
        if (transportControlCommand != null) {
            switch (transportControlCommand.ordinal()) {
                case 6:
                    sendSecondaryPlayerCommandEvent(EntertainmentConstants.PLAYER_COMMAND_SHUFFLE, z);
                    return;
                case 7:
                    sendSecondaryPlayerCommandEvent(EntertainmentConstants.PLAYER_COMMAND_REPEAT, z);
                    return;
                case 8:
                    sendSecondaryPlayerCommandEvent(EntertainmentConstants.PLAYER_COMMAND_LIKE, z);
                    return;
                case 9:
                    sendSecondaryPlayerCommandEvent(EntertainmentConstants.PLAYER_COMMAND_DISLIKE, z);
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor
    public void setCurrentPlayerInfo(JSONObject jSONObject) {
        Log.i(TAG, "setCurrentPlayerInfo");
        this.mIsMediaReferenceIdUpdated = false;
        this.mCurrentPlayerInfoModel = jSONObject;
        if (jSONObject != null) {
            try {
                if (!jSONObject.has("state") || !jSONObject.getString("state").equals("PLAYING")) {
                    return;
                }
                updateNowPlayingScreenListeners(jSONObject);
            } catch (JSONException e) {
                String str = TAG;
                Log.e(str, "Error getting current player info " + e);
            }
        }
    }

    void setCurrentPlayerInfoModelAudioState(boolean z) {
        String str = TAG;
        Log.i(str, "setCurrentPlayerInfoModelAudioState " + z);
        JSONObject currentPlayerInfo = getCurrentPlayerInfo();
        if (currentPlayerInfo != null) {
            try {
                if (!currentPlayerInfo.has("state")) {
                    return;
                }
                currentPlayerInfo.put("state", z ? "PLAYING" : EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYBACK_STATE_FINISHED);
                setCurrentPlayerInfo(currentPlayerInfo);
            } catch (JSONException e) {
                String str2 = TAG;
                Log.e(str2, "Error updating audio player state " + e);
            }
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor
    public void updateAudioPlayerState(AlexaPlayerInfoState alexaPlayerInfoState) {
        String str = TAG;
        Log.i(str, "updateAudioPlayerState " + alexaPlayerInfoState);
        int ordinal = alexaPlayerInfoState.ordinal();
        if (ordinal != 0 && ordinal != 3) {
            updateNowPlayingPlaybackStateListeners(false);
            setCurrentPlayerInfoModelAudioState(false);
            return;
        }
        updateNowPlayingPlaybackStateListeners(true);
        setCurrentPlayerInfoModelAudioState(true);
    }

    @VisibleForTesting
    void updateNowPlayingPlaybackStateListeners(boolean z) {
        GeneratedOutlineSupport1.outline173("updateNowPlayingPlaybackStateListeners ", z, TAG);
        for (NowPlayingScreenContract.Interactor.OnUpdateNowPlayingPlaybackStateListener onUpdateNowPlayingPlaybackStateListener : getOnUpdateNowPlayingPlaybackStateListenerList()) {
            onUpdateNowPlayingPlaybackStateListener.updateNowPlayingPlaybackState(z);
        }
    }

    @VisibleForTesting
    void updateNowPlayingScreenListeners(JSONObject jSONObject) {
        if (jSONObject != null) {
            Log.i(TAG, "updateNowPlayingScreenListeners ");
            for (NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener onUpdateNowPlayingScreenListener : getOnUpdateNowPlayingScreenListenerList()) {
                onUpdateNowPlayingScreenListener.updateNowPlayingScreen(jSONObject);
            }
        }
    }
}
