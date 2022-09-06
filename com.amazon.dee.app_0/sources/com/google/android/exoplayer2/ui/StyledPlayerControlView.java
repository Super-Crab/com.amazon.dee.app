package com.google.android.exoplayer2.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.DefaultControlDispatcher;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.RepeatModeUtil;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes2.dex */
public class StyledPlayerControlView extends FrameLayout {
    public static final int DEFAULT_REPEAT_TOGGLE_MODES = 0;
    public static final int DEFAULT_SHOW_TIMEOUT_MS = 5000;
    public static final int DEFAULT_TIME_BAR_MIN_UPDATE_INTERVAL_MS = 200;
    private static final int MAX_UPDATE_INTERVAL_MS = 1000;
    public static final int MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR = 100;
    private static final int SETTINGS_AUDIO_TRACK_SELECTION_POSITION = 1;
    private static final int SETTINGS_PLAYBACK_SPEED_POSITION = 0;
    private long[] adGroupTimesMs;
    @Nullable
    private View audioTrackButton;
    private TrackSelectionAdapter audioTrackSelectionAdapter;
    private final float buttonAlphaDisabled;
    private final float buttonAlphaEnabled;
    private final ComponentListener componentListener;
    private ControlDispatcher controlDispatcher;
    private StyledPlayerControlViewLayoutManager controlViewLayoutManager;
    private long currentWindowOffset;
    @Nullable
    private final TextView durationView;
    private long[] extraAdGroupTimesMs;
    private boolean[] extraPlayedAdGroups;
    @Nullable
    private final View fastForwardButton;
    @Nullable
    private final TextView fastForwardButtonTextView;
    private long fastForwardMs;
    private final StringBuilder formatBuilder;
    private final Formatter formatter;
    @Nullable
    private ImageView fullScreenButton;
    private final String fullScreenEnterContentDescription;
    private final Drawable fullScreenEnterDrawable;
    private final String fullScreenExitContentDescription;
    private final Drawable fullScreenExitDrawable;
    private boolean isAttachedToWindow;
    private boolean isFullScreen;
    @Nullable
    private ImageView minimalFullScreenButton;
    private boolean multiWindowTimeBar;
    private boolean needToHideBars;
    @Nullable
    private final View nextButton;
    @Nullable
    private OnFullScreenModeChangedListener onFullScreenModeChangedListener;
    private final Timeline.Period period;
    @Nullable
    private final View playPauseButton;
    @Nullable
    private PlaybackPreparer playbackPreparer;
    private PlaybackSpeedAdapter playbackSpeedAdapter;
    @Nullable
    private View playbackSpeedButton;
    private boolean[] playedAdGroups;
    @Nullable
    private Player player;
    @Nullable
    private final TextView positionView;
    @Nullable
    private final View previousButton;
    @Nullable
    private ProgressUpdateListener progressUpdateListener;
    private final String repeatAllButtonContentDescription;
    private final Drawable repeatAllButtonDrawable;
    private final String repeatOffButtonContentDescription;
    private final Drawable repeatOffButtonDrawable;
    private final String repeatOneButtonContentDescription;
    private final Drawable repeatOneButtonDrawable;
    @Nullable
    private final ImageView repeatToggleButton;
    private int repeatToggleModes;
    private Resources resources;
    @Nullable
    private final View rewindButton;
    @Nullable
    private final TextView rewindButtonTextView;
    private long rewindMs;
    private boolean scrubbing;
    private SettingsAdapter settingsAdapter;
    @Nullable
    private View settingsButton;
    private RecyclerView settingsView;
    private PopupWindow settingsWindow;
    private int settingsWindowMargin;
    private boolean showMultiWindowTimeBar;
    private int showTimeoutMs;
    @Nullable
    private final ImageView shuffleButton;
    private final Drawable shuffleOffButtonDrawable;
    private final String shuffleOffContentDescription;
    private final Drawable shuffleOnButtonDrawable;
    private final String shuffleOnContentDescription;
    @Nullable
    private ImageView subtitleButton;
    private final Drawable subtitleOffButtonDrawable;
    private final String subtitleOffContentDescription;
    private final Drawable subtitleOnButtonDrawable;
    private final String subtitleOnContentDescription;
    private TrackSelectionAdapter textTrackSelectionAdapter;
    @Nullable
    private final TimeBar timeBar;
    private int timeBarMinUpdateIntervalMs;
    private TrackNameProvider trackNameProvider;
    @Nullable
    private DefaultTrackSelector trackSelector;
    private final Runnable updateProgressAction;
    private final CopyOnWriteArrayList<VisibilityListener> visibilityListeners;
    @Nullable
    private final View vrButton;
    private final Timeline.Window window;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class AudioTrackSelectionAdapter extends TrackSelectionAdapter {
        private AudioTrackSelectionAdapter() {
            super();
        }

        @Override // com.google.android.exoplayer2.ui.StyledPlayerControlView.TrackSelectionAdapter
        public void init(List<Integer> list, List<TrackInfo> list2, MappingTrackSelector.MappedTrackInfo mappedTrackInfo) {
            boolean z;
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= list.size()) {
                    z = false;
                    break;
                }
                int intValue = list.get(i2).intValue();
                TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(intValue);
                if (StyledPlayerControlView.this.trackSelector != null && StyledPlayerControlView.this.trackSelector.getParameters().hasSelectionOverride(intValue, trackGroups)) {
                    z = true;
                    break;
                }
                i2++;
            }
            if (!list2.isEmpty()) {
                if (z) {
                    while (true) {
                        if (i >= list2.size()) {
                            break;
                        }
                        TrackInfo trackInfo = list2.get(i);
                        if (trackInfo.selected) {
                            StyledPlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, trackInfo.trackName);
                            break;
                        }
                        i++;
                    }
                } else {
                    StyledPlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, StyledPlayerControlView.this.getResources().getString(R.string.exo_track_selection_auto));
                }
            } else {
                StyledPlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, StyledPlayerControlView.this.getResources().getString(R.string.exo_track_selection_none));
            }
            this.rendererIndices = list;
            this.tracks = list2;
            this.mappedTrackInfo = mappedTrackInfo;
        }

        public /* synthetic */ void lambda$onBindViewHolderAtZeroPosition$0$StyledPlayerControlView$AudioTrackSelectionAdapter(View view) {
            if (StyledPlayerControlView.this.trackSelector != null) {
                DefaultTrackSelector.ParametersBuilder mo7431buildUpon = StyledPlayerControlView.this.trackSelector.getParameters().mo7431buildUpon();
                for (int i = 0; i < this.rendererIndices.size(); i++) {
                    mo7431buildUpon = mo7431buildUpon.clearSelectionOverrides(this.rendererIndices.get(i).intValue());
                }
                ((DefaultTrackSelector) Assertions.checkNotNull(StyledPlayerControlView.this.trackSelector)).setParameters(mo7431buildUpon);
            }
            StyledPlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, StyledPlayerControlView.this.getResources().getString(R.string.exo_track_selection_auto));
            StyledPlayerControlView.this.settingsWindow.dismiss();
        }

        @Override // com.google.android.exoplayer2.ui.StyledPlayerControlView.TrackSelectionAdapter
        public void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder) {
            boolean z;
            subSettingViewHolder.textView.setText(R.string.exo_track_selection_auto);
            DefaultTrackSelector.Parameters parameters = ((DefaultTrackSelector) Assertions.checkNotNull(StyledPlayerControlView.this.trackSelector)).getParameters();
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= this.rendererIndices.size()) {
                    z = false;
                    break;
                }
                int intValue = this.rendererIndices.get(i2).intValue();
                if (parameters.hasSelectionOverride(intValue, ((MappingTrackSelector.MappedTrackInfo) Assertions.checkNotNull(this.mappedTrackInfo)).getTrackGroups(intValue))) {
                    z = true;
                    break;
                }
                i2++;
            }
            View view = subSettingViewHolder.checkView;
            if (z) {
                i = 4;
            }
            view.setVisibility(i);
            subSettingViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.exoplayer2.ui.-$$Lambda$StyledPlayerControlView$AudioTrackSelectionAdapter$hrRy8Jfn1OBeXfostW2eNouUobI
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    StyledPlayerControlView.AudioTrackSelectionAdapter.this.lambda$onBindViewHolderAtZeroPosition$0$StyledPlayerControlView$AudioTrackSelectionAdapter(view2);
                }
            });
        }

        @Override // com.google.android.exoplayer2.ui.StyledPlayerControlView.TrackSelectionAdapter
        public void onTrackSelection(String str) {
            StyledPlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, str);
        }
    }

    /* loaded from: classes2.dex */
    private final class ComponentListener implements Player.EventListener, TimeBar.OnScrubListener, View.OnClickListener, PopupWindow.OnDismissListener {
        private ComponentListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Player player = StyledPlayerControlView.this.player;
            if (player == null) {
                return;
            }
            StyledPlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
            if (StyledPlayerControlView.this.nextButton == view) {
                StyledPlayerControlView.this.controlDispatcher.dispatchNext(player);
            } else if (StyledPlayerControlView.this.previousButton == view) {
                StyledPlayerControlView.this.controlDispatcher.dispatchPrevious(player);
            } else if (StyledPlayerControlView.this.fastForwardButton != view) {
                if (StyledPlayerControlView.this.rewindButton == view) {
                    StyledPlayerControlView.this.controlDispatcher.dispatchRewind(player);
                } else if (StyledPlayerControlView.this.playPauseButton == view) {
                    StyledPlayerControlView.this.dispatchPlayPause(player);
                } else if (StyledPlayerControlView.this.repeatToggleButton != view) {
                    if (StyledPlayerControlView.this.shuffleButton == view) {
                        StyledPlayerControlView.this.controlDispatcher.dispatchSetShuffleModeEnabled(player, !player.getShuffleModeEnabled());
                    } else if (StyledPlayerControlView.this.settingsButton == view) {
                        StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                        StyledPlayerControlView styledPlayerControlView = StyledPlayerControlView.this;
                        styledPlayerControlView.displaySettingsWindow(styledPlayerControlView.settingsAdapter);
                    } else if (StyledPlayerControlView.this.playbackSpeedButton == view) {
                        StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                        StyledPlayerControlView styledPlayerControlView2 = StyledPlayerControlView.this;
                        styledPlayerControlView2.displaySettingsWindow(styledPlayerControlView2.playbackSpeedAdapter);
                    } else if (StyledPlayerControlView.this.audioTrackButton == view) {
                        StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                        StyledPlayerControlView styledPlayerControlView3 = StyledPlayerControlView.this;
                        styledPlayerControlView3.displaySettingsWindow(styledPlayerControlView3.audioTrackSelectionAdapter);
                    } else if (StyledPlayerControlView.this.subtitleButton != view) {
                    } else {
                        StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                        StyledPlayerControlView styledPlayerControlView4 = StyledPlayerControlView.this;
                        styledPlayerControlView4.displaySettingsWindow(styledPlayerControlView4.textTrackSelectionAdapter);
                    }
                } else {
                    StyledPlayerControlView.this.controlDispatcher.dispatchSetRepeatMode(player, RepeatModeUtil.getNextRepeatMode(player.getRepeatMode(), StyledPlayerControlView.this.repeatToggleModes));
                }
            } else if (player.getPlaybackState() == 4) {
            } else {
                StyledPlayerControlView.this.controlDispatcher.dispatchFastForward(player);
            }
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            if (StyledPlayerControlView.this.needToHideBars) {
                StyledPlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
            }
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onEvents(Player player, Player.Events events) {
            if (events.containsAny(5, 6)) {
                StyledPlayerControlView.this.updatePlayPauseButton();
            }
            if (events.containsAny(5, 6, 8)) {
                StyledPlayerControlView.this.updateProgress();
            }
            if (events.contains(9)) {
                StyledPlayerControlView.this.updateRepeatModeButton();
            }
            if (events.contains(10)) {
                StyledPlayerControlView.this.updateShuffleButton();
            }
            if (events.containsAny(9, 10, 12, 0)) {
                StyledPlayerControlView.this.updateNavigation();
            }
            if (events.containsAny(12, 0)) {
                StyledPlayerControlView.this.updateTimeline();
            }
            if (events.contains(13)) {
                StyledPlayerControlView.this.updatePlaybackSpeedList();
            }
            if (events.contains(2)) {
                StyledPlayerControlView.this.updateTrackLists();
            }
        }

        @Override // com.google.android.exoplayer2.ui.TimeBar.OnScrubListener
        public void onScrubMove(TimeBar timeBar, long j) {
            if (StyledPlayerControlView.this.positionView != null) {
                StyledPlayerControlView.this.positionView.setText(Util.getStringForTime(StyledPlayerControlView.this.formatBuilder, StyledPlayerControlView.this.formatter, j));
            }
        }

        @Override // com.google.android.exoplayer2.ui.TimeBar.OnScrubListener
        public void onScrubStart(TimeBar timeBar, long j) {
            StyledPlayerControlView.this.scrubbing = true;
            if (StyledPlayerControlView.this.positionView != null) {
                StyledPlayerControlView.this.positionView.setText(Util.getStringForTime(StyledPlayerControlView.this.formatBuilder, StyledPlayerControlView.this.formatter, j));
            }
            StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
        }

        @Override // com.google.android.exoplayer2.ui.TimeBar.OnScrubListener
        public void onScrubStop(TimeBar timeBar, long j, boolean z) {
            StyledPlayerControlView.this.scrubbing = false;
            if (!z && StyledPlayerControlView.this.player != null) {
                StyledPlayerControlView styledPlayerControlView = StyledPlayerControlView.this;
                styledPlayerControlView.seekToTimeBarPosition(styledPlayerControlView.player, j);
            }
            StyledPlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
        }
    }

    /* loaded from: classes2.dex */
    public interface OnFullScreenModeChangedListener {
        void onFullScreenModeChanged(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class PlaybackSpeedAdapter extends RecyclerView.Adapter<SubSettingViewHolder> {
        private final String[] playbackSpeedTexts;
        private final int[] playbackSpeedsMultBy100;
        private int selectedIndex;

        public PlaybackSpeedAdapter(String[] strArr, int[] iArr) {
            this.playbackSpeedTexts = strArr;
            this.playbackSpeedsMultBy100 = iArr;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.playbackSpeedTexts.length;
        }

        public String getSelectedText() {
            return this.playbackSpeedTexts[this.selectedIndex];
        }

        public /* synthetic */ void lambda$onBindViewHolder$0$StyledPlayerControlView$PlaybackSpeedAdapter(int i, View view) {
            if (i != this.selectedIndex) {
                StyledPlayerControlView.this.setPlaybackSpeed(this.playbackSpeedsMultBy100[i] / 100.0f);
            }
            StyledPlayerControlView.this.settingsWindow.dismiss();
        }

        public void updateSelectedIndex(float f) {
            int round = Math.round(f * 100.0f);
            int i = 0;
            int i2 = Integer.MAX_VALUE;
            int i3 = 0;
            while (true) {
                int[] iArr = this.playbackSpeedsMultBy100;
                if (i < iArr.length) {
                    int abs = Math.abs(round - iArr[i]);
                    if (abs < i2) {
                        i3 = i;
                        i2 = abs;
                    }
                    i++;
                } else {
                    this.selectedIndex = i3;
                    return;
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, final int i) {
            String[] strArr = this.playbackSpeedTexts;
            if (i < strArr.length) {
                subSettingViewHolder.textView.setText(strArr[i]);
            }
            subSettingViewHolder.checkView.setVisibility(i == this.selectedIndex ? 0 : 4);
            subSettingViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.exoplayer2.ui.-$$Lambda$StyledPlayerControlView$PlaybackSpeedAdapter$mEGmGMMlAI__CipqsxEmK1AQlD4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StyledPlayerControlView.PlaybackSpeedAdapter.this.lambda$onBindViewHolder$0$StyledPlayerControlView$PlaybackSpeedAdapter(i, view);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: onCreateViewHolder  reason: collision with other method in class */
        public SubSettingViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SubSettingViewHolder(LayoutInflater.from(StyledPlayerControlView.this.getContext()).inflate(R.layout.exo_styled_sub_settings_list_item, (ViewGroup) null));
        }
    }

    /* loaded from: classes2.dex */
    public interface ProgressUpdateListener {
        void onProgressUpdate(long j, long j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class SettingViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iconView;
        private final TextView mainTextView;
        private final TextView subTextView;

        public SettingViewHolder(View view) {
            super(view);
            this.mainTextView = (TextView) view.findViewById(R.id.exo_main_text);
            this.subTextView = (TextView) view.findViewById(R.id.exo_sub_text);
            this.iconView = (ImageView) view.findViewById(R.id.exo_icon);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.exoplayer2.ui.-$$Lambda$StyledPlayerControlView$SettingViewHolder$QG0HWBZp7AbXaQL4toH_6bB9s6o
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    StyledPlayerControlView.SettingViewHolder.this.lambda$new$0$StyledPlayerControlView$SettingViewHolder(view2);
                }
            });
        }

        public /* synthetic */ void lambda$new$0$StyledPlayerControlView$SettingViewHolder(View view) {
            StyledPlayerControlView.this.onSettingViewClicked(getAdapterPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class SettingsAdapter extends RecyclerView.Adapter<SettingViewHolder> {
        private final Drawable[] iconIds;
        private final String[] mainTexts;
        private final String[] subTexts;

        public SettingsAdapter(String[] strArr, Drawable[] drawableArr) {
            this.mainTexts = strArr;
            this.subTexts = new String[strArr.length];
            this.iconIds = drawableArr;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mainTexts.length;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int i) {
            return i;
        }

        public void setSubTextAtPosition(int i, String str) {
            this.subTexts[i] = str;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SettingViewHolder settingViewHolder, int i) {
            settingViewHolder.mainTextView.setText(this.mainTexts[i]);
            if (this.subTexts[i] == null) {
                settingViewHolder.subTextView.setVisibility(8);
            } else {
                settingViewHolder.subTextView.setText(this.subTexts[i]);
            }
            if (this.iconIds[i] == null) {
                settingViewHolder.iconView.setVisibility(8);
            } else {
                settingViewHolder.iconView.setImageDrawable(this.iconIds[i]);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: onCreateViewHolder  reason: collision with other method in class */
        public SettingViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SettingViewHolder(LayoutInflater.from(StyledPlayerControlView.this.getContext()).inflate(R.layout.exo_styled_settings_list_item, (ViewGroup) null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SubSettingViewHolder extends RecyclerView.ViewHolder {
        public final View checkView;
        public final TextView textView;

        public SubSettingViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.exo_text);
            this.checkView = view.findViewById(R.id.exo_check);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class TextTrackSelectionAdapter extends TrackSelectionAdapter {
        private TextTrackSelectionAdapter() {
            super();
        }

        @Override // com.google.android.exoplayer2.ui.StyledPlayerControlView.TrackSelectionAdapter
        public void init(List<Integer> list, List<TrackInfo> list2, MappingTrackSelector.MappedTrackInfo mappedTrackInfo) {
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= list2.size()) {
                    break;
                } else if (list2.get(i).selected) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (StyledPlayerControlView.this.subtitleButton != null) {
                StyledPlayerControlView.this.subtitleButton.setImageDrawable(z ? StyledPlayerControlView.this.subtitleOnButtonDrawable : StyledPlayerControlView.this.subtitleOffButtonDrawable);
                StyledPlayerControlView.this.subtitleButton.setContentDescription(z ? StyledPlayerControlView.this.subtitleOnContentDescription : StyledPlayerControlView.this.subtitleOffContentDescription);
            }
            this.rendererIndices = list;
            this.tracks = list2;
            this.mappedTrackInfo = mappedTrackInfo;
        }

        public /* synthetic */ void lambda$onBindViewHolderAtZeroPosition$0$StyledPlayerControlView$TextTrackSelectionAdapter(View view) {
            if (StyledPlayerControlView.this.trackSelector != null) {
                DefaultTrackSelector.ParametersBuilder mo7431buildUpon = StyledPlayerControlView.this.trackSelector.getParameters().mo7431buildUpon();
                for (int i = 0; i < this.rendererIndices.size(); i++) {
                    int intValue = this.rendererIndices.get(i).intValue();
                    mo7431buildUpon = mo7431buildUpon.clearSelectionOverrides(intValue).setRendererDisabled(intValue, true);
                }
                ((DefaultTrackSelector) Assertions.checkNotNull(StyledPlayerControlView.this.trackSelector)).setParameters(mo7431buildUpon);
                StyledPlayerControlView.this.settingsWindow.dismiss();
            }
        }

        @Override // com.google.android.exoplayer2.ui.StyledPlayerControlView.TrackSelectionAdapter
        public void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder) {
            boolean z;
            subSettingViewHolder.textView.setText(R.string.exo_track_selection_none);
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= this.tracks.size()) {
                    z = true;
                    break;
                } else if (this.tracks.get(i2).selected) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            View view = subSettingViewHolder.checkView;
            if (!z) {
                i = 4;
            }
            view.setVisibility(i);
            subSettingViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.exoplayer2.ui.-$$Lambda$StyledPlayerControlView$TextTrackSelectionAdapter$T0d3G57M_lGcyhOvHI6rvuBqAzc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    StyledPlayerControlView.TextTrackSelectionAdapter.this.lambda$onBindViewHolderAtZeroPosition$0$StyledPlayerControlView$TextTrackSelectionAdapter(view2);
                }
            });
        }

        @Override // com.google.android.exoplayer2.ui.StyledPlayerControlView.TrackSelectionAdapter
        public void onTrackSelection(String str) {
        }

        @Override // com.google.android.exoplayer2.ui.StyledPlayerControlView.TrackSelectionAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            super.onBindViewHolder(subSettingViewHolder, i);
            if (i > 0) {
                subSettingViewHolder.checkView.setVisibility(this.tracks.get(i + (-1)).selected ? 0 : 4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class TrackInfo {
        public final int groupIndex;
        public final int rendererIndex;
        public final boolean selected;
        public final int trackIndex;
        public final String trackName;

        public TrackInfo(int i, int i2, int i3, String str, boolean z) {
            this.rendererIndex = i;
            this.groupIndex = i2;
            this.trackIndex = i3;
            this.trackName = str;
            this.selected = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public abstract class TrackSelectionAdapter extends RecyclerView.Adapter<SubSettingViewHolder> {
        protected List<Integer> rendererIndices = new ArrayList();
        protected List<TrackInfo> tracks = new ArrayList();
        @Nullable
        protected MappingTrackSelector.MappedTrackInfo mappedTrackInfo = null;

        public TrackSelectionAdapter() {
        }

        public void clear() {
            this.tracks = Collections.emptyList();
            this.mappedTrackInfo = null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (this.tracks.isEmpty()) {
                return 0;
            }
            return this.tracks.size() + 1;
        }

        public abstract void init(List<Integer> list, List<TrackInfo> list2, MappingTrackSelector.MappedTrackInfo mappedTrackInfo);

        public /* synthetic */ void lambda$onBindViewHolder$0$StyledPlayerControlView$TrackSelectionAdapter(TrackInfo trackInfo, View view) {
            if (this.mappedTrackInfo == null || StyledPlayerControlView.this.trackSelector == null) {
                return;
            }
            DefaultTrackSelector.ParametersBuilder mo7431buildUpon = StyledPlayerControlView.this.trackSelector.getParameters().mo7431buildUpon();
            for (int i = 0; i < this.rendererIndices.size(); i++) {
                int intValue = this.rendererIndices.get(i).intValue();
                if (intValue == trackInfo.rendererIndex) {
                    mo7431buildUpon = mo7431buildUpon.setSelectionOverride(intValue, ((MappingTrackSelector.MappedTrackInfo) Assertions.checkNotNull(this.mappedTrackInfo)).getTrackGroups(intValue), new DefaultTrackSelector.SelectionOverride(trackInfo.groupIndex, trackInfo.trackIndex)).setRendererDisabled(intValue, false);
                } else {
                    mo7431buildUpon = mo7431buildUpon.clearSelectionOverrides(intValue).setRendererDisabled(intValue, true);
                }
            }
            ((DefaultTrackSelector) Assertions.checkNotNull(StyledPlayerControlView.this.trackSelector)).setParameters(mo7431buildUpon);
            onTrackSelection(trackInfo.trackName);
            StyledPlayerControlView.this.settingsWindow.dismiss();
        }

        public abstract void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder);

        public abstract void onTrackSelection(String str);

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            if (StyledPlayerControlView.this.trackSelector == null || this.mappedTrackInfo == null) {
                return;
            }
            if (i == 0) {
                onBindViewHolderAtZeroPosition(subSettingViewHolder);
                return;
            }
            boolean z = true;
            final TrackInfo trackInfo = this.tracks.get(i - 1);
            int i2 = 0;
            if (!((DefaultTrackSelector) Assertions.checkNotNull(StyledPlayerControlView.this.trackSelector)).getParameters().hasSelectionOverride(trackInfo.rendererIndex, this.mappedTrackInfo.getTrackGroups(trackInfo.rendererIndex)) || !trackInfo.selected) {
                z = false;
            }
            subSettingViewHolder.textView.setText(trackInfo.trackName);
            View view = subSettingViewHolder.checkView;
            if (!z) {
                i2 = 4;
            }
            view.setVisibility(i2);
            subSettingViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.exoplayer2.ui.-$$Lambda$StyledPlayerControlView$TrackSelectionAdapter$9z-RzuVk07lzOQ3zOBPYNxuP63k
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    StyledPlayerControlView.TrackSelectionAdapter.this.lambda$onBindViewHolder$0$StyledPlayerControlView$TrackSelectionAdapter(trackInfo, view2);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: onCreateViewHolder  reason: collision with other method in class */
        public SubSettingViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SubSettingViewHolder(LayoutInflater.from(StyledPlayerControlView.this.getContext()).inflate(R.layout.exo_styled_sub_settings_list_item, (ViewGroup) null));
        }
    }

    /* loaded from: classes2.dex */
    public interface VisibilityListener {
        void onVisibilityChange(int i);
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.ui");
    }

    public StyledPlayerControlView(Context context) {
        this(context, null);
    }

    private static boolean canShowMultiWindowTimeBar(Timeline timeline, Timeline.Window window) {
        if (timeline.getWindowCount() > 100) {
            return false;
        }
        int windowCount = timeline.getWindowCount();
        for (int i = 0; i < windowCount; i++) {
            if (timeline.getWindow(i, window).durationUs == C.TIME_UNSET) {
                return false;
            }
        }
        return true;
    }

    private void dispatchPause(Player player) {
        this.controlDispatcher.dispatchSetPlayWhenReady(player, false);
    }

    private void dispatchPlay(Player player) {
        int playbackState = player.getPlaybackState();
        if (playbackState == 1) {
            PlaybackPreparer playbackPreparer = this.playbackPreparer;
            if (playbackPreparer != null) {
                playbackPreparer.preparePlayback();
            } else {
                this.controlDispatcher.dispatchPrepare(player);
            }
        } else if (playbackState == 4) {
            seekTo(player, player.getCurrentWindowIndex(), C.TIME_UNSET);
        }
        this.controlDispatcher.dispatchSetPlayWhenReady(player, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchPlayPause(Player player) {
        int playbackState = player.getPlaybackState();
        if (playbackState != 1 && playbackState != 4 && player.getPlayWhenReady()) {
            dispatchPause(player);
        } else {
            dispatchPlay(player);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displaySettingsWindow(RecyclerView.Adapter<?> adapter) {
        this.settingsView.setAdapter(adapter);
        updateSettingsWindowSize();
        this.needToHideBars = false;
        this.settingsWindow.dismiss();
        this.needToHideBars = true;
        this.settingsWindow.showAsDropDown(this, (getWidth() - this.settingsWindow.getWidth()) - this.settingsWindowMargin, (-this.settingsWindow.getHeight()) - this.settingsWindowMargin);
    }

    private void gatherTrackInfosForAdapter(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int i, List<TrackInfo> list) {
        TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(i);
        TrackSelection trackSelection = ((Player) Assertions.checkNotNull(this.player)).getCurrentTrackSelections().get(i);
        for (int i2 = 0; i2 < trackGroups.length; i2++) {
            TrackGroup trackGroup = trackGroups.get(i2);
            for (int i3 = 0; i3 < trackGroup.length; i3++) {
                Format format = trackGroup.getFormat(i3);
                if (mappedTrackInfo.getTrackSupport(i, i2, i3) == 4) {
                    list.add(new TrackInfo(i, i2, i3, this.trackNameProvider.getTrackName(format), (trackSelection == null || trackSelection.indexOf(format) == -1) ? false : true));
                }
            }
        }
    }

    private void initTrackSelectionAdapter() {
        DefaultTrackSelector defaultTrackSelector;
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo;
        this.textTrackSelectionAdapter.clear();
        this.audioTrackSelectionAdapter.clear();
        if (this.player == null || (defaultTrackSelector = this.trackSelector) == null || (currentMappedTrackInfo = defaultTrackSelector.getCurrentMappedTrackInfo()) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (int i = 0; i < currentMappedTrackInfo.getRendererCount(); i++) {
            if (currentMappedTrackInfo.getRendererType(i) == 3 && this.controlViewLayoutManager.getShowButton(this.subtitleButton)) {
                gatherTrackInfosForAdapter(currentMappedTrackInfo, i, arrayList);
                arrayList3.add(Integer.valueOf(i));
            } else if (currentMappedTrackInfo.getRendererType(i) == 1) {
                gatherTrackInfosForAdapter(currentMappedTrackInfo, i, arrayList2);
                arrayList4.add(Integer.valueOf(i));
            }
        }
        this.textTrackSelectionAdapter.init(arrayList3, arrayList, currentMappedTrackInfo);
        this.audioTrackSelectionAdapter.init(arrayList4, arrayList2, currentMappedTrackInfo);
    }

    private static void initializeFullScreenButton(View view, View.OnClickListener onClickListener) {
        if (view == null) {
            return;
        }
        view.setVisibility(8);
        view.setOnClickListener(onClickListener);
    }

    @SuppressLint({"InlinedApi"})
    private static boolean isHandledMediaKey(int i) {
        return i == 90 || i == 89 || i == 85 || i == 79 || i == 126 || i == 127 || i == 87 || i == 88;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFullScreenButtonClicked(View view) {
        if (this.onFullScreenModeChangedListener == null) {
            return;
        }
        this.isFullScreen = !this.isFullScreen;
        updateFullScreenButtonForState(this.fullScreenButton, this.isFullScreen);
        updateFullScreenButtonForState(this.minimalFullScreenButton, this.isFullScreen);
        OnFullScreenModeChangedListener onFullScreenModeChangedListener = this.onFullScreenModeChangedListener;
        if (onFullScreenModeChangedListener == null) {
            return;
        }
        onFullScreenModeChangedListener.onFullScreenModeChanged(this.isFullScreen);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i4 - i2;
        int i10 = i8 - i6;
        if (!(i3 - i == i7 - i5 && i9 == i10) && this.settingsWindow.isShowing()) {
            updateSettingsWindowSize();
            this.settingsWindow.update(view, (getWidth() - this.settingsWindow.getWidth()) - this.settingsWindowMargin, (-this.settingsWindow.getHeight()) - this.settingsWindowMargin, -1, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSettingViewClicked(int i) {
        if (i == 0) {
            displaySettingsWindow(this.playbackSpeedAdapter);
        } else if (i == 1) {
            displaySettingsWindow(this.audioTrackSelectionAdapter);
        } else {
            this.settingsWindow.dismiss();
        }
    }

    private boolean seekTo(Player player, int i, long j) {
        return this.controlDispatcher.dispatchSeekTo(player, i, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void seekToTimeBarPosition(Player player, long j) {
        int currentWindowIndex;
        Timeline currentTimeline = player.getCurrentTimeline();
        if (this.multiWindowTimeBar && !currentTimeline.isEmpty()) {
            int windowCount = currentTimeline.getWindowCount();
            currentWindowIndex = 0;
            while (true) {
                long durationMs = currentTimeline.getWindow(currentWindowIndex, this.window).getDurationMs();
                if (j < durationMs) {
                    break;
                } else if (currentWindowIndex == windowCount - 1) {
                    j = durationMs;
                    break;
                } else {
                    j -= durationMs;
                    currentWindowIndex++;
                }
            }
        } else {
            currentWindowIndex = player.getCurrentWindowIndex();
        }
        if (!seekTo(player, currentWindowIndex, j)) {
            updateProgress();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlaybackSpeed(float f) {
        Player player = this.player;
        if (player == null) {
            return;
        }
        this.controlDispatcher.dispatchSetPlaybackParameters(player, player.getPlaybackParameters().withSpeed(f));
    }

    private boolean shouldShowPauseButton() {
        Player player = this.player;
        return (player == null || player.getPlaybackState() == 4 || this.player.getPlaybackState() == 1 || !this.player.getPlayWhenReady()) ? false : true;
    }

    private void updateButton(boolean z, @Nullable View view) {
        if (view == null) {
            return;
        }
        view.setEnabled(z);
        view.setAlpha(z ? this.buttonAlphaEnabled : this.buttonAlphaDisabled);
    }

    private void updateFastForwardButton() {
        ControlDispatcher controlDispatcher = this.controlDispatcher;
        if (controlDispatcher instanceof DefaultControlDispatcher) {
            this.fastForwardMs = ((DefaultControlDispatcher) controlDispatcher).getFastForwardIncrementMs();
        }
        int i = (int) (this.fastForwardMs / 1000);
        TextView textView = this.fastForwardButtonTextView;
        if (textView != null) {
            textView.setText(String.valueOf(i));
        }
        View view = this.fastForwardButton;
        if (view != null) {
            view.setContentDescription(this.resources.getQuantityString(R.plurals.exo_controls_fastforward_by_amount_description, i, Integer.valueOf(i)));
        }
    }

    private void updateFullScreenButtonForState(@Nullable ImageView imageView, boolean z) {
        if (imageView == null) {
            return;
        }
        if (z) {
            imageView.setImageDrawable(this.fullScreenExitDrawable);
            imageView.setContentDescription(this.fullScreenExitContentDescription);
            return;
        }
        imageView.setImageDrawable(this.fullScreenEnterDrawable);
        imageView.setContentDescription(this.fullScreenEnterContentDescription);
    }

    private static void updateFullScreenButtonVisibility(@Nullable View view, boolean z) {
        if (view == null) {
            return;
        }
        if (z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateNavigation() {
        /*
            r8 = this;
            boolean r0 = r8.isVisible()
            if (r0 == 0) goto L9c
            boolean r0 = r8.isAttachedToWindow
            if (r0 != 0) goto Lc
            goto L9c
        Lc:
            com.google.android.exoplayer2.Player r0 = r8.player
            r1 = 0
            if (r0 == 0) goto L73
            com.google.android.exoplayer2.Timeline r2 = r0.getCurrentTimeline()
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L73
            boolean r3 = r0.isPlayingAd()
            if (r3 != 0) goto L73
            int r3 = r0.getCurrentWindowIndex()
            com.google.android.exoplayer2.Timeline$Window r4 = r8.window
            r2.getWindow(r3, r4)
            com.google.android.exoplayer2.Timeline$Window r2 = r8.window
            boolean r3 = r2.isSeekable
            r4 = 1
            if (r3 != 0) goto L40
            boolean r2 = r2.isLive()
            if (r2 == 0) goto L40
            boolean r2 = r0.hasPrevious()
            if (r2 == 0) goto L3e
            goto L40
        L3e:
            r2 = r1
            goto L41
        L40:
            r2 = r4
        L41:
            if (r3 == 0) goto L4d
            com.google.android.exoplayer2.ControlDispatcher r5 = r8.controlDispatcher
            boolean r5 = r5.isRewindEnabled()
            if (r5 == 0) goto L4d
            r5 = r4
            goto L4e
        L4d:
            r5 = r1
        L4e:
            if (r3 == 0) goto L5a
            com.google.android.exoplayer2.ControlDispatcher r6 = r8.controlDispatcher
            boolean r6 = r6.isFastForwardEnabled()
            if (r6 == 0) goto L5a
            r6 = r4
            goto L5b
        L5a:
            r6 = r1
        L5b:
            com.google.android.exoplayer2.Timeline$Window r7 = r8.window
            boolean r7 = r7.isLive()
            if (r7 == 0) goto L69
            com.google.android.exoplayer2.Timeline$Window r7 = r8.window
            boolean r7 = r7.isDynamic
            if (r7 != 0) goto L6f
        L69:
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L70
        L6f:
            r1 = r4
        L70:
            r0 = r1
            r1 = r5
            goto L77
        L73:
            r0 = r1
            r2 = r0
            r3 = r2
            r6 = r3
        L77:
            if (r1 == 0) goto L7c
            r8.updateRewindButton()
        L7c:
            if (r6 == 0) goto L81
            r8.updateFastForwardButton()
        L81:
            android.view.View r4 = r8.previousButton
            r8.updateButton(r2, r4)
            android.view.View r2 = r8.rewindButton
            r8.updateButton(r1, r2)
            android.view.View r1 = r8.fastForwardButton
            r8.updateButton(r6, r1)
            android.view.View r1 = r8.nextButton
            r8.updateButton(r0, r1)
            com.google.android.exoplayer2.ui.TimeBar r0 = r8.timeBar
            if (r0 == 0) goto L9c
            r0.setEnabled(r3)
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.StyledPlayerControlView.updateNavigation():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlayPauseButton() {
        if (!isVisible() || !this.isAttachedToWindow || this.playPauseButton == null) {
            return;
        }
        if (shouldShowPauseButton()) {
            ((ImageView) this.playPauseButton).setImageDrawable(this.resources.getDrawable(R.drawable.exo_styled_controls_pause));
            this.playPauseButton.setContentDescription(this.resources.getString(R.string.exo_controls_pause_description));
            return;
        }
        ((ImageView) this.playPauseButton).setImageDrawable(this.resources.getDrawable(R.drawable.exo_styled_controls_play));
        this.playPauseButton.setContentDescription(this.resources.getString(R.string.exo_controls_play_description));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlaybackSpeedList() {
        Player player = this.player;
        if (player == null) {
            return;
        }
        this.playbackSpeedAdapter.updateSelectedIndex(player.getPlaybackParameters().speed);
        this.settingsAdapter.setSubTextAtPosition(0, this.playbackSpeedAdapter.getSelectedText());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProgress() {
        long j;
        if (!isVisible() || !this.isAttachedToWindow) {
            return;
        }
        Player player = this.player;
        long j2 = 0;
        if (player != null) {
            j2 = this.currentWindowOffset + player.getContentPosition();
            j = this.currentWindowOffset + player.getContentBufferedPosition();
        } else {
            j = 0;
        }
        TextView textView = this.positionView;
        if (textView != null && !this.scrubbing) {
            textView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, j2));
        }
        TimeBar timeBar = this.timeBar;
        if (timeBar != null) {
            timeBar.setPosition(j2);
            this.timeBar.setBufferedPosition(j);
        }
        ProgressUpdateListener progressUpdateListener = this.progressUpdateListener;
        if (progressUpdateListener != null) {
            progressUpdateListener.onProgressUpdate(j2, j);
        }
        removeCallbacks(this.updateProgressAction);
        int playbackState = player == null ? 1 : player.getPlaybackState();
        long j3 = 1000;
        if (player == null || !player.isPlaying()) {
            if (playbackState == 4 || playbackState == 1) {
                return;
            }
            postDelayed(this.updateProgressAction, 1000L);
            return;
        }
        TimeBar timeBar2 = this.timeBar;
        long min = Math.min(timeBar2 != null ? timeBar2.getPreferredUpdateDelay() : 1000L, 1000 - (j2 % 1000));
        float f = player.getPlaybackParameters().speed;
        if (f > 0.0f) {
            j3 = ((float) min) / f;
        }
        postDelayed(this.updateProgressAction, Util.constrainValue(j3, this.timeBarMinUpdateIntervalMs, 1000L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRepeatModeButton() {
        ImageView imageView;
        if (!isVisible() || !this.isAttachedToWindow || (imageView = this.repeatToggleButton) == null) {
            return;
        }
        if (this.repeatToggleModes == 0) {
            updateButton(false, imageView);
            return;
        }
        Player player = this.player;
        if (player == null) {
            updateButton(false, imageView);
            this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
            this.repeatToggleButton.setContentDescription(this.repeatOffButtonContentDescription);
            return;
        }
        updateButton(true, imageView);
        int repeatMode = player.getRepeatMode();
        if (repeatMode == 0) {
            this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
            this.repeatToggleButton.setContentDescription(this.repeatOffButtonContentDescription);
        } else if (repeatMode == 1) {
            this.repeatToggleButton.setImageDrawable(this.repeatOneButtonDrawable);
            this.repeatToggleButton.setContentDescription(this.repeatOneButtonContentDescription);
        } else if (repeatMode != 2) {
        } else {
            this.repeatToggleButton.setImageDrawable(this.repeatAllButtonDrawable);
            this.repeatToggleButton.setContentDescription(this.repeatAllButtonContentDescription);
        }
    }

    private void updateRewindButton() {
        ControlDispatcher controlDispatcher = this.controlDispatcher;
        if (controlDispatcher instanceof DefaultControlDispatcher) {
            this.rewindMs = ((DefaultControlDispatcher) controlDispatcher).getRewindIncrementMs();
        }
        int i = (int) (this.rewindMs / 1000);
        TextView textView = this.rewindButtonTextView;
        if (textView != null) {
            textView.setText(String.valueOf(i));
        }
        View view = this.rewindButton;
        if (view != null) {
            view.setContentDescription(this.resources.getQuantityString(R.plurals.exo_controls_rewind_by_amount_description, i, Integer.valueOf(i)));
        }
    }

    private void updateSettingsWindowSize() {
        this.settingsView.measure(0, 0);
        this.settingsWindow.setWidth(Math.min(this.settingsView.getMeasuredWidth(), getWidth() - (this.settingsWindowMargin * 2)));
        this.settingsWindow.setHeight(Math.min(getHeight() - (this.settingsWindowMargin * 2), this.settingsView.getMeasuredHeight()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShuffleButton() {
        ImageView imageView;
        if (!isVisible() || !this.isAttachedToWindow || (imageView = this.shuffleButton) == null) {
            return;
        }
        Player player = this.player;
        if (!this.controlViewLayoutManager.getShowButton(imageView)) {
            updateButton(false, this.shuffleButton);
        } else if (player == null) {
            updateButton(false, this.shuffleButton);
            this.shuffleButton.setImageDrawable(this.shuffleOffButtonDrawable);
            this.shuffleButton.setContentDescription(this.shuffleOffContentDescription);
        } else {
            updateButton(true, this.shuffleButton);
            this.shuffleButton.setImageDrawable(player.getShuffleModeEnabled() ? this.shuffleOnButtonDrawable : this.shuffleOffButtonDrawable);
            this.shuffleButton.setContentDescription(player.getShuffleModeEnabled() ? this.shuffleOnContentDescription : this.shuffleOffContentDescription);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTimeline() {
        int i;
        long j;
        Timeline.Window window;
        Player player = this.player;
        if (player == null) {
            return;
        }
        boolean z = true;
        this.multiWindowTimeBar = this.showMultiWindowTimeBar && canShowMultiWindowTimeBar(player.getCurrentTimeline(), this.window);
        long j2 = 0;
        this.currentWindowOffset = 0L;
        Timeline currentTimeline = player.getCurrentTimeline();
        if (!currentTimeline.isEmpty()) {
            int currentWindowIndex = player.getCurrentWindowIndex();
            int i2 = this.multiWindowTimeBar ? 0 : currentWindowIndex;
            int windowCount = this.multiWindowTimeBar ? currentTimeline.getWindowCount() - 1 : currentWindowIndex;
            long j3 = 0;
            i = 0;
            while (true) {
                if (i2 > windowCount) {
                    break;
                }
                if (i2 == currentWindowIndex) {
                    this.currentWindowOffset = C.usToMs(j3);
                }
                currentTimeline.getWindow(i2, this.window);
                Timeline.Window window2 = this.window;
                if (window2.durationUs == C.TIME_UNSET) {
                    Assertions.checkState(this.multiWindowTimeBar ^ z);
                    break;
                }
                int i3 = window2.firstPeriodIndex;
                while (true) {
                    window = this.window;
                    if (i3 <= window.lastPeriodIndex) {
                        currentTimeline.getPeriod(i3, this.period);
                        int adGroupCount = this.period.getAdGroupCount();
                        int i4 = i;
                        int i5 = 0;
                        while (i5 < adGroupCount) {
                            long adGroupTimeUs = this.period.getAdGroupTimeUs(i5);
                            if (adGroupTimeUs == Long.MIN_VALUE) {
                                long j4 = this.period.durationUs;
                                if (j4 == C.TIME_UNSET) {
                                    i5++;
                                    j2 = 0;
                                } else {
                                    adGroupTimeUs = j4;
                                }
                            }
                            long positionInWindowUs = this.period.getPositionInWindowUs() + adGroupTimeUs;
                            if (positionInWindowUs >= j2) {
                                long[] jArr = this.adGroupTimesMs;
                                if (i4 == jArr.length) {
                                    int length = jArr.length == 0 ? 1 : jArr.length * 2;
                                    this.adGroupTimesMs = Arrays.copyOf(this.adGroupTimesMs, length);
                                    this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, length);
                                }
                                this.adGroupTimesMs[i4] = C.usToMs(positionInWindowUs + j3);
                                this.playedAdGroups[i4] = this.period.hasPlayedAdGroup(i5);
                                i4++;
                            }
                            i5++;
                            j2 = 0;
                        }
                        i3++;
                        i = i4;
                        j2 = 0;
                    }
                }
                j3 += window.durationUs;
                i2++;
                z = true;
                j2 = 0;
            }
            j = j3;
        } else {
            i = 0;
            j = 0;
        }
        long usToMs = C.usToMs(j);
        TextView textView = this.durationView;
        if (textView != null) {
            textView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, usToMs));
        }
        TimeBar timeBar = this.timeBar;
        if (timeBar != null) {
            timeBar.setDuration(usToMs);
            int length2 = this.extraAdGroupTimesMs.length;
            int i6 = i + length2;
            long[] jArr2 = this.adGroupTimesMs;
            if (i6 > jArr2.length) {
                this.adGroupTimesMs = Arrays.copyOf(jArr2, i6);
                this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, i6);
            }
            System.arraycopy(this.extraAdGroupTimesMs, 0, this.adGroupTimesMs, i, length2);
            System.arraycopy(this.extraPlayedAdGroups, 0, this.playedAdGroups, i, length2);
            this.timeBar.setAdGroupTimesMs(this.adGroupTimesMs, this.playedAdGroups, i6);
        }
        updateProgress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTrackLists() {
        initTrackSelectionAdapter();
        updateButton(this.textTrackSelectionAdapter.getItemCount() > 0, this.subtitleButton);
    }

    public void addVisibilityListener(VisibilityListener visibilityListener) {
        Assertions.checkNotNull(visibilityListener);
        this.visibilityListeners.add(visibilityListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return dispatchMediaKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        Player player = this.player;
        if (player == null || !isHandledMediaKey(keyCode)) {
            return false;
        }
        if (keyEvent.getAction() != 0) {
            return true;
        }
        if (keyCode == 90) {
            if (player.getPlaybackState() == 4) {
                return true;
            }
            this.controlDispatcher.dispatchFastForward(player);
            return true;
        } else if (keyCode == 89) {
            this.controlDispatcher.dispatchRewind(player);
            return true;
        } else if (keyEvent.getRepeatCount() != 0) {
            return true;
        } else {
            if (keyCode == 79 || keyCode == 85) {
                dispatchPlayPause(player);
                return true;
            } else if (keyCode == 87) {
                this.controlDispatcher.dispatchNext(player);
                return true;
            } else if (keyCode == 88) {
                this.controlDispatcher.dispatchPrevious(player);
                return true;
            } else if (keyCode == 126) {
                dispatchPlay(player);
                return true;
            } else if (keyCode != 127) {
                return true;
            } else {
                dispatchPause(player);
                return true;
            }
        }
    }

    @Nullable
    public Player getPlayer() {
        return this.player;
    }

    public int getRepeatToggleModes() {
        return this.repeatToggleModes;
    }

    public boolean getShowShuffleButton() {
        return this.controlViewLayoutManager.getShowButton(this.shuffleButton);
    }

    public boolean getShowSubtitleButton() {
        return this.controlViewLayoutManager.getShowButton(this.subtitleButton);
    }

    public int getShowTimeoutMs() {
        return this.showTimeoutMs;
    }

    public boolean getShowVrButton() {
        return this.controlViewLayoutManager.getShowButton(this.vrButton);
    }

    public void hide() {
        this.controlViewLayoutManager.hide();
    }

    public void hideImmediately() {
        this.controlViewLayoutManager.hideImmediately();
    }

    public boolean isAnimationEnabled() {
        return this.controlViewLayoutManager.isAnimationEnabled();
    }

    public boolean isFullyVisible() {
        return this.controlViewLayoutManager.isFullyVisible();
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyOnVisibilityChange() {
        Iterator<VisibilityListener> it2 = this.visibilityListeners.iterator();
        while (it2.hasNext()) {
            it2.next().onVisibilityChange(getVisibility());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.controlViewLayoutManager.onAttachedToWindow();
        this.isAttachedToWindow = true;
        if (isFullyVisible()) {
            this.controlViewLayoutManager.resetHideCallbacks();
        }
        updateAll();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.controlViewLayoutManager.onDetachedFromWindow();
        this.isAttachedToWindow = false;
        removeCallbacks(this.updateProgressAction);
        this.controlViewLayoutManager.removeHideCallbacks();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.controlViewLayoutManager.onLayout(z, i, i2, i3, i4);
    }

    public void removeVisibilityListener(VisibilityListener visibilityListener) {
        this.visibilityListeners.remove(visibilityListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requestPlayPauseFocus() {
        View view = this.playPauseButton;
        if (view != null) {
            view.requestFocus();
        }
    }

    public void setAnimationEnabled(boolean z) {
        this.controlViewLayoutManager.setAnimationEnabled(z);
    }

    public void setControlDispatcher(ControlDispatcher controlDispatcher) {
        if (this.controlDispatcher != controlDispatcher) {
            this.controlDispatcher = controlDispatcher;
            updateNavigation();
        }
    }

    public void setExtraAdGroupMarkers(@Nullable long[] jArr, @Nullable boolean[] zArr) {
        boolean z = false;
        if (jArr == null) {
            this.extraAdGroupTimesMs = new long[0];
            this.extraPlayedAdGroups = new boolean[0];
        } else {
            boolean[] zArr2 = (boolean[]) Assertions.checkNotNull(zArr);
            if (jArr.length == zArr2.length) {
                z = true;
            }
            Assertions.checkArgument(z);
            this.extraAdGroupTimesMs = jArr;
            this.extraPlayedAdGroups = zArr2;
        }
        updateTimeline();
    }

    public void setOnFullScreenModeChangedListener(@Nullable OnFullScreenModeChangedListener onFullScreenModeChangedListener) {
        this.onFullScreenModeChangedListener = onFullScreenModeChangedListener;
        boolean z = true;
        updateFullScreenButtonVisibility(this.fullScreenButton, onFullScreenModeChangedListener != null);
        ImageView imageView = this.minimalFullScreenButton;
        if (onFullScreenModeChangedListener == null) {
            z = false;
        }
        updateFullScreenButtonVisibility(imageView, z);
    }

    @Deprecated
    public void setPlaybackPreparer(@Nullable PlaybackPreparer playbackPreparer) {
        this.playbackPreparer = playbackPreparer;
    }

    public void setPlayer(@Nullable Player player) {
        boolean z = true;
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        if (player != null && player.getApplicationLooper() != Looper.getMainLooper()) {
            z = false;
        }
        Assertions.checkArgument(z);
        Player player2 = this.player;
        if (player2 == player) {
            return;
        }
        if (player2 != null) {
            player2.removeListener(this.componentListener);
        }
        this.player = player;
        if (player != null) {
            player.addListener(this.componentListener);
        }
        if (player instanceof ExoPlayer) {
            TrackSelector trackSelector = ((ExoPlayer) player).getTrackSelector();
            if (trackSelector instanceof DefaultTrackSelector) {
                this.trackSelector = (DefaultTrackSelector) trackSelector;
            }
        } else {
            this.trackSelector = null;
        }
        updateAll();
    }

    public void setProgressUpdateListener(@Nullable ProgressUpdateListener progressUpdateListener) {
        this.progressUpdateListener = progressUpdateListener;
    }

    public void setRepeatToggleModes(int i) {
        this.repeatToggleModes = i;
        Player player = this.player;
        boolean z = false;
        if (player != null) {
            int repeatMode = player.getRepeatMode();
            if (i == 0 && repeatMode != 0) {
                this.controlDispatcher.dispatchSetRepeatMode(this.player, 0);
            } else if (i == 1 && repeatMode == 2) {
                this.controlDispatcher.dispatchSetRepeatMode(this.player, 1);
            } else if (i == 2 && repeatMode == 1) {
                this.controlDispatcher.dispatchSetRepeatMode(this.player, 2);
            }
        }
        StyledPlayerControlViewLayoutManager styledPlayerControlViewLayoutManager = this.controlViewLayoutManager;
        ImageView imageView = this.repeatToggleButton;
        if (i != 0) {
            z = true;
        }
        styledPlayerControlViewLayoutManager.setShowButton(imageView, z);
        updateRepeatModeButton();
    }

    public void setShowFastForwardButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.fastForwardButton, z);
        updateNavigation();
    }

    public void setShowMultiWindowTimeBar(boolean z) {
        this.showMultiWindowTimeBar = z;
        updateTimeline();
    }

    public void setShowNextButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.nextButton, z);
        updateNavigation();
    }

    public void setShowPreviousButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.previousButton, z);
        updateNavigation();
    }

    public void setShowRewindButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.rewindButton, z);
        updateNavigation();
    }

    public void setShowShuffleButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.shuffleButton, z);
        updateShuffleButton();
    }

    public void setShowSubtitleButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.subtitleButton, z);
    }

    public void setShowTimeoutMs(int i) {
        this.showTimeoutMs = i;
        if (isFullyVisible()) {
            this.controlViewLayoutManager.resetHideCallbacks();
        }
    }

    public void setShowVrButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.vrButton, z);
    }

    public void setTimeBarMinUpdateInterval(int i) {
        this.timeBarMinUpdateIntervalMs = Util.constrainValue(i, 16, 1000);
    }

    public void setVrButtonListener(@Nullable View.OnClickListener onClickListener) {
        View view = this.vrButton;
        if (view != null) {
            view.setOnClickListener(onClickListener);
            updateButton(onClickListener != null, this.vrButton);
        }
    }

    public void show() {
        this.controlViewLayoutManager.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateAll() {
        updatePlayPauseButton();
        updateNavigation();
        updateRepeatModeButton();
        updateShuffleButton();
        updateTrackLists();
        updatePlaybackSpeedList();
        updateTimeline();
    }

    public StyledPlayerControlView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private static int getRepeatToggleModes(TypedArray typedArray, int i) {
        return typedArray.getInt(R.styleable.StyledPlayerControlView_repeat_toggle_modes, i);
    }

    public StyledPlayerControlView(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, attributeSet);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [com.google.android.exoplayer2.ui.StyledPlayerControlView$1, android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r8v4 */
    public StyledPlayerControlView(Context context, @Nullable AttributeSet attributeSet, int i, @Nullable AttributeSet attributeSet2) {
        super(context, attributeSet, i);
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        ?? r8;
        boolean z11;
        int i2 = R.layout.exo_styled_player_control_view;
        this.rewindMs = 5000L;
        this.fastForwardMs = 15000L;
        this.showTimeoutMs = 5000;
        this.repeatToggleModes = 0;
        this.timeBarMinUpdateIntervalMs = 200;
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet2, R.styleable.StyledPlayerControlView, 0, 0);
            try {
                this.rewindMs = obtainStyledAttributes.getInt(R.styleable.StyledPlayerControlView_rewind_increment, (int) this.rewindMs);
                this.fastForwardMs = obtainStyledAttributes.getInt(R.styleable.StyledPlayerControlView_fastforward_increment, (int) this.fastForwardMs);
                i2 = obtainStyledAttributes.getResourceId(R.styleable.StyledPlayerControlView_controller_layout_id, i2);
                this.showTimeoutMs = obtainStyledAttributes.getInt(R.styleable.StyledPlayerControlView_show_timeout, this.showTimeoutMs);
                this.repeatToggleModes = getRepeatToggleModes(obtainStyledAttributes, this.repeatToggleModes);
                boolean z12 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_show_rewind_button, true);
                boolean z13 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_show_fastforward_button, true);
                boolean z14 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_show_previous_button, true);
                boolean z15 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_show_next_button, true);
                z = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_show_shuffle_button, false);
                z2 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_show_subtitle_button, false);
                z3 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_show_vr_button, false);
                setTimeBarMinUpdateInterval(obtainStyledAttributes.getInt(R.styleable.StyledPlayerControlView_time_bar_min_update_interval, this.timeBarMinUpdateIntervalMs));
                z6 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_animation_enabled, true);
                obtainStyledAttributes.recycle();
                z7 = z12;
                z8 = z13;
                z5 = z14;
                z4 = z15;
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        } else {
            z = false;
            z2 = false;
            z3 = false;
            z4 = true;
            z5 = true;
            z6 = true;
            z7 = true;
            z8 = true;
        }
        LayoutInflater.from(context).inflate(i2, this);
        setDescendantFocusability(262144);
        this.componentListener = new ComponentListener();
        this.visibilityListeners = new CopyOnWriteArrayList<>();
        this.period = new Timeline.Period();
        this.window = new Timeline.Window();
        this.formatBuilder = new StringBuilder();
        this.formatter = new Formatter(this.formatBuilder, Locale.getDefault());
        this.adGroupTimesMs = new long[0];
        this.playedAdGroups = new boolean[0];
        this.extraAdGroupTimesMs = new long[0];
        this.extraPlayedAdGroups = new boolean[0];
        boolean z16 = z4;
        this.controlDispatcher = new DefaultControlDispatcher(this.fastForwardMs, this.rewindMs);
        this.updateProgressAction = new Runnable() { // from class: com.google.android.exoplayer2.ui.-$$Lambda$StyledPlayerControlView$d94Vth4Hab4ZDVeJwJC_V3ktUD8
            @Override // java.lang.Runnable
            public final void run() {
                StyledPlayerControlView.this.updateProgress();
            }
        };
        this.durationView = (TextView) findViewById(R.id.exo_duration);
        this.positionView = (TextView) findViewById(R.id.exo_position);
        this.subtitleButton = (ImageView) findViewById(R.id.exo_subtitle);
        ImageView imageView = this.subtitleButton;
        if (imageView != null) {
            imageView.setOnClickListener(this.componentListener);
        }
        this.fullScreenButton = (ImageView) findViewById(R.id.exo_fullscreen);
        initializeFullScreenButton(this.fullScreenButton, new View.OnClickListener() { // from class: com.google.android.exoplayer2.ui.-$$Lambda$StyledPlayerControlView$sd8-DmGij0WN1841R1uwrzjMVPo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StyledPlayerControlView.this.onFullScreenButtonClicked(view);
            }
        });
        this.minimalFullScreenButton = (ImageView) findViewById(R.id.exo_minimal_fullscreen);
        initializeFullScreenButton(this.minimalFullScreenButton, new View.OnClickListener() { // from class: com.google.android.exoplayer2.ui.-$$Lambda$StyledPlayerControlView$sd8-DmGij0WN1841R1uwrzjMVPo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StyledPlayerControlView.this.onFullScreenButtonClicked(view);
            }
        });
        this.settingsButton = findViewById(R.id.exo_settings);
        View view = this.settingsButton;
        if (view != null) {
            view.setOnClickListener(this.componentListener);
        }
        this.playbackSpeedButton = findViewById(R.id.exo_playback_speed);
        View view2 = this.playbackSpeedButton;
        if (view2 != null) {
            view2.setOnClickListener(this.componentListener);
        }
        this.audioTrackButton = findViewById(R.id.exo_audio_track);
        View view3 = this.audioTrackButton;
        if (view3 != null) {
            view3.setOnClickListener(this.componentListener);
        }
        TimeBar timeBar = (TimeBar) findViewById(R.id.exo_progress);
        View findViewById = findViewById(R.id.exo_progress_placeholder);
        if (timeBar != null) {
            this.timeBar = timeBar;
            z9 = z16;
            z10 = z5;
            r8 = 0;
        } else if (findViewById != null) {
            r8 = 0;
            z9 = z16;
            z10 = z5;
            DefaultTimeBar defaultTimeBar = new DefaultTimeBar(context, null, 0, attributeSet2, R.style.ExoStyledControls_TimeBar);
            defaultTimeBar.setId(R.id.exo_progress);
            defaultTimeBar.setLayoutParams(findViewById.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) findViewById.getParent();
            int indexOfChild = viewGroup.indexOfChild(findViewById);
            viewGroup.removeView(findViewById);
            viewGroup.addView(defaultTimeBar, indexOfChild);
            this.timeBar = defaultTimeBar;
        } else {
            z9 = z16;
            z10 = z5;
            r8 = 0;
            this.timeBar = null;
        }
        TimeBar timeBar2 = this.timeBar;
        if (timeBar2 != null) {
            timeBar2.addListener(this.componentListener);
        }
        this.playPauseButton = findViewById(R.id.exo_play_pause);
        View view4 = this.playPauseButton;
        if (view4 != null) {
            view4.setOnClickListener(this.componentListener);
        }
        this.previousButton = findViewById(R.id.exo_prev);
        View view5 = this.previousButton;
        if (view5 != null) {
            view5.setOnClickListener(this.componentListener);
        }
        this.nextButton = findViewById(R.id.exo_next);
        View view6 = this.nextButton;
        if (view6 != null) {
            view6.setOnClickListener(this.componentListener);
        }
        Typeface font = ResourcesCompat.getFont(context, R.font.roboto_medium_numbers);
        View findViewById2 = findViewById(R.id.exo_rew);
        this.rewindButtonTextView = findViewById2 == null ? (TextView) findViewById(R.id.exo_rew_with_amount) : r8;
        TextView textView = this.rewindButtonTextView;
        if (textView != null) {
            textView.setTypeface(font);
        }
        this.rewindButton = findViewById2 == null ? this.rewindButtonTextView : findViewById2;
        View view7 = this.rewindButton;
        if (view7 != null) {
            view7.setOnClickListener(this.componentListener);
        }
        View findViewById3 = findViewById(R.id.exo_ffwd);
        this.fastForwardButtonTextView = findViewById3 == null ? (TextView) findViewById(R.id.exo_ffwd_with_amount) : r8;
        TextView textView2 = this.fastForwardButtonTextView;
        if (textView2 != null) {
            textView2.setTypeface(font);
        }
        this.fastForwardButton = findViewById3 == null ? this.fastForwardButtonTextView : findViewById3;
        View view8 = this.fastForwardButton;
        if (view8 != null) {
            view8.setOnClickListener(this.componentListener);
        }
        this.repeatToggleButton = (ImageView) findViewById(R.id.exo_repeat_toggle);
        ImageView imageView2 = this.repeatToggleButton;
        if (imageView2 != null) {
            imageView2.setOnClickListener(this.componentListener);
        }
        this.shuffleButton = (ImageView) findViewById(R.id.exo_shuffle);
        ImageView imageView3 = this.shuffleButton;
        if (imageView3 != null) {
            imageView3.setOnClickListener(this.componentListener);
        }
        this.resources = context.getResources();
        this.buttonAlphaEnabled = this.resources.getInteger(R.integer.exo_media_button_opacity_percentage_enabled) / 100.0f;
        this.buttonAlphaDisabled = this.resources.getInteger(R.integer.exo_media_button_opacity_percentage_disabled) / 100.0f;
        this.vrButton = findViewById(R.id.exo_vr);
        View view9 = this.vrButton;
        if (view9 != null) {
            updateButton(false, view9);
        }
        this.controlViewLayoutManager = new StyledPlayerControlViewLayoutManager(this);
        this.controlViewLayoutManager.setAnimationEnabled(z6);
        this.settingsAdapter = new SettingsAdapter(new String[]{this.resources.getString(R.string.exo_controls_playback_speed), this.resources.getString(R.string.exo_track_selection_title_audio)}, new Drawable[]{this.resources.getDrawable(R.drawable.exo_styled_controls_speed), this.resources.getDrawable(R.drawable.exo_styled_controls_audiotrack)});
        this.settingsWindowMargin = this.resources.getDimensionPixelSize(R.dimen.exo_settings_offset);
        this.settingsView = (RecyclerView) LayoutInflater.from(context).inflate(R.layout.exo_styled_settings_list, (ViewGroup) r8);
        this.settingsView.setAdapter(this.settingsAdapter);
        this.settingsView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.settingsWindow = new PopupWindow((View) this.settingsView, -2, -2, true);
        if (Util.SDK_INT < 23) {
            z11 = false;
            this.settingsWindow.setBackgroundDrawable(new ColorDrawable(0));
        } else {
            z11 = false;
        }
        this.settingsWindow.setOnDismissListener(this.componentListener);
        this.needToHideBars = true;
        this.trackNameProvider = new DefaultTrackNameProvider(getResources());
        this.subtitleOnButtonDrawable = this.resources.getDrawable(R.drawable.exo_styled_controls_subtitle_on);
        this.subtitleOffButtonDrawable = this.resources.getDrawable(R.drawable.exo_styled_controls_subtitle_off);
        this.subtitleOnContentDescription = this.resources.getString(R.string.exo_controls_cc_enabled_description);
        this.subtitleOffContentDescription = this.resources.getString(R.string.exo_controls_cc_disabled_description);
        this.textTrackSelectionAdapter = new TextTrackSelectionAdapter();
        this.audioTrackSelectionAdapter = new AudioTrackSelectionAdapter();
        this.playbackSpeedAdapter = new PlaybackSpeedAdapter(this.resources.getStringArray(R.array.exo_playback_speeds), this.resources.getIntArray(R.array.exo_speed_multiplied_by_100));
        this.fullScreenExitDrawable = this.resources.getDrawable(R.drawable.exo_styled_controls_fullscreen_exit);
        this.fullScreenEnterDrawable = this.resources.getDrawable(R.drawable.exo_styled_controls_fullscreen_enter);
        this.repeatOffButtonDrawable = this.resources.getDrawable(R.drawable.exo_styled_controls_repeat_off);
        this.repeatOneButtonDrawable = this.resources.getDrawable(R.drawable.exo_styled_controls_repeat_one);
        this.repeatAllButtonDrawable = this.resources.getDrawable(R.drawable.exo_styled_controls_repeat_all);
        this.shuffleOnButtonDrawable = this.resources.getDrawable(R.drawable.exo_styled_controls_shuffle_on);
        this.shuffleOffButtonDrawable = this.resources.getDrawable(R.drawable.exo_styled_controls_shuffle_off);
        this.fullScreenExitContentDescription = this.resources.getString(R.string.exo_controls_fullscreen_exit_description);
        this.fullScreenEnterContentDescription = this.resources.getString(R.string.exo_controls_fullscreen_enter_description);
        this.repeatOffButtonContentDescription = this.resources.getString(R.string.exo_controls_repeat_off_description);
        this.repeatOneButtonContentDescription = this.resources.getString(R.string.exo_controls_repeat_one_description);
        this.repeatAllButtonContentDescription = this.resources.getString(R.string.exo_controls_repeat_all_description);
        this.shuffleOnContentDescription = this.resources.getString(R.string.exo_controls_shuffle_on_description);
        this.shuffleOffContentDescription = this.resources.getString(R.string.exo_controls_shuffle_off_description);
        this.controlViewLayoutManager.setShowButton((ViewGroup) findViewById(R.id.exo_bottom_bar), true);
        this.controlViewLayoutManager.setShowButton(this.fastForwardButton, z8);
        this.controlViewLayoutManager.setShowButton(this.rewindButton, z7);
        this.controlViewLayoutManager.setShowButton(this.previousButton, z10);
        this.controlViewLayoutManager.setShowButton(this.nextButton, z9);
        this.controlViewLayoutManager.setShowButton(this.shuffleButton, z);
        this.controlViewLayoutManager.setShowButton(this.subtitleButton, z2);
        this.controlViewLayoutManager.setShowButton(this.vrButton, z3);
        this.controlViewLayoutManager.setShowButton(this.repeatToggleButton, this.repeatToggleModes != 0 ? true : z11);
        addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.google.android.exoplayer2.ui.-$$Lambda$StyledPlayerControlView$niv5NEP7ncglQ9JFsIeRrAXS1yQ
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view10, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                StyledPlayerControlView.this.onLayoutChange(view10, i3, i4, i5, i6, i7, i8, i9, i10);
            }
        });
    }
}
