package com.quanmin.paresfile.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * @description 登录校验Token
 */
@Slf4j
public class TokenUtil {

    /**
     * 密钥
     */
    private static String TOKEN_CODE = "guo@)$kang402";

    /**
     * token过期时间,单位是秒,默认30天
     */
    private static final long TOKEN_EXPIRE_TIME = 24 * 60 * 60 * 30;

    /**
     * 校验成功
     */
    public static int VERIFY_SUCCESS = 0;

    /**
     * 校验不匹配
     */
    public static int VERIFY_NOT_MATCH = 1;

    /**
     * 登录过期
     */
    public static int VERIFY_LOGON_TIMEOUT = 2;


    /**
     * 创建token
     */
    public static String createToken(String id) {
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        String md5 = Md5Util.md5s32(time + TOKEN_CODE);
        return md5 + time + id;
    }


    /**
     * 校验Token信息
     */
    public static TokenParsedResult parseTokenStr(String token) {
        TokenParsedResult result = new TokenParsedResult();
        if (token == null) {
            log.error("token is null");
            result.setParseResult(VERIFY_NOT_MATCH);
        } else {
            int length = token.length();
            if (length < 43) {
                log.error(StrUtil.format("token's length error:{}",token));
                result.setParseResult(VERIFY_NOT_MATCH);
            } else {
                try {
                    String md5 = token.substring(0, 32);
                    String timeS = token.substring(32, 42);
                    String id = token.substring(42);
                    long time = Long.parseLong(timeS);
                    if (System.currentTimeMillis() / 1000 - time > TOKEN_EXPIRE_TIME) {
                        log.error(StrUtil.format(
                                "token already timeout: systemtime={},tokentime={},token={}",
                                System.currentTimeMillis() / 1000,time,token));
                        result.setParseResult(VERIFY_LOGON_TIMEOUT);
                    } else {
                        if (Md5Util.md5s32(timeS + TOKEN_CODE).equals(md5)) {
                            result.setParseResult(VERIFY_SUCCESS);
                            result.setId(id);
                        } else {
                            log.error(
                                    StrUtil.format("token not match error:{}", token));
                            result.setParseResult(VERIFY_NOT_MATCH);
                        }
                    }
                } catch (Exception e) {
                    log.error(
                            StrUtil.format("we met exception in analyzing token,token={}",
                                    token), e);
                    result.setParseResult(VERIFY_NOT_MATCH);
                }
            }
        }
        return result;
    }

    /**
     * Copyright © 2013 国康健康管理服务有限公司.<br> All rights reserved.
     *
     * @author Emrys
     * @description Token解析结果
     * @since 2013年9月14日 下午2:07:51
     */
    public static class TokenParsedResult {

        /**
         * 解析结果
         */
        private int parseResult;
        /**
         * 用户id
         */
        private String Id;


        public int getParseResult() {
            return parseResult;
        }

        public void setParseResult(int parseResult) {
            this.parseResult = parseResult;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }
    }


}
