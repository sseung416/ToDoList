package com.example.todolist.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableCompletableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subscribers.DisposableSubscriber

abstract class BaseViewModel : ViewModel() {
    private val disposable = CompositeDisposable( )

    protected fun addDisposable(flowable: Flowable<*>, nextListener: (Any)->Unit, failListener: (Throwable?)->Unit) {
        disposable.add(flowable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSubscriber<Any>() {
                override fun onNext(t: Any) {
                    nextListener(t)
                }

                override fun onError(t: Throwable?) {
                    failListener(t)
                }

                override fun onComplete() {}
            }))
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
            }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}