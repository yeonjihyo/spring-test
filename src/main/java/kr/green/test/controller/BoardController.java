package kr.green.test.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

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
	//게시판
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
	//게시글
	@RequestMapping(value="board/display", method=RequestMethod.GET)
	public String boardDisplayGet(Model model, Integer num) {
		//조회수 증가 bvo앞에 쓰는 이유는 먼저 조회수를 올리고 게시글을 가져오려고 
		boardService.updateViews(num);
		//서비스에게 번호를 알려주면서 이 번호와 일치하는 게시글을 가져오라고 일을 시킴
		BoardVO bVo=boardService.getBoard(num);
		System.out.println(bVo);
		
		model.addAttribute("board", bVo);
		
		return "board/display";
	}
	//게시판등록
	@RequestMapping (value="board/register",method=RequestMethod.GET)
	public String boardRegisterGet() {
		
		return "board/register";
	}
	@RequestMapping (value="board/register",method=RequestMethod.POST)
	public String boardRegisterPost(Model model, BoardVO bVo) {
		//System.out.println(bVo); //일을시키기위해 제대로 넘어왔는지 확인 
		boardService.registerBoard(bVo);
		return "redirect:/board/list";
	}
	//게시글 수정 
	@RequestMapping(value="board/modify", method=RequestMethod.GET)
	public String boardModifyGet(Model model, Integer num) {
		System.out.println(num);
		BoardVO bVo=boardService.getBoard(num);
		model.addAttribute("board",bVo);
		
		return "board/modify";
	}
	@RequestMapping(value="board/modify", method=RequestMethod.POST)
	public String boardModifyPost(Model model,BoardVO bVo) {
		System.out.println(bVo); 
		boardService.modifyBoard(bVo);
		model.addAttribute("num",bVo.getNum());
		
		return "redirect:/board/display";
	}
		
}
