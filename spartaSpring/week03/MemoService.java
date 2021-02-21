package com.sparta.week03_prac.service;

import com.sparta.week03_prac.domain.Memo;
import com.sparta.week03_prac.domain.MemoDTO;
import com.sparta.week03_prac.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemoService {

    private final MemoRepository memoRepository;

    @Autowired
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }


    public Long memoUpdate(Long id, MemoDTO memoDTO){
        Memo foundMemo = memoRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 아이디는 존재하지 않습니다.")
        );
        foundMemo.update(memoDTO);
        return id;
    }

    public List<Memo> findAll(){
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    public Long deleteMemo(Long id){
        memoRepository.deleteById(id);
        return id;
    }

    public Memo insertMemo(MemoDTO memoDTO){
        return memoRepository.save(new Memo(memoDTO));
    }
}
