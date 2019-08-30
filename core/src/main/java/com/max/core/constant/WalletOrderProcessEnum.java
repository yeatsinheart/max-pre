package com.max.core.constant;

public enum WalletOrderProcessEnum {
    /**
     * 顺利流程
     * 流程一旦变化，所有老数据都将按照新流程走，
     * 流程最好是在两个老流程之间插入
     * */

    /*流程必须把控好*/
    /**
     * 提交订单
     */
    ZERO(OrderProcessEnum.SUBMIT.getCode(), OrderProcessResultEnum.SURE.getCode(), OrderProcessEnum.FENGKONG),
    ZERO_FAIL(OrderProcessEnum.SUBMIT.getCode(), OrderProcessResultEnum.FAIL.getCode(), OrderProcessEnum.FAIL),
    /**
     * 分控审核通过
     */
    ONE(OrderProcessEnum.FENGKONG.getCode(), OrderProcessResultEnum.SURE.getCode(), OrderProcessEnum.CAIWU),
    ONE_FAIL(OrderProcessEnum.FENGKONG.getCode(), OrderProcessResultEnum.FAIL.getCode(), OrderProcessEnum.FAIL),
    /**
     * 财务审核通过
     */
    TWO(OrderProcessEnum.CAIWU.getCode(), OrderProcessResultEnum.SURE.getCode(), OrderProcessEnum.OPERATION),
    TWO_FAIL(OrderProcessEnum.CAIWU.getCode(), OrderProcessResultEnum.FAIL.getCode(), OrderProcessEnum.FAIL),
    /**
     * 最后支付操作确认完成
     */
    THREE(OrderProcessEnum.OPERATION.getCode(), OrderProcessResultEnum.SURE.getCode(), OrderProcessEnum.FINISH),
    THREE_FAIL(OrderProcessEnum.OPERATION.getCode(), OrderProcessResultEnum.FAIL.getCode(), OrderProcessEnum.FAIL),

    /**
     * 最后支付操作确认完成
     */
    CANCEL(OrderProcessEnum.CANCEL.getCode(), OrderProcessResultEnum.WAITING.getCode(), OrderProcessEnum.FAIL);

    private int process;
    private int result;
    private OrderProcessEnum value;
    WalletOrderProcessEnum(int process, int result, OrderProcessEnum value) {
        this.process = process;
        this.result = result;
        this.value = value;
    }

    //成功获取下一流程
    public static OrderProcessEnum nextProcess(int code, int result) {
        for (WalletOrderProcessEnum ele : values()) {
            if (ele.getProcess() == code) {
                if (ele.getResult() == result) {
                    return ele.getValue();
                }
            }
        }
        return OrderProcessEnum.NULL;
    }

    /*充值订单初始化环节*/
    public static OrderProcessEnum getProcessOfStart(Integer type) {
        if (type == WalletOrderTypeEnum.RECHARGE.getCode()) {
            return OrderProcessEnum.OPERATION;
        }
        if (type == WalletOrderTypeEnum.WIDTHDRAW.getCode()) {
            return OrderProcessEnum.FENGKONG;
        }
        if (type == WalletOrderTypeEnum.TRANSFER_IN.getCode()) {
            return OrderProcessEnum.OPERATION;
        }
        if (type == WalletOrderTypeEnum.TRANSFER_OUT.getCode()) {
            return OrderProcessEnum.OPERATION;
        }
        //todo 最后这个异常状态，对流程有多大影响
        return OrderProcessEnum.NULL;
    }

    public int getResult() {
        return result;
    }

    public int getProcess() {
        return process;
    }

    public OrderProcessEnum getValue() {
        return value;
    }

    public enum OrderProcessEnum {
        //todo 流程是人工操作还是系统自动操作。
        //todo 默认都是人工，修改状态
        //todo 修改状态，如果操作失败，是否会有重试？自动操作两次？还是自循环到成功？
        /*订单正常过程*/
        SUBMIT(0, "提交订单"),
        FENGKONG(10, "风控审核"),
        CAIWU(20, "财务审核"),
        OPERATION(30, "三方确认失败/财务出款失败"),

        /*人工撤销*/
        CANCEL(97, "人工撤单"),



        /*最终状态*/
        FINISH(88, "成功结束"),
        FAIL(98, "失败"),
        NULL(99, "未知流程");
        private Integer code;
        private String value;

        OrderProcessEnum(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    public enum OrderProcessResultEnum {
        WAITING(0, "等待处理"),
        SURE(1, "成功"),
        FAIL(2, "失败");
        private Integer code;
        private String value;

        OrderProcessResultEnum(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }
}



