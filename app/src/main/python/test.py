import yfinance as yf
from datetime import datetime



def plot():
    msft = yf.download("MSFT", start= datetime(2020,1,1), end = datetime(2022,1,1),interval='1d')['Close']
    return msft