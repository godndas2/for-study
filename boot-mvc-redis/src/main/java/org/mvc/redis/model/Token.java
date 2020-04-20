package org.mvc.redis.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
* @author halfdev
* @since 2020-04-21
* 여러 기기에서 Remember Me 를 시도할 경우 삭제 해버리는 Issue
 * Ps.직접 Remember Me 구현하는 방법도 있으나 현재 프로젝트에서는 이대로 진행
*/
@Data
@Entity
public class Token implements Serializable {

    private static final long serialVersionUID = 839035049585559894L;

    @Id
    private String username;
    private String series;
    private String token;
    private Date lastUsed;
}
