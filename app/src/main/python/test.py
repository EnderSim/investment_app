import yfinance as yf



def plot():
    msft = yf.Ticker("MSFT")
    msft_hist = msft.history(period="max")
    msft_hist.plot.line(y="Close", use_index=True)
    return msft_hist