import requests,time
from bs4 import BeautifulSoup
from requests_html import HTMLSession

def news():
    t = time.monotonic()
    session = HTMLSession()
    result = []
        
    link = "https://quote.rbc.ru"
    response = requests.get(link)
    text = response.text
    #print(text) вывод html кода страницы

    def get_new(x):
        soup = BeautifulSoup(x, 'html.parser')
        new = "news-feed__item__title"
        if soup.find("span", class_= new) is not None:
            news = soup.find("span", class_= new).text
            curr_index = x.find(news)
            working_text_new = x[curr_index:curr_index+300]
            sym = working_text_new.find(":")
            
            return news
            #return news, working_text_new[sym-2:sym+3] #возвращение текста новости и даты

    get_new(text)
    soup = BeautifulSoup(text, 'html.parser')
    newes = str(soup.find_all("span"))
    
    k = 0
    while len(result) <= 15 and time.monotonic()-t < 2.5:
        new = get_new(newes[k:k+500])       
        if new is not None and len(new) >= 3:
            new = new[21:-17]
            new = new.replace(",",".")
            new = new.replace("\xa0"," ")
            result.append(new)
            #print(new)
        k += 510
        
    return str(result)[1:-1]


#print(len(news().split(",")))


        



        


