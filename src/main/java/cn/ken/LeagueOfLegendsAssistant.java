package cn.ken;

import cn.ken.bo.LeagueClientBO;
import cn.ken.service.LeagueClientApi;
import cn.ken.util.LeagueClientUtil;
import cn.ken.util.RequestUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/Ken-Chy129">Ken-Chy129</a>
 * @since ${DATE} ${TIME}
 */
public class LeagueOfLegendsAssistant {
    public static void main(String[] args) throws IOException {
        LeagueClientBO leagueClientBO = LeagueClientUtil.getLeagueClientBO();
        RequestUtil requestUtil = new RequestUtil(leagueClientBO);
        LeagueClientApi api = new LeagueClientApi(requestUtil);
        System.out.println(requestUtil.doGet("/lol-summoner/v1/current-summoner"));
    }
}
