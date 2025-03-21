package vivatech.nurse_mobile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static vivatech.nurse_mobile.config.Constants.*;

@Getter
@Setter
@NoArgsConstructor
public class Response {
    private String code = SUCCESS_CODE;
    private String message = SUCCESS;
    private String status = SUCCESS_CODE;
    private Object data;

    public Response(Exception e){
        this.code = INTERNAL_SERVER_ERROR_CODE;
        this.message = e.getMessage();
        this.status = SUCCESS_CODE;
    }

    public Response(String message){
        this.message = message;
    }

    public Response(String status,String code,String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public Response(String status,String code,String message,Object data){
        this.status = status;
        this.code = code;
        this.message = message;
//        try{
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.registerModule(new JavaTimeModule());
//            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//            String dataString = objectMapper.writeValueAsString(data);
//            this.data = dataString;
//        }catch (Exception e){
        this.data = data;
//        }
    }

}

