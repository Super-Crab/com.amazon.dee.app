package org.greenrobot.eventbus;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;
/* loaded from: classes5.dex */
public class EventBus {
    public static String TAG = "EventBus";
    static volatile EventBus defaultInstance;
    private final AsyncPoster asyncPoster;
    private final BackgroundPoster backgroundPoster;
    private final ThreadLocal<PostingThreadState> currentPostingThreadState;
    private final boolean eventInheritance;
    private final ExecutorService executorService;
    private final int indexCount;
    private final boolean logNoSubscriberMessages;
    private final boolean logSubscriberExceptions;
    private final Logger logger;
    private final Poster mainThreadPoster;
    private final MainThreadSupport mainThreadSupport;
    private final boolean sendNoSubscriberEvent;
    private final boolean sendSubscriberExceptionEvent;
    private final Map<Class<?>, Object> stickyEvents;
    private final SubscriberMethodFinder subscriberMethodFinder;
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType;
    private final boolean throwSubscriberException;
    private final Map<Object, List<Class<?>>> typesBySubscriber;
    private static final EventBusBuilder DEFAULT_BUILDER = new EventBusBuilder();
    private static final Map<Class<?>, List<Class<?>>> eventTypesCache = new HashMap();

    /* renamed from: org.greenrobot.eventbus.EventBus$2  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$greenrobot$eventbus$ThreadMode = new int[ThreadMode.values().length];

        static {
            try {
                $SwitchMap$org$greenrobot$eventbus$ThreadMode[ThreadMode.POSTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$greenrobot$eventbus$ThreadMode[ThreadMode.MAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$greenrobot$eventbus$ThreadMode[ThreadMode.MAIN_ORDERED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$greenrobot$eventbus$ThreadMode[ThreadMode.BACKGROUND.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$greenrobot$eventbus$ThreadMode[ThreadMode.ASYNC.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes5.dex */
    interface PostCallback {
        void onPostCompleted(List<SubscriberExceptionEvent> list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class PostingThreadState {
        boolean canceled;
        Object event;
        final List<Object> eventQueue = new ArrayList();
        boolean isMainThread;
        boolean isPosting;
        Subscription subscription;

        PostingThreadState() {
        }
    }

    public EventBus() {
        this(DEFAULT_BUILDER);
    }

    static void addInterfaces(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                addInterfaces(list, cls.getInterfaces());
            }
        }
    }

    public static EventBusBuilder builder() {
        return new EventBusBuilder();
    }

    private void checkPostStickyEventToSubscription(Subscription subscription, Object obj) {
        if (obj != null) {
            postToSubscription(subscription, obj, isMainThread());
        }
    }

    public static void clearCaches() {
        SubscriberMethodFinder.clearCaches();
        eventTypesCache.clear();
    }

    public static EventBus getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventBus();
                }
            }
        }
        return defaultInstance;
    }

    private void handleSubscriberException(Subscription subscription, Object obj, Throwable th) {
        if (obj instanceof SubscriberExceptionEvent) {
            if (!this.logSubscriberExceptions) {
                return;
            }
            Logger logger = this.logger;
            Level level = Level.SEVERE;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SubscriberExceptionEvent subscriber ");
            outline107.append(subscription.subscriber.getClass());
            outline107.append(" threw an exception");
            logger.log(level, outline107.toString(), th);
            SubscriberExceptionEvent subscriberExceptionEvent = (SubscriberExceptionEvent) obj;
            Logger logger2 = this.logger;
            Level level2 = Level.SEVERE;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Initial event ");
            outline1072.append(subscriberExceptionEvent.causingEvent);
            outline1072.append(" caused exception in ");
            outline1072.append(subscriberExceptionEvent.causingSubscriber);
            logger2.log(level2, outline1072.toString(), subscriberExceptionEvent.throwable);
        } else if (!this.throwSubscriberException) {
            if (this.logSubscriberExceptions) {
                Logger logger3 = this.logger;
                Level level3 = Level.SEVERE;
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Could not dispatch event: ");
                outline1073.append(obj.getClass());
                outline1073.append(" to subscribing class ");
                outline1073.append(subscription.subscriber.getClass());
                logger3.log(level3, outline1073.toString(), th);
            }
            if (!this.sendSubscriberExceptionEvent) {
                return;
            }
            post(new SubscriberExceptionEvent(this, th, obj, subscription.subscriber));
        } else {
            throw new EventBusException("Invoking subscriber failed", th);
        }
    }

    private boolean isMainThread() {
        MainThreadSupport mainThreadSupport = this.mainThreadSupport;
        if (mainThreadSupport != null) {
            return mainThreadSupport.isMainThread();
        }
        return true;
    }

    private static List<Class<?>> lookupAllEventTypes(Class<?> cls) {
        List<Class<?>> list;
        synchronized (eventTypesCache) {
            list = eventTypesCache.get(cls);
            if (list == null) {
                list = new ArrayList<>();
                for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    addInterfaces(list, cls2.getInterfaces());
                }
                eventTypesCache.put(cls, list);
            }
        }
        return list;
    }

    private void postSingleEvent(Object obj, PostingThreadState postingThreadState) throws Error {
        boolean postSingleEventForEventType;
        Class<?> cls = obj.getClass();
        if (this.eventInheritance) {
            List<Class<?>> lookupAllEventTypes = lookupAllEventTypes(cls);
            int size = lookupAllEventTypes.size();
            postSingleEventForEventType = false;
            for (int i = 0; i < size; i++) {
                postSingleEventForEventType |= postSingleEventForEventType(obj, postingThreadState, lookupAllEventTypes.get(i));
            }
        } else {
            postSingleEventForEventType = postSingleEventForEventType(obj, postingThreadState, cls);
        }
        if (!postSingleEventForEventType) {
            if (this.logNoSubscriberMessages) {
                this.logger.log(Level.FINE, "No subscribers registered for event " + cls);
            }
            if (!this.sendNoSubscriberEvent || cls == NoSubscriberEvent.class || cls == SubscriberExceptionEvent.class) {
                return;
            }
            post(new NoSubscriberEvent(this, obj));
        }
    }

    private boolean postSingleEventForEventType(Object obj, PostingThreadState postingThreadState, Class<?> cls) {
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator<Subscription> it2 = copyOnWriteArrayList.iterator();
        while (it2.hasNext()) {
            Subscription next = it2.next();
            postingThreadState.event = obj;
            postingThreadState.subscription = next;
            try {
                postToSubscription(next, obj, postingThreadState.isMainThread);
                if (postingThreadState.canceled) {
                    return true;
                }
            } finally {
                postingThreadState.event = null;
                postingThreadState.subscription = null;
                postingThreadState.canceled = false;
            }
        }
        return true;
    }

    private void postToSubscription(Subscription subscription, Object obj, boolean z) {
        int ordinal = subscription.subscriberMethod.threadMode.ordinal();
        if (ordinal == 0) {
            invokeSubscriber(subscription, obj);
        } else if (ordinal == 1) {
            if (z) {
                invokeSubscriber(subscription, obj);
            } else {
                this.mainThreadPoster.enqueue(subscription, obj);
            }
        } else if (ordinal == 2) {
            Poster poster = this.mainThreadPoster;
            if (poster != null) {
                poster.enqueue(subscription, obj);
            } else {
                invokeSubscriber(subscription, obj);
            }
        } else if (ordinal == 3) {
            if (z) {
                this.backgroundPoster.enqueue(subscription, obj);
            } else {
                invokeSubscriber(subscription, obj);
            }
        } else if (ordinal == 4) {
            this.asyncPoster.enqueue(subscription, obj);
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown thread mode: ");
            outline107.append(subscription.subscriberMethod.threadMode);
            throw new IllegalStateException(outline107.toString());
        }
    }

    private void subscribe(Object obj, SubscriberMethod subscriberMethod) {
        Class<?> cls = subscriberMethod.eventType;
        Subscription subscription = new Subscription(obj, subscriberMethod);
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.subscriptionsByEventType.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(subscription)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Subscriber ");
            outline107.append(obj.getClass());
            outline107.append(" already registered to event ");
            outline107.append(cls);
            throw new EventBusException(outline107.toString());
        }
        int size = copyOnWriteArrayList.size();
        for (int i = 0; i <= size; i++) {
            if (i == size || subscriberMethod.priority > copyOnWriteArrayList.get(i).subscriberMethod.priority) {
                copyOnWriteArrayList.add(i, subscription);
                break;
            }
        }
        List<Class<?>> list = this.typesBySubscriber.get(obj);
        if (list == null) {
            list = new ArrayList<>();
            this.typesBySubscriber.put(obj, list);
        }
        list.add(cls);
        if (subscriberMethod.sticky) {
            if (this.eventInheritance) {
                for (Map.Entry<Class<?>, Object> entry : this.stickyEvents.entrySet()) {
                    if (cls.isAssignableFrom(entry.getKey())) {
                        checkPostStickyEventToSubscription(subscription, entry.getValue());
                    }
                }
                return;
            }
            checkPostStickyEventToSubscription(subscription, this.stickyEvents.get(cls));
        }
    }

    private void unsubscribeByEventType(Object obj, Class<?> cls) {
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        if (copyOnWriteArrayList != null) {
            int size = copyOnWriteArrayList.size();
            int i = 0;
            while (i < size) {
                Subscription subscription = copyOnWriteArrayList.get(i);
                if (subscription.subscriber == obj) {
                    subscription.active = false;
                    copyOnWriteArrayList.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
        }
    }

    public void cancelEventDelivery(Object obj) {
        PostingThreadState postingThreadState = this.currentPostingThreadState.get();
        if (postingThreadState.isPosting) {
            if (obj != null) {
                if (postingThreadState.event == obj) {
                    if (postingThreadState.subscription.subscriberMethod.threadMode == ThreadMode.POSTING) {
                        postingThreadState.canceled = true;
                        return;
                    }
                    throw new EventBusException(" event handlers may only abort the incoming event");
                }
                throw new EventBusException("Only the currently handled event may be aborted");
            }
            throw new EventBusException("Event may not be null");
        }
        throw new EventBusException("This method may only be called from inside event handling methods on the posting thread");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExecutorService getExecutorService() {
        return this.executorService;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public <T> T getStickyEvent(Class<T> cls) {
        T cast;
        synchronized (this.stickyEvents) {
            cast = cls.cast(this.stickyEvents.get(cls));
        }
        return cast;
    }

    public boolean hasSubscriberForEvent(Class<?> cls) {
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList;
        List<Class<?>> lookupAllEventTypes = lookupAllEventTypes(cls);
        if (lookupAllEventTypes != null) {
            int size = lookupAllEventTypes.size();
            for (int i = 0; i < size; i++) {
                Class<?> cls2 = lookupAllEventTypes.get(i);
                synchronized (this) {
                    copyOnWriteArrayList = this.subscriptionsByEventType.get(cls2);
                }
                if (copyOnWriteArrayList != null && !copyOnWriteArrayList.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void invokeSubscriber(PendingPost pendingPost) {
        Object obj = pendingPost.event;
        Subscription subscription = pendingPost.subscription;
        PendingPost.releasePendingPost(pendingPost);
        if (subscription.active) {
            invokeSubscriber(subscription, obj);
        }
    }

    public synchronized boolean isRegistered(Object obj) {
        return this.typesBySubscriber.containsKey(obj);
    }

    public void post(Object obj) {
        PostingThreadState postingThreadState = this.currentPostingThreadState.get();
        List<Object> list = postingThreadState.eventQueue;
        list.add(obj);
        if (!postingThreadState.isPosting) {
            postingThreadState.isMainThread = isMainThread();
            postingThreadState.isPosting = true;
            if (postingThreadState.canceled) {
                throw new EventBusException("Internal error. Abort state was not reset");
            }
            while (true) {
                try {
                    if (list.isEmpty()) {
                        return;
                    }
                    postSingleEvent(list.remove(0), postingThreadState);
                } finally {
                    postingThreadState.isPosting = false;
                    postingThreadState.isMainThread = false;
                }
            }
        }
    }

    public void postSticky(Object obj) {
        synchronized (this.stickyEvents) {
            this.stickyEvents.put(obj.getClass(), obj);
        }
        post(obj);
    }

    public void register(Object obj) {
        List<SubscriberMethod> findSubscriberMethods = this.subscriberMethodFinder.findSubscriberMethods(obj.getClass());
        synchronized (this) {
            for (SubscriberMethod subscriberMethod : findSubscriberMethods) {
                subscribe(obj, subscriberMethod);
            }
        }
    }

    public void removeAllStickyEvents() {
        synchronized (this.stickyEvents) {
            this.stickyEvents.clear();
        }
    }

    public <T> T removeStickyEvent(Class<T> cls) {
        T cast;
        synchronized (this.stickyEvents) {
            cast = cls.cast(this.stickyEvents.remove(cls));
        }
        return cast;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EventBus[indexCount=");
        outline107.append(this.indexCount);
        outline107.append(", eventInheritance=");
        return GeneratedOutlineSupport1.outline97(outline107, this.eventInheritance, "]");
    }

    public synchronized void unregister(Object obj) {
        List<Class<?>> list = this.typesBySubscriber.get(obj);
        if (list != null) {
            for (Class<?> cls : list) {
                unsubscribeByEventType(obj, cls);
            }
            this.typesBySubscriber.remove(obj);
        } else {
            Logger logger = this.logger;
            Level level = Level.WARNING;
            logger.log(level, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventBus(EventBusBuilder eventBusBuilder) {
        this.currentPostingThreadState = new ThreadLocal<PostingThreadState>() { // from class: org.greenrobot.eventbus.EventBus.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public PostingThreadState initialValue() {
                return new PostingThreadState();
            }
        };
        this.logger = eventBusBuilder.getLogger();
        this.subscriptionsByEventType = new HashMap();
        this.typesBySubscriber = new HashMap();
        this.stickyEvents = new ConcurrentHashMap();
        this.mainThreadSupport = eventBusBuilder.getMainThreadSupport();
        MainThreadSupport mainThreadSupport = this.mainThreadSupport;
        this.mainThreadPoster = mainThreadSupport != null ? mainThreadSupport.createPoster(this) : null;
        this.backgroundPoster = new BackgroundPoster(this);
        this.asyncPoster = new AsyncPoster(this);
        List<SubscriberInfoIndex> list = eventBusBuilder.subscriberInfoIndexes;
        this.indexCount = list != null ? list.size() : 0;
        this.subscriberMethodFinder = new SubscriberMethodFinder(eventBusBuilder.subscriberInfoIndexes, eventBusBuilder.strictMethodVerification, eventBusBuilder.ignoreGeneratedIndex);
        this.logSubscriberExceptions = eventBusBuilder.logSubscriberExceptions;
        this.logNoSubscriberMessages = eventBusBuilder.logNoSubscriberMessages;
        this.sendSubscriberExceptionEvent = eventBusBuilder.sendSubscriberExceptionEvent;
        this.sendNoSubscriberEvent = eventBusBuilder.sendNoSubscriberEvent;
        this.throwSubscriberException = eventBusBuilder.throwSubscriberException;
        this.eventInheritance = eventBusBuilder.eventInheritance;
        this.executorService = eventBusBuilder.executorService;
    }

    public boolean removeStickyEvent(Object obj) {
        synchronized (this.stickyEvents) {
            Class<?> cls = obj.getClass();
            if (obj.equals(this.stickyEvents.get(cls))) {
                this.stickyEvents.remove(cls);
                return true;
            }
            return false;
        }
    }

    void invokeSubscriber(Subscription subscription, Object obj) {
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber, obj);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unexpected exception", e);
        } catch (InvocationTargetException e2) {
            handleSubscriberException(subscription, obj, e2.getCause());
        }
    }
}
