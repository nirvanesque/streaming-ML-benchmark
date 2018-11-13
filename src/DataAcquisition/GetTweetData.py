# -*- coding: utf-8 -*-

# ------------------------------------------------------------------
#  Author : Baruch AMOUSSOU-DJANGBAN
#           Data Scientist
# ------------------------------------------------------------------

import tweepy
from tweepy import OAuthHandler
from tweepy import Stream
from tweepy.streaming import StreamListener

# Key Token 
consumer_key = 'dtSibnNrCzluG3VOy3joXyd4V'
consumer_secret = 'l4wl5YCP7IoGfhOYf15VeJEGFd6RiGOLnqFxiXjKetfC3la3CR'
access_token = '2843636385-eVSmWPZ9bUPzX28IzguOeFfK5QQeOrrMSIv4xjK'
access_secret = 'SYaWcVqZdNrwclcS3jsYqYZXdyfUanTaUAomJoIITXj9A'
 
# Initialise api
auth = OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_secret)
api = tweepy.API(auth, proxy="https://proxydigital.jcdecaux.com:8000")

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
twitter_stream.filter(track=['#Jcdecaux'])
                             



