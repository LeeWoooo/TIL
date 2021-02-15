package com.sparta.week2.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//이 class를 상속하면 안에있는 멤버변수를 column으로 인식해줘
@MappedSuperclass // 상속했을 때, 컬럼으로 인식하게 합니다.
//AuditingEntityListener -> 수정이 일어날 때 자동으로 반영을 해줘
//CreatedDate는 생성될 때 선언되니 변경 x
//LastModifiedDate는 수정이 일어날 때마다 값 변경
@EntityListeners(AuditingEntityListener.class) // 생성/수정 시간을 자동으로 반영하도록 설정
public abstract class Timestamped {

    @CreatedDate // 생성일자임을 나타냅니다.
    private LocalDateTime createdAt;

    @LastModifiedDate // 마지막 수정일자임을 나타냅니다.
    //수정일자는 처음생성될 때는 생성일자와 동일하게 들어간 후 수정되면 변경된다.
    private LocalDateTime modifiedAt;
}
