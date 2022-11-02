package com.ssafy.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.mapper.BoardDaoImpl;
import com.ssafy.board.model.mapper.BoardMapper;
import com.ssafy.util.SizeConstant;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardDao;

	@Override
	public int writeArticle(BoardDto boardDto) throws Exception {
		return boardDao.writeArticle(boardDto);
	}

	@Override
	public List<BoardDto> listArticle() throws Exception {
//		int pgno = Integer.parseInt(map.get("pgno"));
//		int spl = SizeConstant.SIZE_PER_LIST;
//		int start = (pgno - 1) * spl;
//		map.put("start", start + "");
//		map.put("spl", spl + "");
		return boardDao.listArticle();
	}

	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		return boardDao.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		boardDao.updateHit(articleNo);
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		boardDao.modifyArticle(boardDto);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		boardDao.deleteArticle(articleNo);
	}

	@Override
	public int totalArticleCount(Map<String, String> map) throws Exception {
		return boardDao.totalArticleCount(map);
	}

	@Override
	public List<BoardDto> searchArticle(Map<String, String> m) throws Exception {
		return boardDao.searchArticle(m);
	}
}
