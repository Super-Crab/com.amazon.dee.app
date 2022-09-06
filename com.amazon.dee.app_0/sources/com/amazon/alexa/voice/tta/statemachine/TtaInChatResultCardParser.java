package com.amazon.alexa.voice.tta.statemachine;

import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.voice.tta.simba.SearchResult;
import com.amazon.alexa.voice.ui.onedesign.tta.ItemRouteImpl;
import com.amazon.alexa.voice.ui.onedesign.tta.TtaInChatResultRenderedCard;
import com.amazon.alexa.voice.ui.suggestions.SuggestionAction;
import com.amazon.alexa.voice.ui.tta.search.ItemPosition;
import com.amazon.alexa.voice.ui.tta.search.ItemRoute;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TtaInChatResultCardParser.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/TtaInChatResultCardParser;", "", "()V", "Companion", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class TtaInChatResultCardParser {
    private static final String APP_PAGE = "app_page";
    public static final Companion Companion = new Companion(null);
    private static final String EXTERNAL_LINK = "external_link";
    private static final String TAG = "TICRCParser";
    private static final String UTTERANCE = "utterance";

    /* compiled from: TtaInChatResultCardParser.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0004H\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/TtaInChatResultCardParser$Companion;", "", "()V", SuggestionAction.APP_PAGE, "", "EXTERNAL_LINK", "TAG", SuggestionAction.UTTERANCE, "constructTtaInChatResultRenderedCard", "Lcom/amazon/alexa/voice/ui/onedesign/tta/TtaInChatResultRenderedCard;", "result", "Lcom/amazon/alexa/voice/tta/simba/SearchResult;", "index", "", ReactProperties.GEOFENCE_MAXIMUM_RANGE, "getItemPosition", "Lcom/amazon/alexa/voice/ui/tta/search/ItemPosition;", "getItemRoute", "Lcom/amazon/alexa/voice/ui/tta/search/ItemRoute;", "getRouteType", JsonFields.ACTION_TYPE, "getSecondMessage", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        private final ItemPosition getItemPosition(int i, int i2) {
            if (i == 0) {
                return ItemPosition.FIRST;
            }
            if (i == i2 - 1) {
                return ItemPosition.LAST;
            }
            return ItemPosition.MID;
        }

        private final ItemRoute getItemRoute(SearchResult searchResult) {
            if (Intrinsics.areEqual(searchResult.getActionType(), "utterance")) {
                return null;
            }
            return ItemRouteImpl.create(searchResult.getActionData(), searchResult.getActionType());
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0036, code lost:
            return 1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final int getRouteType(java.lang.String r4) {
            /*
                r3 = this;
                java.util.Locale r0 = java.util.Locale.ROOT
                java.lang.String r1 = "Locale.ROOT"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
                if (r4 == 0) goto L62
                java.lang.String r0 = r4.toLowerCase(r0)
                java.lang.String r1 = "(this as java.lang.String).toLowerCase(locale)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
                int r1 = r0.hashCode()
                r2 = -2140169871(0xffffffff806f9971, float:-1.0248784E-38)
                if (r1 == r2) goto L38
                r2 = -4084754(0xffffffffffc1abee, float:NaN)
                if (r1 == r2) goto L2e
                r2 = 1167707629(0x4599cded, float:4921.7407)
                if (r1 != r2) goto L42
                java.lang.String r1 = "app_page"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L42
                goto L36
            L2e:
                java.lang.String r1 = "external_link"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L42
            L36:
                r4 = 1
                goto L41
            L38:
                java.lang.String r1 = "utterance"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L42
                r4 = 2
            L41:
                return r4
            L42:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                r0.append(r4)
                java.lang.String r1 = " actionType is not supported"
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.String r2 = "TICRCParser"
                android.util.Log.e(r2, r0)
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.String r4 = com.android.tools.r8.GeneratedOutlineSupport1.outline72(r4, r1)
                r0.<init>(r4)
                throw r0
            L62:
                kotlin.TypeCastException r4 = new kotlin.TypeCastException
                java.lang.String r0 = "null cannot be cast to non-null type java.lang.String"
                r4.<init>(r0)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.voice.tta.statemachine.TtaInChatResultCardParser.Companion.getRouteType(java.lang.String):int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:24:?, code lost:
            return r5.getDescription();
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final java.lang.String getSecondMessage(com.amazon.alexa.voice.tta.simba.SearchResult r5) {
            /*
                r4 = this;
                java.lang.String r0 = r5.getActionType()
                java.util.Locale r1 = java.util.Locale.ROOT
                java.lang.String r2 = "Locale.ROOT"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
                if (r0 == 0) goto L69
                java.lang.String r1 = r0.toLowerCase(r1)
                java.lang.String r2 = "(this as java.lang.String).toLowerCase(locale)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
                int r2 = r1.hashCode()
                r3 = -2140169871(0xffffffff806f9971, float:-1.0248784E-38)
                if (r2 == r3) goto L3f
                r3 = -4084754(0xffffffffffc1abee, float:NaN)
                if (r2 == r3) goto L32
                r3 = 1167707629(0x4599cded, float:4921.7407)
                if (r2 != r3) goto L49
                java.lang.String r2 = "app_page"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L49
                goto L3a
            L32:
                java.lang.String r2 = "external_link"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L49
            L3a:
                java.lang.String r5 = r5.getDescription()
                goto L48
            L3f:
                java.lang.String r5 = "utterance"
                boolean r5 = r1.equals(r5)
                if (r5 == 0) goto L49
                r5 = 0
            L48:
                return r5
            L49:
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                r5.append(r0)
                java.lang.String r1 = " actionType is not supported"
                r5.append(r1)
                java.lang.String r5 = r5.toString()
                java.lang.String r2 = "TICRCParser"
                android.util.Log.e(r2, r5)
                java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
                java.lang.String r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline72(r0, r1)
                r5.<init>(r0)
                throw r5
            L69:
                kotlin.TypeCastException r5 = new kotlin.TypeCastException
                java.lang.String r0 = "null cannot be cast to non-null type java.lang.String"
                r5.<init>(r0)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.voice.tta.statemachine.TtaInChatResultCardParser.Companion.getSecondMessage(com.amazon.alexa.voice.tta.simba.SearchResult):java.lang.String");
        }

        @NotNull
        public final TtaInChatResultRenderedCard constructTtaInChatResultRenderedCard(@NotNull SearchResult result, int i, int i2) {
            Intrinsics.checkParameterIsNotNull(result, "result");
            return new TtaInChatResultRenderedCard(result.getResultId(), result.getTitle(), getSecondMessage(result), getItemRoute(result), getRouteType(result.getActionType()), getItemPosition(i, i2));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
