import java.io.BufferedReader
import java.io.FileReader
import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class OracleConnect() {
    var url = ""
    var username = ""
    var password = ""

    init {
        val properties = Properties()

        val bufferedReader = BufferedReader(
            FileReader(ConfigProperty.get(SOURCE_EXEC_DIR).toString() + "/conf/export.conf")
        )
        properties.load(bufferedReader)
        val login: String = properties.getProperty("src_login")
        val substring1 = login.substring(0, login.indexOf("@"))
        val substring2 = login.substring(login.indexOf("@"))

        val userAndPwd = substring1.split("/").toTypedArray()


        val dbURL = String.format("jdbc:oracle:thin:%s", substring2)
    }

    private fun getConnection(): Connection {
        val username = "DP_CI"
        val password = "datapipeline123"
        try {
            Class.forName("oracle.jdbc.OracleDriver").newInstance()
            return DriverManager.getConnection("", username, password)
        } catch (e: Exception) {
            throw e;
        }
    }

    fun getMaxSequence(): Long {
        val rs = getConnection().createStatement().executeQuery("select max(SEQUENCE#) from v${'$'}log")
        if (rs.next()) {
            return rs.getLong(1)
        }
        throw RuntimeException("max sequence is null, stop test!")
    }
}
