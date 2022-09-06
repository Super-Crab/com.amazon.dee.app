package com.amazon.deecomms.services;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.amazon.alexa.routing.api.RouteContext;
/* loaded from: classes12.dex */
public interface ConversationUIService {
    void handleAudioMessageRecordingInterruption();

    boolean isContactListAtTopOfBackstack(RouteContext[] routeContextArr);

    void start(FragmentManager fragmentManager, Fragment fragment);

    void stop();
}
