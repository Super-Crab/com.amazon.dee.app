package com.amazon.alexa.accessoryservice.service.rxipc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import com.amazon.alexa.accessory.Accessories;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryScanner;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.AccessorySupplier;
import com.amazon.alexa.accessory.SessionListener;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.davs.DavsI18nConfig;
import com.amazon.alexa.accessory.internal.bluetooth.CompanionDeviceModule;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.kota.InventoryUpdateBundle;
import com.amazon.alexa.accessory.kota.KotaDownloader;
import com.amazon.alexa.accessory.protocol.Cbl;
import com.amazon.alexa.accessory.protocol.Cloudpairing;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.protocol.Fitness;
import com.amazon.alexa.accessory.protocol.Hearing;
import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.accessory.protocol.Media;
import com.amazon.alexa.accessory.protocol.Notification;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessory.registration.RegistrationSupplier;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier;
import com.amazon.alexa.accessory.repositories.device.DeviceFeatures;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareUpdateStatus;
import com.amazon.alexa.accessory.repositories.fitness.FitnessDataAvailableNotification;
import com.amazon.alexa.accessory.repositories.fitness.FitnessDataSource;
import com.amazon.alexa.accessory.repositories.fitness.FitnessRepository;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSession;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessoryclient.common.api.ConnectionStatus;
import com.amazon.alexa.accessoryclient.common.api.FitnessSessionUpdateMetadata;
import com.amazon.alexa.accessoryclient.common.connection.BundleSink;
import com.amazon.alexa.accessoryclient.common.connection.BundleSource;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.AccessoryRequest;
import com.amazon.alexa.accessoryclient.common.query.request.AtCommandRequest;
import com.amazon.alexa.accessoryclient.common.query.request.CallInfoRequest;
import com.amazon.alexa.accessoryclient.common.query.request.CloudPairingSeedRequest;
import com.amazon.alexa.accessoryclient.common.query.request.CreateSessionRequest;
import com.amazon.alexa.accessoryclient.common.query.request.DeviceAccountRequest;
import com.amazon.alexa.accessoryclient.common.query.request.DeviceGroupRequest;
import com.amazon.alexa.accessoryclient.common.query.request.DiagnosticsRequest;
import com.amazon.alexa.accessoryclient.common.query.request.DownloadPackageRequest;
import com.amazon.alexa.accessoryclient.common.query.request.InventoryUpdateRequest;
import com.amazon.alexa.accessoryclient.common.query.request.LongRequest;
import com.amazon.alexa.accessoryclient.common.query.request.MediaControlRequest;
import com.amazon.alexa.accessoryclient.common.query.request.NotificationWithContentRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionBooleanIntRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionBooleanRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionBooleanStringRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionByteArrayRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionIntListRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionIntRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionLocaleRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionStringIntRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionStringRequest;
import com.amazon.alexa.accessoryclient.common.query.request.ReplaceCloudPairingKeysRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetAudiogramRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetCloudPairingKeysRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetFitnessSessionRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetInputConfigurationRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetMediaEnhancementCorrectionAmountRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetPlaybackStatusRequest;
import com.amazon.alexa.accessoryclient.common.query.request.StateRequest;
import com.amazon.alexa.accessoryclient.common.query.request.UpdateRequestRequest;
import com.amazon.alexa.accessoryclient.common.query.response.AccessoryListResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceRegistrationResponse;
import com.amazon.alexa.accessoryclient.common.query.response.FitnessSessionUpdateMetadataResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCEvent;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCEventId;
import com.amazon.alexa.accessoryclient.common.util.Preconditions;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessoriesRequestHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\bR\u0018\u0000 ¡\u00012\u00020\u00012\u00020\u0002:\u0006¡\u0001¢\u0001£\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005BM\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017¢\u0006\u0002\u0010\u0018J\u0018\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010.\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010/\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u00100\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u00101\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u00102\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u00103\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u00104\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u00105\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u00106\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0014\u00107\u001a\u00020)2\n\u00108\u001a\u000609j\u0002`:H\u0016J\u0018\u0010;\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010<\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010=\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010>\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u0010?\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0018\u0010@\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010A\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010B\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010C\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010D\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010E\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010F\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010G\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010H\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010I\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010J\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020\u001dH\u0002J\u0010\u0010N\u001a\u00020)2\u0006\u0010O\u001a\u00020-H\u0016J \u0010P\u001a\u00020)2\u0006\u0010Q\u001a\u00020R2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010S\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010T\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010U\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010V\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0003J\u0018\u0010W\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010X\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010Y\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010Z\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010[\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010\\\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u0010]\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010^\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010_\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010`\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010a\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010b\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010c\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010d\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010e\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010f\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0018\u0010g\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010h\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010i\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010j\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010k\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010l\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010m\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010n\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u0010o\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0018\u0010p\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010q\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010r\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010s\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010t\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010u\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010v\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010w\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010x\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u0010y\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0003J\u0018\u0010z\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u0010{\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010|\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0003J\u0018\u0010}\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010~\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u0010\u007f\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0080\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0081\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0003J\u0019\u0010\u0082\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0083\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0084\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0085\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0086\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0087\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0003J\u0019\u0010\u0088\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0089\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u008a\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u008b\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u008c\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u008d\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u008e\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u008f\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0012\u0010\u0090\u0001\u001a\u00020)2\u0007\u0010\u0091\u0001\u001a\u00020\u0001H\u0016J\u0019\u0010\u0092\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0093\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0094\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0095\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0096\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0097\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0098\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u0099\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u009a\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u009b\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u009c\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u009d\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u009e\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010\u009f\u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0019\u0010 \u0001\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0019\u001a\u00060\u001aR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u001b\u001a\u001a\u0012\u0004\u0012\u00020\u001d\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001f0\u001e0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006¤\u0001"}, d2 = {"Lcom/amazon/alexa/accessoryservice/service/rxipc/AccessoriesRequestHandler;", "Lcom/amazon/alexa/accessoryclient/common/connection/BundleSink;", "Lcom/amazon/alexa/accessoryclient/common/connection/BundleSource;", "accessories", "Lcom/amazon/alexa/accessory/Accessories;", "(Lcom/amazon/alexa/accessory/Accessories;)V", "sessionFactory", "Lcom/amazon/alexa/accessory/AccessorySession$Factory;", "accessorySupplier", "Lcom/amazon/alexa/accessory/AccessorySupplier;", "sessionSupplier", "Lcom/amazon/alexa/accessory/SessionSupplier;", "deviceSupplier", "Lcom/amazon/alexa/accessory/repositories/device/v2/DeviceSupplierV2;", "accessoryScanner", "Lcom/amazon/alexa/accessory/AccessoryScanner;", "registrationSupplier", "Lcom/amazon/alexa/accessory/registration/RegistrationSupplier;", "companionDeviceModule", "Lcom/amazon/alexa/accessory/internal/bluetooth/CompanionDeviceModule;", "kotaDownloader", "Lcom/amazon/alexa/accessory/kota/KotaDownloader;", "deviceAccountSupplier", "Lcom/amazon/alexa/accessory/registration/deviceaccount/DeviceAccountSupplier;", "(Lcom/amazon/alexa/accessory/AccessorySession$Factory;Lcom/amazon/alexa/accessory/AccessorySupplier;Lcom/amazon/alexa/accessory/SessionSupplier;Lcom/amazon/alexa/accessory/repositories/device/v2/DeviceSupplierV2;Lcom/amazon/alexa/accessory/AccessoryScanner;Lcom/amazon/alexa/accessory/registration/RegistrationSupplier;Lcom/amazon/alexa/accessory/internal/bluetooth/CompanionDeviceModule;Lcom/amazon/alexa/accessory/kota/KotaDownloader;Lcom/amazon/alexa/accessory/registration/deviceaccount/DeviceAccountSupplier;)V", "fitnessSessionListener", "Lcom/amazon/alexa/accessoryservice/service/rxipc/AccessoriesRequestHandler$FitnessSessionListener;", "fitnessUpdateCache", "", "", "Landroid/util/LruCache;", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSessionUpdate;", JoinPoint.SYNCHRONIZATION_LOCK, "", "mainThreadHandler", "Landroid/os/Handler;", "released", "", "rxIPCServer", "Lcom/amazon/alexa/accessoryservice/service/rxipc/RxIPCServer;", "addDeviceGroup", "", "requestIdentifier", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId;", "requestBody", "Landroid/os/Bundle;", "addOutgoingNotification", "connectUser", "createAndConnectSession", "createAndConnectSessionAwaitConnection", "createAndConnectSessionWithOptions", "createAndConnectSessionWithOptionsAwaitConnection", "deleteDeviceAccounts", "deregister", "disconnectUser", "dispose", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "downloadPackageRequest", "fetchAndStoreDeviceAccount", "fitnessUpdateProcessedInternal", "forwardAtCommand", "getActiveAccessories", "getAudiogram", "getCloudPairingAttributes", "getCloudPairingStatus", "getDeviceAccount", "getDeviceGroup", "getDeviceIdentifier", "getDeviceRegistration", "getFitnessData", "getFitnessDataWithToken", "getMediaEnhancementCorrectionAmount", "getOrCreateDeviceRegistration", "getSessionFromIdentifier", "Lcom/amazon/alexa/accessory/AccessorySession;", "identifier", "handleBundle", "bundle", "handleRequest", "apiIdentifier", "Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;", "hasDeviceGroup", "initiateFirmwareTransfer", "inventoryUpdateRequest", "isCompanionDevice", "issueMediaControl", "kotaUpdateRequest", "linkAccessory", "observeFitnessDataAvailableNotifications", "observeFitnessSessionUpdates", "observeLiveFitnessDataNotifications", "observeOnBleAccessoryFoundNearby", "observeOnBleDataBeaconFoundNearby", "observeOnConnectedAccessoryFound", "observeOnConnectedAccessoryLost", "observeSessionConnected", "observeSessionCreated", "observeSessionDisconnected", "observeSessionFailed", "observeSessionReleased", "observeSessionTransportChanged", "observeStopLiveFitnessDataNotifications", "queryActionCommandsForOutgoingNotifications", "queryCblLoginState", "queryConnectedAccessory", "queryConnectionStatus", "queryDavsI18nConfig", "queryDeviceConfiguration", "queryDeviceFeatures", "queryDeviceGroups", "queryDeviceInformationSet", "queryDiagnostics", "queryFirmwareInformationSet", "queryFirmwareUpdateStatus", "queryInputConfiguration", "queryInventoryUpdateSet", "queryIsAwaitingDerivedKeys", "queryLocales", "queryMediaControl", "queryNewCompanionDevices", "queryRegisterForMediaEvents", "queryRegistrations", "queryRemovedCompanionDevices", "queryState", "queryUsers", "releaseSession", "removeCloudPairingKeys", "removeCompanionDevice", "removeDeviceGroup", "removeDeviceGroupByDeviceGroup", "removeOutgoingNotification", "replaceCloudPairingKeys", "requestCblInformation", "requestCompanionDevice", "requestCompleteSetup", "requestOverrideAssistant", "requestResetConnection", "requestStartSetup", "requestTransportUpgrade", "requestUpdateDeviceInformation", "resetInputConfiguration", "setAudiogram", "setBundleSink", "bundleSink", "setCloudPairingKeys", "setFitnessSession", "setInputConfiguration", "setLocale", "setMediaEnhancementCorrectionAmount", "setPlaybackStatus", "setState", "shouldUpgradeTransport", "startLiveFitnessData", "stopLiveFitnessData", "unlinkAccessory", "unpairUser", "updateCallInfo", "updateDeviceGroup", "updateOutgoingNotification", "Companion", "FitnessSessionListener", "SessionNotFoundException", "AlexaAccessoryAndroidService_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccessoriesRequestHandler implements BundleSink, BundleSource {
    public static final Companion Companion = new Companion(null);
    public static final int FITNESS_CACHE_LRU_SIZE = 32;
    private static final String TAG = "AccessoriesRequestHandler: ";
    private final AccessoryScanner accessoryScanner;
    private final AccessorySupplier accessorySupplier;
    private final CompanionDeviceModule companionDeviceModule;
    private final DeviceAccountSupplier deviceAccountSupplier;
    private final DeviceSupplierV2 deviceSupplier;
    private final FitnessSessionListener fitnessSessionListener;
    private final Map<String, LruCache<String, FitnessSessionUpdate>> fitnessUpdateCache;
    private final KotaDownloader kotaDownloader;
    private final Object lock;
    private final Handler mainThreadHandler;
    private final RegistrationSupplier registrationSupplier;
    private boolean released;
    private final RxIPCServer rxIPCServer;
    private final AccessorySession.Factory sessionFactory;
    private final SessionSupplier sessionSupplier;

    /* compiled from: AccessoriesRequestHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessoryservice/service/rxipc/AccessoriesRequestHandler$Companion;", "", "()V", "FITNESS_CACHE_LRU_SIZE", "", "TAG", "", "AlexaAccessoryAndroidService_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: AccessoriesRequestHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessoryservice/service/rxipc/AccessoriesRequestHandler$FitnessSessionListener;", "Lcom/amazon/alexa/accessory/AccessorySessionListener;", "(Lcom/amazon/alexa/accessoryservice/service/rxipc/AccessoriesRequestHandler;)V", "onAccessorySessionReleased", "", ModelTransformer.KEY_ACCESSORY, "Lcom/amazon/alexa/accessory/Accessory;", "AlexaAccessoryAndroidService_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class FitnessSessionListener extends AccessorySessionListener {
        public FitnessSessionListener() {
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionReleased(@NotNull Accessory accessory) {
            Intrinsics.checkParameterIsNotNull(accessory, "accessory");
            synchronized (AccessoriesRequestHandler.this.lock) {
                LruCache lruCache = (LruCache) AccessoriesRequestHandler.this.fitnessUpdateCache.remove(accessory.getAddress());
            }
        }
    }

    /* compiled from: AccessoriesRequestHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/accessoryservice/service/rxipc/AccessoriesRequestHandler$SessionNotFoundException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "identifier", "", "(Ljava/lang/String;)V", "AlexaAccessoryAndroidService_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class SessionNotFoundException extends Exception {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SessionNotFoundException(@NotNull String identifier) {
            super("Session with identifier " + identifier + " is not active.");
            Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[RxIPCEventId.Action.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[RxIPCEventId.Action.INIT.ordinal()] = 1;
            $EnumSwitchMapping$0[RxIPCEventId.Action.ON_DISPOSE.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[ApiIdentifier.values().length];
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_CONNECTION_STATUS.ordinal()] = 1;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_CONNECTED_ACCESSORY.ordinal()] = 2;
            $EnumSwitchMapping$1[ApiIdentifier.RELEASE_SESSION.ordinal()] = 3;
            $EnumSwitchMapping$1[ApiIdentifier.LINK_ACCESSORY.ordinal()] = 4;
            $EnumSwitchMapping$1[ApiIdentifier.UNLINK_ACCESSORY.ordinal()] = 5;
            $EnumSwitchMapping$1[ApiIdentifier.OBSERVE_SESSION_CREATED.ordinal()] = 6;
            $EnumSwitchMapping$1[ApiIdentifier.OBSERVE_SESSION_CONNECTED.ordinal()] = 7;
            $EnumSwitchMapping$1[ApiIdentifier.OBSERVE_SESSION_DISCONNECTED.ordinal()] = 8;
            $EnumSwitchMapping$1[ApiIdentifier.OBSERVE_SESSION_FAILED.ordinal()] = 9;
            $EnumSwitchMapping$1[ApiIdentifier.OBSERVE_SESSION_RELEASED.ordinal()] = 10;
            $EnumSwitchMapping$1[ApiIdentifier.OBSERVE_SESSION_TRANSPORT_CHANGED.ordinal()] = 11;
            $EnumSwitchMapping$1[ApiIdentifier.GET_ACTIVE_ACCESSORIES.ordinal()] = 12;
            $EnumSwitchMapping$1[ApiIdentifier.CREATE_AND_CONNECT_SESSION.ordinal()] = 13;
            $EnumSwitchMapping$1[ApiIdentifier.CREATE_AND_CONNECT_SESSION_WITH_OPTIONS.ordinal()] = 14;
            $EnumSwitchMapping$1[ApiIdentifier.CREATE_AND_CONNECT_SESSION_AWAIT_CONNECTION.ordinal()] = 15;
            $EnumSwitchMapping$1[ApiIdentifier.CREATE_AND_CONNECT_SESSION_WITH_OPTIONS_AWAIT_CONNECTION.ordinal()] = 16;
            $EnumSwitchMapping$1[ApiIdentifier.SET_STATE.ordinal()] = 17;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_STATE.ordinal()] = 18;
            $EnumSwitchMapping$1[ApiIdentifier.REQUEST_OVERRIDE_ASSISTANT.ordinal()] = 19;
            $EnumSwitchMapping$1[ApiIdentifier.REQUEST_COMPLETE_SETUP.ordinal()] = 20;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_DEVICE_CONFIGURATION.ordinal()] = 21;
            $EnumSwitchMapping$1[ApiIdentifier.REQUEST_UPDATE_DEVICE_INFORMATION.ordinal()] = 22;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_DEVICE_INFORMATION_SET.ordinal()] = 23;
            $EnumSwitchMapping$1[ApiIdentifier.REQUEST_START_SETUP.ordinal()] = 24;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_DEVICE_FEATURES.ordinal()] = 25;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_DEVICE_GROUPS.ordinal()] = 26;
            $EnumSwitchMapping$1[ApiIdentifier.REMOVE_DEVICE_GROUP.ordinal()] = 27;
            $EnumSwitchMapping$1[ApiIdentifier.REMOVE_DEVICE_GROUP_BY_DEVICE_GROUP.ordinal()] = 28;
            $EnumSwitchMapping$1[ApiIdentifier.GET_DEVICE_GROUP.ordinal()] = 29;
            $EnumSwitchMapping$1[ApiIdentifier.HAS_DEVICE_GROUP.ordinal()] = 30;
            $EnumSwitchMapping$1[ApiIdentifier.UPDATE_DEVICE_GROUP.ordinal()] = 31;
            $EnumSwitchMapping$1[ApiIdentifier.ADD_DEVICE_GROUP.ordinal()] = 32;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_FIRMWARE_INFORMATION_SET.ordinal()] = 33;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_FIRMWARE_UPDATE_STATUS.ordinal()] = 34;
            $EnumSwitchMapping$1[ApiIdentifier.INITIATE_FIRMWARE_TRANSFER.ordinal()] = 35;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_INVENTORY_UPDATE_SET.ordinal()] = 36;
            $EnumSwitchMapping$1[ApiIdentifier.OBSERVE_BLE_ACCESSORY_FOUND_NEARBY.ordinal()] = 37;
            $EnumSwitchMapping$1[ApiIdentifier.OBSERVE_BLE_ACCESSORY_DATA_BEACON_FOUND_NEARBY.ordinal()] = 38;
            $EnumSwitchMapping$1[ApiIdentifier.OBSERVE_CONNECTED_ACCESSORY_FOUND.ordinal()] = 39;
            $EnumSwitchMapping$1[ApiIdentifier.OBSERVE_CONNECTED_ACCESSORY_LOST.ordinal()] = 40;
            $EnumSwitchMapping$1[ApiIdentifier.GET_DEVICE_REGISTRATION.ordinal()] = 41;
            $EnumSwitchMapping$1[ApiIdentifier.GET_OR_CREATE_DEVICE_REGISTRATION.ordinal()] = 42;
            $EnumSwitchMapping$1[ApiIdentifier.DEREGISTER.ordinal()] = 43;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_REGISTRATIONS.ordinal()] = 44;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_INPUT_CONFIGURATION.ordinal()] = 45;
            $EnumSwitchMapping$1[ApiIdentifier.SET_INPUT_CONFIGURATION.ordinal()] = 46;
            $EnumSwitchMapping$1[ApiIdentifier.RESET_INPUT_CONFIGURATION.ordinal()] = 47;
            $EnumSwitchMapping$1[ApiIdentifier.SHOULD_UPGRADE_TRANSPORT.ordinal()] = 48;
            $EnumSwitchMapping$1[ApiIdentifier.REQUEST_TRANSPORT_UPGRADE.ordinal()] = 49;
            $EnumSwitchMapping$1[ApiIdentifier.REQUEST_COMPANION_DEVICE.ordinal()] = 50;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_NEW_COMPANION_DEVICES.ordinal()] = 51;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_REMOVED_COMPANION_DEVICES.ordinal()] = 52;
            $EnumSwitchMapping$1[ApiIdentifier.IS_COMPANION_DEVICE.ordinal()] = 53;
            $EnumSwitchMapping$1[ApiIdentifier.REMOVE_COMPANION_DEVICE.ordinal()] = 54;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_USERS.ordinal()] = 55;
            $EnumSwitchMapping$1[ApiIdentifier.CONNECT_USER.ordinal()] = 56;
            $EnumSwitchMapping$1[ApiIdentifier.DISCONNECT_USER.ordinal()] = 57;
            $EnumSwitchMapping$1[ApiIdentifier.UNPAIR_USER.ordinal()] = 58;
            $EnumSwitchMapping$1[ApiIdentifier.REQUEST_RESET_CONNECTION.ordinal()] = 59;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_LOCALES.ordinal()] = 60;
            $EnumSwitchMapping$1[ApiIdentifier.SET_LOCALE.ordinal()] = 61;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_IS_AWAITING_DERIVED_KEYS.ordinal()] = 62;
            $EnumSwitchMapping$1[ApiIdentifier.GENERATE_UPDATE_REQUEST.ordinal()] = 63;
            $EnumSwitchMapping$1[ApiIdentifier.GET_AVAILABLE_INVENTORY_UPDATE_REQUEST.ordinal()] = 64;
            $EnumSwitchMapping$1[ApiIdentifier.DOWNLOAD_PACKAGE_REQUEST.ordinal()] = 65;
            $EnumSwitchMapping$1[ApiIdentifier.FORWARD_AT_COMMAND.ordinal()] = 66;
            $EnumSwitchMapping$1[ApiIdentifier.GET_FITNESS_DATA.ordinal()] = 67;
            $EnumSwitchMapping$1[ApiIdentifier.GET_FITNESS_DATA_WITH_TOKEN.ordinal()] = 68;
            $EnumSwitchMapping$1[ApiIdentifier.OBSERVE_FITNESS_SESSION_UPDATES.ordinal()] = 69;
            $EnumSwitchMapping$1[ApiIdentifier.FITNESS_UPDATE_PROCESSED_INTERNAL.ordinal()] = 70;
            $EnumSwitchMapping$1[ApiIdentifier.SET_FITNESS_SESSION.ordinal()] = 71;
            $EnumSwitchMapping$1[ApiIdentifier.START_LIVE_FITNESS_DATA.ordinal()] = 72;
            $EnumSwitchMapping$1[ApiIdentifier.STOP_LIVE_FITNESS_DATA.ordinal()] = 73;
            $EnumSwitchMapping$1[ApiIdentifier.OBSERVE_LIVE_FITNESS_DATA_NOTIFICATIONS.ordinal()] = 74;
            $EnumSwitchMapping$1[ApiIdentifier.OBSERVE_STOP_LIVE_FITNESS_DATA_NOTIFICATIONS.ordinal()] = 75;
            $EnumSwitchMapping$1[ApiIdentifier.OBSERVE_FITNESS_DATA_AVAILABLE_NOTIFICATIONS.ordinal()] = 76;
            $EnumSwitchMapping$1[ApiIdentifier.FETCH_AND_STORE_DEVICE_ACCOUNT.ordinal()] = 77;
            $EnumSwitchMapping$1[ApiIdentifier.GET_DEVICE_ACCOUNT.ordinal()] = 78;
            $EnumSwitchMapping$1[ApiIdentifier.GET_DEVICE_IDENTIFIER.ordinal()] = 79;
            $EnumSwitchMapping$1[ApiIdentifier.DELETE_DEVICE_ACCOUNTS.ordinal()] = 80;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_DIAGNOSTICS.ordinal()] = 81;
            $EnumSwitchMapping$1[ApiIdentifier.ISSUE_MEDIA_CONTROL.ordinal()] = 82;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_MEDIA_CONTROL.ordinal()] = 83;
            $EnumSwitchMapping$1[ApiIdentifier.SET_PLAYBACK_STATUS.ordinal()] = 84;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_REGISTER_FOR_MEDIA_EVENTS.ordinal()] = 85;
            $EnumSwitchMapping$1[ApiIdentifier.GET_AUDIOGRAM.ordinal()] = 86;
            $EnumSwitchMapping$1[ApiIdentifier.SET_AUDIOGRAM.ordinal()] = 87;
            $EnumSwitchMapping$1[ApiIdentifier.GET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT.ordinal()] = 88;
            $EnumSwitchMapping$1[ApiIdentifier.SET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT.ordinal()] = 89;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_CBL_LOGIN_STATE.ordinal()] = 90;
            $EnumSwitchMapping$1[ApiIdentifier.REQUEST_CBL_INFORMATION.ordinal()] = 91;
            $EnumSwitchMapping$1[ApiIdentifier.UPDATE_CALL_INFO.ordinal()] = 92;
            $EnumSwitchMapping$1[ApiIdentifier.ADD_OUTGOING_NOTIFICATION.ordinal()] = 93;
            $EnumSwitchMapping$1[ApiIdentifier.UPDATE_OUTGOING_NOTIFICATION.ordinal()] = 94;
            $EnumSwitchMapping$1[ApiIdentifier.REMOVE_OUTGOING_NOTIFICATION.ordinal()] = 95;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_ACTION_COMMANDS_FOR_OUTGOING_NOTIFICATIONS.ordinal()] = 96;
            $EnumSwitchMapping$1[ApiIdentifier.QUERY_I18N_CONFIG.ordinal()] = 97;
            $EnumSwitchMapping$1[ApiIdentifier.GET_CLOUD_PAIRING_STATUS.ordinal()] = 98;
            $EnumSwitchMapping$1[ApiIdentifier.GET_CLOUD_PAIRING_ATTRIBUTES.ordinal()] = 99;
            $EnumSwitchMapping$1[ApiIdentifier.SET_CLOUD_PAIRING_KEYS.ordinal()] = 100;
            $EnumSwitchMapping$1[ApiIdentifier.REPLACE_CLOUD_PAIRING_KEYS.ordinal()] = 101;
            $EnumSwitchMapping$1[ApiIdentifier.REMOVE_CLOUD_PAIRING_KEYS.ordinal()] = 102;
        }
    }

    public AccessoriesRequestHandler(@NotNull AccessorySession.Factory sessionFactory, @NotNull AccessorySupplier accessorySupplier, @NotNull SessionSupplier sessionSupplier, @NotNull DeviceSupplierV2 deviceSupplier, @NotNull AccessoryScanner accessoryScanner, @NotNull RegistrationSupplier registrationSupplier, @NotNull CompanionDeviceModule companionDeviceModule, @NotNull KotaDownloader kotaDownloader, @NotNull DeviceAccountSupplier deviceAccountSupplier) {
        Intrinsics.checkParameterIsNotNull(sessionFactory, "sessionFactory");
        Intrinsics.checkParameterIsNotNull(accessorySupplier, "accessorySupplier");
        Intrinsics.checkParameterIsNotNull(sessionSupplier, "sessionSupplier");
        Intrinsics.checkParameterIsNotNull(deviceSupplier, "deviceSupplier");
        Intrinsics.checkParameterIsNotNull(accessoryScanner, "accessoryScanner");
        Intrinsics.checkParameterIsNotNull(registrationSupplier, "registrationSupplier");
        Intrinsics.checkParameterIsNotNull(companionDeviceModule, "companionDeviceModule");
        Intrinsics.checkParameterIsNotNull(kotaDownloader, "kotaDownloader");
        Intrinsics.checkParameterIsNotNull(deviceAccountSupplier, "deviceAccountSupplier");
        this.sessionFactory = sessionFactory;
        this.accessorySupplier = accessorySupplier;
        this.sessionSupplier = sessionSupplier;
        this.deviceSupplier = deviceSupplier;
        this.accessoryScanner = accessoryScanner;
        this.registrationSupplier = registrationSupplier;
        this.companionDeviceModule = companionDeviceModule;
        this.kotaDownloader = kotaDownloader;
        this.deviceAccountSupplier = deviceAccountSupplier;
        this.rxIPCServer = new RxIPCServer();
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
        this.lock = new Object();
        this.fitnessUpdateCache = new HashMap();
        this.fitnessSessionListener = new FitnessSessionListener();
        this.sessionSupplier.addSessionListener(this.fitnessSessionListener);
    }

    private final void addDeviceGroup(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        SingleSource addDeviceGroupSingle = this.deviceSupplier.addDeviceGroup(DeviceGroupRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getDeviceGroup()).map(AccessoriesRequestHandler$addDeviceGroup$addDeviceGroupSingle$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(addDeviceGroupSingle, "addDeviceGroupSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, addDeviceGroupSingle);
    }

    private final void addOutgoingNotification(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final NotificationWithContentRequest mo568fromBundle = NotificationWithContentRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single addOutgoingNotificationSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$addOutgoingNotification$addOutgoingNotificationSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Common.ErrorCode> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIpcCallIdentifier());
                return sessionFromIdentifier.getNotificationRepository().addOutgoingNotification(mo568fromBundle.getNotificationId(), mo568fromBundle.getNotificationContent());
            }
        }).map(AccessoriesRequestHandler$addOutgoingNotification$addOutgoingNotificationSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(addOutgoingNotificationSingle, "addOutgoingNotificationSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, addOutgoingNotificationSingle);
    }

    private final void connectUser(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionStringRequest mo568fromBundle = QuerySessionStringRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single connectUserSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$connectUser$connectUserSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Common.ErrorCode> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getSystemRepository().connectUser(mo568fromBundle.getStringValue());
            }
        }).map(AccessoriesRequestHandler$connectUser$connectUserSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(connectUserSingle, "connectUserSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, connectUserSingle);
    }

    private final void createAndConnectSession(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Accessory accessory = AccessoryRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getAccessory();
        if (this.sessionSupplier.getSession(accessory) != null) {
            RxIPCServer rxIPCServer = this.rxIPCServer;
            Completable error = Completable.error(new IOException("Session for accessory " + accessory + " already exists"));
            Intrinsics.checkExpressionValueIsNotNull(error, "Completable.error(IOExce…Connect already exists\"))");
            rxIPCServer.subscribeCompletable(rxIPCEventId, error);
            return;
        }
        this.sessionSupplier.createSession(accessory, this.sessionFactory).connect();
        RxIPCServer rxIPCServer2 = this.rxIPCServer;
        Completable complete = Completable.complete();
        Intrinsics.checkExpressionValueIsNotNull(complete, "Completable.complete()");
        rxIPCServer2.subscribeCompletable(rxIPCEventId, complete);
    }

    private final void createAndConnectSessionAwaitConnection(RxIPCEventId rxIPCEventId, Bundle bundle) {
        final Accessory accessory = AccessoryRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getAccessory();
        Completable createAndConnectSessionAwaitCompletable = Completable.create(new CompletableOnSubscribe() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$createAndConnectSessionAwaitConnection$createAndConnectSessionAwaitCompletable$1
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(final CompletableEmitter completableEmitter) {
                SessionSupplier sessionSupplier;
                SessionSupplier sessionSupplier2;
                AccessorySession.Factory factory;
                sessionSupplier = AccessoriesRequestHandler.this.sessionSupplier;
                if (sessionSupplier.getSession(accessory) == null) {
                    sessionSupplier2 = AccessoriesRequestHandler.this.sessionSupplier;
                    Accessory accessory2 = accessory;
                    factory = AccessoriesRequestHandler.this.sessionFactory;
                    sessionSupplier2.createSession(accessory2, factory, new SessionListener() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$createAndConnectSessionAwaitConnection$createAndConnectSessionAwaitCompletable$1.1
                        private boolean respondedToEmitter;

                        @Override // com.amazon.alexa.accessory.SessionListener
                        public void onSessionConnected() {
                            if (!this.respondedToEmitter) {
                                completableEmitter.onComplete();
                                this.respondedToEmitter = true;
                            }
                        }

                        @Override // com.amazon.alexa.accessory.SessionListener
                        public void onSessionDisconnected() {
                            if (!this.respondedToEmitter) {
                                CompletableEmitter completableEmitter2 = completableEmitter;
                                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Session disconnected before session establishment completed for ");
                                outline107.append(accessory);
                                completableEmitter2.onError(new IllegalStateException(outline107.toString()));
                                this.respondedToEmitter = true;
                            }
                        }

                        @Override // com.amazon.alexa.accessory.SessionListener
                        public void onSessionFailed(@NotNull Throwable throwable) {
                            Intrinsics.checkParameterIsNotNull(throwable, "throwable");
                            if (!this.respondedToEmitter) {
                                completableEmitter.onError(throwable);
                                this.respondedToEmitter = true;
                            }
                        }
                    }).connect();
                    return;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Session for accessory ");
                outline107.append(accessory);
                outline107.append(" already exists");
                completableEmitter.onError(new IOException(outline107.toString()));
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(createAndConnectSessionAwaitCompletable, "createAndConnectSessionAwaitCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, createAndConnectSessionAwaitCompletable);
    }

    private final void createAndConnectSessionWithOptions(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        CreateSessionRequest mo568fromBundle = CreateSessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        if (this.sessionSupplier.getSession(mo568fromBundle.getAccessory()) != null) {
            RxIPCServer rxIPCServer = this.rxIPCServer;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Session for accessory ");
            outline107.append(mo568fromBundle.getAccessory());
            outline107.append(" already exists");
            Completable error = Completable.error(new IOException(outline107.toString()));
            Intrinsics.checkExpressionValueIsNotNull(error, "Completable.error(IOExce…essory} already exists\"))");
            rxIPCServer.subscribeCompletable(rxIPCEventId, error);
            return;
        }
        this.sessionSupplier.createSession(mo568fromBundle.getAccessory(), this.sessionFactory, new SessionListener() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$createAndConnectSessionWithOptions$1
            @Override // com.amazon.alexa.accessory.SessionListener
            public void onSessionConnected() {
            }

            @Override // com.amazon.alexa.accessory.SessionListener
            public void onSessionDisconnected() {
            }

            @Override // com.amazon.alexa.accessory.SessionListener
            public void onSessionFailed(@NotNull Throwable throwable) {
                Intrinsics.checkParameterIsNotNull(throwable, "throwable");
            }
        }, mo568fromBundle.getAccessorySessionOptions()).connect();
        RxIPCServer rxIPCServer2 = this.rxIPCServer;
        Completable complete = Completable.complete();
        Intrinsics.checkExpressionValueIsNotNull(complete, "Completable.complete()");
        rxIPCServer2.subscribeCompletable(rxIPCEventId, complete);
    }

    private final void createAndConnectSessionWithOptionsAwaitConnection(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final CreateSessionRequest mo568fromBundle = CreateSessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable createAndConnectSessionAwaitCompletable = Completable.create(new CompletableOnSubscribe() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$createAndConnectSessionWithOptionsAwaitConnection$createAndConnectSessionAwaitCompletable$1
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(final CompletableEmitter completableEmitter) {
                SessionSupplier sessionSupplier;
                SessionSupplier sessionSupplier2;
                AccessorySession.Factory factory;
                sessionSupplier = AccessoriesRequestHandler.this.sessionSupplier;
                if (sessionSupplier.getSession(mo568fromBundle.getAccessory()) == null) {
                    sessionSupplier2 = AccessoriesRequestHandler.this.sessionSupplier;
                    Accessory accessory = mo568fromBundle.getAccessory();
                    factory = AccessoriesRequestHandler.this.sessionFactory;
                    sessionSupplier2.createSession(accessory, factory, new SessionListener() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$createAndConnectSessionWithOptionsAwaitConnection$createAndConnectSessionAwaitCompletable$1.1
                        private boolean respondedToEmitter;

                        @Override // com.amazon.alexa.accessory.SessionListener
                        public void onSessionConnected() {
                            if (!this.respondedToEmitter) {
                                completableEmitter.onComplete();
                                this.respondedToEmitter = true;
                            }
                        }

                        @Override // com.amazon.alexa.accessory.SessionListener
                        public void onSessionDisconnected() {
                            if (!this.respondedToEmitter) {
                                CompletableEmitter completableEmitter2 = completableEmitter;
                                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Session disconnected before session establishment completed for ");
                                outline107.append(mo568fromBundle.getAccessory());
                                completableEmitter2.onError(new IllegalStateException(outline107.toString()));
                                this.respondedToEmitter = true;
                            }
                        }

                        @Override // com.amazon.alexa.accessory.SessionListener
                        public void onSessionFailed(@NotNull Throwable throwable) {
                            Intrinsics.checkParameterIsNotNull(throwable, "throwable");
                            if (!this.respondedToEmitter) {
                                completableEmitter.onError(throwable);
                                this.respondedToEmitter = true;
                            }
                        }
                    }, mo568fromBundle.getAccessorySessionOptions()).connect();
                    return;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Session for accessory ");
                outline107.append(mo568fromBundle.getAccessory());
                outline107.append(" already exists");
                completableEmitter.onError(new IOException(outline107.toString()));
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(createAndConnectSessionAwaitCompletable, "createAndConnectSessionAwaitCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, createAndConnectSessionAwaitCompletable);
    }

    private final void deleteDeviceAccounts(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        Completable deleteDeviceAccountsCompletable = this.deviceAccountSupplier.deleteDeviceAccounts(QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getIdentifier());
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(deleteDeviceAccountsCompletable, "deleteDeviceAccountsCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, deleteDeviceAccountsCompletable);
    }

    private final void deregister(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Completable deregisterCompletable = this.registrationSupplier.deregister(QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getIdentifier());
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(deregisterCompletable, "deregisterCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, deregisterCompletable);
    }

    private final void disconnectUser(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionStringRequest mo568fromBundle = QuerySessionStringRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single disconnectUserSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$disconnectUser$disconnectUserSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Common.ErrorCode> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getSystemRepository().disconnectUser(mo568fromBundle.getStringValue());
            }
        }).map(AccessoriesRequestHandler$disconnectUser$disconnectUserSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(disconnectUserSingle, "disconnectUserSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, disconnectUserSingle);
    }

    private final void downloadPackageRequest(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        DownloadPackageRequest mo568fromBundle = DownloadPackageRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable downloadCompletable = this.kotaDownloader.downloadPackage(mo568fromBundle.getUpdateRequest(), mo568fromBundle.getInventoryUpdate(), mo568fromBundle.getHardUpdate());
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(downloadCompletable, "downloadCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, downloadCompletable);
    }

    private final void fetchAndStoreDeviceAccount(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        SingleSource getDeviceAccountSingle = this.deviceAccountSupplier.fetchAndStoreDeviceAccount(DeviceAccountRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getRegistration()).map(AccessoriesRequestHandler$fetchAndStoreDeviceAccount$getDeviceAccountSingle$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(getDeviceAccountSingle, "getDeviceAccountSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, getDeviceAccountSingle);
    }

    private final void fitnessUpdateProcessedInternal(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionBooleanStringRequest mo568fromBundle = QuerySessionBooleanStringRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable updateCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$fitnessUpdateProcessedInternal$updateCompletable$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                Completable error;
                synchronized (AccessoriesRequestHandler.this.lock) {
                    Map map = AccessoriesRequestHandler.this.fitnessUpdateCache;
                    sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                    Accessory accessory = sessionFromIdentifier.getAccessory();
                    Intrinsics.checkExpressionValueIsNotNull(accessory, "getSessionFromIdentifier…est.identifier).accessory");
                    LruCache lruCache = (LruCache) map.get(accessory.getAddress());
                    if (lruCache != null) {
                        FitnessSessionUpdate fitnessSessionUpdate = (FitnessSessionUpdate) lruCache.remove(mo568fromBundle.getStringValue());
                        if (fitnessSessionUpdate != null) {
                            if (mo568fromBundle.getBooleanValue()) {
                                fitnessSessionUpdate.markSuccessful();
                            } else {
                                fitnessSessionUpdate.markFailed(new IOException("Update has failed"));
                            }
                            error = Completable.complete();
                        } else {
                            error = null;
                        }
                        if (error != null) {
                        }
                    }
                    error = Completable.error(new IOException("No value for session with identifier" + mo568fromBundle + ".identifier and uuid " + mo568fromBundle.getStringValue() + " in cache"));
                }
                return error;
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(updateCompletable, "updateCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, updateCompletable);
    }

    private final void forwardAtCommand(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final AtCommandRequest mo568fromBundle = AtCommandRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single atCommandSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$forwardAtCommand$atCommandSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Common.ErrorCode> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getCallingRepository().forwardAtCommand(mo568fromBundle.getAtCommand());
            }
        }).map(AccessoriesRequestHandler$forwardAtCommand$atCommandSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(atCommandSingle, "atCommandSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, atCommandSingle);
    }

    private final void getActiveAccessories(RxIPCEventId rxIPCEventId) {
        int collectionSizeOrDefault;
        RxIPCServer rxIPCServer = this.rxIPCServer;
        List<AccessorySession> activeSessions = this.sessionSupplier.getActiveSessions();
        Intrinsics.checkExpressionValueIsNotNull(activeSessions, "sessionSupplier.activeSessions");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(activeSessions, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (AccessorySession it2 : activeSessions) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            arrayList.add(it2.getConnectedAccessory());
        }
        Single just = Single.just(new AccessoryListResponse(arrayList));
        Intrinsics.checkExpressionValueIsNotNull(just, "Single.just(AccessoryLis…it.connectedAccessory }))");
        rxIPCServer.subscribeSingle(rxIPCEventId, just);
    }

    private final void getAudiogram(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionIntRequest mo568fromBundle = QuerySessionIntRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single getAudiogramSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$getAudiogram$getAudiogramSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Hearing.Audiogram> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getHearingEnhancementRepository().getAudiogram(mo568fromBundle.getIntValue());
            }
        }).map(AccessoriesRequestHandler$getAudiogram$getAudiogramSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(getAudiogramSingle, "getAudiogramSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, getAudiogramSingle);
    }

    private final void getCloudPairingAttributes(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single cloudPairingAttributesSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$getCloudPairingAttributes$cloudPairingAttributesSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Cloudpairing.CloudPairingAttributes> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.mo312getCloudPairingRepository().getCloudPairingAttributes();
            }
        }).map(AccessoriesRequestHandler$getCloudPairingAttributes$cloudPairingAttributesSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(cloudPairingAttributesSingle, "cloudPairingAttributesSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, cloudPairingAttributesSingle);
    }

    private final void getCloudPairingStatus(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final CloudPairingSeedRequest mo568fromBundle = CloudPairingSeedRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single cloudPairingStatusSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$getCloudPairingStatus$cloudPairingStatusSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Cloudpairing.CloudPairingStatus> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.mo312getCloudPairingRepository().getCloudPairingStatus(mo568fromBundle.getSeed());
            }
        }).map(AccessoriesRequestHandler$getCloudPairingStatus$cloudPairingStatusSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(cloudPairingStatusSingle, "cloudPairingStatusSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, cloudPairingStatusSingle);
    }

    private final void getDeviceAccount(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        QuerySessionStringRequest mo568fromBundle = QuerySessionStringRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        SingleSource getDeviceAccountSingle = this.deviceAccountSupplier.getDeviceAccount(mo568fromBundle.getIdentifier(), mo568fromBundle.getStringValue()).map(AccessoriesRequestHandler$getDeviceAccount$getDeviceAccountSingle$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(getDeviceAccountSingle, "getDeviceAccountSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, getDeviceAccountSingle);
    }

    private final void getDeviceGroup(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        SingleSource deviceGroupSingle = this.deviceSupplier.getDeviceGroup(QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getIdentifier()).map(AccessoriesRequestHandler$getDeviceGroup$deviceGroupSingle$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(deviceGroupSingle, "deviceGroupSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, deviceGroupSingle);
    }

    private final void getDeviceIdentifier(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        SingleSource getDeviceIdentifierSingle = this.deviceAccountSupplier.getDeviceIdentifier(QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getIdentifier()).map(AccessoriesRequestHandler$getDeviceIdentifier$getDeviceIdentifierSingle$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(getDeviceIdentifierSingle, "getDeviceIdentifierSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, getDeviceIdentifierSingle);
    }

    private final void getDeviceRegistration(RxIPCEventId rxIPCEventId, Bundle bundle) {
        SingleSource getDeviceRegistrationSingle = this.registrationSupplier.getDeviceRegistration(QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getIdentifier()).map(AccessoriesRequestHandler$getDeviceRegistration$getDeviceRegistrationSingle$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(getDeviceRegistrationSingle, "getDeviceRegistrationSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, getDeviceRegistrationSingle);
    }

    private final void getFitnessData(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single getFitnessDataSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$getFitnessData$getFitnessDataSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<FitnessDataSource> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                FitnessRepository fitnessRepository = sessionFromIdentifier.getFitnessRepository();
                Intrinsics.checkExpressionValueIsNotNull(fitnessRepository, "getSessionFromIdentifier…       .fitnessRepository");
                return fitnessRepository.getFitnessData();
            }
        }).map(AccessoriesRequestHandler$getFitnessData$getFitnessDataSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(getFitnessDataSingle, "getFitnessDataSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, getFitnessDataSingle);
    }

    private final void getFitnessDataWithToken(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionByteArrayRequest mo568fromBundle = QuerySessionByteArrayRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single getFitnessDataSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$getFitnessDataWithToken$getFitnessDataSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<FitnessDataSource> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getFitnessRepository().getFitnessData(mo568fromBundle.getByteArray());
            }
        }).map(AccessoriesRequestHandler$getFitnessDataWithToken$getFitnessDataSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(getFitnessDataSingle, "getFitnessDataSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, getFitnessDataSingle);
    }

    private final void getMediaEnhancementCorrectionAmount(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionIntRequest mo568fromBundle = QuerySessionIntRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single correctionAmountSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$getMediaEnhancementCorrectionAmount$correctionAmountSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Hearing.MediaEnhancementCorrectionAmount> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getHearingEnhancementRepository().getMediaEnhancementCorrectionAmount(mo568fromBundle.getIntValue());
            }
        }).map(AccessoriesRequestHandler$getMediaEnhancementCorrectionAmount$correctionAmountSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(correctionAmountSingle, "correctionAmountSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, correctionAmountSingle);
    }

    private final void getOrCreateDeviceRegistration(RxIPCEventId rxIPCEventId, Bundle bundle) {
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single getOrCreateDeviceRegistrationSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$getOrCreateDeviceRegistration$getOrCreateDeviceRegistrationSingle$1

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: AccessoriesRequestHandler.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/accessoryclient/common/query/response/DeviceRegistrationResponse;", "it", "Lcom/amazon/alexa/accessory/registration/DeviceRegistration;", "kotlin.jvm.PlatformType", "apply"}, k = 3, mv = {1, 1, 16})
            /* renamed from: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$getOrCreateDeviceRegistration$getOrCreateDeviceRegistrationSingle$1$1  reason: invalid class name */
            /* loaded from: classes6.dex */
            public static final class AnonymousClass1<T, R> implements Function<T, R> {
                public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

                AnonymousClass1() {
                }

                @Override // io.reactivex.rxjava3.functions.Function
                @NotNull
                /* renamed from: apply */
                public final DeviceRegistrationResponse mo10358apply(DeviceRegistration it2) {
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    return new DeviceRegistrationResponse(it2);
                }
            }

            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<DeviceRegistrationResponse> mo10365get() {
                RegistrationSupplier registrationSupplier;
                AccessorySession sessionFromIdentifier;
                registrationSupplier = AccessoriesRequestHandler.this.registrationSupplier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return registrationSupplier.getOrCreateDeviceRegistration(sessionFromIdentifier).map(AnonymousClass1.INSTANCE);
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(getOrCreateDeviceRegistrationSingle, "getOrCreateDeviceRegistrationSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, getOrCreateDeviceRegistrationSingle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AccessorySession getSessionFromIdentifier(String str) throws SessionNotFoundException {
        for (AccessorySession session : this.sessionSupplier.getActiveSessions()) {
            Intrinsics.checkExpressionValueIsNotNull(session, "session");
            Accessory connectedAccessory = session.getConnectedAccessory();
            Intrinsics.checkExpressionValueIsNotNull(connectedAccessory, "session.connectedAccessory");
            if (!Intrinsics.areEqual(connectedAccessory.getAddress(), str)) {
                Accessory accessory = session.getAccessory();
                Intrinsics.checkExpressionValueIsNotNull(accessory, "session.accessory");
                if (Intrinsics.areEqual(accessory.getAddress(), str)) {
                }
            }
            return session;
        }
        throw new SessionNotFoundException(str);
    }

    private final void handleRequest(ApiIdentifier apiIdentifier, RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        switch (WhenMappings.$EnumSwitchMapping$1[apiIdentifier.ordinal()]) {
            case 1:
                queryConnectionStatus(rxIPCEventId, bundle);
                return;
            case 2:
                queryConnectedAccessory(rxIPCEventId, bundle);
                return;
            case 3:
                releaseSession(rxIPCEventId, bundle);
                return;
            case 4:
                linkAccessory(rxIPCEventId, bundle);
                return;
            case 5:
                unlinkAccessory(rxIPCEventId, bundle);
                return;
            case 6:
                observeSessionCreated(rxIPCEventId);
                return;
            case 7:
                observeSessionConnected(rxIPCEventId);
                return;
            case 8:
                observeSessionDisconnected(rxIPCEventId);
                return;
            case 9:
                observeSessionFailed(rxIPCEventId);
                return;
            case 10:
                observeSessionReleased(rxIPCEventId);
                return;
            case 11:
                observeSessionTransportChanged(rxIPCEventId);
                return;
            case 12:
                getActiveAccessories(rxIPCEventId);
                return;
            case 13:
                createAndConnectSession(rxIPCEventId, bundle);
                return;
            case 14:
                createAndConnectSessionWithOptions(rxIPCEventId, bundle);
                return;
            case 15:
                createAndConnectSessionAwaitConnection(rxIPCEventId, bundle);
                return;
            case 16:
                createAndConnectSessionWithOptionsAwaitConnection(rxIPCEventId, bundle);
                return;
            case 17:
                setState(rxIPCEventId, bundle);
                return;
            case 18:
                queryState(rxIPCEventId, bundle);
                return;
            case 19:
                requestOverrideAssistant(rxIPCEventId, bundle);
                return;
            case 20:
                requestCompleteSetup(rxIPCEventId, bundle);
                return;
            case 21:
                queryDeviceConfiguration(rxIPCEventId, bundle);
                return;
            case 22:
                requestUpdateDeviceInformation(rxIPCEventId, bundle);
                return;
            case 23:
                queryDeviceInformationSet(rxIPCEventId, bundle);
                return;
            case 24:
                requestStartSetup(rxIPCEventId, bundle);
                return;
            case 25:
                queryDeviceFeatures(rxIPCEventId, bundle);
                return;
            case 26:
                queryDeviceGroups(rxIPCEventId);
                return;
            case 27:
                removeDeviceGroup(rxIPCEventId, bundle);
                return;
            case 28:
                removeDeviceGroupByDeviceGroup(rxIPCEventId, bundle);
                return;
            case 29:
                getDeviceGroup(rxIPCEventId, bundle);
                return;
            case 30:
                hasDeviceGroup(rxIPCEventId, bundle);
                return;
            case 31:
                updateDeviceGroup(rxIPCEventId, bundle);
                return;
            case 32:
                addDeviceGroup(rxIPCEventId, bundle);
                return;
            case 33:
                queryFirmwareInformationSet(rxIPCEventId, bundle);
                return;
            case 34:
                queryFirmwareUpdateStatus(rxIPCEventId, bundle);
                return;
            case 35:
                initiateFirmwareTransfer(rxIPCEventId, bundle);
                return;
            case 36:
                queryInventoryUpdateSet(rxIPCEventId, bundle);
                return;
            case 37:
                observeOnBleAccessoryFoundNearby(rxIPCEventId);
                return;
            case 38:
                observeOnBleDataBeaconFoundNearby(rxIPCEventId);
                return;
            case 39:
                observeOnConnectedAccessoryFound(rxIPCEventId);
                return;
            case 40:
                observeOnConnectedAccessoryLost(rxIPCEventId);
                return;
            case 41:
                getDeviceRegistration(rxIPCEventId, bundle);
                return;
            case 42:
                getOrCreateDeviceRegistration(rxIPCEventId, bundle);
                return;
            case 43:
                deregister(rxIPCEventId, bundle);
                return;
            case 44:
                queryRegistrations(rxIPCEventId);
                return;
            case 45:
                queryInputConfiguration(rxIPCEventId, bundle);
                return;
            case 46:
                setInputConfiguration(rxIPCEventId, bundle);
                return;
            case 47:
                resetInputConfiguration(rxIPCEventId, bundle);
                return;
            case 48:
                shouldUpgradeTransport(rxIPCEventId, bundle);
                return;
            case 49:
                requestTransportUpgrade(rxIPCEventId, bundle);
                return;
            case 50:
                requestCompanionDevice(rxIPCEventId, bundle);
                return;
            case 51:
                queryNewCompanionDevices(rxIPCEventId);
                return;
            case 52:
                queryRemovedCompanionDevices(rxIPCEventId);
                return;
            case 53:
                isCompanionDevice(rxIPCEventId, bundle);
                return;
            case 54:
                removeCompanionDevice(rxIPCEventId, bundle);
                return;
            case 55:
                queryUsers(rxIPCEventId, bundle);
                return;
            case 56:
                connectUser(rxIPCEventId, bundle);
                return;
            case 57:
                disconnectUser(rxIPCEventId, bundle);
                return;
            case 58:
                unpairUser(rxIPCEventId, bundle);
                return;
            case 59:
                requestResetConnection(rxIPCEventId, bundle);
                return;
            case 60:
                queryLocales(rxIPCEventId, bundle);
                return;
            case 61:
                setLocale(rxIPCEventId, bundle);
                return;
            case 62:
                queryIsAwaitingDerivedKeys(rxIPCEventId, bundle);
                return;
            case 63:
                kotaUpdateRequest(rxIPCEventId, bundle);
                return;
            case 64:
                inventoryUpdateRequest(rxIPCEventId, bundle);
                return;
            case 65:
                downloadPackageRequest(rxIPCEventId, bundle);
                return;
            case 66:
                forwardAtCommand(rxIPCEventId, bundle);
                return;
            case 67:
                getFitnessData(rxIPCEventId, bundle);
                return;
            case 68:
                getFitnessDataWithToken(rxIPCEventId, bundle);
                return;
            case 69:
                observeFitnessSessionUpdates(rxIPCEventId, bundle);
                return;
            case 70:
                fitnessUpdateProcessedInternal(rxIPCEventId, bundle);
                return;
            case 71:
                setFitnessSession(rxIPCEventId, bundle);
                return;
            case 72:
                startLiveFitnessData(rxIPCEventId, bundle);
                return;
            case 73:
                stopLiveFitnessData(rxIPCEventId, bundle);
                return;
            case 74:
                observeLiveFitnessDataNotifications(rxIPCEventId, bundle);
                return;
            case 75:
                observeStopLiveFitnessDataNotifications(rxIPCEventId, bundle);
                return;
            case 76:
                observeFitnessDataAvailableNotifications(rxIPCEventId, bundle);
                return;
            case 77:
                fetchAndStoreDeviceAccount(rxIPCEventId, bundle);
                return;
            case 78:
                getDeviceAccount(rxIPCEventId, bundle);
                return;
            case 79:
                getDeviceIdentifier(rxIPCEventId, bundle);
                return;
            case 80:
                deleteDeviceAccounts(rxIPCEventId, bundle);
                return;
            case 81:
                queryDiagnostics(rxIPCEventId, bundle);
                return;
            case 82:
                issueMediaControl(rxIPCEventId, bundle);
                return;
            case 83:
                queryMediaControl(rxIPCEventId, bundle);
                return;
            case 84:
                setPlaybackStatus(rxIPCEventId, bundle);
                return;
            case 85:
                queryRegisterForMediaEvents(rxIPCEventId, bundle);
                return;
            case 86:
                getAudiogram(rxIPCEventId, bundle);
                return;
            case 87:
                setAudiogram(rxIPCEventId, bundle);
                return;
            case 88:
                getMediaEnhancementCorrectionAmount(rxIPCEventId, bundle);
                return;
            case 89:
                setMediaEnhancementCorrectionAmount(rxIPCEventId, bundle);
                return;
            case 90:
                queryCblLoginState(rxIPCEventId, bundle);
                return;
            case 91:
                requestCblInformation(rxIPCEventId, bundle);
                return;
            case 92:
                updateCallInfo(rxIPCEventId, bundle);
                return;
            case 93:
                addOutgoingNotification(rxIPCEventId, bundle);
                return;
            case 94:
                updateOutgoingNotification(rxIPCEventId, bundle);
                return;
            case 95:
                removeOutgoingNotification(rxIPCEventId, bundle);
                return;
            case 96:
                queryActionCommandsForOutgoingNotifications(rxIPCEventId, bundle);
                return;
            case 97:
                queryDavsI18nConfig(rxIPCEventId, bundle);
                return;
            case 98:
                getCloudPairingStatus(rxIPCEventId, bundle);
                return;
            case 99:
                getCloudPairingAttributes(rxIPCEventId, bundle);
                return;
            case 100:
                setCloudPairingKeys(rxIPCEventId, bundle);
                return;
            case 101:
                replaceCloudPairingKeys(rxIPCEventId, bundle);
                return;
            case 102:
                removeCloudPairingKeys(rxIPCEventId, bundle);
                return;
            default:
                RxIPCServer rxIPCServer = this.rxIPCServer;
                Completable error = Completable.error(new IOException(apiIdentifier.name() + " is not supported"));
                Intrinsics.checkExpressionValueIsNotNull(error, "Completable.error(IOExce…name} is not supported\"))");
                rxIPCServer.subscribeCompletable(rxIPCEventId, error);
                return;
        }
    }

    private final void hasDeviceGroup(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        SingleSource deviceGroupSingle = this.deviceSupplier.hasDeviceGroup(QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getIdentifier()).map(AccessoriesRequestHandler$hasDeviceGroup$deviceGroupSingle$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(deviceGroupSingle, "deviceGroupSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, deviceGroupSingle);
    }

    private final void initiateFirmwareTransfer(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable initiateFirmwareTransferCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$initiateFirmwareTransfer$initiateFirmwareTransferCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getFirmwareRepositoryV2().initiateFirmwareTransfer();
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(initiateFirmwareTransferCompletable, "initiateFirmwareTransferCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, initiateFirmwareTransferCompletable);
    }

    private final void inventoryUpdateRequest(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        InventoryUpdateRequest mo568fromBundle = InventoryUpdateRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        MaybeSource inventoryUpdateMaybe = this.kotaDownloader.getAvailableInventoryUpdate(mo568fromBundle.getUpdateRequest(), mo568fromBundle.getForce()).map(AccessoriesRequestHandler$inventoryUpdateRequest$inventoryUpdateMaybe$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(inventoryUpdateMaybe, "inventoryUpdateMaybe");
        rxIPCServer.subscribeMaybe(rxIPCEventId, inventoryUpdateMaybe);
    }

    @SuppressLint({"NewApi"})
    private final void isCompanionDevice(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        Single isCompanionDeviceSingle = Single.just(Boolean.valueOf(this.companionDeviceModule.isCompanionDevice(QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getIdentifier()))).map(AccessoriesRequestHandler$isCompanionDevice$isCompanionDeviceSingle$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(isCompanionDeviceSingle, "isCompanionDeviceSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, isCompanionDeviceSingle);
    }

    private final void issueMediaControl(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final MediaControlRequest mo568fromBundle = MediaControlRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable issueMediaControlCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$issueMediaControl$issueMediaControlCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getMediaRepository().issueMediaControl(mo568fromBundle.getMediaControl());
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(issueMediaControlCompletable, "issueMediaControlCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, issueMediaControlCompletable);
    }

    private final void kotaUpdateRequest(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        UpdateRequestRequest mo568fromBundle = UpdateRequestRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        SingleSource updateRequestSingle = this.kotaDownloader.generateUpdateRequest(mo568fromBundle.getDeviceInformation(), mo568fromBundle.getFirmwareInformation()).map(AccessoriesRequestHandler$kotaUpdateRequest$updateRequestSingle$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(updateRequestSingle, "updateRequestSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, updateRequestSingle);
    }

    private final void linkAccessory(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final AccessoryRequest mo568fromBundle = AccessoryRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable linkAccessoryCompletable = Completable.create(new CompletableOnSubscribe() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$linkAccessory$linkAccessoryCompletable$1
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(final CompletableEmitter completableEmitter) {
                AccessorySupplier accessorySupplier;
                accessorySupplier = AccessoriesRequestHandler.this.accessorySupplier;
                accessorySupplier.link(mo568fromBundle.getAccessory(), new Accessories.SimpleAccessoryLinkListener() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$linkAccessory$linkAccessoryCompletable$1.1
                    @Override // com.amazon.alexa.accessory.Accessories.SimpleAccessoryLinkListener, com.amazon.alexa.accessory.AccessoryLinkListener
                    public void onAccessoryLinkFailed(@NotNull Accessory accessory, @NotNull Throwable e) {
                        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
                        Intrinsics.checkParameterIsNotNull(e, "e");
                        CompletableEmitter.this.onError(e);
                    }

                    @Override // com.amazon.alexa.accessory.Accessories.SimpleAccessoryLinkListener, com.amazon.alexa.accessory.AccessoryLinkListener
                    public void onAccessoryLinked(@NotNull Accessory accessory) {
                        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
                        CompletableEmitter.this.onComplete();
                    }
                });
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(linkAccessoryCompletable, "linkAccessoryCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, linkAccessoryCompletable);
    }

    private final void observeFitnessDataAvailableNotifications(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable fitnessDataAvailableNotificationObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$observeFitnessDataAvailableNotifications$fitnessDataAvailableNotificationObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<FitnessDataAvailableNotification> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getFitnessRepository().observeFitnessDataAvailableNotifications();
            }
        }).map(AccessoriesRequestHandler$observeFitnessDataAvailableNotifications$fitnessDataAvailableNotificationObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(fitnessDataAvailableNotificationObservable, "fitnessDataAvailableNotificationObservable");
        rxIPCServer.subscribe(rxIPCEventId, fitnessDataAvailableNotificationObservable);
    }

    private final void observeFitnessSessionUpdates(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable fitnessSessionUpdatesObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$observeFitnessSessionUpdates$fitnessSessionUpdatesObservable$1

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: AccessoriesRequestHandler.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/accessoryclient/common/query/response/FitnessSessionUpdateMetadataResponse;", "it", "Lcom/amazon/alexa/accessoryclient/common/api/FitnessSessionUpdateMetadata;", "kotlin.jvm.PlatformType", "apply"}, k = 3, mv = {1, 1, 16})
            /* renamed from: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$observeFitnessSessionUpdates$fitnessSessionUpdatesObservable$1$2  reason: invalid class name */
            /* loaded from: classes6.dex */
            public static final class AnonymousClass2<T, R> implements Function<T, R> {
                public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

                AnonymousClass2() {
                }

                @Override // io.reactivex.rxjava3.functions.Function
                @NotNull
                /* renamed from: apply */
                public final FitnessSessionUpdateMetadataResponse mo10358apply(FitnessSessionUpdateMetadata it2) {
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    return new FitnessSessionUpdateMetadataResponse(it2);
                }
            }

            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<FitnessSessionUpdateMetadataResponse> mo10365get() {
                final AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getFitnessRepository().observeFitnessSessionUpdates().observeOn(AndroidSchedulers.mainThread()).map(new Function<T, R>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$observeFitnessSessionUpdates$fitnessSessionUpdatesObservable$1.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    @NotNull
                    /* renamed from: apply */
                    public final FitnessSessionUpdateMetadata mo10358apply(FitnessSessionUpdate fitnessSessionUpdate) {
                        AccessorySession accessorySession;
                        Intrinsics.checkExpressionValueIsNotNull(fitnessSessionUpdate, "fitnessSessionUpdate");
                        FitnessSessionUpdate.Origin origin = fitnessSessionUpdate.getOrigin();
                        Intrinsics.checkExpressionValueIsNotNull(origin, "fitnessSessionUpdate.origin");
                        FitnessSession fitnessSession = fitnessSessionUpdate.getFitnessSession();
                        Intrinsics.checkExpressionValueIsNotNull(fitnessSession, "fitnessSessionUpdate.fitnessSession");
                        FitnessSessionUpdateMetadata fitnessSessionUpdateMetadata = new FitnessSessionUpdateMetadata(origin, fitnessSession, null, 4, null);
                        AccessorySession accessorySession2 = sessionFromIdentifier;
                        try {
                            accessorySession = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                        } catch (AccessoriesRequestHandler.SessionNotFoundException unused) {
                            accessorySession = null;
                        }
                        if (!Intrinsics.areEqual(accessorySession2, accessorySession)) {
                            return fitnessSessionUpdateMetadata;
                        }
                        synchronized (AccessoriesRequestHandler.this.lock) {
                            Map map = AccessoriesRequestHandler.this.fitnessUpdateCache;
                            Accessory accessory = sessionFromIdentifier.getAccessory();
                            Intrinsics.checkExpressionValueIsNotNull(accessory, "session.accessory");
                            String address = accessory.getAddress();
                            Intrinsics.checkExpressionValueIsNotNull(address, "session.accessory.address");
                            Map map2 = AccessoriesRequestHandler.this.fitnessUpdateCache;
                            Accessory accessory2 = sessionFromIdentifier.getAccessory();
                            Intrinsics.checkExpressionValueIsNotNull(accessory2, "session.accessory");
                            LruCache lruCache = (LruCache) map2.get(accessory2.getAddress());
                            if (lruCache != null) {
                                lruCache.put(fitnessSessionUpdateMetadata.getMetadataUuid(), fitnessSessionUpdate);
                            } else {
                                lruCache = new LruCache(32);
                                lruCache.put(fitnessSessionUpdateMetadata.getMetadataUuid(), fitnessSessionUpdate);
                            }
                            LruCache lruCache2 = (LruCache) map.put(address, lruCache);
                        }
                        return fitnessSessionUpdateMetadata;
                    }
                }).map(AnonymousClass2.INSTANCE);
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(fitnessSessionUpdatesObservable, "fitnessSessionUpdatesObservable");
        rxIPCServer.subscribe(rxIPCEventId, fitnessSessionUpdatesObservable);
    }

    private final void observeLiveFitnessDataNotifications(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable liveFitnessDataNotificationsObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$observeLiveFitnessDataNotifications$liveFitnessDataNotificationsObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<Fitness.LiveFitnessData> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getFitnessRepository().observeLiveFitnessDataNotifications();
            }
        }).map(AccessoriesRequestHandler$observeLiveFitnessDataNotifications$liveFitnessDataNotificationsObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(liveFitnessDataNotificationsObservable, "liveFitnessDataNotificationsObservable");
        rxIPCServer.subscribe(rxIPCEventId, liveFitnessDataNotificationsObservable);
    }

    private final void observeOnBleAccessoryFoundNearby(RxIPCEventId rxIPCEventId) {
        Observable observeNearbyLeObservable = Observable.create(new AccessoriesRequestHandler$observeOnBleAccessoryFoundNearby$observeNearbyLeObservable$1(this)).throttleFirst(200L, TimeUnit.MILLISECONDS);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(observeNearbyLeObservable, "observeNearbyLeObservable");
        rxIPCServer.subscribe(rxIPCEventId, observeNearbyLeObservable);
    }

    private final void observeOnBleDataBeaconFoundNearby(RxIPCEventId rxIPCEventId) {
        Observable observeOnBleDataBeaconFoundNearbyObservable = Observable.create(new AccessoriesRequestHandler$observeOnBleDataBeaconFoundNearby$observeOnBleDataBeaconFoundNearbyObservable$1(this));
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(observeOnBleDataBeaconFoundNearbyObservable, "observeOnBleDataBeaconFoundNearbyObservable");
        rxIPCServer.subscribe(rxIPCEventId, observeOnBleDataBeaconFoundNearbyObservable);
    }

    private final void observeOnConnectedAccessoryFound(RxIPCEventId rxIPCEventId) {
        Observable observeConnectedAccessoryFoundObservable = Observable.create(new AccessoriesRequestHandler$observeOnConnectedAccessoryFound$observeConnectedAccessoryFoundObservable$1(this));
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(observeConnectedAccessoryFoundObservable, "observeConnectedAccessoryFoundObservable");
        rxIPCServer.subscribe(rxIPCEventId, observeConnectedAccessoryFoundObservable);
    }

    private final void observeOnConnectedAccessoryLost(RxIPCEventId rxIPCEventId) {
        Observable observeConnectedAccessoryLostObservable = Observable.create(new AccessoriesRequestHandler$observeOnConnectedAccessoryLost$observeConnectedAccessoryLostObservable$1(this));
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(observeConnectedAccessoryLostObservable, "observeConnectedAccessoryLostObservable");
        rxIPCServer.subscribe(rxIPCEventId, observeConnectedAccessoryLostObservable);
    }

    private final void observeSessionConnected(RxIPCEventId rxIPCEventId) {
        Preconditions.Companion.hasLock(this.lock);
        Observable observeSessionConnectedObservable = Observable.create(new AccessoriesRequestHandler$observeSessionConnected$observeSessionConnectedObservable$1(this)).map(AccessoriesRequestHandler$observeSessionConnected$observeSessionConnectedObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(observeSessionConnectedObservable, "observeSessionConnectedObservable");
        rxIPCServer.subscribe(rxIPCEventId, observeSessionConnectedObservable);
    }

    private final void observeSessionCreated(RxIPCEventId rxIPCEventId) {
        Preconditions.Companion.hasLock(this.lock);
        Observable observeSessionCreatedObservable = Observable.create(new AccessoriesRequestHandler$observeSessionCreated$observeSessionCreatedObservable$1(this)).map(AccessoriesRequestHandler$observeSessionCreated$observeSessionCreatedObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(observeSessionCreatedObservable, "observeSessionCreatedObservable");
        rxIPCServer.subscribe(rxIPCEventId, observeSessionCreatedObservable);
    }

    private final void observeSessionDisconnected(RxIPCEventId rxIPCEventId) {
        Preconditions.Companion.hasLock(this.lock);
        Observable observeSessionDisconnectedObservable = Observable.create(new AccessoriesRequestHandler$observeSessionDisconnected$observeSessionDisconnectedObservable$1(this)).map(AccessoriesRequestHandler$observeSessionDisconnected$observeSessionDisconnectedObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(observeSessionDisconnectedObservable, "observeSessionDisconnectedObservable");
        rxIPCServer.subscribe(rxIPCEventId, observeSessionDisconnectedObservable);
    }

    private final void observeSessionFailed(RxIPCEventId rxIPCEventId) {
        Preconditions.Companion.hasLock(this.lock);
        Observable observeSessionFailedObservable = Observable.create(new AccessoriesRequestHandler$observeSessionFailed$observeSessionFailedObservable$1(this)).map(AccessoriesRequestHandler$observeSessionFailed$observeSessionFailedObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(observeSessionFailedObservable, "observeSessionFailedObservable");
        rxIPCServer.subscribe(rxIPCEventId, observeSessionFailedObservable);
    }

    private final void observeSessionReleased(RxIPCEventId rxIPCEventId) {
        Preconditions.Companion.hasLock(this.lock);
        Observable observeSessionReleasedObservable = Observable.create(new AccessoriesRequestHandler$observeSessionReleased$observeSessionReleasedObservable$1(this)).map(AccessoriesRequestHandler$observeSessionReleased$observeSessionReleasedObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(observeSessionReleasedObservable, "observeSessionReleasedObservable");
        rxIPCServer.subscribe(rxIPCEventId, observeSessionReleasedObservable);
    }

    private final void observeSessionTransportChanged(RxIPCEventId rxIPCEventId) {
        Preconditions.Companion.hasLock(this.lock);
        Observable observeSessionTransportChangeObservable = Observable.create(new AccessoriesRequestHandler$observeSessionTransportChanged$observeSessionTransportChangeObservable$1(this)).map(AccessoriesRequestHandler$observeSessionTransportChanged$observeSessionTransportChangeObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(observeSessionTransportChangeObservable, "observeSessionTransportChangeObservable");
        rxIPCServer.subscribe(rxIPCEventId, observeSessionTransportChangeObservable);
    }

    private final void observeStopLiveFitnessDataNotifications(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable stopLiveFitnessDataNotificationsObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$observeStopLiveFitnessDataNotifications$stopLiveFitnessDataNotificationsObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<Fitness.StopLiveFitnessData> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getFitnessRepository().observeStopLiveFitnessDataNotifications();
            }
        }).map(AccessoriesRequestHandler$observeStopLiveFitnessDataNotifications$stopLiveFitnessDataNotificationsObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(stopLiveFitnessDataNotificationsObservable, "stopLiveFitnessDataNotificationsObservable");
        rxIPCServer.subscribe(rxIPCEventId, stopLiveFitnessDataNotificationsObservable);
    }

    private final void queryActionCommandsForOutgoingNotifications(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable queryActionCommandsObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryActionCommandsForOutgoingNotifications$queryActionCommandsObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<Notification.ExecuteNotificationAction> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getNotificationRepository().queryActionCommandsForOutgoingNotifications().toObservable();
            }
        }).map(AccessoriesRequestHandler$queryActionCommandsForOutgoingNotifications$queryActionCommandsObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryActionCommandsObservable, "queryActionCommandsObservable");
        rxIPCServer.subscribe(rxIPCEventId, queryActionCommandsObservable);
    }

    private final void queryCblLoginState(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable requestCblLoginStateObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryCblLoginState$requestCblLoginStateObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<Cbl.CblLoginState> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getCblRepository().queryCblLoginState();
            }
        }).map(AccessoriesRequestHandler$queryCblLoginState$requestCblLoginStateObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(requestCblLoginStateObservable, "requestCblLoginStateObservable");
        rxIPCServer.subscribe(rxIPCEventId, requestCblLoginStateObservable);
    }

    private final void queryConnectedAccessory(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single connectedAccessorySingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryConnectedAccessory$connectedAccessorySingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Accessory> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return Single.just(sessionFromIdentifier.getConnectedAccessory());
            }
        }).map(AccessoriesRequestHandler$queryConnectedAccessory$connectedAccessorySingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(connectedAccessorySingle, "connectedAccessorySingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, connectedAccessorySingle);
    }

    private final void queryConnectionStatus(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single connectionStatusSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryConnectionStatus$connectionStatusSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<ConnectionStatus> mo10365get() {
                AccessorySession sessionFromIdentifier;
                try {
                    sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                    if (sessionFromIdentifier.isConnecting()) {
                        return Single.just(ConnectionStatus.CONNECTING);
                    }
                    if (sessionFromIdentifier.isConnected()) {
                        return Single.just(ConnectionStatus.CONNECTED);
                    }
                    return Single.just(ConnectionStatus.DISCONNECTED);
                } catch (AccessoriesRequestHandler.SessionNotFoundException unused) {
                    return Single.just(ConnectionStatus.NONEXISTENT);
                }
            }
        }).map(AccessoriesRequestHandler$queryConnectionStatus$connectionStatusSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(connectionStatusSingle, "connectionStatusSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, connectionStatusSingle);
    }

    private final void queryDavsI18nConfig(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable queryI18nConfig = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryDavsI18nConfig$queryI18nConfig$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<DavsI18nConfig> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getSystemRepository().queryDavsI18nConfig();
            }
        }).map(AccessoriesRequestHandler$queryDavsI18nConfig$queryI18nConfig$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryI18nConfig, "queryI18nConfig");
        rxIPCServer.subscribe(rxIPCEventId, queryI18nConfig);
    }

    private final void queryDeviceConfiguration(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable queryDeviceConfigurationObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryDeviceConfiguration$queryDeviceConfigurationObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<Device.DeviceConfiguration> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getDeviceRepositoryV2().queryDeviceConfiguration();
            }
        }).map(AccessoriesRequestHandler$queryDeviceConfiguration$queryDeviceConfigurationObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryDeviceConfigurationObservable, "queryDeviceConfigurationObservable");
        rxIPCServer.subscribe(rxIPCEventId, queryDeviceConfigurationObservable);
    }

    private final void queryDeviceFeatures(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single deviceFeaturesSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryDeviceFeatures$deviceFeaturesSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<DeviceFeatures> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getDeviceRepositoryV2().queryDeviceFeatures();
            }
        }).map(AccessoriesRequestHandler$queryDeviceFeatures$deviceFeaturesSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(deviceFeaturesSingle, "deviceFeaturesSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, deviceFeaturesSingle);
    }

    private final void queryDeviceGroups(RxIPCEventId rxIPCEventId) {
        Preconditions.Companion.hasLock(this.lock);
        ObservableSource queryDeviceGroupsObservable = this.deviceSupplier.queryDeviceGroups().map(AccessoriesRequestHandler$queryDeviceGroups$queryDeviceGroupsObservable$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryDeviceGroupsObservable, "queryDeviceGroupsObservable");
        rxIPCServer.subscribe(rxIPCEventId, queryDeviceGroupsObservable);
    }

    private final void queryDeviceInformationSet(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable queryDeviceInformationSetObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryDeviceInformationSet$queryDeviceInformationSetObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<Set<Device.DeviceInformation>> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getDeviceRepositoryV2().queryDeviceInformationSet();
            }
        }).map(AccessoriesRequestHandler$queryDeviceInformationSet$queryDeviceInformationSetObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryDeviceInformationSetObservable, "queryDeviceInformationSetObservable");
        rxIPCServer.subscribe(rxIPCEventId, queryDeviceInformationSetObservable);
    }

    private final void queryDiagnostics(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final DiagnosticsRequest mo568fromBundle = DiagnosticsRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single queryDiagnostics = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryDiagnostics$queryDiagnostics$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Source> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getDiagnosticsRepository().queryDiagnostics(mo568fromBundle.getDiagnosticsType());
            }
        }).map(AccessoriesRequestHandler$queryDiagnostics$queryDiagnostics$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryDiagnostics, "queryDiagnostics");
        rxIPCServer.subscribeSingle(rxIPCEventId, queryDiagnostics);
    }

    private final void queryFirmwareInformationSet(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single queryFirmwareInformationSetSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryFirmwareInformationSet$queryFirmwareInformationSetSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Set<Firmware.FirmwareInformation>> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getFirmwareRepositoryV2().queryInformationSet();
            }
        }).map(AccessoriesRequestHandler$queryFirmwareInformationSet$queryFirmwareInformationSetSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryFirmwareInformationSetSingle, "queryFirmwareInformationSetSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, queryFirmwareInformationSetSingle);
    }

    private final void queryFirmwareUpdateStatus(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable queryFirmwareUpdateStatusObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryFirmwareUpdateStatus$queryFirmwareUpdateStatusObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<FirmwareUpdateStatus> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getFirmwareRepositoryV2().queryUpdateStatus().toObservable();
            }
        }).map(AccessoriesRequestHandler$queryFirmwareUpdateStatus$queryFirmwareUpdateStatusObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryFirmwareUpdateStatusObservable, "queryFirmwareUpdateStatusObservable");
        rxIPCServer.subscribe(rxIPCEventId, queryFirmwareUpdateStatusObservable);
    }

    private final void queryInputConfiguration(RxIPCEventId rxIPCEventId, Bundle bundle) {
        final QuerySessionIntRequest mo568fromBundle = QuerySessionIntRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable queryInputObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryInputConfiguration$queryInputObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<Input.InputBehaviorConfigurationSet> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getInputRepository().queryConfiguration(mo568fromBundle.getIntValue()).toObservable();
            }
        }).map(AccessoriesRequestHandler$queryInputConfiguration$queryInputObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryInputObservable, "queryInputObservable");
        rxIPCServer.subscribe(rxIPCEventId, queryInputObservable);
    }

    private final void queryInventoryUpdateSet(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionBooleanRequest mo568fromBundle = QuerySessionBooleanRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single queryInventoryUpdateBundleSetSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryInventoryUpdateSet$queryInventoryUpdateBundleSetSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Set<InventoryUpdateBundle>> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getFirmwareRepositoryV2().queryInventoryUpdateSet(mo568fromBundle.getBooleanValue());
            }
        }).map(AccessoriesRequestHandler$queryInventoryUpdateSet$queryInventoryUpdateBundleSetSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryInventoryUpdateBundleSetSingle, "queryInventoryUpdateBundleSetSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, queryInventoryUpdateBundleSetSingle);
    }

    private final void queryIsAwaitingDerivedKeys(RxIPCEventId rxIPCEventId, Bundle bundle) {
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable queryIsAwaitingDerivedKeysObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryIsAwaitingDerivedKeys$queryIsAwaitingDerivedKeysObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<Boolean> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getKeyExchangeRepository().queryIsAwaitingDerivedKeys();
            }
        }).map(AccessoriesRequestHandler$queryIsAwaitingDerivedKeys$queryIsAwaitingDerivedKeysObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryIsAwaitingDerivedKeysObservable, "queryIsAwaitingDerivedKeysObservable");
        rxIPCServer.subscribe(rxIPCEventId, queryIsAwaitingDerivedKeysObservable);
    }

    private final void queryLocales(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable queryLocalesObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryLocales$queryLocalesObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<System.Locales> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getSystemRepository().queryLocales().toObservable();
            }
        }).map(AccessoriesRequestHandler$queryLocales$queryLocalesObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryLocalesObservable, "queryLocalesObservable");
        rxIPCServer.subscribe(rxIPCEventId, queryLocalesObservable);
    }

    private final void queryMediaControl(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable queryMediaControlObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryMediaControl$queryMediaControlObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<Media.MediaControl> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getMediaRepository().queryMediaControls();
            }
        }).map(AccessoriesRequestHandler$queryMediaControl$queryMediaControlObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryMediaControlObservable, "queryMediaControlObservable");
        rxIPCServer.subscribe(rxIPCEventId, queryMediaControlObservable);
    }

    @SuppressLint({"NewApi"})
    private final void queryNewCompanionDevices(RxIPCEventId rxIPCEventId) {
        Preconditions.Companion.hasLock(this.lock);
        ObservableSource queryCompanionDeviceObservable = this.companionDeviceModule.queryNewCompanionDevices().map(AccessoriesRequestHandler$queryNewCompanionDevices$queryCompanionDeviceObservable$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryCompanionDeviceObservable, "queryCompanionDeviceObservable");
        rxIPCServer.subscribe(rxIPCEventId, queryCompanionDeviceObservable);
    }

    private final void queryRegisterForMediaEvents(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable queryRegisterForMediaEventsObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryRegisterForMediaEvents$queryRegisterForMediaEventsObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<Media.RegisterForMediaEvents> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getMediaRepository().queryRegisterForMediaEvents();
            }
        }).map(AccessoriesRequestHandler$queryRegisterForMediaEvents$queryRegisterForMediaEventsObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryRegisterForMediaEventsObservable, "queryRegisterForMediaEventsObservable");
        rxIPCServer.subscribe(rxIPCEventId, queryRegisterForMediaEventsObservable);
    }

    private final void queryRegistrations(RxIPCEventId rxIPCEventId) {
        ObservableSource queryRegistrationsObservable = this.registrationSupplier.queryRegistrations().map(AccessoriesRequestHandler$queryRegistrations$queryRegistrationsObservable$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryRegistrationsObservable, "queryRegistrationsObservable");
        rxIPCServer.subscribe(rxIPCEventId, queryRegistrationsObservable);
    }

    @SuppressLint({"NewApi"})
    private final void queryRemovedCompanionDevices(RxIPCEventId rxIPCEventId) {
        Preconditions.Companion.hasLock(this.lock);
        ObservableSource removedCompanionDeviceObservable = this.companionDeviceModule.queryRemovedCompanionDevices().map(AccessoriesRequestHandler$queryRemovedCompanionDevices$removedCompanionDeviceObservable$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(removedCompanionDeviceObservable, "removedCompanionDeviceObservable");
        rxIPCServer.subscribe(rxIPCEventId, removedCompanionDeviceObservable);
    }

    private final void queryState(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final StateRequest mo568fromBundle = StateRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable queryStateObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryState$queryStateObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<StateOuterClass.State> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getStateRepository().query(StateFeature.from(mo568fromBundle.getState().getFeature())).toObservable();
            }
        }).map(AccessoriesRequestHandler$queryState$queryStateObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryStateObservable, "queryStateObservable");
        rxIPCServer.subscribe(rxIPCEventId, queryStateObservable);
    }

    private final void queryUsers(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Observable queryUsersObservable = Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$queryUsers$queryUsersObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Observable<System.Users> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getSystemRepository().queryUsers();
            }
        }).map(AccessoriesRequestHandler$queryUsers$queryUsersObservable$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(queryUsersObservable, "queryUsersObservable");
        rxIPCServer.subscribe(rxIPCEventId, queryUsersObservable);
    }

    private final void releaseSession(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable releaseSessionCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$releaseSession$releaseSessionCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                sessionFromIdentifier.release();
                return Completable.complete();
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(releaseSessionCompletable, "releaseSessionCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, releaseSessionCompletable);
    }

    private final void removeCloudPairingKeys(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final CloudPairingSeedRequest mo568fromBundle = CloudPairingSeedRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable removeCloudPairingKeysCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$removeCloudPairingKeys$removeCloudPairingKeysCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.mo312getCloudPairingRepository().removeCloudPairingKeys(mo568fromBundle.getSeed());
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(removeCloudPairingKeysCompletable, "removeCloudPairingKeysCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, removeCloudPairingKeysCompletable);
    }

    @SuppressLint({"NewApi"})
    private final void removeCompanionDevice(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable removeCompanionDeviceCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$removeCompanionDevice$removeCompanionDeviceCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                CompanionDeviceModule companionDeviceModule;
                companionDeviceModule = AccessoriesRequestHandler.this.companionDeviceModule;
                companionDeviceModule.removeCompanionDevice(mo568fromBundle.getIdentifier());
                return Completable.complete();
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(removeCompanionDeviceCompletable, "removeCompanionDeviceCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, removeCompanionDeviceCompletable);
    }

    private final void removeDeviceGroup(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        Completable removeDeviceGroupCompletable = this.deviceSupplier.removeDeviceGroup(LongRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getLongValue());
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(removeDeviceGroupCompletable, "removeDeviceGroupCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, removeDeviceGroupCompletable);
    }

    private final void removeDeviceGroupByDeviceGroup(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        Completable removeDeviceGroupCompletable = this.deviceSupplier.removeDeviceGroup(DeviceGroupRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getDeviceGroup());
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(removeDeviceGroupCompletable, "removeDeviceGroupCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, removeDeviceGroupCompletable);
    }

    private final void removeOutgoingNotification(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionIntRequest mo568fromBundle = QuerySessionIntRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single removeOutgoingNotificationSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$removeOutgoingNotification$removeOutgoingNotificationSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Common.ErrorCode> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getNotificationRepository().removeOutgoingNotification(mo568fromBundle.getIntValue());
            }
        }).map(AccessoriesRequestHandler$removeOutgoingNotification$removeOutgoingNotificationSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(removeOutgoingNotificationSingle, "removeOutgoingNotificationSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, removeOutgoingNotificationSingle);
    }

    private final void replaceCloudPairingKeys(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final ReplaceCloudPairingKeysRequest mo568fromBundle = ReplaceCloudPairingKeysRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable replaceCloudPairingKeysCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$replaceCloudPairingKeys$replaceCloudPairingKeysCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.mo312getCloudPairingRepository().replaceCloudPairingKeys(mo568fromBundle.getSeed(), mo568fromBundle.getCloudPairingKeys());
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(replaceCloudPairingKeysCompletable, "replaceCloudPairingKeysCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, replaceCloudPairingKeysCompletable);
    }

    private final void requestCblInformation(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single requestCblInformationSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$requestCblInformation$requestCblInformationSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Cbl.CblInformation> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getCblRepository().requestCblInformation();
            }
        }).map(AccessoriesRequestHandler$requestCblInformation$requestCblInformationSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(requestCblInformationSingle, "requestCblInformationSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, requestCblInformationSingle);
    }

    @SuppressLint({"NewApi"})
    private final void requestCompanionDevice(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        SingleSource companionDeviceSingle = this.companionDeviceModule.requestCompanionDevice(QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getIdentifier()).map(AccessoriesRequestHandler$requestCompanionDevice$companionDeviceSingle$1.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(companionDeviceSingle, "companionDeviceSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, companionDeviceSingle);
    }

    private final void requestCompleteSetup(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionBooleanRequest mo568fromBundle = QuerySessionBooleanRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single requestCompleteSetupSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$requestCompleteSetup$requestCompleteSetupSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Common.ErrorCode> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getDeviceRepositoryV2().requestCompleteSetup(mo568fromBundle.getBooleanValue());
            }
        }).map(AccessoriesRequestHandler$requestCompleteSetup$requestCompleteSetupSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(requestCompleteSetupSingle, "requestCompleteSetupSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, requestCompleteSetupSingle);
    }

    private final void requestOverrideAssistant(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionBooleanRequest mo568fromBundle = QuerySessionBooleanRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single requestOverrideAssistantSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$requestOverrideAssistant$requestOverrideAssistantSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Common.ErrorCode> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getDeviceRepositoryV2().requestOverrideAssistant(mo568fromBundle.getBooleanValue());
            }
        }).map(AccessoriesRequestHandler$requestOverrideAssistant$requestOverrideAssistantSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(requestOverrideAssistantSingle, "requestOverrideAssistantSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, requestOverrideAssistantSingle);
    }

    private final void requestResetConnection(RxIPCEventId rxIPCEventId, Bundle bundle) {
        final QuerySessionBooleanIntRequest mo568fromBundle = QuerySessionBooleanIntRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single requestResetConnectionSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$requestResetConnection$requestResetConnectionSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Common.ErrorCode> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getSystemRepository().requestResetConnection(mo568fromBundle.getIntValue(), mo568fromBundle.getBooleanValue());
            }
        }).map(AccessoriesRequestHandler$requestResetConnection$requestResetConnectionSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(requestResetConnectionSingle, "requestResetConnectionSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, requestResetConnectionSingle);
    }

    private final void requestStartSetup(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single requestStartSetupSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$requestStartSetup$requestStartSetupSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Common.ErrorCode> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getDeviceRepositoryV2().requestStartSetup();
            }
        }).map(AccessoriesRequestHandler$requestStartSetup$requestStartSetupSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(requestStartSetupSingle, "requestStartSetupSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, requestStartSetupSingle);
    }

    private final void requestTransportUpgrade(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable requestTransportUpgradeCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$requestTransportUpgrade$requestTransportUpgradeCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getTransportRepository().requestUpgrade();
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(requestTransportUpgradeCompletable, "requestTransportUpgradeCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, requestTransportUpgradeCompletable);
    }

    private final void requestUpdateDeviceInformation(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionStringIntRequest mo568fromBundle = QuerySessionStringIntRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single requestUpdateDeviceInformationSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$requestUpdateDeviceInformation$requestUpdateDeviceInformationSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Common.ErrorCode> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getDeviceRepositoryV2().requestUpdateDeviceInformation(mo568fromBundle.getStringValue(), mo568fromBundle.getIntValue());
            }
        }).map(AccessoriesRequestHandler$requestUpdateDeviceInformation$requestUpdateDeviceInformationSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(requestUpdateDeviceInformationSingle, "requestUpdateDeviceInformationSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, requestUpdateDeviceInformationSingle);
    }

    private final void resetInputConfiguration(RxIPCEventId rxIPCEventId, Bundle bundle) {
        final QuerySessionIntRequest mo568fromBundle = QuerySessionIntRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable resetInputConfigurationCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$resetInputConfiguration$resetInputConfigurationCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getInputRepository().resetConfiguration(mo568fromBundle.getIntValue());
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(resetInputConfigurationCompletable, "resetInputConfigurationCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, resetInputConfigurationCompletable);
    }

    private final void setAudiogram(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final SetAudiogramRequest mo568fromBundle = SetAudiogramRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable setAudiogramCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$setAudiogram$setAudiogramCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getHearingEnhancementRepository().setAudiogram(mo568fromBundle.getAudiogram());
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(setAudiogramCompletable, "setAudiogramCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, setAudiogramCompletable);
    }

    private final void setCloudPairingKeys(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final SetCloudPairingKeysRequest mo568fromBundle = SetCloudPairingKeysRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable setCloudPairingKeysCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$setCloudPairingKeys$setCloudPairingKeysCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.mo312getCloudPairingRepository().setCloudPairingKeys(mo568fromBundle.getCloudPairingKeys());
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(setCloudPairingKeysCompletable, "setCloudPairingKeysCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, setCloudPairingKeysCompletable);
    }

    private final void setFitnessSession(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final SetFitnessSessionRequest mo568fromBundle = SetFitnessSessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable setFitnessSessionCompletabe = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$setFitnessSession$setFitnessSessionCompletabe$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getFitnessRepository().setFitnessSession(mo568fromBundle.getFitnessSession());
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(setFitnessSessionCompletabe, "setFitnessSessionCompletabe");
        rxIPCServer.subscribeCompletable(rxIPCEventId, setFitnessSessionCompletabe);
    }

    private final void setInputConfiguration(RxIPCEventId rxIPCEventId, Bundle bundle) {
        final SetInputConfigurationRequest mo568fromBundle = SetInputConfigurationRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable setInputConfigurationCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$setInputConfiguration$setInputConfigurationCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getInputRepository().setConfiguration(mo568fromBundle.getDeviceId(), mo568fromBundle.getInputBehaviorConfiguration());
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(setInputConfigurationCompletable, "setInputConfigurationCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, setInputConfigurationCompletable);
    }

    private final void setLocale(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionLocaleRequest mo568fromBundle = QuerySessionLocaleRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single setLocaleSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$setLocale$setLocaleSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Common.ErrorCode> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getSystemRepository().setLocale(mo568fromBundle.getLocale());
            }
        }).map(AccessoriesRequestHandler$setLocale$setLocaleSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(setLocaleSingle, "setLocaleSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, setLocaleSingle);
    }

    private final void setMediaEnhancementCorrectionAmount(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final SetMediaEnhancementCorrectionAmountRequest mo568fromBundle = SetMediaEnhancementCorrectionAmountRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable setMediaEnhancementCorrectionAmountCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$setMediaEnhancementCorrectionAmount$setMediaEnhancementCorrectionAmountCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getHearingEnhancementRepository().setMediaEnhancementCorrectionAmount(mo568fromBundle.getCorrectionAmount());
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(setMediaEnhancementCorrectionAmountCompletable, "setMediaEnhancementCorrectionAmountCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, setMediaEnhancementCorrectionAmountCompletable);
    }

    private final void setPlaybackStatus(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final SetPlaybackStatusRequest mo568fromBundle = SetPlaybackStatusRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable setPlaybackStatusObservable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$setPlaybackStatus$setPlaybackStatusObservable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getMediaRepository().setPlaybackStatus(mo568fromBundle.getPlaybackStatus());
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(setPlaybackStatusObservable, "setPlaybackStatusObservable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, setPlaybackStatusObservable);
    }

    private final void setState(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final StateRequest mo568fromBundle = StateRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable setStateCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$setState$setStateCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getStateRepository().set(mo568fromBundle.getState());
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(setStateCompletable, "setStateCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, setStateCompletable);
    }

    private final void shouldUpgradeTransport(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionRequest mo568fromBundle = QuerySessionRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single shouldUpgradeTransportSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$shouldUpgradeTransport$shouldUpgradeTransportSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Boolean> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getTransportRepository().shouldUpgrade();
            }
        }).map(AccessoriesRequestHandler$shouldUpgradeTransport$shouldUpgradeTransportSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(shouldUpgradeTransportSingle, "shouldUpgradeTransportSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, shouldUpgradeTransportSingle);
    }

    private final void startLiveFitnessData(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionIntListRequest mo568fromBundle = QuerySessionIntListRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable startLiveFitnessDataCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$startLiveFitnessData$startLiveFitnessDataCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getFitnessRepository().startLiveFitnessData(mo568fromBundle.getList());
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(startLiveFitnessDataCompletable, "startLiveFitnessDataCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, startLiveFitnessDataCompletable);
    }

    private final void stopLiveFitnessData(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionIntListRequest mo568fromBundle = QuerySessionIntListRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable stopLiveFitnessDataCompletable = Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$stopLiveFitnessData$stopLiveFitnessDataCompletable$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final CompletableSource mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getFitnessRepository().stopLiveFitnessData(mo568fromBundle.getList());
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(stopLiveFitnessDataCompletable, "stopLiveFitnessDataCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, stopLiveFitnessDataCompletable);
    }

    private final void unlinkAccessory(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final AccessoryRequest mo568fromBundle = AccessoryRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Completable unlinkAccessoryCompletable = Completable.create(new CompletableOnSubscribe() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$unlinkAccessory$unlinkAccessoryCompletable$1
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(final CompletableEmitter completableEmitter) {
                AccessorySupplier accessorySupplier;
                accessorySupplier = AccessoriesRequestHandler.this.accessorySupplier;
                accessorySupplier.unlink(mo568fromBundle.getAccessory(), new Accessories.SimpleAccessoryLinkListener() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$unlinkAccessory$unlinkAccessoryCompletable$1.1
                    @Override // com.amazon.alexa.accessory.Accessories.SimpleAccessoryLinkListener, com.amazon.alexa.accessory.AccessoryLinkListener
                    public void onAccessoryUnlinkFailed(@NotNull Accessory accessory, @NotNull Throwable e) {
                        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
                        Intrinsics.checkParameterIsNotNull(e, "e");
                        CompletableEmitter.this.onError(e);
                    }

                    @Override // com.amazon.alexa.accessory.Accessories.SimpleAccessoryLinkListener, com.amazon.alexa.accessory.AccessoryLinkListener
                    public void onAccessoryUnlinked(@NotNull Accessory accessory) {
                        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
                        CompletableEmitter.this.onComplete();
                    }
                });
            }
        });
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(unlinkAccessoryCompletable, "unlinkAccessoryCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, unlinkAccessoryCompletable);
    }

    private final void unpairUser(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final QuerySessionStringRequest mo568fromBundle = QuerySessionStringRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single unpairUserSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$unpairUser$unpairUserSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Common.ErrorCode> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getSystemRepository().unpairUser(mo568fromBundle.getStringValue());
            }
        }).map(AccessoriesRequestHandler$unpairUser$unpairUserSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(unpairUserSingle, "unpairUserSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, unpairUserSingle);
    }

    private final void updateCallInfo(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final CallInfoRequest mo568fromBundle = CallInfoRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single updateCallStateSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$updateCallInfo$updateCallStateSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Common.ErrorCode> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIdentifier());
                return sessionFromIdentifier.getNonHfpCallingRepository().updateCallInfo(mo568fromBundle.getCallInfo());
            }
        }).map(AccessoriesRequestHandler$updateCallInfo$updateCallStateSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(updateCallStateSingle, "updateCallStateSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, updateCallStateSingle);
    }

    private final void updateDeviceGroup(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        Completable updateDeviceGroupCompletable = this.deviceSupplier.updateDeviceGroup(DeviceGroupRequest.Transformer.INSTANCE.mo568fromBundle(bundle).getDeviceGroup());
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(updateDeviceGroupCompletable, "updateDeviceGroupCompletable");
        rxIPCServer.subscribeCompletable(rxIPCEventId, updateDeviceGroupCompletable);
    }

    private final void updateOutgoingNotification(RxIPCEventId rxIPCEventId, Bundle bundle) {
        Preconditions.Companion.hasLock(this.lock);
        final NotificationWithContentRequest mo568fromBundle = NotificationWithContentRequest.Transformer.INSTANCE.mo568fromBundle(bundle);
        Single updateOutgoingNotificationSingle = Single.defer(new Supplier<SingleSource<? extends T>>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler$updateOutgoingNotification$updateOutgoingNotificationSingle$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Single<Common.ErrorCode> mo10365get() {
                AccessorySession sessionFromIdentifier;
                sessionFromIdentifier = AccessoriesRequestHandler.this.getSessionFromIdentifier(mo568fromBundle.getIpcCallIdentifier());
                return sessionFromIdentifier.getNotificationRepository().updateOutgoingNotification(mo568fromBundle.getNotificationId(), mo568fromBundle.getNotificationContent());
            }
        }).map(AccessoriesRequestHandler$updateOutgoingNotification$updateOutgoingNotificationSingle$2.INSTANCE);
        RxIPCServer rxIPCServer = this.rxIPCServer;
        Intrinsics.checkExpressionValueIsNotNull(updateOutgoingNotificationSingle, "updateOutgoingNotificationSingle");
        rxIPCServer.subscribeSingle(rxIPCEventId, updateOutgoingNotificationSingle);
    }

    @Override // com.amazon.alexa.accessoryclient.common.connection.BundleSink
    public void dispose(@NotNull Exception e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        synchronized (this.lock) {
            Logger.d("AccessoriesRequestHandler:  dispose called", e);
            if (this.released) {
                return;
            }
            this.released = true;
            this.rxIPCServer.release();
            this.fitnessUpdateCache.clear();
            this.sessionSupplier.removeSessionListener(this.fitnessSessionListener);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.accessoryclient.common.connection.BundleSink
    public void handleBundle(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        com.amazon.alexa.accessory.internal.util.Preconditions.mainThread();
        synchronized (this.lock) {
            if (this.released) {
                return;
            }
            com.amazon.alexa.accessoryclient.common.util.Logger.INSTANCE.v(new AccessoriesRequestHandler$handleBundle$$inlined$synchronized$lambda$1(this, bundle));
            RxIPCEvent fromBundle = RxIPCEvent.Companion.fromBundle(bundle);
            int i = WhenMappings.$EnumSwitchMapping$0[fromBundle.getRxIPCEventId().getAction().ordinal()];
            if (i == 1) {
                ApiIdentifier.ApiRequest from = ApiIdentifier.ApiRequest.Companion.from(fromBundle.getData());
                handleRequest(from.getApiIdentifier(), fromBundle.getRxIPCEventId(), from.getRequestBundle());
            } else if (i != 2) {
                com.amazon.alexa.accessoryclient.common.util.Logger.INSTANCE.e(new AccessoriesRequestHandler$handleBundle$$inlined$synchronized$lambda$2(fromBundle, this, bundle));
            } else {
                this.rxIPCServer.dispose(fromBundle.getRxIPCEventId());
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.accessoryclient.common.connection.BundleSource
    public void setBundleSink(@NotNull BundleSink bundleSink) {
        Intrinsics.checkParameterIsNotNull(bundleSink, "bundleSink");
        synchronized (this.lock) {
            this.rxIPCServer.setBundleSink(bundleSink);
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AccessoriesRequestHandler(@org.jetbrains.annotations.NotNull com.amazon.alexa.accessory.Accessories r12) {
        /*
            r11 = this;
            java.lang.String r0 = "accessories"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            com.amazon.alexa.accessory.AccessorySession$Factory r2 = r12.getSessionFactory()
            java.lang.String r0 = "accessories.sessionFactory"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r0)
            com.amazon.alexa.accessory.AccessorySupplier r3 = r12.getAccessorySupplier()
            java.lang.String r0 = "accessories.accessorySupplier"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r0)
            com.amazon.alexa.accessory.SessionSupplier r4 = r12.getSessionSupplier()
            java.lang.String r0 = "accessories.sessionSupplier"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r0)
            com.amazon.alexa.accessory.repositories.device.DeviceSupplier r5 = r12.getDeviceSupplier()
            java.lang.String r0 = "accessories.deviceSupplier"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)
            com.amazon.alexa.accessory.AccessoryScanner r6 = r12.getScanner()
            java.lang.String r0 = "accessories.scanner"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r0)
            com.amazon.alexa.accessory.registration.RegistrationSupplier r7 = r12.getRegistrationSupplier()
            java.lang.String r0 = "accessories.registrationSupplier"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r0)
            com.amazon.alexa.accessory.internal.bluetooth.CompanionDeviceModule r8 = r12.getCompanionDeviceModule()
            java.lang.String r0 = "accessories.companionDeviceModule"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r0)
            com.amazon.alexa.accessory.kota.KotaDownloader r9 = r12.getKotaDownloader()
            java.lang.String r0 = "accessories.kotaDownloader"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r0)
            com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier r10 = r12.getDeviceAccountSupplier()
            java.lang.String r12 = "accessories.deviceAccountSupplier"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r12)
            r1 = r11
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler.<init>(com.amazon.alexa.accessory.Accessories):void");
    }
}
