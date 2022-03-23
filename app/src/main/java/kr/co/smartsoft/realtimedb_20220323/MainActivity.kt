package kr.co.smartsoft.realtimedb_20220323

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

    }

    override fun setValues() {
//        DB 연결
        val db = FirebaseDatabase.getInstance("https://realtimedb-20220323-default-rtdb.asia-southeast1.firebasedatabase.app/")     // 싱가폴 db 주송
//        DB 의 하위정보 설정
        val testRef = db.getReference("test")

        testRef.setValue("Hello World")

    }
}