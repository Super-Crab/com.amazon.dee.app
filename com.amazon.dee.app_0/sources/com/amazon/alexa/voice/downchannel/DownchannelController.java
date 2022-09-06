package com.amazon.alexa.voice.downchannel;

import android.content.Context;
import com.amazon.alexa.AlexaService;
/* loaded from: classes11.dex */
class DownchannelController {
    private final Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DownchannelController(Context context) {
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void wakeUp() {
        AlexaService.wakeUp(this.context);
    }
}
