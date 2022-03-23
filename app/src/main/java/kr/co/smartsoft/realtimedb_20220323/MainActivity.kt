package kr.co.smartsoft.realtimedb_20220323

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.smartsoft.realtimedb_20220323.adapters.ChattingRecyclerAdapter
import kr.co.smartsoft.realtimedb_20220323.datas.ChattingData
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {
    var messageCount = 0L // db에 저장된 채팅 갯수 Long 타입으로 저장

    val mChattingList = ArrayList<ChattingData>()

    lateinit var mAdapter: ChattingRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

        realtimeDB.getReference("message").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
//                snapshot 변수 : 현재 변경된 상태
                messageCount = snapshot.childrenCount

//                snapShot => 마지막 자여(최신 채팅 메세지) 추출 => ChattingData로 변환 + 목록 추가

                mChattingList.add(
                    ChattingData(
                    snapshot.children.last().child("content").value.toString(),
                    snapshot.children.last().child("createAt").value.toString()
                ))
                mAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        btnSend.setOnClickListener {
            val inputContent = edtContent.text.toString()

            val testRef = realtimeDB.getReference("message")
            testRef.child(messageCount.toString()).child("content").setValue(inputContent)
//            추가 기록
            val now = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyy년 M월 d일 a h:mm")
            val nowStr = sdf.format(now.time)

            testRef.child(messageCount.toString()).child("createAt").setValue(nowStr)

        }

    }

    override fun setValues() {
        mAdapter = ChattingRecyclerAdapter(mContext, mChattingList)
        chattingRecyclerView.adapter = mAdapter
        chattingRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }
}