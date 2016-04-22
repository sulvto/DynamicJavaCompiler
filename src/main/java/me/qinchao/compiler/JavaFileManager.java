package me.qinchao.compiler;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import java.io.IOException;

/**
 * Created by sulvto on 16-4-22.
 */
public class JavaFileManager extends ForwardingJavaFileManager {
    private JavaFileObjectImpl javaFileObject;

    public JavaFileObjectImpl getJavaFileObject() {
        return javaFileObject;
    }

    /**
     * Creates a new instance of ForwardingJavaFileManager.
     *
     * @param fileManager delegate to this file manager
     */
    public JavaFileManager(javax.tools.JavaFileManager fileManager) {
        super(fileManager);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        javaFileObject = new JavaFileObjectImpl(className, kind);
        return javaFileObject;
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
    }
}