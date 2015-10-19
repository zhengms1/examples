import com.pahlsoft.watchdog.utility.CommandLineRunner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;

public class CommandLineRunnerTest extends TestNG {
    @Test
    public void testCommandLine() {
        JSONObject sample = CommandLineRunner.execute("echo","echo 'hello world'");
        Assert.assertEquals("hello world", sample.get("echo"));
    }

    @Test
    public void testHostName() {
        JSONObject sample = CommandLineRunner.execute("hostname","hostname");
        Assert.assertEquals("", sample.getString("hostname"));
    }
}
