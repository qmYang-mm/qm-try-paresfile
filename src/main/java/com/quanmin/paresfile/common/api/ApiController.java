package com.quanmin.paresfile.common.api;

import cn.hutool.core.io.IoUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.quanmin.paresfile.common.LoginUser;
import com.quanmin.paresfile.util.JacksonUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;

import static cn.hutool.core.util.CharsetUtil.UTF_8;
import static com.quanmin.paresfile.GlobalConstants.SESSION_KEY;


public class ApiController {

    protected static final Integer EMPTY_ID = 0;

    private static final String DEFAULT_SUCCESS_MESSAGE = "ok";
    private static final String DEFAULT_FAILED_MESSAGE = "error";

    protected Result success() {
        return Result.success(DEFAULT_SUCCESS_MESSAGE, DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 获得Request
     *
     * @return HttpServletRequest
     */
    protected final HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    /**
     * 获得Response
     *
     * @return HttpServletResponse
     */
    protected final HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getResponse();
    }

    /**
     * 获得当前的session
     */
    protected final HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获得当前登录的用户
     *
     * @return 登录用户
     */
    protected LoginUser getLoginUser() {
        HttpSession session = getSession();
        if (session == null) {
            return null;
        }
        return (LoginUser) session.getAttribute(SESSION_KEY);
    }


    /**
     * 获得当前登录的用户的ID
     */
    protected Integer getLoginUserId() {
        LoginUser user = getLoginUser();
        return user != null ? user.getId() : -1;
    }

    protected String getLoginUserName() {
        LoginUser user = getLoginUser();
        return user != null ? user.getNickname() : "";
    }

    protected Result response(boolean isOK) {
        if (isOK) {
            return Result.success(DEFAULT_SUCCESS_MESSAGE, DEFAULT_SUCCESS_MESSAGE);
        } else {
            return Result.failed(ResultCode.FAIL, DEFAULT_FAILED_MESSAGE);
        }
    }

    /**
     * 请求成功
     *
     * @param data 返回的数据
     * @return ignore
     */
    protected <T> Result<T> success(T data) {
        return Result.success(data, DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 请求成功
     *
     * @param data    返回的数据
     * @param message 返回的消息
     * @return ignore
     */
    protected <T> Result<T> success(T data, String message) {
        return Result.success(data, message);
    }


    /**
     * 默认失败返回
     *
     * @return ignore
     */
    protected <T> Result<T> failed() {
        return Result.failed(ResultCode.FAIL, null, DEFAULT_FAILED_MESSAGE);
    }


    protected Result failed(String message) {
        return Result.failed(ResultCode.FAIL, message);
    }

    protected <T> Result<T> failed(T data) {
        return Result.failed(ResultCode.FAIL, data, DEFAULT_FAILED_MESSAGE);
    }

    /**
     * 请求失败，带错误码
     *
     * @param code    错误码
     * @param message 消息
     * @return ignore
     */
    protected Result failed(ResultCode code, String message) {
        return Result.failed(code, message);
    }

    /**
     * 请求失败，带错误码和数据
     *
     * @param code    错误码
     * @param data    数据
     * @param message 返回消息
     * @param <T>     返回的数据类型
     * @return ignore
     */
    protected <T> Result<T> failed(ResultCode code, T data, String message) {
        return Result.failed(code, data, message);
    }

    /**
     * 模拟返回数据，从服务器读取json数据，模拟返回。
     *
     * @param mockDataFile mock文件的名字
     * @return ignore
     */
    protected Result mocked(String mockDataFile) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("mocks/" + mockDataFile);
        String jsonStr = IoUtil.read(inputStream, UTF_8);

        JsonNode jsonNode = JacksonUtils.parseJson(jsonStr);
        return Result.success(jsonNode, "warning: this is mock data from api server!");
    }
}
