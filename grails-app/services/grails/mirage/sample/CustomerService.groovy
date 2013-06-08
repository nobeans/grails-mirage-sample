package grails.mirage.sample

import com.area_b.samples.Customer

class CustomerService {

    def sqlManager

    def list() {
        sqlManager.getResultList(Customer.class, "com/area_b/samples/select.sql")
    }

    def update() {
        int countBefore = sqlManager.getCount("com/area_b/samples/select.sql")

        sqlManager.executeUpdate("com/area_b/samples/insert.sql")

        int countAfter = sqlManager.getCount("com/area_b/samples/select.sql")
        int countAfterByGorm = Customer.count()

        println(countBefore + ":" + countAfter + ":" + countAfterByGorm)

        assert countBefore + 1 == countAfter
        assert countAfter == countAfterByGorm
    }
}
