package com.ssafy.qna.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.board.model.BoardParameterDto;
import com.ssafy.qna.model.QnaAnswerDto;
import com.ssafy.qna.model.QnaDto;

public interface QnaMapper {
	public List<QnaDto> listArticle(BoardParameterDto boardParameterDto) throws SQLException;
	public QnaDto getArticle(int articleno) throws SQLException;
	public QnaAnswerDto getQnaAnswer(int articleno) throws SQLException;
	public int deleteArticle(int articleno) throws SQLException;
	public int writeArticle(QnaDto qnaDto) throws SQLException;
	public int modifyArticle(QnaDto qnaDto)  throws SQLException;
	
}
