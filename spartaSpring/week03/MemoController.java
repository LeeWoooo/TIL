package com.sparta.week03_prac.controller;

import com.sparta.week03_prac.domain.Memo;
import com.sparta.week03_prac.domain.MemoDTO;
import com.sparta.week03_prac.repository.MemoRepository;
import com.sparta.week03_prac.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemoController {

    private final MemoService memoService;

    @Autowired
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/api/memos")
    @ResponseBody
    public List<Memo> findAll(){
        return memoService.findAll();
    }

    @PostMapping("/api/memos")
    @ResponseBody
    public Memo insertMemo(@RequestBody MemoDTO memoDTO){
        return memoService.insertMemo(memoDTO);
    }

    @PutMapping("/api/memos/{id}")
    @ResponseBody
    public Long updateMemo(@PathVariable Long id,@RequestBody MemoDTO memoDTO){
        memoService.memoUpdate(id,memoDTO);
        return id;
    }

    @DeleteMapping("/api/memos/{id}")
    @ResponseBody
    public Long deleteMemo(@PathVariable Long id){
        return memoService.deleteMemo(id);
    }
}
