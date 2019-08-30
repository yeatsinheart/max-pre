public class Test {
    public static void main(String[] args) {
        String host = "http://localhost:8080/";
        System.out.println("用户注册    "+host+"" +"      "+"登录成功，获取token，后续无状态，有注册事件");
        System.out.println("用户登录    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("用户设置支付密码    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("用户修改个人信息    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("用户修改密码    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("用户修改提现银行卡    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");

        System.out.println("用户查看钱包    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");

        System.out.println("用户查看所有资产    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("用户归并资产刷新余额    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");

        System.out.println("用户分页查看钱包成功记录    "+host+"" +"      "+"钱包成功记录");
        System.out.println("用户分页查看投注记录    "+host+"" +"      "+"投注记录");

        System.out.println("用户查看充值方式    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("用户充值，提交订单    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("#用户充值，显示在充值订单    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("用户充值，消息提醒    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");

        System.out.println("用户查看提现限制    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("用户提现，提交订单，校验金额，校验提现，提前扣款    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("用户提现，消息提醒    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");


        System.out.println("用户校验活动资格    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("用户申请活动，活动奖励，申请单    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("用户活动奖励，申请单通过，领取    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("用户活动，消息提醒    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");


        System.out.println("活动列表展示    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");

        /*
         SELECT  * FROM `view` a
 left JOIN `relation_category_view` b on a.`type` = 6 AND a.`id`=b.`category_id`
 left JOIN `view` c on c.`type` = 4 AND c.`id`=b.`view_id`
 where a.`type`=6
        * */
        System.out.println("游戏列表展示    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("轮播图列表展示    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("公告列表展示    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");
        System.out.println("充值列表展示    "+host+"" +"      "+"登录成功，获取token，后续无状态，有登录事件");

    }
}
