package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.streams.control.ControlMessage;
import com.amazon.alexa.accessory.streams.control.ControlResponseHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.functions.Function;
/* loaded from: classes.dex */
public final class ObservableStream {
    private ObservableStream() {
        throw new IllegalStateException("No instances!");
    }

    public static Single<Accessories.Response> dispatchSingle(ControlStream controlStream, final ControlMessage controlMessage) {
        return dispatchSingleSuccessOnErrorResponse(controlStream, controlMessage).map(new Function() { // from class: com.amazon.alexa.accessory.internal.util.-$$Lambda$ObservableStream$7llCAXGRfApVSsPJb6V1yILooYM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Accessories.Response response = (Accessories.Response) obj;
                ObservableStream.lambda$dispatchSingle$0(ControlMessage.this, response);
                return response;
            }
        });
    }

    public static Single<Accessories.Response> dispatchSingleSuccessOnErrorResponse(final ControlStream controlStream, final ControlMessage controlMessage) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessory.internal.util.-$$Lambda$ObservableStream$KR8LJfY26JEdROlMHkiA62VmV7g
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                ObservableStream.lambda$dispatchSingleSuccessOnErrorResponse$2(ControlStream.this, controlMessage, singleEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Accessories.Response lambda$dispatchSingle$0(ControlMessage controlMessage, Accessories.Response response) throws Throwable {
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
            return response;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Response for ");
        sb.append(controlMessage);
        sb.append(" failed with error ");
        throw new Exception(GeneratedOutlineSupport1.outline34(response, sb));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$dispatchSingleSuccessOnErrorResponse$2(final ControlStream controlStream, ControlMessage controlMessage, final SingleEmitter singleEmitter) throws Throwable {
        final ControlResponseHandler controlResponseHandler = new ControlResponseHandler() { // from class: com.amazon.alexa.accessory.internal.util.ObservableStream.1
            @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
            public void onResponseReceived(ControlStream controlStream2, Accessories.Command command, Accessories.Response response) throws Exception {
                if (SingleEmitter.this.isDisposed()) {
                    return;
                }
                controlStream2.removeResponseHandler(this);
                SingleEmitter.this.onSuccess(response);
            }
        };
        controlStream.addResponseHandler(controlMessage.getCommand(), controlResponseHandler);
        controlStream.dispatch(controlMessage);
        singleEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.internal.util.-$$Lambda$ObservableStream$OuK9t0iCx-kzkCUdnHK3RjE06yw
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                ControlStream.this.removeResponseHandler(controlResponseHandler);
            }
        });
    }
}
