package io.ktor.http.parsing;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ParserDsl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a'\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\u0000\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a%\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00010\u000fH\u0080\b\u001a\u0014\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0003H\u0000\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0080\u0004\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0080\u0004\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0001H\u0080\u0004\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0080\u0004\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0080\u0004\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0001H\u0080\u0004\u001a\u0015\u0010\u0016\u001a\u00020\u0001*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0080\u0004¨\u0006\u0019"}, d2 = {"anyOf", "Lio/ktor/http/parsing/Grammar;", "value", "", "atLeastOne", "grammar", "many", "maybe", "Lkotlin/Function0;", "block", "Lkotlin/Function1;", "Lio/ktor/http/parsing/GrammarBuilder;", "", "Lkotlin/ExtensionFunctionType;", "flatten", "", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/http/parsing/ComplexGrammar;", GroupNotificationHelper.GROUP_TYPE_NAMED, "name", "or", "then", "to", "", "other", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ParserDslKt {
    @NotNull
    public static final Grammar anyOf(@NotNull String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        return new AnyOfGrammar(value);
    }

    @NotNull
    public static final Grammar atLeastOne(@NotNull Grammar grammar) {
        Intrinsics.checkParameterIsNotNull(grammar, "grammar");
        return new AtLeastOne(grammar);
    }

    private static final <T extends ComplexGrammar> List<Grammar> flatten(@NotNull List<? extends Grammar> list) {
        ArrayList arrayList = new ArrayList();
        for (Grammar grammar : list) {
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (grammar instanceof ComplexGrammar) {
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, ((ComplexGrammar) grammar).getGrammars());
            } else {
                arrayList.add(grammar);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final Grammar many(@NotNull Grammar grammar) {
        Intrinsics.checkParameterIsNotNull(grammar, "grammar");
        return new ManyGrammar(grammar);
    }

    @NotNull
    public static final Grammar maybe(@NotNull Grammar grammar) {
        Intrinsics.checkParameterIsNotNull(grammar, "grammar");
        return new MaybeGrammar(grammar);
    }

    @NotNull
    public static final Grammar named(@NotNull Grammar receiver$0, @NotNull String name) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new NamedGrammar(name, receiver$0);
    }

    @NotNull
    public static final Grammar or(@NotNull Grammar receiver$0, @NotNull Grammar grammar) {
        List listOf;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(grammar, "grammar");
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Grammar[]{receiver$0, grammar});
        return new OrGrammar(listOf);
    }

    @NotNull
    public static final Grammar then(@NotNull String receiver$0, @NotNull Grammar grammar) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(grammar, "grammar");
        return then(new StringGrammar(receiver$0), grammar);
    }

    @NotNull
    public static final Grammar to(char c, char c2) {
        return new RangeGrammar(c, c2);
    }

    @NotNull
    public static final Grammar maybe(@NotNull String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        return new MaybeGrammar(new StringGrammar(value));
    }

    @NotNull
    public static final Grammar or(@NotNull Grammar receiver$0, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(value, "value");
        return or(receiver$0, new StringGrammar(value));
    }

    @NotNull
    public static final Grammar then(@NotNull Grammar receiver$0, @NotNull Grammar grammar) {
        List listOf;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(grammar, "grammar");
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Grammar[]{receiver$0, grammar});
        return new SequenceGrammar(listOf);
    }

    @NotNull
    public static final Function0<Grammar> maybe(@NotNull Function1<? super GrammarBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        return new ParserDslKt$maybe$1(block);
    }

    @NotNull
    public static final Grammar or(@NotNull String receiver$0, @NotNull Grammar grammar) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(grammar, "grammar");
        return or(new StringGrammar(receiver$0), grammar);
    }

    @NotNull
    public static final Grammar then(@NotNull Grammar receiver$0, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(value, "value");
        return then(receiver$0, new StringGrammar(value));
    }
}
