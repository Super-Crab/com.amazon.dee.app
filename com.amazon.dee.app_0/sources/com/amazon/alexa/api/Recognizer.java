package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public class Recognizer {
    private static final String DEFAULT_INVOCATION_TYPE = "HandsFree.DSP";
    private static final String TAG = "Recognizer";

    private Recognizer() {
        throw new UnsupportedOperationException();
    }

    private static Bundle attachInvocationType(@Nullable AlexaDialogExtras alexaDialogExtras) {
        String str;
        AlexaDialogExtras.Builder builder = AlexaDialogExtras.builder();
        if (alexaDialogExtras != null) {
            str = alexaDialogExtras.getInvocationType();
            builder.setUserVoiceVerified(alexaDialogExtras.isUserVoiceVerified());
            builder.suppressEndpointSound(alexaDialogExtras.suppressEndpointSound());
            builder.suppressEndpointSoundOnlyOnFirstTurn(alexaDialogExtras.suppressEndpointSoundOnlyOnFirstTurn());
            builder.suppressUserInterface(alexaDialogExtras.suppressUserInterface());
            builder.suppressWakeSound(alexaDialogExtras.suppressWakeSound());
            builder.suppressWakeSoundOnlyOnFirstTurn(alexaDialogExtras.suppressWakeSoundOnlyOnFirstTurn());
            builder.setAudioOutputContext(alexaDialogExtras.getAudioOutputContext());
        } else {
            str = null;
        }
        if (str == null) {
            str = DEFAULT_INVOCATION_TYPE;
        }
        builder.setInvocationType(str);
        return builder.build().getBundle();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void continueDialog(@com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioProviderConnection r5, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaDialogController r6, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioMetadata r7, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioSink r8) {
        /*
            java.lang.String r0 = "alexaAudioProviderConnection was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r5, r0)
            java.lang.String r0 = "alexaDialogController was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r6, r0)
            java.lang.String r0 = "alexaAudioMetadata was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r7, r0)
            java.lang.String r0 = "alexaAudioSink was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r8, r0)
            r0 = 1
            boolean r1 = r5.isConnected()     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            r2 = 0
            if (r1 == 0) goto L61
            com.amazon.alexa.api.AlexaDialogControllerProxy r1 = r5.getProxy(r6)     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            if (r1 == 0) goto L59
            boolean r3 = com.amazon.alexa.api.h.a(r7)     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            if (r3 == 0) goto L51
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            r3.<init>()     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            java.lang.String r4 = "continueDialog: "
            r3.append(r4)     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            java.lang.String r4 = r1.getDialogIdentifier()     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            r3.append(r4)     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            r3.toString()     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            com.amazon.alexa.api.ExtendedClient r3 = r5.getClient()     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            com.amazon.alexa.api.Releasable r5 = r5.mo838get()     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            com.amazon.alexa.api.AlexaAudioProviderManagerMessageSender r5 = (com.amazon.alexa.api.AlexaAudioProviderManagerMessageSender) r5     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            if (r5 == 0) goto L66
            android.os.ParcelFileDescriptor r2 = r8.getReadDescriptor()     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            r5.continueDialog(r3, r1, r7, r2)     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            r2 = r0
            goto L66
        L51:
            java.lang.String r5 = com.amazon.alexa.api.Recognizer.TAG     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            java.lang.String r7 = "Invalid Alexa Audio Metadata"
        L55:
            android.util.Log.e(r5, r7)     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            goto L66
        L59:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            java.lang.String r7 = "The provided AlexaDialogController was not the same as was used for the startDialog of this Dialog."
            r5.<init>(r7)     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            throw r5     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
        L61:
            java.lang.String r5 = com.amazon.alexa.api.Recognizer.TAG     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            java.lang.String r7 = "Connection object is not connected"
            goto L55
        L66:
            if (r2 != 0) goto L79
            goto L73
        L69:
            r5 = move-exception
            goto L7a
        L6b:
            r5 = move-exception
            java.lang.String r7 = com.amazon.alexa.api.Recognizer.TAG     // Catch: java.lang.Throwable -> L69
            java.lang.String r1 = "Unable to communicate with the service"
            android.util.Log.e(r7, r1, r5)     // Catch: java.lang.Throwable -> L69
        L73:
            r8.abandon()
            dropDialog(r6, r0)
        L79:
            return
        L7a:
            r8.abandon()
            dropDialog(r6, r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.api.Recognizer.continueDialog(com.amazon.alexa.api.AlexaAudioProviderConnection, com.amazon.alexa.api.AlexaDialogController, com.amazon.alexa.api.AlexaAudioMetadata, com.amazon.alexa.api.AlexaAudioSink):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void continueDialog(@com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioProviderConnection r5, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaDialogControllerV2 r6, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioMetadata r7, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioSink r8) {
        /*
            java.lang.String r0 = "alexaAudioProviderConnection was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r5, r0)
            java.lang.String r0 = "alexaDialogController was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r6, r0)
            java.lang.String r0 = "alexaAudioMetadata was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r7, r0)
            java.lang.String r0 = "alexaAudioSink was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r8, r0)
            r0 = 1
            boolean r1 = r5.isConnected()     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            r2 = 0
            if (r1 == 0) goto L61
            com.amazon.alexa.api.AlexaDialogControllerProxyV2 r1 = r5.getProxy(r6)     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            if (r1 == 0) goto L59
            boolean r3 = com.amazon.alexa.api.h.a(r7)     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            if (r3 == 0) goto L51
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            r3.<init>()     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            java.lang.String r4 = "continueDialog: "
            r3.append(r4)     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            java.lang.String r4 = r1.getDialogIdentifier()     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            r3.append(r4)     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            r3.toString()     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            com.amazon.alexa.api.ExtendedClient r3 = r5.getClient()     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            com.amazon.alexa.api.Releasable r5 = r5.mo838get()     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            com.amazon.alexa.api.AlexaAudioProviderManagerMessageSender r5 = (com.amazon.alexa.api.AlexaAudioProviderManagerMessageSender) r5     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            if (r5 == 0) goto L66
            android.os.ParcelFileDescriptor r2 = r8.getReadDescriptor()     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            r5.continueDialog(r3, r1, r7, r2)     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            r2 = r0
            goto L66
        L51:
            java.lang.String r5 = com.amazon.alexa.api.Recognizer.TAG     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            java.lang.String r7 = "Invalid Alexa Audio Metadata"
        L55:
            android.util.Log.e(r5, r7)     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            goto L66
        L59:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            java.lang.String r7 = "The provided AlexaDialogController was not the same as was used for the startDialog of this Dialog."
            r5.<init>(r7)     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            throw r5     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
        L61:
            java.lang.String r5 = com.amazon.alexa.api.Recognizer.TAG     // Catch: java.lang.Throwable -> L69 android.os.RemoteException -> L6b
            java.lang.String r7 = "Connection object is not connected"
            goto L55
        L66:
            if (r2 != 0) goto L79
            goto L73
        L69:
            r5 = move-exception
            goto L7a
        L6b:
            r5 = move-exception
            java.lang.String r7 = com.amazon.alexa.api.Recognizer.TAG     // Catch: java.lang.Throwable -> L69
            java.lang.String r1 = "Unable to communicate with the service"
            android.util.Log.e(r7, r1, r5)     // Catch: java.lang.Throwable -> L69
        L73:
            r8.abandon()
            dropDialog(r6, r0)
        L79:
            return
        L7a:
            r8.abandon()
            dropDialog(r6, r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.api.Recognizer.continueDialog(com.amazon.alexa.api.AlexaAudioProviderConnection, com.amazon.alexa.api.AlexaDialogControllerV2, com.amazon.alexa.api.AlexaAudioMetadata, com.amazon.alexa.api.AlexaAudioSink):void");
    }

    private static void dropDialog(@Nullable AlexaDialogController alexaDialogController, boolean z) {
        Log.w(TAG, "Dropping dialog");
        if (alexaDialogController != null) {
            if (z) {
                alexaDialogController.onDialogStarted();
            }
            alexaDialogController.stopRecording();
            alexaDialogController.onDialogFinished();
        }
    }

    private static void dropDialog(@Nullable AlexaDialogControllerV2 alexaDialogControllerV2, boolean z) {
        Log.w(TAG, "Dropping dialog");
        if (alexaDialogControllerV2 != null) {
            if (z) {
                alexaDialogControllerV2.onDialogStarted();
            }
            alexaDialogControllerV2.stopRecording();
            alexaDialogControllerV2.onDialogFinished();
        }
    }

    public static void startDialog(@NonNull AlexaAudioProviderConnection alexaAudioProviderConnection, @NonNull AlexaDialogController alexaDialogController, @NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink) {
        startDialog(alexaAudioProviderConnection, alexaDialogController, alexaAudioMetadata, alexaAudioSink, (AlexaDialogExtras) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.Object, com.amazon.alexa.api.AlexaAudioMetadata] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void startDialog(@com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioProviderConnection r8, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaDialogController r9, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioMetadata r10, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioSink r11, @com.amazon.alexa.client.annotations.Nullable com.amazon.alexa.api.AlexaDialogExtras r12) {
        /*
            java.lang.String r0 = "alexaAudioProviderConnection was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r8, r0)
            java.lang.String r0 = "alexaDialogController was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r9, r0)
            java.lang.String r0 = "alexaAudioMetadata was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r10, r0)
            java.lang.String r0 = "alexaAudioSink was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r11, r0)
            r0 = 0
            boolean r1 = r8.isConnected()     // Catch: java.lang.Throwable -> L77
            if (r1 == 0) goto L66
            com.amazon.alexa.api.ak r1 = new com.amazon.alexa.api.ak     // Catch: java.lang.Throwable -> L77
            r1.<init>(r9, r8)     // Catch: java.lang.Throwable -> L77
            boolean r2 = com.amazon.alexa.api.h.a(r10)     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L77
            if (r2 == 0) goto L6d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L77
            r2.<init>()     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L77
            java.lang.String r3 = "startDialog: "
            r2.append(r3)     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L77
            java.lang.String r3 = r1.getDialogIdentifier()     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L77
            r2.append(r3)     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L77
            r2.toString()     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L77
            com.amazon.alexa.api.ExtendedClient r3 = r8.getClient()     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L77
            com.amazon.alexa.api.Releasable r2 = r8.mo838get()     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L77
            com.amazon.alexa.api.AlexaAudioProviderManagerMessageSender r2 = (com.amazon.alexa.api.AlexaAudioProviderManagerMessageSender) r2     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L77
            if (r2 == 0) goto L6d
            android.os.ParcelFileDescriptor r6 = r11.getReadDescriptor()     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L77
            android.os.Bundle r7 = attachInvocationType(r12)     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L77
            r4 = r1
            r5 = r10
            r2.startDialog(r3, r4, r5, r6, r7)     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L77
            r10 = 1
            r8.addProxy(r9, r1)     // Catch: android.os.RemoteException -> L58 java.lang.Throwable -> L64
            goto L6e
        L58:
            r8 = move-exception
            goto L5c
        L5a:
            r8 = move-exception
            r10 = r0
        L5c:
            java.lang.String r12 = com.amazon.alexa.api.Recognizer.TAG     // Catch: java.lang.Throwable -> L64
            java.lang.String r1 = "Unable to communicate with the service"
            android.util.Log.e(r12, r1, r8)     // Catch: java.lang.Throwable -> L64
            goto L6e
        L64:
            r8 = move-exception
            goto L79
        L66:
            java.lang.String r8 = com.amazon.alexa.api.Recognizer.TAG     // Catch: java.lang.Throwable -> L77
            java.lang.String r10 = "Connection object is not connected"
            android.util.Log.e(r8, r10)     // Catch: java.lang.Throwable -> L77
        L6d:
            r10 = r0
        L6e:
            if (r10 != 0) goto L76
            r11.abandon()
            dropDialog(r9, r0)
        L76:
            return
        L77:
            r8 = move-exception
            r10 = r0
        L79:
            if (r10 != 0) goto L81
            r11.abandon()
            dropDialog(r9, r0)
        L81:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.api.Recognizer.startDialog(com.amazon.alexa.api.AlexaAudioProviderConnection, com.amazon.alexa.api.AlexaDialogController, com.amazon.alexa.api.AlexaAudioMetadata, com.amazon.alexa.api.AlexaAudioSink, com.amazon.alexa.api.AlexaDialogExtras):void");
    }

    public static void startDialog(@NonNull AlexaAudioProviderConnection alexaAudioProviderConnection, @NonNull AlexaDialogControllerV2 alexaDialogControllerV2, @NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink) {
        startDialog(alexaAudioProviderConnection, alexaDialogControllerV2, alexaAudioMetadata, alexaAudioSink, (AlexaDialogExtras) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void startDialog(@com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioProviderConnection r8, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaDialogControllerV2 r9, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioMetadata r10, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioSink r11, @com.amazon.alexa.client.annotations.Nullable com.amazon.alexa.api.AlexaDialogExtras r12) {
        /*
            java.lang.String r0 = "alexaAudioProviderConnection was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r8, r0)
            java.lang.String r0 = "alexaDialogController was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r9, r0)
            java.lang.String r0 = "alexaAudioMetadata was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r10, r0)
            java.lang.String r0 = "alexaAudioSink was null"
            com.amazon.alexa.utils.validation.Preconditions.notNull(r11, r0)
            r0 = 0
            boolean r1 = r8.isConnected()     // Catch: java.lang.Throwable -> L7c
            if (r1 == 0) goto L6b
            com.amazon.alexa.api.al r1 = new com.amazon.alexa.api.al     // Catch: java.lang.Throwable -> L7c
            r1.<init>(r9, r8)     // Catch: java.lang.Throwable -> L7c
            boolean r2 = com.amazon.alexa.api.h.a(r10)     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L7c
            if (r2 == 0) goto L72
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L7c
            r2.<init>()     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L7c
            java.lang.String r3 = "startDialog: "
            r2.append(r3)     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L7c
            java.lang.String r3 = r1.getDialogIdentifier()     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L7c
            r2.append(r3)     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L7c
            r2.toString()     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L7c
            com.amazon.alexa.api.ExtendedClient r3 = r8.getClient()     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L7c
            com.amazon.alexa.api.Releasable r2 = r8.mo838get()     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L7c
            com.amazon.alexa.api.AlexaAudioProviderManagerMessageSender r2 = (com.amazon.alexa.api.AlexaAudioProviderManagerMessageSender) r2     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L7c
            if (r2 == 0) goto L72
            android.os.ParcelFileDescriptor r6 = r11.getReadDescriptor()     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L7c
            if (r12 != 0) goto L4e
            r12 = 0
            goto L52
        L4e:
            android.os.Bundle r12 = r12.getBundle()     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L7c
        L52:
            r7 = r12
            r4 = r1
            r5 = r10
            r2.startDialog(r3, r4, r5, r6, r7)     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L7c
            r10 = 1
            r8.addProxy(r9, r1)     // Catch: android.os.RemoteException -> L5d java.lang.Throwable -> L69
            goto L73
        L5d:
            r8 = move-exception
            goto L61
        L5f:
            r8 = move-exception
            r10 = r0
        L61:
            java.lang.String r12 = com.amazon.alexa.api.Recognizer.TAG     // Catch: java.lang.Throwable -> L69
            java.lang.String r1 = "Unable to communicate with the service"
            android.util.Log.e(r12, r1, r8)     // Catch: java.lang.Throwable -> L69
            goto L73
        L69:
            r8 = move-exception
            goto L7e
        L6b:
            java.lang.String r8 = com.amazon.alexa.api.Recognizer.TAG     // Catch: java.lang.Throwable -> L7c
            java.lang.String r10 = "Connection object is not connected"
            android.util.Log.e(r8, r10)     // Catch: java.lang.Throwable -> L7c
        L72:
            r10 = r0
        L73:
            if (r10 != 0) goto L7b
            r11.abandon()
            dropDialog(r9, r0)
        L7b:
            return
        L7c:
            r8 = move-exception
            r10 = r0
        L7e:
            if (r10 != 0) goto L86
            r11.abandon()
            dropDialog(r9, r0)
        L86:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.api.Recognizer.startDialog(com.amazon.alexa.api.AlexaAudioProviderConnection, com.amazon.alexa.api.AlexaDialogControllerV2, com.amazon.alexa.api.AlexaAudioMetadata, com.amazon.alexa.api.AlexaAudioSink, com.amazon.alexa.api.AlexaDialogExtras):void");
    }

    public static void stopDialogTurn(@NonNull AlexaAudioProviderConnection alexaAudioProviderConnection, @NonNull AlexaDialogController alexaDialogController) {
        Preconditions.notNull(alexaAudioProviderConnection, "alexaAudioProviderConnection was null");
        Preconditions.notNull(alexaDialogController, "alexaDialogController was null");
        try {
            try {
                boolean z = false;
                if (alexaAudioProviderConnection.isConnected()) {
                    AlexaDialogControllerProxy proxy = alexaAudioProviderConnection.getProxy(alexaDialogController);
                    if (proxy != null) {
                        String str = "stopDialogTurn: " + proxy.getDialogIdentifier();
                        ExtendedClient client = alexaAudioProviderConnection.getClient();
                        AlexaAudioProviderManagerMessageSender alexaAudioProviderManagerMessageSender = (AlexaAudioProviderManagerMessageSender) alexaAudioProviderConnection.mo838get();
                        if (alexaAudioProviderManagerMessageSender != null) {
                            alexaAudioProviderManagerMessageSender.stopDialogTurn(client, proxy);
                            z = true;
                        }
                    } else {
                        Log.w(TAG, "The provided AlexaDialogController was not the same as was used for the startDialog of this Dialog.");
                    }
                } else {
                    Log.e(TAG, "Connection object is not connected");
                }
                if (z) {
                }
            } catch (RemoteException e) {
                Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        } finally {
            dropDialog(alexaDialogController, true);
        }
    }

    public static void stopDialogTurn(@NonNull AlexaAudioProviderConnection alexaAudioProviderConnection, @NonNull AlexaDialogControllerV2 alexaDialogControllerV2) {
        Preconditions.notNull(alexaAudioProviderConnection, "alexaAudioProviderConnection was null");
        Preconditions.notNull(alexaDialogControllerV2, "alexaDialogController was null");
        try {
            try {
                boolean z = false;
                if (alexaAudioProviderConnection.isConnected()) {
                    AlexaDialogControllerProxyV2 proxy = alexaAudioProviderConnection.getProxy(alexaDialogControllerV2);
                    if (proxy != null) {
                        String str = "stopDialogTurn: " + proxy.getDialogIdentifier();
                        ExtendedClient client = alexaAudioProviderConnection.getClient();
                        AlexaAudioProviderManagerMessageSender alexaAudioProviderManagerMessageSender = (AlexaAudioProviderManagerMessageSender) alexaAudioProviderConnection.mo838get();
                        if (alexaAudioProviderManagerMessageSender != null) {
                            alexaAudioProviderManagerMessageSender.stopDialogTurn(client, proxy);
                            z = true;
                        }
                    } else {
                        Log.w(TAG, "The provided AlexaDialogController was not the same as was used for the startDialog of this Dialog.");
                    }
                } else {
                    Log.e(TAG, "Connection object is not connected");
                }
                if (z) {
                }
            } catch (RemoteException e) {
                Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        } finally {
            dropDialog(alexaDialogControllerV2, true);
        }
    }
}
