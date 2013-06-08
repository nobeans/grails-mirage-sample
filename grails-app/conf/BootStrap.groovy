import com.area_b.samples.Customer

class BootStrap {

    def init = { servletContext ->
        environments {
            development {
                ["Taro Yamada", "Jiro Yoshida"].each { name ->
                    new Customer(name: name).save(failOnError: true)
                }
            }
        }
    }

    def destroy = {
    }
}
