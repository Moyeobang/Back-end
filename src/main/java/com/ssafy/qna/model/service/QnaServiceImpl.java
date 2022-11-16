package com.ssafy.qna.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.board.model.BoardParameterDto;
import com.ssafy.qna.model.QnaAnswerDto;
import com.ssafy.qna.model.QnaDto;
import com.ssafy.qna.model.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<QnaDto> listArticle(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		return sqlSession.getMapper(QnaMapper.class).listArticle(boardParameterDto);
	}
	
	@Override
	public QnaDto getArticle(int articleno) throws Exception {
		QnaDto qnaDto = sqlSession.getMapper(QnaMapper.class).getArticle(articleno);
		QnaAnswerDto qnaAnswerDto = sqlSession.getMapper(QnaMapper.class).getQnaAnswer(articleno);
		qnaDto.setQnaAnswerDto(qnaAnswerDto);
		return qnaDto;
	}
	
	
	@Override
	@Transactional
	public boolean deleteArticle(int articleno) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).deleteArticle(articleno) == 1;
	}

	@Override
	public boolean writeArticle(QnaDto qnaDto) throws Exception {
		// TODO Auto-generated method stub
		if(qnaDto.getSubject() == null || qnaDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(QnaMapper.class).writeArticle(qnaDto) == 1;
	}

	@Override
	public boolean modifyArticle(QnaDto qnaDto) throws Exception {
		int temp = sqlSession.getMapper(QnaMapper.class).modifyArticle(qnaDto);
		System.out.println(temp);
		return temp == 1;
	}
}
