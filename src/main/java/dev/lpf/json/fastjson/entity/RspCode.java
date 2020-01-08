package dev.lpf.json.fastjson.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: gradle-demo-1
 * @description:
 * @author: Li Pengfei
 * @create: 2019-12-18 15:45
 **/
@Data
public class RspCode {
    @JSONField(name = "ret_code")
    private String code;

    @JSONField(name = "ret_msg")
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
        this.transcode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        this.transcode();
    }

    public RspCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RspCode() {
    }

    public void transcode() {
        switch (this.code) {
            case "400":
                this.msg = "client error";
                this.code = "C.400";
                break;
            case "200":
                this.code = "C.200";
                this.msg = "success";
                break;
            default:
                this.code = "C.500";
                this.msg = "server internal error";
        }
    }
}

