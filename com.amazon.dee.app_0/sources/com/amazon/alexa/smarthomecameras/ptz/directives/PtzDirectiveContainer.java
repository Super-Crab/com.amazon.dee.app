package com.amazon.alexa.smarthomecameras.ptz.directives;

import com.amazon.alexa.smarthomecameras.directives.AlexaDirective;
import com.google.common.base.Preconditions;
import java.util.Objects;
/* loaded from: classes10.dex */
public class PtzDirectiveContainer {
    private final AlexaDirective directive;

    private PtzDirectiveContainer(AlexaDirective alexaDirective) {
        Preconditions.checkNotNull(alexaDirective, "Directive cannot be null");
        this.directive = alexaDirective;
    }

    public static PtzDirectiveContainer create(AlexaDirective alexaDirective) {
        return new PtzDirectiveContainer(alexaDirective);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PtzDirectiveContainer) {
            return Objects.equals(this.directive, ((PtzDirectiveContainer) obj).directive);
        }
        return false;
    }

    public AlexaDirective getAlexaDirective() {
        return this.directive;
    }
}
