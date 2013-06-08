// Place your Spring DSL code here
beans = {

    connectionProvider(com.area_b.samples.GrailsConnectionProvider) {
        dataSource = ref('dataSource')
    }

    // SpringConnectionProviderは、Grailsが使っているDataSource実装のGrailsHibernateTransactionManagerを
    // 受け取れないため、独自のGrailsConnectionProviderが必要という流れ。
    //
    // java.lang.IllegalStateException: Cannot convert value of type
    // [org.codehaus.groovy.grails.orm.hibernate.GrailsHibernateTransactionManager] to required type
    // [org.springframework.jdbc.datasource.DataSourceTransactionManager] for property 'transactionManager':
    // no matching editors or conversion strategy found
    //
    //connectionProvider(jp.sf.amateras.mirage.integration.spring.SpringConnectionProvider) {
    //    transactionManager = ref('transactionManager')
    //}

    sqlManager(jp.sf.amateras.mirage.SqlManagerImpl) {
        connectionProvider = connectionProvider
    }
}
