package board.controller;

import java.util.List;
import board.dto.BoardDto;
import board.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import lombok.extern.slf4j.*;

@Slf4j
@Controller
public class BoardController {

	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/openBoardList.do") 
	public ModelAndView openBoardList() throws Exception{
		
		log.debug("openBoardLIst");
		
		//requestmapping의 경로 문제는 없음.
		ModelAndView mv = new ModelAndView("board/boardList");
		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list",list);
		
		return mv;
	}
	
	//글쓰기 버튼
	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception{
		System.out.println("글쓰기 눌렸을때");
		return "/board/boardWrite";
		
	}
	
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardDto board) throws Exception{
		boardService.insertBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("board/openBoardDetail.do") //여기서RP는 html 파일에서의 파라미터이다.
	public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception{
		ModelAndView mv = new ModelAndView("board/boardDetail");
	
		BoardDto board = boardService.selectBoardDetail(boardIdx);
		mv.addObject("board",board);
		return mv;
		
		
	}
	
	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(BoardDto board) throws Exception{
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception{
		System.out.println("삭제버튼클");
		System.out.println(boardIdx);
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
		
	}
	
	
 
}
