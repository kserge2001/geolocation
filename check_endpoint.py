import requests

url_set = ['https://www.google.com', 'https://www.netflix.com', 'http://uber.net', 'http://upmenu.org', 'https://www.youtube.com']

for _url in url_set:
    try:
        response = requests.get(_url)
        _code = response.status_code
        if _code == 200:
            print(f"{_url} is up and running")
        else: 
            print(f"{_url} is down!!")
    except:
        print(f"The url of {_url} is not good")
