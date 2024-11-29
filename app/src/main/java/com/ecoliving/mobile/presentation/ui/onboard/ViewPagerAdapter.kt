package com.ecoliving.mobile.presentation.ui.onboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.ecoliving.R

class ViewPagerAdapter : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutId = when (position) {
            0 -> R.layout.onboard_pager_one
            1 -> R.layout.onboard_pager_two
            2 -> R.layout.onboard_pager_three
            else -> R.layout.onboard_pager_one
        }

        val item = LayoutInflater.from(container.context).inflate(
            layoutId, container,
            false
        )

        container.addView(item)
        return item
    }

    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
