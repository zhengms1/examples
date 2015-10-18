import com.pahlsoft.watchdog.utility.CommandLine;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;

public class CommandLineTest extends TestNG {
    @Test
    public void testCommandLine() {
        JSONArray sample = CommandLine.execute("echo 'hello world'");
        Assert.assertEquals("hello world", sample.getString(0));
    }

    @Test
    public void testHostName() {
        JSONArray sample = CommandLine.execute("hostname");
        Assert.assertEquals("", sample.getString(0));
    }
}
