package com.ssafy.qna.model.service;

import java.util.List;

import com.ssafy.board.model.BoardParameterDto;
import com.ssafy.qna.model.QnaDto;

public interface QnaService {
	public List<QnaDto> listArticle(BoardParameterDto boardParameterDto) throws Exception;
	public QnaDto getArticle(int articleno) throws Exception;
	boolean deleteArticle(int articleno) throws Exception;
	public boolean writeArticle(QnaDto qnaDto)throws Exception;
	public boolean modifyArticle(QnaDto qnaDto)throws Exception;
}
