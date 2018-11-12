package GetStreamingDataFromTwitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class Tweets {

    ConfigurationBuilder cb = new ConfigurationBuilder();
    Twitter twitter;
    ArrayList<Status> tweets;

    Tweets() {
        cb.setDebugEnabled(true).setOAuthConsumerKey("dtSibnNrCzluG3VOy3joXyd4V")
                .setOAuthConsumerSecret("l4wl5YCP7IoGfhOYf15VeJEGFd6RiGOLnqFxiXjKetfC3la3CR")
                .setOAuthAccessToken("2843636385-eVSmWPZ9bUPzX28IzguOeFfK5QQeOrrMSIv4xjK")
                .setOAuthAccessTokenSecret("SYaWcVqZdNrwclcS3jsYqYZXdyfUanTaUAomJoIITXj9A")
                .setHttpProxyHost("10.231.2.3")
                .setHttpProxyPort(8000) ;
        twitter = new TwitterFactory(cb.build()).getInstance();
        tweets = new ArrayList<Status>();
    }

    public void getTweets(String tag, int numberOfTweets, int queryCount) {
        Query query = new Query(tag);
        long lastID = Long.MAX_VALUE;

        while (tweets.size() < numberOfTweets) {
            if (numberOfTweets - tweets.size() > 100)
                query.setCount(queryCount);
            else
                query.setCount(numberOfTweets - tweets.size());
            try {
                QueryResult result = twitter.search(query);
                tweets.addAll(result.getTweets());
                System.out.println("Gathered " + tweets.size() + " tweets" + "\n");
                for (Status t : tweets) {
                    if (t.getId() < lastID)
                        lastID = t.getId();
                }
            }

            catch (TwitterException te) {
                System.out.println("Couldn't connect: " + te);
            }
            ;
            query.setMaxId(lastID - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        Tweets t = new Tweets();
        t.getTweets("#Trump", 50, 50);

    }
}
