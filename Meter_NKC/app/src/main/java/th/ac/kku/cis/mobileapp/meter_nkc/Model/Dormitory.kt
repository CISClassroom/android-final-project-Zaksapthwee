package th.ac.kku.cis.mobileapp.meter_nkc.Model

import java.util.*

//class Dormitory  (var id:String,var Dorm: String,var floor: String,var RoomNum: String,var Meter: String,var timerstamp: Date){
//
//    companion object Factory {
//        fun create():Dormitory = Dormitory()
//
//    }
//
//
//
//}
class Dormitory {

    companion object Factory {
        fun create(): Dormitory = Dormitory()
    }
    var id:String?=null
    var Dorm:String?=null
    var floor:String?=null
    var RoomNum:String?=null
    var Meter:String?=null
    var timerstamp:Date?=null

}

