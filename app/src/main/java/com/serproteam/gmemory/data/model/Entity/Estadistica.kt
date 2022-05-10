package com.serproteam.gmemory.data.model.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "Estadistica")
class Estadistica(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var nivel: Int,
    var tiempo:String
)