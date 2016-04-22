package me.qinchao.compiler;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * Created by sulvto on 16-4-22.
 */
public  class JavaSourceFromString extends SimpleJavaFileObject {

    private String code;

    /**
     * @param name the name of the compilation unit represented by this file object
     * @param code the source code for the compilation unit represented by this file object
     */
    protected JavaSourceFromString(String name, String code) {
        super(URI.create("string:///" + name.replace(".", "/") + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}