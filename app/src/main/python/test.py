import yfinance as yf
from datetime import datetime


def get_price():
    return yf.download("MSFT", start=datetime(2020, 1, 1), end=datetime(2022, 1, 1), interval='1d')


def plot():
    msft = get_price()
    result = msft['Close']
    print(date())
    return result


def date():
    msft = get_price().index
    return msft.format()


if __name__ == '__main__':
    plot()
