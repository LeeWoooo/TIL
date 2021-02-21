package com.sparta.week03_prac.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass //상속을 받게되면 자동으로 column으로 인식하게 된다.
@EntityListeners(AuditingEntityListener.class)//지켜보고 있다가 변경이 생기면 자동으로 업데이트를 해준다.
@Getter
public abstract class TimeStamped {

    @CreatedDate//생성시간
    private LocalDateTime createAt;

    @LastModifiedDate//수정시간
    private LocalDateTime modifiedAt;

}
