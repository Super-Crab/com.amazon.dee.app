package com.amazon.ptz.physical.events;

import javax.annotation.Nonnull;
/* loaded from: classes13.dex */
public enum PhysicalPtzEvent {
    DIRECTIVE_SENT("DirectiveSent"),
    MOTION_ENDED("MotionEnded"),
    OBSTACLE_ENCOUNTERED("ObstacleEncountered"),
    END_OF_RANGE("EndOfRange");
    
    private final String name;

    PhysicalPtzEvent(@Nonnull String str) {
        this.name = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
