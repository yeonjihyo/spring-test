package kr.green.test.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.test.service.BoardService;
import kr.green.test.vo.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "board/list", method = RequestMethod.GET)//value가 의미하는거는 uri의 경로
	public String boardListGet(Model model) {
		ArrayList<BoardVO> boardList=boardService.getBoardList();
		//new를 쓰지 않는 이유: 매퍼에서 만들어서 넘겨주기 때문에 
		
		//jsp를 맨나중에 작성하는데 이때 model.addAttribute("board",boardList );에 담기는지 확인하기 위해 
		//향상된for문을 통해 서버를 돌렸을때 제대로 되는지 콘솔로 확인후 제대로 되면 jsp작성
		for(BoardVO tmp:boardList) {
			System.out.println(tmp);
		}
		
		model.addAttribute("board",boardList );
		
		return "board/list";
		//WEB-INF/views/뒤에 오는거라 위에와 의미가 다름 보드앞에 /붙으면 안됨
		//앞에확인하는 방법은 servlet-context에서 보면 WEB-INF/views/로 뒤에 /가 붙어 있기 떄문에 보드앞에/붙이면안됨
	}
	@RequestMapping(value="board/display", method=RequestMethod.GET)
	public String boardDisplayGet(Model model, Integer num) {
		
		//서비스에게 번호를 알려주면서 이 번호와 일치하는 게시글을 가져오라고 일을 시킴
		BoardVO bVo=boardService.getBoard(num);
		System.out.println(bVo);
		
		model.addAttribute("oneBoard", bVo);
		
		return "board/display";
	}
	
}
