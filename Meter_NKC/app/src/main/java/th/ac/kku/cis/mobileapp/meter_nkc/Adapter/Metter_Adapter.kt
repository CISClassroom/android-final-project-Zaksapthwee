package th.ac.kku.cis.mobileapp.meter_nkc.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import th.ac.kku.cis.mobileapp.meter_nkc.Model.Dormitory
import th.ac.kku.cis.mobileapp.meter_nkc.R

class Metter_Adapter (var mCtx: Context,
                     var resource:Int,
                     var items:List<Dormitory>)
    : ArrayAdapter<Dormitory>(mCtx,resource,items){
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val layout: LayoutInflater = LayoutInflater.from(mCtx)
        val v: View = layout.inflate(resource,null)
//        val icon: ImageView = v.findViewById(R.id.studentphoto)
//        val name: TextView = v.findViewById(R.id.namestudent)
//        val idstudent: TextView = v.findViewById(R.id.idstudent)

        val vDorm: TextView = v.findViewById(R.id.namedorm)
        val vName:TextView = v.findViewById(R.id.namedorm)
        val vRoomNum:TextView = v.findViewById(R.id.room)
        val vMeter:TextView = v.findViewById(R.id.metter)

        val metter:Dormitory = items[position]

        vDorm.text = metter.Dorm
        vName.text = metter.floor
        vRoomNum.text = metter.RoomNum
        vMeter.text    = metter.Meter
        return v

    }
}