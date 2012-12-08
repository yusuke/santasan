/*
Copyright (c) 2012, Yusuke Yamamoto
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the Yusuke Yamamoto nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY Yusuke Yamamoto ``AS IS'' AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL Yusuke Yamamoto BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package santa;

import twitter4j.*;
import twitter4j.internal.logging.Logger;

/**
 * @author Yusuke Yamamoto <yusuke at mac.com>
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
