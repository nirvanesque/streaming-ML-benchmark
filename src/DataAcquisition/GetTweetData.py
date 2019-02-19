# -*- coding: utf-8 -*-

# ------------------------------------------------------------------
#  Authors : Anirvan BASU, Baruch AMOUSSOU-DJANGBAN
# ------------------------------------------------------------------

import tweepy
from tweepy import OAuthHandler
from tweepy import Stream
from tweepy.streaming import StreamListener

# Read parameters from config.yml - needs to be implemented / modified

# Key Token 
consumer_key = '<<consumer key>>'
consumer_secret = '<<consumer secret>>'
access_token = '<<access token>>'
access_secret = '<<access secret>>'
 
# Initialise api
auth = OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_secret)
api = tweepy.API(auth, proxy="<<enter proxy+port here>>")

for status in tweepy.Cursor(api.home_timeline).items(10):
    # Process a single status
    print(status.text)
    
    

 
# Get streaming data 
class MyListener(StreamListener):
 
    def on_data(self, data):
        try:
            with open('python.json', 'a') as f:
                f.write(data)
                return True
        except BaseException as e:
            print("Error on_data: %s" % str(e))
        return True
 
    def on_error(self, status):
        print(status)
        return True
 
twitter_stream = Stream(auth, MyListener())
# Add Twitter track - implement
twitter_stream.filter(track=['<<track>>'])
                             



