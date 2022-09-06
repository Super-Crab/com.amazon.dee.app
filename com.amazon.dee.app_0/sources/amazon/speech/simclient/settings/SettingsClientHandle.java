package amazon.speech.simclient.settings;

import amazon.speech.simclient.common.SimClientServiceHandle;
import android.content.Context;
/* loaded from: classes.dex */
public class SettingsClientHandle extends SimClientServiceHandle<SettingsClient> {
    public SettingsClientHandle() {
        super(SettingsClient.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.speech.simclient.common.SimClientServiceHandle
    public boolean serviceExists(Context context) {
        return SettingsClient.isSettingsServiceAvailable(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.speech.simclient.common.SimClientServiceHandle
    /* renamed from: createClient  reason: collision with other method in class */
    public SettingsClient mo50createClient(Context context) {
        return new SettingsClient(context);
    }
}
