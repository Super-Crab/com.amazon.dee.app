package com.amazon.tarazed.sessionmanager;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.view.WindowManager;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.ObservableField;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.tarazed.activity.ActivityLifecycleAction;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.annotations.AnnotationView;
import com.amazon.tarazed.appinfo.sessioninfo.SessionInfoSenderAndroid1P;
import com.amazon.tarazed.appinfo.sessioninfo.SessionInfoSenderFireTV;
import com.amazon.tarazed.arcus.ArcusHelper;
import com.amazon.tarazed.core.appInfo.TarazedAppInfoRequester;
import com.amazon.tarazed.core.appInfo.sessioninfo.SessionInfoSender;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notification.type.TarazedLaunchRequest;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.rotation.DeviceRotationHandler;
import com.amazon.tarazed.core.rotation.android.DisplayRotationListener;
import com.amazon.tarazed.core.session.PlaybackStateCache;
import com.amazon.tarazed.core.session.StateChangeRequestHandler;
import com.amazon.tarazed.core.session.TarazedSession;
import com.amazon.tarazed.core.session.sessionEvents.PlaybackStates;
import com.amazon.tarazed.core.sessionclient.TarazedSessionClient;
import com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache;
import com.amazon.tarazed.core.sessionclient.sessioncache.Post23SessionClientCache;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import com.amazon.tarazed.core.types.Environment;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.core.webrtc.android.WebRTCManagerHelper;
import com.amazon.tarazed.dagger.scopes.LibraryScope;
import com.amazon.tarazed.receiver.DeviceShutdownReceiver;
import com.amazon.tarazed.session.dialog.SessionDialogManagerImpl;
import com.amazon.tarazed.session.dialog.SessionEndedDialog;
import com.amazon.tarazed.type.SharedPrefsConstants;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.manager.TarazedUIManager;
import com.amazon.tarazed.ui.manager.TarazedUIManagerFactory;
import com.amazon.tarazed.ui.tv.MoveTVUIHandler;
import com.amazonaws.services.s3.internal.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedResourceManager.kt */
@LibraryScope
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¸\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0001\u0018\u0000 Ö\u00012\u00020\u0001:\u0004Õ\u0001Ö\u0001B¡\u0001\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0013\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u001e\u0012\u0006\u0010\u001f\u001a\u00020 \u0012\u0006\u0010!\u001a\u00020\"\u0012\u0006\u0010#\u001a\u00020$¢\u0006\u0002\u0010%J\u000f\u0010\u0098\u0001\u001a\u00020+H\u0000¢\u0006\u0003\b\u0099\u0001J \u0010\u009a\u0001\u001a\u00020+2\b\u0010\u009b\u0001\u001a\u00030\u009c\u0001H\u0080@ø\u0001\u0000¢\u0006\u0006\b\u009d\u0001\u0010\u009e\u0001J\u0014\u0010\u009f\u0001\u001a\u00030 \u00012\b\u0010\u009b\u0001\u001a\u00030\u009c\u0001H\u0002J\t\u0010¡\u0001\u001a\u00020+H\u0002J\n\u0010¢\u0001\u001a\u00030£\u0001H\u0002J_\u0010¤\u0001\u001a\u00020k2\b\u0010¥\u0001\u001a\u00030 \u00012\b\u0010¦\u0001\u001a\u00030§\u00012\b\u0010¨\u0001\u001a\u00030©\u00012\b\u0010ª\u0001\u001a\u00030«\u00012\b\u0010\u009b\u0001\u001a\u00030\u009c\u00012\b\u0010¬\u0001\u001a\u00030\u00ad\u00012\b\u0010®\u0001\u001a\u00030\u00ad\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\t\u0010¯\u0001\u001a\u00020+H\u0002J\t\u0010°\u0001\u001a\u00020+H\u0002J\u0013\u0010±\u0001\u001a\u00020=H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010²\u0001J\u0013\u0010³\u0001\u001a\u00020=H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010²\u0001J\u000f\u0010´\u0001\u001a\u00020+H\u0000¢\u0006\u0003\bµ\u0001J\u0018\u0010¶\u0001\u001a\u0004\u0018\u00010?H\u0080@ø\u0001\u0000¢\u0006\u0006\b·\u0001\u0010²\u0001J\u0018\u0010¸\u0001\u001a\u0004\u0018\u00010?H\u0080@ø\u0001\u0000¢\u0006\u0006\b¹\u0001\u0010²\u0001J\u0018\u0010º\u0001\u001a\u0011\u0012\u0005\u0012\u00030\u00ad\u0001\u0012\u0005\u0012\u00030\u00ad\u00010»\u0001H\u0002J\u0017\u0010¼\u0001\u001a\u0004\u0018\u00010+H\u0080@ø\u0001\u0000¢\u0006\u0005\bv\u0010²\u0001J\u000f\u0010½\u0001\u001a\u00020=H\u0000¢\u0006\u0003\b¾\u0001J\u0013\u0010¿\u0001\u001a\u00020+H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010²\u0001J\t\u0010À\u0001\u001a\u00020=H\u0002J\u000f\u0010Á\u0001\u001a\u00020=H\u0000¢\u0006\u0003\bÂ\u0001J\u000f\u0010Ã\u0001\u001a\u00020=H\u0000¢\u0006\u0003\bÄ\u0001J\u0016\u0010Å\u0001\u001a\u00020=H\u0080@ø\u0001\u0000¢\u0006\u0006\bÆ\u0001\u0010²\u0001J\u000f\u0010Ç\u0001\u001a\u00020=H\u0000¢\u0006\u0003\bÈ\u0001J\t\u0010É\u0001\u001a\u00020+H\u0002J\t\u0010Ê\u0001\u001a\u00020=H\u0002J\u001d\u0010Ë\u0001\u001a\u00020+2\b\u0010\u009b\u0001\u001a\u00030\u009c\u0001H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009e\u0001J\u0013\u0010Ì\u0001\u001a\u00020+H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010²\u0001J\u0016\u0010Í\u0001\u001a\u00020+H\u0080@ø\u0001\u0000¢\u0006\u0006\bÎ\u0001\u0010²\u0001J\u0013\u0010Ï\u0001\u001a\u00020+H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010²\u0001J\t\u0010Ð\u0001\u001a\u00020+H\u0002J\u000f\u0010Ñ\u0001\u001a\u00020+H\u0000¢\u0006\u0003\bÒ\u0001J\t\u0010Ó\u0001\u001a\u00020+H\u0002J\t\u0010Ô\u0001\u001a\u00020=H\u0002RM\u0010&\u001a&\b\u0001\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010'8\u0000@\u0000X\u0081\u000eø\u0001\u0000¢\u0006\u0016\n\u0002\u00102\u0012\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R&\u00103\u001a\u0004\u0018\u0001048\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b5\u0010-\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u0014\u0010\u0017\u001a\u00020\u0018X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u0004\u0018\u00010?X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010A\u001a\u00020BX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010G\u001a\u0004\u0018\u00010H8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bI\u0010-\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010N\u001a\u00020OX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010T\u001a\u00020=8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bU\u0010-\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\u001c\u0010Z\u001a\u0004\u0018\u00010?X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\u001a\u0010\u0002\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bc\u0010dR\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010e\u001a\u0010\u0012\f\u0012\n g*\u0004\u0018\u00010?0?0fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010iR&\u0010j\u001a\u0004\u0018\u00010k8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bl\u0010-\u001a\u0004\bm\u0010n\"\u0004\bo\u0010pR\u0014\u0010q\u001a\u00020=8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\br\u0010WR&\u0010s\u001a\u0004\u0018\u00010t8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bu\u0010-\u001a\u0004\bv\u0010w\"\u0004\bx\u0010yR\u001a\u0010z\u001a\u00020{X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010}\"\u0004\b~\u0010\u007fR\u0012\u0010\u0080\u0001\u001a\u0005\u0018\u00010\u0081\u0001X\u0082\u000e¢\u0006\u0002\n\u0000RD\u0010\u0082\u0001\u001a \b\u0001\u0012\u0005\u0012\u00030\u0084\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0083\u00018\u0000X\u0081\u0004ø\u0001\u0000¢\u0006\u0014\n\u0003\u0010\u0088\u0001\u0012\u0005\b\u0085\u0001\u0010-\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001R \u0010\u0089\u0001\u001a\u00030\u008a\u0001X\u0080.¢\u0006\u0012\n\u0000\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001\"\u0006\b\u008d\u0001\u0010\u008e\u0001R\u0016\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u0092\u00018\u0000@\u0000X\u0081\u000e¢\u0006\u0019\n\u0000\u0012\u0005\b\u0093\u0001\u0010-\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001\"\u0006\b\u0096\u0001\u0010\u0097\u0001R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006×\u0001"}, d2 = {"Lcom/amazon/tarazed/sessionmanager/TarazedResourceManager;", "", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "deviceInfo", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "eventDispatcher", "Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;", "iotManager", "Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;", "displayRotationListener", "Lcom/amazon/tarazed/core/rotation/android/DisplayRotationListener;", "viewGroupManager", "Ljavax/inject/Provider;", "Lcom/amazon/tarazed/ui/ViewGroupManager;", "moveTVUIHandler", "Lcom/amazon/tarazed/ui/tv/MoveTVUIHandler;", "arcusHelper", "Lcom/amazon/tarazed/arcus/ArcusHelper;", "sharedPreferences", "Landroid/content/SharedPreferences;", "mainLooperHandler", "Landroid/os/Handler;", "uiManagerFactory", "Lcom/amazon/tarazed/ui/manager/TarazedUIManagerFactory;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "dispatchers", "Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;", "activityTracker", "Lcom/amazon/tarazed/activity/ActivityTracker;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;Lcom/amazon/tarazed/core/rotation/android/DisplayRotationListener;Ljavax/inject/Provider;Ljavax/inject/Provider;Lcom/amazon/tarazed/arcus/ArcusHelper;Ljavax/inject/Provider;Landroid/os/Handler;Lcom/amazon/tarazed/ui/manager/TarazedUIManagerFactory;Lkotlinx/coroutines/CoroutineScope;Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;Lcom/amazon/tarazed/activity/ActivityTracker;)V", "activitySubscription", "Lkotlin/Function3;", "Landroid/app/Activity;", "Lcom/amazon/tarazed/activity/ActivityLifecycleAction;", "Lkotlin/coroutines/Continuation;", "", "activitySubscription$annotations", "()V", "getActivitySubscription$TarazedAndroidLibrary_release", "()Lkotlin/jvm/functions/Function3;", "setActivitySubscription$TarazedAndroidLibrary_release", "(Lkotlin/jvm/functions/Function3;)V", "Lkotlin/jvm/functions/Function3;", "annotationView", "Lcom/amazon/tarazed/annotations/AnnotationView;", "annotationView$annotations", "getAnnotationView$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/annotations/AnnotationView;", "setAnnotationView$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/annotations/AnnotationView;)V", "getArcusHelper$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/arcus/ArcusHelper;", "cacheContentsFetched", "", "cachedCredentials", "", "cachedLaunchRequest", "context", "Landroid/content/Context;", "getContext$TarazedAndroidLibrary_release", "()Landroid/content/Context;", "setContext$TarazedAndroidLibrary_release", "(Landroid/content/Context;)V", "deviceShutdownReceiver", "Lcom/amazon/tarazed/receiver/DeviceShutdownReceiver;", "deviceShutdownReceiver$annotations", "getDeviceShutdownReceiver$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/receiver/DeviceShutdownReceiver;", "setDeviceShutdownReceiver$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/receiver/DeviceShutdownReceiver;)V", "headsUpNotificationManager", "Lcom/amazon/tarazed/sessionmanager/HeadsUpNotificationManager;", "getHeadsUpNotificationManager$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/sessionmanager/HeadsUpNotificationManager;", "setHeadsUpNotificationManager$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/sessionmanager/HeadsUpNotificationManager;)V", "isCacheServiceBound", "isCacheServiceBound$annotations", "isCacheServiceBound$TarazedAndroidLibrary_release", "()Z", "setCacheServiceBound$TarazedAndroidLibrary_release", "(Z)V", "launchRequestString", "getLaunchRequestString$TarazedAndroidLibrary_release", "()Ljava/lang/String;", "setLaunchRequestString$TarazedAndroidLibrary_release", "(Ljava/lang/String;)V", "getLogger$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "setLogger$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "getMetricsHelper$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "playbackStateObservable", "Landroidx/databinding/ObservableField;", "kotlin.jvm.PlatformType", "getPlaybackStateObservable$TarazedAndroidLibrary_release", "()Landroidx/databinding/ObservableField;", "session", "Lcom/amazon/tarazed/core/session/TarazedSession;", "session$annotations", "getSession$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/session/TarazedSession;", "setSession$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/session/TarazedSession;)V", "sessionAlreadyExists", "getSessionAlreadyExists", "sessionCacheServiceConnection", "Landroid/content/ServiceConnection;", "sessionCacheServiceConnection$annotations", "getSessionCacheServiceConnection$TarazedAndroidLibrary_release", "()Landroid/content/ServiceConnection;", "setSessionCacheServiceConnection$TarazedAndroidLibrary_release", "(Landroid/content/ServiceConnection;)V", "sessionClientCacheService", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/ISessionClientCache;", "getSessionClientCacheService$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/sessionclient/sessioncache/ISessionClientCache;", "setSessionClientCacheService$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/sessionclient/sessioncache/ISessionClientCache;)V", "sessionDialogManager", "Lcom/amazon/tarazed/session/dialog/SessionDialogManagerImpl;", "sessionNotificationHandler", "Lkotlin/Function2;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "sessionNotificationHandler$annotations", "getSessionNotificationHandler$TarazedAndroidLibrary_release", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "sessionNotificationManager", "Lcom/amazon/tarazed/sessionmanager/SessionNotificationManager;", "getSessionNotificationManager$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/sessionmanager/SessionNotificationManager;", "setSessionNotificationManager$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/sessionmanager/SessionNotificationManager;)V", "getSessionNotifier$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "uiManager", "Lcom/amazon/tarazed/ui/manager/TarazedUIManager;", "uiManager$annotations", "getUiManager$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/ui/manager/TarazedUIManager;", "setUiManager$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/ui/manager/TarazedUIManager;)V", "cacheLaunchRequest", "cacheLaunchRequest$TarazedAndroidLibrary_release", "createSession", Post23SessionClientCache.LAUNCH_REQUEST_CACHE, "Lcom/amazon/tarazed/core/notification/type/TarazedLaunchRequest;", "createSession$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/notification/type/TarazedLaunchRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createSessionClient", "Lcom/amazon/tarazed/core/sessionclient/TarazedSessionClient;", "createSessionDialogManager", "createSessionInfoSender", "Lcom/amazon/tarazed/core/appInfo/sessioninfo/SessionInfoSender;", "createTarazedSession", "sessionClient", "webRTCManagerHelper", "Lcom/amazon/tarazed/core/webrtc/android/WebRTCManagerHelper;", "playbackStateCache", "Lcom/amazon/tarazed/core/session/PlaybackStateCache;", Constants.REQUESTER_PAYS, "Lcom/amazon/tarazed/core/appInfo/TarazedAppInfoRequester;", EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_SCREEN_WIDTH, "", "screenHeight", "deregisterDisplayListener", "deregisterHandlers", "fetchCacheContents", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchCacheContentsAndUnbindConnection", "forceShowSessionEndedDialog", "forceShowSessionEndedDialog$TarazedAndroidLibrary_release", "getCachedCredentials", "getCachedCredentials$TarazedAndroidLibrary_release", "getCachedLaunchRequest", "getCachedLaunchRequest$TarazedAndroidLibrary_release", "getScreenDimensions", "Lkotlin/Pair;", "getSessionCacheServiceConnection", "hasSessionRequestedPermission", "hasSessionRequestedPermission$TarazedAndroidLibrary_release", "initializeUIElements", "isBeta", "isSessionActive", "isSessionActive$TarazedAndroidLibrary_release", "isSessionPaused", "isSessionPaused$TarazedAndroidLibrary_release", "isSessionSuspended", "isSessionSuspended$TarazedAndroidLibrary_release", "isStreamPaused", "isStreamPaused$TarazedAndroidLibrary_release", "registerSessionReceivers", "resourcesBeingReused", "setupDependenciesAndInitializeSession", "subscribeToActivityChanges", "tearDownSession", "tearDownSession$TarazedAndroidLibrary_release", "teardownUI", "unassignResources", "unbindSessionCacheServiceConnection", "unbindSessionCacheServiceConnection$TarazedAndroidLibrary_release", "unregisterSessionReceivers", "wasPreviousStatePaused", "AnnotationRotationHandler", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedResourceManager {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final long MAX_SERVICE_CONNECTION_WAIT_MS = 10000;
    private static final String METRIC_FORCE_SHOW_END_SESSION_ON_ACTIVE_SESSION = "ForceShowEndSessionOnActiveSession";
    private static final String METRIC_SESSION_CACHE_NOT_CONNECTED = "SessionCacheNotConnected";
    private static final String METRIC_SESSION_CACHE_UNBIND_ERROR = "SessionCacheUnbindError";
    private static final String METRIC_SESSION_NOT_NULL_ON_CREATE = "SessionNotNullOnCreate";
    private static final String METRIC_SESSION_REUSING_RESOURCES = "SessionReusingResources";
    private static final String TAG = "TarazedResourceManager";
    @Nullable
    private Function3<? super Activity, ? super ActivityLifecycleAction, ? super Continuation<? super Unit>, ? extends Object> activitySubscription;
    private final ActivityTracker activityTracker;
    @Nullable
    private AnnotationView annotationView;
    @NotNull
    private final ArcusHelper arcusHelper;
    private final BizMetricsHelper bizMetricsHelper;
    private boolean cacheContentsFetched;
    private String cachedCredentials;
    private String cachedLaunchRequest;
    @NotNull
    public Context context;
    private final CoroutineScope coroutineScope;
    private final DeviceInfoUtility deviceInfo;
    @Nullable
    private DeviceShutdownReceiver deviceShutdownReceiver;
    private final DispatcherProvider dispatchers;
    private final DisplayRotationListener displayRotationListener;
    private final TarazedEventDispatcher eventDispatcher;
    @NotNull
    public HeadsUpNotificationManager headsUpNotificationManager;
    private final TarazedIoTManager iotManager;
    private boolean isCacheServiceBound;
    @Nullable
    private String launchRequestString;
    @NotNull
    private TarazedSessionLogger logger;
    private final Handler mainLooperHandler;
    @NotNull
    private final TarazedMetricsHelper metricsHelper;
    private final Provider<MoveTVUIHandler> moveTVUIHandler;
    @NotNull
    private final ObservableField<String> playbackStateObservable;
    @Nullable
    private TarazedSession session;
    @Nullable
    private ServiceConnection sessionCacheServiceConnection;
    @NotNull
    public ISessionClientCache sessionClientCacheService;
    private SessionDialogManagerImpl sessionDialogManager;
    @NotNull
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> sessionNotificationHandler;
    @NotNull
    public SessionNotificationManager sessionNotificationManager;
    @NotNull
    private final TarazedSessionNotifier sessionNotifier;
    private final Provider<SharedPreferences> sharedPreferences;
    @Nullable
    private TarazedUIManager uiManager;
    private final TarazedUIManagerFactory uiManagerFactory;
    private final Provider<ViewGroupManager> viewGroupManager;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TarazedResourceManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/tarazed/sessionmanager/TarazedResourceManager$AnnotationRotationHandler;", "Lcom/amazon/tarazed/core/rotation/DeviceRotationHandler;", "(Lcom/amazon/tarazed/sessionmanager/TarazedResourceManager;)V", "currentHeight", "", "currentWidth", "initialDimensions", "Lkotlin/Pair;", "onDeviceRotated", "", EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_SCREEN_WIDTH, "screenHeight", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class AnnotationRotationHandler implements DeviceRotationHandler {
        private int currentHeight;
        private int currentWidth;
        private final Pair<Integer, Integer> initialDimensions;

        public AnnotationRotationHandler() {
            this.initialDimensions = TarazedResourceManager.this.getScreenDimensions();
            this.currentWidth = this.initialDimensions.getFirst().intValue();
            this.currentHeight = this.initialDimensions.getSecond().intValue();
        }

        @Override // com.amazon.tarazed.core.rotation.DeviceRotationHandler
        public void onDeviceRotated(int i, int i2) {
            TarazedSessionLogger logger$TarazedAndroidLibrary_release = TarazedResourceManager.this.getLogger$TarazedAndroidLibrary_release();
            Companion unused = TarazedResourceManager.Companion;
            logger$TarazedAndroidLibrary_release.i(TarazedResourceManager.TAG, "onDeviceRotated called");
            TarazedSessionLogger logger$TarazedAndroidLibrary_release2 = TarazedResourceManager.this.getLogger$TarazedAndroidLibrary_release();
            Companion unused2 = TarazedResourceManager.Companion;
            logger$TarazedAndroidLibrary_release2.i(TarazedResourceManager.TAG, "Previous screen dimensions: screenWidth=" + this.currentWidth + ", screenHeight=" + this.currentHeight);
            TarazedSessionLogger logger$TarazedAndroidLibrary_release3 = TarazedResourceManager.this.getLogger$TarazedAndroidLibrary_release();
            Companion unused3 = TarazedResourceManager.Companion;
            logger$TarazedAndroidLibrary_release3.i(TarazedResourceManager.TAG, "Given screen dimensions: screenWidth=" + i + ", screenHeight=" + i2);
            if (i == this.currentWidth && i2 == this.currentHeight) {
                TarazedSessionLogger logger$TarazedAndroidLibrary_release4 = TarazedResourceManager.this.getLogger$TarazedAndroidLibrary_release();
                Companion unused4 = TarazedResourceManager.Companion;
                logger$TarazedAndroidLibrary_release4.i(TarazedResourceManager.TAG, "Device not rotated, not clearing annotations");
                return;
            }
            TarazedSessionLogger logger$TarazedAndroidLibrary_release5 = TarazedResourceManager.this.getLogger$TarazedAndroidLibrary_release();
            Companion unused5 = TarazedResourceManager.Companion;
            logger$TarazedAndroidLibrary_release5.i(TarazedResourceManager.TAG, "Device has rotated, clearing annotations and updating session dimensions");
            TarazedSession session$TarazedAndroidLibrary_release = TarazedResourceManager.this.getSession$TarazedAndroidLibrary_release();
            if (session$TarazedAndroidLibrary_release != null) {
                session$TarazedAndroidLibrary_release.setScreenDimensions(i, i2);
            }
            AnnotationView annotationView$TarazedAndroidLibrary_release = TarazedResourceManager.this.getAnnotationView$TarazedAndroidLibrary_release();
            if (annotationView$TarazedAndroidLibrary_release != null) {
                annotationView$TarazedAndroidLibrary_release.removeAllAnnotations();
            }
            this.currentWidth = i;
            this.currentHeight = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TarazedResourceManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/tarazed/sessionmanager/TarazedResourceManager$Companion;", "", "()V", "MAX_SERVICE_CONNECTION_WAIT_MS", "", "METRIC_FORCE_SHOW_END_SESSION_ON_ACTIVE_SESSION", "", "METRIC_SESSION_CACHE_NOT_CONNECTED", "METRIC_SESSION_CACHE_UNBIND_ERROR", "METRIC_SESSION_NOT_NULL_ON_CREATE", "METRIC_SESSION_REUSING_RESOURCES", "TAG", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TarazedNotificationEvent.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[TarazedNotificationEvent.MEDIA_PERMISSION_ACCEPTED.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[ActivityLifecycleAction.values().length];
            $EnumSwitchMapping$1[ActivityLifecycleAction.INITIAL.ordinal()] = 1;
            $EnumSwitchMapping$1[ActivityLifecycleAction.RESUME.ordinal()] = 2;
        }
    }

    @Inject
    public TarazedResourceManager(@NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull BizMetricsHelper bizMetricsHelper, @NotNull TarazedSessionNotifier sessionNotifier, @NotNull DeviceInfoUtility deviceInfo, @NotNull TarazedEventDispatcher eventDispatcher, @NotNull TarazedIoTManager iotManager, @NotNull DisplayRotationListener displayRotationListener, @NotNull Provider<ViewGroupManager> viewGroupManager, @NotNull Provider<MoveTVUIHandler> moveTVUIHandler, @NotNull ArcusHelper arcusHelper, @NotNull Provider<SharedPreferences> sharedPreferences, @NotNull Handler mainLooperHandler, @NotNull TarazedUIManagerFactory uiManagerFactory, @NotNull CoroutineScope coroutineScope, @NotNull DispatcherProvider dispatchers, @NotNull ActivityTracker activityTracker) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        Intrinsics.checkParameterIsNotNull(eventDispatcher, "eventDispatcher");
        Intrinsics.checkParameterIsNotNull(iotManager, "iotManager");
        Intrinsics.checkParameterIsNotNull(displayRotationListener, "displayRotationListener");
        Intrinsics.checkParameterIsNotNull(viewGroupManager, "viewGroupManager");
        Intrinsics.checkParameterIsNotNull(moveTVUIHandler, "moveTVUIHandler");
        Intrinsics.checkParameterIsNotNull(arcusHelper, "arcusHelper");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        Intrinsics.checkParameterIsNotNull(mainLooperHandler, "mainLooperHandler");
        Intrinsics.checkParameterIsNotNull(uiManagerFactory, "uiManagerFactory");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "coroutineScope");
        Intrinsics.checkParameterIsNotNull(dispatchers, "dispatchers");
        Intrinsics.checkParameterIsNotNull(activityTracker, "activityTracker");
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.bizMetricsHelper = bizMetricsHelper;
        this.sessionNotifier = sessionNotifier;
        this.deviceInfo = deviceInfo;
        this.eventDispatcher = eventDispatcher;
        this.iotManager = iotManager;
        this.displayRotationListener = displayRotationListener;
        this.viewGroupManager = viewGroupManager;
        this.moveTVUIHandler = moveTVUIHandler;
        this.arcusHelper = arcusHelper;
        this.sharedPreferences = sharedPreferences;
        this.mainLooperHandler = mainLooperHandler;
        this.uiManagerFactory = uiManagerFactory;
        this.coroutineScope = coroutineScope;
        this.dispatchers = dispatchers;
        this.activityTracker = activityTracker;
        this.playbackStateObservable = new ObservableField<>("inactive");
        this.sessionNotificationHandler = new TarazedResourceManager$sessionNotificationHandler$1(this, null);
    }

    @VisibleForTesting
    public static /* synthetic */ void activitySubscription$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void annotationView$annotations() {
    }

    private final TarazedSessionClient createSessionClient(TarazedLaunchRequest tarazedLaunchRequest) {
        String sessionEndpoint = tarazedLaunchRequest.getSessionEndpoint();
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        TarazedMetricsHelper tarazedMetricsHelper = this.metricsHelper;
        ISessionClientCache iSessionClientCache = this.sessionClientCacheService;
        if (iSessionClientCache == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionClientCacheService");
        }
        return new TarazedSessionClient(sessionEndpoint, tarazedSessionLogger, tarazedMetricsHelper, iSessionClientCache, null, 16, null);
    }

    private final void createSessionDialogManager() {
        SessionDialogManagerImpl sessionDialogManagerImpl = this.sessionDialogManager;
        if (sessionDialogManagerImpl != null) {
            sessionDialogManagerImpl.dismissAllDialogs();
        }
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        this.sessionDialogManager = new SessionDialogManagerImpl(context, this.deviceInfo, this.sessionNotifier, this.logger, this.metricsHelper, this.bizMetricsHelper, this.coroutineScope, this.dispatchers, this.arcusHelper);
    }

    private final SessionInfoSender createSessionInfoSender() {
        if (this.deviceInfo.isFireTV()) {
            return new SessionInfoSenderFireTV(this.eventDispatcher, this.iotManager, this.deviceInfo);
        }
        return new SessionInfoSenderAndroid1P(this.eventDispatcher, this.iotManager, this.deviceInfo);
    }

    private final TarazedSession createTarazedSession(TarazedSessionClient tarazedSessionClient, WebRTCManagerHelper webRTCManagerHelper, PlaybackStateCache playbackStateCache, TarazedAppInfoRequester tarazedAppInfoRequester, TarazedLaunchRequest tarazedLaunchRequest, int i, int i2, DeviceInfoUtility deviceInfoUtility, BizMetricsHelper bizMetricsHelper) {
        TarazedMetricsHelper tarazedMetricsHelper = this.metricsHelper;
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        TarazedSessionNotifier tarazedSessionNotifier = this.sessionNotifier;
        TarazedEventDispatcher tarazedEventDispatcher = this.eventDispatcher;
        TarazedIoTManager tarazedIoTManager = this.iotManager;
        DisplayRotationListener displayRotationListener = this.displayRotationListener;
        SessionDialogManagerImpl sessionDialogManagerImpl = this.sessionDialogManager;
        if (sessionDialogManagerImpl == null) {
            Intrinsics.throwNpe();
        }
        TarazedSession tarazedSession = new TarazedSession(tarazedMetricsHelper, tarazedSessionLogger, tarazedSessionNotifier, tarazedSessionClient, webRTCManagerHelper, playbackStateCache, tarazedEventDispatcher, tarazedIoTManager, displayRotationListener, tarazedAppInfoRequester, sessionDialogManagerImpl, tarazedLaunchRequest.getSessionId(), this.arcusHelper.getVideoParameters(), i, i2, isBeta(), this.playbackStateObservable, deviceInfoUtility, bizMetricsHelper);
        this.eventDispatcher.registerHandler(new StateChangeRequestHandler(this.logger, this.metricsHelper, tarazedSession));
        return tarazedSession;
    }

    private final void deregisterDisplayListener() {
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        Object systemService = context.getSystemService("display");
        if (systemService != null) {
            ((DisplayManager) systemService).unregisterDisplayListener(this.displayRotationListener);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.hardware.display.DisplayManager");
    }

    private final void deregisterHandlers() {
        this.eventDispatcher.deregisterHandlers();
        this.displayRotationListener.deregisterAllRotationHandlers();
    }

    @VisibleForTesting
    public static /* synthetic */ void deviceShutdownReceiver$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<Integer, Integer> getScreenDimensions() {
        Point point = new Point();
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        Object systemService = context.getSystemService("window");
        if (systemService != null) {
            ((WindowManager) systemService).getDefaultDisplay().getRealSize(point);
            return new Pair<>(Integer.valueOf(point.x), Integer.valueOf(point.y));
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
    }

    private final boolean getSessionAlreadyExists() {
        return this.session != null;
    }

    private final boolean isBeta() {
        boolean equals;
        equals = StringsKt__StringsJVMKt.equals(Environment.BETA.name(), this.sharedPreferences.mo10268get().getString(SharedPrefsConstants.DEVICE_ENVIRONMENT, null), true);
        return equals;
    }

    @VisibleForTesting
    public static /* synthetic */ void isCacheServiceBound$annotations() {
    }

    private final void registerSessionReceivers() {
        IntentFilter outline10 = GeneratedOutlineSupport1.outline10("android.intent.action.ACTION_SHUTDOWN");
        this.deviceShutdownReceiver = new DeviceShutdownReceiver(this.logger);
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        context.registerReceiver(this.deviceShutdownReceiver, outline10);
    }

    private final boolean resourcesBeingReused() {
        return (this.uiManager == null && this.deviceShutdownReceiver == null && this.annotationView == null) ? false : true;
    }

    @VisibleForTesting
    public static /* synthetic */ void session$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void sessionCacheServiceConnection$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void sessionNotificationHandler$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void uiManager$annotations() {
    }

    private final void unassignResources() {
        this.session = null;
        this.bizMetricsHelper.setSessionId(null);
        this.uiManager = null;
        this.annotationView = null;
        this.deviceShutdownReceiver = null;
        this.cachedLaunchRequest = null;
        this.cachedCredentials = null;
        this.cacheContentsFetched = false;
    }

    private final void unregisterSessionReceivers() {
        try {
            if (this.deviceShutdownReceiver == null) {
                return;
            }
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            context.unregisterReceiver(this.deviceShutdownReceiver);
        } catch (IllegalArgumentException e) {
            this.logger.w(TAG, "Failed to unregister receivers, possibly due to deregistration", e);
        }
    }

    private final boolean wasPreviousStatePaused() {
        TarazedSession tarazedSession = this.session;
        return Intrinsics.areEqual("paused", tarazedSession != null ? tarazedSession.getPreviousPlaybackState() : null);
    }

    public final void cacheLaunchRequest$TarazedAndroidLibrary_release() {
        String str = this.launchRequestString;
        if (str != null) {
            ISessionClientCache iSessionClientCache = this.sessionClientCacheService;
            if (iSessionClientCache == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sessionClientCacheService");
            }
            iSessionClientCache.putLaunchRequest(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object createSession$TarazedAndroidLibrary_release(@org.jetbrains.annotations.NotNull com.amazon.tarazed.core.notification.type.TarazedLaunchRequest r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.amazon.tarazed.sessionmanager.TarazedResourceManager$createSession$1
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$createSession$1 r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager$createSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$createSession$1 r0 = new com.amazon.tarazed.sessionmanager.TarazedResourceManager$createSession$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r7 = r0.L$1
            com.amazon.tarazed.core.notification.type.TarazedLaunchRequest r7 = (com.amazon.tarazed.core.notification.type.TarazedLaunchRequest) r7
            java.lang.Object r7 = r0.L$0
            com.amazon.tarazed.sessionmanager.TarazedResourceManager r7 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto Lb1
        L32:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3a:
            kotlin.ResultKt.throwOnFailure(r8)
            boolean r8 = r6.getSessionAlreadyExists()
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.String r2 = "TarazedResourceManager"
            if (r8 == 0) goto L67
            com.amazon.tarazed.core.logging.TarazedSessionLogger r8 = r6.logger
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "A session already exists, not creating for launch request: "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r8.w(r2, r7)
            com.amazon.tarazed.core.metrics.TarazedMetricsHelper r7 = r6.metricsHelper
            java.lang.String r8 = "SessionNotNullOnCreate"
            r7.addCount(r2, r8, r4)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L67:
            boolean r8 = r6.resourcesBeingReused()
            if (r8 == 0) goto L8d
            com.amazon.tarazed.core.logging.TarazedSessionLogger r8 = r6.logger
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "One or more resources are being reused from the previous session, not creating launch for request: "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r8.w(r2, r7)
            com.amazon.tarazed.core.metrics.TarazedMetricsHelper r7 = r6.metricsHelper
            java.lang.String r8 = "SessionReusingResources"
            r7.addCount(r2, r8, r4)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L8d:
            com.amazon.tarazed.core.logging.TarazedSessionLogger r8 = r6.logger
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Creating session for launch request: "
            r4.append(r5)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            r8.i(r2, r4)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r7 = r6.setupDependenciesAndInitializeSession(r7, r0)
            if (r7 != r1) goto Lb0
            return r1
        Lb0:
            r7 = r6
        Lb1:
            r7.registerSessionReceivers()
            com.amazon.tarazed.application.lifecycle.TarazedAppLifeCycleOwner r8 = com.amazon.tarazed.application.lifecycle.TarazedAppLifeCycleOwner.INSTANCE
            r8.registerLifeCycleObserver$TarazedAndroidLibrary_release()
            com.amazon.tarazed.core.notifier.TarazedSessionNotifier r0 = r7.sessionNotifier
            kotlin.jvm.functions.Function2<com.amazon.tarazed.core.notifier.TarazedNotificationEvent, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r1 = r7.sessionNotificationHandler
            com.amazon.tarazed.core.notifier.ListenerPriority r2 = com.amazon.tarazed.core.notifier.ListenerPriority.HIGH
            r3 = 0
            r4 = 4
            r5 = 0
            com.amazon.tarazed.core.notifier.TarazedSessionNotifier.subscribe$default(r0, r1, r2, r3, r4, r5)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.sessionmanager.TarazedResourceManager.createSession$TarazedAndroidLibrary_release(com.amazon.tarazed.core.notification.type.TarazedLaunchRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0060  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object fetchCacheContents(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.amazon.tarazed.sessionmanager.TarazedResourceManager$fetchCacheContents$1
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$fetchCacheContents$1 r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager$fetchCacheContents$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$fetchCacheContents$1 r0 = new com.amazon.tarazed.sessionmanager.TarazedResourceManager$fetchCacheContents$1
            r0.<init>(r7, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.sessionmanager.TarazedResourceManager r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L46
        L2d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L35:
            kotlin.ResultKt.throwOnFailure(r8)
            r7.cacheContentsFetched = r3
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r7.getSessionCacheServiceConnection$TarazedAndroidLibrary_release(r0)
            if (r8 != r1) goto L45
            return r1
        L45:
            r0 = r7
        L46:
            r1 = 0
            if (r8 != 0) goto L60
            com.amazon.tarazed.core.logging.TarazedSessionLogger r8 = r0.logger
            java.lang.String r2 = "TarazedResourceManager"
            java.lang.String r3 = "Unable to connect to session cache service"
            r8.e(r2, r3)
            com.amazon.tarazed.core.metrics.TarazedMetricsHelper r8 = r0.metricsHelper
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.String r0 = "SessionCacheNotConnected"
            r8.addCount(r2, r0, r3)
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r1)
            return r8
        L60:
            com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache r8 = r0.sessionClientCacheService
            java.lang.String r2 = "sessionClientCacheService"
            if (r8 != 0) goto L69
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L69:
            java.lang.String r8 = r8.getLaunchRequest()
            if (r8 == 0) goto Lb0
            r0.cachedLaunchRequest = r8
            kotlinx.serialization.json.Json$Default r8 = kotlinx.serialization.json.Json.Default
            kotlinx.serialization.json.Json r8 = r8.getNonstrict()
            com.amazon.tarazed.core.notification.type.TarazedLaunchRequest$Companion r4 = com.amazon.tarazed.core.notification.type.TarazedLaunchRequest.Companion
            kotlinx.serialization.KSerializer r4 = r4.serializer()
            com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache r5 = r0.sessionClientCacheService
            if (r5 != 0) goto L84
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L84:
            java.lang.String r5 = r5.getLaunchRequest()
            java.lang.String r6 = "sessionClientCacheService.launchRequest"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            java.lang.Object r8 = r8.parse(r4, r5)
            com.amazon.tarazed.core.notification.type.TarazedLaunchRequest r8 = (com.amazon.tarazed.core.notification.type.TarazedLaunchRequest) r8
            com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache r4 = r0.sessionClientCacheService
            if (r4 != 0) goto L9a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L9a:
            java.lang.String r8 = r8.getSessionId()
            java.lang.String r8 = r4.getSessionCredentials(r8)
            if (r8 == 0) goto Lab
            r0.cachedCredentials = r8
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r8
        Lab:
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r1)
            return r8
        Lb0:
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r1)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.sessionmanager.TarazedResourceManager.fetchCacheContents(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object fetchCacheContentsAndUnbindConnection(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.amazon.tarazed.sessionmanager.TarazedResourceManager$fetchCacheContentsAndUnbindConnection$1
            if (r0 == 0) goto L13
            r0 = r5
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$fetchCacheContentsAndUnbindConnection$1 r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager$fetchCacheContentsAndUnbindConnection$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$fetchCacheContentsAndUnbindConnection$1 r0 = new com.amazon.tarazed.sessionmanager.TarazedResourceManager$fetchCacheContentsAndUnbindConnection$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.sessionmanager.TarazedResourceManager r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L44
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.fetchCacheContents(r0)
            if (r5 != r1) goto L43
            return r1
        L43:
            r0 = r4
        L44:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r0.unbindSessionCacheServiceConnection$TarazedAndroidLibrary_release()
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.sessionmanager.TarazedResourceManager.fetchCacheContentsAndUnbindConnection(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void forceShowSessionEndedDialog$TarazedAndroidLibrary_release() {
        SessionEndedDialog mo4644getSessionEndedDialog;
        if (this.session != null) {
            this.logger.w(TAG, "Requested to force show end session dialog, but active session exists, ignoring.");
            this.metricsHelper.addCount(TAG, METRIC_FORCE_SHOW_END_SESSION_ON_ACTIVE_SESSION, 1.0d);
            return;
        }
        createSessionDialogManager();
        SessionDialogManagerImpl sessionDialogManagerImpl = this.sessionDialogManager;
        if (sessionDialogManagerImpl != null && (mo4644getSessionEndedDialog = sessionDialogManagerImpl.mo4644getSessionEndedDialog()) != null) {
            mo4644getSessionEndedDialog.show();
        }
        this.sessionDialogManager = null;
    }

    @Nullable
    public final Function3<Activity, ActivityLifecycleAction, Continuation<? super Unit>, Object> getActivitySubscription$TarazedAndroidLibrary_release() {
        return this.activitySubscription;
    }

    @Nullable
    public final AnnotationView getAnnotationView$TarazedAndroidLibrary_release() {
        return this.annotationView;
    }

    @NotNull
    public final ArcusHelper getArcusHelper$TarazedAndroidLibrary_release() {
        return this.arcusHelper;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getCachedCredentials$TarazedAndroidLibrary_release(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.tarazed.sessionmanager.TarazedResourceManager$getCachedCredentials$1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$getCachedCredentials$1 r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager$getCachedCredentials$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$getCachedCredentials$1 r0 = new com.amazon.tarazed.sessionmanager.TarazedResourceManager$getCachedCredentials$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.sessionmanager.TarazedResourceManager r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5b
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            boolean r6 = r5.cacheContentsFetched
            java.lang.String r2 = "TarazedResourceManager"
            if (r6 == 0) goto L48
            com.amazon.tarazed.core.logging.TarazedSessionLogger r6 = r5.logger
            java.lang.String r0 = "Cache contents have been previously fetched, returning cached credentials"
            r6.i(r2, r0)
            java.lang.String r6 = r5.cachedCredentials
            goto L5d
        L48:
            com.amazon.tarazed.core.logging.TarazedSessionLogger r6 = r5.logger
            java.lang.String r4 = "Cache contents should have first been fetched. Fetching contents and returning cached credentials"
            r6.w(r2, r4)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r5.fetchCacheContentsAndUnbindConnection(r0)
            if (r6 != r1) goto L5a
            return r1
        L5a:
            r0 = r5
        L5b:
            java.lang.String r6 = r0.cachedCredentials
        L5d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.sessionmanager.TarazedResourceManager.getCachedCredentials$TarazedAndroidLibrary_release(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getCachedLaunchRequest$TarazedAndroidLibrary_release(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.tarazed.sessionmanager.TarazedResourceManager$getCachedLaunchRequest$1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$getCachedLaunchRequest$1 r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager$getCachedLaunchRequest$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$getCachedLaunchRequest$1 r0 = new com.amazon.tarazed.sessionmanager.TarazedResourceManager$getCachedLaunchRequest$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.sessionmanager.TarazedResourceManager r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5b
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            boolean r6 = r5.cacheContentsFetched
            java.lang.String r2 = "TarazedResourceManager"
            if (r6 == 0) goto L48
            com.amazon.tarazed.core.logging.TarazedSessionLogger r6 = r5.logger
            java.lang.String r0 = "Cache contents have been previously fetched, returning cached launch request"
            r6.i(r2, r0)
            java.lang.String r6 = r5.cachedLaunchRequest
            goto L5d
        L48:
            com.amazon.tarazed.core.logging.TarazedSessionLogger r6 = r5.logger
            java.lang.String r4 = "Cache contents should have first been fetched. Fetching contents and returning cached launch request"
            r6.w(r2, r4)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r5.fetchCacheContentsAndUnbindConnection(r0)
            if (r6 != r1) goto L5a
            return r1
        L5a:
            r0 = r5
        L5b:
            java.lang.String r6 = r0.cachedLaunchRequest
        L5d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.sessionmanager.TarazedResourceManager.getCachedLaunchRequest$TarazedAndroidLibrary_release(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final Context getContext$TarazedAndroidLibrary_release() {
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context;
    }

    @Nullable
    public final DeviceShutdownReceiver getDeviceShutdownReceiver$TarazedAndroidLibrary_release() {
        return this.deviceShutdownReceiver;
    }

    @NotNull
    public final HeadsUpNotificationManager getHeadsUpNotificationManager$TarazedAndroidLibrary_release() {
        HeadsUpNotificationManager headsUpNotificationManager = this.headsUpNotificationManager;
        if (headsUpNotificationManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("headsUpNotificationManager");
        }
        return headsUpNotificationManager;
    }

    @Nullable
    public final String getLaunchRequestString$TarazedAndroidLibrary_release() {
        return this.launchRequestString;
    }

    @NotNull
    public final TarazedSessionLogger getLogger$TarazedAndroidLibrary_release() {
        return this.logger;
    }

    @NotNull
    public final TarazedMetricsHelper getMetricsHelper$TarazedAndroidLibrary_release() {
        return this.metricsHelper;
    }

    @NotNull
    public final ObservableField<String> getPlaybackStateObservable$TarazedAndroidLibrary_release() {
        return this.playbackStateObservable;
    }

    @Nullable
    public final TarazedSession getSession$TarazedAndroidLibrary_release() {
        return this.session;
    }

    @Nullable
    public final ServiceConnection getSessionCacheServiceConnection$TarazedAndroidLibrary_release() {
        return this.sessionCacheServiceConnection;
    }

    @NotNull
    public final ISessionClientCache getSessionClientCacheService$TarazedAndroidLibrary_release() {
        ISessionClientCache iSessionClientCache = this.sessionClientCacheService;
        if (iSessionClientCache == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionClientCacheService");
        }
        return iSessionClientCache;
    }

    @NotNull
    public final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> getSessionNotificationHandler$TarazedAndroidLibrary_release() {
        return this.sessionNotificationHandler;
    }

    @NotNull
    public final SessionNotificationManager getSessionNotificationManager$TarazedAndroidLibrary_release() {
        SessionNotificationManager sessionNotificationManager = this.sessionNotificationManager;
        if (sessionNotificationManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionNotificationManager");
        }
        return sessionNotificationManager;
    }

    @NotNull
    public final TarazedSessionNotifier getSessionNotifier$TarazedAndroidLibrary_release() {
        return this.sessionNotifier;
    }

    @Nullable
    public final TarazedUIManager getUiManager$TarazedAndroidLibrary_release() {
        return this.uiManager;
    }

    public final boolean hasSessionRequestedPermission$TarazedAndroidLibrary_release() {
        TarazedSession tarazedSession = this.session;
        if (tarazedSession != null) {
            String str = null;
            if (!Intrinsics.areEqual(tarazedSession != null ? tarazedSession.getPlaybackState() : null, "inactive")) {
                TarazedSession tarazedSession2 = this.session;
                if (tarazedSession2 != null) {
                    str = tarazedSession2.getPlaybackState();
                }
                if (!Intrinsics.areEqual(str, PlaybackStates.STARTING_BACKGROUND)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e4  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object initializeUIElements(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instructions count: 236
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.sessionmanager.TarazedResourceManager.initializeUIElements(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean isCacheServiceBound$TarazedAndroidLibrary_release() {
        return this.isCacheServiceBound;
    }

    public final boolean isSessionActive$TarazedAndroidLibrary_release() {
        TarazedSession tarazedSession = this.session;
        if (tarazedSession != null) {
            String str = null;
            if (!Intrinsics.areEqual(tarazedSession != null ? tarazedSession.getPlaybackState() : null, "inactive")) {
                TarazedSession tarazedSession2 = this.session;
                if (!Intrinsics.areEqual(tarazedSession2 != null ? tarazedSession2.getPlaybackState() : null, PlaybackStates.SUSPENDED)) {
                    TarazedSession tarazedSession3 = this.session;
                    if (tarazedSession3 != null) {
                        str = tarazedSession3.getPlaybackState();
                    }
                    if (!Intrinsics.areEqual(str, PlaybackStates.ENDED)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final boolean isSessionPaused$TarazedAndroidLibrary_release() {
        TarazedSession tarazedSession = this.session;
        return Intrinsics.areEqual("paused", tarazedSession != null ? tarazedSession.getPlaybackState() : null);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object isSessionSuspended$TarazedAndroidLibrary_release(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.tarazed.sessionmanager.TarazedResourceManager$isSessionSuspended$1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$isSessionSuspended$1 r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager$isSessionSuspended$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$isSessionSuspended$1 r0 = new com.amazon.tarazed.sessionmanager.TarazedResourceManager$isSessionSuspended$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.sessionmanager.TarazedResourceManager r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L50
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            boolean r6 = r5.cacheContentsFetched
            java.lang.String r2 = "TarazedResourceManager"
            if (r6 != 0) goto L57
            com.amazon.tarazed.core.logging.TarazedSessionLogger r6 = r5.logger
            java.lang.String r4 = "Fetching cache contents to check if session is suspended."
            r6.i(r2, r4)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r5.fetchCacheContentsAndUnbindConnection(r0)
            if (r6 != r1) goto L50
            return r1
        L50:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r3 = r6.booleanValue()
            goto L64
        L57:
            com.amazon.tarazed.core.logging.TarazedSessionLogger r6 = r5.logger
            java.lang.String r0 = "Already fetched cache contents. Checking if session is suspended."
            r6.w(r2, r0)
            java.lang.String r6 = r5.cachedLaunchRequest
            if (r6 == 0) goto L63
            goto L64
        L63:
            r3 = 0
        L64:
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.sessionmanager.TarazedResourceManager.isSessionSuspended$TarazedAndroidLibrary_release(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean isStreamPaused$TarazedAndroidLibrary_release() {
        if (!isSessionPaused$TarazedAndroidLibrary_release()) {
            if (wasPreviousStatePaused()) {
                TarazedSession tarazedSession = this.session;
                String str = null;
                if (!Intrinsics.areEqual(PlaybackStates.CONFIRMING_END, tarazedSession != null ? tarazedSession.getPlaybackState() : null)) {
                    TarazedSession tarazedSession2 = this.session;
                    if (!Intrinsics.areEqual(PlaybackStates.REQUESTING_RESUME, tarazedSession2 != null ? tarazedSession2.getPlaybackState() : null)) {
                        TarazedSession tarazedSession3 = this.session;
                        if (tarazedSession3 != null) {
                            str = tarazedSession3.getPlaybackState();
                        }
                        if (Intrinsics.areEqual(PlaybackStates.ENDING, str)) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final void setActivitySubscription$TarazedAndroidLibrary_release(@Nullable Function3<? super Activity, ? super ActivityLifecycleAction, ? super Continuation<? super Unit>, ? extends Object> function3) {
        this.activitySubscription = function3;
    }

    public final void setAnnotationView$TarazedAndroidLibrary_release(@Nullable AnnotationView annotationView) {
        this.annotationView = annotationView;
    }

    public final void setCacheServiceBound$TarazedAndroidLibrary_release(boolean z) {
        this.isCacheServiceBound = z;
    }

    public final void setContext$TarazedAndroidLibrary_release(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    public final void setDeviceShutdownReceiver$TarazedAndroidLibrary_release(@Nullable DeviceShutdownReceiver deviceShutdownReceiver) {
        this.deviceShutdownReceiver = deviceShutdownReceiver;
    }

    public final void setHeadsUpNotificationManager$TarazedAndroidLibrary_release(@NotNull HeadsUpNotificationManager headsUpNotificationManager) {
        Intrinsics.checkParameterIsNotNull(headsUpNotificationManager, "<set-?>");
        this.headsUpNotificationManager = headsUpNotificationManager;
    }

    public final void setLaunchRequestString$TarazedAndroidLibrary_release(@Nullable String str) {
        this.launchRequestString = str;
    }

    public final void setLogger$TarazedAndroidLibrary_release(@NotNull TarazedSessionLogger tarazedSessionLogger) {
        Intrinsics.checkParameterIsNotNull(tarazedSessionLogger, "<set-?>");
        this.logger = tarazedSessionLogger;
    }

    public final void setSession$TarazedAndroidLibrary_release(@Nullable TarazedSession tarazedSession) {
        this.session = tarazedSession;
    }

    public final void setSessionCacheServiceConnection$TarazedAndroidLibrary_release(@Nullable ServiceConnection serviceConnection) {
        this.sessionCacheServiceConnection = serviceConnection;
    }

    public final void setSessionClientCacheService$TarazedAndroidLibrary_release(@NotNull ISessionClientCache iSessionClientCache) {
        Intrinsics.checkParameterIsNotNull(iSessionClientCache, "<set-?>");
        this.sessionClientCacheService = iSessionClientCache;
    }

    public final void setSessionNotificationManager$TarazedAndroidLibrary_release(@NotNull SessionNotificationManager sessionNotificationManager) {
        Intrinsics.checkParameterIsNotNull(sessionNotificationManager, "<set-?>");
        this.sessionNotificationManager = sessionNotificationManager;
    }

    public final void setUiManager$TarazedAndroidLibrary_release(@Nullable TarazedUIManager tarazedUIManager) {
        this.uiManager = tarazedUIManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x011f  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object setupDependenciesAndInitializeSession(@org.jetbrains.annotations.NotNull com.amazon.tarazed.core.notification.type.TarazedLaunchRequest r33, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r34) {
        /*
            Method dump skipped, instructions count: 334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.sessionmanager.TarazedResourceManager.setupDependenciesAndInitializeSession(com.amazon.tarazed.core.notification.type.TarazedLaunchRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object subscribeToActivityChanges(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        if (this.deviceInfo.is1PDevice()) {
            this.logger.i(TAG, "Device is 1P, UI does not require activity");
            return Unit.INSTANCE;
        }
        this.logger.i(TAG, "Device is 3P, registering activity callbacks and waiting for activity before initializing UI");
        ActivityTracker activityTracker = this.activityTracker;
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        activityTracker.unregisterLifecycleCallbacks(context);
        ActivityTracker activityTracker2 = this.activityTracker;
        Context context2 = this.context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        activityTracker2.registerLifecycleCallbacks(context2);
        this.activitySubscription = new TarazedResourceManager$subscribeToActivityChanges$2(this, null);
        ActivityTracker activityTracker3 = this.activityTracker;
        Function3<? super Activity, ? super ActivityLifecycleAction, ? super Continuation<? super Unit>, ? extends Object> function3 = this.activitySubscription;
        if (function3 == null) {
            Intrinsics.throwNpe();
        }
        Object subscribe = activityTracker3.subscribe(function3, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return subscribe == coroutine_suspended ? subscribe : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object tearDownSession$TarazedAndroidLibrary_release(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.amazon.tarazed.sessionmanager.TarazedResourceManager$tearDownSession$1
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$tearDownSession$1 r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager$tearDownSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$tearDownSession$1 r0 = new com.amazon.tarazed.sessionmanager.TarazedResourceManager$tearDownSession$1
            r0.<init>(r7, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "TarazedResourceManager"
            r4 = 1
            if (r2 == 0) goto L3c
            if (r2 != r4) goto L34
            java.lang.Object r1 = r0.L$1
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.sessionmanager.TarazedResourceManager r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager) r0
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r1
            goto L73
        L34:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L3c:
            kotlin.ResultKt.throwOnFailure(r8)
            com.amazon.tarazed.core.session.TarazedSession r8 = r7.session
            if (r8 == 0) goto L48
            java.lang.String r8 = r8.getSessionId()
            goto L49
        L48:
            r8 = 0
        L49:
            com.amazon.tarazed.core.logging.TarazedSessionLogger r2 = r7.logger
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Tearing down session: "
            r5.append(r6)
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            r2.i(r3, r5)
            r7.deregisterHandlers()
            r7.deregisterDisplayListener()
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r0 = r7.teardownUI(r0)
            if (r0 != r1) goto L72
            return r1
        L72:
            r0 = r7
        L73:
            r0.unregisterSessionReceivers()
            r0.unassignResources()
            com.amazon.tarazed.application.lifecycle.TarazedAppLifeCycleOwner r1 = com.amazon.tarazed.application.lifecycle.TarazedAppLifeCycleOwner.INSTANCE
            r1.deregisterLifeCycleObserver$TarazedAndroidLibrary_release()
            com.amazon.tarazed.core.logging.TarazedSessionLogger r0 = r0.logger
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Session torn down: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r0.i(r3, r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.sessionmanager.TarazedResourceManager.tearDownSession$TarazedAndroidLibrary_release(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object teardownUI(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.amazon.tarazed.sessionmanager.TarazedResourceManager$teardownUI$1
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$teardownUI$1 r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager$teardownUI$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$teardownUI$1 r0 = new com.amazon.tarazed.sessionmanager.TarazedResourceManager$teardownUI$1
            r0.<init>(r7, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.sessionmanager.TarazedResourceManager r0 = (com.amazon.tarazed.sessionmanager.TarazedResourceManager) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L4f
        L2d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L35:
            kotlin.ResultKt.throwOnFailure(r8)
            com.amazon.tarazed.annotations.AnnotationView r8 = r7.annotationView
            if (r8 == 0) goto L3f
            r8.removeAllAnnotations()
        L3f:
            com.amazon.tarazed.ui.manager.TarazedUIManager r8 = r7.uiManager
            if (r8 == 0) goto L4e
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r8.unsubscribeFromActivityChanges(r0)
            if (r8 != r1) goto L4e
            return r1
        L4e:
            r0 = r7
        L4f:
            kotlinx.coroutines.GlobalScope r1 = kotlinx.coroutines.GlobalScope.INSTANCE
            kotlinx.coroutines.MainCoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getMain()
            r3 = 0
            com.amazon.tarazed.sessionmanager.TarazedResourceManager$teardownUI$2 r4 = new com.amazon.tarazed.sessionmanager.TarazedResourceManager$teardownUI$2
            r8 = 0
            r4.<init>(r0, r8)
            r5 = 2
            r6 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r1, r2, r3, r4, r5, r6)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.sessionmanager.TarazedResourceManager.teardownUI(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void unbindSessionCacheServiceConnection$TarazedAndroidLibrary_release() {
        if (this.sessionCacheServiceConnection != null) {
            this.logger.i(TAG, "Unbinding session cache service connection");
            try {
                try {
                    Context context = this.context;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                    }
                    ServiceConnection serviceConnection = this.sessionCacheServiceConnection;
                    if (serviceConnection == null) {
                        Intrinsics.throwNpe();
                    }
                    context.unbindService(serviceConnection);
                } catch (Exception e) {
                    this.logger.e(TAG, "Exception while trying to unbind sessionCacheService", e);
                    this.metricsHelper.addCount(TAG, METRIC_SESSION_CACHE_UNBIND_ERROR, 1.0d);
                }
            } finally {
                this.isCacheServiceBound = false;
            }
        }
    }

    @Nullable
    public final Object getSessionCacheServiceConnection$TarazedAndroidLibrary_release(@NotNull Continuation<? super Unit> continuation) {
        return TimeoutKt.withTimeoutOrNull(10000L, new TarazedResourceManager$getSessionCacheServiceConnection$2(this, null), continuation);
    }
}
