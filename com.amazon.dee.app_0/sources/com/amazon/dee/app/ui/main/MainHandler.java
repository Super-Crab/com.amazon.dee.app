package com.amazon.dee.app.ui.main;

import android.view.KeyEvent;
/* loaded from: classes12.dex */
public interface MainHandler {
    void onBackClicked();

    void onHeaderClicked();

    void onHelpAndFeedbackClicked();

    void onIngressClicked();

    void onMenuClicked();

    boolean onMenuKeyEvent(int i, KeyEvent keyEvent);

    void onTabChannelsDeviceClicked();

    void onTabChannelsEntertainmentClicked();

    void onTabChannelsHomeClicked();

    void onTabConversationsClicked();

    void onTabNavMenuClicked();

    void onTabNowPlayingClicked();
}
