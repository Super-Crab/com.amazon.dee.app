package com.horcrux.svg;

import com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.here.sdk.search.PlaceCategory;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
/* loaded from: classes3.dex */
class TextProperties {

    /* loaded from: classes3.dex */
    enum AlignmentBaseline {
        baseline("baseline"),
        textBottom("text-bottom"),
        alphabetic("alphabetic"),
        ideographic("ideographic"),
        middle("middle"),
        central("central"),
        mathematical("mathematical"),
        textTop("text-top"),
        bottom(ViewProps.BOTTOM),
        center("center"),
        top(ViewProps.TOP),
        textBeforeEdge("text-before-edge"),
        textAfterEdge("text-after-edge"),
        beforeEdge("before-edge"),
        afterEdge("after-edge"),
        hanging("hanging");
        
        private static final Map<String, AlignmentBaseline> alignmentToEnum = new HashMap();
        private final String alignment;

        static {
            AlignmentBaseline[] values;
            for (AlignmentBaseline alignmentBaseline : values()) {
                alignmentToEnum.put(alignmentBaseline.alignment, alignmentBaseline);
            }
        }

        AlignmentBaseline(String str) {
            this.alignment = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static AlignmentBaseline getEnum(String str) {
            if (alignmentToEnum.containsKey(str)) {
                return alignmentToEnum.get(str);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unknown String Value: ", str));
        }

        @Override // java.lang.Enum
        @Nonnull
        public String toString() {
            return this.alignment;
        }
    }

    /* loaded from: classes3.dex */
    enum Direction {
        ltr,
        rtl
    }

    /* loaded from: classes3.dex */
    enum FontStyle {
        normal,
        italic,
        oblique
    }

    /* loaded from: classes3.dex */
    enum FontVariantLigatures {
        normal,
        none
    }

    /* loaded from: classes3.dex */
    enum FontWeight {
        Normal(BaseTemplateProvider.NON_INVITATION_KEY),
        Bold(TtmlNode.BOLD),
        w100(PlaceCategory.EAT_AND_DRINK),
        w200("200"),
        w300(PlaceCategory.SIGHTS_AND_MUSEUMS),
        w400(PlaceCategory.TRANSPORT),
        w500(PlaceCategory.ACCOMODATION),
        w600(PlaceCategory.SHOPPING),
        w700(PlaceCategory.BUSINESS_AND_SERVICES),
        w800(PlaceCategory.FACILITIES),
        w900(PlaceCategory.AREAS_AND_BUILDINGS),
        Bolder("bolder"),
        Lighter("lighter");
        
        private static final Map<String, FontWeight> weightToEnum = new HashMap();
        private final String weight;

        static {
            FontWeight[] values;
            for (FontWeight fontWeight : values()) {
                weightToEnum.put(fontWeight.weight, fontWeight);
            }
        }

        FontWeight(String str) {
            this.weight = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static FontWeight get(String str) {
            return weightToEnum.get(str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static boolean hasEnum(String str) {
            return weightToEnum.containsKey(str);
        }

        @Override // java.lang.Enum
        @Nonnull
        public String toString() {
            return this.weight;
        }
    }

    /* loaded from: classes3.dex */
    enum TextAnchor {
        start,
        middle,
        end
    }

    /* loaded from: classes3.dex */
    enum TextDecoration {
        None("none"),
        Underline(TtmlNode.UNDERLINE),
        Overline("overline"),
        LineThrough("line-through"),
        Blink("blink");
        
        private static final Map<String, TextDecoration> decorationToEnum = new HashMap();
        private final String decoration;

        static {
            TextDecoration[] values;
            for (TextDecoration textDecoration : values()) {
                decorationToEnum.put(textDecoration.decoration, textDecoration);
            }
        }

        TextDecoration(String str) {
            this.decoration = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static TextDecoration getEnum(String str) {
            if (decorationToEnum.containsKey(str)) {
                return decorationToEnum.get(str);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unknown String Value: ", str));
        }

        @Override // java.lang.Enum
        @Nonnull
        public String toString() {
            return this.decoration;
        }
    }

    /* loaded from: classes3.dex */
    enum TextLengthAdjust {
        spacing,
        spacingAndGlyphs
    }

    /* loaded from: classes3.dex */
    enum TextPathMethod {
        align,
        stretch
    }

    /* loaded from: classes3.dex */
    enum TextPathMidLine {
        sharp,
        smooth
    }

    /* loaded from: classes3.dex */
    enum TextPathSide {
        left,
        right
    }

    /* loaded from: classes3.dex */
    enum TextPathSpacing {
        auto,
        exact
    }

    TextProperties() {
    }
}
