package com.amazon.alexa.voice.ui.driveMode.space;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.amazon.alexa.voice.ui.driveMode.widget.DriveModeBindableViewHolder;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public class DriveModeEmptySpaceViewHolder extends DriveModeBindableViewHolder {
    public DriveModeEmptySpaceViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this(layoutInflater, R.layout.voice_ui_od_empty_space_header_content, viewGroup);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.widget.DriveModeBindableViewHolder
    public void bind(Object obj, int i) {
    }

    public DriveModeEmptySpaceViewHolder(LayoutInflater layoutInflater, int i, ViewGroup viewGroup) {
        super(layoutInflater.inflate(i, viewGroup, false));
    }
}
