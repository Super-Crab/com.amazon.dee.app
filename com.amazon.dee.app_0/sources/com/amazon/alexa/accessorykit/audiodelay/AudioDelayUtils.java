package com.amazon.alexa.accessorykit.audiodelay;

import androidx.annotation.NonNull;
import com.facebook.react.bridge.ReactApplicationContext;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class AudioDelayUtils {
    private static final String AUDIO_DELAY_DIRECTORY = "accessory_multi-turn_delay";
    private static final String AUDIO_DELAY_FILENAME = "delay.json";

    private AudioDelayUtils() {
        throw new IllegalStateException("Audio delay utility class");
    }

    private static File getAudioDelayConfigFileDir(@NonNull ReactApplicationContext reactApplicationContext) {
        File file = new File(reactApplicationContext.getFilesDir(), AUDIO_DELAY_DIRECTORY);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static synchronized File getAudioDelayConfigFileInstance(@NonNull ReactApplicationContext reactApplicationContext) {
        File file;
        synchronized (AudioDelayUtils.class) {
            file = new File(getAudioDelayConfigFileDir(reactApplicationContext), AUDIO_DELAY_FILENAME);
        }
        return file;
    }

    public static synchronized void writeAudioDelayToConfigFile(int i, @NonNull ReactApplicationContext reactApplicationContext) throws JsonIOException, IOException {
        synchronized (AudioDelayUtils.class) {
            FileWriter fileWriter = new FileWriter(getAudioDelayConfigFileInstance(reactApplicationContext));
            new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(new AudioDelayConfig(i), fileWriter);
            fileWriter.flush();
            fileWriter.close();
        }
    }
}
