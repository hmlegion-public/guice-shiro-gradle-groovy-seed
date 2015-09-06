package com.ljstudio.dao.impl

import com.ljstudio.dao.GenericDao
import com.ljstudio.domain.BaseBean
import com.ljstudio.utils.RequestLogger
import org.apache.onami.persist.EntityManagerProvider
import org.apache.onami.persist.Transactional

import javax.persistence.EntityManager

/**
 * Created by lijin on 15-9-6.
 */
abstract class GenericDaoImpl < T extends BaseBean, PK extends Serializable >
        implements GenericDao< T, PK > {
    private EntityManagerProvider emp

    private Class < T > clazz;
    protected RequestLogger logger;

    protected GenericDaoImpl ( EntityManagerProvider emp,
                               Class < T > clazz,
                               RequestLogger logger ) {
        this.emp = emp;
        this.clazz = clazz;
        this.logger = logger;
    }

    protected EntityManager em() {
        return emp.get();
    }

    @Transactional
    public T save ( T t ) {
        logger.info ( "saving " + clazz.getSimpleName() + ". primary key: " + t.getPrimaryKey() );
        //JPA的merge和persist ！
        //http://pz0513.blog.51cto.com/443986/113098/
        //用 merge 在有ManyToOne 时会出错
//        return em().merge ( t );
        return em().persist ( t );
    }

    public T find ( PK id ) {
        logger.info ( "finding " + clazz.getSimpleName() + ". primary key: " + id );
        return em().find ( clazz, id );
    }

    @Transactional
    public void remove ( T t ) {
        logger.info ( "removing " + clazz.getSimpleName() + ". primary key: " + t.getPrimaryKey() );
        em().remove ( t );
    }
}
