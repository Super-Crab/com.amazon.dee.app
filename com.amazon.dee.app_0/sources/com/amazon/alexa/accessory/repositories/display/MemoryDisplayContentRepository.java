package com.amazon.alexa.accessory.repositories.display;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.protocol.Cardrendering;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.display.DisplayContentProducer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public final class MemoryDisplayContentRepository extends BaseProducer<DisplayContentProducer.ActionHandler> implements DisplayContentRepository, DisplayContentProducer {
    private static final String TAG = "MemoryDisplayContentRepository:";

    private List<Cardrendering.DisplayContent> getDisplayContentChunkList(String str, Cardrendering.DisplayContentType displayContentType, int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        try {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8(str);
            int size = copyFromUtf8.size();
            int i4 = ((size + i3) - 1) / i3;
            for (int i5 = 0; i5 < i4; i5++) {
                int i6 = i5 * i3;
                arrayList.add(Cardrendering.DisplayContent.newBuilder().setPayload(copyFromUtf8.substring(i6, Math.min(i6 + i3, size))).setType(displayContentType).setIndex(i5).setSequenceId(i).setSubsequenceId(i2).setTotalChunks(i4).mo10084build());
            }
        } catch (Exception e) {
            arrayList.clear();
            Logger.e("%s: Fail to get display content chunk list", e, TAG);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(Common.ErrorCode errorCode) throws Throwable {
        if (errorCode == Common.ErrorCode.SUCCESS) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Send Render Data to Accessory with ErrorCode: ");
        outline107.append(errorCode.name());
        throw new Exception(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setDisplayContentByChunks$3(AccessoryMetricsService accessoryMetricsService, Cardrendering.DisplayContentType displayContentType, Throwable th) throws Throwable {
        accessoryMetricsService.recordOccurrence(MetricsConstants.DisplayContent.SET_DISPLAY_CONTENT, displayContentType.name(), false, null);
        Logger.e("%s: Got exception when sending display content: ", th, TAG);
    }

    public /* synthetic */ CompletableSource lambda$setDisplayContentByChunks$1$MemoryDisplayContentRepository(Cardrendering.DisplayContent displayContent) throws Throwable {
        Logger.d("%s: Send Render Data to Accessory by chunk: %s", TAG, Integer.valueOf(displayContent.getIndex()));
        return sendDisplayContent(displayContent).doOnSuccess($$Lambda$MemoryDisplayContentRepository$Za1f98cQoofGgkTVv9oC1iP951E.INSTANCE).ignoreElement();
    }

    @VisibleForTesting
    Single<Common.ErrorCode> sendDisplayContent(final Cardrendering.DisplayContent displayContent) {
        final DisplayContentProducer.ActionHandler handler = getHandler();
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.display.-$$Lambda$MemoryDisplayContentRepository$LWpXhQ3Om8cdkJ85_UA6KiCdXuc
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                DisplayContentProducer.ActionHandler.this.handleSetDisplayContent(displayContent, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.display.DisplayContentRepository
    public Completable setDisplayContentByChunks(String str, final Cardrendering.DisplayContentType displayContentType, int i, int i2, int i3) {
        Preconditions.precondition(i3 > 0, "Set display content: the chunk size should be greater than 0");
        Preconditions.precondition(i3 <= 1024, "Set display content: the chunk size should be no greater than 1024");
        final AccessoryMetricsService accessoryMetricsService = AccessoryMetricsServiceHolder.getInstance().get();
        Logger.d("%s: Set display content[sequenceId: %s, subSequenceId: %s] with chunk size: %d", TAG, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        return Observable.fromIterable(getDisplayContentChunkList(str, displayContentType, i, i2, i3)).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.repositories.display.-$$Lambda$MemoryDisplayContentRepository$ZNJIPIEb10mWXA2Vd3xxAAY_Zig
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MemoryDisplayContentRepository.this.lambda$setDisplayContentByChunks$1$MemoryDisplayContentRepository((Cardrendering.DisplayContent) obj);
            }
        }).doOnComplete(new Action() { // from class: com.amazon.alexa.accessory.repositories.display.-$$Lambda$MemoryDisplayContentRepository$uUTyqF-8Zu2_BmtiJKv5hZMUZlw
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AccessoryMetricsService.this.recordOccurrence(MetricsConstants.DisplayContent.SET_DISPLAY_CONTENT, displayContentType.name(), true, null);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.repositories.display.-$$Lambda$MemoryDisplayContentRepository$6ki2MXAvhKFcRlurrHJEh_kD9VA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MemoryDisplayContentRepository.lambda$setDisplayContentByChunks$3(AccessoryMetricsService.this, displayContentType, (Throwable) obj);
            }
        });
    }
}
