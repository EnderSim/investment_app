import requests,datetime
from bs4 import BeautifulSoup
from requests_html import HTMLSession
import test,test1

def getsrc(x):
    x = int(x)
    new = test.news().split(",")
    now = datetime.datetime.now()
    with open("src.txt","r+") as fil:
        res = fil.readline()
        if res == "start":
            with open("src.txt","w") as file:
                file.write(str(now.year) + " ")
                file.write(str(now.month) + " ")
                file.write(str(now.day) + " ")
                file.write(str(now.hour) + " ") 
                file.write(str(len(new)) + " ")
                time_src = [str(now.year),str(now.month),str(now.day),str(now.hour)]
                motion = True
        else:
            time_src = res.split()
            #print(time_src)
            motion = False


        
    time = [str(now.year),str(now.month),str(now.day),str(now.hour)]
    #print(time)
    
    if time_src[0] != time[0] or time_src[1] != time[1] or time_src[2] != time[2] or abs(int(time_src[3])-int(time[3])) >= 24 or motion == True:
        #news = dict() #news dictionary new - source
        sources = test1.sources()
        with open("src.txt","w") as f1:
            f1.write(str(now.year) + " ")
            f1.write(str(now.month) + " ")
            f1.write(str(now.day) + " ")
            f1.write(str(now.hour) + " ") 
            f1.write(str(len(new)) + "\n")
            for elem in sources:
                f1.write(elem + " % ")
                
        return str(sources)[1:-1]
    else:
        with open("src.txt","r+") as f2:
            date = f2.readline()
            srcs = f2.readline()
           
            
            
            return srcs.split("%")[x]
            
            
            
            

            
#print(getsrc())


        



        


