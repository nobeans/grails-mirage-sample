package grails.mirage.sample

import com.area_b.samples.Customer
import jp.sf.amateras.mirage.SqlManager

class CustomerService {

    def sqlManager

    def list() {
        return sqlManager.getResultList(
                Customer.class, "com/area_b/samples/select.sql");
    }

    def update() {
        int count = sqlManager.getCount("com/area_b/samples/select.sql")

        sqlManager.executeUpdate("com/area_b/samples/insert.sql")

        int count2 = sqlManager.getCount("com/area_b/samples/select.sql")

        int count3 = Customer.count

        print(count + ":" + count2 + ":" + count3)
    }
}
