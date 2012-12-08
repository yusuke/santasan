package santa;

import ch.deathmar.Store;
import twitter4j.*;
import twitter4j.internal.logging.Logger;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yusuke
 * Date: 2012/12/08
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
public class SantaThread {
    Logger logger = Logger.getLogger(SantaThread.class);
    TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
    Twitter twitter = TwitterFactory.getSingleton();
    String path;
    SantaThread(String path){
        this.path = path;
        twitterStream.addListener(listener);
         // user() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
         twitterStream.user();
    }
    public void shutdown(){
        twitterStream.shutdown();
    }
    UserStreamListener listener = new UserStreamAdapter() {
        @Override
        public void onStatus(Status status) {
            logger.info("onStatus:" + status);
        }

        @Override
        public void onDirectMessage(DirectMessage directMessage) {
            logger.info("onDirectMessage text:" + directMessage.getText());
            Store.put(String.valueOf(directMessage.getSender().getId()), directMessage);
        }

        @Override
        public void onFollow(User source, User followedUser) {
            logger.info("onFollow: source:"+source +" followedUser:"+ followedUser);
            if(!source.getScreenName().equals("_santasan")){
                try {
                    twitter.createFriendship(source.getId());
                } catch (TwitterException e) {
                    logger.error("error", e);
                }
                try{
                    twitter.sendDirectMessage(source.getId(), "フォローありがとうございます。このURLよりアクセスしていただけます。 "+path +"/santa?id="+source.getId());
                    twitter.sendDirectMessage(source.getId(), "サンタさんからのメッセージを更新するには@"+followedUser.getScreenName()+"にDMを送ってください。 ");
                } catch (TwitterException e) {
                    logger.error("error", e);
                }
            }

        }

        @Override
        public void onException(Exception ex) {
            logger.error("error", ex);
        }
    };
}
