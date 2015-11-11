package redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sddtc on 15/11/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;
    String biliSearchUrl = "http://www.bilibili.com/search?keyword=%E5%89%91%E4%B8%89&orderby=click&page=1";
    String result="{\"title\":\"实况解说 【剑三群像】江湖意 正式版\",\"url\":\"http://www.bilibili.com/video/av906051/\"},{\"title\":\"实况解说 【剑网三】田螺打奶+地鼠门绝恋（上）剑三讲坛：揭秘地鼠门\",\"url\":\"http://www.bilibili.com/video/av2092206/\"},{\"title\":\"网络游戏 【帮主扒点档】第一期：剑三真好玩（1）\",\"url\":\"http://www.bilibili.com/video/av2487069/\"},{\"title\":\"实况解说 剑三恶人谷《杀伐》归墟原创曲\",\"url\":\"http://www.bilibili.com/video/av949686/\"},{\"title\":\"网络游戏 【沐帮主】最长绕口令串烧剑三版（哭着也要念完）\",\"url\":\"http://www.bilibili.com/video/av2221221/\"},{\"title\":\"网络游戏 【帮主扒点档】第三期：剑三真好玩（2）\",\"url\":\"http://www.bilibili.com/video/av2568373/\"},{\"title\":\"原创动画 【剑三小故事】我是丐帮不打奶花[第二章]\",\"url\":\"http://www.bilibili.com/video/av1774190/\"},{\"title\":\"翻唱 【剑三】奇妙的约会-丐帮打本\",\"url\":\"http://www.bilibili.com/video/av1956959/\"},{\"title\":\"原创动画 【剑三小故事】我是丐帮不打奶花[第一章]（新语联盟）\",\"url\":\"http://www.bilibili.com/video/av1628382/\"},{\"title\":\"翻唱 【剑三】我知道pvp的你们一定会有这样的错觉（后面的朋友，举起你的膝盖好吗\",\"url\":\"http://www.bilibili.com/video/av1949377/\"},{\"title\":\"翻唱 【剑三】天策爬山- 一只哈士奇\",\"url\":\"http://www.bilibili.com/video/av1966707/\"},{\"title\":\"翻唱 【剑三】苍云——跪下叫爸爸（该来的还是回来）\",\"url\":\"http://www.bilibili.com/video/av1976717/\"},{\"title\":\"实况解说 【剑网三】剑三全门派内部消化，一门派一首歌\",\"url\":\"http://www.bilibili.com/video/av1980611/\"},{\"title\":\"ACG相关舞蹈 【Sakuya】剑三七秀COS❤礼仪之邦\",\"url\":\"http://www.bilibili.com/video/av1562122/\"},{\"title\":\"原创动画 【剑三小故事】我是丐帮不打奶花[第三章]\",\"url\":\"http://www.bilibili.com/video/av1977951/\"},{\"title\":\"翻唱 【剑三】---藏剑做人\",\"url\":\"http://www.bilibili.com/video/av1972709/\"},{\"title\":\"原创动画 【剑三小故事】我是丐帮不打奶花[番外]\",\"url\":\"http://www.bilibili.com/video/av1849201/\"},{\"title\":\"实况解说 [剑三][莫毛]时间煮酒（代投）\",\"url\":\"http://www.bilibili.com/video/av649060/\"},{\"title\":\"实况解说 【剑网3】F/Z×JX3【To the beginning】我的剑三不可能这么燃\",\"url\":\"http://www.bilibili.com/video/av471775/\"},{\"title\":\"日常 剑三同人九门派全御姐燃系MV《红颜绝》\",\"url\":\"http://www.bilibili.com/video/av1158975/\"}";

    @Test
    public void test() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//        valueOperations.set(biliSearchUrl, "hei");

        System.out.println("save it");

        System.out.println("then println it");
        System.out.println(valueOperations.get(biliSearchUrl));
    }
}
