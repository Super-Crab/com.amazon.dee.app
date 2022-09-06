package com.amazon.alexa.drive.entertainment.interactor;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaMediaPlaybackListener;
import com.amazon.alexa.api.AlexaMediaPlaybackMetadata;
import com.amazon.alexa.api.AlexaMediaPlaybackState;
import com.amazon.alexa.api.AlexaPlayerInfoCardListener;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.api.AlexaServicesApis;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.entertainment.model.EntertainmentCardItem;
import com.amazon.alexa.drive.entertainment.model.MediaErrorPayload;
import com.amazon.alexa.drive.entertainment.repository.EntertainmentDataRepository;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class EntertainmentInteractor implements EntertainmentLandingPageContract.EntertainmentInteractor, AlexaServicesConnection.ConnectionListener, AlexaPlayerInfoCardListener, AlexaMediaPlaybackListener {
    private static final int ALEXA_CONNECTION_RETRY_ATTEMPT_LIMIT = 2;
    private static final String AUDIO_ITEM_DELIMITER = "#";
    private static final int ENTERTAINMENT_ITEM_LIMIT = 9;
    static final int ENTERTAINMENT_LOADING_ITEM_LIMIT = 6;
    private static final int ENTERTAINMENT_RECENTLY_PLAYED_LIST_START = 0;
    private static final String TAG = "EntertainmentInteractor";
    private int mAlexaConnectionRetryCount;
    private AlexaServicesConnection mAlexaServicesConnection;
    private String mCurrentMediaReferenceId;
    private String mCurrentPlayerInfo;
    private DeviceInformation mDeviceInformation;
    private String mDeviceType;
    private String mDsn;
    private EntertainmentAsyncTaskFactory mEntertainmentAsyncTaskFactory;
    private EntertainmentDataRepository mEntertainmentDataRepository;
    private Lazy<EntertainmentLandingPageContract.Presenter> mEntertainmentLandingPagePresenter;
    private EntertainmentMetrics mEntertainmentMetrics;
    private EventBus mEventBus;
    private Handler mHandler;
    private boolean mIsMediaPlaying;
    private boolean mIsMediaReferenceIdUpdated;
    private boolean mIsNowPlayingItemActive;
    private boolean mIsRecentlyPlayedDataFetchComplete;
    private boolean mIsVoiceRequest;
    private AlexaMediaPlaybackMetadata mMediaMetadata;
    private boolean mNewPlayerInfoRequested;
    private EntertainmentCardItem mNowPlayingEntertainmentCardItem;
    private NowPlayingScreenContract.Interactor mNowPlayingScreenInteractor;
    private EntertainmentLandingPageContract.EntertainmentInteractor.OnEntertainmentCardsFetchCompleteListener mOnEntertainmentCardsFetchCompleteListener;
    private NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener mOnNowPlayingScreenInfoAsyncTaskListener;
    private EntertainmentLandingPageContract.EntertainmentInteractor.OnPlaylistInitTaskCompleteListener mOnPlaylistInitTaskCompleteListener;
    private List<EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener> mOnUpdateNowPlayingCardListenerList;
    private List<NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener> mOnUpdateNowPlayingScreenListenerList;
    private SimpleMultiFilterSubscriber mTCommEventSubscriber;
    private boolean mUpdateCardsWhenAudioStateChanges;

    public EntertainmentInteractor(Lazy<EntertainmentLandingPageContract.Presenter> lazy, AlexaServicesConnection alexaServicesConnection, EntertainmentDataRepository entertainmentDataRepository, NowPlayingScreenContract.Interactor interactor, EntertainmentAsyncTaskFactory entertainmentAsyncTaskFactory, EntertainmentMetrics entertainmentMetrics) {
        Log.i(TAG, "EntertainmentInteractor Constructor");
        this.mEntertainmentLandingPagePresenter = lazy;
        this.mAlexaServicesConnection = alexaServicesConnection;
        this.mNowPlayingScreenInteractor = interactor;
        this.mEntertainmentDataRepository = entertainmentDataRepository;
        this.mEntertainmentAsyncTaskFactory = entertainmentAsyncTaskFactory;
        this.mEntertainmentMetrics = entertainmentMetrics;
        initialize();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAlexaServicesConnection() {
        Log.i(TAG, "initAlexaServicesConnection");
        this.mAlexaServicesConnection.connect();
        this.mAlexaServicesConnection.registerListener(this);
    }

    private void initTCommEventBus() {
        Log.i(TAG, "initTCommEventBus");
        this.mEventBus = (EventBus) ComponentRegistry.getInstance().get(EventBus.class).orNull();
        Preconditions.checkNotNull(this.mEventBus);
        this.mTCommEventSubscriber = new SimpleMultiFilterSubscriber();
        getTCommEventSubscriber().subscribe(new EventTypeMessageFilter("tcomm::message"), new MessageHandler() { // from class: com.amazon.alexa.drive.entertainment.interactor.-$$Lambda$EntertainmentInteractor$7fGvDCRzAKU6lJOpAWERO6GPj_U
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                EntertainmentInteractor.this.lambda$initTCommEventBus$0$EntertainmentInteractor(message);
            }
        });
        this.mEventBus.subscribe(getTCommEventSubscriber());
    }

    private String logEntertainmentCardItem(EntertainmentCardItem entertainmentCardItem) {
        StringBuilder sb = new StringBuilder();
        if (entertainmentCardItem != null) {
            sb.append(entertainmentCardItem.getTitle());
            sb.append("\n");
            sb.append(entertainmentCardItem.getQueueId());
            sb.append("\n");
            sb.append(entertainmentCardItem.getProviderId());
            sb.append("\n");
            sb.append(entertainmentCardItem.getPlayUri());
            sb.append("\n");
            return sb.toString();
        }
        return "";
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void addOnUpdateNowPlayingCardListener(EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener onUpdateNowPlayingCardListener) {
        Log.i(TAG, "addOnUpdateNowPlayingCardListener");
        getOnUpdateNowPlayingCardListenerList().add(onUpdateNowPlayingCardListener);
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void addOnUpdateNowPlayingScreenListener(NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener onUpdateNowPlayingScreenListener) {
        Log.i(TAG, "addOnUpdateNowPlayingScreenListener");
        getOnUpdateNowPlayingScreenListenerList().add(onUpdateNowPlayingScreenListener);
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void cleanUp() {
        Log.i(TAG, "cleanUp");
    }

    @VisibleForTesting
    EntertainmentLandingPageContract.EntertainmentInteractor.OnEntertainmentCardsFetchCompleteListener createOnEntertainmentCardsFetchCompleteListener() {
        return new EntertainmentLandingPageContract.EntertainmentInteractor.OnEntertainmentCardsFetchCompleteListener() { // from class: com.amazon.alexa.drive.entertainment.interactor.EntertainmentInteractor.1
            @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor.OnEntertainmentCardsFetchCompleteListener
            public void onEntertainmentCardsFetchComplete(List<EntertainmentCardItem> list) {
                String str = EntertainmentInteractor.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onEntertainmentCardsFetchComplete ");
                outline107.append(list.size());
                Log.i(str, outline107.toString());
                EntertainmentInteractor.this.mIsRecentlyPlayedDataFetchComplete = true;
                EntertainmentInteractor.this.updateEntertainmentItemList(list);
                if (EntertainmentInteractor.this.getEntertainmentLandingPagePresenter().mo358get() != null) {
                    EntertainmentInteractor.this.getEntertainmentLandingPagePresenter().mo358get().onEntertainmentDataListFetchComplete(list.size());
                }
            }

            @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor.OnEntertainmentCardsFetchCompleteListener
            public void onEntertainmentCardsFetchFailed(MediaErrorPayload mediaErrorPayload) {
                String str = EntertainmentInteractor.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onEntertainmentCardsFetchFailed ");
                outline107.append(mediaErrorPayload.getTitle());
                Log.e(str, outline107.toString());
                if (EntertainmentInteractor.this.getEntertainmentLandingPagePresenter().mo358get() != null) {
                    EntertainmentInteractor.this.getEntertainmentLandingPagePresenter().mo358get().onMediaError(mediaErrorPayload);
                }
                if (!EntertainmentInteractor.this.getAlexaServicesConnection().isConnected()) {
                    EntertainmentInteractor.this.getAlexaServicesConnection().deregisterListener(EntertainmentInteractor.this);
                    EntertainmentInteractor.this.initAlexaServicesConnection();
                }
            }
        };
    }

    NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener createOnNowPlayingScreenInfoAsyncTaskListener() {
        return new NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener() { // from class: com.amazon.alexa.drive.entertainment.interactor.EntertainmentInteractor.2
            @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener
            public void onNowPlayingScreenInfoFetchComplete(String str) {
                Log.i(EntertainmentInteractor.TAG, "onNowPlayingScreenInfoFetchComplete");
                EntertainmentInteractor.this.handlePlayerInfoUpdate(str);
            }

            @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener
            public void onNowPlayingScreenInfoFetchFailed(MediaErrorPayload mediaErrorPayload) {
                String str = EntertainmentInteractor.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onNowPlayingScreenInfoFetchFailed ");
                outline107.append(mediaErrorPayload.getTitle());
                Log.e(str, outline107.toString());
                EntertainmentInteractor.this.mCurrentPlayerInfo = null;
            }
        };
    }

    @VisibleForTesting
    EntertainmentLandingPageContract.EntertainmentInteractor.OnPlaylistInitTaskCompleteListener createOnPlaylistInitTaskCompleteListener() {
        return new EntertainmentLandingPageContract.EntertainmentInteractor.OnPlaylistInitTaskCompleteListener() { // from class: com.amazon.alexa.drive.entertainment.interactor.EntertainmentInteractor.3
            @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor.OnPlaylistInitTaskCompleteListener
            public void onPlaylistInitFailed(MediaErrorPayload mediaErrorPayload) {
                String str = EntertainmentInteractor.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onPlaylistInitFailed ");
                outline107.append(mediaErrorPayload.getTitle());
                Log.e(str, outline107.toString());
                EntertainmentInteractor.this.mEntertainmentMetrics.logMusicHistoryErrorDisplayed(mediaErrorPayload.getTitle());
                if (EntertainmentInteractor.this.getEntertainmentLandingPagePresenter().mo358get() != null) {
                    EntertainmentInteractor.this.getEntertainmentLandingPagePresenter().mo358get().onMediaError(mediaErrorPayload);
                }
                if (!EntertainmentInteractor.this.getAlexaServicesConnection().isConnected()) {
                    EntertainmentInteractor.this.getAlexaServicesConnection().deregisterListener(EntertainmentInteractor.this);
                    EntertainmentInteractor.this.initAlexaServicesConnection();
                }
            }
        };
    }

    @VisibleForTesting
    EntertainmentCardItem createPartialEntertainmentCardItem(String str) {
        JSONObject optJSONObject;
        String str2 = "";
        Log.i(TAG, "createPartialEntertainmentCardItem ");
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO)) {
                jSONObject = jSONObject.getJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO);
            }
            String idFromAudioItem = getIdFromAudioItem(jSONObject.optString("mediaId", str2));
            JSONObject optJSONObject2 = jSONObject.optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_INFO_TEXT);
            String optString = (optJSONObject2 != null || jSONObject.optJSONObject("template") == null) ? optJSONObject2 != null ? optJSONObject2.optString("title", str2) : str2 : jSONObject.optJSONObject("template").optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CARD).optString("title", str2);
            if (!optString.isEmpty() && optString.toLowerCase().equals("null")) {
                optString = str2;
            }
            JSONObject optJSONObject3 = jSONObject.optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER);
            String string = optJSONObject3 != null ? optJSONObject3.getString("providerName") : str2;
            if (jSONObject.optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_MAIN_ART) != null) {
                optJSONObject = jSONObject.getJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_MAIN_ART);
            } else {
                optJSONObject = jSONObject.optJSONObject("template") != null ? jSONObject.getJSONObject("template").optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_ART) : null;
            }
            if (optJSONObject != null) {
                str2 = optJSONObject.optString("url", str2);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("url", str2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(jSONObject2);
            EntertainmentCardItem entertainmentCardItem = new EntertainmentCardItem();
            entertainmentCardItem.setQueueId(idFromAudioItem);
            entertainmentCardItem.setSubTitle(string);
            entertainmentCardItem.setTitle(optString);
            entertainmentCardItem.setAlbumArtUrls(arrayList);
            return entertainmentCardItem;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Error creating EntertainmentCardItem ", e, TAG);
            return null;
        }
    }

    JSONObject createPlayerInfoFromEntertainmentCardItem(EntertainmentCardItem entertainmentCardItem) {
        JSONObject jSONObject;
        if (entertainmentCardItem != null) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("title", entertainmentCardItem.getTitle());
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("providerName", entertainmentCardItem.getSubTitle());
                List<JSONObject> albumArtUrls = entertainmentCardItem.getAlbumArtUrls();
                if (albumArtUrls != null && albumArtUrls.size() > 0 && (jSONObject = albumArtUrls.get(albumArtUrls.size() - 1)) != null) {
                    jSONObject2.put(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_MAIN_ART, jSONObject);
                }
                jSONObject2.put(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER, jSONObject4);
                jSONObject2.put(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_INFO_TEXT, jSONObject3);
                jSONObject2.put("mediaId", "defaultID");
                String str = TAG;
                Log.i(str, "createPlayerInfoFromEntertainmentCardItem " + jSONObject2.toString());
                return jSONObject2;
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline156("Error creating player info ", e, TAG);
                return null;
            }
        }
        return null;
    }

    JSONObject createPlayerInfoFromMediaMetadata(AlexaMediaPlaybackMetadata alexaMediaPlaybackMetadata) {
        Log.i(TAG, "createPlayerInfoFromMediaMetadata ");
        try {
            JSONObject jSONObject = new JSONObject();
            if (alexaMediaPlaybackMetadata.getSongName() != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("title", alexaMediaPlaybackMetadata.getSongName());
                if (alexaMediaPlaybackMetadata.getArtistName() != null) {
                    jSONObject2.put(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_SUB_TEXT_1, alexaMediaPlaybackMetadata.getArtistName());
                }
                jSONObject.put(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_INFO_TEXT, jSONObject2);
            }
            if (alexaMediaPlaybackMetadata.getMediaServiceProvider() != null) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("providerName", alexaMediaPlaybackMetadata.getMediaServiceProvider());
                jSONObject.put(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER, jSONObject3);
            }
            if (alexaMediaPlaybackMetadata.getAlbumArtUri() != null) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("url", alexaMediaPlaybackMetadata.getAlbumArtUri().toString());
                jSONObject.put(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_MAIN_ART, jSONObject4);
            }
            if (alexaMediaPlaybackMetadata.getDuration() > 0) {
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("visible", true);
                jSONObject5.put(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_MEDIA_LENGTH, (int) alexaMediaPlaybackMetadata.getDuration());
                jSONObject5.put(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_MEDIA_PROGRESS, (int) alexaMediaPlaybackMetadata.getCurrentPosition());
                jSONObject.put("progress", jSONObject5);
            }
            jSONObject.put("mediaId", alexaMediaPlaybackMetadata.getMediaItemIdentifier());
            String str = TAG;
            Log.i(str, "createPlayerInfoFromMediaMetadata " + jSONObject.toString());
            return jSONObject;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Error creating player info ", e, TAG);
            return null;
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void destroy() {
        Log.i(TAG, "destroy");
        if (getAlexaServicesConnection() != null) {
            AlexaServices.Cards.deregisterPlayerInfoCardListener(getAlexaServicesConnection(), this);
            AlexaServicesApis.MediaPlaybackController.deregisterListener(getAlexaServicesConnection(), this);
            this.mAlexaServicesConnection.disconnect();
            this.mAlexaServicesConnection.deregisterListener(this);
        }
        if (getTCommEventSubscriber() != null) {
            this.mEventBus.unsubscribe(getTCommEventSubscriber());
        }
    }

    @VisibleForTesting
    AlexaServicesConnection getAlexaServicesConnection() {
        return this.mAlexaServicesConnection;
    }

    String getCurrentMediaReferenceId() {
        return this.mCurrentMediaReferenceId;
    }

    String getCurrentPlayerInfo() {
        return this.mCurrentPlayerInfo;
    }

    @VisibleForTesting
    String getDSN() {
        return this.mDsn;
    }

    @VisibleForTesting
    JSONObject getDeserializedTCommPayloadJSON(String str) {
        Log.i(TAG, "getDeserializedTCommPayloadJSON");
        try {
            return new JSONObject(new JSONObject(str).getString("payload").replaceAll(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_SERIALIZED, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED).replace(EntertainmentConstants.TCOMM_PAYLOAD_SERIALIZED_OPEN, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN).replace(EntertainmentConstants.TCOMM_PAYLOAD_SERIALIZED_CLOSE, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE));
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
    EntertainmentDataRepository getEntertainmentDataRepository() {
        return this.mEntertainmentDataRepository;
    }

    @VisibleForTesting
    Lazy<EntertainmentLandingPageContract.Presenter> getEntertainmentLandingPagePresenter() {
        return this.mEntertainmentLandingPagePresenter;
    }

    JSONObject getFlashBriefingPlayerInfo(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject("transport");
                if (optJSONObject == null) {
                    return null;
                }
                optJSONObject.put(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CONTROL_PLAY_PAUSE, "ENABLED");
                jSONObject.put("transport", optJSONObject);
                return jSONObject;
            } catch (JSONException e) {
                String str = TAG;
                Log.e(str, "Error parsing flash briefing JSON " + e);
                return null;
            }
        }
        return null;
    }

    public String getIdFromAudioItem(String str) {
        Log.i(TAG, "getIdFromAudioItem");
        return str.split("#").length < 5 ? "" : str.split("#")[4].split(":")[0];
    }

    AlexaMediaPlaybackMetadata getMediaMetadata() {
        return this.mMediaMetadata;
    }

    @VisibleForTesting
    EntertainmentCardItem getNowPlayingEntertainmentCardItem() {
        return this.mNowPlayingEntertainmentCardItem;
    }

    @VisibleForTesting
    EntertainmentLandingPageContract.EntertainmentInteractor.OnEntertainmentCardsFetchCompleteListener getOnEntertainmentCardsFetchCompleteListener() {
        return this.mOnEntertainmentCardsFetchCompleteListener;
    }

    @VisibleForTesting
    NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener getOnNowPlayingScreenInfoAsyncTaskListener() {
        return this.mOnNowPlayingScreenInfoAsyncTaskListener;
    }

    @VisibleForTesting
    EntertainmentLandingPageContract.EntertainmentInteractor.OnPlaylistInitTaskCompleteListener getOnPlaylistInitTaskCompleteListener() {
        return this.mOnPlaylistInitTaskCompleteListener;
    }

    @VisibleForTesting
    List<EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener> getOnUpdateNowPlayingCardListenerList() {
        return this.mOnUpdateNowPlayingCardListenerList;
    }

    @VisibleForTesting
    List<NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener> getOnUpdateNowPlayingScreenListenerList() {
        return this.mOnUpdateNowPlayingScreenListenerList;
    }

    @VisibleForTesting
    JSONObject getPlayerInfoJson(@Nullable String str) {
        Log.i(TAG, "getPlayerInfoJson");
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            String str2 = TAG;
            Log.e(str2, "Exception when parsing player info. " + e);
            return null;
        }
    }

    SimpleMultiFilterSubscriber getTCommEventSubscriber() {
        return this.mTCommEventSubscriber;
    }

    void handleFlashBriefingPlayerInfo(JSONObject jSONObject) {
        String str = "";
        Log.i(TAG, "handleFlashBriefingPlayerInfo");
        if (getCurrentPlayerInfo() != null) {
            try {
                JSONObject jSONObject2 = new JSONObject(getCurrentPlayerInfo()).getJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO);
                if (jSONObject2 == null) {
                    return;
                }
                String optString = jSONObject2.optString("mediaId", str);
                JSONObject optJSONObject = jSONObject2.optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_INFO_TEXT);
                String optString2 = optJSONObject != null ? optJSONObject.optString("title", str) : str;
                if (jSONObject == null) {
                    return;
                }
                String optString3 = jSONObject.optString("mediaId", str);
                JSONObject optJSONObject2 = jSONObject.optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_INFO_TEXT);
                if (optJSONObject2 != null) {
                    str = optJSONObject2.optString("title", str);
                }
                if (optString.equals(optString3) || optString2.equals(str)) {
                    return;
                }
                this.mHandler.post(new $$Lambda$ahruOxX8KeEFDzsEpj9kFaszbhw(this));
                return;
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline156("Error parsing current player info ", e, TAG);
                return;
            }
        }
        Log.i(TAG, "getCurrentPlayerInfo is null, navigating to NPS");
        this.mHandler.post(new $$Lambda$ahruOxX8KeEFDzsEpj9kFaszbhw(this));
    }

    void handlePlayerInfoUpdate(String str) {
        Log.i(TAG, "handlePlayerInfoUpdate ");
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str).getJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO);
                if (!jSONObject.optString("mediaId", "").isEmpty() && !jSONObject.optString("mediaId", "").equals("null")) {
                    this.mCurrentPlayerInfo = str;
                    EntertainmentCardItem createPartialEntertainmentCardItem = createPartialEntertainmentCardItem(str);
                    if (isNewPlayerInfoRequested()) {
                        this.mNewPlayerInfoRequested = false;
                        if (createPartialEntertainmentCardItem != null) {
                            this.mEntertainmentMetrics.logPlayQueueStarted(createPartialEntertainmentCardItem.getSubTitle());
                        }
                    }
                    if (!isNowPlayingItemActive()) {
                        return;
                    }
                    this.mNowPlayingEntertainmentCardItem = createPartialEntertainmentCardItem;
                    if (isMediaPlaying()) {
                        notifyOnUpdateNowPlayingCardListeners(createPartialEntertainmentCardItem, AlexaPlayerInfoState.PLAYING);
                        return;
                    } else {
                        notifyOnUpdateNowPlayingCardListeners(createPartialEntertainmentCardItem, AlexaPlayerInfoState.DONE);
                        return;
                    }
                }
                Log.w(TAG, "Null player info");
            } catch (JSONException unused) {
                Log.e(TAG, "Error parsing player info");
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x00df, code lost:
        r8.mIsMediaPlaying = false;
        notifyOnUpdateNowPlayingCardListeners(getNowPlayingEntertainmentCardItem(), com.amazon.alexa.api.AlexaPlayerInfoState.DONE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void handleTCommAudioPlayerStateCommand(org.json.JSONObject r9) {
        /*
            Method dump skipped, instructions count: 296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.drive.entertainment.interactor.EntertainmentInteractor.handleTCommAudioPlayerStateCommand(org.json.JSONObject):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: handleTCommMessage */
    public void lambda$initTCommEventBus$0$EntertainmentInteractor(Message message) {
        try {
            Log.i(TAG, "handleTCommMessage");
            JSONObject jSONObject = new JSONObject(message.getPayloadAsString());
            JSONObject deserializedTCommPayloadJSON = getDeserializedTCommPayloadJSON(message.getPayloadAsString());
            if (!isCurrentDevice(deserializedTCommPayloadJSON)) {
                return;
            }
            Log.i(TAG, "current device");
            String string = jSONObject.getString("command");
            Log.i(TAG, "command " + string);
            char c = 65535;
            int hashCode = string.hashCode();
            if (hashCode != -1624419135) {
                if (hashCode == -1269114722 && string.equals(EntertainmentConstants.TCOMM_MESSAGE_EVENT_PUSH_MEDIA_QUEUE_CHANGE)) {
                    c = 0;
                }
            } else if (string.equals(EntertainmentConstants.TCOMM_MESSAGE_EVENT_PUSH_AUDIO_PLAYER_STATE)) {
                c = 1;
            }
            if (c == 0) {
                Log.i(TAG, EntertainmentConstants.TCOMM_MESSAGE_EVENT_PUSH_MEDIA_QUEUE_CHANGE);
            } else if (c != 1) {
            } else {
                Log.i(TAG, EntertainmentConstants.TCOMM_MESSAGE_EVENT_PUSH_AUDIO_PLAYER_STATE);
                handleTCommAudioPlayerStateCommand(deserializedTCommPayloadJSON);
            }
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Error parsing TComm message ", e, TAG);
        }
    }

    @VisibleForTesting
    void initListeners(EntertainmentLandingPageContract.EntertainmentInteractor.OnEntertainmentCardsFetchCompleteListener onEntertainmentCardsFetchCompleteListener, EntertainmentLandingPageContract.EntertainmentInteractor.OnPlaylistInitTaskCompleteListener onPlaylistInitTaskCompleteListener, NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener onNowPlayingScreenInfoAsyncTaskListener) {
        Log.i(TAG, "initListeners");
        this.mOnEntertainmentCardsFetchCompleteListener = onEntertainmentCardsFetchCompleteListener;
        this.mOnPlaylistInitTaskCompleteListener = onPlaylistInitTaskCompleteListener;
        this.mOnNowPlayingScreenInfoAsyncTaskListener = onNowPlayingScreenInfoAsyncTaskListener;
    }

    @VisibleForTesting
    void initialize() {
        Log.i(TAG, "initialize");
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mDeviceInformation = (DeviceInformation) GeneratedOutlineSupport1.outline21(DeviceInformation.class);
        Preconditions.checkNotNull(this.mDeviceInformation);
        try {
            this.mDsn = this.mDeviceInformation.getDeviceSerialNumber();
            this.mDeviceType = this.mDeviceInformation.getDeviceType();
        } catch (DeviceInformationException e) {
            String str = TAG;
            Log.e(str, "DeviceInformationException " + e);
        }
        this.mOnUpdateNowPlayingCardListenerList = new ArrayList();
        this.mOnUpdateNowPlayingScreenListenerList = new ArrayList();
        initAlexaServicesConnection();
        initTCommEventBus();
        initListeners(createOnEntertainmentCardsFetchCompleteListener(), createOnPlaylistInitTaskCompleteListener(), createOnNowPlayingScreenInfoAsyncTaskListener());
        requestPlayerInfo();
    }

    @VisibleForTesting
    boolean isCurrentDevice(JSONObject jSONObject) {
        Log.i(TAG, "isCurrentDevice ");
        try {
            String optString = jSONObject.getJSONObject(EntertainmentConstants.TCOMM_MESSAGE_ATTRIBUTE_DOPPLER_ID).optString("deviceSerialNumber", "");
            String str = TAG;
            Log.i(str, "isEqual" + optString.equals(getDSN()));
            return optString.equals(getDSN());
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Error parsing TComm payload ", e, TAG);
            return false;
        }
    }

    boolean isFlashBriefingProvider(JSONObject jSONObject) {
        if (jSONObject != null) {
            String optString = jSONObject.optString("mediaId", "");
            return !optString.isEmpty() && optString.contains(EntertainmentConstants.PROVIDER_FLASH_BRIEFING);
        }
        return false;
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    @VisibleForTesting
    public boolean isMediaPlaying() {
        return this.mIsMediaPlaying;
    }

    boolean isMediaReferenceIdUpdated() {
        return this.mIsMediaReferenceIdUpdated;
    }

    boolean isNewPlayerInfoRequested() {
        return this.mNewPlayerInfoRequested;
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    @VisibleForTesting
    public boolean isNowPlayingItemActive() {
        return this.mIsNowPlayingItemActive;
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public boolean isRecentlyPlayedDataFetchComplete() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("isRecentlyPlayedDataFetchComplete ");
        outline107.append(this.mIsRecentlyPlayedDataFetchComplete);
        Log.i(str, outline107.toString());
        return this.mIsRecentlyPlayedDataFetchComplete;
    }

    boolean isVoiceRequest() {
        return this.mIsVoiceRequest;
    }

    public /* synthetic */ void lambda$notifyOnUpdateNowPlayingCardListeners$1$EntertainmentInteractor(EntertainmentCardItem entertainmentCardItem, AlexaPlayerInfoState alexaPlayerInfoState) {
        for (EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener onUpdateNowPlayingCardListener : getOnUpdateNowPlayingCardListenerList()) {
            onUpdateNowPlayingCardListener.updateNowPlayingCard(entertainmentCardItem, alexaPlayerInfoState);
        }
    }

    public /* synthetic */ void lambda$notifyOnUpdateNowPlayingScreenListeners$2$EntertainmentInteractor(JSONObject jSONObject) {
        for (NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener onUpdateNowPlayingScreenListener : getOnUpdateNowPlayingScreenListenerList()) {
            onUpdateNowPlayingScreenListener.updateNowPlayingScreen(jSONObject);
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void loadEntertainmentListItems() {
        Log.i(TAG, "loadEntertainmentListItems");
        EntertainmentCardItem entertainmentCardItem = new EntertainmentCardItem();
        entertainmentCardItem.setIsLoading(true);
        for (int i = 0; i < 6; i++) {
            getEntertainmentDataRepository().addEntertainmentCardItem(entertainmentCardItem);
        }
        if (getEntertainmentLandingPagePresenter().mo358get() != null) {
            getEntertainmentLandingPagePresenter().mo358get().onEntertainmentDataListFetchComplete(6);
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void navigateToNowPlayingScreen() {
        Log.i(TAG, "navigateToNowPlayingScreen");
        this.mUpdateCardsWhenAudioStateChanges = true;
        RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline21(RoutingService.class);
        Preconditions.checkNotNull(routingService);
        if (routingService.match("drive-mode/entertainment/now-playing-screen") != null) {
            GeneratedOutlineSupport1.outline145(routingService, "drive-mode/entertainment/now-playing-screen");
        }
    }

    void next() {
        Log.i(TAG, "VOX Next");
        if (getAlexaServicesConnection() != null) {
            AlexaServicesApis.MediaPlaybackController.next(getAlexaServicesConnection());
        }
    }

    @VisibleForTesting
    void notifyOnUpdateNowPlayingCardListeners(final EntertainmentCardItem entertainmentCardItem, final AlexaPlayerInfoState alexaPlayerInfoState) {
        Log.i(TAG, "notifyOnUpdateNowPlayingCardListeners");
        if (entertainmentCardItem != null) {
            this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.interactor.-$$Lambda$EntertainmentInteractor$LaJvJsKruVHRtZsY6OG5u1Gkvds
                @Override // java.lang.Runnable
                public final void run() {
                    EntertainmentInteractor.this.lambda$notifyOnUpdateNowPlayingCardListeners$1$EntertainmentInteractor(entertainmentCardItem, alexaPlayerInfoState);
                }
            });
        }
    }

    @VisibleForTesting
    void notifyOnUpdateNowPlayingScreenListeners(final JSONObject jSONObject) {
        Log.i(TAG, "notifyOnUpdateNowPlayingScreenListeners");
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.interactor.-$$Lambda$EntertainmentInteractor$CTY-OkzaueXQDm1xLSUoakBqVpk
            @Override // java.lang.Runnable
            public final void run() {
                EntertainmentInteractor.this.lambda$notifyOnUpdateNowPlayingScreenListeners$2$EntertainmentInteractor(jSONObject);
            }
        });
    }

    @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListener
    public synchronized void onAudioItemStateChanged(AlexaPlayerInfoState alexaPlayerInfoState, String str, long j) {
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        Log.i(TAG, "onConnected");
        AlexaServices.Cards.registerPlayerInfoCardListener(getAlexaServicesConnection(), this);
        AlexaServicesApis.MediaPlaybackController.registerListener(getAlexaServicesConnection(), this);
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        Log.i(TAG, "onConnectingFailed");
        if (this.mAlexaServicesConnection == null || this.mAlexaConnectionRetryCount >= 2) {
            return;
        }
        Log.i(TAG, "Retry connecting to Alexa service");
        this.mAlexaConnectionRetryCount++;
        this.mAlexaServicesConnection.connect();
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        Log.i(TAG, "onDisconnected");
    }

    @Override // com.amazon.alexa.api.AlexaMediaPlaybackListener
    public void onMediaMetadata(@NonNull AlexaMediaPlaybackMetadata alexaMediaPlaybackMetadata) {
        Log.i(TAG, "onMediaMetadata");
        this.mIsNowPlayingItemActive = true;
        JSONObject createPlayerInfoFromMediaMetadata = createPlayerInfoFromMediaMetadata(alexaMediaPlaybackMetadata);
        if (createPlayerInfoFromMediaMetadata == null || isFlashBriefingProvider(createPlayerInfoFromMediaMetadata)) {
            return;
        }
        this.mMediaMetadata = alexaMediaPlaybackMetadata;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO, createPlayerInfoFromMediaMetadata);
            this.mCurrentPlayerInfo = jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.mNowPlayingEntertainmentCardItem = createPartialEntertainmentCardItem(createPlayerInfoFromMediaMetadata.toString());
        if (isMediaPlaying()) {
            notifyOnUpdateNowPlayingCardListeners(this.mNowPlayingEntertainmentCardItem, AlexaPlayerInfoState.PLAYING);
        } else {
            notifyOnUpdateNowPlayingCardListeners(this.mNowPlayingEntertainmentCardItem, AlexaPlayerInfoState.DONE);
        }
        this.mNowPlayingScreenInteractor.setCurrentPlayerInfo(createPlayerInfoFromMediaMetadata);
        notifyOnUpdateNowPlayingScreenListeners(createPlayerInfoFromMediaMetadata);
        this.mNowPlayingScreenInteractor.requestPlayerInfo();
    }

    @Override // com.amazon.alexa.api.AlexaMediaPlaybackListener
    public void onMediaPlaybackStateUpdate(@NonNull AlexaMediaPlaybackState alexaMediaPlaybackState) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onMediaPlaybackStateUpdate() | state: ");
        outline107.append(alexaMediaPlaybackState.getPlayerState());
        Log.i(str, outline107.toString());
        AlexaPlayerInfoState playerState = alexaMediaPlaybackState.getPlayerState();
        this.mNowPlayingScreenInteractor.updateAudioPlayerState(playerState);
        this.mIsMediaPlaying = playerState.equals(AlexaPlayerInfoState.PLAYING);
        if (getNowPlayingEntertainmentCardItem() != null) {
            Log.i(TAG, "Id matches");
            notifyOnUpdateNowPlayingCardListeners(getNowPlayingEntertainmentCardItem(), playerState);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009f A[Catch: JSONException -> 0x00cc, all -> 0x00e5, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:6:0x0025, B:10:0x004b, B:12:0x0051, B:14:0x006a, B:28:0x0099, B:29:0x009c, B:30:0x009f, B:32:0x00b3, B:19:0x0080, B:22:0x008a), top: B:42:0x0025, outer: #0 }] */
    @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void onReceivedPlayerInfoCard(java.lang.String r6, boolean r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = com.amazon.alexa.drive.entertainment.interactor.EntertainmentInteractor.TAG     // Catch: java.lang.Throwable -> Le5
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Le5
            r1.<init>()     // Catch: java.lang.Throwable -> Le5
            java.lang.String r2 = "onReceivedPlayerInfoCard fromVoiceRequest "
            r1.append(r2)     // Catch: java.lang.Throwable -> Le5
            r1.append(r7)     // Catch: java.lang.Throwable -> Le5
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Le5
            android.util.Log.i(r0, r1)     // Catch: java.lang.Throwable -> Le5
            r0 = 1
            if (r7 == 0) goto L25
            java.lang.String r7 = com.amazon.alexa.drive.entertainment.interactor.EntertainmentInteractor.TAG     // Catch: java.lang.Throwable -> Le5
            java.lang.String r1 = "setting voice request to true"
            android.util.Log.i(r7, r1)     // Catch: java.lang.Throwable -> Le5
            r5.mUpdateCardsWhenAudioStateChanges = r0     // Catch: java.lang.Throwable -> Le5
            r5.mIsVoiceRequest = r0     // Catch: java.lang.Throwable -> Le5
        L25:
            org.json.JSONObject r7 = r5.getPlayerInfoJson(r6)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            java.lang.String r1 = com.amazon.alexa.drive.entertainment.interactor.EntertainmentInteractor.TAG     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            r2.<init>()     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            java.lang.String r3 = "isFlashBriefingProvider "
            r2.append(r3)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            boolean r3 = r5.isFlashBriefingProvider(r7)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            r2.append(r3)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            java.lang.String r2 = r2.toString()     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            android.util.Log.i(r1, r2)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            boolean r1 = r5.isFlashBriefingProvider(r7)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            if (r1 != 0) goto L4b
            monitor-exit(r5)
            return
        L4b:
            boolean r1 = r5.isNowPlayingItemActive()     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            if (r1 != 0) goto L68
            r5.mIsNowPlayingItemActive = r0     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            r1.<init>()     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            java.lang.String r2 = "playerInfo"
            r1.put(r2, r7)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            java.lang.String r1 = r1.toString()     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            r5.mCurrentPlayerInfo = r1     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract$Interactor r1 = r5.mNowPlayingScreenInteractor     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            r1.setCurrentPlayerInfo(r7)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
        L68:
            if (r7 == 0) goto Le3
            java.lang.String r1 = "state"
            java.lang.String r1 = r7.getString(r1)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            r2 = -1
            int r3 = r1.hashCode()     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            r4 = -1446859902(0xffffffffa9c2ab82, float:-8.6450825E-14)
            if (r3 == r4) goto L8a
            r4 = 224418830(0xd605c0e, float:6.9136136E-31)
            if (r3 == r4) goto L80
            goto L94
        L80:
            java.lang.String r3 = "PLAYING"
            boolean r1 = r1.equals(r3)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            if (r1 == 0) goto L94
            r1 = 0
            goto L95
        L8a:
            java.lang.String r3 = "BUFFERING"
            boolean r1 = r1.equals(r3)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            if (r1 == 0) goto L94
            r1 = r0
            goto L95
        L94:
            r1 = r2
        L95:
            if (r1 == 0) goto L9f
            if (r1 == r0) goto L9c
            com.amazon.alexa.api.AlexaPlayerInfoState r6 = com.amazon.alexa.api.AlexaPlayerInfoState.DONE     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            goto Le3
        L9c:
            com.amazon.alexa.api.AlexaPlayerInfoState r6 = com.amazon.alexa.api.AlexaPlayerInfoState.BUFFERING     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            goto Le3
        L9f:
            com.amazon.alexa.api.AlexaPlayerInfoState r0 = com.amazon.alexa.api.AlexaPlayerInfoState.PLAYING     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            com.amazon.alexa.drive.entertainment.model.EntertainmentCardItem r6 = r5.createPartialEntertainmentCardItem(r6)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            r5.mNowPlayingEntertainmentCardItem = r6     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            r5.notifyOnUpdateNowPlayingCardListeners(r6, r0)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            r5.notifyOnUpdateNowPlayingScreenListeners(r7)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            org.json.JSONObject r6 = r5.getFlashBriefingPlayerInfo(r7)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            if (r6 == 0) goto Le3
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            r7.<init>()     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            java.lang.String r0 = "playerInfo"
            r7.put(r0, r6)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            r5.handleFlashBriefingPlayerInfo(r6)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            java.lang.String r7 = r7.toString()     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            r5.mCurrentPlayerInfo = r7     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract$Interactor r7 = r5.mNowPlayingScreenInteractor     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            r7.setCurrentPlayerInfo(r6)     // Catch: org.json.JSONException -> Lcc java.lang.Throwable -> Le5
            goto Le3
        Lcc:
            r6 = move-exception
            java.lang.String r7 = com.amazon.alexa.drive.entertainment.interactor.EntertainmentInteractor.TAG     // Catch: java.lang.Throwable -> Le5
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Le5
            r0.<init>()     // Catch: java.lang.Throwable -> Le5
            java.lang.String r1 = "onReceivedCard() | JSONException: "
            r0.append(r1)     // Catch: java.lang.Throwable -> Le5
            r0.append(r6)     // Catch: java.lang.Throwable -> Le5
            java.lang.String r6 = r0.toString()     // Catch: java.lang.Throwable -> Le5
            android.util.Log.e(r7, r6)     // Catch: java.lang.Throwable -> Le5
        Le3:
            monitor-exit(r5)
            return
        Le5:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.drive.entertainment.interactor.EntertainmentInteractor.onReceivedPlayerInfoCard(java.lang.String, boolean):void");
    }

    @VisibleForTesting
    void pause() {
        Log.i(TAG, "VOX Pause");
        if (getAlexaServicesConnection() != null) {
            AlexaServicesApis.MediaPlaybackController.pause(getAlexaServicesConnection());
        }
    }

    @VisibleForTesting
    void play() {
        Log.i(TAG, "VOX Play");
        if (getAlexaServicesConnection() != null) {
            AlexaServicesApis.MediaPlaybackController.play(getAlexaServicesConnection());
        }
    }

    void previous() {
        Log.i(TAG, "VOX Previous");
        if (getAlexaServicesConnection() != null) {
            AlexaServicesApis.MediaPlaybackController.previous(getAlexaServicesConnection());
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void removeOnUpdateNowPlayingCardListener(EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener onUpdateNowPlayingCardListener) {
        Log.i(TAG, "removeOnUpdateNowPlayingCardListener");
        getOnUpdateNowPlayingCardListenerList().remove(onUpdateNowPlayingCardListener);
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void removeOnUpdateNowPlayingScreenListener(NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener onUpdateNowPlayingScreenListener) {
        Log.i(TAG, "removeOnUpdateNowPlayingScreenListener");
        getOnUpdateNowPlayingScreenListenerList().remove(onUpdateNowPlayingScreenListener);
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void requestEntertainmentCards() {
        Log.i(TAG, "requestEntertainmentCards");
        requestEntertainmentJSON(EntertainmentConstants.ENT_HOME, "home", getEntertainmentAsyncTaskFactory().createFetchEntertainmentCardDataAsyncTask(getOnEntertainmentCardsFetchCompleteListener()));
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void requestEntertainmentCardsWhenReady() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("requestEntertainmentCardsWhenReady ");
        outline107.append(isMediaReferenceIdUpdated());
        Log.i(str, outline107.toString());
        if (isMediaReferenceIdUpdated()) {
            this.mIsMediaReferenceIdUpdated = false;
            requestEntertainmentCards();
        }
    }

    @VisibleForTesting
    void requestEntertainmentJSON(String str, String str2, FetchEntertainmentCardDataAsyncTask fetchEntertainmentCardDataAsyncTask) {
        Log.i(TAG, "requestEntertainmentJSON");
        fetchEntertainmentCardDataAsyncTask.execute(str, str2);
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void requestNext() {
        if (getNowPlayingEntertainmentCardItem() == null || !isMediaPlaying()) {
            return;
        }
        next();
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void requestNowPlayingCardItem() {
        Log.i(TAG, "requestNowPlayingCardItem");
        if (getNowPlayingEntertainmentCardItem() != null) {
            notifyOnUpdateNowPlayingCardListeners(getNowPlayingEntertainmentCardItem(), isMediaPlaying() ? AlexaPlayerInfoState.PLAYING : AlexaPlayerInfoState.DONE);
        } else {
            requestEntertainmentCards();
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void requestPause() {
        Log.i(TAG, "requestPause");
        if (getNowPlayingEntertainmentCardItem() == null || !isMediaPlaying()) {
            return;
        }
        pause();
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void requestPlay() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("requestPlay ");
        outline107.append(isNowPlayingItemActive());
        Log.i(str, outline107.toString());
        if (getNowPlayingEntertainmentCardItem() != null) {
            if (isNowPlayingItemActive()) {
                play();
            } else {
                requestPlaylistPlayback(getNowPlayingEntertainmentCardItem());
            }
        }
    }

    public void requestPlayerInfo() {
        Log.i(TAG, "requestPlayerInfo");
        getEntertainmentAsyncTaskFactory().createNowPlayingScreenInfoAsyncTask(getOnNowPlayingScreenInfoAsyncTaskListener()).execute(getDeviceType(), getDSN());
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void requestPlaylistPlayback(EntertainmentCardItem entertainmentCardItem) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("requestPlaylistPlayback ");
        outline107.append(entertainmentCardItem == null);
        Log.i(str, outline107.toString());
        this.mNowPlayingScreenInteractor.setCurrentPlayerInfo(createPlayerInfoFromEntertainmentCardItem(entertainmentCardItem));
        this.mNowPlayingEntertainmentCardItem = entertainmentCardItem;
        getEntertainmentAsyncTaskFactory().createPlaylistInitAsyncTask(getOnPlaylistInitTaskCompleteListener()).execute(entertainmentCardItem.getPlayUri(), entertainmentCardItem.getPlayPayload());
        navigateToNowPlayingScreen();
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor
    public void requestPrevious() {
        if (getNowPlayingEntertainmentCardItem() == null || !isMediaPlaying()) {
            return;
        }
        previous();
    }

    @VisibleForTesting
    boolean updateCardsWhenAudioStateChanges() {
        return this.mUpdateCardsWhenAudioStateChanges;
    }

    void updateEntertainmentItemList(List<EntertainmentCardItem> list) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("updateEntertainmentItemList ");
        outline107.append(list.size());
        Log.i(str, outline107.toString());
        getEntertainmentDataRepository().getEntertainmentCardList().clear();
        if (list.size() > 0) {
            EntertainmentCardItem entertainmentCardItem = list.get(0);
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("getCurrentPlayerInfo ");
            outline1072.append(getCurrentPlayerInfo());
            outline1072.toString();
            if (getCurrentPlayerInfo() != null && isNowPlayingItemActive()) {
                handlePlayerInfoUpdate(getCurrentPlayerInfo());
            } else {
                this.mNowPlayingScreenInteractor.setCurrentPlayerInfo(createPlayerInfoFromEntertainmentCardItem(entertainmentCardItem));
                this.mNowPlayingEntertainmentCardItem = entertainmentCardItem;
                if (isMediaPlaying()) {
                    notifyOnUpdateNowPlayingCardListeners(entertainmentCardItem, AlexaPlayerInfoState.PLAYING);
                } else {
                    notifyOnUpdateNowPlayingCardListeners(entertainmentCardItem, AlexaPlayerInfoState.DONE);
                }
            }
            for (int i = 0; i < Math.min(9, list.size()); i++) {
                getEntertainmentDataRepository().addEntertainmentCardItem(list.get(i));
            }
        }
    }
}
