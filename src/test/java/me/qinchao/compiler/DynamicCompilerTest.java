package me.qinchao.compiler;

import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by sulvto on 16-4-23.
 */
public class DynamicCompilerTest {

    @Test
    public void testCodeToObject() throws Exception {

        String name = "HelloWorld";
        StringBuilder src = new StringBuilder();
        src.append("public class HelloWorld {\n");
        src.append("    public String toString() {\n");
        src.append("        return \"Hello World\" ;\n");
        src.append("    }\n");
        src.append("}\n");

        Object hello = new DynamicCompiler().codeToObject(name, src.toString());
        Assert.assertNotNull(hello);
        Assert.assertEquals("Hello World", hello.toString());
        System.out.println(hello.toString());
    }
}