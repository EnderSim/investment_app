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
            working_text_new = x[curr_index-300:curr_index+100]
            soup = BeautifulSoup(working_text_new, 'html.parser')
            print(soup)
            for link in soup.find_all('a'):
                print(link.get('href'))


    get_new(text)
    soup = BeautifulSoup(text, 'html.parser')
    newes = str(soup.find_all("span"))
    
    k = 0
    while len(result) <= 15 and time.monotonic()-t < 2.5:
        new = get_new(newes[k:k+500])       
        k += 510
        
    return str(result)[1:-1]


news()   


        



        


