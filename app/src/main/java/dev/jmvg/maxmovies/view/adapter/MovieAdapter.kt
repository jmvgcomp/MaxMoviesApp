import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.jmvg.maxmovies.api.MovieAPI.BASE_IMAGE_URL
import dev.jmvg.maxmovies.databinding.MovieItemBinding
import dev.jmvg.maxmovies.model.Movie


class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

class MoviesAdapter(val movieClickListener: (Int) -> Unit) : RecyclerView.Adapter<ViewHolder>() {


  val movieList: MutableList<Movie> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = MovieItemBinding.inflate(inflater, parent, false)
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    val item = movieList[position]
    Glide.with(holder.binding.root)
      .load("${BASE_IMAGE_URL}${item.poster_path}")
      .into(holder.binding.imageViewCover)
    holder.binding.imageViewCover.setOnClickListener{
      movieClickListener(item.id)
    }
  }

  override fun getItemCount() = movieList.size

  fun setItems(list: List<Movie>){
    movieList.addAll(list)
    notifyDataSetChanged()
  }
}