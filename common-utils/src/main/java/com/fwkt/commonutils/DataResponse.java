package com.fwkt.commonutils;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * 带数据的Web响应模型
 *
 * @param <T> 数据类型
 * @see WebResponse
 */
@Data
@ApiModel(description = "带数据的Web响应模型")
@ToString
public class DataResponse<T> extends WebResponse {

    public DataResponse() {
        super();
    }

    /**
     * 生成响应模型
     *
     * @param data 响应数据
     */
    DataResponse(T data) {
        this(SUCCESS, data);
    }

    /**
     * 生成响应模型
     *
     * @param code 响应码
     * @param data 响应数据
     */
    public DataResponse(String code, T data) {
        super.setCode(code);
        this.data = data;
    }

    /**
     * 生成响应模型
     *
     * @param code 响应码
     */
    public DataResponse(String code, String msg) {
        super.setCode(code);
        if (!StringUtils.isBlank(msg)) {
            super.setMessage(msg);
        }
    }

    /**
     * 生成响应模型
     *
     * @param code 响应码
     */
    public DataResponse(String code) {
        super.setCode(code);
    }

    public static DataResponse error(String message) {
        DataResponse response = new DataResponse();
        response.setCode(FAILD);
        response.setMessage(message);
        return response;
    }

    @ApiModelProperty(value = "数据")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
