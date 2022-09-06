package com.amazon.alexa.fitness.service.hds.model;

import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: GraphQLRequestBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/GraphQLRequestBuilder;", "", "()V", "Companion", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class GraphQLRequestBuilder {
    public static final Companion Companion = new Companion(null);

    /* compiled from: GraphQLRequestBuilder.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ \u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/GraphQLRequestBuilder$Companion;", "", "()V", "buildBatchPutQuantitySessionRecordsMutation", "", "batchQuantitySessionRecords", "Lcom/amazon/alexa/fitness/service/hds/model/BatchQuantitySessionRecords;", "buildCreateSessionMutation", "session", "Lcom/amazon/alexa/fitness/service/hds/model/Session;", "buildMutation", NotificationConstants.REQUEST_TYPE, "Lcom/amazon/alexa/fitness/service/hds/model/RequestType;", "input", "outputFieldSelection", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        private final String buildMutation(RequestType requestType, String str, String str2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("mutation{");
            outline107.append(requestType.getValue());
            outline107.append("(input:");
            outline107.append(str);
            outline107.append(')');
            return GeneratedOutlineSupport1.outline89(outline107, str2, JsonReaderKt.END_OBJ);
        }

        @NotNull
        public final String buildBatchPutQuantitySessionRecordsMutation(@NotNull BatchQuantitySessionRecords batchQuantitySessionRecords) {
            Intrinsics.checkParameterIsNotNull(batchQuantitySessionRecords, "batchQuantitySessionRecords");
            return buildMutation(RequestType.BATCH_PUT_QUANTITY_SESSION_RECORDS, batchQuantitySessionRecords.buildBatchPutQuantitySessionRecordsInput(), BatchQuantitySessionRecords.Companion.getOutputFieldSelection());
        }

        @NotNull
        public final String buildCreateSessionMutation(@NotNull Session session) {
            Intrinsics.checkParameterIsNotNull(session, "session");
            return buildMutation(RequestType.CREATE_SESSION, session.buildCreateSessionInput(), Session.Companion.getOutputFieldSelection());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
