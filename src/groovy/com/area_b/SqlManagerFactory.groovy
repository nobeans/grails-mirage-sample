package com.area_b

import jp.sf.amateras.mirage.SqlManager
import jp.sf.amateras.mirage.SqlManagerImpl
import jp.sf.amateras.mirage.dialect.Dialect
import jp.sf.amateras.mirage.provider.ConnectionProvider
import jp.sf.amateras.mirage.provider.DefaultConnectionProvider
import org.hibernate.SessionFactory
import org.hibernate.connection.ConnectionProvider as SCP
import org.hibernate.engine.SessionFactoryImplementor

/**
 * GORMに使用するのと同じConnectionを使用するSqlManagerを生成する.
 * @author naga
 */
class SqlManagerFactory {

    /**
     * sessionFactory に設定されている Connection を使用する
     * SqlManagerのインスタンスを生成する.
     *
     * @param sessionFactory non-null
     * @param dialect non-null 使用するDBに応じて、jp.sf.amateras.mirage.dialect より適切なものを設定.
     * @return non-null
     */
    public static SqlManager createInstance(SessionFactory sessionFactory, Dialect dialect) {
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) sessionFactory
        SCP scp = sfi.getConnectionProvider()
        ConnectionProvider connectionProvider = new DefaultConnectionProvider()
        connectionProvider.connection = scp.connection
        SqlManager manager = new SqlManagerImpl()
        manager.connectionProvider = connectionProvider
        manager.dialect = dialect
        return manager
    }
}
