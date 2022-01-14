package com.fwkt.commonutils.enums;

public enum ResultEnum {

    UNKNOWN_ERROR(500, "未知错误"),
    UNKNOWN_REFERER(499, "请不重要匿名访问"),
    NO_AUTHORITY(403, "没有此权限,拒绝访问"),

    AUTH_NOT_LOGIN(203, "未登录,请您重新登录"),
    AUTH_REFER_LOGIN(498, "当前登录已失效，请退出重新登录"),
    AUTH_UNKNOWN_ACCOUNT(1001, "当前输入的账户不存在，请检查或联系管理员！"),
    AUTH_USER_LOGIN_ERROR(1002, "用户名密码不正确，请重新输入！"),
    AUTH_VERIFICATION_ERR(1003, "当前验证码已失效"),
    AUTH_VERIFICATION_NULL(1004, "验证码输入为空"),
    AUTH_VERIFICATION_SYS_EQ_ERR(1005, "图形验证码错误"),
    AUTH_VERIFICATION_ALI_EQ_ERR(1006, "手机验证码错误"),
    AUTH_VCODE_DISABLE_ERR(1007, "图形验证码已经失效，请重新获取"),
    AUTH_USER_DELETED(1012, "用户已经注销，请联系管理员"),



    ADMIN_USER_MONILE_IS_EXIST(1008, "手机号已存在"),
    ADMIN_USER_IS_FREEZE(1009, "账号冻结"),
    ADMIN_USER_IS_DELETED(1010, "用户账号已删除"),
    ADMIN_USER_IS_NOTEXIST(1011, "用户不存在"),
    ADMIN_USER_LOGINNAME_EXIST(1012, "登录名已存在"),

    ROLE_IS_NOT_EXIST(4101, "角色不存在"),
    ROLE_IS_FREEZE(4102, "角色已冻结"),
    ROLE_IS_DELETED(4103, "角色已删除"),
    ROLE_IS_EXIST(4104, "角色名已存在"),

    DEPT_IS_NOT_EXIST(4201, "部门不存在"),
    DEPT_IS_ALLOW_UPDATE(4202, "不允许跨级修改部门"),
    DEPT_IS_EXIST(4203, "部门名称已存在，不允许重复"),
    DEPT_HAS_CHIRDREN(4204, "当前部门关联下级部门，不允许删除"),
    DEPT_HAS_USER(4205, "当前部门尚有人员存在，不允许删除"),
    DEPT_ADDRESS_NOT_AREA(4206, "部门地址必须精确到区县"),

    PERMIT_IS_RELEVANCE_LOW(4301, "当前菜单有下级菜单，不允许删除"),
    PERMIT_IS_NOT_EXIST(4302, "当前菜单不存在"),
    PERMIT_TYPE_ERROR(4303, "当前菜单类型范围错误，菜单类型为1：目录 2：菜单 3：按钮"),

    SYS_PARAM_NAME_REPETITION(4401, "参数名重复"),
    SYS_PARAM_IS_ERROR(4402, "系统参数错误，请联系管理员"),
    SYS_PARAM_HSA_CHILDREN(4403, "当前参数包含下级，不允许删除"),

    MENHU_BOLL_IS_NOT_EXIST(4501, "当前友情链接不存在"),
    MENHU_PRODUCT_IS_EXIST(4502, "产品名称重复"),
    MENHU_PRODUCT_IS_NOT_EXIST(4503, "当前产品不存在"),

    FILE_IS_NOT_EXIST(4551, "当前上传文件为空，请确认"),

    FINANCE_COMPANY_TYPE_EXIST(4601, "金融机构业务类型已存在"),
    FINANCE_COMPANY_NAME_EXIST(4602, "金融机构名称已存在"),
    FINANCE_COMPANY_PRODUCT_EXIST(4603, "金融机构产品名称已存在"),
    FINANCE_COMPANY_RATE_EXIST(4604, "当前省或市费率配置已存在，请勿重复添加"),
    FINANCE_COMPANY_RATE_IS_FORBIDDEN(4605, "当前城市费率配置已禁用，请联系管理员"),
    FINANCE_COMPANY_RATE_IS_ERROR(4606, "费率配置参数错误，请联系管理员"),

    SYS_ERROR(2001, "操作失败"),
    SYS_DATABASE_ERROR(2002, "数据库异常"),
    SYS_PARAM_ERROR(2003, "参数不合法"),
    SYS_FILTER_PARAM_ERROR(2004, "filter过滤参数错误"),
    SYS_FILE_UPLOAD_FAIL(2005, "filter过滤参数错误"),


    SMS_ERROR(3000, "短信发送失败"),
    SMS_MOBILE_NUMBER_ILLEGAL(3001, "非法手机号"),
    SMS_DAY_LIMIT_CONTROL(3002, "触发日发送限额"),
    SMS_MOBILE_COUNT_OVER_LIMIT(3003, "手机号码数量超过限制"),
    SMS_BUSINESS_LIMIT_CONTROL(3004, "业务限流"),
    SMS_SEND_TOO_FAST(3005, "短信发送过于频繁，请一分钟后重试！"),

    REGION_IS_NOT_EXIST(4001, "当前查询的地区不存在"),
    REGION_IS_NOT_CHECKED(4002, "未选定地区"),
    REGION_NUM_IS_REPETITION(4003, "行政编码重复"),
    REGION_NAME_IS_REPETITION(4004, "地区名称重复"),
    REGION_HAS_CHILDREN(4005, "当前地区有下级，不允许删除"),
    REGION_GRAND_ERROR(4006, "上级地区选择错误，不允许跨级添加"),
    REGION_NOT_ALLOW_ADD(4007, "地区不允许随意添加"),
    REGION_IS_DELETE(4008, "修改后的地区名重复，已删除"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultEnum getMsgByCode(Integer code) {
        for (ResultEnum val : ResultEnum.values()) {
            if (val.code.equals(code)) {
                return val;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
