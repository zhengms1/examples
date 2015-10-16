import com.pahlsoft.watchdog.utility.CommandLine;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class CommandLineTest extends TestNG {
    @Test
    public void testCommandLine() {
        ArrayList<String> sample = CommandLine.execute("echo 'hello world'");
        Assert.assertEquals("hello world", sample.get(0));
    }
}
