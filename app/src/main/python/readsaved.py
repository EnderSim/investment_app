from os.path import dirname, join

def readsaved():
    filename = join(dirname(__file__),"saved.txt")

    with open(filename,"r+") as f1:
        user_id1 = f1.readline()
        saved = f1.readline()
        saved_news = saved.split("sep")
                            
        return saved_news

print(readsaved())



    
            
            
    


        



        


