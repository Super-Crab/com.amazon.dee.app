package com.amazon.deecomms.calling.incallexperiences.storytime.contracts;
/* loaded from: classes12.dex */
public interface StoryTimeButtonViewContract {
    void bindPresenterCallback(StoryTimeButtonPresenterContract storyTimeButtonPresenterContract);

    void hideFloatingStoryTimeButton();

    void showFloatingStoryTimeButton();

    void showStoryTimeMessage(int i);

    void startStoryTime(boolean z);
}
