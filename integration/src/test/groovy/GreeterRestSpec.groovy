
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@Testcontainers
class GreeterRestSpec extends Specification {

    @Shared
    def environment = new DockerComposeContainer(
            new File("src/test/resources/docker-compose-rest.yml"))
            .withExposedService("app", 9099)

    def setupSpec() {
        environment.start()
    }

    def "can access web server"() {
        given: "web server url"
        def url = "http://" + environment.getServiceHost("appr_1", 9099) +
                ":" + environment.getServicePort("app_1", 9099) + "/sayhello?caller=test"

        when: "get request is made"
        def connection = (HttpURLConnection) new URL(url).openConnection()
        connection.setRequestMethod("GET")
        def result = connection.getInputStream().text

        then: "correct response is received"
        result.contains("test!")
    }
}