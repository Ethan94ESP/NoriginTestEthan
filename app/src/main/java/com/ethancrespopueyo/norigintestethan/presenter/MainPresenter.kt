package com.ethancrespopueyo.norigintestethan.presenter

import com.ethancrespopueyo.norigintestethan.data.interactor.MainMvpInteractor
import com.ethancrespopueyo.norigintestethan.view.MainMvpView

class MainPresenter(var mainView: MainMvpView?, val mainInteractor: MainMvpInteractor) : MainMvpPresenter{

    override fun synchronizeJsonWithRoom() {

    }

    override fun getRecyclerViewItems() {

    }

}