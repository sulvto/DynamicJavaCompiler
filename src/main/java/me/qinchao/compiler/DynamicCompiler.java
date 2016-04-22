package me.qinchao.compiler;

import javax.tools.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sulvto on 16-4-22.
 */
public class DynamicCompiler {
    private final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

    public Object codeToObject(String className, String code) throws IllegalAccessException, InstantiationException, IOException {
        List<JavaFileObject> compilationUnits = new ArrayList<>();


        DiagnosticCollector<Object> diagnosticListener = new DiagnosticCollector<>();

        JavaFileManager javaFileManager = new JavaFileManager(compiler.getStandardFileManager(diagnosticListener, null, null));

        compilationUnits.add(new JavaSourceFromString(className, code));
        List<String> options = new ArrayList<>();
        options.add("-encoding");
        options.add("UTF-8");
        //TODO options

        JavaCompiler.CompilationTask task = compiler.getTask(null, javaFileManager, diagnosticListener, options, null, compilationUnits);

        if (task.call()) {
            DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(this.getClass().getClassLoader());
            Class aClass = dynamicClassLoader.loadClass(className, javaFileManager.getJavaFileObject().getClassData());
            return aClass.newInstance();
        } else {
            ClassFormatError classFormatError = new ClassFormatError();
            for (Diagnostic diagnostic : diagnosticListener.getDiagnostics()) {
                System.out.println("Position:" + diagnostic.getPosition());
                System.out.println("Start Position:" + diagnostic.getStartPosition());
                System.out.println("End Position:" + diagnostic.getEndPosition());
                System.out.println("Source:" + diagnostic.getSource());
                String message = diagnostic.getMessage(null);
                System.out.println("Message:" + message);
                System.out.println("LineNumber:" + diagnostic.getLineNumber());
                System.out.println("ColumnNumber:" + diagnostic.getColumnNumber());
                classFormatError.addSuppressed(new Throwable(message));
            }
            throw classFormatError;
        }
    }


}
