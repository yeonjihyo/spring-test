package kr.green.test.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.test.dao.BoardDAO;
import kr.green.test.vo.BoardVO;
import kr.green.test.vo.MemberVO;

@Service
public class BoardServiceImp implements BoardService {

	@Autowired
	BoardDAO boardDao;

	@Override
	public ArrayList<BoardVO> getBoardList() {
		
		return boardDao.boardList();
	}

	@Override
	public BoardVO getBoard(Integer num) {
		// TODO Auto-generated method stub
		if(num == null || num<=0){
			return null;
		}
		return 	boardDao.getBoard(num);
	}

	@Override
	public void updateViews(Integer num) {
		//첫번째 방법:
		boardDao.updateViews(num);
		//두번째방법 :
//		BoardVO tmp = boardDao.getBoard(num);
//		if(tmp != null) {
//			int oldViews = tmp.getViews();//현재조회소를 올드뷰에 저장
//			tmp.setViews(oldViews+1);
//			boardDao.updateBoard(tmp);
//		}
	}

	@Override
	public void registerBoard(BoardVO bVo) {
		boardDao.registerBoard(bVo);
		
	}


	@Override
	public void modifyBoard(BoardVO bVo) {
		boardDao.modifyBoard(bVo);
		
	}
}
