package com.amazon.deecomms.calling.incallexperiences.storytime;

import com.amazon.deecomms.calling.datachannel.InCallOrientation;
import com.amazon.deecomms.calling.model.Boundary;
/* loaded from: classes12.dex */
public final class StoryTimeState {
    public static final StoryTimeState INSTANCE = new StoryTimeState();
    private boolean isStoryTimeEnabled = false;
    private InCallOrientation orientation = null;
    private Boundary boundary = new Boundary();

    private StoryTimeState() {
    }

    public void clear() {
        this.isStoryTimeEnabled = false;
        this.orientation = null;
        this.boundary.clear();
    }

    public Boundary getBoundary() {
        return this.boundary;
    }

    public InCallOrientation getOrientation() {
        return this.orientation;
    }

    public boolean isStoryTimeEnabled() {
        return this.isStoryTimeEnabled;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }

    public void setOrientation(InCallOrientation inCallOrientation) {
        this.orientation = inCallOrientation;
    }

    public void setStoryTimeEnabled(boolean z) {
        this.isStoryTimeEnabled = z;
    }
}
