package com.amazon.alexa.accessory.capabilities.state;

import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.capabilities.state.StateCapability;
import com.amazon.alexa.accessory.capabilities.state.exceptions.StateException;
import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.MappedResponseAction;
import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableStream;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.state.PhoneStateSupplier;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessory.repositories.state.StateProducer;
import com.amazon.alexa.accessory.repositories.state.StateProvider;
import com.amazon.alexa.accessory.streams.control.ControlMessage;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.io.IOException;
import org.reactivestreams.Publisher;
/* loaded from: classes.dex */
public final class StateCapability extends AccessoryCapability {
    private final StateActionHandler actionHandler;
    private final PhoneStateSupplier phoneStateSupplier;
    private final StateProducer stateProducer;
    private final StateProvider stateProvider;
    private ControlStream stream;
    private Disposable synchronizePhoneStateDisposable;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class StateActionHandler implements StateProducer.ActionHandler {
        private final ActionQueue getQueue = new ActionQueue();
        private final ActionQueue setQueue = new ActionQueue();

        public StateActionHandler() {
        }

        void cancelAllActions() {
            this.getQueue.cancelAll();
            this.setQueue.cancelAll();
        }

        @Override // com.amazon.alexa.accessory.repositories.state.StateProducer.ActionHandler
        public void handleGetState(StateFeature stateFeature, Producer.Result<StateOuterClass.State> result) {
            Preconditions.notNull(stateFeature, "feature");
            Preconditions.notNull(result, "result");
            this.getQueue.enqueue(new SuccessResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_STATE).setGetState(StateOuterClass.GetState.newBuilder().setFeature(stateFeature.toInteger())).mo10084build()), StateCapability.this.stream, result, Accessories.Response.PayloadCase.STATE, $$Lambda$5CxEGZ1MnHC3ydwtSvcBS_QDIE.INSTANCE));
        }

        @Override // com.amazon.alexa.accessory.repositories.state.StateProducer.ActionHandler
        public void handleSetState(final StateOuterClass.State state, Producer.Result<CompletableResult.Value> result) {
            Preconditions.notNull(state, "state");
            Preconditions.notNull(result, "result");
            this.setQueue.enqueue(new MappedResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.SET_STATE).setSetState(StateOuterClass.SetState.newBuilder().setState(state)).mo10084build()), StateCapability.this.stream, result, new MappedResponseAction.Mapper() { // from class: com.amazon.alexa.accessory.capabilities.state.-$$Lambda$StateCapability$StateActionHandler$ICk9aGz9WsBuEtW2ri5oZ9XQQ7U
                @Override // com.amazon.alexa.accessory.internal.MappedResponseAction.Mapper
                public final Object map(Accessories.Response response) {
                    return StateCapability.StateActionHandler.this.lambda$handleSetState$0$StateCapability$StateActionHandler(state, response);
                }
            }));
        }

        public /* synthetic */ CompletableResult.Value lambda$handleSetState$0$StateCapability$StateActionHandler(StateOuterClass.State state, Accessories.Response response) throws IOException {
            if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
                StateCapability.this.stateProvider.provideState(state);
                return CompletableResult.Value.complete();
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Accessory returned error ");
            outline107.append(response.getErrorCode());
            outline107.append(" in response to SetState");
            throw new StateException(outline107.toString(), response.getErrorCode());
        }
    }

    public StateCapability(StateProducer stateProducer, StateProvider stateProvider, PhoneStateSupplier phoneStateSupplier) {
        Preconditions.notNull(stateProducer, "stateProducer");
        Preconditions.notNull(stateProvider, "stateProvider");
        Preconditions.notNull(phoneStateSupplier, "phoneStateSupplier");
        this.stateProducer = stateProducer;
        this.stateProvider = stateProvider;
        this.phoneStateSupplier = phoneStateSupplier;
        this.actionHandler = new StateActionHandler();
    }

    private static ControlMessage createSynchronizeStateMessage(StateOuterClass.State state) {
        return new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.SYNCHRONIZE_STATE).setSynchronizeState(StateOuterClass.SynchronizeState.newBuilder().setState(state)).mo10084build());
    }

    private ControlMessageHandler<StateOuterClass.GetState> getGetPhoneStateHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.state.-$$Lambda$StateCapability$yp5UWZE2t4Sh3hZl8acip5xS_0M
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                StateCapability.this.lambda$getGetPhoneStateHandler$6$StateCapability(controlStream, command, (StateOuterClass.GetState) obj);
            }
        };
    }

    private ControlMessageHandler<StateOuterClass.SetState> getSetPhoneStateHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.state.-$$Lambda$StateCapability$9PC_OHjTiuR-sDBszTTo3uSdYnU
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                StateCapability.this.lambda$getSetPhoneStateHandler$3$StateCapability(controlStream, command, (StateOuterClass.SetState) obj);
            }
        };
    }

    private ControlMessageHandler<StateOuterClass.SynchronizeState> getSynchronizeAccessoryStateHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.state.-$$Lambda$StateCapability$65b2VAI2jOlBXvw4ZzxTqm8vno0
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                StateCapability.this.lambda$getSynchronizeAccessoryStateHandler$0$StateCapability(controlStream, command, (StateOuterClass.SynchronizeState) obj);
            }
        };
    }

    static /* synthetic */ StateOuterClass.State lambda$synchronizeState$11(StateOuterClass.State state, Accessories.Response response) throws Throwable {
        return state;
    }

    private Single<StateOuterClass.State> synchronizeState(final StateOuterClass.State state) {
        return ObservableStream.dispatchSingle(this.stream, createSynchronizeStateMessage(state)).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.state.-$$Lambda$StateCapability$pI4GI5X4QnWweyzqNMib6wYgbZA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Accessories.Response response = (Accessories.Response) obj;
                return StateOuterClass.State.this;
            }
        });
    }

    private void synchronizeUpdatesFromPhoneState() {
        this.synchronizePhoneStateDisposable = this.phoneStateSupplier.queryStates().observeOn(AndroidSchedulers.mainThread()).concatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.state.-$$Lambda$StateCapability$hbmZGwwb5KxPjnEZUXfAJdm0AqU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return StateCapability.this.lambda$synchronizeUpdatesFromPhoneState$8$StateCapability((StateOuterClass.State) obj);
            }
        }).subscribe($$Lambda$StateCapability$XofcuAO_Rmj2YQlddeXzezsOTYs.INSTANCE, $$Lambda$StateCapability$V8PE_O7WTpbYh_I4KnaPVdGkPc.INSTANCE);
    }

    public /* synthetic */ void lambda$getGetPhoneStateHandler$6$StateCapability(ControlStream controlStream, Accessories.Command command, StateOuterClass.GetState getState) throws Exception {
        final int feature = getState.getFeature();
        this.phoneStateSupplier.getState(StateFeature.from(feature)).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.state.-$$Lambda$StateCapability$aBJ0K4JAlG3FO5-gMJhJZ5gMy98
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StateCapability.this.lambda$null$4$StateCapability((StateOuterClass.State) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.state.-$$Lambda$StateCapability$aRcbq2t5DrWk__9T-GYpHv4beNc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StateCapability.this.lambda$null$5$StateCapability(feature, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ void lambda$getSetPhoneStateHandler$3$StateCapability(ControlStream controlStream, Accessories.Command command, StateOuterClass.SetState setState) throws Exception {
        final StateOuterClass.State state = setState.getState();
        this.phoneStateSupplier.setState(state).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() { // from class: com.amazon.alexa.accessory.capabilities.state.-$$Lambda$StateCapability$mViS8Vfwl4boUr9OGqGmGPnkkjQ
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                StateCapability.this.lambda$null$1$StateCapability(state);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.state.-$$Lambda$StateCapability$F9IcGo1hbt1moHkwWUof1mcwO6c
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StateCapability.this.lambda$null$2$StateCapability(state, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ void lambda$getSynchronizeAccessoryStateHandler$0$StateCapability(ControlStream controlStream, Accessories.Command command, StateOuterClass.SynchronizeState synchronizeState) throws Exception {
        this.stateProvider.provideState(synchronizeState.getState());
        controlStream.respond(Accessories.Command.SYNCHRONIZE_STATE, Common.ErrorCode.SUCCESS);
    }

    public /* synthetic */ void lambda$null$1$StateCapability(StateOuterClass.State state) throws Throwable {
        this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.SET_STATE).setResponse(Accessories.Response.newBuilder().setState(state).setErrorCode(Common.ErrorCode.SUCCESS).mo10084build()).mo10084build()));
    }

    public /* synthetic */ void lambda$null$2$StateCapability(StateOuterClass.State state, Throwable th) throws Throwable {
        this.stream.respond(Accessories.Command.SET_STATE, Common.ErrorCode.UNSUPPORTED);
        Logger.e("Failed to set phone state %s", th, state);
    }

    public /* synthetic */ void lambda$null$4$StateCapability(StateOuterClass.State state) throws Throwable {
        this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_STATE).setResponse(Accessories.Response.newBuilder().setState(state).setErrorCode(Common.ErrorCode.SUCCESS).mo10084build()).mo10084build()));
    }

    public /* synthetic */ void lambda$null$5$StateCapability(int i, Throwable th) throws Throwable {
        this.stream.respond(Accessories.Command.GET_STATE, Common.ErrorCode.UNSUPPORTED);
        Logger.e("Failed to get phone state for feature %d", th, Integer.valueOf(i));
    }

    public /* synthetic */ Publisher lambda$synchronizeUpdatesFromPhoneState$8$StateCapability(final StateOuterClass.State state) throws Throwable {
        return synchronizeState(state).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.state.-$$Lambda$StateCapability$mKUpmwhtQBkW_2QqATEokjZpOho
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Logger.e("Failed to synchronize state %s with accessory", (Throwable) obj, StateOuterClass.State.this);
            }
        }).onErrorReturnItem(state).toFlowable();
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        ObservableUtils.dispose(this.synchronizePhoneStateDisposable);
        this.stateProducer.detachActionHandler(this.actionHandler);
        this.actionHandler.cancelAllActions();
        accessoryDescriptor.remove(this.stream);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.stateProducer.attachActionHandler(this.actionHandler);
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.stream.addMessageHandler(Accessories.Command.SYNCHRONIZE_STATE, getSynchronizeAccessoryStateHandler());
        this.stream.addMessageHandler(Accessories.Command.GET_STATE, getGetPhoneStateHandler());
        this.stream.addMessageHandler(Accessories.Command.SET_STATE, getSetPhoneStateHandler());
        synchronizeUpdatesFromPhoneState();
        accessoryDescriptor.add(this.stream);
    }
}
