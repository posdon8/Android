import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learn.email.R

class EmailAdapter(private val emailList: List<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSenderIcon: TextView = itemView.findViewById(R.id.tvSenderIcon)
        val tvSenderName: TextView = itemView.findViewById(R.id.tvSenderName)
        val tvEmailSnippet: TextView = itemView.findViewById(R.id.tvEmailSnippet)
        val tvEmailContent: TextView = itemView.findViewById(R.id.tvEmailContent)
        val tvTimestamp: TextView = itemView.findViewById(R.id.tvTimestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_email, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailList[position]
        holder.tvSenderIcon.text = email.senderName.first().toString()
        holder.tvSenderName.text = email.senderName
        holder.tvEmailSnippet.text = email.snippet
        holder.tvEmailContent.text = email.content
        holder.tvTimestamp.text = email.timestamp
    }

    override fun getItemCount(): Int = emailList.size
}
