package com.amazon.alexa.fitness.message;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.fitness.api.afx.FitnessRoutesKt;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.util.GsonUtilsKt;
import com.amazon.alexa.mode.Constants;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessHomeCardPublisher.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001:\u0001\u0010B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/message/FitnessHomeCardPublisher;", "", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/logs/ILog;)V", "componentRegistry", "Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;", "fitnessCardPayload", "", "createFitnessCardMessage", "Lcom/amazon/alexa/eventbus/api/Message;", "show", "", "hideFitnessHomeCard", "", "showFitnessHomeCard", "FitnessCard", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public class FitnessHomeCardPublisher {
    private final ComponentRegistry componentRegistry;
    private final String fitnessCardPayload;
    private final ILog log;

    /* compiled from: FitnessHomeCardPublisher.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/fitness/message/FitnessHomeCardPublisher$FitnessCard;", "", "id", "", "customTemplateRoute", "templateType", "contentProvider", "contentType", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getContentProvider", "()Ljava/lang/String;", "getContentType", "getCustomTemplateRoute", "getId", "getTemplateType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    private static final class FitnessCard {
        @NotNull
        private final String contentProvider;
        @NotNull
        private final String contentType;
        @NotNull
        private final String customTemplateRoute;
        @NotNull
        private final String id;
        @NotNull
        private final String templateType;

        public FitnessCard(@NotNull String id, @NotNull String customTemplateRoute, @NotNull String templateType, @NotNull String contentProvider, @NotNull String contentType) {
            Intrinsics.checkParameterIsNotNull(id, "id");
            Intrinsics.checkParameterIsNotNull(customTemplateRoute, "customTemplateRoute");
            Intrinsics.checkParameterIsNotNull(templateType, "templateType");
            Intrinsics.checkParameterIsNotNull(contentProvider, "contentProvider");
            Intrinsics.checkParameterIsNotNull(contentType, "contentType");
            this.id = id;
            this.customTemplateRoute = customTemplateRoute;
            this.templateType = templateType;
            this.contentProvider = contentProvider;
            this.contentType = contentType;
        }

        public static /* synthetic */ FitnessCard copy$default(FitnessCard fitnessCard, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = fitnessCard.id;
            }
            if ((i & 2) != 0) {
                str2 = fitnessCard.customTemplateRoute;
            }
            String str6 = str2;
            if ((i & 4) != 0) {
                str3 = fitnessCard.templateType;
            }
            String str7 = str3;
            if ((i & 8) != 0) {
                str4 = fitnessCard.contentProvider;
            }
            String str8 = str4;
            if ((i & 16) != 0) {
                str5 = fitnessCard.contentType;
            }
            return fitnessCard.copy(str, str6, str7, str8, str5);
        }

        @NotNull
        public final String component1() {
            return this.id;
        }

        @NotNull
        public final String component2() {
            return this.customTemplateRoute;
        }

        @NotNull
        public final String component3() {
            return this.templateType;
        }

        @NotNull
        public final String component4() {
            return this.contentProvider;
        }

        @NotNull
        public final String component5() {
            return this.contentType;
        }

        @NotNull
        public final FitnessCard copy(@NotNull String id, @NotNull String customTemplateRoute, @NotNull String templateType, @NotNull String contentProvider, @NotNull String contentType) {
            Intrinsics.checkParameterIsNotNull(id, "id");
            Intrinsics.checkParameterIsNotNull(customTemplateRoute, "customTemplateRoute");
            Intrinsics.checkParameterIsNotNull(templateType, "templateType");
            Intrinsics.checkParameterIsNotNull(contentProvider, "contentProvider");
            Intrinsics.checkParameterIsNotNull(contentType, "contentType");
            return new FitnessCard(id, customTemplateRoute, templateType, contentProvider, contentType);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof FitnessCard)) {
                    return false;
                }
                FitnessCard fitnessCard = (FitnessCard) obj;
                return Intrinsics.areEqual(this.id, fitnessCard.id) && Intrinsics.areEqual(this.customTemplateRoute, fitnessCard.customTemplateRoute) && Intrinsics.areEqual(this.templateType, fitnessCard.templateType) && Intrinsics.areEqual(this.contentProvider, fitnessCard.contentProvider) && Intrinsics.areEqual(this.contentType, fitnessCard.contentType);
            }
            return true;
        }

        @NotNull
        public final String getContentProvider() {
            return this.contentProvider;
        }

        @NotNull
        public final String getContentType() {
            return this.contentType;
        }

        @NotNull
        public final String getCustomTemplateRoute() {
            return this.customTemplateRoute;
        }

        @NotNull
        public final String getId() {
            return this.id;
        }

        @NotNull
        public final String getTemplateType() {
            return this.templateType;
        }

        public int hashCode() {
            String str = this.id;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.customTemplateRoute;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.templateType;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.contentProvider;
            int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
            String str5 = this.contentType;
            if (str5 != null) {
                i = str5.hashCode();
            }
            return hashCode4 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FitnessCard(id=");
            outline107.append(this.id);
            outline107.append(", customTemplateRoute=");
            outline107.append(this.customTemplateRoute);
            outline107.append(", templateType=");
            outline107.append(this.templateType);
            outline107.append(", contentProvider=");
            outline107.append(this.contentProvider);
            outline107.append(", contentType=");
            return GeneratedOutlineSupport1.outline91(outline107, this.contentType, ")");
        }

        public /* synthetic */ FitnessCard(String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? Constants.HOME_CHANNEL_CUSTOM_TEMPLATE_TYPE : str3, (i & 8) != 0 ? "FitnessNativeHomeCard" : str4, (i & 16) != 0 ? "FitnessNativeHomeCard" : str5);
        }
    }

    @Inject
    public FitnessHomeCardPublisher(@NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.log = log;
        this.fitnessCardPayload = GsonUtilsKt.toJson(new FitnessCard("FitnessNativeHomeCard", FitnessRoutesKt.FITNESS_HOME_CARD_ROUTE, null, null, null, 28, null));
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(componentRegistry, "ComponentRegistry.getInstance()");
        this.componentRegistry = componentRegistry;
    }

    private final Message createFitnessCardMessage(boolean z) {
        String str = z ? Constants.HOME_CHANNEL_EVENT_ADD_CARD : Constants.HOME_CHANNEL_EVENT_REMOVE_CARD;
        Message.Builder builder = new Message.Builder();
        builder.setEventType(str);
        builder.setPayload(this.fitnessCardPayload);
        Message build = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Message.Builder().apply …ayload)\n        }.build()");
        return build;
    }

    public void hideFitnessHomeCard() {
        ILog.DefaultImpls.info$default(this.log, "FitnessHomeCardPublisher", "publish hide fitness card message", null, 4, null);
        ((EventBus) this.componentRegistry.get(EventBus.class).get()).publish(createFitnessCardMessage(false));
    }

    public void showFitnessHomeCard() {
        ILog.DefaultImpls.info$default(this.log, "FitnessHomeCardPublisher", "publish show fitness card message", null, 4, null);
        ((EventBus) this.componentRegistry.get(EventBus.class).get()).publish(createFitnessCardMessage(true));
    }
}
