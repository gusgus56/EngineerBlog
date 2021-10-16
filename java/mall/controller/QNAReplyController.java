package mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mall.model.QNADao;

@Controller
public class QNAReplyController {
	private final String command = "/reply.mall";
	private final String getPage = "replyForm";
	private final String gotoPage = "redirect:/qna.mall";
	
	@Autowired
	QNADao qdao;
	
	@RequestMapping(value = command)
	public String reply(@RequestParam("QREF") int QREF, @RequestParam("QRESTEP") int QRESTEP,
							@RequestParam("QRELEVEL") int QRELEVEL) {
		
		return getPage;
	}
	
}
