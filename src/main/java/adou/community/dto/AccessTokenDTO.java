package adou.community.dto;

import lombok.Data;

@Data
public class AccessTokenDTO {
    private String client_id; //从GitHub收到的GitHub App的客户端ID
    private String client_secret; //GitHub收到的GitHub App的客户密码
    private String redirect_uri; //授权后将用户发送到应用程序中的URL
    private String code; //收到的作为对步骤1的响应的代码
    private String state; // 在步骤1中提供的无法猜测的随机字符串


}
