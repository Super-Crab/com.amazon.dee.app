package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import androidx.annotation.AttrRes;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.ui.TrackSelectionView;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
/* loaded from: classes2.dex */
public class TrackSelectionView extends LinearLayout {
    private boolean allowAdaptiveSelections;
    private boolean allowMultipleOverrides;
    private final ComponentListener componentListener;
    private final CheckedTextView defaultView;
    private final CheckedTextView disableView;
    private final LayoutInflater inflater;
    private boolean isDisabled;
    @Nullable
    private TrackSelectionListener listener;
    private MappingTrackSelector.MappedTrackInfo mappedTrackInfo;
    private final SparseArray<DefaultTrackSelector.SelectionOverride> overrides;
    private int rendererIndex;
    private final int selectableItemBackgroundResourceId;
    private TrackGroupArray trackGroups;
    @Nullable
    private Comparator<TrackInfo> trackInfoComparator;
    private TrackNameProvider trackNameProvider;
    private CheckedTextView[][] trackViews;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ComponentListener implements View.OnClickListener {
        private ComponentListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TrackSelectionView.this.onClick(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class TrackInfo {
        public final Format format;
        public final int groupIndex;
        public final int trackIndex;

        public TrackInfo(int i, int i2, Format format) {
            this.groupIndex = i;
            this.trackIndex = i2;
            this.format = format;
        }
    }

    /* loaded from: classes2.dex */
    public interface TrackSelectionListener {
        void onTrackSelectionChanged(boolean z, List<DefaultTrackSelector.SelectionOverride> list);
    }

    public TrackSelectionView(Context context) {
        this(context, null);
    }

    private static int[] getTracksAdding(int[] iArr, int i) {
        int[] copyOf = Arrays.copyOf(iArr, iArr.length + 1);
        copyOf[copyOf.length - 1] = i;
        return copyOf;
    }

    private static int[] getTracksRemoving(int[] iArr, int i) {
        int[] iArr2 = new int[iArr.length - 1];
        int i2 = 0;
        for (int i3 : iArr) {
            if (i3 != i) {
                iArr2[i2] = i3;
                i2++;
            }
        }
        return iArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClick(View view) {
        if (view == this.disableView) {
            onDisableViewClicked();
        } else if (view == this.defaultView) {
            onDefaultViewClicked();
        } else {
            onTrackViewClicked(view);
        }
        updateViewStates();
        TrackSelectionListener trackSelectionListener = this.listener;
        if (trackSelectionListener != null) {
            trackSelectionListener.onTrackSelectionChanged(getIsDisabled(), getOverrides());
        }
    }

    private void onDefaultViewClicked() {
        this.isDisabled = false;
        this.overrides.clear();
    }

    private void onDisableViewClicked() {
        this.isDisabled = true;
        this.overrides.clear();
    }

    private void onTrackViewClicked(View view) {
        this.isDisabled = false;
        TrackInfo trackInfo = (TrackInfo) Assertions.checkNotNull(view.getTag());
        int i = trackInfo.groupIndex;
        int i2 = trackInfo.trackIndex;
        DefaultTrackSelector.SelectionOverride selectionOverride = this.overrides.get(i);
        Assertions.checkNotNull(this.mappedTrackInfo);
        if (selectionOverride == null) {
            if (!this.allowMultipleOverrides && this.overrides.size() > 0) {
                this.overrides.clear();
            }
            this.overrides.put(i, new DefaultTrackSelector.SelectionOverride(i, i2));
            return;
        }
        int i3 = selectionOverride.length;
        int[] iArr = selectionOverride.tracks;
        boolean isChecked = ((CheckedTextView) view).isChecked();
        boolean shouldEnableAdaptiveSelection = shouldEnableAdaptiveSelection(i);
        boolean z = shouldEnableAdaptiveSelection || shouldEnableMultiGroupSelection();
        if (isChecked && z) {
            if (i3 == 1) {
                this.overrides.remove(i);
            } else {
                this.overrides.put(i, new DefaultTrackSelector.SelectionOverride(i, getTracksRemoving(iArr, i2)));
            }
        } else if (isChecked) {
        } else {
            if (shouldEnableAdaptiveSelection) {
                this.overrides.put(i, new DefaultTrackSelector.SelectionOverride(i, getTracksAdding(iArr, i2)));
            } else {
                this.overrides.put(i, new DefaultTrackSelector.SelectionOverride(i, i2));
            }
        }
    }

    @RequiresNonNull({"mappedTrackInfo"})
    private boolean shouldEnableAdaptiveSelection(int i) {
        return this.allowAdaptiveSelections && this.trackGroups.get(i).length > 1 && this.mappedTrackInfo.getAdaptiveSupport(this.rendererIndex, i, false) != 0;
    }

    private boolean shouldEnableMultiGroupSelection() {
        return this.allowMultipleOverrides && this.trackGroups.length > 1;
    }

    private void updateViewStates() {
        this.disableView.setChecked(this.isDisabled);
        this.defaultView.setChecked(!this.isDisabled && this.overrides.size() == 0);
        for (int i = 0; i < this.trackViews.length; i++) {
            DefaultTrackSelector.SelectionOverride selectionOverride = this.overrides.get(i);
            int i2 = 0;
            while (true) {
                CheckedTextView[][] checkedTextViewArr = this.trackViews;
                if (i2 < checkedTextViewArr[i].length) {
                    if (selectionOverride != null) {
                        this.trackViews[i][i2].setChecked(selectionOverride.containsTrack(((TrackInfo) Assertions.checkNotNull(checkedTextViewArr[i][i2].getTag())).trackIndex));
                    } else {
                        checkedTextViewArr[i][i2].setChecked(false);
                    }
                    i2++;
                }
            }
        }
    }

    private void updateViews() {
        for (int childCount = getChildCount() - 1; childCount >= 3; childCount--) {
            removeViewAt(childCount);
        }
        if (this.mappedTrackInfo == null) {
            this.disableView.setEnabled(false);
            this.defaultView.setEnabled(false);
            return;
        }
        this.disableView.setEnabled(true);
        this.defaultView.setEnabled(true);
        this.trackGroups = this.mappedTrackInfo.getTrackGroups(this.rendererIndex);
        this.trackViews = new CheckedTextView[this.trackGroups.length];
        boolean shouldEnableMultiGroupSelection = shouldEnableMultiGroupSelection();
        int i = 0;
        while (true) {
            TrackGroupArray trackGroupArray = this.trackGroups;
            if (i < trackGroupArray.length) {
                TrackGroup trackGroup = trackGroupArray.get(i);
                boolean shouldEnableAdaptiveSelection = shouldEnableAdaptiveSelection(i);
                CheckedTextView[][] checkedTextViewArr = this.trackViews;
                int i2 = trackGroup.length;
                checkedTextViewArr[i] = new CheckedTextView[i2];
                TrackInfo[] trackInfoArr = new TrackInfo[i2];
                for (int i3 = 0; i3 < trackGroup.length; i3++) {
                    trackInfoArr[i3] = new TrackInfo(i, i3, trackGroup.getFormat(i3));
                }
                Comparator<TrackInfo> comparator = this.trackInfoComparator;
                if (comparator != null) {
                    Arrays.sort(trackInfoArr, comparator);
                }
                for (int i4 = 0; i4 < trackInfoArr.length; i4++) {
                    if (i4 == 0) {
                        addView(this.inflater.inflate(R.layout.exo_list_divider, (ViewGroup) this, false));
                    }
                    CheckedTextView checkedTextView = (CheckedTextView) this.inflater.inflate((shouldEnableAdaptiveSelection || shouldEnableMultiGroupSelection) ? 17367056 : 17367055, (ViewGroup) this, false);
                    checkedTextView.setBackgroundResource(this.selectableItemBackgroundResourceId);
                    checkedTextView.setText(this.trackNameProvider.getTrackName(trackInfoArr[i4].format));
                    checkedTextView.setTag(trackInfoArr[i4]);
                    if (this.mappedTrackInfo.getTrackSupport(this.rendererIndex, i, i4) == 4) {
                        checkedTextView.setFocusable(true);
                        checkedTextView.setOnClickListener(this.componentListener);
                    } else {
                        checkedTextView.setFocusable(false);
                        checkedTextView.setEnabled(false);
                    }
                    this.trackViews[i][i4] = checkedTextView;
                    addView(checkedTextView);
                }
                i++;
            } else {
                updateViewStates();
                return;
            }
        }
    }

    public boolean getIsDisabled() {
        return this.isDisabled;
    }

    public List<DefaultTrackSelector.SelectionOverride> getOverrides() {
        ArrayList arrayList = new ArrayList(this.overrides.size());
        for (int i = 0; i < this.overrides.size(); i++) {
            arrayList.add(this.overrides.valueAt(i));
        }
        return arrayList;
    }

    public void init(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int i, boolean z, List<DefaultTrackSelector.SelectionOverride> list, @Nullable final Comparator<Format> comparator, @Nullable TrackSelectionListener trackSelectionListener) {
        this.mappedTrackInfo = mappedTrackInfo;
        this.rendererIndex = i;
        this.isDisabled = z;
        this.trackInfoComparator = comparator == null ? null : new Comparator() { // from class: com.google.android.exoplayer2.ui.-$$Lambda$TrackSelectionView$KrnfNB5IAmqGW-gPl4yQT3OirQ8
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = comparator.compare(((TrackSelectionView.TrackInfo) obj).format, ((TrackSelectionView.TrackInfo) obj2).format);
                return compare;
            }
        };
        this.listener = trackSelectionListener;
        int size = this.allowMultipleOverrides ? list.size() : Math.min(list.size(), 1);
        for (int i2 = 0; i2 < size; i2++) {
            DefaultTrackSelector.SelectionOverride selectionOverride = list.get(i2);
            this.overrides.put(selectionOverride.groupIndex, selectionOverride);
        }
        updateViews();
    }

    public void setAllowAdaptiveSelections(boolean z) {
        if (this.allowAdaptiveSelections != z) {
            this.allowAdaptiveSelections = z;
            updateViews();
        }
    }

    public void setAllowMultipleOverrides(boolean z) {
        if (this.allowMultipleOverrides != z) {
            this.allowMultipleOverrides = z;
            if (!z && this.overrides.size() > 1) {
                for (int size = this.overrides.size() - 1; size > 0; size--) {
                    this.overrides.remove(size);
                }
            }
            updateViews();
        }
    }

    public void setShowDisableOption(boolean z) {
        this.disableView.setVisibility(z ? 0 : 8);
    }

    public void setTrackNameProvider(TrackNameProvider trackNameProvider) {
        this.trackNameProvider = (TrackNameProvider) Assertions.checkNotNull(trackNameProvider);
        updateViews();
    }

    public TrackSelectionView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TrackSelectionView(Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        setOrientation(1);
        this.overrides = new SparseArray<>();
        setSaveFromParentEnabled(false);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{16843534});
        this.selectableItemBackgroundResourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        this.inflater = LayoutInflater.from(context);
        this.componentListener = new ComponentListener();
        this.trackNameProvider = new DefaultTrackNameProvider(getResources());
        this.trackGroups = TrackGroupArray.EMPTY;
        this.disableView = (CheckedTextView) this.inflater.inflate(17367055, (ViewGroup) this, false);
        this.disableView.setBackgroundResource(this.selectableItemBackgroundResourceId);
        this.disableView.setText(R.string.exo_track_selection_none);
        this.disableView.setEnabled(false);
        this.disableView.setFocusable(true);
        this.disableView.setOnClickListener(this.componentListener);
        this.disableView.setVisibility(8);
        addView(this.disableView);
        addView(this.inflater.inflate(R.layout.exo_list_divider, (ViewGroup) this, false));
        this.defaultView = (CheckedTextView) this.inflater.inflate(17367055, (ViewGroup) this, false);
        this.defaultView.setBackgroundResource(this.selectableItemBackgroundResourceId);
        this.defaultView.setText(R.string.exo_track_selection_auto);
        this.defaultView.setEnabled(false);
        this.defaultView.setFocusable(true);
        this.defaultView.setOnClickListener(this.componentListener);
        addView(this.defaultView);
    }
}
