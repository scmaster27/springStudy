package com.example.springStudy.service.memo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.springStudy.model.memo.dao.MemoDAO;
import com.example.springStudy.model.memo.dto.MemoDTO;


@Service
public class MemoServiceImpl implements MemoService {

	@Inject
	MemoDAO memoDao; // 스프링에서 생성한 dao 객체가 연결됨
	
	@Override
	public List<MemoDTO> list() {
		return memoDao.list();
	}


}
