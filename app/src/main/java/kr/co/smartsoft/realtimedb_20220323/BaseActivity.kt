package kr.co.smartsoft.realtimedb_20220323

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

abstract class BaseActivity : AppCompatActivity() {
    lateinit var mContext : Context

    val realtimeDB = FirebaseDatabase.getInstance("https://realtimedb-20220323-default-rtdb.asia-southeast1.firebasedatabase.app/")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }
    abstract fun setUpEvents()
    abstract fun setValues()
}