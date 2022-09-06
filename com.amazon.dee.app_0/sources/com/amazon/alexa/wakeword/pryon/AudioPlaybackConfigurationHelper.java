package com.amazon.alexa.wakeword.pryon;

import android.media.AudioManager;
import android.media.AudioPlaybackConfiguration;
import android.os.Build;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.amazon.pryon.android.asr.PryonLite5000;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes11.dex */
public class AudioPlaybackConfigurationHelper {
    private static final String TAG = "AudioPlaybackConfigurationHelper";
    @VisibleForTesting
    static final Set<PryonLite5000.ClientProperty> CLIENT_PROPERTIES = Collections.unmodifiableSet(new HashSet(Arrays.asList(PryonLite5000.ClientProperty.AUDIO_PLAYBACK, PryonLite5000.ClientProperty.ALARM_STATE, PryonLite5000.ClientProperty.MEDIA_PLAYER_STATE, PryonLite5000.ClientProperty.EARCON_PLAYER_STATE, PryonLite5000.ClientProperty.TTS_PLAYER_STATE)));
    private static final Map<Integer, List<PryonLite5000.ClientProperty>> AUDIO_ATTRIBUTES_TO_CLIENT_PROPERTIES = new HashMap();

    /* loaded from: classes11.dex */
    private static class InstanceHolder {
        private static final AudioPlaybackConfigurationHelper INSTANCE = new AudioPlaybackConfigurationHelper();

        private InstanceHolder() {
        }
    }

    static {
        addAttributeToClientProperties(1, PryonLite5000.ClientProperty.MEDIA_PLAYER_STATE);
        addAttributeToClientProperties(16);
        addAttributeToClientProperties(11, PryonLite5000.ClientProperty.TTS_PLAYER_STATE);
        addAttributeToClientProperties(4, PryonLite5000.ClientProperty.ALARM_STATE);
        addAttributeToClientProperties(6, PryonLite5000.ClientProperty.ALARM_STATE);
        addAttributeToClientProperties(3, PryonLite5000.ClientProperty.ALARM_STATE);
        addAttributeToClientProperties(14);
        addAttributeToClientProperties(5, PryonLite5000.ClientProperty.EARCON_PLAYER_STATE);
        addAttributeToClientProperties(10, PryonLite5000.ClientProperty.EARCON_PLAYER_STATE);
        addAttributeToClientProperties(2);
        addAttributeToClientProperties(13, PryonLite5000.ClientProperty.EARCON_PLAYER_STATE);
        addAttributeToClientProperties(7, PryonLite5000.ClientProperty.EARCON_PLAYER_STATE);
        addAttributeToClientProperties(8, PryonLite5000.ClientProperty.EARCON_PLAYER_STATE);
        addAttributeToClientProperties(9, PryonLite5000.ClientProperty.EARCON_PLAYER_STATE);
    }

    private static void addAttributeToClientProperties(Integer num) {
        addAttributeToClientProperties(num, null);
    }

    public static AudioPlaybackConfigurationHelper getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private void initClientProperty(Map<PryonLite5000.ClientProperty, Integer> map, int i) {
        for (PryonLite5000.ClientProperty clientProperty : CLIENT_PROPERTIES) {
            map.put(clientProperty, Integer.valueOf(i));
        }
    }

    private void setClientProperty(Map<PryonLite5000.ClientProperty, Integer> map, PryonLite5000.ClientProperty clientProperty) {
        map.put(clientProperty, 1);
    }

    @RequiresApi(api = 26)
    public Map<PryonLite5000.ClientProperty, Integer> getClientPropertiesMap(AudioManager audioManager) {
        HashMap hashMap = new HashMap();
        int i = Build.VERSION.SDK_INT;
        List<AudioPlaybackConfiguration> activePlaybackConfigurations = audioManager.getActivePlaybackConfigurations();
        if (activePlaybackConfigurations.isEmpty()) {
            Log.i(TAG, "No audio being detected");
            initClientProperty(hashMap, 0);
            return Collections.unmodifiableMap(hashMap);
        } else if (activePlaybackConfigurations.size() == 1 && activePlaybackConfigurations.get(0).getAudioAttributes().getUsage() == 0) {
            Log.i(TAG, "Attribute COMMON_UNKNOWN is detected");
            initClientProperty(hashMap, -1);
            return Collections.unmodifiableMap(hashMap);
        } else {
            initClientProperty(hashMap, 0);
            for (AudioPlaybackConfiguration audioPlaybackConfiguration : activePlaybackConfigurations) {
                int usage = audioPlaybackConfiguration.getAudioAttributes().getUsage();
                Log.i(TAG, String.format("Audio attribute %d detected", Integer.valueOf(usage)));
                if (AUDIO_ATTRIBUTES_TO_CLIENT_PROPERTIES.containsKey(Integer.valueOf(usage))) {
                    for (PryonLite5000.ClientProperty clientProperty : AUDIO_ATTRIBUTES_TO_CLIENT_PROPERTIES.get(Integer.valueOf(usage))) {
                        String str = TAG;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Update client property with group Id ");
                        outline107.append(clientProperty.groupId);
                        outline107.append(" and property Id ");
                        outline107.append(clientProperty.propertyId);
                        Log.i(str, outline107.toString());
                        setClientProperty(hashMap, clientProperty);
                    }
                }
            }
            return Collections.unmodifiableMap(hashMap);
        }
    }

    private AudioPlaybackConfigurationHelper() {
    }

    private static void addAttributeToClientProperties(Integer num, @Nullable PryonLite5000.ClientProperty clientProperty) {
        ArrayList arrayList = new ArrayList(Arrays.asList(PryonLite5000.ClientProperty.AUDIO_PLAYBACK));
        if (clientProperty != null) {
            arrayList.add(clientProperty);
        }
        AUDIO_ATTRIBUTES_TO_CLIENT_PROPERTIES.put(num, arrayList);
    }
}
