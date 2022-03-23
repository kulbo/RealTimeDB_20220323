package kr.co.smartsoft.realtimedb_20220323

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

        btnSend.setOnClickListener {
            val inputContent = edtContent.text.toString()

            val testRef = realtimeDB.getReference("message")
            testRef.child("0").child("content").setValue(inputContent)
//            추가 기록
            val now = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyy년 M월 d일 a h:mm")
            val nowStr = sdf.format(now.time)

            testRef.child("0").child("createAt").setValue(nowStr)

        }

    }

    override fun setValues() {
//        DB 연결
//        val db = FirebaseDatabase.getInstance("https://realtimedb-20220323-default-rtdb.asia-southeast1.firebasedatabase.app/")     // 싱가폴 db 주송
////        DB 의 하위정보 설정
//        val testRef = db.getReference("test")
//
//        testRef.setValue("Hello World")

    }
}