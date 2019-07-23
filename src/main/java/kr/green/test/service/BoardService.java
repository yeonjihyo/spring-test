package kr.green.test.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import kr.green.test.pagination.Criteria;
import kr.green.test.vo.BoardVO;

public interface BoardService {

	ArrayList<BoardVO> getBoardList(Criteria cri);

	BoardVO getBoard(Integer num);

	void updateViews(Integer num);

	void registerBoard(BoardVO bVo);

	void modifyBoard(BoardVO bVo);

	void deleteBoard(Integer num);

	boolean isWriter(HttpServletRequest r, Integer num);

	int totalCount(Criteria cri);



}
