package com.amazon.alexa.voice.tta.statemachine;

import com.amazon.alexa.api.compat.TextResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SearchWorkflow.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/Inputs;", "", "()V", "TextRequestInput", "TextResponseInput", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public abstract class Inputs {

    /* compiled from: SearchWorkflow.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/Inputs$TextRequestInput;", "", "requestText", "", "(Ljava/lang/String;)V", "getRequestText", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class TextRequestInput {
        @NotNull
        private final String requestText;

        public TextRequestInput(@NotNull String requestText) {
            Intrinsics.checkParameterIsNotNull(requestText, "requestText");
            this.requestText = requestText;
        }

        public static /* synthetic */ TextRequestInput copy$default(TextRequestInput textRequestInput, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = textRequestInput.requestText;
            }
            return textRequestInput.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.requestText;
        }

        @NotNull
        public final TextRequestInput copy(@NotNull String requestText) {
            Intrinsics.checkParameterIsNotNull(requestText, "requestText");
            return new TextRequestInput(requestText);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof TextRequestInput) && Intrinsics.areEqual(this.requestText, ((TextRequestInput) obj).requestText);
            }
            return true;
        }

        @NotNull
        public final String getRequestText() {
            return this.requestText;
        }

        public int hashCode() {
            String str = this.requestText;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        @NotNull
        public String toString() {
            return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("TextRequestInput(requestText="), this.requestText, ")");
        }
    }

    /* compiled from: SearchWorkflow.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/Inputs$TextResponseInput;", "", "response", "Lcom/amazon/alexa/api/compat/TextResponse;", "(Lcom/amazon/alexa/api/compat/TextResponse;)V", "getResponse", "()Lcom/amazon/alexa/api/compat/TextResponse;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class TextResponseInput {
        @NotNull
        private final TextResponse response;

        public TextResponseInput(@NotNull TextResponse response) {
            Intrinsics.checkParameterIsNotNull(response, "response");
            this.response = response;
        }

        public static /* synthetic */ TextResponseInput copy$default(TextResponseInput textResponseInput, TextResponse textResponse, int i, Object obj) {
            if ((i & 1) != 0) {
                textResponse = textResponseInput.response;
            }
            return textResponseInput.copy(textResponse);
        }

        @NotNull
        public final TextResponse component1() {
            return this.response;
        }

        @NotNull
        public final TextResponseInput copy(@NotNull TextResponse response) {
            Intrinsics.checkParameterIsNotNull(response, "response");
            return new TextResponseInput(response);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof TextResponseInput) && Intrinsics.areEqual(this.response, ((TextResponseInput) obj).response);
            }
            return true;
        }

        @NotNull
        public final TextResponse getResponse() {
            return this.response;
        }

        public int hashCode() {
            TextResponse textResponse = this.response;
            if (textResponse != null) {
                return textResponse.hashCode();
            }
            return 0;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TextResponseInput(response=");
            outline107.append(this.response);
            outline107.append(")");
            return outline107.toString();
        }
    }

    private Inputs() {
    }
}
