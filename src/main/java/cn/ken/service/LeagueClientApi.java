package cn.ken.service;

import cn.ken.bo.SummonerInfoBO;
import cn.ken.util.RequestUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/Ken-Chy129">Ken-Chy129</a>
 * @since 2024-04-22 19:22
 */
public class LeagueClientApi {

    private final RequestUtil requestUtil;

    public LeagueClientApi(RequestUtil requestUtil) {
        this.requestUtil = requestUtil;
    }
    
    /**
     * 获取登录用户信息
     */
    public SummonerInfoBO getCurrentSummoner() throws IOException {
        String resp = requestUtil.doGet("/lol-summoner/v1/current-summoner");
        return JSON.parseObject(resp, SummonerInfoBO.class);
    }

    public String msg2Room(String roomId, String msg) throws IOException {
        JSONObject body = new JSONObject(2);
        body.put("body", msg);
        body.put("type", "chat");
        String endpoint = "/lol-chat/v1/conversations/" + roomId + "/messages";
        return requestUtil.doPost(endpoint, body.toJSONString());
    }
}
