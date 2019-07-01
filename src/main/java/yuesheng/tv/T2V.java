package yuesheng.tv;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class T2V {
    public static final String APP_ID = "16682530";
    public static final String API_KEY = "AHo1rGgmZy29ULcCPyBVxcrY";
    public static final String SECRET_KEY = "rtwOSBH7kEjoVrghtfW52MNsWqLupi9Z";

    public String TextToAudioBinary(String text) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", 80);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "D:\\软件工程导论\\week19\\tv\\src\\main\\resources\\log4j.properties");

        // 调用接口
        int length = text.length(), i = 0, j = 0, resLength;
        System.out.println(length);
        System.out.println(text.substring(0,3));
        byte[] result = new byte[0],oldResult;
        while(i<length){
            j=i;
            if(length-i<512) i = length;
            else {
                i+=512;
                char c = text.charAt(i);
                while(i<1024&&c!='。'&&c!='！'&&c!='？'&&c!='，'&&c!='；'&&c!='…')
                    c = text.charAt(++i);
            }
            System.out.println("i= "+i);
            String excerpt = text.substring(j,i);
            TtsResponse res = client.synthesis(excerpt, "zh", 1, null);
            byte[] ResponseB = res.getData();
            oldResult = result;
            result = new byte[ResponseB.length+result.length];
            System.arraycopy(oldResult,0, result,0,oldResult.length);
            System.arraycopy(ResponseB,0,result,oldResult.length,ResponseB.length);
            JSONObject res1 = res.getResult();
            if(res1!=null) {
                System.out.println(res1.toString());
            }
        }
        if (result != null) {
            try {
                Util.writeBytesToFileSystem(result, "output7.mp3");
                return "Output.";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Failure occurred.";
    }
}
