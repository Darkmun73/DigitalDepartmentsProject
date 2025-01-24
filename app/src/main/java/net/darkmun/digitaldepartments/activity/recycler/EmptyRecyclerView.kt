package net.darkmun.digitaldepartments.activity.recycler

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EmptyRecyclerView : RecyclerView {
    private var mEmptyView: View? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    private fun processEmptiness() {
        val zeroCount = adapter == null || adapter!!.itemCount == 0
        
        mEmptyView?.visibility = if (zeroCount) VISIBLE else GONE
        this.visibility = if (zeroCount) GONE else VISIBLE
    }

    private val observer: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            processEmptiness()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            processEmptiness()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            processEmptiness()
        }
    }

    override fun setAdapter(adapter: Adapter<ViewHolder>?) {
        val oldAdapter = getAdapter()
        super.setAdapter(adapter)

        oldAdapter?.unregisterAdapterDataObserver(observer)
        adapter?.registerAdapterDataObserver(observer)
    }

    fun setEmptyView(view: View?) {
        this.mEmptyView = view
        processEmptiness()
    }
}