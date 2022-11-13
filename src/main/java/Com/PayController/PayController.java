package Com.PayController;

import Com.PayConf.PayCongfig;
import Com.pojo.Pay;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("index")
public class PayController {
    @ResponseBody // 返回参数是json格式，必须加
    @PostMapping("/pay")
    public String get(Pay pay) throws AlipayApiException {
      //  PayCongfig payCongfig = new PayCongfig();

        AlipayClient alipayClient = new DefaultAlipayClient(
                PayCongfig.URL, //请求网关
                PayCongfig.APPID, //收款人ID
                PayCongfig.PSA_KEY, //支付宝私钥
                PayCongfig.FORMAT, //返回格式（json）
                PayCongfig.CHARSET, //编码集（UTF-8）
                PayCongfig.ALLPAYKEY, //支付宝公钥
                PayCongfig.SIGNTYPE); //加密方式
        //创建一个Request请求
        AlipayTradeWapPayRequest Payrequest = new AlipayTradeWapPayRequest();
        //封装对象
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(pay.getOut_trade_no()); //商品ID
        model.setSubject(pay.getSubject());//商品名称
        model.setTotalAmount(pay.getTotal_amount()); //支付页金额
        model.setBody(pay.getBody()); // 商品描述
        model.setTimeoutExpress(String.valueOf(50)); //超时时间（50秒）
        model.setProductCode(pay.getCode());//产品码

        Payrequest.setBizModel(model);//设置请求参数
        Payrequest.setNotifyUrl(PayCongfig.notify_url);//异步回调地址
        Payrequest.setReturnUrl(PayCongfig.return_url);//同步毁掉地址

        //返回生成的表单
        return alipayClient.pageExecute(Payrequest).getBody();
    }
}
