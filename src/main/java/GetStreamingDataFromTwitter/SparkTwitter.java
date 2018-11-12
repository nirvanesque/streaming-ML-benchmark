package GetStreamingDataFromTwitter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;

import twitter4j.Status;

public class SparkTwitter {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

      //  Properties prop = new Properties();
       // InputStream input = null;

      //  input = new FileInputStream("../TwitterStreaming/src/main/resources/config.properties");

        // load a properties file
      //  prop.load(input);

        // get the property value and print it out
        String consumerKey = "dtSibnNrCzluG3VOy3joXyd4V" ;//prop.getProperty("consumerKey");
        String consumerSecret = "l4wl5YCP7IoGfhOYf15VeJEGFd6RiGOLnqFxiXjKetfC3la3CR" ; //prop.getProperty("consumerSecret");
        String accessToken = "2843636385-eVSmWPZ9bUPzX28IzguOeFfK5QQeOrrMSIv4xjK" ;//prop.getProperty("accessToken");
        String accessTokenSecret = "SYaWcVqZdNrwclcS3jsYqYZXdyfUanTaUAomJoIITXj9A" ;//prop.getProperty("accessTokenSecret");
        String filePath = "/mnt/twitter.txt" ;//prop.getProperty("filePath");
      //  String[] filters = new String[2] ; //{ prop.getProperty("filter") };
        String[] filters = {"trump", "donald"};
        final File file = new File(filePath);

        SparkConf sparkConfig = new SparkConf().setMaster("local[5]").setAppName("Spark Streaming - Twitter");
        // 1 minute streaming window
        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(sparkConfig, new Duration(60000));
        System.setProperty("twitter4j.oauth.consumerKey", consumerKey);
        System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret);
        System.setProperty("twitter4j.oauth.accessToken", accessToken);
        System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret);

        JavaReceiverInputDStream<Status> twitterStream = TwitterUtils.createStream(javaStreamingContext, filters);

        twitterStream.foreachRDD(new Function<JavaRDD<Status>, Void>() {

            @Override
            public Void call(JavaRDD<Status> status) throws Exception {
                status.foreach(new VoidFunction<Status>() {

                    @Override
                    public void call(Status tweet) throws Exception {
                        FileWriter fout = new FileWriter(file, true);
                        PrintWriter fileout = new PrintWriter(fout);
                        fileout.println("--------------------------------------------------");
                        fileout.println(tweet.getCreatedAt());
                        fileout.println(tweet.getText());
                        fileout.println("**************************************************");
                        fileout.println(tweet);
                        fileout.println("**************************************************");
                        fileout.println("--------------------------------------------------");
                        fileout.flush();

                    }
                });
                return null;
            }
        });

        javaStreamingContext.start();
        javaStreamingContext.awaitTermination();

    }



}


