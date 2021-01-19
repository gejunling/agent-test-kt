import java.io.BufferedReader
import java.io.FileReader
import java.util.*


const val SOURCE_EXEC_DIR = "source.exec.dir"

object ConfigProperty {
    private val testProps: Properties = Properties()
    private val sendProps: Properties = Properties()

    init {
//        val bufferedReader = BufferedReader(FileReader("./conf/test.properties"))
        val bufferedReader =
            BufferedReader(FileReader("/Users/gejunling/gradle/agent-test/src/main/resources/conf/test.properties"))
        bufferedReader.use { testProps.load(it) }

        val execDir = get(SOURCE_EXEC_DIR)
        println(execDir)
    }

    fun get(key: String): String? {
        return testProps.getProperty(key)
    }

}

fun main() {

}
