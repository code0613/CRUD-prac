package com.sparta.memo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoResponseDto {

    private String msg;


    public MemoResponseDto(String msg) {
        this.msg = msg;
    }
}
