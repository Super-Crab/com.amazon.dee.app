package com.amazon.alexa;

import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import java.util.List;
/* compiled from: ConcatenatingMediaSourceFactory.java */
/* loaded from: classes.dex */
public class cCP {
    public ConcatenatingMediaSource zZm(List<MediaSource> list) {
        if (list == null) {
            return new ConcatenatingMediaSource(new MediaSource[0]);
        }
        return new ConcatenatingMediaSource((MediaSource[]) list.toArray(new MediaSource[0]));
    }
}
