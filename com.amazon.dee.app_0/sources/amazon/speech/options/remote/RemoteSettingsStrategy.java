package amazon.speech.options.remote;

import amazon.speech.options.remote.RemoteSettingsManager;
import org.json.JSONObject;
/* loaded from: classes.dex */
public interface RemoteSettingsStrategy {
    void destroy();

    JSONObject getRemoteSettingsObj();

    void init(RemoteSettingsManager.RemoteSettingsListener remoteSettingsListener);

    void sync();
}
