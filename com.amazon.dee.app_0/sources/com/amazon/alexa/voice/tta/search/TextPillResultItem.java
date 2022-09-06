package com.amazon.alexa.voice.tta.search;

import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
import com.amazon.alexa.voice.ui.tta.search.PillResultItem;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TextPillResultItem.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010\f\u001a\u00020\u0003H\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0005H\u0016J\b\u0010\u0010\u001a\u00020\tH\u0016R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/voice/tta/search/TextPillResultItem;", "Lcom/amazon/alexa/voice/ui/tta/search/PillResultItem;", "itemText", "", "type", "", "itemRoute", "id", "fromSimba", "", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getId", "getItemRoute", "getItemText", "getType", "isFromSimba", "Companion", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class TextPillResultItem implements PillResultItem {
    public static final Companion Companion = new Companion(null);
    private final Boolean fromSimba;
    private final String id;
    private final String itemRoute;
    private final String itemText;
    private final int type;

    /* compiled from: TextPillResultItem.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/voice/tta/search/TextPillResultItem$Companion;", "", "()V", "getResultType", "", "type", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        public final int getResultType(@NotNull String type) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            Locale locale = Locale.ROOT;
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ROOT");
            String lowerCase = type.toLowerCase(locale);
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            int hashCode = lowerCase.hashCode();
            if (hashCode != -2140169871) {
                return (hashCode == 1167707629 && lowerCase.equals("app_page")) ? 1 : 2;
            }
            lowerCase.equals(InteractionType.UTTERANCE);
            return 2;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TextPillResultItem(@NotNull String itemText, int i, @Nullable String str, @NotNull String id, @Nullable Boolean bool) {
        Intrinsics.checkParameterIsNotNull(itemText, "itemText");
        Intrinsics.checkParameterIsNotNull(id, "id");
        this.itemText = itemText;
        this.type = i;
        this.itemRoute = str;
        this.id = id;
        this.fromSimba = bool;
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.ResultItem
    @NotNull
    public String getId() {
        return this.id;
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.PillResultItem
    @Nullable
    public String getItemRoute() {
        return this.itemRoute;
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.PillResultItem
    @NotNull
    public String getItemText() {
        return this.itemText;
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.ResultItem
    public int getType() {
        return this.type;
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.PillResultItem
    public boolean isFromSimba() {
        Boolean bool = this.fromSimba;
        if (bool == null) {
            Intrinsics.throwNpe();
        }
        return bool.booleanValue();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ TextPillResultItem(java.lang.String r7, int r8, java.lang.String r9, java.lang.String r10, java.lang.Boolean r11, int r12, kotlin.jvm.internal.DefaultConstructorMarker r13) {
        /*
            r6 = this;
            r13 = r12 & 4
            if (r13 == 0) goto L5
            r9 = 0
        L5:
            r3 = r9
            r9 = r12 & 8
            if (r9 == 0) goto L17
            java.util.UUID r9 = java.util.UUID.randomUUID()
            java.lang.String r10 = r9.toString()
            java.lang.String r9 = "UUID.randomUUID().toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r9)
        L17:
            r4 = r10
            r9 = r12 & 16
            if (r9 == 0) goto L21
            r9 = 1
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r9)
        L21:
            r5 = r11
            r0 = r6
            r1 = r7
            r2 = r8
            r0.<init>(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.voice.tta.search.TextPillResultItem.<init>(java.lang.String, int, java.lang.String, java.lang.String, java.lang.Boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
