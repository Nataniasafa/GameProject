package com.natania.gameproject.binding


import androidx.databinding.Bindable
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

class LiveDataViewModel : ViewModel(){
    private val _likes = MutableLiveData(0)
    private val _unlikes = MutableLiveData(0)

    val likes : LiveData<Int> = _likes
    val unlikes : LiveData<Int> = _unlikes

    val popularity: LiveData<ObservableViewModel.LikesNumber> = Transformations.map(_likes){
        when{
            it > 9 -> ObservableViewModel.LikesNumber.STAR
            it > 5 -> ObservableViewModel.LikesNumber.POPULAR
            else -> ObservableViewModel.LikesNumber.NORMAL
        }
    }
    val worsty: LiveData<ObservableViewModel.UnlikesNumber> = Transformations.map(_unlikes){
        when{
            it > 9 -> ObservableViewModel.UnlikesNumber.WORST
            it > 5 -> ObservableViewModel.UnlikesNumber.BAD
            else -> ObservableViewModel.UnlikesNumber.NORMAL
        }
    }
    fun onLike(){
        _likes.value = (_likes.value ?:0) + 1
    }
    fun onUnlike(){
        _unlikes.value = (_unlikes.value ?:0) + 1
    }

}

class ObservableViewModel : ViewModel(){
    val likes = ObservableInt(0)
    val unlikes = ObservableInt(0)

    enum class LikesNumber {
        NORMAL,
        POPULAR,
        STAR
    }

    enum class UnlikesNumber {
        NORMAL,
        BAD,
        WORST
    }

    fun onLike(){
        likes.increment()
        notifyPropertyChanged(BR.popularity)
    }
    fun onUnlike(){
        unlikes.decrement()
        notifyPropertyChanged(BR.worsty)
    }
    @Bindable
    fun getPopularity(): LikesNumber{
        return  likes.get().let {
            when{
                it > 9 -> LikesNumber.STAR
                it > 5 -> LikesNumber.POPULAR
                else -> LikesNumber.NORMAL
            }
        }
    }
    @Bindable
    fun getWorsty(): UnlikesNumber{
        return  unlikes.get().let {
            when{
                it > 9 -> UnlikesNumber.WORST
                it > 5 -> UnlikesNumber.BAD
                else -> UnlikesNumber.NORMAL
            }
        }
    }
}

private fun ObservableInt.increment() {
    set(get()+1)
}
private fun ObservableInt.decrement() {
    set(get()+1)
}