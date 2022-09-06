package com.amazon.alexa.voice.ui.player;

import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface TrackInformationModel extends Parcelable {
    @Nullable
    String getArtImageUrl();

    @Nullable
    String getAudioItemId();

    CharSequence getDescription();

    CharSequence getDetails();

    int getMaxPosition();

    @Nullable
    String getProviderIconId();

    @Nullable
    CharSequence getProviderName();

    CharSequence getTitle();

    CharSequence getTotalTime();

    boolean isSeekable();
}
