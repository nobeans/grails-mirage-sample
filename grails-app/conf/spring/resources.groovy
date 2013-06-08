// Place your Spring DSL code here
beans = {

    connectionProvider(com.area_b.samples.GrailsConnectionProvider) {
        dataSource = ref('dataSource')
    }

    sqlManager(jp.sf.amateras.mirage.SqlManagerImpl) {
        connectionProvider = connectionProvider
    }
}
