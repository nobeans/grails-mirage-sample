package grails.mirage.sample

import com.area_b.samples.Customer
import grails.plugin.spock.IntegrationSpec

class CustomerServiceSpec extends IntegrationSpec {

    // Tx絡みの挙動確認をする場合、テスト機構による自動Tx制御は邪魔になるのでオフにする
    static transactional = false

    CustomerService customerService

    def setup() {
        ["Taro Yamada", "Jiro Yoshida"].each { name ->
            new Customer(name: name).save(failOnError: true, flush: true)
        }
    }

    def cleanup() {
        Customer.list()*.delete(flush: true)
    }

    void "list()はソートされたCustomerのリストを返す"() {
        expect:
        customerService.list()*.id == Customer.listOrderById()*.id
    }

    void "update()はレコードを1つ新規追加する"() {
        given:
        assert Customer.count() == 2

        when:
        customerService.update()

        then:
        Customer.count() == 3
    }

    void "既存Txありの場合、実装によってはTxがコミットされるまでGORM経由のアクセスでMirageの変更結果が見えなくなることもあるが、今の実装は問題ない"() {
        given:
        assert Customer.count() == 2

        when:
        Customer.withTransaction {
            customerService.update()

            // 同一Tx内のGORMアクセスでもupdate()で追加されたレコードが見えている
            assert Customer.count() == 3
        }

        then:
        assert Customer.count() == 3
    }
}