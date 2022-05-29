from os.path import dirname, join

def savenewid(user_id):
    filename = join(dirname(__file__),"saved.txt")

    with open(filename,"r+") as f:
        if f.readline() == "start":
            with open(filename,"w") as file:
                file.write(user_id + "\n")
                
                return "0"
            
        else:
            with open(filename,"r+") as f1:
                user_id1 = f1.readline()
                #print(user in user_id)
                saved = f1.readline()
                saved_news = saved.split("sep")
                    
                            
                return str(len(saved_news))
                
print(savenewid("211-171"))




    
            
            
    


        



        


