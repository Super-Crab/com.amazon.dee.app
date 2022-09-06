package com.amazon.deecomms.messaging.util;

import android.media.MediaPlayer;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.media.audio.AudioContentManager;
import com.amazon.deecomms.messaging.model.client.ClientMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public final class AudioMessageUtils {
    public static final int INVALID_AUDIO_FILE = -1;
    private static final String LENOVO_BUILD_MANUFACTURER_NAME = "LENOVO";
    private static final String LENOVO_BUILD_MODEL_PREFIX = "XT";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AudioMessageUtils.class);
    private static final String MOTOROLA_BUILD_MANUFACTURER_NAME = "motorola";

    private AudioMessageUtils() {
    }

    @Nullable
    public static Map<String, Object> getAudioMetadataFromMessage(@NonNull ClientMessage clientMessage) {
        File localMsgAudioFile = AudioContentManager.getLocalMsgAudioFile(clientMessage.getClientID());
        if (localMsgAudioFile != null) {
            return getMetadataFromAudioFile(localMsgAudioFile);
        }
        return null;
    }

    public static int getMessageDuration(@NonNull File file) {
        MediaPlayer mediaPlayer;
        MediaPlayer mediaPlayer2 = null;
        try {
            try {
                mediaPlayer = new MediaPlayer();
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            mediaPlayer.setAudioStreamType(3);
            mediaPlayer.setDataSource(file.getAbsolutePath());
            mediaPlayer.prepare();
            int duration = mediaPlayer.getDuration();
            mediaPlayer.release();
            return duration;
        } catch (IOException e2) {
            e = e2;
            mediaPlayer2 = mediaPlayer;
            LOG.e("Received IOException when setting MediaPlayer - setDataSource.", e);
            if (mediaPlayer2 != null) {
                mediaPlayer2.release();
            }
            return -1;
        } catch (Throwable th2) {
            th = th2;
            mediaPlayer2 = mediaPlayer;
            if (mediaPlayer2 != null) {
                mediaPlayer2.release();
            }
            throw th;
        }
    }

    @Nullable
    public static Map<String, Object> getMetadataFromAudioFile(@NonNull File file) {
        int messageDuration = getMessageDuration(file);
        if (messageDuration == -1) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("size", Long.valueOf(file.length()));
        hashMap.put("duration", Integer.valueOf(messageDuration));
        return hashMap;
    }

    public static boolean requiresMonoChannelRecording() {
        return TextUtils.equals(Build.MANUFACTURER, MOTOROLA_BUILD_MANUFACTURER_NAME) || (TextUtils.equals(Build.MANUFACTURER, LENOVO_BUILD_MANUFACTURER_NAME) && Build.MODEL.startsWith(LENOVO_BUILD_MODEL_PREFIX));
    }
}
