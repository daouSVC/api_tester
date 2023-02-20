package kr.co.daou.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.co.daou.api.service.MultiMedeaUpload;
import kr.co.daou.api.service.RequestService;
import kr.co.daou.api.service.SendMessageAPI;
import kr.co.daou.api.vo.MessageVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class BizppurioMessageAPI {
/*	
    @PostMapping(value = "/sendSMS")
    @ApiOperation(value = "문자 발송 API", notes = "SMS, LMS, MMS 문자 발송을 위한 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공 코드이나, Response 를 확인해 주세요."),
            @ApiResponse(code = 400, message = "Request 오류 파라미터 값을 확인하세요...")
    })
    public String sendSMS(
            @ApiParam(value = "메세지 타입(Ex : sms", required = true)@RequestParam String a_type,
            @ApiParam(value = "직접 부여하는 키 값", required = true)@RequestParam String b_refkey,
            @ApiParam(value = "수신자 번호", required = true)@RequestParam String c_to,
            @ApiParam(value = "메세지 내용", required = true)@RequestParam String e_message
    ){
        MessageVO vo = new MessageVO();
        RequestService rq = new RequestService();
        SendMessageAPI send = new SendMessageAPI();
        vo.setType(a_type);
        vo.setRefkey(b_refkey);
        vo.setTo(c_to);
        vo.setMessage(e_message);
        return send.sendAllMessage(rq.makeMessageRequest(vo));
    }

    @PostMapping(value = "/sendLMS")
    @ApiOperation(value = "문자 발송 API", notes = "LMS 문자 발송을 위한 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공 코드이나, Response 를 확인해 주세요."),
            @ApiResponse(code = 400, message = "Request 오류 파라미터 값을 확인하세요...")
    })
    public String sendLMS(
            @ApiParam(value = "메세지 타입(Ex : lms)", required = true)@RequestParam String a_type,
            @ApiParam(value = "직접 부여하는 키 값", required = true)@RequestParam String b_refkey,
            @ApiParam(value = "수신자 번호", required = true)@RequestParam String c_to,
            @ApiParam(value = "메세지 제목", required = true)@RequestParam(defaultValue = "제목 없음") String d_subject,
            @ApiParam(value = "메세지 내용", required = true)@RequestParam String e_message
    ){
        MessageVO vo = new MessageVO();
        RequestService rq = new RequestService();
        SendMessageAPI send = new SendMessageAPI();
        vo.setType(a_type);
        vo.setRefkey(b_refkey);
        vo.setTo(c_to);
        vo.setSubject(d_subject);
        vo.setMessage(e_message);
        return send.sendAllMessage(rq.makeMessageRequest(vo));
    }

    @PostMapping(value = "/sendMMS")
    @ApiOperation(value = "문자 발송 API", notes = "MMS 문자 발송을 위한 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공 코드이나, Response 를 확인해 주세요."),
            @ApiResponse(code = 400, message = "Request 오류 파라미터 값을 확인하세요...")
    })
    public String sendMMS(
            @ApiParam(value = "메세지 타입(Ex : mms)", required = true)@RequestParam String a_type,
            @ApiParam(value = "직접 부여하는 키 값", required = true)@RequestParam String b_refkey,
            @ApiParam(value = "수신자 번호", required = true)@RequestParam String c_to,
            @ApiParam(value = "메세지 제목", required = true)@RequestParam(defaultValue = "null") String d_subject,
            @ApiParam(value = "메세지 내용", required = true)@RequestParam String e_message,
            @ApiParam(value = "첨부 이미지 절대 경로로 입력.", required = true)@RequestParam(defaultValue = "C:\\Users\\daou\\Pictures\\") String f_attach,
            @ApiParam(value = "추가 되는 이미지는 20KB 제한.", required = true)@RequestParam(defaultValue = "C:\\Users\\daou\\Pictures\\") String g_attach,
            @ApiParam(value = "추가 되는 이미지는 20KB 제한.", required = true)@RequestParam(defaultValue = "C:\\Users\\daou\\Pictures\\") String h_attach
    ){
        MessageVO vo = new MessageVO();
        RequestService rq = new RequestService();
        SendMessageAPI send = new SendMessageAPI();
        vo.setType(a_type);
        vo.setRefkey(b_refkey);
        vo.setTo(c_to);
        vo.setSubject(d_subject);
        vo.setMessage(e_message);
        vo.setAttach1(f_attach);
        vo.setAttach2(g_attach);
        vo.setAttach3(h_attach);
        return send.sendAllMessage(rq.makeMessageRequest(vo));
    }
*/    
    @PostMapping(value = "/sendFile")
    @ApiOperation(value = "첨부파일 업로드API", notes = "MMS 발송을 위한 첨부파일 업로드 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공 코드이나, Response 를 확인해 주세요."),
            @ApiResponse(code = 400, message = "Request 오류 파라미터 값을 확인하세요...")
    })	
    public String sendFile(
    		@ApiParam(value = "비즈뿌리오 계정", required = true)@RequestParam String a_id,
            @ApiParam(value = "첨부 이미지 절대 경로로 입력.", required = true)@RequestParam(defaultValue = "C:\\Users\\daou\\Pictures\\") String b_attach
    ){
    	String strResult = "";
    	
    	MultiMedeaUpload mmUpload = new MultiMedeaUpload();
    	
    	mmUpload.id = a_id;
    	
        if (!b_attach.isEmpty() || !b_attach.equals("null")) {
        	mmUpload.upload(b_attach);
        	
        	strResult = mmUpload.uploadKey;
        }
        else
        	strResult = "b_attach is empty or \"null\".";
            
        return strResult;
    }	
    @PostMapping(value = "/sendMessage")
    @ApiOperation(value = "※Jason전문 발송 API", notes = "Jason전문을 그대로 발송하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공 코드이나, Response 를 확인해 주세요."),
            @ApiResponse(code = 400, message = "Request 오류 파라미터 값을 확인하세요...")
    })    
    public String sendMessage(
    		@ApiParam(value = "비즈뿌리오 계정", required = true)@RequestParam String a_id,
    		@ApiParam(value = "비즈뿌리오 비밀번호", required = true)@RequestParam String b_passwd,
            @ApiParam(value = "메세지 Jason타입", required = true)@RequestBody String c_message
    ){
        SendMessageAPI send = new SendMessageAPI();
        
        send.id = a_id;
        send.passwd = b_passwd;
               
        return send.sendMessage(c_message);
    }    
}