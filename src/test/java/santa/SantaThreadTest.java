package santa;

import junit.framework.TestCase;
import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import java.util.List;

public class SantaThreadTest extends TestCase {
    public SantaThreadTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSantaThread() throws Exception {
        Twitter twtrc = new TwitterFactory("/test").getInstance();
        List<DirectMessage> dms = twtrc.getDirectMessages();
        for(DirectMessage dm : dms){
            twtrc.destroyDirectMessage(dm.getId());
        }
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.destroyFriendship("twtr_c");
        SantaThread thread = new SantaThread("http://localhost:8080");
        Thread.sleep(2000);
        twtrc.createFriendship("_santasan");
        Thread.sleep(2000);
        DirectMessage dm = twtrc.getDirectMessages().get(0);
        assertTrue(dm.getText().contains("http://localhost"));
        System.out.println(dm.getText());
    }
}
