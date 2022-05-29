import test,requests,re

def get_news():
    link = "https://quote.rbc.ru"
    response = requests.get(link)
    text = response.text
    news = []
    k = 0
    for i in range(10):
        text = text[k:-1]
        if 'news-feed__item__title"' in text:
            k = text.find('news-feed__item__title"')+500
            index= text.find('news-feed__item__title')
            result = text[index:index+350]
            result = result[result.find(">")+1:result.find("</span>")]
            result = result.replace(" " * 20,"")
            result = result.replace("\n","")
            result = result.replace("                ","")
            result = result.replace(",",".")
            news.append(result)

    return str(news)[1:-1]
        
            
print(get_news())
