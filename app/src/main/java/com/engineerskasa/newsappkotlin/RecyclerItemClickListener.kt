package com.engineerskasa.newsappkotlin

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(
    context: Context,
    recyclerView: RecyclerView,
    private val listener: OnItemClicked
) : RecyclerView.SimpleOnItemTouchListener() {
    private val TAG = "RecItemClickListener"

    interface OnItemClicked {
        fun onItemClicked(view: View, position: Int)
    }

    val gestureDetector =
        GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                val childView = recyclerView.findChildViewUnder(e.x, e.y)
                if (childView != null) {
                    listener.onItemClicked(
                        childView,
                        recyclerView.getChildAdapterPosition(childView)
                    )
                }
                return true
            }
        })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val result = gestureDetector.onTouchEvent(e)
        return result
    }
}