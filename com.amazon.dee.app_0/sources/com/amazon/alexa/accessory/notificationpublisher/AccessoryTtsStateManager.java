package com.amazon.alexa.accessory.notificationpublisher;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaMetadataRetriever;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateManager;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public final class AccessoryTtsStateManager {
    private static final String ASSETS_DIRECTORY = "/assets/";
    private static final String TAG = "AccessoryTtsStateManager";
    private static AccessoryTtsStateManager instance;
    private Context context;
    private MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ByteConversionHelper {
        private ByteConversionHelper() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int encode(Integer num, long j) {
            byte[] byteArray = toByteArray((int) j);
            int i = toInt(new byte[]{num.byteValue(), byteArray[1], byteArray[2], byteArray[3]});
            String str = AccessoryTtsStateManager.TAG;
            Log.d(str, "stateValue: " + num + ", durationInMillis: " + j + ", encodedInt: " + i);
            return i;
        }

        private static byte[] toByteArray(int i) {
            return ByteBuffer.allocate(4).putInt(i).array();
        }

        private static int toInt(byte[] bArr) {
            int i = 0;
            for (int i2 = 0; i2 < 4; i2++) {
                i = (i << 8) | (bArr[i2] & 255);
            }
            return i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum TtsState {
        STATE_IDLE(0),
        STATE_EARCON_DELAY(1),
        STATE_ANNOUNCEMENT(2),
        STATE_ANNOUNCEMENT_DELAY(3),
        STATE_CONTENT(4),
        STATE_CONTENT_DELAY(5),
        STATE_INSTRUCTIONAL_AUDIO(6),
        STATE_EDUCATIONAL_AUDIO(7),
        STATE_REPLY(8),
        STATE_REPLY_DELAY(9),
        STATE_REPLY_READBACK(10),
        STATE_REPLY_READBACK_DELAY(11);
        
        private int value;

        TtsState(int i) {
            this.value = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getValue() {
            return this.value;
        }
    }

    private AccessoryTtsStateManager(@NonNull Context context) {
        this.context = context;
    }

    private StateOuterClass.State buildState(int i, long j) {
        return StateOuterClass.State.newBuilder().setFeature(StateFeature.FOCUS_FILTER_MESSAGE_STATE.toInteger()).setInteger(ByteConversionHelper.encode(Integer.valueOf(i), j)).mo10084build();
    }

    public static synchronized AccessoryTtsStateManager getInstance() {
        AccessoryTtsStateManager accessoryTtsStateManager;
        synchronized (AccessoryTtsStateManager.class) {
            if (instance != null) {
                accessoryTtsStateManager = instance;
            } else {
                Log.e(TAG, "getInstance called before calling init(). Throw an exception.");
                throw new IllegalStateException("AccessoryTtsStateManager is not initialized, init() must be called before calling this method.");
            }
        }
        return accessoryTtsStateManager;
    }

    private long getPlaybackFileDuration(String str) throws IllegalArgumentException, IOException {
        AssetFileDescriptor assetFileDescriptor;
        String str2 = TAG;
        Log.d(str2, "getPlaybackFileDuration: filePath: " + str);
        if (str.startsWith("/assets/")) {
            assetFileDescriptor = this.context.getAssets().openFd(str.substring(8));
            this.mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        } else {
            this.mediaMetadataRetriever.setDataSource(str);
            assetFileDescriptor = null;
        }
        String extractMetadata = this.mediaMetadataRetriever.extractMetadata(9);
        GeneratedOutlineSupport1.outline165("getPlaybackFileDuration: duration: ", extractMetadata, TAG);
        if (assetFileDescriptor != null) {
            assetFileDescriptor.close();
        }
        return Long.parseLong(extractMetadata);
    }

    public static synchronized void init(@NonNull Context context) throws IllegalArgumentException {
        synchronized (AccessoryTtsStateManager.class) {
            if (instance == null) {
                Log.d(TAG, "First time init");
                if (context != null) {
                    instance = new AccessoryTtsStateManager(context);
                } else {
                    Log.e(TAG, "Context is null, throw exception");
                    throw new IllegalArgumentException("Cannot initialize AccessoryTtsStateManager with a null Context.");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$updateTtsState$1(Accessory accessory, TtsState ttsState, long j, Throwable th) throws Throwable {
        Log.w(TAG, String.format(Locale.US, "accessory: %s updateTtsState error: %s while updating setState: %s, duration: %s", accessory.getName(), th.getMessage(), ttsState, Long.valueOf(j)));
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.UPDATE_TTS_STATE_FAILURE);
    }

    public static synchronized void releaseInstance() {
        synchronized (AccessoryTtsStateManager.class) {
            instance = null;
        }
    }

    private void updateTtsState(final TtsState ttsState, final long j) {
        List<Accessory> connectedAccessoryList = AccessoryProvider.getConnectedAccessoryList();
        if (connectedAccessoryList != null && connectedAccessoryList.size() != 0) {
            for (final Accessory accessory : connectedAccessoryList) {
                DependencyProvider.getClientAccessories().getAccessorySession(accessory.getAddress()).getStateRepository().set(buildState(ttsState.getValue(), j)).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action() { // from class: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$AccessoryTtsStateManager$zN2_QYThrqMQSghsp-MNc689eco
                    @Override // io.reactivex.rxjava3.functions.Action
                    public final void run() {
                        Log.i(AccessoryTtsStateManager.TAG, String.format(Locale.US, "accessory: %s updateTtsState setState: %s, duration: %s", Accessory.this.getName(), ttsState, Long.valueOf(j)));
                    }
                }, new Consumer() { // from class: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$AccessoryTtsStateManager$nXxB-WgFP6wc4h8l4Nkmu6Nzrzg
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Object obj) {
                        AccessoryTtsStateManager.lambda$updateTtsState$1(Accessory.this, ttsState, j, (Throwable) obj);
                    }
                });
            }
            return;
        }
        String str = TAG;
        Log.d(str, "no connected accessory found to send state: " + ttsState + " withtime duration: " + j);
    }

    public void updateAccessoryTtsState(String str, String str2) {
        Preconditions.notNull(str, "State is null");
        Preconditions.notNull(str2, "filePath is null");
        Log.d(TAG, "informAccessoryTtsState state: " + str);
        if (!FeatureAccessChecker.hasOtgVipFilterAccess()) {
            Log.d(TAG, "OTG VIP Filter weblab is disabled, not sending any TTS state to accessory");
            return;
        }
        char c = 65535;
        try {
            switch (str.hashCode()) {
                case -1934380307:
                    if (str.equals(StateManager.STATE_REPLY_READ_BACK)) {
                        c = 3;
                        break;
                    }
                    break;
                case -1217121653:
                    if (str.equals(StateManager.STATE_CONTENT)) {
                        c = 1;
                        break;
                    }
                    break;
                case -336637355:
                    if (str.equals(StateManager.STATE_ANNOUNCEMENT)) {
                        c = 0;
                        break;
                    }
                    break;
                case 243264578:
                    if (str.equals(StateManager.STATE_INSTRUCTIONAL_AUDIO)) {
                        c = 2;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                updateTtsState(TtsState.STATE_ANNOUNCEMENT, getPlaybackFileDuration(str2));
            } else if (c == 1) {
                updateTtsState(TtsState.STATE_CONTENT, getPlaybackFileDuration(str2));
            } else if (c == 2) {
                updateTtsState(TtsState.STATE_INSTRUCTIONAL_AUDIO, getPlaybackFileDuration(str2));
            } else if (c == 3) {
                updateTtsState(TtsState.STATE_REPLY_READBACK, getPlaybackFileDuration(str2));
            } else {
                throw new UnsupportedOperationException("State: " + str + " is notcurrently supported in informAccessoryTtsState");
            }
        } catch (IOException | IllegalArgumentException e) {
            String str3 = TAG;
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("An exception occurred in getting file duration for state: ", str, " with message: ");
            outline115.append(e.getMessage());
            Log.e(str3, outline115.toString());
        }
    }

    /* renamed from: clone */
    public AccessoryTtsStateManager m326clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }

    public void updateAccessoryTtsState(String str, long j) {
        Preconditions.notNull(str, "State is null");
        Log.d(TAG, "informAccessoryTtsState state: " + str + " duration: " + j);
        if (!FeatureAccessChecker.hasOtgVipFilterAccess()) {
            Log.d(TAG, "OTG VIP Filter weblab is disabled, not sending any TTS state to accessory");
            return;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1492909393:
                if (str.equals(StateManager.STATE_CONTENT_DELAY)) {
                    c = 1;
                    break;
                }
                break;
            case -233397511:
                if (str.equals(StateManager.STATE_ANNOUNCEMENT_DELAY)) {
                    c = 0;
                    break;
                }
                break;
            case -67663648:
                if (str.equals(StateManager.STATE_REPLY_DELAY)) {
                    c = 3;
                    break;
                }
                break;
            case 286737122:
                if (str.equals(StateManager.STATE_IDLE)) {
                    c = 2;
                    break;
                }
                break;
        }
        if (c == 0) {
            updateTtsState(TtsState.STATE_ANNOUNCEMENT_DELAY, j);
        } else if (c == 1) {
            updateTtsState(TtsState.STATE_CONTENT_DELAY, j);
        } else if (c == 2) {
            updateTtsState(TtsState.STATE_IDLE, j);
        } else if (c == 3) {
            updateTtsState(TtsState.STATE_REPLY_DELAY, j);
        } else {
            throw new UnsupportedOperationException(GeneratedOutlineSupport1.outline75("State: ", str, " is notcurrently supported in informAccessoryTtsState"));
        }
    }

    public void updateAccessoryTtsState(String str) {
        updateAccessoryTtsState(StateManager.getInstance().getCurrentState(), str);
    }

    public void updateAccessoryTtsState(long j) {
        updateAccessoryTtsState(StateManager.getInstance().getCurrentState(), j);
    }
}
