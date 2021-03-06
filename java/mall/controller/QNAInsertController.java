package mall.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mall.model.QNABean;
import mall.model.QNADao;

@Controller
public class QNAInsertController {
	private final String command = "/insert.mall";
	private final String getPage = "insert";
	private final String gotoPage = "redirect:/qna.mall";
	
	@Autowired
	QNADao qdao;
	
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String doAction() {
		System.out.println("---- insert GET ----");
		
		return getPage;
	}
	
	
	@RequestMapping(value=command, method = RequestMethod.POST)
	public ModelAndView doActionPOST(@Valid QNABean qna, BindingResult result, 
										ModelAndView mav, HttpServletResponse response) {
		System.out.println("---- insert POST ----");		
		
		if(result.hasErrors()) { 
			System.out.println("유효성 검사 오류입니다.");
			mav.setViewName(getPage); 
			return mav; 
		}
		
		if(!qna.getQPW().equals("1234")) {
			System.out.println("비밀번호 오류 " + qna.getQPW());
			
			try {
				PrintWriter pw = response.getWriter();
				pw.println("<script>alert('비밀번호가 틀렸습니다.');</script>");
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			mav.setViewName(getPage); 
			return mav; 
		}
		else {
			System.out.println("비밀번호 일치");
			
			int cnt = qdao.insertQNA(qna);
			System.out.println("cnt: " + cnt);
			
			mav.setViewName(gotoPage);
			return mav;
		}
		 		
		
	}
	
	
	
	
	
}
