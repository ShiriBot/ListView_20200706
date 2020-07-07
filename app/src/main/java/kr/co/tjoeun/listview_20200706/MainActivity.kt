package kr.co.tjoeun.listview_20200706

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.listview_20200706.adapters.StudentAdapter
import kr.co.tjoeun.listview_20200706.datas.Student

class MainActivity : AppCompatActivity() {

//        학생 목록을 담고 있는 변수
    val mStudentList = ArrayList<Student>()

    lateinit var mAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        미뤄둔 어댑터 객체화 실행
        mAdapter = StudentAdapter(this,R.layout.student_list_item,mStudentList)

//        완성된 어댑터를 리스트뷰와 연결
        studentListView.adapter = mAdapter

//        어댑터를 먼저 연결하고, 내용물 추가
        mStudentList.add(Student("조경진","서울시 은평구",1988))
        mStudentList.add(Student("김미희","서울시 중랑구",1995))
        mStudentList.add(Student("김재영","서울시 은평구",1986))
        mStudentList.add(Student("박호준","인천시 부평구",1990))
        mStudentList.add(Student("이예원","서울시 금천구",1984))
        mStudentList.add(Student("조장우","서울시 종로구",1983))
        mStudentList.add(Student("채정실","서울시 용산구",1991))

//        어댑터에게 새로고침 시키기
        mAdapter.notifyDataSetChanged()

        studentListView.setOnItemClickListener { parent, view, position, id ->

//            각 줄을 누르면 눌린 사람의 이름을 토스트로
//            눌린 사람이 누군지?
            val clickedUser = mStudentList[position]

//            토스트로 눌린 사람 이름
            Toast.makeText(this,clickedUser.name, Toast.LENGTH_SHORT).show()
        }

//        학생목록 롱클릭 이벤트 처리

        studentListView.setOnItemLongClickListener { parent, view, position, id ->

//            정말 그 사람을 지울지 확인.  => 확인 OK 여야 삭제.
            val deleteAlert = AlertDialog.Builder(this)
            deleteAlert.setTitle("학생 목록 삭제")
            deleteAlert.setMessage("정말 이 학생을 삭제하시겠습니까?")
            deleteAlert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
//                확인을 누르면 실제로 삭제 처리
//                오래 눌린 사람을 명단에서 삭제.
                mStudentList.removeAt(position)

//            어댑터에게 새로고침시키기
                mAdapter.notifyDataSetChanged()
            })
            deleteAlert.setNegativeButton("취소", null)
            deleteAlert.show()

//            LongClick은 반드시 Boolean값을 리턴해줘야 함.
            return@setOnItemLongClickListener true
        }

    }
}