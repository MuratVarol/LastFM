import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline observer: (t: T?) -> Unit) {
    observe(owner, Observer { observer(it) })
}

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer {
        it?.let(observer)
    })
}

operator fun <T> MutableLiveData<MutableList<T>>.plusAssign(values: MutableList<T>) {
    val value = this.value ?: mutableListOf()
    value.addAll(values)
    this.value = value
}