package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.display.window.AutoValue_Window;
import com.amazon.alexa.client.alexaservice.display.window.AutoValue_WindowConfiguration;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import java.util.HashSet;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AlexaDisplayWindowComponentStateProvider.java */
@Singleton
/* renamed from: com.amazon.alexa.wXy  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0237wXy extends Bwo {
    public final rqw BIo;

    @Inject
    public C0237wXy(rqw rqwVar) {
        super(AvsApiConstants.Alexa.Display.Window.zZm, AvsApiConstants.Alexa.Display.Window.ComponentStates.WindowState.zZm);
        this.BIo = rqwVar;
    }

    @Override // com.amazon.alexa.dRG
    public ComponentState getState() {
        AutoValue_Window autoValue_Window = new AutoValue_Window("app_window", "app_window_template", null, new AutoValue_WindowConfiguration(this.BIo.zQM() ? "auto_mode" : "mobile_mode", "fullscreen"));
        HashSet hashSet = new HashSet();
        hashSet.add(autoValue_Window);
        return ComponentState.create(this.zZm, Bsa.zZm("app_window", hashSet));
    }
}
