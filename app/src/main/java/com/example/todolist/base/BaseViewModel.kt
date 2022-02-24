package com.example.todolist.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableCompletableObserver
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseViewModel : ViewModel() {
    private val disposable = CompositeDisposable( )

    protected fun addDisposable(single: Single<*>, successListener: (Any)->Unit, errorListener: (Throwable)->Unit) {
        disposable.add(single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<Any>() {
                override fun onSuccess(t: Any) {
                    successListener(t)
                }

                override fun onError(e: Throwable) {
                    errorListener(e)
                }
            }) as Disposable)
    }

    protected fun addDisposable(completable: Completable, completeListener: ()->Unit, errorListener: (Throwable)->Unit) {
        disposable.add(completable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    completeListener()
                }

                override fun onError(e: Throwable) {
                    errorListener(e)
                }
            }) as Disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}