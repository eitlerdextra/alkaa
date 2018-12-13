package com.escodro.alkaa.ui.category.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.escodro.alkaa.R
import com.escodro.alkaa.common.databinding.BindingHolder
import com.escodro.alkaa.data.local.model.Category
import com.escodro.alkaa.databinding.ItemCategoryBinding

/**
 * [RecyclerView.Adapter] to bind the [Category] in the [RecyclerView].
 */
class CategoryListAdapter : ListAdapter<Category, BindingHolder<ItemCategoryBinding>>
    (CategoryDiffCallback()) {

    var listener: CategoryListListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingHolder<ItemCategoryBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCategoryBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_category, parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder<ItemCategoryBinding>, position: Int) {
        val item = getItem(position)
        holder.binding.category = item
        holder.binding.imageviewItemcategoryOptions.setOnClickListener { view ->
            notifyMenuClicked(view, item)
        }
    }

    private fun notifyMenuClicked(view: View, category: Category) {
        listener?.onOptionMenuClicked(view, category)
    }

    /**
     * Listener responsible to callback interactions with [Category] item.
     */
    interface CategoryListListener {

        /**
         * Callback notified when one item of [android.widget.PopupMenu] is clicked.
         *
         * @param view the view clicked
         * @param category the category clicked
         */
        fun onOptionMenuClicked(view: View, category: Category)
    }
}
