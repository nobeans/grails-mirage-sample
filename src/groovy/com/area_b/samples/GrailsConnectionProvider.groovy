package com.area_b.samples

import jp.sf.amateras.mirage.provider.ConnectionProvider

import javax.sql.DataSource
import java.sql.Connection

/**
 * Springのトランザクション制御下のカレントセッションのコネクションを返すコネクションプロバイダ実装です。
 * Spring/Hibernateのコネクションをそのまま利用できるため、トランザクション管理等はすべて
 * 通常のGrailsによって制御できます。のはず。
 */
class GrailsConnectionProvider implements ConnectionProvider {

    DataSource dataSource

    @Override
    Connection getConnection() {
        dataSource.connection
    }
}
