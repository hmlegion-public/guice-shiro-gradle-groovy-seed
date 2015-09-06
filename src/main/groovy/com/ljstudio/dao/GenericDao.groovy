package com.ljstudio.dao

import com.ljstudio.domain.BaseBean

/**
 * Created by lijin on 15-9-6.
 */
public interface GenericDao < T extends BaseBean, PK extends Serializable > {
    public T save ( T t );
    public T find ( PK id );
    public void remove ( T t );
}