package cn.ken.util;

import cn.ken.bo.LeagueClientBO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/Ken-Chy129">Ken-Chy129</a>
 * @since 2024-04-22 18:08
 */
public class LeagueClientUtil {
    
    private static final String LEAGUE_CLIENT_PATH = "D:\\wegame\\英雄联盟(26)\\LeagueClient\\";

    private static Pattern appPortPattern = Pattern.compile("--app-port=(\\d+)");

    private static Pattern tokenPattern = Pattern.compile("--remoting-auth-token=([\\w-]+)");
    
    private static String port;
    
    private static String token;
    
    public static LeagueClientBO getLeagueClientBO() {
        LeagueClientBO leagueClientBO = new LeagueClientBO();
        String currentFileName = FileUtil.filesInDir(LEAGUE_CLIENT_PATH)
                .stream()
                .filter(fileName -> fileName.endsWith("_LeagueClientUx.log"))
                .map(filename -> LEAGUE_CLIENT_PATH + filename)
                .max(Comparator.comparingInt(FileUtil::getFileLastModifiedTime))
                .orElseThrow(() -> new RuntimeException("未找到LeagueClientUx.log文件"));
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(currentFileName), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher appPortMatcher = appPortPattern.matcher(line);
                Matcher tokenPatternMatcher = tokenPattern.matcher(line);
                if (tokenPatternMatcher.find()) {
                    leagueClientBO.setToken(tokenPatternMatcher.group(1));
                }
                if (appPortMatcher.find()) {
                    leagueClientBO.setPort(appPortMatcher.group(1));
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file:");
            e.printStackTrace();
        }
        return leagueClientBO;
    }
    
}
