package com.sparta.week04.models;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter //getter method를 만들어 주는 lombok
@MappedSuperclass //이 class를 상속받게되면 상속받은 entity에서 column의로 적용
@EntityListeners(AuditingEntityListener.class) //현재 class를 audition에 등록
public abstract class Timestamped { //이 class를 단독으로 생성해서 쓸일은 없기 때문에

    @CreatedDate //생성시점
    private LocalDateTime crateAt;

    @LastModifiedDate //수정시점
    private LocalDateTime modifiedAt;

}
