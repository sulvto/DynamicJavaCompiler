package me.qinchao.compiler;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by sulvto on 16-4-22.
 */
public class JavaFileObjectImpl extends SimpleJavaFileObject {
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Construct a SimpleJavaFileObject of the given kind and with the
     * given URI.
     */
    public JavaFileObjectImpl(String name, Kind kind) {
        super(URI.create("string:///" + name.replace(".", "/") + kind.extension), kind);
    }


    public byte[] getClassData() {
        return outputStream.toByteArray();
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return outputStream;
    }

}
