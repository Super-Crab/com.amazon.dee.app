package com.horcrux.svg;

import android.view.View;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.horcrux.svg.TextProperties;
import java.util.ArrayList;
/* loaded from: classes3.dex */
class TextLayoutAlgorithm {

    /* renamed from: com.horcrux.svg.TextLayoutAlgorithm$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor = new int[TextProperties.TextAnchor.values().length];

        static {
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor[TextProperties.TextAnchor.start.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor[TextProperties.TextAnchor.middle.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor[TextProperties.TextAnchor.end.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class CharacterInformation {
        double advance;
        char character;
        TextView element;
        int index;
        double x = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        double y = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        double rotate = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        boolean hidden = false;
        boolean middle = false;
        boolean resolved = false;
        boolean xSpecified = false;
        boolean ySpecified = false;
        boolean addressable = true;
        boolean anchoredChunk = false;
        boolean rotateSpecified = false;
        boolean firstCharacterInResolvedDescendant = false;

        CharacterInformation(int i, char c) {
            this.index = i;
            this.character = c;
        }
    }

    /* loaded from: classes3.dex */
    class LayoutInput {
        boolean horizontal;
        TextView text;

        LayoutInput() {
        }
    }

    TextLayoutAlgorithm() {
    }

    private void getSubTreeTypographicCharacterPositions(ArrayList<TextPathView> arrayList, ArrayList<TextView> arrayList2, StringBuilder sb, View view, TextPathView textPathView) {
        int i = 0;
        if (view instanceof TSpanView) {
            TSpanView tSpanView = (TSpanView) view;
            String str = tSpanView.mContent;
            if (str == null) {
                while (i < tSpanView.getChildCount()) {
                    getSubTreeTypographicCharacterPositions(arrayList, arrayList2, sb, tSpanView.getChildAt(i), textPathView);
                    i++;
                }
                return;
            }
            while (i < str.length()) {
                arrayList2.add(tSpanView);
                arrayList.add(textPathView);
                i++;
            }
            sb.append(str);
            return;
        }
        if (view instanceof TextPathView) {
            textPathView = (TextPathView) view;
        }
        while (i < textPathView.getChildCount()) {
            getSubTreeTypographicCharacterPositions(arrayList, arrayList2, sb, textPathView.getChildAt(i), textPathView);
            i++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x019d, code lost:
        if (r18 == Double.POSITIVE_INFINITY) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01c4, code lost:
        if (r8 == com.horcrux.svg.TextProperties.Direction.ltr) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01d1, code lost:
        if (r8 == com.horcrux.svg.TextProperties.Direction.ltr) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01d3, code lost:
        r2 = r2 - r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01d6, code lost:
        r2 = r2 - r16;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:152:0x02ec  */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.horcrux.svg.TextLayoutAlgorithm$1TextLengthResolver] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [android.graphics.Canvas, android.graphics.Paint] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    com.horcrux.svg.TextLayoutAlgorithm.CharacterInformation[] layoutText(com.horcrux.svg.TextLayoutAlgorithm.LayoutInput r30) {
        /*
            Method dump skipped, instructions count: 937
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TextLayoutAlgorithm.layoutText(com.horcrux.svg.TextLayoutAlgorithm$LayoutInput):com.horcrux.svg.TextLayoutAlgorithm$CharacterInformation[]");
    }
}
