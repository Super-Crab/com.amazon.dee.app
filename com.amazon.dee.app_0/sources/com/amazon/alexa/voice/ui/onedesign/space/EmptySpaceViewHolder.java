package com.amazon.alexa.voice.ui.onedesign.space;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public class EmptySpaceViewHolder extends BindableViewHolder {
    public EmptySpaceViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this(layoutInflater, R.layout.voice_ui_od_empty_space_header_content, viewGroup);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder
    public void bind(Object obj) {
    }

    public EmptySpaceViewHolder(LayoutInflater layoutInflater, int i, ViewGroup viewGroup) {
        super(layoutInflater.inflate(i, viewGroup, false));
    }
}
