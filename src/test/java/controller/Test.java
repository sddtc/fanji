package controller;

import com.google.common.base.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by sddtc on 15/10/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-db.xml"})
public class Test {

    @org.junit.Test
    public void test() {
        try {
            Document du = Jsoup.connect("http://www.bilibili.com/search?keyword=%E5%89%91%E4%B8%89&orderby=click&page=1").get();
            Elements el = du.select(".r_sp");

            for(int i=0;i<el.size();i++) {
                String result = el.get(i).getElementsByClass("t").text();
                String resultHref = el.get(i).childNodes().get(0).attributes().get("href").toString();
                if(!Strings.isNullOrEmpty(result)) {
                    System.out.println(result);
                    System.out.println(resultHref);
                }
            }
        } catch (IOException ex) {
            System.out.print("IOException");
        }
    }

}
