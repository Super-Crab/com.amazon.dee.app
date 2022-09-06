package com.amazon.alexa.eventing;

import com.amazon.alexa.eventing.Listen;
@Deprecated
/* loaded from: classes7.dex */
public final class Listen {

    /* loaded from: classes7.dex */
    public interface Cancelable {
        void cancel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class ListenOnce<T> {
        private Event<T> event;
        private EventHandler<T> handler;
        private EventSubscription subscription;

        ListenOnce(Event<T> event) {
            this.event = event;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onEvent(EventArgs<T> eventArgs) {
            EventHandler<T> eventHandler = this.handler;
            if (eventHandler == null) {
                return;
            }
            eventHandler.onEvent(eventArgs);
            unregister();
        }

        private void unregister() {
            EventSubscription eventSubscription = this.subscription;
            if (eventSubscription == null) {
                return;
            }
            eventSubscription.unsubscribe();
            this.handler = null;
            this.event = null;
            this.subscription = null;
        }

        public void cancel() {
            unregister();
        }

        public Cancelable listen(EventHandler<T> eventHandler) {
            if (this.handler == null) {
                this.handler = eventHandler;
                this.subscription = this.event.subscribe(new EventHandler() { // from class: com.amazon.alexa.eventing.-$$Lambda$Listen$ListenOnce$MqiJVzWygzvyobXx2xztjSmyEqY
                    @Override // com.amazon.alexa.eventing.EventHandler
                    public final void onEvent(EventArgs eventArgs) {
                        Listen.ListenOnce.this.onEvent(eventArgs);
                    }
                });
                return new Cancelable() { // from class: com.amazon.alexa.eventing.-$$Lambda$S2-8Wdpekl-xAJVsy37LCiwMJFE
                    @Override // com.amazon.alexa.eventing.Listen.Cancelable
                    public final void cancel() {
                        Listen.ListenOnce.this.cancel();
                    }
                };
            }
            throw new RuntimeException("There is already a listener attached.");
        }
    }

    private Listen() {
    }

    public static <T> Cancelable once(Event<T> event, EventHandler<T> eventHandler) {
        return new ListenOnce(event).listen(eventHandler);
    }
}
