package lombok.javac.apt;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.tools.javac.processing.JavacFiler;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.util.Options;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileManager;
import javax.tools.StandardLocation;
import lombok.permit.Permit;
@Deprecated
/* loaded from: classes4.dex */
public class Processor extends AbstractProcessor {
    private void addStacktrace(StringBuilder sb) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace != null) {
            sb.append("Called from\n");
            for (int i = 1; i < stackTrace.length; i++) {
                StackTraceElement stackTraceElement = stackTrace[i];
                if (!stackTraceElement.getClassName().equals("lombok.javac.apt.Processor")) {
                    sb.append("- ");
                    sb.append(stackTraceElement);
                    sb.append("\n");
                }
            }
        } else {
            sb.append("No stacktrace available\n");
        }
        sb.append("\n");
    }

    private String collectData(ProcessingEnvironment processingEnvironment) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Problem report for usages of 'lombok.javac.apt.Processor'\n\n");
        listOptions(outline107, processingEnvironment);
        findServices(outline107, processingEnvironment.getFiler());
        addStacktrace(outline107);
        listProperties(outline107);
        return outline107.toString();
    }

    private static String escape(char c) {
        if (c != '\f') {
            if (c == '\r') {
                return "\\r";
            }
            if (c == '\"') {
                return "\\\"";
            }
            if (c == '\'') {
                return "\\'";
            }
            if (c == '\\') {
                return "\\\\";
            }
            switch (c) {
                case '\b':
                    return "\\b";
                case '\t':
                    return "\\t";
                case '\n':
                    return "\\n";
                default:
                    return c < ' ' ? String.format("\\%03o", Integer.valueOf(c)) : String.valueOf(c);
            }
        }
        return "\\f";
    }

    private void findServices(StringBuilder sb, Filer filer) {
        Enumeration<URL> resources;
        try {
            JavaFileManager javaFileManager = (JavaFileManager) Permit.getField(JavacFiler.class, "fileManager").get(filer);
            resources = javaFileManager.getClassLoader(javaFileManager.hasLocation(StandardLocation.ANNOTATION_PROCESSOR_PATH) ? StandardLocation.ANNOTATION_PROCESSOR_PATH : StandardLocation.CLASS_PATH).getResources("META-INF/services/javax.annotation.processing.Processor");
        } catch (Exception unused) {
            sb.append("Filer information unavailable\n");
        }
        if (!resources.hasMoreElements()) {
            sb.append("No processors discovered\n\n");
            return;
        }
        sb.append("Discovered processors:\n");
        while (resources.hasMoreElements()) {
            URL nextElement = resources.nextElement();
            sb.append("- '");
            sb.append(nextElement);
            sb.append("'");
            InputStream inputStream = (InputStream) nextElement.getContent();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                StringWriter stringWriter = new StringWriter();
                char[] cArr = new char[8192];
                while (true) {
                    int read = inputStreamReader.read(cArr);
                    if (read == -1) {
                        break;
                    }
                    stringWriter.write(cArr, 0, read);
                }
                String stringWriter2 = stringWriter.toString();
                if (stringWriter2.contains("lombok.javac.apt.Processor")) {
                    sb.append(" <= problem\n");
                } else {
                    sb.append(" (ok)\n");
                }
                sb.append("    ");
                sb.append(stringWriter2.replace("\n", "\n    "));
                sb.append("\n");
                inputStream.close();
            }
        }
        sb.append("\n");
    }

    private void listOptions(StringBuilder sb, ProcessingEnvironment processingEnvironment) {
        try {
            Map map = (Map) Permit.getField(Options.class, "values").get(Options.instance(((JavacProcessingEnvironment) processingEnvironment).getContext()));
            if (map.isEmpty()) {
                sb.append("Options: empty\n\n");
                return;
            }
            sb.append("Compiler Options:\n");
            for (Map.Entry entry : map.entrySet()) {
                sb.append("- ");
                string(sb, (String) entry.getKey());
                sb.append(" = ");
                string(sb, (String) entry.getValue());
                sb.append("\n");
            }
            sb.append("\n");
        } catch (Exception unused) {
            sb.append("No options available\n\n");
        }
    }

    private void listProperties(StringBuilder sb) {
        ArrayList arrayList = new ArrayList(System.getProperties().stringPropertyNames());
        Collections.sort(arrayList);
        sb.append("Properties: \n");
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            String str = (String) it2.next();
            if (!str.startsWith("user.")) {
                sb.append("- ");
                sb.append(str);
                sb.append(" = ");
                string(sb, System.getProperty(str));
                sb.append("\n");
            }
        }
        sb.append("\n");
    }

    private String report(ProcessingEnvironment processingEnvironment) {
        String collectData = collectData(processingEnvironment);
        try {
            return writeFile(collectData);
        } catch (Exception unused) {
            return "Report:\n\n" + collectData;
        }
    }

    private static void string(StringBuilder sb, String str) {
        if (str == null) {
            sb.append("null");
            return;
        }
        sb.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        for (int i = 0; i < str.length(); i++) {
            sb.append(escape(str.charAt(i)));
        }
        sb.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
    }

    private String writeFile(String str) throws IOException {
        File createTempFile = File.createTempFile("lombok-processor-report-", ".txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(createTempFile));
        outputStreamWriter.write(str);
        outputStreamWriter.close();
        return "Report written to '" + createTempFile.getCanonicalPath() + "'\n";
    }

    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.values()[SourceVersion.values().length - 1];
    }

    public void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        if (System.getProperty("lombok.disable") != null) {
            return;
        }
        Messager messager = processingEnvironment.getMessager();
        Diagnostic.Kind kind = Diagnostic.Kind.WARNING;
        messager.printMessage(kind, "Wrong usage of 'lombok.javac.apt.Processor'. " + report(processingEnvironment));
    }

    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }
}
