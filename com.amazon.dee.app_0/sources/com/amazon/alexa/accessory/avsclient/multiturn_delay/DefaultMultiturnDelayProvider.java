package com.amazon.alexa.accessory.avsclient.multiturn_delay;

import android.content.Context;
import android.os.FileObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class DefaultMultiturnDelayProvider extends FileObserver implements MultiturnDelayProvider {
    private static final int DEFAULT = 0;
    private static final String DIRECTORY = "/accessory_multi-turn_delay/";
    private static final String ECHO_AUTO = "A303PJF6ISQ7IC";
    private static final String FILE = "delay.json";
    public static final String G = "A13W6HQIHKEN3Z";
    private static final String JSON_KEY = "delay";
    private static final String M = "A1388YOZ88W373";
    private static final Set<String> MULTITURN_DELAY_DEVICE_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList("A303PJF6ISQ7IC", "A1388YOZ88W373", "A13W6HQIHKEN3Z")));
    private static final int SAFE_MAX = 3000;
    private static final int SAFE_MIN = 0;
    private final String baseDir;
    private final String fullPath;
    private int multiturnDelay;

    public DefaultMultiturnDelayProvider(@NonNull Context context) {
        super(context.getFilesDir() + DIRECTORY, 520);
        this.baseDir = context.getFilesDir().toString() + DIRECTORY;
        this.fullPath = GeneratedOutlineSupport1.outline91(new StringBuilder(), this.baseDir, FILE);
        this.multiturnDelay = 0;
    }

    private void updateFromFile() {
        Logger.d("Updating multi-turn delay value from %s", this.fullPath);
        try {
            this.multiturnDelay = new JSONObject(IOUtils.fileToString(new File(this.fullPath))).getInt(JSON_KEY);
        } catch (IOException unused) {
            Logger.e("Error reading multi-turn delay value from file, setting value to default.");
            this.multiturnDelay = 0;
        } catch (JSONException unused2) {
            Logger.e("Error parsing multi-turn delay file, setting value to default.");
            this.multiturnDelay = 0;
        }
        int i = this.multiturnDelay;
        if (i < 0 || i > 3000) {
            Logger.d("A multi-turn delay of %d is out of safe bounds, setting value to default.", Integer.valueOf(this.multiturnDelay));
            this.multiturnDelay = 0;
        }
    }

    @Override // com.amazon.alexa.accessory.avsclient.multiturn_delay.MultiturnDelayProvider
    public void activate() {
        Logger.d("Activating multi-turn delay provider.");
        File file = new File(this.baseDir);
        if (!file.exists() && !file.mkdirs()) {
            Logger.e("Failed to create multi-turn delay config directory");
            return;
        }
        if (new File(this.fullPath).exists()) {
            updateFromFile();
        }
        startWatching();
    }

    @Override // com.amazon.alexa.accessory.avsclient.multiturn_delay.MultiturnDelayProvider
    public void deactivate() {
        Logger.d("Deactivating multi-turn delay provider.");
        stopWatching();
    }

    @Override // com.amazon.alexa.accessory.avsclient.multiturn_delay.MultiturnDelayProvider
    public int getMultiturnDelay(String str) {
        if (MULTITURN_DELAY_DEVICE_TYPES.contains(str)) {
            return this.multiturnDelay;
        }
        return 0;
    }

    @Override // android.os.FileObserver
    public void onEvent(int i, @Nullable String str) {
        if (!FILE.equals(str)) {
            return;
        }
        if (i == 512) {
            this.multiturnDelay = 0;
        } else {
            updateFromFile();
        }
    }
}
