// Place your Spring DSL code here
beans = {

    transactionManager(org.springframework.jdbc.datasource.DataSourceTransactionManager) {
        dataSource = ref('dataSource')
    }

    connectionProvider(jp.sf.amateras.mirage.integration.spring.SpringConnectionProvider) {
        transactionManager = transactionManager
    }

    sqlManager(jp.sf.amateras.mirage.SqlManagerImpl) {
        connectionProvider = connectionProvider
    }
}
