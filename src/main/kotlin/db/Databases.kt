package db

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

class Databases {
    companion object {
        fun init() {
            Database.connect("jdbc:sqlite:./data.db", "org.sqlite.JDBC")
            TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_READ_UNCOMMITTED
            transaction {
                // print sql to std-out
                addLogger(StdOutSqlLogger)
                SchemaUtils.create(TownTable)
            }
        }
    }
}

fun <T> db(func: () -> T) {
    transaction {
        func()
    }
}