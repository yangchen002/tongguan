package com.fwkt.commonutils;

import com.fwkt.commonutils.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * web响应
 */
@Data
@ApiModel(description= "返回响应数据")
public class WebResponse implements Serializable {

    /**
     * 默认成功的响应码 -- "{@value}"
     */
    public static final String SUCCESS = "0000";
    /**
     * 默认失败的响应码 -- "{@value}"
     */
    public static final String FAILD = "9999";

    /**
     * 响应成功
     *
     * @return WebResponse
     */
    public static WebResponse success() {
        WebResponse response = new WebResponse();
        response.setCode(SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    /**
     * 响应成功
     * @param msg 响应信息
     */
    public static WebResponse success(String msg) {
        WebResponse response = new WebResponse();
        response.setCode(SUCCESS);
        response.setMessage(msg);
        return response;
    }

    /**
     * 成功响应数据模型
     * <p>默认成功响应码 {@value SUCCESS }</p>
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return DataResponse {@link DataResponse}
     */
    public static <T> DataResponse<T> success(T data) {
        return new DataResponse<T>(data);
    }

    /**
     * 响应数据模型
     *
     * @param code 响应码
     * @param data 相应数据
     * @param <T>  数据类型
     * @return DataResponse {@link DataResponse}
     */
    public static <T> DataResponse<T> success(String code, T data) {
        return new DataResponse<T>(code, data);
    }

    /**
     * 响应分页数据
     *
     * @param list        分页数据集合
     * @param currentPage 当前页码
     * @param totalNum    总记录数
     * @param pageSize    每页记录数
     * @param <T>         数据模型
     * @return {@linkplain PagingResponse PagingResponse}
     */
    public static <T> PagingResponse<T> response(List<T> list, int currentPage, long totalNum, int pageSize) {
        PagingResponse<T> response = new PagingResponse<T>(list, currentPage, totalNum, pageSize);
        return response;
    }

    /**
     * 错误响应
     * <p>响应错误码 {@value FAILD}</p>
     *
     * @return WebResponse
     */
    public static WebResponse error() {
        WebResponse response = new WebResponse();
        response.setCode(FAILD);
        return response;
    }


    public static WebResponse error(Object obj) {
        DataResponse response = new DataResponse();
        response.setCode(FAILD);
        response.setMessage("请求失败!");
        response.setData(obj);
        return response;
    }

    /**
     * 错误响应
     * <p>响应错误码 {@value FAILD}</p>
     *
     * @param message 错误信息
     * @return WebResponse
     */
    public static WebResponse error(String message) {
        WebResponse response = new WebResponse();
        response.setCode(FAILD);
        response.message = message;
        return response;
    }

    public static WebResponse error(String code,String message) {
        WebResponse response = new WebResponse();
        response.setCode(code);
        response.message = message;
        return response;
    }


    public static WebResponse error(ResultEnum resultEnum) {
        WebResponse response = new WebResponse();
        response.setCode(resultEnum.getCode().toString());
        response.message = resultEnum.getMessage();
        return response;
    }



    /**
     * 根据异常响应错误信息
     * <p>响应错误码 {@value FAILD}</p>
     *
     * @param t 异常
     * @return WebResponse
     */
    public static WebResponse error(Throwable t) {
        if (StringUtils.isNotEmpty(t.getMessage())) {
            return WebResponse.error(t.getMessage());
        }
        return WebResponse.error();
    }


    /**
     * 生成响应
     * <p>包私有构造函数，禁止外部代码实例化</p>
     */
    public WebResponse() {
    }

    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "返回信息")
    private String message;

    public String getCode() {
        return code;
    }

    public WebResponse setCode(String code) {
        this.code = code;
        return this;
    }

    public WebResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return message;
    }


    public  boolean checkSuccess(){
        if(this.getCode().equalsIgnoreCase(SUCCESS)){
            return true;
        }
        return false;
    }

}
