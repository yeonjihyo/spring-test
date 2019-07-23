package kr.green.test.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.test.dao.BoardDAO;
import kr.green.test.pagination.Criteria;
import kr.green.test.vo.BoardVO;
import kr.green.test.vo.MemberVO;

@Service
public class BoardServiceImp implements BoardService {

	@Autowired
	BoardDAO boardDao;

	@Override
	public ArrayList<BoardVO> getBoardList(Criteria cri) {
		
		return boardDao.boardList(cri);
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

	@Override
	public void deleteBoard(Integer num) {
		boardDao.deleteBoard(num);
		
	}

	@Override
	public boolean isWriter(HttpServletRequest r, Integer num) {//HttpServletRequest r : 현재열려있는서버라고 생각
		MemberVO user = (MemberVO)r.getSession().getAttribute("user");//현재 열려 있는 서버의 세션을 가져와서 세션의 user라는 속성을 추가한다   
		BoardVO board=boardDao.getBoard(num);
		if(user !=null && board !=null && user.getId().equals(board.getWriter())) {
			return true;
		}
		return false;
	}

	@Override
	public int totalCount(Criteria cri) {
		
		return boardDao.totalCount(cri);
	}
}
