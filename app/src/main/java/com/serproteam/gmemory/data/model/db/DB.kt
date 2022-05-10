package com.serproteam.gmemory.data.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.serproteam.gmemory.data.model.Dao.EstadisticasDao
import com.serproteam.gmemory.data.model.Dao.JugadorDao
import com.serproteam.gmemory.data.model.Entity.Estadistica
import com.serproteam.gmemory.data.model.Entity.Jugador

@Database(entities = [Estadistica::class, Jugador::class], version = 1, exportSchema = false)
abstract class DB : RoomDatabase() {
    abstract val estadisticaDao: EstadisticasDao
    abstract val jugadorDao: JugadorDao

    companion object {
        @Volatile
        private var INSTANCE: DB? = null
        fun createDB(context: Context): DB {
            synchronized(this) {
                var instance: DB? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DB::class.java,
                        "gmemory"
                    ).allowMainThreadQueries()
                        .build()
                }
                return instance
            }
        }
    }

}