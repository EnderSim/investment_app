import requests,time,random
from bs4 import BeautifulSoup
from requests_html import HTMLSession

def sources():
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
        index = random.randint(0,3)
        sources = ["РБК - Новости","ВТБ","Investing","БКС Экспресс"]
        if soup.find("span", class_= new) is not None:
            news = soup.find("span", class_= new).text
            curr_index = x.find(news)
            working_text_new = x[curr_index:curr_index+300]
            sym = working_text_new.find(":")
                    
        return sources[index]

    get_new(text)
    soup = BeautifulSoup(text, 'html.parser')
    newes = str(soup.find_all("span"))
    
    k = 0
    while len(result) <= 10 and time.monotonic()-t < 2.5:
        new = get_new(newes[k:k+500])       
        k += 510
        result.append(new)

    return result
        




        



        


