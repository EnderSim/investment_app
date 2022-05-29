import requests,re

def get_data(key):
    key = int(key)
    link = "https://quote.rbc.ru"
    response = requests.get(link)
    text = response.text
    result = re.findall("https://quote.rbc.ru/news/short_article/[\w]{15,}", text)
    time1 = list()
    # https://quote.rbc.ru/news/short_article/6291e37a9a79470115387c57?from=newsfeed
    for elem in result:
        response = requests.get(elem)
        text = response.text
        if '<a target="_blank"  href="' in text:
            index= text.find('<a target="_blank"  href="')
            index_time = text.find('</span>&nbsp')
            time = text[index_time-13 : index_time]
            res_text = str()
            k = True
            for elem in text: # первичная обработка текста
                if elem != "<" and elem != ">" and k == True:
                    res_text += elem
                elif elem == "<" or elem == "&":
                    k = False
                elif elem == ">" or elem == ";":
                    k = True
                    
            ress = str() # вторичная обработка текста
            for elem in res_text:
                if elem != "&" and elem != ";" and k == True:
                    ress += elem
                elif elem == "&":
                    k = False
                elif elem == ";":
                    k = True
            ress = ress.split()
            data = str()
            for elem in ress:
                data += elem
                data += " "
                
            time1.append(time)
                
    return time1[key]

#print(get_text(0))
