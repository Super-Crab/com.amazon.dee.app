package com.amazon.alexa.cantilever;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.cantilever.utils.FileUtils;
import com.amazon.alexa.logging.Lib;
import com.amazon.alexa.logging.Tag;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class HelpConfigService {
    private static final String CONFIG_FILE_NAME = "cantilever-config.json";
    private static final String URL_KEY = "urls";
    private final Context context;
    private static final Tag TAG = LogConfig.TAGGER.tag(HelpConfigService.class);
    private static boolean isLoaded = false;
    private static Map<String, String> urlConfig = new HashMap();

    public HelpConfigService(Context context) {
        this.context = context;
    }

    @VisibleForTesting
    static void resetForTest() {
        isLoaded = false;
        urlConfig = new HashMap();
    }

    public Map<String, String> getURLConfig() {
        validateConfig();
        return urlConfig;
    }

    @VisibleForTesting
    void loadConfig() {
        urlConfig = (Map) ((HashMap) new Gson().fromJson(FileUtils.readFile(this.context, CONFIG_FILE_NAME), (Class<Object>) HashMap.class)).get(URL_KEY);
    }

    @VisibleForTesting
    void validateConfig() {
        if (isLoaded) {
            return;
        }
        isLoaded = true;
        try {
            loadConfig();
        } catch (Exception e) {
            Lib.Log.e(TAG, "Failed to load Help Config", e);
        }
    }
}
