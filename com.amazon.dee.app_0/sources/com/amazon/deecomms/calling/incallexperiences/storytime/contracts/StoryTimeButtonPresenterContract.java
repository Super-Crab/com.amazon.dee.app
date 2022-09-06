package com.amazon.deecomms.calling.incallexperiences.storytime.contracts;

import android.view.MotionEvent;
import com.amazon.deecomms.util.ChangeOrientationListener;
/* loaded from: classes12.dex */
public interface StoryTimeButtonPresenterContract {
    void exitStoryTime();

    boolean isStoryTimeEnabled();

    boolean processStoryTimeTouchEvents(MotionEvent motionEvent);

    void releaseStoryTimeResources();

    void setChangeOrientationListener(ChangeOrientationListener changeOrientationListener);

    void startStoryTime();
}
