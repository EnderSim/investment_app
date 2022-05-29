import test
from os.path import dirname, join

def getsaved(user,n):
    n = int(n)
    new = test.news().split(",")
    filename = join(dirname(__file__),"saved.txt")

    with open(filename,"r+") as f:
        
        if f.readline() == "start":
            with open(filename,"w") as file:
                file.write(user + "\n")
                file.write(new[n] + "sep")
                
                return "1"
            
        else:
            with open(filename,"r+") as f1:
                user_id = f1.readline()
                #print(user in user_id)
                saved = f1.readline()
                saved_news = saved.split("sep")
                #print("do", len(saved_news))
                if new[n] not in saved_news:
                    if len(saved_news) >= 5:
                        #print("if")
                        with open(filename,"w") as f2:
                            f2.write(user + "\n")
                            while len(saved_news) > 7:
                                saved_news.remove(saved_news[0])
                            #print("posle", len(saved_news))
                            f2.write("sep".join(saved_news) + "sep")
                            f2.write(new[n])
                    else:
                        #print("else")
                        f1.write(new[n] + "sep")
                            
                return str(len(saved_news))





    
            
            
    


        



        


