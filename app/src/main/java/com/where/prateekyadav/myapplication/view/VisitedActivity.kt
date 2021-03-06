package com.where.prateekyadav.myapplication.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.where.prateekyadav.myapplication.GlideApp
import com.where.prateekyadav.myapplication.R
import com.where.prateekyadav.myapplication.Util.AppConstant
import com.where.prateekyadav.myapplication.Util.AppUtility
import com.where.prateekyadav.myapplication.Util.MySharedPref
import com.where.prateekyadav.myapplication.database.DBContract
import com.where.prateekyadav.myapplication.database.DataBaseController
import com.where.prateekyadav.myapplication.database.VisitedLocationInformation
import java.util.*


/**
 * Created by Infobeans on 1/23/2018.
 */
class VisitedActivity : AppCompatActivity() {
    var mLocationList: List<VisitedLocationInformation>? = null;
    var mIvMap:ImageView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visited)
        mIvMap=findViewById(R.id.iv_map) as ImageView
        if (intent.hasExtra(DBContract.VisitedLocationData.COLUMN_PLACE_ID)) {
            var placeID = intent.getStringExtra(DBContract.VisitedLocationData.COLUMN_PLACE_ID)
            mLocationList = DataBaseController(this).getVisitedLocationsFromPlaceid(placeID)
            val listView: ListView = findViewById<ListView>(R.id.lv_visited)
            listView.adapter = LocationsAdapter(mLocationList!!)
            listView!!.emptyView = findViewById(R.id.tv_no_records) as TextView
             val url = AppUtility().getStaticMapUrl(mLocationList!!.get(0).latitude.toString()
                     ,mLocationList!!.get(0).longitude.toString())
            GlideApp.with(this).load(url).into(mIvMap!!);

        }

    }

    inner class LocationsAdapter() : BaseAdapter() {
        var mContext: Context? = this@VisitedActivity;

        var inflater: LayoutInflater? = null
        var pref: MySharedPref? = null

        constructor(locationList: List<VisitedLocationInformation>) : this() {
            inflater = LayoutInflater.from(mContext);
            pref = MySharedPref.getinstance(mContext!!.applicationContext)
            pref!!.getFloat(AppConstant.SP_KEY_ACCURACY)
        }

        override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
            val mViewHolder: MyViewHolder
            var convertView = view
            if (convertView == null) {
                convertView = inflater!!.inflate(R.layout.layout_list_item_visited, parent, false)
                mViewHolder = MyViewHolder(convertView)
                convertView.setTag(mViewHolder)
            } else {
                mViewHolder = convertView.getTag() as MyViewHolder
            }

            val calToTime = Calendar.getInstance();
            val calFromTime = Calendar.getInstance();
            calToTime.timeInMillis = mLocationList!!.get(position).toTime
            calFromTime.timeInMillis = mLocationList!!.get(position).fromTime

            mViewHolder.tvOrgAddress.text = mLocationList!!.get(position).address
            mViewHolder.tvOrgVicinity.text = mLocationList!!.get(position).vicinity


            mViewHolder.tvTime.text = AppUtility().decorateFromAndToTime(mLocationList!!.get(position).fromTime,
                    mLocationList!!.get(position).toTime, mContext)
            mViewHolder.tvDate.text = AppUtility().getDecoratedDate(mLocationList!!.get(position).fromTime,
                    mContext)
            mViewHolder.tvVisitNo.text = mContext!!.getString(R.string.str_visits) + "${position+1}"


            return convertView!!
        }

        override fun getItem(p0: Int): Any {
            return mLocationList!!.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return mLocationList!!.size
        }

        private inner class MyViewHolder(item: View) {
            internal var tvOrgAddress: TextView
            internal var tvOrgVicinity: TextView
            internal var tvDate: TextView
            internal var tvTime: TextView
            internal var tvVisitNo: TextView

            init {
                tvOrgAddress = item.findViewById(R.id.tv_address) as TextView
                tvOrgVicinity = item.findViewById(R.id.tv_location_vicinity) as TextView
                tvDate = item.findViewById(R.id.tv_visit_date) as TextView
                tvTime = item.findViewById(R.id.tv_time) as TextView
                tvVisitNo = item.findViewById(R.id.tv_nearby_your_location) as TextView
            }
        }
    }


}