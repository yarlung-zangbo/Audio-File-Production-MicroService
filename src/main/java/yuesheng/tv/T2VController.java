package yuesheng.tv;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/T2V")
public class T2VController {
    @PostMapping(value = "/getaudio")
    public Map<String,Object> T2V(@RequestBody() Map request){
        T2V t2v = new T2V();
        String text = request.get("text").toString();
        String res = t2v.TextToAudioBinary(text);
        Map<String,Object> response = new HashMap();
        response.put("response",res);
        return response;
    }
}
