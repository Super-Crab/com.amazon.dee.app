package amazon.speech.options.remote;

import amazon.speech.options.remote.RemoteSettingsManager;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class NullStrategy implements RemoteSettingsStrategy {
    @Override // amazon.speech.options.remote.RemoteSettingsStrategy
    public void destroy() {
    }

    @Override // amazon.speech.options.remote.RemoteSettingsStrategy
    public JSONObject getRemoteSettingsObj() {
        return null;
    }

    @Override // amazon.speech.options.remote.RemoteSettingsStrategy
    public void init(RemoteSettingsManager.RemoteSettingsListener remoteSettingsListener) {
    }

    @Override // amazon.speech.options.remote.RemoteSettingsStrategy
    public void sync() {
    }
}
