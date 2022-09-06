package com.amazon.deecomms.services.conversation;

import android.view.Menu;
import androidx.annotation.MenuRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
/* loaded from: classes12.dex */
public interface ConversationUIDelegate {
    boolean isConversationUIActive();

    boolean isFullScreen();

    boolean isReactNativeCommsActive();

    void resetHeaderMenu();

    Menu setHeaderMenu(@MenuRes int i, Toolbar.OnMenuItemClickListener onMenuItemClickListener);

    void setHeaderTitle(@Nullable CharSequence charSequence);

    void setHeadersAndTabVisible(boolean z, boolean z2);

    void setMessageIndicatorVisible(boolean z);

    void setUserName(String str);
}
