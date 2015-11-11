package sddtc.fanji.controller;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sddtc.fanji.service.RedisServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * sddtc's One Page App
 * Created by sddtc on 15/11/2.
 */
@Controller
@RequestMapping(value="/sddtc")
public class FixedSearchController extends BaseController{
    @Autowired
    private RedisServiceImpl redisService;

    /**
     * bilibili.tv search with stable words
     * @return
     */
    @RequestMapping(value="/video")
    @ResponseBody
    public String wordsSearchAtBilibli() {
        String biliSearchUrl = "http://www.bilibili.com/search?keyword=%E5%89%91%E4%B8%89&orderby=click&page=1";

        String result = redisService.get(biliSearchUrl);

        if(Strings.isNullOrEmpty(result)) {

            result = biliSoup(biliSearchUrl);
            redisService.set(biliSearchUrl, result);

        }

        return result;
    }

    /**
     * 获取bili search result
     * @param url
     * @return
     */
    private String biliSoup(String url) {
        Gson gson = new Gson();
        List<JsonObject> list = new ArrayList<JsonObject>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select(".r_sp");
            for(int i=0;i<elements.size();i++) {
                JsonObject jsonObject = new JsonObject();

                String result = elements.get(i).getElementsByClass("t").text();
                String resultHref = elements.get(i).childNodes().get(0).attributes().get("href").toString();

                if(!Strings.isNullOrEmpty(result)) {
                    jsonObject.addProperty("title", result);
                    jsonObject.addProperty("url", resultHref);
                }

                list.add(jsonObject);
            }
        } catch (IOException ex) {
            CONTROLLER_LOG.error("Jsoup Error:{}", ex.getMessage());
            return "ops...error";
        }

        return gson.toJson(list);
    }
}
