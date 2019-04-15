
import br.com.jaya.jonatasrodrigues.config.AppConfig
import br.com.jaya.jonatasrodrigues.domain.Event
import br.com.jaya.jonatasrodrigues.domain.Issue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.javalin.Javalin
import junit.framework.TestCase
import org.json.JSONObject
import org.junit.Test

class EventTest : TestCase() {

        private lateinit var app: Javalin
        private val url = "http://localhost:8081/"


        override fun setUp() {
            app =   AppConfig(8081).test()
        }

        override fun tearDown() {
            app.stop()
        }

    fun testCreateEvent() {
        val issue =Issue(id = 1, created_at = "2019-04-12",url = "teste@teste", number = 123)
        val payload = mapOf("action" to "opened", "issue" to issue)
        val response = khttp.post(url = url, data = JSONObject(payload))
        assertEquals(201, response.statusCode)
    }

    fun testGetEvent() {
        val response = khttp.get(url = url + "issues/321/events")
        val events = response.text.deserialize<List<Event>>()

        assertEquals(200, response.statusCode)
        assertEquals("opened", events[0].action)
        assertEquals(1, events[0].issue.id)
        assertEquals("teste1@teste.com", events[0].issue.url)
        assertEquals("2019-04-12", events[0].issue.created_at)
    }

    fun testGetEventNotExist() {
        val response = khttp.get(url = url + "issues/555/events")
        val events = response.text.deserialize<List<Event>>()

        assertEquals(200, response.statusCode)
        assertEquals(true, events.size==0)
    }

    inline fun<reified T : Any>String.deserialize(): T= jacksonObjectMapper().readValue(this)

    }