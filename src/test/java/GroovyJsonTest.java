import groovy.json.JsonOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.ops4j.pax.exam.CoreOptions.*;

@RunWith(PaxExam.class)
public class GroovyJsonTest {
    private static final String GROOVY_VERSION = "3.0.17";
    private static final String GROOVY_GROUPID = "org.codehaus.groovy";

    @Configuration
    public static Option[] config() {
        return options(
                cleanCaches(),
//                mavenBundle("org.ow2.asm", "asm", "9.5"),
//                mavenBundle("org.ow2.asm", "asm-util", "9.5"),
//                mavenBundle("org.ow2.asm", "asm-tree", "9.5"),
//                mavenBundle("org.ow2.asm", "asm-analysis", "9.5"),
//                mavenBundle("org.ow2.asm", "asm-commons", "9.5"),
                mavenBundle("org.apache.aries.spifly", "org.apache.aries.spifly.dynamic.bundle", "1.2.4"),
                mavenBundle(GROOVY_GROUPID, "groovy-json", GROOVY_VERSION).noStart(),
                mavenBundle(GROOVY_GROUPID, "groovy", GROOVY_VERSION),
                systemTimeout(10000),
                junitBundles()
        );
    }

    private static final Map<String, Integer> mydata = new HashMap<>();

    static {
        mydata.put("one", 1);
        mydata.put("two", 2);
    }

    @Test
    public void testJsonOutput() {
        String jsonStr = JsonOutput.toJson(mydata);
        String expected = "{\n    \"one\": 1,\n    \"two\": 2\n}";
        assertEquals(expected, JsonOutput.prettyPrint(jsonStr));
    }
}
