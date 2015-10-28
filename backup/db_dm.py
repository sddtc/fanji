#!usr/bin/env python
'''get data from doubanAPI'''

import urllib2
import json
import MySQLdb

conn=MySQLdb.connect(host='localhost',user='root',passwd='root',port=3306,charset='utf8')
cur=conn.cursor()
conn.select_db('fanji')
q='%E5%8A%A8%E7%94%BB'

def save2database(movies):
    print 'save movies readying...'
    for movie in movies:
        m_id=movie['id']
        otitle=movie['original_title']
        title=movie['title']
        alt=movie['alt']
        
        images=movie['images']
        l_image=images['large']
        m_image=images['medium']
        s_image=images['small']

        rating=movie['rating']
        min_rating=rating['min']
        max_rating=rating['max']
        aver=rating['average']

        year=movie['year']
        stype=movie['subtype']

        try:
            cur.execute('insert into movies values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)', (m_id,otitle,title,alt,l_image,m_image,s_image,min_rating,max_rating,aver,year,stype))
        except MySQLdb.Error,e:
            print 'MySQL Error %d,%s' %(e.args[0],e.args[1])
    conn.commit()
    print 'submit database success...'

def saveMoviesFromPage1ToEnd(pages):
    for page in range(303,pages+1):
        url=r'http://api.douban.com/v2/movie/search?q=%s&start=%d&count=20' % (q,page*20)
        req=urllib2.urlopen(url)
        print 'get %d movies success...' %page
        data=req.read()
        jdata=json.loads(data)
        movies=jdata['subjects']
        save2database(movies)

def main():
    print 'connect database fanji...'
    url=r'http://api.douban.com/v2/movie/search?q=%s&start=0&count=20' %q
    req = urllib2.urlopen(url)
    total_data = req.read()
    jtotal_data = json.loads(total_data)
    total_count = jtotal_data['total']
    print 'movies counts:%d' %total_count
    pages=total_count/20 
    arr=jtotal_data['subjects']
#    save2database(arr)

    saveMoviesFromPage1ToEnd(pages);

    cur.close()
    conn.close()

    print 'fanji movies get success...'

if __name__=="__main__":
    main()
