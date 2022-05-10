package com.serproteam.gmemory.data.model.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "Jugador")
class Jugador(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var nombre: String,
    var apellidos: String,
    var avatar:Int
)