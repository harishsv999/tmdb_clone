package com.harish.tmdb.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harish.tmdb.data.modals.Movies
import com.harish.tmdb.data.remote.modals.PopularMoviesList
import com.harish.tmdb.domain.repositories.MoviesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(private val moviesRepo: MoviesListRepository) :
    ViewModel() {

    private val _pageState = MutableLiveData<Boolean?>(null)
    val pageState: LiveData<Boolean?> = _pageState

    private val _moviesList = MutableLiveData<PopularMoviesList?>(null)
    val movies: LiveData<PopularMoviesList?> = _moviesList

    private val disposable = CompositeDisposable()

    fun initialize() {
        disposable.add(
            moviesRepo.findPopularMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    _pageState.value = false
                    _moviesList.value = it
                }, {
                    _pageState.value = true
                })
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}
