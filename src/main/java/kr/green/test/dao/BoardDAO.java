package kr.green.test.dao;

import java.util.ArrayList;

import kr.green.test.pagination.Criteria;
import kr.green.test.vo.BoardVO;

public interface BoardDAO {


	ArrayList<BoardVO> boardList(Criteria cri);

	BoardVO getBoard(Integer num);

	void updateViews(Integer num);

	void registerBoard(BoardVO bVo);

	void modifyBoard(BoardVO bVo);

	void deleteBoard(Integer num);

	int totalCount(Criteria cri);



}
