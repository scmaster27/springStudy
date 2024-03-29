package com.example.springStudy.service.memo;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.springStudy.model.memo.dao.MemoDAO;
import com.example.springStudy.model.memo.dto.MemoDTO;


@Service
public class MemoServiceImpl implements MemoService {

	@Inject
	MemoDAO memoDao;
	
	@Override
	public List<MemoDTO> list() {
		return memoDao.list();
	}

	@Override
	public void insert(MemoDTO dto) {

	}

	@Override
	public void insert(String writer, String memo) {
		memoDao.insert(writer, memo);
	}

	@Override 
	public MemoDTO memo_view(int idx) {
		return memoDao.memo_view(idx);
	}

	@Override
	public void update(MemoDTO dto) {
		memoDao.update(dto);
	}

	@Override
	public void delete(int idx) {
		memoDao.delete(idx);
	}
}
