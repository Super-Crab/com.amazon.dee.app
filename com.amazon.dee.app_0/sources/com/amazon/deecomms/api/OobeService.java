package com.amazon.deecomms.api;

import android.app.Activity;
import com.amazon.deecomms.api.navigation.CommsView;
/* loaded from: classes12.dex */
public interface OobeService {
    void cleanUpOobe();

    boolean getCommsOobeJustFinished();

    boolean getSkippedCommsOobe();

    boolean hasSelectedProfile();

    boolean isCommsOoobeCompleted();

    void routeUserToOobe(Activity activity, CommsView commsView);

    void sendOOBECompletionEvent();

    void setCommsOobeInProgress(boolean z);

    void setCommsOobeJustFinished(boolean z);

    void setSkippedCommsOobe(boolean z);

    boolean shouldShowCommsOOBEForUser();
}
