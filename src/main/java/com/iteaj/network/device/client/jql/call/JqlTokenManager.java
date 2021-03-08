package com.iteaj.network.device.client.jql.call;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JqlTokenManager {

    private Map<String, Token> tokenMap = new HashMap<>();
    private static JqlTokenManager instance = new JqlTokenManager();
    // 过期20分钟, 转成毫秒数
    private long expireTime = TimeUnit.MINUTES.toMillis(18);

    protected JqlTokenManager() {}

    public static JqlTokenManager getInstance() {
        return instance;
    }

    public String getToken(String url) {
        final Token token = tokenMap.get(url);
        if(token == null) {
            return null;
        }

        if(token.isExpire()) {
            this.tokenMap.remove(url);
            return null;
        }

        return token.getToken();
    }

    public String removeToken(String url) {
        final Token remove = this.tokenMap.remove(url);
        return remove.getToken();
    }

    public void addToken(String url, String token) {
        synchronized (instance) {
            tokenMap.put(url, new Token(token));
        }

        validateExpireToken();
    }

    private void validateExpireToken() {
        final Iterator<Token> iterator = tokenMap.values().iterator();
        while (iterator.hasNext()) {
            final Token next = iterator.next();
            if(next.isExpire()) {
                iterator.remove();
            }
        }
    }

    public String getIp(String url) {
        return url;
    }

    protected class Token {

        private String token;
        private long createMill;

        public Token(String token) {
            this.token = token;
            this.createMill = System.currentTimeMillis();
        }

        /**
         * 是否过期
         * @return
         */
        public boolean isExpire() {
            return (System.currentTimeMillis() - this.createMill) > expireTime;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public long getCreateMill() {
            return createMill;
        }

        public void setCreateMill(long createMill) {
            this.createMill = createMill;
        }
    }
}
