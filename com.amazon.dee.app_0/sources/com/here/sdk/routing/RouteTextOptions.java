package com.here.sdk.routing;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import com.here.sdk.core.LanguageCode;
import com.here.sdk.core.TextFormat;
import com.here.sdk.core.UnitSystem;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class RouteTextOptions {
    @NonNull
    public TextFormat instructionFormat;
    @NonNull
    public LanguageCode language;
    @NonNull
    public UnitSystem unitSystem;

    public RouteTextOptions() {
        this.language = LanguageCode.EN_US;
        this.instructionFormat = TextFormat.HTML;
        this.unitSystem = UnitSystem.METRIC;
    }

    public RouteTextOptions(@NonNull LanguageCode languageCode, @NonNull TextFormat textFormat, @NonNull UnitSystem unitSystem) {
        this.language = languageCode;
        this.instructionFormat = textFormat;
        this.unitSystem = unitSystem;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RouteTextOptions)) {
            return false;
        }
        RouteTextOptions routeTextOptions = (RouteTextOptions) obj;
        return Objects.equals(this.language, routeTextOptions.language) && Objects.equals(this.instructionFormat, routeTextOptions.instructionFormat) && Objects.equals(this.unitSystem, routeTextOptions.unitSystem);
    }

    public int hashCode() {
        LanguageCode languageCode = this.language;
        int i = 0;
        int hashCode = ((languageCode != null ? languageCode.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        TextFormat textFormat = this.instructionFormat;
        int hashCode2 = (hashCode + (textFormat != null ? textFormat.hashCode() : 0)) * 31;
        UnitSystem unitSystem = this.unitSystem;
        if (unitSystem != null) {
            i = unitSystem.hashCode();
        }
        return hashCode2 + i;
    }
}
