import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealmate.R
import com.example.mealmate.models.NoticeModel

class NoticeAdapter(private val noticeList: ArrayList<NoticeModel>) :
    RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_notice_single, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNotice = noticeList[position]
        holder.tvLoadingNoticeTopic.text = currentNotice.nTopic
        holder.tvField1.text = currentNotice.nPlace
        holder.tvField2.text = currentNotice.nDate
    }

    override fun getItemCount(): Int {
        return noticeList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvLoadingNoticeTopic : TextView = itemView.findViewById(R.id.tvLoadingNoticeTopic)
        val tvField1 : TextView = itemView.findViewById(R.id.tvField1)
        val tvField2 : TextView = itemView.findViewById(R.id.tvField2)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    (itemView.context as onItemClickListener).onItemClick(position)
                }
            }
        }

    }

}
