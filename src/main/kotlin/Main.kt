import db.Databases
import db.TownTable
import db.db
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insert

fun main(args: Array<String>) {
    Databases.init()
    db {
        val stPeteId = TownTable.insert {
        } get TownTable.id
        println(stPeteId)
        val cityNames = listOf("Paris", "Moscow", "Helsinki")
        val allCitiesID = TownTable.batchInsert(cityNames) {
            s -> println(s)
        }
        allCitiesID.forEach {
            println("${it.keys} = ${it[it.keys.elementAt(0)]}")
        }

    }
}