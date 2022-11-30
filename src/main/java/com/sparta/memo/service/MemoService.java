package com.sparta.memo.service;


import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;
    }

    @Transactional(readOnly = true)
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Optional<Memo> getOneMemos(Long id) {
        memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );

        return memoRepository.findById(id);
    }

    @Transactional
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        if (memo.getPassword().equals(requestDto.getPassword())){
            memo.update(requestDto);
        }else {
        }
        return memo.getId();
    }
    @Transactional
    public MemoResponseDto deleteMemo(Long id, MemoRequestDto requestDto) {

       memoRepository.findByIdAndPassword(id, requestDto.getPassword()).orElseThrow(
                () -> new IllegalArgumentException("패스워드가 틀렸습니다")
        );

//        if (memo.getPassword().equals(pw)){
            memoRepository.deleteById(id);
//            return new MemoResponseDto("삭제성공");
//        }else {
//            return new MemoResponseDto("실패");
//        }
        return new MemoResponseDto("성공");
//        if (memo.getPassword().equals(pw)){
//            memoRepository.deleteById(id);
//            return new MemoResponseDto("삭제성공");
//        }else {
//            return new MemoResponseDto("실패");
//        }

    }
}
