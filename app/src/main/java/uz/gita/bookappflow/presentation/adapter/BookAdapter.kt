package uz.gita.bookappflow.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.bookappflow.R
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.databinding.ItemOwnBookBinding


class BookAdapter : ListAdapter<OwnBooksEntity, BookAdapter.BookViewHolder>(BookDiffUtil) {
    private var clickItemBookListener: ((OwnBooksEntity) -> Unit)? = null
    private var clickDeleteBookListener: ((Int) -> Unit)? = null
    private var clickEditeBookListener: ((OwnBooksEntity) -> Unit)? = null

    fun setClickListener(listener: (OwnBooksEntity) -> Unit) {
        clickItemBookListener = listener
    }

    fun setClickDeleteBookListener(listener: (Int) -> Unit) {
        clickDeleteBookListener = listener
    }

    fun setClickEditeBookListener(listener: (OwnBooksEntity) -> Unit) {
        clickEditeBookListener = listener
    }

    object BookDiffUtil : DiffUtil.ItemCallback<OwnBooksEntity>() {
        override fun areItemsTheSame(oldItem: OwnBooksEntity, newItem: OwnBooksEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: OwnBooksEntity, newItem: OwnBooksEntity): Boolean {
            return oldItem == newItem
        }

    }

    inner class BookViewHolder(binding: ItemOwnBookBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageBook = binding.ivItemBook
        private val titleBook = binding.tvItemTitleBook
        private val authorBook = binding.tvItemAuthor
        private val countPage = binding.tvCountPage
        private val mainLayout = binding.mainLayout

        init {
            binding.btnDelete.setOnClickListener { clickDeleteBookListener?.invoke(currentList[bindingAdapterPosition].id) }
            binding.btnEdite.setOnClickListener { clickEditeBookListener?.invoke(currentList[bindingAdapterPosition]) }
            itemView.setOnClickListener { clickItemBookListener?.invoke(currentList[bindingAdapterPosition]) }
        }

        fun bind(data: OwnBooksEntity) {
            if (data.status!=0)
                mainLayout.setBackgroundColor(itemView.context.getColor(R.color.item_text_grey))
            else
                mainLayout.setBackgroundColor(itemView.context.getColor(R.color.white))
            Glide.with(imageBook).load(R.drawable.image_book).into(imageBook)
            titleBook.text = data.title
            authorBook.text = data.author
            countPage.text = data.pageCount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            ItemOwnBookBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_own_book, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}