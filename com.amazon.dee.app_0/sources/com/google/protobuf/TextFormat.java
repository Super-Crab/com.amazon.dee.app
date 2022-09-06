package com.google.protobuf;

import com.adobe.xmp.XMPConst;
import com.amazon.alexa.accessory.internal.bluetooth.GenericAccessProfile;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Message;
import com.google.protobuf.MessageReflection;
import com.google.protobuf.TextFormatParseInfoTree;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes3.dex */
public final class TextFormat {
    private static final Logger logger = Logger.getLogger(TextFormat.class.getName());
    private static final Parser PARSER = Parser.newBuilder().build();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.protobuf.TextFormat$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = new int[Descriptors.FieldDescriptor.Type.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.INT32.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED32.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.INT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT64.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.BOOL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.DOUBLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.STRING.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.GROUP.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class InvalidEscapeSequenceException extends IOException {
        private static final long serialVersionUID = -8164033650142593304L;

        InvalidEscapeSequenceException(String str) {
            super(str);
        }
    }

    /* loaded from: classes3.dex */
    public static class ParseException extends IOException {
        private static final long serialVersionUID = 3196188060225107702L;
        private final int column;
        private final int line;

        public ParseException(int i, int i2, String str) {
            super(Integer.toString(i) + ":" + i2 + RealTimeTextConstants.COLON_SPACE + str);
            this.line = i;
            this.column = i2;
        }

        public ParseException(String str) {
            this(-1, -1, str);
        }

        public int getColumn() {
            return this.column;
        }

        public int getLine() {
            return this.line;
        }
    }

    /* loaded from: classes3.dex */
    public static class Parser {
        private static final int BUFFER_SIZE = 4096;
        private final boolean allowUnknownFields;
        private TextFormatParseInfoTree.Builder parseInfoTreeBuilder;
        private final SingularOverwritePolicy singularOverwritePolicy;

        /* loaded from: classes3.dex */
        public static class Builder {
            private boolean allowUnknownFields = false;
            private SingularOverwritePolicy singularOverwritePolicy = SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;
            private TextFormatParseInfoTree.Builder parseInfoTreeBuilder = null;

            public Parser build() {
                return new Parser(this.allowUnknownFields, this.singularOverwritePolicy, this.parseInfoTreeBuilder, null);
            }

            public Builder setParseInfoTreeBuilder(TextFormatParseInfoTree.Builder builder) {
                this.parseInfoTreeBuilder = builder;
                return this;
            }

            public Builder setSingularOverwritePolicy(SingularOverwritePolicy singularOverwritePolicy) {
                this.singularOverwritePolicy = singularOverwritePolicy;
                return this;
            }
        }

        /* loaded from: classes3.dex */
        public enum SingularOverwritePolicy {
            ALLOW_SINGULAR_OVERWRITES,
            FORBID_SINGULAR_OVERWRITES
        }

        private Parser(boolean z, SingularOverwritePolicy singularOverwritePolicy, TextFormatParseInfoTree.Builder builder) {
            this.allowUnknownFields = z;
            this.singularOverwritePolicy = singularOverwritePolicy;
            this.parseInfoTreeBuilder = builder;
        }

        /* synthetic */ Parser(boolean z, SingularOverwritePolicy singularOverwritePolicy, TextFormatParseInfoTree.Builder builder, AnonymousClass1 anonymousClass1) {
            this(z, singularOverwritePolicy, builder);
        }

        private void checkUnknownFields(List<String> list) throws ParseException {
            if (list.isEmpty()) {
                return;
            }
            StringBuilder sb = new StringBuilder("Input contains unknown fields and/or extensions:");
            for (String str : list) {
                sb.append('\n');
                sb.append(str);
            }
            if (!this.allowUnknownFields) {
                String[] split = list.get(0).split(":");
                throw new ParseException(Integer.valueOf(split[0]).intValue(), Integer.valueOf(split[1]).intValue(), sb.toString());
            }
            TextFormat.logger.warning(sb.toString());
        }

        private void consumeFieldValue(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, Descriptors.FieldDescriptor fieldDescriptor, ExtensionRegistry.ExtensionInfo extensionInfo, TextFormatParseInfoTree.Builder builder, List<String> list) throws ParseException {
            long consumeInt64;
            int consumeInt32;
            String str;
            Object obj = null;
            Message message = null;
            if (fieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                switch (fieldDescriptor.getType().ordinal()) {
                    case 0:
                        obj = Double.valueOf(tokenizer.consumeDouble());
                        break;
                    case 1:
                        obj = Float.valueOf(tokenizer.consumeFloat());
                        break;
                    case 2:
                    case 15:
                    case 17:
                        consumeInt64 = tokenizer.consumeInt64();
                        obj = Long.valueOf(consumeInt64);
                        break;
                    case 3:
                    case 5:
                        consumeInt64 = tokenizer.consumeUInt64();
                        obj = Long.valueOf(consumeInt64);
                        break;
                    case 4:
                    case 14:
                    case 16:
                        consumeInt32 = tokenizer.consumeInt32();
                        obj = Integer.valueOf(consumeInt32);
                        break;
                    case 6:
                    case 12:
                        consumeInt32 = tokenizer.consumeUInt32();
                        obj = Integer.valueOf(consumeInt32);
                        break;
                    case 7:
                        obj = Boolean.valueOf(tokenizer.consumeBoolean());
                        break;
                    case 8:
                        obj = tokenizer.consumeString();
                        break;
                    case 9:
                    case 10:
                        throw new RuntimeException("Can't get here.");
                    case 11:
                        obj = tokenizer.consumeByteString();
                        break;
                    case 13:
                        Descriptors.EnumDescriptor mo9296getEnumType = fieldDescriptor.mo9296getEnumType();
                        if (tokenizer.lookingAtInteger()) {
                            int consumeInt322 = tokenizer.consumeInt32();
                            obj = mo9296getEnumType.mo9850findValueByNumber(consumeInt322);
                            if (obj == null) {
                                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Enum type \"");
                                outline107.append(mo9296getEnumType.getFullName());
                                outline107.append("\" has no value with number ");
                                outline107.append(consumeInt322);
                                outline107.append('.');
                                throw tokenizer.parseExceptionPreviousToken(outline107.toString());
                            }
                        } else {
                            String consumeIdentifier = tokenizer.consumeIdentifier();
                            obj = mo9296getEnumType.findValueByName(consumeIdentifier);
                            if (obj == null) {
                                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Enum type \"");
                                outline1072.append(mo9296getEnumType.getFullName());
                                outline1072.append("\" has no value named \"");
                                outline1072.append(consumeIdentifier);
                                outline1072.append("\".");
                                throw tokenizer.parseExceptionPreviousToken(outline1072.toString());
                            }
                        }
                        break;
                }
            } else {
                if (tokenizer.tryConsume(Config.Compare.LESS_THAN)) {
                    str = Config.Compare.GREATER_THAN;
                } else {
                    tokenizer.consume(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
                    str = EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE;
                }
                String str2 = str;
                if (extensionInfo != null) {
                    message = extensionInfo.defaultInstance;
                }
                MessageReflection.MergeTarget newMergeTargetForField = mergeTarget.newMergeTargetForField(fieldDescriptor, message);
                while (!tokenizer.tryConsume(str2)) {
                    if (tokenizer.atEnd()) {
                        throw tokenizer.parseException(GeneratedOutlineSupport1.outline75("Expected \"", str2, "\"."));
                    }
                    mergeField(tokenizer, extensionRegistry, newMergeTargetForField, builder, list);
                }
                obj = newMergeTargetForField.finish();
            }
            if (fieldDescriptor.isRepeated()) {
                mergeTarget.addRepeatedField(fieldDescriptor, obj);
            } else if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && mergeTarget.hasField(fieldDescriptor)) {
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Non-repeated field \"");
                outline1073.append(fieldDescriptor.getFullName());
                outline1073.append("\" cannot be overwritten.");
                throw tokenizer.parseExceptionPreviousToken(outline1073.toString());
            } else if (this.singularOverwritePolicy != SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES || fieldDescriptor.getContainingOneof() == null || !mergeTarget.hasOneof(fieldDescriptor.getContainingOneof())) {
                mergeTarget.setField(fieldDescriptor, obj);
            } else {
                Descriptors.OneofDescriptor containingOneof = fieldDescriptor.getContainingOneof();
                StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Field \"");
                outline1074.append(fieldDescriptor.getFullName());
                outline1074.append("\" is specified along with field \"");
                outline1074.append(mergeTarget.getOneofFieldDescriptor(containingOneof).getFullName());
                outline1074.append("\", another member of oneof \"");
                outline1074.append(containingOneof.getName());
                outline1074.append("\".");
                throw tokenizer.parseExceptionPreviousToken(outline1074.toString());
            }
        }

        private void consumeFieldValues(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, Descriptors.FieldDescriptor fieldDescriptor, ExtensionRegistry.ExtensionInfo extensionInfo, TextFormatParseInfoTree.Builder builder, List<String> list) throws ParseException {
            if (!fieldDescriptor.isRepeated() || !tokenizer.tryConsume("[")) {
                consumeFieldValue(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo, builder, list);
            } else if (tokenizer.tryConsume("]")) {
            } else {
                while (true) {
                    consumeFieldValue(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo, builder, list);
                    if (tokenizer.tryConsume("]")) {
                        return;
                    }
                    tokenizer.consume(",");
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:52:0x017f  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x018e  */
        /* JADX WARN: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void mergeField(com.google.protobuf.TextFormat.Tokenizer r17, com.google.protobuf.ExtensionRegistry r18, com.google.protobuf.MessageReflection.MergeTarget r19, com.google.protobuf.TextFormatParseInfoTree.Builder r20, java.util.List<java.lang.String> r21) throws com.google.protobuf.TextFormat.ParseException {
            /*
                Method dump skipped, instructions count: 404
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.TextFormat.Parser.mergeField(com.google.protobuf.TextFormat$Tokenizer, com.google.protobuf.ExtensionRegistry, com.google.protobuf.MessageReflection$MergeTarget, com.google.protobuf.TextFormatParseInfoTree$Builder, java.util.List):void");
        }

        private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, List<String> list) throws ParseException {
            mergeField(tokenizer, extensionRegistry, mergeTarget, this.parseInfoTreeBuilder, list);
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        private void skipField(Tokenizer tokenizer) throws ParseException {
            if (tokenizer.tryConsume("[")) {
                do {
                    tokenizer.consumeIdentifier();
                } while (tokenizer.tryConsume("."));
                tokenizer.consume("]");
            } else {
                tokenizer.consumeIdentifier();
            }
            if (!tokenizer.tryConsume(":") || tokenizer.lookingAt(Config.Compare.LESS_THAN) || tokenizer.lookingAt(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN)) {
                skipFieldMessage(tokenizer);
            } else {
                skipFieldValue(tokenizer);
            }
            if (!tokenizer.tryConsume(";")) {
                tokenizer.tryConsume(",");
            }
        }

        private void skipFieldMessage(Tokenizer tokenizer) throws ParseException {
            String str;
            if (tokenizer.tryConsume(Config.Compare.LESS_THAN)) {
                str = Config.Compare.GREATER_THAN;
            } else {
                tokenizer.consume(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
                str = EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE;
            }
            while (!tokenizer.lookingAt(Config.Compare.GREATER_THAN) && !tokenizer.lookingAt(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE)) {
                skipField(tokenizer);
            }
            tokenizer.consume(str);
        }

        private void skipFieldValue(Tokenizer tokenizer) throws ParseException {
            if (tokenizer.tryConsumeString()) {
                do {
                } while (tokenizer.tryConsumeString());
            } else if (tokenizer.tryConsumeIdentifier() || tokenizer.tryConsumeInt64() || tokenizer.tryConsumeUInt64() || tokenizer.tryConsumeDouble() || tokenizer.tryConsumeFloat()) {
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid field value: ");
                outline107.append(tokenizer.currentToken);
                throw tokenizer.parseException(outline107.toString());
            }
        }

        private static StringBuilder toStringBuilder(Readable readable) throws IOException {
            StringBuilder sb = new StringBuilder();
            CharBuffer allocate = CharBuffer.allocate(4096);
            while (true) {
                int read = readable.read(allocate);
                if (read == -1) {
                    return sb;
                }
                allocate.flip();
                sb.append((CharSequence) allocate, 0, read);
            }
        }

        public void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
            Tokenizer tokenizer = new Tokenizer(charSequence, null);
            MessageReflection.BuilderAdapter builderAdapter = new MessageReflection.BuilderAdapter(builder);
            ArrayList arrayList = new ArrayList();
            while (!tokenizer.atEnd()) {
                mergeField(tokenizer, extensionRegistry, builderAdapter, arrayList);
            }
            checkUnknownFields(arrayList);
        }

        public void merge(CharSequence charSequence, Message.Builder builder) throws ParseException {
            merge(charSequence, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
            merge(toStringBuilder(readable), extensionRegistry, builder);
        }

        public void merge(Readable readable, Message.Builder builder) throws IOException {
            merge(readable, ExtensionRegistry.getEmptyRegistry(), builder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class Printer {
        static final Printer DEFAULT = new Printer(true);
        static final Printer UNICODE = new Printer(false);
        private final boolean escapeNonAscii;

        private Printer(boolean z) {
            this.escapeNonAscii = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void print(MessageOrBuilder messageOrBuilder, TextGenerator textGenerator) throws IOException {
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : messageOrBuilder.getAllFields().entrySet()) {
                printField(entry.getKey(), entry.getValue(), textGenerator);
            }
            printUnknownFields(messageOrBuilder.getUnknownFields(), textGenerator);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            if (!fieldDescriptor.isRepeated()) {
                printSingleField(fieldDescriptor, obj, textGenerator);
                return;
            }
            for (Object obj2 : (List) obj) {
                printSingleField(fieldDescriptor, obj2, textGenerator);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            String num;
            String escapeText;
            switch (fieldDescriptor.getType().ordinal()) {
                case 0:
                    num = ((Double) obj).toString();
                    textGenerator.print(num);
                    return;
                case 1:
                    num = ((Float) obj).toString();
                    textGenerator.print(num);
                    return;
                case 2:
                case 15:
                case 17:
                    num = ((Long) obj).toString();
                    textGenerator.print(num);
                    return;
                case 3:
                case 5:
                    num = TextFormat.unsignedToString(((Long) obj).longValue());
                    textGenerator.print(num);
                    return;
                case 4:
                case 14:
                case 16:
                    num = ((Integer) obj).toString();
                    textGenerator.print(num);
                    return;
                case 6:
                case 12:
                    num = TextFormat.unsignedToString(((Integer) obj).intValue());
                    textGenerator.print(num);
                    return;
                case 7:
                    num = ((Boolean) obj).toString();
                    textGenerator.print(num);
                    return;
                case 8:
                    textGenerator.print(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
                    String str = (String) obj;
                    escapeText = this.escapeNonAscii ? TextFormatEscaper.escapeText(str) : TextFormat.escapeDoubleQuotesAndBackslashes(str).replace("\n", "\\n");
                    textGenerator.print(escapeText);
                    textGenerator.print(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
                    return;
                case 9:
                case 10:
                    print((Message) obj, textGenerator);
                    return;
                case 11:
                    textGenerator.print(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
                    escapeText = obj instanceof ByteString ? TextFormat.escapeBytes((ByteString) obj) : TextFormat.escapeBytes((byte[]) obj);
                    textGenerator.print(escapeText);
                    textGenerator.print(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
                    return;
                case 13:
                    num = ((Descriptors.EnumValueDescriptor) obj).getName();
                    textGenerator.print(num);
                    return;
                default:
                    return;
            }
        }

        private void printSingleField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            String name;
            if (fieldDescriptor.isExtension()) {
                textGenerator.print("[");
                textGenerator.print((!fieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat() || fieldDescriptor.getType() != Descriptors.FieldDescriptor.Type.MESSAGE || !fieldDescriptor.isOptional() || fieldDescriptor.getExtensionScope() != fieldDescriptor.getMessageType()) ? fieldDescriptor.getFullName() : fieldDescriptor.getMessageType().getFullName());
                name = "]";
            } else {
                name = fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP ? fieldDescriptor.getMessageType().getName() : fieldDescriptor.getName();
            }
            textGenerator.print(name);
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                textGenerator.print(" {");
                textGenerator.eol();
                textGenerator.indent();
            } else {
                textGenerator.print(RealTimeTextConstants.COLON_SPACE);
            }
            printFieldValue(fieldDescriptor, obj, textGenerator);
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                textGenerator.outdent();
                textGenerator.print(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
            textGenerator.eol();
        }

        private void printUnknownField(int i, int i2, List<?> list, TextGenerator textGenerator) throws IOException {
            for (Object obj : list) {
                textGenerator.print(String.valueOf(i));
                textGenerator.print(RealTimeTextConstants.COLON_SPACE);
                TextFormat.printUnknownFieldValue(i2, obj, textGenerator);
                textGenerator.eol();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printUnknownFields(UnknownFieldSet unknownFieldSet, TextGenerator textGenerator) throws IOException {
            for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFieldSet.asMap().entrySet()) {
                int intValue = entry.getKey().intValue();
                UnknownFieldSet.Field value = entry.getValue();
                printUnknownField(intValue, 0, value.getVarintList(), textGenerator);
                printUnknownField(intValue, 5, value.getFixed32List(), textGenerator);
                printUnknownField(intValue, 1, value.getFixed64List(), textGenerator);
                printUnknownField(intValue, 2, value.getLengthDelimitedList(), textGenerator);
                for (UnknownFieldSet unknownFieldSet2 : value.getGroupList()) {
                    textGenerator.print(entry.getKey().toString());
                    textGenerator.print(" {");
                    textGenerator.eol();
                    textGenerator.indent();
                    printUnknownFields(unknownFieldSet2, textGenerator);
                    textGenerator.outdent();
                    textGenerator.print(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                    textGenerator.eol();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class TextGenerator {
        private boolean atStartOfLine;
        private final StringBuilder indent;
        private final Appendable output;
        private final boolean singleLineMode;

        private TextGenerator(Appendable appendable, boolean z) {
            this.indent = new StringBuilder();
            this.atStartOfLine = false;
            this.output = appendable;
            this.singleLineMode = z;
        }

        /* synthetic */ TextGenerator(Appendable appendable, boolean z, AnonymousClass1 anonymousClass1) {
            this(appendable, z);
        }

        public void eol() throws IOException {
            if (!this.singleLineMode) {
                this.output.append("\n");
            }
            this.atStartOfLine = true;
        }

        public void indent() {
            this.indent.append("  ");
        }

        public void outdent() {
            int length = this.indent.length();
            if (length != 0) {
                this.indent.setLength(length - 2);
                return;
            }
            throw new IllegalArgumentException(" Outdent() without matching Indent().");
        }

        public void print(CharSequence charSequence) throws IOException {
            if (this.atStartOfLine) {
                this.atStartOfLine = false;
                this.output.append(this.singleLineMode ? " " : this.indent);
            }
            this.output.append(charSequence);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class Tokenizer {
        private int column;
        private String currentToken;
        private int line;
        private final Matcher matcher;
        private int pos;
        private int previousColumn;
        private int previousLine;
        private final CharSequence text;
        private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
        private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
        private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
        private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
        private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);

        private Tokenizer(CharSequence charSequence) {
            this.pos = 0;
            this.line = 0;
            this.column = 0;
            this.previousLine = 0;
            this.previousColumn = 0;
            this.text = charSequence;
            this.matcher = WHITESPACE.matcher(charSequence);
            skipWhitespace();
            nextToken();
        }

        /* synthetic */ Tokenizer(CharSequence charSequence, AnonymousClass1 anonymousClass1) {
            this(charSequence);
        }

        private void consumeByteString(List<ByteString> list) throws ParseException {
            char c = 0;
            if (this.currentToken.length() > 0) {
                c = this.currentToken.charAt(0);
            }
            if (c == '\"' || c == '\'') {
                if (this.currentToken.length() >= 2) {
                    String str = this.currentToken;
                    if (str.charAt(str.length() - 1) == c) {
                        try {
                            ByteString unescapeBytes = TextFormat.unescapeBytes(this.currentToken.substring(1, this.currentToken.length() - 1));
                            nextToken();
                            list.add(unescapeBytes);
                            return;
                        } catch (InvalidEscapeSequenceException e) {
                            throw parseException(e.getMessage());
                        }
                    }
                }
                throw parseException("String missing ending quote.");
            }
            throw parseException("Expected string.");
        }

        private ParseException floatParseException(NumberFormatException numberFormatException) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Couldn't parse number: ");
            outline107.append(numberFormatException.getMessage());
            return parseException(outline107.toString());
        }

        private ParseException integerParseException(NumberFormatException numberFormatException) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Couldn't parse integer: ");
            outline107.append(numberFormatException.getMessage());
            return parseException(outline107.toString());
        }

        private void skipWhitespace() {
            this.matcher.usePattern(WHITESPACE);
            if (this.matcher.lookingAt()) {
                Matcher matcher = this.matcher;
                matcher.region(matcher.end(), this.matcher.regionEnd());
            }
        }

        public boolean atEnd() {
            return this.currentToken.length() == 0;
        }

        public void consume(String str) throws ParseException {
            if (tryConsume(str)) {
                return;
            }
            throw parseException(GeneratedOutlineSupport1.outline75("Expected \"", str, "\"."));
        }

        public boolean consumeBoolean() throws ParseException {
            if (this.currentToken.equals("true") || this.currentToken.equals(XMPConst.TRUESTR) || this.currentToken.equals("t") || this.currentToken.equals("1")) {
                nextToken();
                return true;
            } else if (!this.currentToken.equals(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE) && !this.currentToken.equals(XMPConst.FALSESTR) && !this.currentToken.equals("f") && !this.currentToken.equals("0")) {
                throw parseException("Expected \"true\" or \"false\".");
            } else {
                nextToken();
                return false;
            }
        }

        public ByteString consumeByteString() throws ParseException {
            ArrayList arrayList = new ArrayList();
            while (true) {
                consumeByteString(arrayList);
                if (!this.currentToken.startsWith("'") && !this.currentToken.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED)) {
                    return ByteString.copyFrom(arrayList);
                }
            }
        }

        public double consumeDouble() throws ParseException {
            if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
                boolean startsWith = this.currentToken.startsWith(ProcessIdUtil.DEFAULT_PROCESSID);
                nextToken();
                return startsWith ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            } else if (this.currentToken.equalsIgnoreCase("nan")) {
                nextToken();
                return Double.NaN;
            } else {
                try {
                    double parseDouble = Double.parseDouble(this.currentToken);
                    nextToken();
                    return parseDouble;
                } catch (NumberFormatException e) {
                    throw floatParseException(e);
                }
            }
        }

        public float consumeFloat() throws ParseException {
            if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
                boolean startsWith = this.currentToken.startsWith(ProcessIdUtil.DEFAULT_PROCESSID);
                nextToken();
                return startsWith ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
            } else if (FLOAT_NAN.matcher(this.currentToken).matches()) {
                nextToken();
                return Float.NaN;
            } else {
                try {
                    float parseFloat = Float.parseFloat(this.currentToken);
                    nextToken();
                    return parseFloat;
                } catch (NumberFormatException e) {
                    throw floatParseException(e);
                }
            }
        }

        public String consumeIdentifier() throws ParseException {
            for (int i = 0; i < this.currentToken.length(); i++) {
                char charAt = this.currentToken.charAt(i);
                if (('a' > charAt || charAt > 'z') && (('A' > charAt || charAt > 'Z') && !(('0' <= charAt && charAt <= '9') || charAt == '_' || charAt == '.'))) {
                    throw parseException(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Expected identifier. Found '"), this.currentToken, "'"));
                }
            }
            String str = this.currentToken;
            nextToken();
            return str;
        }

        public int consumeInt32() throws ParseException {
            try {
                int parseInt32 = TextFormat.parseInt32(this.currentToken);
                nextToken();
                return parseInt32;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public long consumeInt64() throws ParseException {
            try {
                long parseInt64 = TextFormat.parseInt64(this.currentToken);
                nextToken();
                return parseInt64;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public String consumeString() throws ParseException {
            return consumeByteString().toStringUtf8();
        }

        public int consumeUInt32() throws ParseException {
            try {
                int parseUInt32 = TextFormat.parseUInt32(this.currentToken);
                nextToken();
                return parseUInt32;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public long consumeUInt64() throws ParseException {
            try {
                long parseUInt64 = TextFormat.parseUInt64(this.currentToken);
                nextToken();
                return parseUInt64;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        int getColumn() {
            return this.column;
        }

        int getLine() {
            return this.line;
        }

        int getPreviousColumn() {
            return this.previousColumn;
        }

        int getPreviousLine() {
            return this.previousLine;
        }

        public boolean lookingAt(String str) {
            return this.currentToken.equals(str);
        }

        public boolean lookingAtInteger() {
            if (this.currentToken.length() == 0) {
                return false;
            }
            char charAt = this.currentToken.charAt(0);
            return ('0' <= charAt && charAt <= '9') || charAt == '-' || charAt == '+';
        }

        public void nextToken() {
            Matcher matcher;
            int i;
            int regionEnd;
            int i2;
            this.previousLine = this.line;
            this.previousColumn = this.column;
            while (this.pos < this.matcher.regionStart()) {
                if (this.text.charAt(this.pos) == '\n') {
                    this.line++;
                    i2 = 0;
                } else {
                    i2 = this.column + 1;
                }
                this.column = i2;
                this.pos++;
            }
            if (this.matcher.regionStart() == this.matcher.regionEnd()) {
                this.currentToken = "";
                return;
            }
            this.matcher.usePattern(TOKEN);
            if (this.matcher.lookingAt()) {
                this.currentToken = this.matcher.group();
                matcher = this.matcher;
                i = matcher.end();
                regionEnd = this.matcher.regionEnd();
            } else {
                this.currentToken = String.valueOf(this.text.charAt(this.pos));
                matcher = this.matcher;
                i = this.pos + 1;
                regionEnd = matcher.regionEnd();
            }
            matcher.region(i, regionEnd);
            skipWhitespace();
        }

        public ParseException parseException(String str) {
            return new ParseException(this.line + 1, this.column + 1, str);
        }

        public ParseException parseExceptionPreviousToken(String str) {
            return new ParseException(this.previousLine + 1, this.previousColumn + 1, str);
        }

        public boolean tryConsume(String str) {
            if (this.currentToken.equals(str)) {
                nextToken();
                return true;
            }
            return false;
        }

        public boolean tryConsumeDouble() {
            try {
                consumeDouble();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean tryConsumeFloat() {
            try {
                consumeFloat();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean tryConsumeIdentifier() {
            try {
                consumeIdentifier();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean tryConsumeInt64() {
            try {
                consumeInt64();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean tryConsumeString() {
            try {
                consumeString();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean tryConsumeUInt64() {
            try {
                consumeUInt64();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public UnknownFieldParseException unknownFieldParseExceptionPreviousToken(String str, String str2) {
            return new UnknownFieldParseException(this.previousLine + 1, this.previousColumn + 1, str, str2);
        }
    }

    /* loaded from: classes3.dex */
    public static class UnknownFieldParseException extends ParseException {
        private final String unknownField;

        public UnknownFieldParseException(int i, int i2, String str, String str2) {
            super(i, i2, str2);
            this.unknownField = str;
        }

        public UnknownFieldParseException(String str) {
            this(-1, -1, "", str);
        }

        public String getUnknownField() {
            return this.unknownField;
        }
    }

    private TextFormat() {
    }

    private static int digitValue(byte b) {
        if (48 > b || b > 57) {
            return ((97 > b || b > 122) ? b + ByteSourceJsonBootstrapper.UTF8_BOM_3 : b - 97) + 10;
        }
        return b - 48;
    }

    public static String escapeBytes(ByteString byteString) {
        return TextFormatEscaper.escapeBytes(byteString);
    }

    public static String escapeBytes(byte[] bArr) {
        return TextFormatEscaper.escapeBytes(bArr);
    }

    public static String escapeDoubleQuotesAndBackslashes(String str) {
        return TextFormatEscaper.escapeDoubleQuotesAndBackslashes(str);
    }

    static String escapeText(String str) {
        return escapeBytes(ByteString.copyFromUtf8(str));
    }

    public static Parser getParser() {
        return PARSER;
    }

    private static boolean isHex(byte b) {
        return (48 <= b && b <= 57) || (97 <= b && b <= 102) || (65 <= b && b <= 70);
    }

    private static boolean isOctal(byte b) {
        return 48 <= b && b <= 55;
    }

    public static void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        PARSER.merge(charSequence, extensionRegistry, builder);
    }

    public static void merge(CharSequence charSequence, Message.Builder builder) throws ParseException {
        PARSER.merge(charSequence, builder);
    }

    public static void merge(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
        PARSER.merge(readable, extensionRegistry, builder);
    }

    public static void merge(Readable readable, Message.Builder builder) throws IOException {
        PARSER.merge(readable, builder);
    }

    private static TextGenerator multiLineOutput(Appendable appendable) {
        return new TextGenerator(appendable, false, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int parseInt32(String str) throws NumberFormatException {
        return (int) parseInteger(str, true, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long parseInt64(String str) throws NumberFormatException {
        return parseInteger(str, true, true);
    }

    private static long parseInteger(String str, boolean z, boolean z2) throws NumberFormatException {
        int i;
        int i2;
        int i3 = 0;
        if (str.startsWith(ProcessIdUtil.DEFAULT_PROCESSID, 0)) {
            if (!z) {
                throw new NumberFormatException(GeneratedOutlineSupport1.outline72("Number must be positive: ", str));
            }
            i3 = 1;
        }
        int i4 = 10;
        if (str.startsWith("0x", i3)) {
            i2 = i3 + 2;
            i = 16;
        } else {
            if (str.startsWith("0", i3)) {
                i4 = 8;
            }
            i = i4;
            i2 = i3;
        }
        String substring = str.substring(i2);
        if (substring.length() < 16) {
            long parseLong = Long.parseLong(substring, i);
            if (i3 != 0) {
                parseLong = -parseLong;
            }
            if (z2) {
                return parseLong;
            }
            if (z) {
                if (parseLong <= 2147483647L && parseLong >= -2147483648L) {
                    return parseLong;
                }
                throw new NumberFormatException(GeneratedOutlineSupport1.outline72("Number out of range for 32-bit signed integer: ", str));
            } else if (parseLong < 4294967296L && parseLong >= 0) {
                return parseLong;
            } else {
                throw new NumberFormatException(GeneratedOutlineSupport1.outline72("Number out of range for 32-bit unsigned integer: ", str));
            }
        }
        BigInteger bigInteger = new BigInteger(substring, i);
        if (i3 != 0) {
            bigInteger = bigInteger.negate();
        }
        if (!z2) {
            if (z) {
                if (bigInteger.bitLength() > 31) {
                    throw new NumberFormatException(GeneratedOutlineSupport1.outline72("Number out of range for 32-bit signed integer: ", str));
                }
            } else if (bigInteger.bitLength() > 32) {
                throw new NumberFormatException(GeneratedOutlineSupport1.outline72("Number out of range for 32-bit unsigned integer: ", str));
            }
        } else if (z) {
            if (bigInteger.bitLength() > 63) {
                throw new NumberFormatException(GeneratedOutlineSupport1.outline72("Number out of range for 64-bit signed integer: ", str));
            }
        } else if (bigInteger.bitLength() > 64) {
            throw new NumberFormatException(GeneratedOutlineSupport1.outline72("Number out of range for 64-bit unsigned integer: ", str));
        }
        return bigInteger.longValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int parseUInt32(String str) throws NumberFormatException {
        return (int) parseInteger(str, false, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long parseUInt64(String str) throws NumberFormatException {
        return parseInteger(str, false, true);
    }

    public static void print(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
        Printer.DEFAULT.print(messageOrBuilder, multiLineOutput(appendable));
    }

    public static void print(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
        Printer.DEFAULT.printUnknownFields(unknownFieldSet, multiLineOutput(appendable));
    }

    public static void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        Printer.DEFAULT.printField(fieldDescriptor, obj, multiLineOutput(appendable));
    }

    public static String printFieldToString(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
        try {
            StringBuilder sb = new StringBuilder();
            printField(fieldDescriptor, obj, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        Printer.DEFAULT.printFieldValue(fieldDescriptor, obj, multiLineOutput(appendable));
    }

    public static String printToString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            print(messageOrBuilder, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            print(unknownFieldSet, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            Printer.UNICODE.print(messageOrBuilder, multiLineOutput(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            Printer.UNICODE.printUnknownFields(unknownFieldSet, multiLineOutput(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printUnicode(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
        Printer.UNICODE.print(messageOrBuilder, multiLineOutput(appendable));
    }

    public static void printUnicode(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
        Printer.UNICODE.printUnknownFields(unknownFieldSet, multiLineOutput(appendable));
    }

    public static void printUnicodeFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        Printer.UNICODE.printFieldValue(fieldDescriptor, obj, multiLineOutput(appendable));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void printUnknownFieldValue(int i, Object obj, TextGenerator textGenerator) throws IOException {
        String unsignedToString;
        int tagWireType = WireFormat.getTagWireType(i);
        if (tagWireType == 0) {
            unsignedToString = unsignedToString(((Long) obj).longValue());
        } else if (tagWireType == 1) {
            unsignedToString = String.format(null, "0x%016x", (Long) obj);
        } else if (tagWireType == 2) {
            try {
                UnknownFieldSet parseFrom = UnknownFieldSet.parseFrom((ByteString) obj);
                textGenerator.print(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
                textGenerator.eol();
                textGenerator.indent();
                Printer.DEFAULT.printUnknownFields(parseFrom, textGenerator);
                textGenerator.outdent();
                textGenerator.print(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return;
            } catch (InvalidProtocolBufferException unused) {
                unsignedToString = EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED;
                textGenerator.print(unsignedToString);
                textGenerator.print(escapeBytes((ByteString) obj));
            }
        } else if (tagWireType == 3) {
            Printer.DEFAULT.printUnknownFields((UnknownFieldSet) obj, textGenerator);
            return;
        } else if (tagWireType != 5) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Bad tag: ", i));
        } else {
            unsignedToString = String.format(null, "0x%08x", (Integer) obj);
        }
        textGenerator.print(unsignedToString);
    }

    public static void printUnknownFieldValue(int i, Object obj, Appendable appendable) throws IOException {
        printUnknownFieldValue(i, obj, multiLineOutput(appendable));
    }

    public static String shortDebugString(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
        try {
            StringBuilder sb = new StringBuilder();
            Printer.DEFAULT.printField(fieldDescriptor, obj, singleLineOutput(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String shortDebugString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            Printer.DEFAULT.print(messageOrBuilder, singleLineOutput(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String shortDebugString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            Printer.DEFAULT.printUnknownFields(unknownFieldSet, singleLineOutput(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static TextGenerator singleLineOutput(Appendable appendable) {
        return new TextGenerator(appendable, true, null);
    }

    public static ByteString unescapeBytes(CharSequence charSequence) throws InvalidEscapeSequenceException {
        int i;
        int i2;
        ByteString copyFromUtf8 = ByteString.copyFromUtf8(charSequence.toString());
        byte[] bArr = new byte[copyFromUtf8.size()];
        int i3 = 0;
        int i4 = 0;
        while (i3 < copyFromUtf8.size()) {
            byte byteAt = copyFromUtf8.byteAt(i3);
            if (byteAt == 92) {
                i3++;
                if (i3 >= copyFromUtf8.size()) {
                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
                }
                byte byteAt2 = copyFromUtf8.byteAt(i3);
                if (isOctal(byteAt2)) {
                    int digitValue = digitValue(byteAt2);
                    int i5 = i3 + 1;
                    if (i5 < copyFromUtf8.size() && isOctal(copyFromUtf8.byteAt(i5))) {
                        digitValue = (digitValue * 8) + digitValue(copyFromUtf8.byteAt(i5));
                        i3 = i5;
                    }
                    int i6 = i3 + 1;
                    if (i6 < copyFromUtf8.size() && isOctal(copyFromUtf8.byteAt(i6))) {
                        digitValue = (digitValue * 8) + digitValue(copyFromUtf8.byteAt(i6));
                        i3 = i6;
                    }
                    i = i4 + 1;
                    bArr[i4] = (byte) digitValue;
                } else {
                    if (byteAt2 == 34) {
                        i2 = i4 + 1;
                        bArr[i4] = GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE;
                    } else if (byteAt2 == 39) {
                        i2 = i4 + 1;
                        bArr[i4] = GenericAccessProfile.LE_SUPPORTED_FEATURES;
                    } else if (byteAt2 == 92) {
                        i2 = i4 + 1;
                        bArr[i4] = 92;
                    } else if (byteAt2 == 102) {
                        i2 = i4 + 1;
                        bArr[i4] = 12;
                    } else if (byteAt2 == 110) {
                        i2 = i4 + 1;
                        bArr[i4] = 10;
                    } else if (byteAt2 == 114) {
                        i2 = i4 + 1;
                        bArr[i4] = 13;
                    } else if (byteAt2 == 116) {
                        i2 = i4 + 1;
                        bArr[i4] = 9;
                    } else if (byteAt2 == 118) {
                        i2 = i4 + 1;
                        bArr[i4] = 11;
                    } else if (byteAt2 == 120) {
                        i3++;
                        if (i3 >= copyFromUtf8.size() || !isHex(copyFromUtf8.byteAt(i3))) {
                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                        }
                        int digitValue2 = digitValue(copyFromUtf8.byteAt(i3));
                        int i7 = i3 + 1;
                        if (i7 < copyFromUtf8.size() && isHex(copyFromUtf8.byteAt(i7))) {
                            digitValue2 = (digitValue2 * 16) + digitValue(copyFromUtf8.byteAt(i7));
                            i3 = i7;
                        }
                        i = i4 + 1;
                        bArr[i4] = (byte) digitValue2;
                    } else if (byteAt2 == 97) {
                        i2 = i4 + 1;
                        bArr[i4] = 7;
                    } else if (byteAt2 != 98) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid escape sequence: '\\");
                        outline107.append((char) byteAt2);
                        outline107.append(Chars.QUOTE);
                        throw new InvalidEscapeSequenceException(outline107.toString());
                    } else {
                        i2 = i4 + 1;
                        bArr[i4] = 8;
                    }
                    i4 = i2;
                    i3++;
                }
            } else {
                i = i4 + 1;
                bArr[i4] = byteAt;
            }
            i4 = i;
            i3++;
        }
        return bArr.length == i4 ? ByteString.wrap(bArr) : ByteString.copyFrom(bArr, 0, i4);
    }

    static String unescapeText(String str) throws InvalidEscapeSequenceException {
        return unescapeBytes(str).toStringUtf8();
    }

    public static String unsignedToString(int i) {
        return i >= 0 ? Integer.toString(i) : Long.toString(i & BodyPartID.bodyIdMax);
    }

    public static String unsignedToString(long j) {
        return j >= 0 ? Long.toString(j) : BigInteger.valueOf(j & Long.MAX_VALUE).setBit(63).toString();
    }
}
