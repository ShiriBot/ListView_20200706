package kr.co.tjoeun.listview_20200706.datas

import java.util.*

class Student(val name: String, val address: String, val birthYear: Int) {

    fun getKoreanAge() : Int {

//        켤 때마다 실제 나이가 계산되게
        var now = Calendar.getInstance()

        return now.get(Calendar.YEAR) - this.birthYear +1
    }

}