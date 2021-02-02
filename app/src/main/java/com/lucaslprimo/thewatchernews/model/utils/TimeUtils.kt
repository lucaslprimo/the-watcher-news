package com.lucaslprimo.thewatchernews.model.utils

import android.content.Context
import com.lucaslprimo.thewatchernews.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class TimeUtils{

    companion object {

        private const val DATE_DD_MMM = "dd MMM"
        const val DATE_API = "yyyy-MM-dd'T'HH:mm:ss"

        fun getTimeAgo(date: Date,context: Context): String{
            val today =  Calendar.getInstance()
            today.time = Date()

            val selectedDate = Calendar.getInstance()
            selectedDate.time = date

            val minutes = TimeUnit.MILLISECONDS.toMinutes(today.timeInMillis) - TimeUnit.MILLISECONDS.toMinutes(selectedDate.timeInMillis)
            val hours = TimeUnit.MILLISECONDS.toHours(today.timeInMillis) - TimeUnit.MILLISECONDS.toHours(selectedDate.timeInMillis)
            val days = TimeUnit.MILLISECONDS.toDays(today.timeInMillis) - TimeUnit.MILLISECONDS.toDays(selectedDate.timeInMillis)

            when{
                days in 1..29 -> return if(days > 1){
                    String.format(context.getString(R.string.days_ago),days)
                }else{
                    context.getString(R.string.day_ago)
                }
                hours in 1..24 -> return if(hours > 1){
                    String.format(context.getString(R.string.hours_ago),hours)
                }else{
                    context.getString(R.string.hour_ago)
                }
                minutes in 1..60 -> return if(minutes > 1){
                    String.format(context.getString(R.string.minutes_ago),minutes)
                }else{
                    context.getString(R.string.minute_ago)
                }
                else -> return SimpleDateFormat(DATE_DD_MMM, Locale.getDefault()).format(date)
            }
        }
    }

}