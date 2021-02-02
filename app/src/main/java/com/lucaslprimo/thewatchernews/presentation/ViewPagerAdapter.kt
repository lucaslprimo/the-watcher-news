package com.lucaslprimo.thewatchernews.presentation

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lucaslprimo.thewatchernews.R
import com.lucaslprimo.thewatchernews.presentation.objects.Category

class ViewPagerAdapter(val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var categories:MutableList<Category>? = null
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
       return TabFragment(categories?.get(position)!!)
    }

    override fun getCount(): Int {
        if(categories!=null){
            return categories!!.size
        }else{
            return 0
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        categories?.let {
            when(categories!![position]){
                Category.ENTERTAINMENT -> return context.getString(R.string.tab_entertainment)
                Category.TECHNOLOGY -> return context.getString(R.string.tab_tech)
                Category.BUSINESS -> return context.getString(R.string.tab_business)
                Category.WORLD -> return context.getString(R.string.tab_world)
                Category.SPORTS -> return context.getString(R.string.tab_sports)
                Category.POLITICS -> return context.getString(R.string.tab_politics)
                Category.BREAKINGNEWS -> return context.getString(R.string.tab_breaking)
            }
        }

        return ""
    }
}