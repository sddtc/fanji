package sddtc.fanji.controller;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sddtc on 15/11/2.
 */
@Controller
@RequestMapping(value="/sddtc")
public class FixedSearchController extends BaseController{

    /**
     * bilibili.tv search with stable words
     * @return
     */
    @RequestMapping(value="/video")
    @ResponseBody
    public String wordsSearchAtBilibli() {
        Gson gson = new Gson();
        List<JsonObject> list = new ArrayList<JsonObject>();
        try {
            Document document = Jsoup.connect("http://www.bilibili.com/search?keyword=%E5%89%91%E4%B8%89&orderby=click&page=1").get();
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
        }

        return gson.toJson(list);
    }
}
